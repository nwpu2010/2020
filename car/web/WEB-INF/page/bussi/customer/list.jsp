<%--
  Created by IntelliJ IDEA.
  User: lee
  Date: 2020/1/13
  Time: 19:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>客户列表</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css" />
</head>
<body style="padding-left: 10px;padding-top: 10px">
<form class="layui-form layui-form-pane">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label" for="idCard">
                身份证
            </label>
            <div class="layui-input-inline">
                <input class="layui-input" placeholder="身份证" id="idCard" />
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label" for="custName">
                客户名称
            </label>
            <div class="layui-input-inline">
                <input class="layui-input" placeholder="客户名称" id="custName" />
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label" for="sex">
                客户性别
            </label>
            <div class="layui-input-inline">
                <select id="sex">
                    <option value="">请选择性别</option>
                    <option value="1">男</option>
                    <option value="2">女</option>
                </select>
            </div>
        </div>


    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label" for="phone">
                手机号
            </label>
            <div class="layui-input-inline">
                <input class="layui-input" placeholder="手机号" id="phone" />
            </div>
        </div>
        <button type="button" class="layui-btn" id="searchBtn">搜索</button>
        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
</form>
<table id="dataTable" lay-filter="dataTableFilter"></table>

<%--按钮模板--%>
<script type="text/html" id="headerBtns">
    <button class="layui-btn layui-btn-sm" lay-event="add">新增</button>
    <button class="layui-btn layui-btn-sm" lay-event="export">导出</button>
</script>
<script type="text/html" id="rowBtns">
    <button class="layui-btn layui-btn-warm layui-btn-sm" lay-event="update">修改</button>
</script>
<script type="text/html" id="updateForm">
    <form class="layui-form layui-form-pane" style="padding-left: 10px;padding-top: 5px" lay-filter="updateFormFilter">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">
                    客户名称
                </label>
                <div class="layui-input-inline">
                    <input class="layui-input" placeholder="请输入客户名称" name="custName"  lay-verify="required" lay-reqText="客户名称不能为空" />
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">
                    身份证
                </label>
                <div class="layui-input-inline">
                    <input class="layui-input" name="idCard" lay-verify="identity"  />
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">
                    性别
                </label>
                <div class="layui-input-inline">
                    <input type="radio"  name="sex"  title="男"  value="1" checked/>
                    <input type="radio"  name="sex"  title="女"  value="2" />
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">
                    手机号
                </label>
                <div class="layui-input-inline">
                    <input class="layui-input" name="phone" lay-verify="phone"  />
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-block">
                <label class="layui-form-label">
                    地址
                </label>
                <div class="layui-input-block">
                    <textarea style="width: 512px" class="layui-textarea" placeholder="请输入地址" name="address"></textarea>
                </div>
            </div>
            <button  style="display: none" id="submitBtn" lay-filter="submitBtnFilter" lay-submit />
        </div>
    </form>
</script>


<%-- 引入layui的js --%>
<script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script>
    layui.use(['table','jquery','layer','form'],function () {
        let table = layui.table;
        let $ = layui.jquery;
        let layer = layui.layer;
        let form = layui.form;
        //初始化数据表格
        var t = table.render({
            id:"dataTableId",
            elem:"#dataTable",
            page:true,//开启分页
            url:"${pageContext.request.contextPath}/bussi/customer/list.do",//数据接口
            toolbar: "#headerBtns",//头工具栏
            cols:[[
                {type:"checkbox"},
                {field:"idCard",title:"身份证"},
                {field:"custName",title:"客户名称"},
                {field:"sex",title:"性别",templet:function (d) {
                        let s = d.sex;
                        if(s == 1){
                            return '男';
                        }else if (s == 2){
                            return  '女';
                        }
                    }},
                {field:"phone",title:"手机号"},
                {field:"address",title:"地址"},
                {field:"createTime",title:"创建时间"},
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
            let idCard = $("#idCard").val();
            let custName = $("#custName").val();
            let sex = $("#sex").val();
            let phone = $("#phone").val();
            t.reload({
                where:{
                    page:1,
                    limit:10,
                    idCard:idCard,
                    custName:custName,
                    sex:sex,
                    phone:phone
                }
            })
        });
        //表格头工具栏事件
        table.on("toolbar(dataTableFilter)",function (d) {
            let event = d.event;
            if(event == "add"){
                add();
            }else if (event == "export"){
                let idCard = $("#idCard").val();
                let custName = $("#custName").val();
                let sex = $("#sex").val();
                let phone = $("#phone").val();
                var param = "";
                var param = param +"idCard="+idCard+"&";
                var param = param + "custName="+custName+"&";
                var param = param + "sex="+sex+"&";
                var param = param + "phone="+phone;
                location.href = "${pageContext.request.contextPath}/bussi/customer/export.do?"+param;
            }
        });
        //新增
        function add(){
            layer.open({
                type:1,
                content:$("#updateForm").html(),
                area:['660px','400px'],
                success:function(layero,index){//为表单绑定提交监听事件
                    // 重新渲染表单
                    form.render("radio");
                    //为表单绑定提交事件
                    form.on("submit(submitBtnFilter)",function (d) {
                        let data = d.field;
                        $.post("${pageContext.request.contextPath}/bussi/customer/add.do",data,function (rs) {
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
            }
        });

        //重置密码
        function update(cust) {
            layer.open({
                type:1,
                content:$("#updateForm").html(),
                area:['660px','400px'],
                success:function(layero,index){//为表单绑定提交监听事件
                    form.val("updateFormFilter",cust);
                    // 重新渲染表单
                    form.render("radio");
                    //为表单绑定提交事件
                    form.on("submit(submitBtnFilter)",function (d) {
                        let data = d.field;
                        data.id = cust.id;
                        $.post("${pageContext.request.contextPath}/bussi/customer/update.do",data,function (rs) {
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


    })
</script>
</body>
</html>
