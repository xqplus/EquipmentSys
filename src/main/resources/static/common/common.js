
/* common 公共变量，方法 */

/**
 * 新增用户信息弹窗内容
 * @type {string}
 */
let addUserContent = '<form class="layui-form" id="dialogAddForm">\n' +
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
    '                <!-- TODO 管理员，维修员注册需申请流程 可暂存redis -->\n' +
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
let editUserContent = '<form class="layui-form" id="dialogAddForm">\n' +
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
    '                <!-- TODO 管理员，维修员注册需申请流程 可暂存redis -->\n' +
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
let deptContent = '<form class="layui-form" id="dialogAddForm">\n' +
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
let equipContent = '<form class="layui-form" id="dialogAddForm">\n' +
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
let equipTypeContent = '<form class="layui-form" id="dialogAddForm">\n' +
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
let applyContent = '<form class="layui-form" id="dialogAddForm">\n' +
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
 * @param $ layui jquery组件
 * @param pwdId1 input id1
 * @param pwdId2 input id2
 * @description 参数有先后顺序，再次输入密码框为后
 */
function passwordsInputCheck($, pwdId1, pwdId2) {
    $(pwdId2).blur(function () {
        let pwd2 = $(pwdId2).val();
        let pwd = $(pwdId1).val();
        if (pwd2 !== pwd) {
            layer.msg('两次输入的密码不一致', {icon: 0});
            $(pwdId1).val("");
            $(pwdId2).val("");
        }
    });
}

/**
 * 验证用户是否存在
 * @param $ layui jquery组件
 * @param selector 选择器
 * @param boolean 为true时做用户名可用验证，false做用户存在验证，分别用于注册和密码找回
 * @description success回调函数返回exists即存在，返回noExists即不存在
 */
function userExistsCheck($, selector, boolean) {
    $(selector).blur(function () {
        $.ajax({
            async: false,
            type: 'GET',
            url: '/equipmentSys/user/userCheck',
            data: {
                userName: $(selector).val(),
            },
            success: function (data) {
                if (boolean) {
                    if (data === "exists") {
                        $(selector).val("");
                        layer.msg('用户名不可用', {icon: 2});
                    }
                } else {
                    if (data === "noExists") {
                        $(selector).val("");
                        layer.msg('该用户不存在', {icon: 2});
                    }
                }
            }
        });
    });
}

/**
 * 监听表单提交事件（ajax）
 * @param form layui form组件
 * @param filter 监听的事件
 * @param url ajax请求接口
 * @param boolean 为true时做注册回调，为false时做密码找回回调
 * @description 前提条件是经过表单验证，有密码输入框，id必须分别为pwd,pwd2，ajax请求类型限定POST
 */
function formSubmitEvent(form, filter, url, boolean) {
    form.on('submit('+filter+')', function (data) {
        // 解决用户最后输入密码即提交无法校验两次密码输入的问题 TODO 复用
        let pwd2 = data.field.password2;
        let pwd = data.field.password;
        if (pwd2 !== pwd) {
            layer.msg('两次输入的密码不一致', {icon: 0});
            $("#pwd").val("");
            $("#pwd2").val("");
        }
        delete data.field.password2;
        $.ajax({
            async: false, // 异步提交
            type: 'POST',
            url: url,
            data: data.field,
            // dataType: 'json',
            success: function (data) {
                if (boolean) { // 注册
                    if (data === "success") {
                        layer.alert('注册成功，请返回登录~', {icon: 1});
                    }
                    if (data === "error") {
                        layer.alert('注册失败，请重新注册或联系管理员！', {icon: 5});
                    }
                } else { // 密码找回
                    if (data === "success") {
                        layer.alert('找回成功，请妥善保管您的密码~', {icon: 1});
                    }
                    if (data === "noMatch") {
                        layer.alert('用户信息不匹配，请重试', {icon: 0});
                        form.reset();
                    }
                    if (data === "error") {
                        $(form).val("");
                        layer.alert('找回失败，请重试或联系管理员！', {icon: 5});
                    }
                }
            }
        });
    });
}

/**
 * 角色部门级联（ajax）
 * @param form layui form组件
 * @param $ layui jquery组件
 * @param filter 监听的事件
 * @description 前提经过表单校验，部门id必须为deptNumber，ajax请求类型限定GET
 */
function roleDeptCascade(form, $, filter) {
    form.on('select('+filter+')', function (data) {
        // 这行代码解决重复选择角色导致部门option重复append的问题，思路为每次选择角色将部门置空
        $('#deptNumber').html("<option value=\"\"></option>");
        if (data.value !== '') {
            $.ajax({
                async: false, // 异步提交
                type: 'GET',
                url: '/equipmentSys/department/getDeptByRole',
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
                }
            });
        }
    });
}

/**
 * 定制化layui新增信息弹窗
 * @param layer layui layer组件
 * @param form layui form组件
 * @param $ layui jquery组件
 * @param title 弹窗标题
 * @param content 弹窗内容
 * @param userNameSelector 用户名校验selector
 * @param pwdId1 密码校验框 id1
 * @param pwdId2 密码校验框 id2
 * @param filter 角色部门级联监听事件
 * @param saveUrl 保存事件ajax请求接口
 * @param type 弹窗类型 可选参数 'addUser','editUser','addDept','editDept','addEquip','editEquip'
 * @param data 数据参数
 * @description 提交事件名必须为'dialogSave'，取消id必须为'cancel'
 */
function addFormDialog(layer, form, $, title, content, userNameSelector, pwdId1, pwdId2, filter, saveUrl, type ,data) {
    let currIndex = layer.open({
        title: [title, 'font-size: 18px; text-align: center; margin:10px 0;']
        // content不能使用$('#dialogAddForm')的形式，会出现关闭弹窗再打开不显示的问题
        ,content: content
        ,area: '500px' // 宽  宽高：['width', 'height']
        // ,btn: ['保存', '取消'] // 按钮
        ,btn: false // 关闭弹窗自带按钮
        ,btnAlign: 'c' // 按钮居中对齐
        ,success: function (index) {
            form.render('select'); // 渲染select 否则显示异常
            // 用户编辑时将行数据显示在表单上
            if (type === 'editUser') {
                $('#userName').val(data.userName);
                $('#password').val(data.password);
                $('#trueName').val(data.trueName);
            }
            if (type === 'addUser' || type === 'editUser') {
                // 输入用户名校验
                userExistsCheck($, userNameSelector, true);
                // 输入密码校验
                passwordsInputCheck($, pwdId1, pwdId2);
                // 角色部门级联
                roleDeptCascade(form, $, filter);
            }
            if (type === 'addDept' || type === 'editDept') {
                // 部门信息级联
                getNextDeptByRole(form, $);
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
                $('#equipTypeNumber').val(data.equipTypeNumber); // TODO 不可用（下拉框）
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
                roleDeptCascade(form, $, filter);
                // 获取最新的申请编号
                getNextApplyNumberByDept(form, $);
            }
            if (type === 'editApply') {
                $('#applyNumber').val(data.applyNumber);
                $('#applyReason').val(data.applyReason);
            }
            // 弹窗成功后监听表单dialogSave提交
            form.on('submit(dialogSave)', function (data) {
                // TODO 未填写必填项不应该发送请求和关闭弹窗,此处不可用 msg冲突
                $.ajax({
                    async: false,
                    type: 'POST',
                    url: saveUrl,
                    data: data.field,
                    success: function (data) {
                        layer.close(currIndex);
                        if (data === 'success') {
                            if (type.substring(0,3) === 'add') {
                                layer.msg('新增成功',{icon: 1});
                            }
                            if (type.substring(0,4) === 'edit') {
                                layer.msg('更新成功',{icon: 1});
                            }
                            // 新增成功后要刷新页面,延迟1.5s刷新保证提示显示
                            setTimeout(function () {
                                window.location.reload();
                            }, 1500);
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
                                layer.msg('新增失败，请重试或联系管理员',{icon: 2, anim: 6});
                            }
                            if (type.substring(0,4) === 'edit') {
                                layer.msg('更新失败，请重试或联系管理员',{icon: 2, anim: 6});
                            }
                        }
                        if (data === 'applySuccess') {
                            layer.msg('申请成功', {icon: 1});
                            setTimeout(function () {
                                window.location.reload();
                            }, 1500);
                        }
                        if (data === 'conflict') {
                            layer.msg('您已经是该职位，不可申请', {icon: 0});
                        }
                        if (data === 'applyError') {
                            layer.msg('申请失败，请重试或联系管理员', {icon: 2, anim: 6});
                        }
                    },
                    error: function (e) {
                        layer.msg("系统错误，请联系管理员", {icon: 2});
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
 * @param form layui form组件
 * @param $ layui jquery组件
 * @description 为避免与部门模块角色部门级联冲突，监听事件名限定为roleTypeDept
 */
function getNextDeptByRole(form, $) {
    form.on('select(roleTypeDept)', function (data) {
        if (data.value !== '') {
            $.ajax({
                async: false, // 异步提交
                type: 'GET',
                url: '/equipmentSys/department/getNextDeptByRole',
                data: {
                    role: data.value
                },
                // dataType: 'json',
                success: function (data) {
                    // 将对应信息显示在表单上
                    $('#deptNumber').val(data.deptNumber);
                    $('#deptName').val(data.deptName);
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
        url: '/equipmentSys/equipment/getNextEquipNumber',
        data: {
        },
        // dataType: 'json',
        success: function (data) {
            // 将对应信息显示在表单上
            $('#equipNumber').val(data.equipNumber);
        }
    });
}

/**
 * 时间转换，用于查询传参
 * @param data 表单数据
 */
function timeConverter(data) {
    if (data.field.createTime !== '') { // 选择时间才进行操作 否则时间转换出现NaN
        let arr = data.field.createTime.split(' - '); // 得到时间数组
        let startTime = new Date(arr[0]); // 转换为Date
        let endTime = new Date(arr[1]);
        startTime = startTime.getTime(); // 转换为时间戳
        endTime = endTime.getTime();
        data.field.startTime = startTime; // 设置提交数据的值
        data.field.endTime = endTime;
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
        url: '/equipmentSys/equipmentType/getNextEquipTypeNumber',
        data: {
        },
        // dataType: 'json',
        success: function (data) {
            // 将对应信息显示在表单上
            $('#equipTypeNumber').val(data.equipTypeNumber);
        }
    });
}

/**
 * 通过部门编号获取最新设备编号，用于新建申请信息
 */
function getNextApplyNumberByDept(form, $) {
    form.on('select(deptNumber)', function (data) {
        if (data.value !== '') {
            $.ajax({
                async: false, // 异步提交
                type: 'GET',
                url: '/equipmentSys/apply/getNextApplyNumberByDeptNumber',
                data: {
                    deptNumber: data.value
                },
                // dataType: 'json',
                success: function (data) {
                    $('#applyNumber').val(data);
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
        url: '/equipmentSys/user/getCurrentUserInfo',
        success: function (data) {
            currentUserInfo = data;
        }
    });
    return currentUserInfo;
}

