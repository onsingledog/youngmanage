<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>角色管理-查看</title>
    <link rel="stylesheet" href="/file/layui/css/layui.css">
    <script type="text/javascript" src="/file/js/jquery-1.10.2.min.js" ></script>
    <script type="text/javascript" src="/file/layui/layui.js"></script>
    <script type="text/javascript" src="/file/js/manu.js" ></script>
</head>
<body class="layui-layout-body">

<blockquote class="layui-elem-quote" >
<span class="layui-breadcrumb" style="visibility: visible;">
    <a >角色查看</a>
</span>
</blockquote>

<form class="layui-form layui-form-pane" action="">
    <div class="layui-form-item">
        <label class="layui-form-label">角色编码</label>
        <div class="layui-input-block">
            <input type="text" name="roleCode" value="${(role.roleCode)!}" required  lay-verify="required" placeholder="请输入角色编码" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">角色名称</label>
        <div class="layui-input-block">
            <input type="text" name="roleName" required value="${(role.roleName)!}" lay-verify="required" placeholder="请输入角色名称" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea name="comment" placeholder="请输入角色备注内容" class="layui-textarea">${(role.comment)!}</textarea>
        </div>
    </div>
</form>

<script>

</script>

</body>
</html>