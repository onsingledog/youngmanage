

layui.use(['jquery','element','layer'], function(){
    var $ = layui.jquery,
        element = layui.element,
        form = layui.form;
    var html = '';
    //获取菜单
    $.ajax({
        url:"../manage/permissionList?type=1",
        type:"post",
        dataType:"json",
        data:{},
        success:function(data){
            var navs=data.result;
            $.each(navs,function(i,item){
                html += '<dl>';
                html += '<dt><a href="javascript:;" data-url="'+item.url+'" nav-id="'+item.id+'"><i class="'+item.icon+'"></i><cite>'+item.title+'</cite></a></dt>';
                //如果有第二级菜单
                if(item.children !== undefined && item.children.length > 0){
                    $.each(item.children,function(j,item2){
                        html += '<dd>';
                        html += '<a href="javascript:;" data-url="'+item2.url+'" nav-id="'+item2.id+'"><i class="'+item2.icon+'"></i><cite>'+item2.title+'</cite></a>';
                        //如果有三级菜单
                        if(item2.children !== undefined && item2.children.length > 0){
                            html += '<ul>';
                            $.each(item2.children,function(k,item3){
                                html += '<li>'+
                                    '<a href="javascript:;" data-url="'+item3.url+'" nav-id="'+item3.id+'">'+
                                    '<i class="'+item3.icon+'"></i>'+
                                    '<cite>'+item3.title+'</cite>'+
                                    '</a>'+
                                    '</li>';
                            });
                            html += '</ul>';
                        }
                        html += '</dd>';
                    });
                }
                html += '</dl>';
            });
            //渲染html
            $('#admin-navbar-side').html(html);
        }
    });

    //触发事件
    var active = {
        tabAdd: function(obj){
            //新增一个Tab项
            element.tabAdd('admin-tab', {
                title: '112233111111111111122233333'//用于演示
                ,content: '<iframe src="'+$(this).attr('data-url')+'"></iframe>'
            });
            element.tabChange("admin-tab", $('.layui-tab-title li').length - 1);
        },
        tabDelete: function(index) {
            //删除指定Tab项
            element.tabDelete('admin-tab', index); //删除（注意序号是从0开始计算）
        }
        ,tabChange: function(lay_id){
            //切换到指定Tab项
            element.tabChange('admin-tab', lay_id); //切换到：用户管理
        }
    };
    //添加tab
    $(document).on('click','a',function(){
        if(!$(this)[0].hasAttribute('data-url') || $(this).attr('data-url')==='')return;
        var title = $.trim($(this).text());
        var tabs = $(".layui-tab-title").children();
        for(var i = 0; i < tabs.length; i++) {
            if($(tabs).eq(i).children('cite').text() == title) {
                element.tabChange('admin-tab', i);
                return;
            }
        }
        active["tabAdd"].call(this);
        FrameWH();
        active.tabChange($(".layui-tab-title").children().length - 1);
    });

    //iframe自适应
    function resize(){
        var $content = $('.admin-nav-card .layui-tab-content');
        $content.height($(this).height() - 147);
        $content.find('iframe').each(function() {
            $(this).height($content.height());
        });
    }
    $(window).on('resize', function() {
        var $content = $('.admin-nav-card .layui-tab-content');
        $content.height($(this).height() - 147);
        $content.find('iframe').each(function() {
            $(this).height($content.height());
        });
    }).resize();


    //toggle左侧菜单
    $('.admin-side-toggle').on('click', function() {
        var sideWidth = $('#admin-side').width();
        if(sideWidth === 200) {
            $('#admin-body').animate({
                left: '0'
            }); //admin-footer
            $('#admin-footer').animate({
                left: '0'
            });
            $('#admin-side').animate({
                width: '0'
            });
        } else {
            $('#admin-body').animate({
                left: '200px'
            });
            $('#admin-footer').animate({
                left: '200px'
            });
            $('#admin-side').animate({
                width: '200px'
            });
        }
    });
    $(document).on('click','dt',function(){
        $(this).parent().find('dd').toggle();
    });
    $(document).on('click','dd a',function(){
        $(this).next('ul').toggle();
    });
});

function FrameWH() {
    var h = $(window).height() -41- 10 - 60 -10-44 -10;
    $("iframe").css("height",h+"px");
}

$(window).resize(function () {
    FrameWH();
})



