<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>菜单管理</title>
    <link rel="stylesheet" href="/file/layui/css/layui.css">
    <script type="text/javascript" src="/file/js/jquery-1.10.2.min.js" ></script>
    <script type="text/javascript" src="/file/layui/layui.js"></script>
    <script type="text/javascript" src="/file/js/manu.js" ></script>
</head>
<body class="layui-layout-body">

<blockquote class="layui-elem-quote" >
<span class="layui-breadcrumb" style="visibility: visible;">
    <a href="/menu/menuList" >菜单管理</a>
    <#if oper=='add' >
        ><a >菜单添加</a>
    </#if>
</span>
</blockquote>

<form class="layui-form layui-form-pane" action="">
    <div class="layui-form-item">
        <label class="layui-form-label">菜单名称</label>
        <div class="layui-input-block">
            <input type="text" name="menuTitle" required  lay-verify="required" placeholder="请输入菜单名称" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">菜单图标</label>
        <div class="layui-input-block">
            <input type="text" name="menuIconClass" required  lay-verify="required" placeholder="请输入菜单图标" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">菜单级别</label>
        <div class="layui-input-block">
            <select id="menuLevel" name="menuLevel" lay-filter="menuLevel" lay-verify="required">
                <option value="">请选择菜单级别</option>
                <option value="1">一级菜单</option>
                <option value="2">二级菜单</option>
            </select>
        </div>
    </div>
    <div id="parentDivId" class="layui-form-item" style="display: none" >
        <label class="layui-form-label">父级菜单</label>
        <div class="layui-input-block">
            <select name="menuParentId" lay-verify="required">
                <option value="">请选择父级菜单</option>
                <#list parentMenuList as menu >
                    <option value="${(menu.menuId)!}">${(menu.menuTitle)!}</option>
                </#list>
            </select>
        </div>
    </div>
    <div id="levenDivId" class="layui-form-item"  style="display: none" >
        <label class="layui-form-label">菜单序号</label>
        <div class="layui-input-block">
            <select name="menuLevel" lay-verify="required">

            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script>
    $(function(){

    });

    //Demo
    layui.use(['form','layer'], function(){
        var form = layui.form, layer = layui.layer;

        form.on('select(menuLevel)', function(data){
            var selectVal = data.value;
            if(selectVal=="2"){
                $("#parentDivId").show();
                $("#levenDivId").show();
            }else if(selectVal=="1"){
                $.post("/menu/getMenuNum",{"menuLevel":"1"},function(data){
                    var html="";
                    for(var i=0;i<data.data;i++){
                        html += "<option value='"+i+"'>"+i+"</option>"
                    }
                    $("#menuLevel").html("<option value=''>请选择菜单级别</option>"+html+"<option value='"+(data.data+1)+"'>"+(data.data+1)+"</option>");
                    form.render('select');
                    $("#parentDivId").hide();
                    $("#levenDivId").show();
                });
            }else{
                $("#parentDivId").hide();
                $("#levenDivId").hide();
            }
        });

        //监听提交
        form.on('submit(formDemo)', function(data){
            var url = "/role/addRole";
            $.post(url,data.field,function (r) {
                layer.alert(r.msg, function(index){
                    layer.close(index);
                    if(r.code == "1"){
                        window.location.href = "/role/role";
                    }
                });
            });
//            layer.msg(JSON.stringify(data.field));
            return false;
        });
    });
</script>

</body>
</html>