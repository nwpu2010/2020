<%--
  Created by IntelliJ IDEA.
  User: lee
  Date: 2020/2/6
  Time: 22:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <style type="text/css">
        * {
            margin: 0;
            padding: 0
        }

        html, body {
            height: 100%
        }

        /*这里很关键*/

        .outer-wrap {
            /*只有同时为html和body设置height: 100%时，这里的height才生效，
            并且随浏览器窗口变化始终保持和浏览器视窗等高*/
            height: 100%;
            position: relative;
            /*background-color: rgba(0, 0, 0, .5);*/
        }

        .login-panel {
            width: 400px;
            height: 300px;
            /*background-color: orange;*/
            position: absolute;
            top: 50%;
            left: 50%;
            margin-top: -150px;
            margin-left: -200px;
        }
        h1{
            margin-bottom: 100px;
        }
    </style>

    <link rel="stylesheet" href="resources/layui/css/layui.css" media="all"/>
</head>
<body>
<div class="outer-wrap">
    <%--get 最大长度 1204字节？--%>
    <form class="layui-form" action="<%=request.getContextPath()%>/login.do" method="post">
        <div class="login-panel">
            <h1 align="center">登录页面</h1><hr>
            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-inline">
                    <input type="text" name="loginname" required lay-verify="required" placeholder="请输入用户名"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                    <input type="password" name="pwd" required lay-verify="required" placeholder="请输入密码"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div align="center"><a style="color: red">${error}</a></div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit>登录</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>
