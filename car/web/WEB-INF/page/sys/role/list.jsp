<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>角色列表</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui_ext/dtree/dtree.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui_ext/dtree/font/dtreefont.css" />
</head>
<body style="padding-left: 10px;padding-top: 10px">
    <div class="layui-form layui-form-pane">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label" for="name">
                    角色名
                </label>
                <div class="layui-input-inline">
                    <input class="layui-input" placeholder="角色名" id="name" />
                </div>
            </div>
            <button class="layui-btn" id="searchBtn">搜索</button>
        </div>
    </div>
    <table id="dataTable" lay-filter="dataTableFilter"></table>

    <%--按钮模板--%>
    <script type="text/html" id="headerBtns">
        <button class="layui-btn layui-btn-sm" lay-event="add">新增</button>
    </script>
    <script type="text/html" id="rowBtns">
        <button class="layui-btn layui-btn-warm layui-btn-sm" lay-event="update">修改角色</button>
        <button class="layui-btn layui-btn-warm layui-btn-sm" lay-event="setRoleMenu">设置权限</button>
    </script>
    <script type="text/html" id="updateForm">
       <form class="layui-form layui-form-pane" style="padding-left: 10px;padding-top: 5px" lay-filter="updateFormFilter">
           <div class="layui-form-item">

                    <label class="layui-form-label">
                       角色名
                    </label>
                    <div class="layui-input-inline">
                       <input class="layui-input" placeholder="角色名" name="name"  lay-verify="required" lay-reqText="角色名不能为空" />
                    </div>
           </div>

               <div class="layui-form-item">
                   <label class="layui-form-label">
                       介绍
                   </label>
                   <div class="layui-input-inline">
                       <textarea class="layui-textarea" name="desc" placeholder="角色介绍"></textarea>
                   </div>
                   <button style="display: none" id="submitBtn" lay-filter="submitBtnFilter" lay-submit />
               </div>
       </form>
    </script>
    <%-- 设置权限的弹出层模板 --%>
    <script type="text/html" id="setMenuRole">
        <ul class="dtree" id="roleMenu" data-id="0"></ul>
    </script>

    <%-- 引入layui的js --%>
    <script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
    <script>
        layui.extend({
            dtree: '${pageContext.request.contextPath}/resources/layui_ext/dtree/dtree'
        }).use(['table','jquery','layer','form','dtree'],function () {
            let table = layui.table;
            let $ = layui.jquery;
            let layer = layui.layer;
            let form = layui.form;
            let dtree = layui.dtree;
            //初始化数据表格
            var t = table.render({
                id:"dataTableId",
                elem:"#dataTable",
                page:true,//开启分页
                url:"${pageContext.request.contextPath}/sys/role/list.do",//数据接口
                toolbar: "#headerBtns",//头工具栏
                cols:[[
                    {field:"id",title:"角色ID"},
                    {field:"name",title:"角色名"},
                    {field:"desc",title:"角色介绍"},
                    {title:"操作",toolbar:"#rowBtns"}
                ]],
                parseData:function (rs) {
                    return {
                        "code": rs.code, //解析接口状态
                        "msg": rs.msg, //解析提示文本
                        "count": rs.data.total, //解析数据长度
                        "data": rs.data.list //解析数据列表
                    }
                },
                response:{
                    statusCode: 200 //规定成功的状态码，默认：0
                }
            });
            //搜索功能
            $("#searchBtn").click(function () {
                let name = $("#name").val();
                t.reload({
                    where:{
                        page:1,
                        limit:10,
                        name:name,
                    }
                })
            });
            //表格头工具栏事件
            table.on("toolbar(dataTableFilter)",function (d) {
                let event = d.event;
                if(event == "add"){
                    add();
                }
            });
            //用户新增
            function add(){
                layer.open({
                    type:1,
                    content:$("#updateForm").html(),
                    area:['335px','325px'],
                    success:function(layero,index){//为表单绑定提交监听事件
                        //为表单绑定提交事件
                        form.on("submit(submitBtnFilter)",function (d) {
                            let data = d.field;
                            $.post("${pageContext.request.contextPath}/sys/role/add.do",data,function (rs) {
                                if (rs.code != 200){
                                    layer.msg(rs.msg);
                                    return false;
                                }
                                //刷新数据
                                $("#searchBtn").click();
                                //关闭弹出层
                                layer.close(index);
                            });
                            return false;//阻止表单默认提交
                        })

                    },
                    btn:['确认','取消'],
                    btnAlign:"c",
                    yes:function (index,layero) {//点击确认时 模拟点击提交
                        $("#submitBtn").click();
                    }
                });
            }
            //行监听事件
            table.on("tool(dataTableFilter)",function (d) {
                let event = d.event;
                if(event == "update"){
                    //重置的的方法
                    update(d.data);
                }else if(event == "setRoleMenu"){
                    setRoleMenu(d.data.id);
                }
            });

            //修改角色
            function update(role) {
                layer.open({
                    type:1,
                    content:$("#updateForm").html(),
                    area:['335px','325px'],
                    success:function(layero,index){//为表单绑定提交监听事件
                        form.val("updateFormFilter",role);
                        //为表单绑定提交事件
                        form.on("submit(submitBtnFilter)",function (d) {
                            let data = d.field;
                            data.id = role.id;
                            $.post("${pageContext.request.contextPath}/sys/role/update.do",data,function (rs) {
                                if (rs.code != 200){
                                    layer.msg(rs.msg);
                                    return false;
                                }
                                //刷新数据
                                $("#searchBtn").click();
                                //关闭弹出层
                                layer.close(index);
                            });
                            return false;//阻止表单默认提交
                        })

                    },
                    btn:['确认','取消'],
                    btnAlign:"c",
                    yes:function (index,layero) {//点击确认时 模拟点击提交
                        $("#submitBtn").click();
                    }
                });

            };
            //设置权限
            function setRoleMenu(roleId) {
                layer.open({
                    type:1,
                    content:$("#setMenuRole").html(),
                    area:['335px','325px'],
                    success:function(layero,index){//为表单绑定提交监听事件
                        // 渲染树
                        let leftTree = dtree.render({
                            elem:"#roleMenu",
                            url:"${pageContext.request.contextPath}/sys/role/getRoleMenu.do?id="+roleId,  //树的数据地址
                            dataStyle:"layuiStyle",//数据风格为layui风格  ：code 0 msg  data
                            dataFormat:"list",
                            response:{message:"msg",statusCode:200}, // 修改返回数据的定义
                            checkbar:true
                        });

                    },
                    btn:['确认','取消'],
                    btnAlign:"c",
                    yes:function (index,layero) {//点击确认时 模拟点击提交
                       //点击确认时，提交所有的被选中的菜单的ID  和  当前的角色ID
                        //获取所有选中的复选框
                        var params = dtree.getCheckbarNodesParam("roleMenu");
                        console.log(params);
                        //获取所有的复选框中菜单ID
                        var param = "";
                        $.each(params,function (index,value) {
                            param = param +"menuId="+value.nodeId+"&";
                        })
                        param = param +"roleId="+roleId;
                        //提交修改数据的请求
                        $.post("${pageContext.request.contextPath}/sys/role/setRoleMenu.do?"+param,function (rs) {
                            if (rs.code != 200){
                                layer.msg(rs.msg);
                                return false;
                            }
                            //刷新数据
                            $("#searchBtn").click();
                            //关闭弹出层
                            layer.close(index);
                        })


                    }
                });
            }

        })
    </script>
</body>
</html>
