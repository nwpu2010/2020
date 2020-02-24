<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>检查单列表</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css" />
</head>
<body style="padding-left: 10px;padding-top: 10px">
    <form class="layui-form layui-form-pane">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label" for="checkNo">
                    检查单号
                </label>
                <div class="layui-input-inline">
                    <input class="layui-input" placeholder="检查单号" id="checkNo" />
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label" for="rentNo">
                  出租单
                </label>
                <div class="layui-input-inline">
                    <input class="layui-input" placeholder="出租单" id="rentNo" />
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label" for="minCreateTime">
                    创建时间
                </label>
                <div class="layui-input-inline" style="width: 100px;">
                    <input class="layui-input" placeholder="创建时间" id="minCreateTime" />
                </div>
                <div class="layui-form-mid">-</div>
                <div class="layui-input-inline" style="width: 100px;">
                    <input class="layui-input" placeholder="创建时间" id="maxCreateTime" />
                </div>
            </div>
            <button type="button" class="layui-btn" id="searchBtn">搜索</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </form>
    <table id="dataTable" lay-filter="dataTableFilter"></table>
    <%-- 引入layui的js --%>
    <script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
    <script>
        layui.use(['table','jquery','layer','form','laydate'],function () {
            let table = layui.table;
            let $ = layui.jquery;
            let layer = layui.layer;
            let form = layui.form;
            let laydate = layui.laydate;
            laydate.render({
                elem:"#minCreateTime"
            });
            laydate.render({
                elem:"#maxCreateTime"
            });

            //初始化数据表格
            var t = table.render({
                id:"dataTableId",
                elem:"#dataTable",
                page:true,//开启分页
                url:"${pageContext.request.contextPath}/bussi/checks/list.do",//数据接口
                toolbar: true,//头工具栏
                cols:[[
                    {type:"checkbox"},
                    {field:"checkNo",title:"检查单号"},
                    {field:"rentNo",title:"出租单号"},
                    {field:"checkTime",title:"检查时间"},
                    {field:"rentMoney",title:"出租金额"},
                    {field:"payMoney",title:"赔付金额"},
                    {field:"problem",title:"问题"},
                    {field:"desc",title:"描述"},
                    {field:"totalMoney",title:"总金额"},
                    {field:"createTime",title:"创建时间"}
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
                let checkNo = $("#checkNo").val();
                let rentNo = $("#rentNo").val();
                let minCreateTime = $("#minCreateTime").val();
                let maxCreateTime = $("#maxCreateTime").val();
                t.reload({
                    where:{
                        page:1,
                        limit:10,
                        checkNo:checkNo,
                        rentNo:rentNo,
                        minCreateTime:minCreateTime,
                        maxCreateTime:maxCreateTime
                    }
                })
            });
        })
    </script>
</body>
</html>
