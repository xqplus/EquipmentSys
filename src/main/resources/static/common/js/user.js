
/**
 * 用户信息相关
 */

layui.use(['element', 'table', 'laydate', 'form', 'jquery'], function(){
    let element = layui.element // 导航栏相关
        ,table = layui.table // 数据表格
        ,laydate = layui.laydate // 日期选择
        ,form = layui.form // 表单
        ,$ = layui.jquery;
    // 表单search监听
    form.on('submit(search)', function (data) {
        timeConverter(data);
        delete data.field.createTime; // 传入后台可能出现类型不匹配问题，删除
        // search 后端数据渲染 TODO 表格数据重载
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
    // 后端数据渲染
    tableRender({});
    toolProcess();
    // 设置待处理事件 徽章
    setBadge();
    // 鼠标悬停显示用户详情
    userInfoShow($);

    /**
     * 数据表格渲染
     * @param where 参数
     */
    function tableRender(where) {
        // 后端数据渲染
        table.render({
            elem: '#userData'
            ,id: 'userData'
            ,url:'/equipmentSys/user/page'
            ,method: 'GET'
            ,async: false
            ,where: where
            ,height: 370
            ,parseData: function(res){ //res 即为原始返回的数据
                let result;
                if(this.page.curr){
                    result = res.data.slice(this.limit*(this.page.curr-1),this.limit*this.page.curr);
                }
                else{
                    result=res.data.slice(0,this.limit);
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
            ,title: '用户信息表'
            ,page: true // 开启分页
            ,limit: 10
            ,cellMinWidth: 80 // 全局常规单元格最小宽度
            ,cols: [
                [{type: 'checkbox', fixed: 'left'}
                ,{type: 'numbers', title: '序号', fixed: 'left', sort: true, width: 80}
                ,{field: 'id', title: 'ID', sort: true, fixed: 'left', width: 80, hide: true} // 隐藏数据表id
                ,{field: 'userNumber', title: '用户编号', sort: true}
                ,{field: 'userName', title: '用户名'}
                ,{field: 'password', title: '密码'}
                ,{field: 'trueName', title: '真实姓名'}
                ,{field: 'roleName', title: '角色名称'}
                ,{field: 'deptName', title: '所在部门'}
                ,{field: 'createDate', title: '创建时间', sort: true}
                ,{field: 'updateDate', title: '修改时间', sort: true}
                ,{fixed: 'right', title:'操作', toolbar: '#rightToolBar'}]
            ]
        });
    }
    /**
     * 头工具栏和行工具栏事件
     */
    function toolProcess() {
        // 头工具栏事件
        table.on('toolbar(userData)', function(obj){
            let checkStatus = table.checkStatus(obj.config.id)
                ,data = checkStatus.data // 选中行信息
                ,ids = [];

            switch(obj.event){
                case 'add': // 用户新增
                    addFormDialog(layer, form, $,
                        '新增用户信息', addUserContent,
                        '#userName',
                        '#pwd',
                        '#pwd2',
                        'roleType',
                        '/equipmentSys/user/add',
                        'addUser');
                    break;

                case 'deleteBatch': // 批量删除
                    $.each(data, function (i, val) {
                        ids.push(val.id);
                    });
                    if (ids.length === 0) {
                        layer.msg("请至少选择一行");
                        return;
                    }
                    layer.confirm('确定删除选中的用户信息？', {icon: 3, title: '提示'}, function (index) {
                        myAjax('POST'
                            , '/equipmentSys/user/deleteBatch'
                            , {ids: ids}, '批量删除成功'
                            , '批量删除失败，请重试或联系管理员'
                            , true
                        );
                        //layer.close(index);
                    });
                    break;

                case 'exportExcel': // 导出列表Excel, 导出不需要刷新页面
                    $.each(data, function (i, val) {
                        ids.push(val.id);
                    });
                    if (ids.length === 0) {
                        // 在没有选定数据行的时候导出全部数据
                        exportExcel("是否导出全部用户信息？",
                            "/equipmentSys/user/exportExcel", ids);
                        return;
                    }
                    // 选定行时导出选中的数据
                    exportExcel("是否导出选中用户信息？",
                        "/equipmentSys/user/exportExcel", ids);
                    break;

                case 'importBatch': // 批量导入
                    break;
            }
        });
        // 监听行工具事件
        table.on('tool(userData)', function(obj){
            let data = obj.data; // 操作行数据
            if (obj.event === 'edit') {
                addFormDialog(layer, form, $,
                    '编辑用户信息', editUserContent,
                    '#userName',
                    '#pwd',
                    '#pwd2',
                    'roleType',
                    '/equipmentSys/user/update',
                    'editUser', data);
            } else if (obj.event === 'del') {
                layer.confirm('确定删除用户 '+data.userName+' 的信息？', function (index) {
                    $.ajax({
                        async: false,
                        type: 'POST',
                        url: '/equipmentSys/user/delete',
                        data: {id: data.id},
                        success: function (data) {
                            layer.close(index);
                            if (data === 'success') {
                                layer.msg('删除成功', {icon: 1});
                                setTimeout(function () {
                                    window.location.reload();}, 1500);
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

