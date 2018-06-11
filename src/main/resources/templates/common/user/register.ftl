<!DOCTYPE html>
<html lang="en" >

<head>
    <meta charset="UTF-8">
    <title>用户注册</title>
    <script type="text/javascript" src="/file/js/jquery-1.10.2.min.js" ></script>
    <link rel="stylesheet" href="/file/layui/css/layui.css" >
    <script type="text/javascript" src="/file/layui/layui.js" ></script>
</head>

<body >

<div class="layui-container fly-marginTop">
    <div class="fly-panel fly-panel-user" pad20="">
        <div class="layui-tab layui-tab-brief" lay-filter="user">
            <ul class="layui-tab-title">
                <li >登入</li>
                <li class="layui-this" >注册</li>
            </ul>
            <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
                <div class="layui-tab-item layui-show">
                    <div class="layui-form layui-form-pane" >
                        <form method="post" class="layui-form"  >
                            <div class="layui-form-item">
                                <label for="L_telephone" class="layui-form-label">手机号</label>
                                <div class="layui-input-inline">
                                    <input id="L_telephone" name="telephone" required=""
                                           lay-verify="required" autocomplete="off"
                                           class="layui-input layui-form-danger" type="text">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_mail" class="layui-form-label">邮箱</label>
                                <div class="layui-input-inline">
                                    <input id="L_mail" name="mail" required=""
                                           lay-verify="required|email" autocomplete="off"
                                           class="layui-input layui-form-danger" type="text">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_nickname" class="layui-form-label">昵称</label>
                                <div class="layui-input-inline">
                                    <input id="L_nickname" name="nickname" required=""
                                           lay-verify="required" autocomplete="off"
                                           class="layui-input layui-form-danger" type="text">
                                </div>
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
                                <label for="L_password2" class="layui-form-label">确认密码</label>
                                <div class="layui-input-inline">
                                    <input id="L_password2" name="password2" required=""
                                           lay-verify="required" autocomplete="off"
                                           class="layui-input" type="password">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <button class="layui-btn" lay-filter="registerSubmit" lay-submit=""  >注册</button>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
<script type="text/javascript">

    function register(data,layer) {
        if(data.field.password != data.field.password2){
            layer.alert("两次输入密码不一致，请检查！");
            return false;
        }
        var url = "/user/register";
        console.log(data.field);
        $.post(url,data.field,function (resule){
            if(resule.code=="1"){
                layer.open({
                    content: "注册成功",
                    yes:function(index,layero){
                        layer.close(index);
                        window.location.href="/user/main";
                    }
                });
            }else{
                layer.alert(resule.msg);
            }
        });
    }

    layui.use(['form', 'layer'],function () {
        var form = layui.form,layer = layui.layer;
        form.on("submit(registerSubmit)",function(data){
            register(data,layer);
            return false;
        });

    })



</script>

</html>
