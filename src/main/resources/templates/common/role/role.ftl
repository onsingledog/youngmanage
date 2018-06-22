<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>角色管理</title>
    <link rel="stylesheet" href="/file/layui/css/layui.css">
    <script type="text/javascript" src="/file/js/jquery-1.10.2.min.js" ></script>
    <script type="text/javascript" src="/file/layui/layui.js"></script>
    <script type="text/javascript" src="/file/js/manu.js" ></script>
    <script type="text/javascript" src="/file/js/common.js" ></script>
</head>
<body class="layui-layout-body">

<blockquote class="layui-elem-quote" >
<span class="layui-breadcrumb" style="visibility: visible;">
        <a >角色管理</a>
</span>
</blockquote>
<div class="my-btn-box">
    <form id="searchForm" class="layui-form">
        <div class="layui-input-inline">
            <input type="text" name="roleCode"  autocomplete="off" placeholder="按角色编码查询" class="layui-input" style="width: 120px;">
        </div>
        <div class="layui-input-inline">
            <input type="text" name="roleName"  autocomplete="off" placeholder="按角色名称查询" class="layui-input" style="width: 120px;">
        </div>

        <button class="layui-btn" id="btn-search" type="button"><i class="layui-icon-search"></i>查询</button>
        <button class="layui-btn" id="btn-reset" type="reset" >重置</button>
    </form>

</div>

<a class="layui-btn layui-btn-normal layui-btn-sm" id="add" >增加</a>

<div class="layui-row">
    <div class="layui-col-md12">
        <div class="grid-demo grid-demo-bg1">
            <table id="dataTable"  lay-filter="grid">
            </table>
        </div>
    </div>
</div>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>

    <!-- 这里同样支持 laytpl 语法，如： -->
    {{#  if(d.auth > 2){ }}
    <a class="layui-btn layui-btn-xs" lay-event="check">审核</a>
    {{#  } }}
</script>

<script type="text/javascript">

    $(function(){
        $("#add").click(function(){
            window.location.href = "/role/addRole";
        });
    });

    layui.use(['element','layer','table','laydate'],function(){
        var layer = layui.layer,table=layui.table,laydate = layui.laydate;
        var gridObj = table.render({
            elem: '#dataTable', //指定原始表格元素选择器（推荐id选择器）
            url:'/role/getRoles',
            method: 'post',
            page:true,
            limits:[10,15,20,50,100],
            skin:'row',
            even: true,
            where: $("#searchForm").serializeArray(),
            cols:[[
                {checkbox: true,field: 'id', LAY_CHECKED: false},
                {field: 'roleCode', title: '角色码',width:200},
                {field: 'roleName', title: '角色名称',width:200},
                {field: 'comment', title: '备注',width:300},
                {fixed: 'right',title: '操作', width:230, align:'center', toolbar: '#barDemo'}
            ]]
        });

        table.on('tool(grid)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

            if(layEvent === 'detail'){ //查看
                //do somehing
            } else if(layEvent === 'del'){ //删除
                layer.confirm('真的删除行么', function(index){
                    var ind = layer.load();
                    $.post("/role/deleteRole",{"id":data.id},function(r){
                        layer.closeAll();
                        layer.alert(r.msg,function(i){
                            layer.close(i);
                            if(r.code=="1"){
                                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                            }
                        })
                    });
                });
            } else if(layEvent === 'edit'){ //编辑
                window.location.href = "/role/updateRole?id="+data.id;
            }
        });

        $("#btn-search").click(function(){
            gridObj.reload({
                where: $("#searchForm").serializeObject()
            });
        });
        $("#btn-reset").click(function(){
            $("#searchForm").find('input[type=text],input[type=hidden],select').each(function() {
                $(this).val('');
            });
            gridObj.reload({
                where: $("#searchForm").serializeObject()
            });
        });


    })
</script>
</body>
</html>