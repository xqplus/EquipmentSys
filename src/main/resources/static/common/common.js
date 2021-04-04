
/* common 公共变量，方法 */

/**
 * 新增用户信息弹窗内容
 * @type {string}
 */
let addUserContent = '<form class="layui-col-md11 layui-form" id="dialogAddForm">\n' +
    '    <div class="layui-form-item">\n' +
    '        <label class="layui-form-label input-required">用户名：</label>\n' +
    '        <div class="layui-input-block">\n' +
    '            <input id="userName" type="text" class="layui-input" name="userName" required lay-verify="required" placeholder="请输入用户名" autocomplete="off"/>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '    <div class="layui-form-item">\n' +
    '        <label class="layui-form-label input-required">密码：</label>\n' +
    '        <div class="layui-input-block">\n' +
    '            <input id="pwd" type="password" class="layui-input" name="password" required lay-verify="required" placeholder="请输入密码"/>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '    <div class="layui-form-item">\n' +
    '        <label class="layui-form-label input-required">再次输入：</label>\n' +
    '        <div class="layui-input-block">\n' +
    '            <input id="pwd2" type="password" class="layui-input" name="password2" required lay-verify="required" placeholder="请再次输入密码"/>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '    <div class="layui-form-item">\n' +
    '        <label class="layui-form-label">真实姓名：</label>\n' +
    '        <div class="layui-input-block">\n' +
    '            <input class="layui-input" name="trueName" placeholder="请输入真实姓名" autocomplete="off"/>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '    <div class="layui-form-item">\n' +
    '        <label class="layui-form-label input-required">职位：</label>\n' +
    '        <div class="layui-input-block">\n' +
    '            <select name="roleType" id="roleType" lay-verify="required" lay-filter="roleType">\n' +
    '                <!-- 管理员，维修员注册需申请流程 可暂存redis -->\n' +
    '                <option value=""></option>\n' +
    '                <option value="0">管理员</option>\n' +
    '                <option value="1">普通用户</option>\n' +
    '                <option value="2">维修员</option>\n' +
    '            </select>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '    <div class="layui-form-item">\n' +
    '        <label class="layui-form-label input-required">部门：</label>\n' +
    '        <div class="layui-input-block">\n' +
    '            <select name="deptNumber" id="deptNumber" lay-verify="required">\n' +
    '                <option value=""></option>\n' +
    '                <option value="1001">管理一部</option>\n' +
    '                <option value="2001">用户一部</option>\n' +
    '                <option value="3001">维修一部</option>\n' +
    '            </select>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '    <div class="layui-form-item">\n' +
    '        <div class="dialog-btn-container">\n' +
    '            <button id="dialogSave" type="button" class="layui-btn layui-btn-normal" lay-submit lay-filter="dialogSave">保存</button>\n' +
    '            <button id="cancel" type="button" class="layui-btn layui-btn-primary">取消</button>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '</form>'

/**
 * 编辑用户信息弹窗内容
 * @type {string}
 */
let editUserContent = '<form class="layui-col-md11 layui-form" id="dialogAddForm">\n' +
    '    <div class="layui-form-item">\n' +
    '        <label class="layui-form-label input-required">用户名：</label>\n' +
    '        <div class="layui-input-block">\n' +
    '            <input id="userName" type="text" class="layui-input" name="userName" required lay-verify="required" placeholder="请输入用户名" autocomplete="off"/>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '    <div class="layui-form-item">\n' +
    '        <label class="layui-form-label input-required">密码：</label>\n' +
    '        <div class="layui-input-block">\n' +
    '            <input id="password" type="text" class="layui-input layui-btn-disabled" name="password" required lay-verify="required" placeholder="请输入密码"/>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '    <div class="layui-form-item">\n' +
    '        <label class="layui-form-label">真实姓名：</label>\n' +
    '        <div class="layui-input-block">\n' +
    '            <input id="trueName" class="layui-input" name="trueName" placeholder="请输入真实姓名" autocomplete="off"/>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '    <div class="layui-form-item">\n' +
    '        <label class="layui-form-label input-required">职位：</label>\n' +
    '        <div class="layui-input-block">\n' +
    '            <select name="roleType" id="roleType" lay-verify="required" lay-filter="roleType">\n' +
    '                <!-- 管理员，维修员注册需申请流程 可暂存redis -->\n' +
    '                <option value=""></option>\n' +
    '                <option value="0">管理员</option>\n' +
    '                <option value="1">普通用户</option>\n' +
    '                <option value="2">维修员</option>\n' +
    '            </select>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '    <div class="layui-form-item">\n' +
    '        <label class="layui-form-label input-required">部门：</label>\n' +
    '        <div class="layui-input-block">\n' +
    '            <select name="deptNumber" id="deptNumber" lay-verify="required">\n' +
    '                <option value=""></option>\n' +
    // '                <option value="1001">管理一部</option>\n' +
    // '                <option value="2001">用户一部</option>\n' +
    // '                <option value="3001">维修一部</option>\n' +
    '            </select>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '    <div class="layui-form-item">\n' +
    '        <div class="dialog-btn-container">\n' +
    '            <button id="dialogSave" type="button" class="layui-btn layui-btn-normal" lay-submit lay-filter="dialogSave">保存</button>\n' +
    '            <button id="cancel" type="button" class="layui-btn layui-btn-primary">取消</button>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '</form>'

/**
 * 部门信息弹窗内容,新增和编辑都可以用
 * @type {string}
 */
let deptContent = '<form class="layui-col-md11 layui-form" id="dialogAddForm">\n' +
    '    <input id="deptId" type="hidden" name="id" readonly>\n' +
    '    <div class="layui-form-item">\n' +
    '        <label class="layui-form-label input-required">部门类型：</label>\n' +
    '        <div class="layui-input-block">\n' +
    '            <select name="roleType" id="roleType" lay-verify="required" lay-filter="roleTypeDept">\n' +
    '                <option value=""></option>\n' +
    '                <option value="0">管理</option>\n' +
    '                <option value="1">用户</option>\n' +
    '                <option value="2">维修</option>\n' +
    '            </select>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '    <div class="layui-form-item">\n' +
    '        <label class="layui-form-label input-required">部门编号：</label>\n' +
    '        <div class="layui-input-block">\n' +
    '            <input id="deptNumber" type="text" readonly class="layui-input layui-disabled" name="deptNumber" lay-verify="required" placeholder="不可用,选择类型自动填充" autocomplete="off"/>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '    <div class="layui-form-item">\n' +
    '        <label class="layui-form-label input-required">部门名称：</label>\n' +
    '        <div class="layui-input-block">\n' +
    '            <input type="text" id="deptName" readonly class="layui-input layui-disabled" name="deptName" lay-verify="required" placeholder="不可用,选择类型自动填充" autocomplete="off"/>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '    <div class="layui-form-item">\n' +
    '        <label class="layui-form-label">部门简介：</label>\n' +
    '        <div class="layui-input-block">\n' +
    '            <textarea id="deptIntroduce" class="layui-textarea" name="deptIntroduce" rows="3"></textarea>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '    <div class="layui-form-item">\n' +
    '        <div class="dialog-btn-container">\n' +
    '            <button id="dialogSave" type="button" class="layui-btn layui-btn-normal" lay-submit lay-filter="dialogSave">保存</button>\n' +
    '            <button id="cancel" type="button" class="layui-btn layui-btn-primary">取消</button>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '</form>'

/**
 * 设备信息弹窗内容
 * @type {string}
 */
let equipContent = '<form class="layui-col-md11 layui-form" id="dialogAddForm">\n' +
    '    <input id="equipId" type="hidden" name="id" readonly>\n' +
    '    <div class="layui-form-item">\n' +
    '        <label class="layui-form-label input-required">设备编号：</label>\n' +
    '        <div class="layui-input-block">\n' +
    '            <input id="equipNumber" type="text" readonly class="layui-input layui-disabled" name="equipNumber" lay-verify="required" autocomplete="off"/>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '    <div class="layui-form-item">\n' +
    '        <label class="layui-form-label input-required">设备类型：</label>\n' +
    '        <div class="layui-input-block">\n' +
    '            <select name="equipTypeNumber" id="equipTypeNumber" lay-verify="required" lay-filter="equipTypeNumber">\n' +
    '                <option value=""></option>\n' +
    '                <option value="001">康明斯系列</option>\n' +
    '                <option value="002">HPT系列</option>\n' +
    '                <option value="003">VSI6X系列</option>\n' +
    '                <option value="004">LUM立式</option>\n' +
    '            </select>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '    <div class="layui-form-item">\n' +
    '        <label class="layui-form-label input-required">设备名称：</label>\n' +
    '        <div class="layui-input-block">\n' +
    '            <input type="text" id="equipName" class="layui-input" name="equipName" required lay-verify="required" placeholder="请输入设备名称" autocomplete="off"/>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '    <div class="layui-form-item">\n' +
    '        <label class="layui-form-label">设备概述：</label>\n' +
    '        <div class="layui-input-block">\n' +
    '            <textarea id="equipSummary" class="layui-textarea" name="equipSummary" rows="3"></textarea>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '    <div class="layui-form-item">\n' +
    '        <div class="dialog-btn-container">\n' +
    '            <button id="dialogSave" type="button" class="layui-btn layui-btn-normal" lay-submit lay-filter="dialogSave">保存</button>\n' +
    '            <button id="cancel" type="button" class="layui-btn layui-btn-primary">取消</button>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '</form>'

/**
 * 设备类型信息弹窗内容
 * @type {string}
 */
let equipTypeContent = '<form class="layui-col-md11 layui-form" id="dialogAddForm">\n' +
    '    <input id="equipTypeId" type="hidden" name="id" readonly>\n' +
    '    <div class="layui-form-item">\n' +
    '        <label class="layui-form-label input-required">设备类型编号：</label>\n' +
    '        <div class="layui-input-block">\n' +
    '            <input id="equipTypeNumber" name="equipTypeNumber" type="text" readonly class="layui-input layui-disabled" lay-verify="required" autocomplete="off"/>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '    <div class="layui-form-item">\n' +
    '        <label class="layui-form-label input-required">设备类型名称：</label>\n' +
    '        <div class="layui-input-block">\n' +
    '            <input id="equipTypeName" type="text" class="layui-input" name="equipTypeName" required lay-verify="required" placeholder="请输入设备类型名称" autocomplete="off"/>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '    <div class="layui-form-item">\n' +
    '        <label class="layui-form-label">设备类型概述：</label>\n' +
    '        <div class="layui-input-block">\n' +
    '            <textarea id="equipTypeSummary" class="layui-textarea" name="equipTypeSummary" rows="3"></textarea>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '    <div class="layui-form-item">\n' +
    '        <div class="dialog-btn-container">\n' +
    '            <button id="dialogSave" type="button" class="layui-btn layui-btn-normal" lay-submit lay-filter="dialogSave">保存</button>\n' +
    '            <button id="cancel" type="button" class="layui-btn layui-btn-primary">取消</button>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '</form>'

/**
 * 职位申请信息弹窗内容
 * @type {string}
 */
let applyContent = '<form class="layui-col-md11 layui-form" id="dialogAddForm">\n' +
    '    <input id="applyId" type="hidden" name="id" readonly>\n' +
    '    <div class="layui-form-item">\n' +
    '        <label class="layui-form-label input-required">申请编号：</label>\n' +
    '        <div class="layui-input-block">\n' +
    '            <input id="applyNumber" name="applyNumber" type="text" readonly class="layui-input layui-disabled" lay-verify="required" autocomplete="off" placeholder="不可编辑，根据职位部门自动生成"/>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '    <div class="layui-form-item">\n' +
    '        <label class="layui-form-label input-required">申请人：</label>\n' +
    '        <div class="layui-input-block">\n' +
    '            <input type="text" id="userName" class="layui-input layui-disabled" name="userName" readonly lay-verify="required" autocomplete="off"/>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '    <div class="layui-form-item">\n' +
    '        <label class="layui-form-label input-required">职位类型：</label>\n' +
    '        <div class="layui-input-block">\n' +
    '            <select name="applyType" lay-verify="required" lay-filter="applyType">\n' +
    '                <option value=""></option>\n' +
    '                <option value="0">管理员</option>\n' +
    '                <option value="2">维修员</option>\n' +
    '            </select>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '    <div class="layui-form-item">\n' +
    '        <label class="layui-form-label input-required">意向部门：</label>\n' +
    '        <div class="layui-input-block">\n' +
    '            <select name="deptNumber" id="deptNumber" lay-verify="required" lay-filter="deptNumber">\n' +
    '                <option value=""></option>\n' +
    '            </select>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '    <div class="layui-form-item">\n' +
    '        <label class="layui-form-label input-required">申请理由：</label>\n' +
    '        <div class="layui-input-block">\n' +
    '            <textarea id="applyReason" class="layui-textarea" required lay-verify="required" name="applyReason" rows="3"></textarea>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '    <div class="layui-form-item">\n' +
    '        <div class="dialog-btn-container">\n' +
    '            <button id="dialogSave" type="button" class="layui-btn layui-btn-normal" lay-submit lay-filter="dialogSave">保存</button>\n' +
    '            <button id="cancel" type="button" class="layui-btn layui-btn-primary">取消</button>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '</form>'

/**
 * 两次密码输入校验
 * @param pwdId1 input id1
 * @param pwdId2 input id2
 * @description 参数有先后顺序，再次输入密码框为后
 */
function passwordsInputCheck(pwdId1, pwdId2) {
    $(pwdId2).blur(function () {
        let pwd2 = $(pwdId2).val();
        let pwd = $(pwdId1).val();
        if (pwd2 !== pwd) {
            layer.msg('两次输入的密码不一致', {icon: 0, time: 1500});
            $(pwdId1).val("");
            $(pwdId2).val("");
        }
    });
}

/**
 * 验证用户是否存在
 * @param selector 选择器
 * @param boolean 为true时做用户名可用验证，false做用户存在验证，分别用于注册和密码找回
 * @description success回调函数返回exists即存在，返回noExists即不存在
 */
function userExistsCheck(selector, boolean) {
    $(selector).blur(function () {
        $.ajax({
            async: false,
            type: 'GET',
            url: getUrl('/equipmentSys/user/userCheck'),
            data: {
                userName: $(selector).val(),
            },
            success: function (data) {
                if (boolean) {
                    if (data === "exists") {
                        $(selector).val("");
                        layer.msg('用户名不可用', {icon: 0, time: 1500});
                    }
                } else {
                    if (data === "noExists") {
                        $(selector).val("");
                        layer.msg('该用户不存在', {icon: 0, time: 1500});
                    }
                }
            },
            error: function () {
                layer.msg('系统错误，请联系管理员', {icon: 2, time: 1500});
            }
        });
    });
}

/**
 * 监听表单提交事件（ajax）
 * @param filter 监听的事件
 * @param url ajax请求接口
 * @param boolean 为true时做注册回调，为false时做密码找回回调
 * @description 前提条件是经过表单验证，有密码输入框，id必须分别为pwd,pwd2，ajax请求类型限定POST
 */
function formSubmitEvent(filter, url, boolean) {
    layui.form.on('submit('+filter+')', function (data) {
        // 解决用户最后输入密码即提交无法校验两次密码输入的问题
        let pwd2 = data.field.password2;
        let pwd = data.field.password;
        if (pwd2 !== pwd) {
            layer.msg('两次输入的密码不一致', {icon: 0, time: 1500});
            $("#pwd").val("");
            $("#pwd2").val("");
        }
        delete data.field.password2;
        $.ajax({
            async: false, // 异步提交
            type: 'POST',
            url: getUrl(url),
            data: data.field,
            // dataType: 'json',
            success: function (data) {
                if (boolean) { // 注册
                    if (data === "success") {
                        layer.msg('注册成功，请返回登录', {icon: 1, time: 1000});
                        $('#userName').val("");
                    }
                    if (data === "error") {
                        layer.msg('注册失败，请重新注册或联系管理员', {icon: 5, time: 1500});
                    }
                } else { // 密码找回
                    if (data === "success") {
                        layer.msg('找回成功，请妥善保管您的密码', {icon: 1, time: 1000});
                        $('#userName').val("");
                    }
                    if (data === "noMatch") {
                        layer.msg('用户信息不匹配，请重试', {icon: 5, time: 1500});

                    }
                    if (data === "error") {
                        layer.msg('找回失败，请重试或联系管理员！', {icon: 5, time: 1500});
                    }
                }
            },
            error: function () {
                layer.msg('系统错误，请联系管理员', {icon: 2, time: 1500});
            }
        });
    });
}

/**
 * 角色部门级联（ajax）
 * @param filter 监听的事件
 * @description 前提经过表单校验，部门id必须为deptNumber，ajax请求类型限定GET
 */
function roleDeptCascade(filter) {
    layui.form.on('select('+filter+')', function (data) {
        // 这行代码解决重复选择角色导致部门option重复append的问题，思路为每次选择角色将部门置空
        $('#deptNumber').html("<option value=\"\"></option>");
        if (data.value !== '') {
            $.ajax({
                async: false, // 异步提交
                type: 'GET',
                url: getUrl('/equipmentSys/department/getDeptByRole'),
                data: {
                    role: data.value
                },
                // dataType: 'json',
                success: function (data) {
                    // 动态追加option和动态参数
                    $.each(data, function (index, item) {
                        $("#deptNumber").append(new Option(item.deptName, item.deptNumber));// 下拉菜单里添加元素
                    });
                    layui.form.render("select"); // layui select渲染
                },
                error: function () {
                    layer.msg('系统错误，请联系管理员', {icon: 2, time: 1500});
                }
            });
        }
    });
}

/**
 * 定制化layui表单信息弹窗
 * @param title 弹窗标题
 * @param content 弹窗内容
 * @param userNameSelector 用户名校验selector
 * @param pwdId1 密码校验框 id1
 * @param pwdId2 密码校验框 id2
 * @param filter 角色部门级联监听事件
 * @param saveUrl 保存事件ajax请求接口
 * @param type 弹窗类型 可选参数 'addUser','editUser','addDept','editDept','addEquip','editEquip'
 * @param data 数据参数
 * @param tableName 刷新表格的名称
 * @description 提交事件名必须为'dialogSave'，取消id必须为'cancel'
 */
function formDialog(title, content, userNameSelector, pwdId1, pwdId2, filter, saveUrl, type ,data, tableName) {
    let currIndex = layer.open({
        type: 1 // 解决弹出msg关闭弹窗的问题
        ,title: [title, 'font-size: 18px; text-align: center; margin:10px 0;']
        // content不能使用$('#dialogAddForm')的形式，会出现关闭弹窗再打开不显示的问题
        ,content: content
        ,area: '550px' // 宽  宽高：['width', 'height']
        // ,btn: ['保存', '取消'] // 按钮
        ,btn: false // 关闭弹窗自带按钮
        ,btnAlign: 'c' // 按钮居中对齐
        ,success: function () {
            layui.form.render('select'); // 渲染select 否则显示异常
            // 用户编辑时将行数据显示在表单上
            if (type === 'editUser') {
                $('#userName').val(data.userName);
                $('#password').val(data.password);
                $('#trueName').val(data.trueName);
            }
            if (type === 'addUser' || type === 'editUser') {
                // 输入用户名校验
                userExistsCheck(userNameSelector, true);
                // 输入密码校验
                passwordsInputCheck(pwdId1, pwdId2);
                // 角色部门级联
                roleDeptCascade(filter);
            }
            if (type === 'addDept' || type === 'editDept') {
                // 部门信息级联
                getNextDeptByRole();
            }
            if (type === 'editDept') {
                // 编辑时提交用于数据检索
                $('#deptId').val(data.id);
                $('#roleType').val()
                $('#deptNumber').val(data.deptNumber);
                $('#deptName').val(data.deptName);
                $('#deptIntroduce').val(data.deptIntroduce);
            }
            if (type === 'addEquip') {
                // 将下一个设备编号显示在表单上
                getNextEquipNumber();
            }
            if (type === 'editEquip') {
                $('#equipNumber').val(data.equipNumber);
                $('#equipTypeNumber').val(data.equipTypeNumber);
                $('#equipName').val(data.equipName);
                $('#equipSummary').val(data.equipSummary);
            }
            if (type === 'addEquipType') {
                // 将最新设备类型编号下一个显示在输入框
                getNextEquipTypeNumber();
            }
            if (type === 'editEquipType') {
                $('#equipTypeNumber').val(data.equipTypeNumber);
                $('#equipTypeName').val(data.equipTypeName);
                $('#equipTypeSummary').val(data.equipTypeSummary);
            }
            if (type === 'addApply' || type === 'editApply') {
                $('#userName').val(getCurrentUserInfo().userName);
                roleDeptCascade(filter);
                // 获取最新的申请编号
                getNextApplyNumberByDept();
            }
            if (type === 'editApply') {
                $('#applyNumber').val(data.applyNumber);
                $('#applyReason').val(data.applyReason);
            }
            // 弹窗成功后监听表单dialogSave提交
            layui.form.on('submit(dialogSave)', function (data) {
                $.ajax({
                    async: false,
                    type: 'POST',
                    url: saveUrl,
                    data: data.field,
                    success: function (data) {
                        layer.close(currIndex);
                        if (data === 'success') {
                            if (type.substring(0,3) === 'add') {
                                layer.msg('新增成功', {icon: 1, time: 1000});
                            }
                            if (type.substring(0,4) === 'edit') {
                                layer.msg('更新成功', {icon: 1, time: 1000});
                            }
                            // 重载
                            tableReload(tableName, {});
                        }
                        // 部门编辑变更时部门下有用户的情况
                        if (data === 'existsUser') {
                            layer.alert('更新失败，该部门下存在用户；如果您仍要变更，请先慎重考虑后删除该部门下用户再执行该操作！', {icon: 5});
                        }
                        // 设备类型信息变更时有当前类型设备存在的情况
                        if (data === 'existsEquip') {
                            layer.alert('更新失败，该类型下存在设备；如果您仍要变更，请先慎重考虑后删除属于该类型设备再执行该操作！', {icon: 5});
                        }
                        if (data === 'error') {
                            if (type.substring(0,3) === 'add') {
                                layer.msg('新增失败，请重试或联系管理员',{icon: 5, time: 1500});
                            }
                            if (type.substring(0,4) === 'edit') {
                                layer.msg('更新失败，请重试或联系管理员',{icon: 5, time: 1500});
                            }
                        }
                        if (data === 'applySuccess') {
                            layer.msg('申请成功', {icon: 1, time: 1000});
                            // 重载
                            tableReload(tableName, {});
                        }
                        if (data === 'existed') {
                            layer.msg('您当前有申请进行中，不可继续申请', {icon: 5, time: 1500});
                        }
                        if (data === 'conflict') {
                            layer.msg('您已经是该职位，不可重复申请', {icon: 5, time: 1500});
                        }
                        if (data === 'applyError') {
                            layer.msg('申请失败，请重试或联系管理员', {icon: 5, time: 1500});
                        }
                    },
                    error: function () {
                        layer.msg("系统错误，请联系管理员", {icon: 2, time: 1500});
                    }
                });
            });
            // 点击取消按钮关闭弹窗
            $('#cancel').click(function () {
                layer.close(currIndex);
            });
        }
    });
}

/**
 * 通过类型获取下一个部门信息，专用于部门模块
 * @description 为避免与部门模块角色部门级联冲突，监听事件名限定为roleTypeDept
 */
function getNextDeptByRole() {
    layui.form.on('select(roleTypeDept)', function (data) {
        if (data.value !== '') {
            $.ajax({
                async: false, // 异步提交
                type: 'GET',
                url: getUrl('/equipmentSys/department/getNextDeptByRole'),
                data: {
                    role: data.value
                },
                // dataType: 'json',
                success: function (data) {
                    // 将对应信息显示在表单上
                    $('#deptNumber').val(data.deptNumber);
                    $('#deptName').val(data.deptName);
                },
                error: function () {
                    layer.msg('系统错误，请联系管理员', {icon: 2, time: 1500});
                }
            });
        }
    });
}

/**
 * 获取最新的设备编号下一个，用于新增
 * @description 选择器限定'#equipNumber'
 */
function getNextEquipNumber() {
    $.ajax({
        async: false, // 异步提交
        type: 'GET',
        url: getUrl('/equipmentSys/equipment/getNextEquipNumber'),
        data: {
        },
        // dataType: 'json',
        success: function (data) {
            // 将对应信息显示在表单上
            $('#equipNumber').val(data.equipNumber);
        },
        error: function () {
            layer.msg('系统错误，请联系管理员', {icon: 2, time: 1500});
        }
    });
}

/**
 * 时间转换，用于查询传参
 * @param data 表单数据
 * @description yyyy-MM-dd -> timestamp
 */
function timeConverter(data) {
    // 选择时间才进行操作 否则时间转换出现NaN
    if (data.field.createTime !== '') {
        // 得到时间数组
        let arr = data.field.createTime.split(' - ');
        // 转换为Date
        let startTime = new Date(arr[0]);
        let endTime = new Date(arr[1]);
        // 转换为时间戳
        startTime = startTime.getTime();
        endTime = endTime.getTime();
        // 设置提交数据的值
        data.field.startTime = startTime;
        data.field.endTime = endTime;
    }
    if (data.field.approvalTime && data.field.approvalTime !== '') {

        let arr = data.field.approvalTime.split(' - ');

        let startTime = new Date(arr[0]);
        let endTime = new Date(arr[1]);

        startTime = startTime.getTime();
        endTime = endTime.getTime();

        data.field.startTime1 = startTime;
        data.field.endTime1 = endTime;
    }
}

/**
 * 获取最新的设备类型编号下一个，用于新增
 * @description 选择器限定'#equipTypeNumber'
 */
function getNextEquipTypeNumber() {
    $.ajax({
        async: false, // 异步提交
        type: 'GET',
        url: getUrl('/equipmentSys/equipmentType/getNextEquipTypeNumber'),
        data: {
        },
        // dataType: 'json',
        success: function (data) {
            // 将对应信息显示在表单上
            $('#equipTypeNumber').val(data.equipTypeNumber);
        },
        error: function () {
            layer.msg('系统错误，请联系管理员', {icon: 2, time: 1500});
        }
    });
}

/**
 * 通过部门编号获取最新设备编号，用于新建申请信息
 */
function getNextApplyNumberByDept() {
    layui.form.on('select(deptNumber)', function (data) {
        if (data.value !== '') {
            $.ajax({
                async: false, // 异步提交
                type: 'GET',
                url: getUrl('/equipmentSys/apply/getNextApplyNumberByDeptNumber'),
                data: {
                    deptNumber: data.value
                },
                // dataType: 'json',
                success: function (data) {
                    $('#applyNumber').val(data);
                },
                error: function () {
                    layer.msg('系统错误，请联系管理员', {icon: 2, time: 1500});
                }
            });
        }
    });
}

/**
 * 获取当前登录用户信息
 * @returns currentUserInfo
 */
function getCurrentUserInfo() {
    let currentUserInfo = null;
    $.ajax({
        async: false,
        type: 'GET',
        url: getUrl('/equipmentSys/user/getCurrentUserInfo'),
        success: function (data) {
            currentUserInfo = data;
        },
        error: function () {
            layer.msg('系统错误，请联系管理员', {icon: 2, time: 1500});
        }
    });
    return currentUserInfo;
}

/**
 * 设置页面导航栏徽章，即待处理事件数量
 */
function setBadge() {
    $.ajax({
        async: false,
        type: 'GET',
        url: getUrl('/equipmentSys/todoAndNotice/todo'),
        data: {},
        success: function (data) {
            if (data[0] > 0) { // 申请数
                $('#applyNum').addClass("layui-badge").html(data[0]);
            }
            if (data[1] > 0) { // 维修数
                $('#repairDot').addClass("layui-badge-dot");
                $('#repairNum').addClass("layui-badge").html(data[1]);
            }
        },
        error: function () {
            layer.msg('系统错误，请联系管理员', {icon: 2, time: 1500});
        }
    });
}

/**
 * 鼠标悬停用户上展示用户详细信息 选择器固定 不可拓展
 */
function userInfoShow() {
    // 鼠标悬停用户展示详细信息
    $('#user')
        .mouseover(function () {
            $('#userInfo').css('display', 'block');
        })
        .mouseleave(function () {
            $('#userInfo').css('display', 'none');
        });
}

/**
 * ajax 定制化封装方法
 * @param type 请求类型 'POST'
 * @param url 请求地址 'xxx/xxx'
 * @param requestData 请求参数 '{}'
 * @param successInfo 成功回调信息，根据场景不同自定义 'success message'
 * @param errorInfo 失败回调信息，根据场景不同自定义 'error message'
 * @param isReload 回调成功后是否需要刷新 true | false
 * @param tableId 刷新的表格id
 * @param where 刷新表格传参
 */
function myAjax(type, url, requestData, successInfo, errorInfo, isReload, tableId, where) {
    $.ajax({
        async: false,
        type: type,
        url: url,
        data: requestData,
        success: function (data) {
            if (data === 'success') {
                layer.msg(successInfo, {icon: 1, time: 1000});
                if (isReload) {
                    tableReload(tableId, where);
                }
            }
            if (data === 'error') {
                layer.msg(errorInfo, {icon: 5, time: 1500});
            }
        },
        error: function () {
            layer.msg('系统错误，请联系管理员', {icon: 2, time: 1500});
        }
    });
}

/**
 * 定制化excel导出基础方法
 * @param confirmMsg 询问内容
 * @param url 导出接口地址
 * @param param 传入参数
 */
function exportExcel(confirmMsg, url, param) {
    if(!$.isArray(param)) {
        param = [param];
    }
    layer.confirm(confirmMsg, {icon: 3, title: '提示'}, function (index) {
        window.open(url + "?ids=" + param);
        layer.close(index);
    });
}

/**
 * 接口数据返回格式限定及分页处理
 * @param res 返回数据
 * @param _this this
 * @deprecated <br> **分页无法使用**
 */
function parseRes(res, _this) {
    let result;
    if(_this.page.curr){
        result = res.data.slice(_this.limit * (_this.page.curr - 1), _this.limit * _this.page.curr);
    }
    else{
        result=res.data.slice(0, _this.limit);
    }
    return {
        "code": res.code,
        "msg": res.message, //解析提示文本
        "count": res.total, //解析数据长度
        "data": result //解析数据列表
    };
}

/**
 * url封装
 * @param url
 * @returns {string}
 */
function getUrl(url) {
    if (url.substring(0, 5) === '/jweb') return url;
    return '/jweb' + url;
}

/**
 * 定制化表格重载
 * @param tableId 表格id 如'id'
 * @param where 传参
 */
function tableReload(tableId, where) {
    layui.table.reload(tableId, {
        method: 'GET'
        ,where: where
        ,page: {
            curr: 1
        }
    });
}

/**
 * 搜索框点击重置刷新数据表格
 * @param func 匿名函数
 */
function resetTableReload(func) {
    $('#reset').click(function () {
        func();
    });
}