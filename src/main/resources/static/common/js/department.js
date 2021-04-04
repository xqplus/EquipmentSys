/**
 * 部门信息相关
 */

layui.use(['element', 'form', 'table', 'laydate', 'jquery'], function () {
    let element = layui.element, // 导航栏相关
        form = layui.form, // 表单相关
        table = layui.table, // 数据表格相关
        laydate = layui.laydate, // 日期选择框
        tableName = 'deptData';
    // 表单search监听
    form.on('submit(search)', function (data) {
        timeConverter(data);
        delete data.field.createTime; // 传入后台可能出现类型不匹配问题，删除
        // search 后表格数据重载
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
     * 数据表格渲染
     */
    function tableRender() {
        // 后端数据渲染
        table.render({
            elem: '#'+ tableName
            ,url: getUrl('/equipmentSys/department/page')
            ,method: 'GET'
            ,async: false
            ,height: 370
            ,parseData: function(res){ //res 即为原始返回的数据
                let result;
                if(this.page.curr){
                    result = res.data.slice(this.limit * (this.page.curr - 1), this.limit * this.page.curr);
                }
                else{
                    result=res.data.slice(0, this.limit);
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
            ,title: '部门信息表'
            ,page: true // 开启分页
            ,limit: 10
            ,cellMinWidth: 80 // 全局常规单元格最小宽度
            ,cols: [
                [{type: 'checkbox', fixed: 'left'}
                ,{type: 'numbers', title: '序号', fixed: 'left', sort: true, width: 80}
                ,{field: 'id', title: 'ID', sort: true, fixed: 'left', width: 80, hide: true} // 隐藏数据表id
                ,{field: 'deptNumber', title: '部门编号', sort: true}
                ,{field: 'deptName', title: '部门名称'}
                ,{field: 'deptIntroduce', title: '部门简介', width: 250}
                ,{field: 'createDate', title: '创建时间', sort: true}
                ,{field: 'updateDate', title: '修改时间', sort: true}
                ,{fixed: 'right', title:'操作', toolbar: '#rightToolBar', width: 120}]
            ]
        });
    }
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
                case 'add':
                    formDialog(
                        '新增部门信息'
                        , deptContent
                        , null
                        , null
                        , null
                        , 'roleType'
                        , getUrl('/equipmentSys/department/add')
                        , 'addDept'
                        , null
                        , tableName
                    );
                    break;

                case 'deleteBatch': // 批量删除

                    break;

                case 'exportExcel': // 导出列表Excel, 导出不需要刷新页面
                    $.each(data, function (i, val) {
                        ids.push(val.id);
                    });
                    if (ids.length === 0) {
                        // 在没有选定数据行的时候导出全部数据
                        exportExcel("是否导出全部部门信息？",
                            getUrl("/equipmentSys/department/exportExcel"), ids);
                        return;
                    }
                    // 选定行时导出选中的数据
                    exportExcel("是否导出选中部门信息？",
                        getUrl("/equipmentSys/department/exportExcel"), ids);
                    break;

                case 'importBatch': // 批量导入
                    break;
            }
        });
        // 监听行工具事件(编辑，删除)
        table.on('tool('+ tableName +')', function(obj){
            let data = obj.data; // 操作行数据
            if (obj.event === 'edit') {
                formDialog(
                    '编辑部门信息'
                    , deptContent
                    , null
                    , null
                    , null
                    , 'roleType'
                    , getUrl('/equipmentSys/department/update')
                    , 'editDept'
                    , data
                    , tableName
                );
            } else if (obj.event === 'del') {
                layer.confirm('确定删除部门 '+ data.deptName +' ？', function (index) {
                    $.ajax({
                        async: false,
                        type: 'POST',
                        url: getUrl('/equipmentSys/department/delete'),
                        data: {id: data.id},
                        success: function (data) {
                            layer.close(index);
                            if (data === 'existsUser') {
                                layer.alert('删除失败，该部门下存在用户；如果您仍要删除，请先慎重考虑后删除该部门下用户再执行该操作', {icon: 5});
                            }
                            if (data === 'success') {
                                layer.msg('删除成功', {icon: 1, time: 1000});
                                tableReload(tableName, {});
                            }
                            if (data === 'error') {
                                layer.msg('删除失败，请重试或联系管理员', {icon: 5, time: 1000});
                            }
                        },
                        error: function () {
                            layer.msg('系统错误，请联系管理员', {icon: 2, time: 1000});
                        }
                    });
                });
            }
        });
    }
});