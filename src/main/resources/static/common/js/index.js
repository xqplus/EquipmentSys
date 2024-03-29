/**
 * 主页相关
 */

layui.use(['element', 'carousel', 'jquery'], function(){
    let element = layui.element // 导航栏相关
        ,carousel = layui.carousel // 轮播图相关

    // 轮播图渲染
    carousel.render({
        elem: '#carousel1',
        width: '100%', //设置容器宽度
        arrow: 'hover', //始终显示箭头
        height: '380px',
        anim: 'default', // fade updown
    });
    // 点击我的通知表示已阅
    $('#myNotice').click(function () {
        $('#myNoticeDot').removeClass("layui-badge-dot");
    });
    // 鼠标悬停显示用户详情
    methods.userInfoShow();
});