/**
 * 设备信息相关
 */

layui.use(['element', 'form', 'table', 'laydate', 'jquery'], function () {
    let element = layui.element, // 导航栏相关
        form = layui.form, // 表单相关
        table = layui.table, // 数据表格相关
        laydate = layui.laydate, // 日期选择框
        $ = layui.jquery; // jquery
    // 表单search监听
    form.on('submit(search)', function (data) {
        // 时间转换 string -> long
        timeConverter(data);
        delete data.field.createTime; // 传入后台可能出现类型不匹配问题，删除
        // search 后端数据渲染
        tableRender(data.field);
        toolProcess();
    });
    // 日期选择组件渲染
    laydate.render({
        elem: '#createTime',
        range: true,
        // eventElem: '#dateIcon',
        trigger: 'click'
    })
    tableRender({});
    toolProcess();

    /**
     * 头工具栏和行工具栏事件
     */
    function toolProcess() {
        // 头工具栏事件(新增)
        table.on('toolbar(equipmentData)', function(obj){
            let checkStatus = table.checkStatus(obj.config.id); // 选中行信息
            switch(obj.event){
                case 'add':
                    addFormDialog(layer, form, $,
                        '新增设备信息', equipContent,
                        null,
                        null,
                        null,
                        null,
                        '/equipmentSys/equipment/add',
                        'addEquip');
                    break;
                case 'getCheckLength':
                    var data = checkStatus.data;
                    layer.msg('选中了：'+ data.length + ' 个');
                    break;
                case 'isAll':
                    layer.msg(checkStatus.isAll ? '全选': '未全选');
                    break;
                //自定义头工具栏右侧图标 - 提示
                case 'LAYTABLE_TIPS':
                    layer.alert('这是工具栏右侧自定义的一个图标按钮');
                    break;
            }
        });
        // 监听行工具事件(编辑，报修，报废，删除)
        table.on('tool(equipmentData)', function(obj){
            let data = obj.data; // 操作行数据
            if (obj.event === 'edit') {
                addFormDialog(layer, form, $,
                    '编辑设备信息', equipContent,
                    null,
                    null,
                    null,
                    null,
                    '/equipmentSys/equipment/update',
                    'editEquip', data);
            } else if (obj.event === 'del') {
                layer.confirm('确定删除设备 '+ data.equipTypeName + data.equipName +' 的信息？', {icon: 3, title: '警告'}, function (index) {
                    $.ajax({
                        async: false,
                        type: 'POST',
                        url: '/equipmentSys/equipment/delete',
                        data: {id: data.id},
                        success: function (data) {
                            layer.close(index);
                            if (data === 'success') {
                                layer.msg('删除成功', {icon: 1});
                                setTimeout(function () {
                                    window.location.reload();
                                }, 3000);
                            }
                            if (data === 'error') {
                                layer.msg('删除失败，请重试或联系管理员！', {icon: 2});
                            }
                        }
                    });
                });
            } else if (obj.event === 'reportRepair') { // 报修
                layer.confirm('当前设备：'+data.equipTypeName+data.equipName+'，设备编号：'+data.equipNumber+'，确认报修？', {icon: 3, title: '提示'}, function (index) {
                    $.ajax({
                        async: false,
                        type: 'GET',
                        url: '/equipmentSys/equipment/reportRepair', // 报修接口
                        data: {id: data.id},
                        success: function (data) {
                            layer.close(index);
                            if (data === 'success') {
                                layer.msg('报修成功', {icon: 1});
                                setTimeout(function () {
                                    window.location.reload();
                                }, 3000);
                            }
                            if (data === 'noProcess') {
                                layer.msg('抱歉，当前设备状态不能报修', {icon: 2});
                            }
                            if (data === 'error') {
                                layer.msg('报修失败，请重试或联系管理员！', {icon: 2});
                            }
                        }
                    });
                });
            } else if (obj.event === 'scrap') { // 报废
                layer.confirm('当前设备：'+data.equipTypeName+data.equipName+'，设备编号：'+data.equipNumber+'，确认报废？', {icon: 3, title: '警告'}, function (index) {
                    $.ajax({
                        async: false,
                        type: 'GET',
                        url: '/equipmentSys/equipment/scrap', // 报废接口
                        data: {id: data.id},
                        success: function (data) {
                            layer.close(index);
                            if (data === 'success') {
                                layer.msg('报废成功', {icon: 1});
                                setTimeout(function () {
                                    window.location.reload();
                                }, 3000);
                            }
                            if (data === 'noProcess') {
                                layer.msg('抱歉，当前设备状态不能报废', {icon: 2});
                            }
                            if (data === 'error') {
                                layer.msg('报修失败，请重试或联系管理员！', {icon: 2});
                            }
                        }
                    });
                });
            }
        });
    }
    /**
     * 数据表格渲染
     * @param where 查询传参
     */
    function tableRender(where) {
        // 后端数据渲染
        table.render({
            elem: '#equipmentData'
            ,url:'/equipmentSys/equipment/page'
            ,method: 'GET'
            ,async: false
            ,where: where // 携带参数
            ,height: 370
            ,parseData: function(res){ //res 即为原始返回的数据
                return {
                    "code": res.maxLimit, //解析接口状态
                    "msg": res.countId, //解析提示文本
                    "count": res.total, //解析数据长度
                    "data": res.records, //解析数据列表
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
                ,{field: 'equipTypeNumber', title: '设备类型编号', sort: true, hide: true} // 隐藏列
                ,{field: 'equipTypeName', title: '设备类型名称'},{field: 'equipName', title: '设备名称'}
                ,{field: 'equipSummary', title: '设备概述'},{field: 'equipStateName', title: '设备状态'}
                ,{field: 'createDate', title: '创建时间', sort: true}
                ,{field: 'updateDate', title: '修改时间', sort: true}
                ,{fixed: 'right', title:'操作', toolbar: '#rightToolBar', width: 200}]
            ]
        });
    }
});