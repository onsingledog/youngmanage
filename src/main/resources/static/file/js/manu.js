
function navBar(strData){
    var data;
    if(typeof(strData) == "string"){
        var data = JSON.parse(strData); //部分用户解析出来的是字符串，转换一下
    }else{
        data = strData;
    }
    var ulHtml = '<ul class="layui-nav layui-nav-tree">';
    for(var i=0;i<data.length;i++){
        if(data[i].spread){
            ulHtml += '<li class="layui-nav-item layui-nav-itemed">';
        }else{
            ulHtml += '<li class="layui-nav-item">';
        }
        if(data[i].childMenus != undefined && data[i].childMenus.length > 0){
            ulHtml += '<a href="javascript:;">';
            if(data[i].menuIconClass != undefined && data[i].menuIconClass != ''){
                if(data[i].menuIconClass.indexOf("icon-") != -1){
                    ulHtml += '<i class="iconfont '+data[i].menuIconClass+'" data-icon="'+data[i].menuIconClass+'"></i>';
                }else{
                    ulHtml += '<i class="layui-icon" data-icon="'+data[i].menuIconClass+'">'+data[i].menuIconClass+'</i>';
                }
            }
            ulHtml += '<cite>'+data[i].menuTitle+'</cite>';
            ulHtml += '<span class="layui-nav-more"></span>';
            ulHtml += '</a>';
            ulHtml += '<dl class="layui-nav-child">';
            for(var j=0;j<data[i].childMenus.length;j++){
                var childMenu = data[i].childMenus[j];
                if(childMenu.target != undefined && childMenu.target == "_blank"){
                    ulHtml += '<dd><a href="javascript:;" data-id="'+childMenu.menuId+'" data-url="'+childMenu.menuUrl+'" target="'+childMenu.target+'">';
                }else{
                    ulHtml += '<dd><a href="javascript:;" data-id="'+childMenu.menuId+'" data-url="'+childMenu.menuUrl+'">';
                }
                if(childMenu.menuIconClass != undefined && childMenu.menuIconClass != ''){
                    if(childMenu.menuIconClass.indexOf("icon-") != -1){
                        ulHtml += '<i class="iconfont '+childMenu.menuIconClass+'" data-icon="'+childMenu.menuIconClass+'"></i>';
                    }else{
                        ulHtml += '<i class="layui-icon" data-icon="'+childMenu.menuIconClass+'">'+childMenu.menuIconClass+'</i>';
                    }
                }
                ulHtml += '<cite>'+childMenu.menuTitle+'</cite></a></dd>';
            }
            ulHtml += "</dl>";
        }else{
            if(data[i].target != undefined && data[i].target == "_blank"){
                ulHtml += '<a href="javascript:;" data-id="'+data[i].menuId+'" data-url="'+data[i].menuUrl+'" target="'+data[i].target+'">';
            }else{
                ulHtml += '<a href="javascript:;" data-id="'+data[i].menuId+'" data-url="'+data[i].menuUrl+'">';
            }
            if(data[i].menuIconClass != undefined && data[i].menuIconClass != ''){
                if(data[i].menuIconClass.indexOf("icon-") != -1){
                    ulHtml += '<i class="iconfont '+data[i].menuIconClass+'" data-icon="'+data[i].menuIconClass+'"></i>';
                }else{
                    ulHtml += '<i class="layui-icon" data-icon="'+data[i].menuIconClass+'">'+data[i].menuIconClass+'</i>';
                }
            }
            ulHtml += '<cite>'+data[i].menuTitle+'</cite></a>';
        }
        ulHtml += '</li>';
    }
    ulHtml += '</ul>';
    return ulHtml;
}


/**
 *
 * @param navs
 */
function getMenuHtml(navs) {
    var html = '';
    $.each(navs,function(i,item){
        html += '<dl>';
        html += '<dt><a href="javascript:;" data-url="'+item.menuUrl+'" nav-id="'+item.menuId+'"><i class="'+item.menuIconClass+'"></i><cite>'+item.menuTitle+'</cite></a></dt>';
        //如果有第二级菜单
        if(item.children !== undefined && item.children.length > 0){
            $.each(item.children,function(j,item2){
                html += '<dd>';
                html += '<a href="javascript:;" data-url="'+item2.menuUrl+'" nav-id="'+item2.menuId+'"><i class="'+item2.menuIconClass+'"></i><cite>'+item2.menuTitle+'</cite></a>';
                //如果有三级菜单
                if(item2.children !== undefined && item2.children.length > 0){
                    html += '<ul>';
                    $.each(item2.children,function(k,item3){
                        html += '<li>'+
                            '<a href="javascript:;" data-url="'+item3.menuUrl+'" nav-id="'+item3.menuId+'">'+
                            '<i class="'+item3.menuIconClass+'"></i>'+
                            '<cite>'+item3.menuTitle+'</cite>'+
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
    return html;
    //渲染html
    //$('#admin-navbar-side').html(html);
}

layui.use(['jquery','element','layer'], function(){
    var $ = jQuery = layui.jquery;
    var element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
    var layer = layui.layer;

    //增加触发事件
    var active = {
        tabAdd: function (url, id,title) {
            //新增一个Tab项
            element.tabAdd('admin-tab', {
                title: title //用于演示
                , content: '<iframe data-frameid="'+id+'" frameborder="0" name="content" scrolling="no" width="100%" src="' + url + '"></iframe>'
                , id: id //实际使用一般是规定好的id，这里以时间戳模拟下
            });
            CustomRightClick(id);//绑定右键菜单
            FrameWH();//计算框架高度
        }
        , tabChange: function (id) {
            //切换到指定Tab项
            element.tabChange('admin-tab', id); //切换到：用户管理
            //$("iframe[data-frameid='"+id+"']").attr("src",$("iframe[data-frameid='"+id+"']").attr("src"))//切换后刷新框架
        }
        , tabDelete: function (id) {
            element.tabDelete("admin-tab", id);//删除
        }
        , tabDeleteAll: function (ids) {//删除所有
            $.each(ids, function (i,item) {
                element.tabDelete("admin-tab", item);
            });
        }
    };

    //给菜单关联触发事件
    $(".layui-side-scroll a").click(function () {
        if(!$(this)[0].hasAttribute('data-url') || $(this).attr('data-url')===''
            || !$(this)[0].hasAttribute('data-id') || $(this).attr('data-id')==='')return;
        var dataid = $(this);
        if ($(".layui-tab-title li[lay-id]").length <= 0) {
            active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"),dataid.text());
        } else {
            var isData = false;
            $.each($(".layui-tab-title li[lay-id]"), function () {
                if ($(this).attr("lay-id") == dataid.attr("data-id")) {
                    isData = true;
                }
            });
            if (isData == false) {
                active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"),dataid.text());
            }
        }
        active.tabChange(dataid.attr("data-id"));
    });

    //绑定右键菜单
    function CustomRightClick(id) {
        //取消右键
        $('.layui-tab-title li').on('contextmenu', function () { return false; });
        $('.layui-tab-title,.layui-tab-title li').click(function () {
            $('.rightmenu').hide();
        });
        //桌面点击右击
        $('.layui-tab-title li').on('contextmenu', function (e) {
            var popupmenu = $(".rightmenu");
            popupmenu.find("li").attr("data-id",id);
            l = ($(document).width() - e.clientX) < popupmenu.width() ? (e.clientX - popupmenu.width()) : e.clientX;
            t = ($(document).height() - e.clientY) < popupmenu.height() ? (e.clientY - popupmenu.height()) : e.clientY;
            popupmenu.css({ left: l, top: t }).show();
            //alert("右键菜单")
            return false;
        });
    }

});

function FrameWH() {
    var h = $(window).height() -41- 10 - 60 -10-44 -10;
    $("iframe").css("height",h+"px");
}

$(window).resize(function () {
    FrameWH();
})



