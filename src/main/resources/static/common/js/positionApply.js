/**
 * 申请信息相关
 */

layui.use(['element', 'form', 'table', 'laydate', 'jquery'], function () {
    let element = layui.element, // 导航栏相关
        form = layui.form, // 表单相关
        table = layui.table, // 数据表格相关
        laydate = layui.laydate, // 日期选择框
        tableName = 'applyData';
    // 表单search监听
    form.on('submit(search)', function (data) {
        // 时间转换 string -> long
        methods.timeConverter(data);
        delete data.field.createTime;
        delete data.field.approvalTime;

        methods.tableReload(tableName, data.field);
    });
    // 日期选择组件渲染
    laydate.render({
        elem: '#createTime',
        range: true,
        // eventElem: '#dateIcon',
        trigger: 'click'
    });
    laydate.render({
        elem: '#approvalTime',
        range: true,
        // eventElem: '#dateIcon',
        trigger: 'click'
    });
    tableRender();
    toolProcess();
    // 设置待处理事件 徽章
    methods.setBadge();
    // 鼠标悬停显示用户详情
    methods.userInfoShow();
    // 重置搜索框刷新数据表格
    methods.resetTableReload(function () {
        tableRender();
    });

    /**
     * 头工具栏和行工具栏事件
     */
    function toolProcess() {
        // 头工具栏事件(新增)
        table.on('toolbar('+ tableName +')', function(obj){
            let checkStatus = table.checkStatus(obj.config.id) // 选中行信息
                ,data = checkStatus.data;

            switch(obj.event){
                case 'apply':
                    methods.formDialog(
                        '新建职位申请'
                        , applyContent
                        , null
                        , null
                        , null
                        , 'applyType'
                        , methods.getUrl('/equipmentSys/apply/add')
                        , 'addApply'
                        , null
                        , tableName
                    );
                    break;

                case 'getCheckLength':
                    layer.msg('选中了：'+ data.length + ' 个');
                    break;

                case 'isAll':
                    layer.msg(checkStatus.isAll ? '全选': '未全选');
                    break;

                case 'LAYTABLE_TIPS':
                    layer.alert('这是工具栏右侧自定义的一个图标按钮');
                    break;
            }
        });
        // 监听行工具事件(编辑，通过，驳回，删除)
        table.on('tool('+ tableName +')', function(obj){
            let data = obj.data; // 操作行数据

            if (obj.event === 'edit') {
                methods.formDialog(
                    '编辑职位申请'
                    , applyContent
                    , null
                    , null
                    , null
                    , 'applyType'
                    , methods.getUrl('/equipmentSys/apply/update')
                    , 'editApply'
                    , data
                    , tableName
                );

            } else if (obj.event === 'del') {
                layer.confirm('当前申请编号：'+ data.applyNumber +'，申请人：'+ data.userName +'，申请职位：' + data.applyTypeName
                    +'，申请状态：'+ data.applyStateName +'，确认删除？', {icon: 3, title: '提示'}, function (index) {
                    methods.myAjax(
                        'POST'
                        , methods.getUrl('/equipmentSys/apply/delete')
                        , {id: data.id}
                        , '删除成功'
                        , '删除失败，请重试或联系管理员'
                        , true
                        , tableName
                        , {}
                    );
                    layer.close(index);
                });

            } else if (obj.event === 'pass') { // 审批通过
                layer.confirm('当前申请编号：'+ data.applyNumber
                    +'，申请人：'+ data.userName +'，申请职位：' + data.applyTypeName
                    +'，申请理由：'+ data.applyReason +'，确认通过？', {icon: 3, title: '提示'}, function (index) {
                    layer.prompt({
                        formType: 2,
                        maxlength: 200,
                        value: '予以通过',
                        title: '通过审批意见',
                        area: ['500px', '300px'] //自定义文本域宽高
                    }, function(value, index1, elem){
                        data.approvalOpinion = value;
                        data.approverName = methods.getCurrentUserInfo().userName;

                        methods.myAjax(
                            'GET'
                            , methods.getUrl('/equipmentSys/apply/pass')
                            , data
                            , '审批通过成功'
                            , '审批失败，请重试或联系管理员'
                            , true
                            , tableName
                            , {}
                        );
                        layer.close(index1);
                        layer.close(index);
                    });
                });

            } else if (obj.event === 'reject') { // 审批驳回
                layer.confirm('当前申请编号：'+ data.applyNumber
                    +'，申请人：'+ data.userName +'，申请职位：' + data.applyTypeName
                    +'，申请理由：'+ data.applyReason +'，确认驳回？', {icon: 3, title: '警告'}, function (index) {
                    layer.prompt({
                        formType: 2,
                        maxlength: 200,
                        value: '予以驳回',
                        title: '驳回审批意见',
                        area: ['500px', '300px'] //自定义文本域宽高
                    }, function(value, index1, elem){
                        data.approvalOpinion = value;
                        data.approverName = methods.getCurrentUserInfo().userName;

                        methods.myAjax(
                            'GET'
                            , methods.getUrl('/equipmentSys/apply/reject')
                            , data
                            , '审批驳回成功'
                            , '审批失败，请重试或联系管理员'
                            , true
                            , tableName
                            , {}
                        );
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
            ,url: methods.getUrl('/equipmentSys/apply/page')
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
            ,title: '职位申请信息表'
            ,page: true // 开启分页
            ,limit: 10
            ,cellMinWidth: 80 // 全局常规单元格最小宽度
            ,cols: [
                [{type: 'checkbox', fixed: 'left'}
                    ,{type: 'numbers', title: '序号', fixed: 'left', sort: true, width: 80}
                    ,{field: 'id', title: 'ID', sort: true, fixed: 'left', width: 80, hide: true} // 隐藏数据表id
                    ,{field: 'applyNumber', title: '申请编号', sort: true}
                    ,{field: 'userName', title: '申请人'}
                    ,{field: 'applyTypeName', title: '申请职位'}
                    ,{field: 'roleName', title: '当前职位'}
                    ,{field: 'deptName', title: '意向部门'}
                    ,{field: 'applyReason', title: '申请理由'}
                    ,{field: 'applyStateName', title: '申请状态',templet: '#applyStateName'}
                    ,{field: 'createDate', title: '申请时间', sort: true}
                    ,{field: 'approverName', title: '审批人'}
                    ,{field: 'updateDate', title: '审批时间', sort: true}
                    ,{field: 'approvalOpinion', title: '审批意见'}
                    ,{fixed: 'right', title:'操作', toolbar: '#rightToolBar', width: 110}]
            ]
        });
    }
});