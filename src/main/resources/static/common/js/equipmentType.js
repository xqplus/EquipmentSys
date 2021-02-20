/**
 * 设备类型信息相关
 */

layui.use(['element', 'form', 'table', 'laydate', 'jquery'], function () {
    let element = layui.element, // 导航栏相关
        form = layui.form, // 表单相关
        table = layui.table, // 数据表格相关
        laydate = layui.laydate, // 日期选择框
        $ = layui.jquery; // jquery
    // 表单search监听
    form.on('submit(search)', function (data) {
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
    // 设置待处理事件 徽章
    setBadge();

    /**
     * 数据表格渲染
     * @param where 参数
     */
    function tableRender(where) {
        // 后端数据渲染
        table.render({
            elem: '#equipTypeData'
            ,url:'/equipmentSys/equipmentType/page'
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
            ,title: '设备类型信息表'
            ,page: true // 开启分页
            ,limit: 10
            ,cellMinWidth: 80 // 全局常规单元格最小宽度
            ,cols: [
                [{type: 'checkbox', fixed: 'left'}
                ,{type: 'numbers', title: '序号', fixed: 'left', sort: true, width: 80}
                ,{field: 'id', title: 'ID', sort: true, fixed: 'left', hide: true} // 隐藏列
                ,{field: 'equipTypeNumber', title: '设备类型编号', sort: true, width: 130}
                ,{field: 'equipTypeName', title: '设备类型名称'}
                ,{field: 'equipTypeSummary', title: '设备类型概述', width: 300}
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
        table.on('toolbar(equipTypeData)', function(obj){
            let checkStatus = table.checkStatus(obj.config.id); // 选中行信息
            switch(obj.event){
                case 'add':
                    addFormDialog(layer, form, $,
                        '新增设备类型信息', equipTypeContent,
                        null,
                        null,
                        null,
                        null,
                        '/equipmentSys/equipmentType/add',
                        'addEquipType');
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
        // 监听行工具事件(编辑，删除)
        table.on('tool(equipTypeData)', function(obj){
            let data = obj.data; // 操作行数据
            if (obj.event === 'edit') {
                addFormDialog(layer, form, $,
                    '编辑设备类型信息', equipTypeContent,
                    null,
                    null,
                    null,
                    null,
                    '/equipmentSys/equipmentType/update',
                    'editEquipType', data);
            } else if (obj.event === 'del') {
                layer.confirm('确定删除设备类型 '+data.equipTypeName+' 的信息？', function (index) {
                    $.ajax({
                        async: false,
                        type: 'POST',
                        url: '/equipmentSys/equipmentType/delete',
                        data: {id: data.id},
                        success: function (data) {
                            layer.close(index);
                            if (data === 'existsEquip') {
                                layer.alert('删除失败，该类型下存在设备；如果您仍要删除，请先慎重考虑后删除该类型下设备再执行该操作！', {icon: 5});
                            }
                            if (data === 'success') {
                                layer.msg('删除成功', {icon: 1});
                                setTimeout(function () {
                                    window.location.reload();
                                }, 1500);
                            }
                            if (data === 'error') {
                                layer.msg('删除失败，请重试或联系管理员！', {icon: 2});
                            }
                        }
                    });
                });
            }
        });
    }
});