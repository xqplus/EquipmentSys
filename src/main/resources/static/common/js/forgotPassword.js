/**
 * 找回密码相关
 */

layui.use(['layer', 'form', 'jquery'], function (){
    let layer = layui.layer,
        form = layui.form,
        $ = layui.jquery;
    // 监听用户输入，密码找回boolean参数为false
    userExistsCheck('#userName', false);
    // 监听提交按钮，密码找回回调boolean参数为false
    formSubmitEvent(
        'resetPassword',
        '/equipmentSys/registry/resetPasswordCheck',
        false);
    // 角色部门级联
    roleDeptCascade('roleType');
    // 两次密码输入校验
    passwordsInputCheck('#pwd', '#pwd2');
});
