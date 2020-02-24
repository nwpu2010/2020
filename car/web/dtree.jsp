
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <script src="<%= request.getContextPath()%>/resources/layui/layui.js"></script>

    <link type="text/css"  rel="stylesheet"  href="<%= request.getContextPath()%>/resources/layui_ext/dtree/dtree.css">
    <link type="text/css"  rel="stylesheet"  href="<%= request.getContextPath()%>/resources/layui/css/layui.css">
    <link type="text/css"  rel="stylesheet"  href="<%= request.getContextPath()%>/resources/layui_ext/dtree/font/dtreefont.css">
</head>
<body>
<ul id="demoTree1" class="dtree" data-id="0"></ul>


<%--// 你可以在ul标签上指定data-value--%>
<%--<ul id="selTree3" class="dtree" data-id="0" data-value="001003"></ul></div>--%>

<script>
    layui.config({
        base:'<%= request.getContextPath()%>/resources/layui_ext/dtree/'
    }).extend({
            dtree:'dtree'
    }).use(['element','layer', 'dtree'], function(){
        var layer = layui.layer,
            dtree = layui.dtree,
            $ = layui.$;

        //下拉树
        // dtree.renderSelect({
        //     elem: "#selTree3",
        //     url: "<%= request.getContextPath()%>/resources/json/demoTree1",
        //     skin: "laySimple",
        //     selectInitVal: "001003", // 你可以在这里指定默认值
        //     done: function(){
        //         dtree.dataInit("selTree3", "001003");
        //         // 也可以在这里指定，第二个参数如果不填，则会自动返显当前选中的数据
        //         var selectParam = dtree.selectVal("selTree3");
        //     }
        // });
        //普通渲染
        <%--dtree.render({--%>
            <%--elem: "#demoTree1",  //绑定元素--%>
            <%--url: "<%= request.getContextPath()%>/resources/json/demoTree1"  //异步接口--%>
        <%--});--%>

        //layui风格渲染
        dtree.render({
            elem: "#demoTree1",
            url: "<%= request.getContextPath()%>/resources/json/demoTree3",
           dataStyle: "layuiStyle",  //使用layui风格的数据格式
            // checkbar:true,//配合json中的checkArr字段，可以在树中增加复选框的功能
            response:{message:"msg",statusCode:200}  //修改response中返回数据的定义
        });
        //单击节点 监听事件
        dtree.on("node('demoTree1')" ,function(param){
            layer.msg(JSON.stringify(param));
        });


    });




</script>
</body>
</html>
