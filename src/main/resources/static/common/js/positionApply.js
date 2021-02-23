/**
 * 申请信息相关
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
        if (data.field.approvalTime !== '') { // 选择时间才进行操作 否则时间转换出现NaN
            let arr = data.field.approvalTime.split(' - '); // 得到时间数组
            let startTime = new Date(arr[0]); // 转换为Date
            let endTime = new Date(arr[1]);
            startTime = startTime.getTime(); // 转换为时间戳
            endTime = endTime.getTime();
            data.field.startTime1 = startTime; // 设置提交数据的值
            data.field.endTime1 = endTime;
        }
        delete data.field.createTime; // 传入后台可能出现类型不匹配问题，删除
        delete data.field.approvalTime;
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
    });
    laydate.render({
        elem: '#approvalTime',
        range: true,
        // eventElem: '#dateIcon',
        trigger: 'click'
    });
    tableRender({});
    toolProcess();
    // 设置待处理事件 徽章
    setBadge();
    // 鼠标悬停显示用户详情
    userInfoShow($);

    /**
     * 头工具栏和行工具栏事件
     */
    function toolProcess() {
        // 头工具栏事件(新增)
        table.on('toolbar(applyData)', function(obj){
            let checkStatus = table.checkStatus(obj.config.id); // 选中行信息
            switch(obj.event){
                case 'apply':
                    addFormDialog(layer, form, $,
                        '新建职位申请', applyContent,
                        null,
                        null,
                        null,
                        'applyType',
                        '/equipmentSys/apply/add',
                        'addApply');
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
        // 监听行工具事件(编辑，通过，驳回，删除)
        table.on('tool(applyData)', function(obj){
            let data = obj.data; // 操作行数据
            if (obj.event === 'edit') {
                addFormDialog(layer, form, $,
                    '编辑职位申请', applyContent,
                    null,
                    null,
                    null,
                    'applyType',
                    '/equipmentSys/apply/update',
                    'editApply', data);
            } else if (obj.event === 'del') {
                layer.confirm('当前申请编号：'+ data.applyNumber +'，申请人：'+ data.userName +'，申请职位：' + data.applyTypeName
                    +'，申请状态：'+ data.applyStateName +'，确认删除？', {icon: 3, title: '警告'}, function (index) {
                    $.ajax({
                        async: false,
                        type: 'POST',
                        url: '/equipmentSys/apply/delete',
                        data: {id: data.id},
                        success: function (data) {
                            layer.close(index);
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
                        data.approverName = getCurrentUserInfo().userName;
                        $.ajax({
                            async: false,
                            type: 'GET',
                            url: '/equipmentSys/apply/pass',
                            data: data,
                            success: function (data) {
                                if (data === 'success') {
                                    layer.msg('审批通过成功', {icon: 1});
                                    setTimeout(function () {
                                        window.location.reload();
                                    }, 1500);
                                }
                                if (data === 'error') {
                                    layer.msg('审批失败，请重试或联系管理员！', {icon: 2});
                                }
                            }
                        });
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
                        data.approverName = getCurrentUserInfo().userName;
                        $.ajax({
                            async: false,
                            type: 'GET',
                            url: '/equipmentSys/apply/reject',
                            data: data,
                            success: function (data) {
                                if (data === 'success') {
                                    layer.msg('审批驳回成功', {icon: 1});
                                    setTimeout(function () {
                                        window.location.reload();
                                    }, 1500);
                                }
                                if (data === 'error') {
                                    layer.msg('审批失败，请重试或联系管理员！', {icon: 2});
                                }
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
     * @param where 查询传参
     */
    function tableRender(where) {
        // 后端数据渲染
        table.render({
            elem: '#applyData'
            ,url:'/equipmentSys/apply/page'
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