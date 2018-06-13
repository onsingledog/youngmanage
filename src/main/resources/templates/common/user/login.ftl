<!DOCTYPE html>
<html lang="en" >

<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
    <link rel="stylesheet" href="/file/layui/css/layui.css" >
    <script type="text/javascript" src="/file/js/jquery-1.10.2.min.js" ></script>
    <script type="text/javascript" src="/file/layui/layui.js" ></script>
</head>

<body >

<div class="layui-container fly-marginTop">
    <div class="fly-panel fly-panel-user" pad20="">
        <div class="layui-tab layui-tab-brief" lay-filter="user">
            <ul class="layui-tab-title">
                <li class="layui-this">登入</li>
                <li><a href="javascript:void(0)">注册</a></li>
            </ul>
            <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
                <div class="layui-tab-item layui-show">
                    <div class="layui-form layui-form-pane" >
                        <form method="post" class="layui-form"  >
                            <div class="layui-form-item">
                                <label for="L_email" class="layui-form-label">用户名</label>
                                <div class="layui-input-inline">
                                    <input id="L_loginName" name="loginName" required=""
                                           lay-verify="required" autocomplete="off"
                                           class="layui-input layui-form-danger" type="text">
                                </div>
                                <div class="layui-form-mid layui-word-aux"> 用户名 </div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_password" class="layui-form-label">密码</label>
                                <div class="layui-input-inline">
                                    <input id="L_password" name="password" required=""
                                           lay-verify="required" autocomplete="off"
                                           class="layui-input" type="password">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <button class="layui-btn" lay-filter="login" lay-submit="">立即登录</button>
                                <span style="padding-left:20px;"> <a href="/user/forget">忘记密码？</a> </span>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
<script type="text/javascript" >


    layui.use(['form','layer'],function () {
        var form = layui.form,layer = layui.layer;
        form.on("submit(login)",function(data){

            var url = "/user/ajaxLogin";
            $.post(url,data.field,function(result){
                if(result.code==1){
                    window.location.href="/user/main";
                }else{
                    layer.alert(result.msg);
                }
            });

            return false;
        });

    })

</script>

</html>
