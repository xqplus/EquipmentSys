/**
 * 设备信息相关
 */

layui.use(['element', 'form', 'table', 'laydate', 'jquery'], function () {
    let element = layui.element, // 导航栏相关
        form = layui.form, // 表单相关
        table = layui.table, // 数据表格相关
        laydate = layui.laydate, // 日期选择框
        tableName = 'equipmentData';
    // 表单search监听
    form.on('submit(search)', function (data) {
        // 时间转换 string -> long
        timeConverter(data);
        delete data.field.createTime; // 传入后台可能出现类型不匹配问题，删除
        // search 后重载表格
        tableReload(tableName, data.field);
    });
    // 日期选择组件渲染
    laydate.render({
        elem: '#createTime',
        range: true,
        // eventElem: '#dateIcon',
        trigger: 'click'
    })
    tableRender();
    toolProcess();
    // 设置待处理事件 徽章
    setBadge();
    // 鼠标悬停显示用户详情
    userInfoShow();
    // 重置搜索框刷新数据表格
    resetTableReload(function () {
        tableRender();
    });

    /**
     * 头工具栏和行工具栏事件
     */
    function toolProcess() {
        // 头工具栏事件(新增)
        table.on('toolbar('+ tableName +')', function(obj){
            let checkStatus = table.checkStatus(obj.config.id) // 选中行信息
                ,data = checkStatus.data
                ,ids = [];

            switch(obj.event){
                case 'add': // 新增
                    formDialog(
                        '新增设备信息'
                        , equipContent
                        , null
                        , null
                        , null
                        , null
                        , getUrl('/equipmentSys/equipment/add')
                        , 'addEquip'
                        , null
                        , tableName
                    );
                    break;

                case 'deleteBatch': // 批量删除
                    $.each(data, function (i, val) {
                        ids.push(val.id);
                    });
                    if (ids.length === 0) {
                        layer.msg("请至少选择一行");
                        return;
                    }
                    layer.confirm('确定删除选中的设备信息？', {icon: 3, title: '提示'}, function (index) {
                        myAjax(
                            'POST'
                            , getUrl('/equipmentSys/equipment/deleteBatch')
                            , {ids: ids}
                            , '批量删除成功'
                            , '只能删除报废的设备，请重试'
                            , true
                            , tableName
                            , {}
                        );
                        layer.close(index);
                    });
                    break;

                case 'exportExcel': // Excel 导出
                    $.each(data, function (i, val) {
                        ids.push(val.id);
                    });
                    if (ids.length === 0) {
                        // 在没有选定数据行的时候导出全部数据
                        exportExcel("是否导出全部设备信息？",
                            getUrl("/equipmentSys/equipment/exportExcel"), ids);
                        return;
                    }
                    // 选定行时导出选中的数据
                    exportExcel("是否导出选中设备信息？",
                        getUrl("/equipmentSys/equipment/exportExcel"), ids);
                    break;

                case 'importBatch': // 批量导入

                    break;

                case 'exportTemplate': // 批量导入模板下载

                    break;
            }
        });
        // 监听行工具事件(编辑，报修，删除)
        table.on('tool(equipmentData)', function(obj){
            let data = obj.data; // 操作行数据

            if (obj.event === 'edit') {
                formDialog(
                    '编辑设备信息'
                    , equipContent
                    , null
                    , null
                    , null
                    , null
                    , getUrl('/equipmentSys/equipment/update')
                    , 'editEquip'
                    , data
                    , tableName
                );

            } else if (obj.event === 'del') {
                layer.confirm('确定删除设备 '+ data.equipName + ' ？', {icon: 3, title: '提示'}, function (index) {
                    myAjax(
                        'POST'
                        , getUrl('/equipmentSys/equipment/delete')
                        , {id: data.id}
                        , '删除成功'
                        , '删除失败，请重试或联系管理员'
                        , true
                        , tableName
                        , {}
                    );
                    layer.close(index);
                });

            } else if (obj.event === 'reportRepair') { // 报修
                layer.confirm('当前设备：'+data.equipTypeName+data.equipName+'，设备编号：'+data.equipNumber+'，确认报修？'
                    , {icon: 3, title: '提示'}, function (index) {
                    layer.prompt({
                        formType: 2,
                        maxlength: 200,
                        placeholder: '请简单描述一下故障原因...',
                        title: '故障描述',
                        area: ['500px', '300px'] //自定义文本域宽高
                    }, function(value, index1, elem){
                        data.faultRemark = value;

                        $.ajax({
                            async: false,
                            type: 'GET',
                            url: getUrl('/equipmentSys/equipment/reportRepair'), // 报修接口
                            data: {id: data.id, faultRemark: data.faultRemark},
                            success: function (data) {
                                layer.close(index);
                                if (data === 'success') {
                                    layer.msg('报修成功', {icon: 1, time: 1000});
                                    tableReload(tableName, {});
                                }
                                if (data === 'noProcess') {
                                    layer.msg('抱歉，当前设备状态不能报修', {icon: 5, time: 1000});
                                }
                                if (data === 'error') {
                                    layer.msg('报修失败，请重试或联系管理员', {icon: 5, time: 1000});
                                }
                            },
                            error: function () {
                                layer.msg('系统错误，请联系管理员', {icon: 2, time: 1000});
                            }
                        });
                        layer.close(index1);
                        layer.close(index);
                    });
                });
            }
        });
    }
    /**
     * 数据表格渲染
     */
    function tableRender() {
        // 后端数据渲染
        table.render({
            elem: '#'+ tableName
            ,url: getUrl('/equipmentSys/equipment/page')
            ,method: 'GET'
            ,async: false
            ,height: 370
            ,parseData: function(res){ //res 即为原始返回的数据
                let result;
                if(this.page.curr){
                    result = res.data.slice(this.limit * (this.page.curr - 1), this.limit * this.page.curr);
                }
                else{
                    result = res.data.slice(0, this.limit);
                }
                return {
                    "code": res.code,
                    "msg": res.message, //解析提示文本
                    "count": res.total, //解析数据长度
                    "data": result //解析数据列表
                };
            }
            ,toolbar: '#topToolBar' //开启头部工具栏，并为其绑定左侧模板
            ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示'
                ,layEvent: 'LAYTABLE_TIPS'
                ,icon: 'layui-icon-tips'
            }]
            ,title: '设备信息表'
            ,page: true // 开启分页
            ,limit: 10
            ,cellMinWidth: 80 // 全局常规单元格最小宽度
            ,cols: [
                [{type: 'checkbox', fixed: 'left'}
                ,{type: 'numbers', title: '序号', fixed: 'left', sort: true, width: 80}
                ,{field: 'id', title: 'ID', sort: true, fixed: 'left', width: 80, hide: true} // 隐藏数据表id
                ,{field: 'equipNumber', title: '设备编号', sort: true}
                ,{field: 'equipName', title: '设备名称'}
                ,{field: 'equipTypeNumber', title: '设备类型编号', sort: true, hide: true} // 隐藏列
                ,{field: 'equipTypeName', title: '设备类型名称'}
                ,{field: 'equipSummary', title: '设备概述'}
                ,{field: 'equipStateName', title: '设备状态', templet: '#equipStateName'}
                ,{field: 'createDate', title: '创建时间', sort: true}
                ,{field: 'updateDate', title: '修改时间', sort: true}
                ,{fixed: 'right', title:'操作', toolbar: '#rightToolBar', width: 120}]
            ]
        });
    }
});