<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>layout 后台大布局 - Layui</title>
    <link rel="stylesheet" href="/file/layui/css/layui.css">
    <script type="text/javascript" src="/file/js/jquery-1.10.2.min.js" ></script>
    <script type="text/javascript" src="/file/layui/layui.js"></script>
    <script type="text/javascript" src="/file/js/manu.js" ></script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">layui 后台布局</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="javascript:void();">控制台</a></li>
            <li class="layui-nav-item"><a href="javascript:void();">商品管理</a></li>
            <li class="layui-nav-item"><a href="javascript:void();">用户</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:void();">邮件管理</a></dd>
                    <dd><a href="javascript:void();">消息管理</a></dd>
                    <dd><a href="javascript:void();">授权管理</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    贤心
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:void();">基本资料</a></dd>
                    <dd><a href="javascript:void();">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="">退了</a></li>
        </ul>
    </div>




    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll" id="admin-navbar-side" >
            <#--垂直导航菜单-->
        </div>
    </div>




    <div class="layui-tab" lay-filter="admin-tab" lay-allowclose="true" style="float: right; width: 85%">
        <ul class="layui-tab-title"></ul>
        <div class="layui-tab-content"></div>
    </div>



    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>



</div>

<ul class="rightmenu">
    <li data-type="closethis">关闭当前</li>
    <li data-type="closeall">关闭所有</li>
</ul>

<script type="text/javascript">
    var menuData = ${(menuData)!};
    $(function () {
        $('#admin-navbar-side').html(getMenuHtml(menuData));
    });
</script>
</body>
</html>