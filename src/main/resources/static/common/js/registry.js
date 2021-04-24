/**
 * 注册页相关
 */

layui.use(['layer', 'form', 'jquery'], function (){
    let layer = layui.layer, // 注册layer
        form = layui.form, // 注册form
        $ = layui.jquery; // 注册jquery
    // 监听填写的用户名
    methods.userExistsCheck('#userName', true);
    // 监听注册按钮
    methods.formSubmitEvent(
        'registry',
        '/equipmentSys/user/add',
        true);
    // 角色部门级联 （监听角色渲染对应部门）
    methods.roleDeptCascade('roleType');
    // 两次密码输入校验
    methods.passwordsInputCheck('#pwd', '#pwd2');
});