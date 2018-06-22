<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>角色管理-添加</title>
    <link rel="stylesheet" href="/file/layui/css/layui.css">
    <script type="text/javascript" src="/file/js/jquery-1.10.2.min.js" ></script>
    <script type="text/javascript" src="/file/layui/layui.js"></script>
    <script type="text/javascript" src="/file/js/manu.js" ></script>
</head>
<body class="layui-layout-body">

<blockquote class="layui-elem-quote" >
<span class="layui-breadcrumb" style="visibility: visible;">
    <a href="/role/role" >角色管理</a> >
    <a >角色添加</a>
</span>
</blockquote>

<form class="layui-form layui-form-pane" action="">
    <div class="layui-form-item">
        <label class="layui-form-label">角色编码</label>
        <div class="layui-input-block">
            <input type="text" name="roleCode" required  lay-verify="required" placeholder="请输入角色编码" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">角色名称</label>
        <div class="layui-input-block">
            <input type="text" name="roleName" required  lay-verify="required" placeholder="请输入角色名称" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea name="comment" placeholder="请输入角色备注内容" class="layui-textarea"></textarea>
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
    //Demo
    layui.use(['form','layer'], function(){
        var form = layui.form, layer = layui.layer;

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