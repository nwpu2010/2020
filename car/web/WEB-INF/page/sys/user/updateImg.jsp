<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>修改用户图像</title>
    <link rel="stylesheet"  href="${pageContext.request.contextPath}/resources/layui/css/layui.css"/>
</head>
<body>
<div style="padding-left: 20px;padding-top: 10px">
    <img  src="${pageContext.request.contextPath}/${user.img}" onerror="javascript:this.src='${pageContext.request.contextPath}/resources/images/face.jpg';" id="userImg"  style="width: 200px;height: 200px;border: #9F9F9F 1px solid"/><br><br><br>
    <button class="layui-btn" style="width: 200px" id="uploadBtn">上传</button>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script type="text/javascript">
    layui.use(['upload','layer','jquery'],function(){
        var  upload = layui.upload;
        var layer = layui.layer;
        var $ = layui.jquery;
        //初始化文件上传容器
        upload.render({
            elem:"#userImg",//点击图片时触发选择文件
            auto:false,// 阻止默认提交
            url:"${pageContext.request.contextPath}/sys/user/updateUserImg.do",//修改图片的地址
            field:"userImg",//重命名文件上传的name属性值
            size:2048,// 图片最大2M
            bindAction:"#uploadBtn",//触发上传的按钮
            choose:function(obj){//选择图片后触发的事件
                obj.preview(function(index, file, result){
                    //实现图片预览
                    $("#userImg").attr("src",result);
                });
            },
            done:function(res, index, upload){
                if(res.code != 200){
                    layer.msg(res.msg);
                    return false;
                }
                layer.msg("图像修改成功!重新登录生效");
            }
        });
    })


</script>
</body>
</html>
