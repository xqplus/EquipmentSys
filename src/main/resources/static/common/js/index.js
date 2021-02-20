/**
 * 主页相关
 */

layui.use(['element', 'carousel', 'jquery'], function(){
    let element = layui.element // 导航栏相关
        ,carousel = layui.carousel // 轮播图相关
        ,$ = layui.jquery;
    // 轮播图渲染 TODO 卡片化效果
    carousel.render({
        elem: '#carousel1',
        width: '100%', //设置容器宽度
        arrow: 'hover', //始终显示箭头
        height: '380px',
        anim: 'default', // fade updown
    });
});