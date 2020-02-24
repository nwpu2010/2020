
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户列表</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css" />
</head>
<body>
<h1 align="center">账单列表</h1><hr>
<form class="layui-form" action="">
    <div class="layui-form-item">
        <label class="layui-form-label">账单种类</label>
        <div class="layui-input-inline">
            <select name="name">
                <option value="" checked>不限</option>
                <c:forEach items="${typeList}" var="item">
                    <option value="${item.id}">${item.name}</option>
                </c:forEach>
            </select>
        </div>

    <div class="layui-inline">
        <label class="layui-form-label">开始时间</label>
        <div class="layui-input-inline">
            <input type="text" class="layui-input" id="beginTime" placeholder="yyyy-MM-dd">
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">结束时间</label>
        <div class="layui-input-inline">
            <input type="text" class="layui-input" id="endTime" placeholder="yyyy-MM-dd">
        </div>
    </div>
    </div>

    <div   class="layui-form-item" align="center">
            <button type="button" class="layui-btn" id="searchBtn" lay-submit lay-filter="formFilter">查询</button>
            <button type="reset" class="layui-btn layui-btn-primary" >重置</button>
            <button type="button" id="addBtn" class="layui-btn layui-btn-warm">添加</button>
    </div>

</form>

<div>
    <table  class="layui-hide"  id="dataTable" lay-filter="dataTableFilter"></table>

</div>



<script type="text/html" id="addBillForm">
    <form class="layui-form" id="billForm" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">账单种类</label>

                    <c:forEach items="${typeList}" var="item" varStatus="status">

                        <c:if test="${status.first}"> ${item.name} <input   type="radio"  name="typeid"  value="${item.id}" checked></c:if>
                        <c:if test="${not status.first}"> ${item.name} <input  type="radio"  name="typeid"  value="${item.id}" ></c:if>
                    </c:forEach>


        </div>
        <div class="layui-form-item">
                <label class="layui-form-label">标题</label>
                <div class="layui-input-inline">
                <input class="layui-input" name="titile" lay-verify="required"  />
            </div>
        </div>

        <div class="layui-form-item">

                <label class="layui-form-label">日期</label>
            <div class="layui-input-inline">
                <input class="layui-input" id="billtimeId" name="billtime" lay-verify="required"  />
            </div>
        </div>

        <div class="layui-form-item">
                <label class="layui-form-label">金额</label>
            <div class="layui-input-inline">
                <input class="layui-input" name="price" lay-verify="required|number"  />
            </div>
        </div>

        <div class="layui-form-item">

                    <label class="layui-form-label">说明</label>
            <div class="layui-input-block">
                    <textarea class="layui-textarea" placeholder="说明" name="remark"></textarea>
                </div>
            <button style="display: none" id="submitBtn" lay-filter="submitBtnFilter" lay-submit />
        </div>

    </form>
</script>

<script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script>
    layui.use(['table','jquery','layer','form',"laydate"],function () {
            var table = layui.table;
            var $ = layui.jquery;
            var layer = layui.layer;
            var form = layui.form;
            var laydate = layui.laydate;
        //初始化数据表格
        var t = table.render({
            id:"dataTableId",
            elem:"#dataTable",
            page:true,//开启分页
            url:"${pageContext.request.contextPath}/bills/list.do",//数据接口
            cols:[[
                {field:"id",title:"ID"},
                {field:"titile",title:"title"},
                {field:"billtime",title:"时间"},
                {field:"name",title:"类型"},
                {field:"price",title:"金额"},
                {field:"remark",title:"备注"},
            ]],
            response:{
                statusCode: 200 //规定成功的状态码，默认：0
            }
        });

        laydate.render({
            elem: '#beginTime'
        });

        laydate.render({
            elem: '#endTime'
        });


        //搜索功能
        $("#searchBtn").click(function () {
            var billTypeId  = $("select[name='name']").val();
            var beginTime = $("#beginTime").val();
            var endTime = $("#endTime").val();
            t.reload({
                    where:{
                        page:1,
                    limit:10,
                    billTypeId:billTypeId,
                    beginTime:beginTime,
                    endTime:endTime
                }
            })
        });

        //新增
        $("#addBtn").on("click", function(){

            layer.open({
                type:1,
                content:$("#addBillForm").html(),
                area:['660px','500px'],
                success:function(layero,index){//为表单绑定提交监听事件
                    // 重新渲染表单
                    form.render("radio");
                    //渲染时间控件
                    laydate.render({
                        elem: '#billtimeId',
                        type: 'datetime',
                        trigger: 'click'

                        }
                    );
                    //为表单绑定提交事件
                    form.on("submit(submitBtnFilter)",function (d) {
                        var data = d.field;
                        $.post("${pageContext.request.contextPath}/bills/add.do",data,function (rs) {
                            if (rs.code != 200){
                                layer.msg("failed");
                            }else {

                                layer.open({
                                    type: 1,
                                    shade: false,
                                    area: '500px',
                                    content: "<div align='center'>添加成功，是否继续添加？</div>",
                                    zIndex: layer.zIndex, //重点1
                                    success: function(layero){
                                        layer.setTop(layero); //重点2
                                    },
                                    btn:['确认','取消'],
                                    btnAlign:"c",
                                    yes:function (index,layero) {
                                        //点击确认时 模拟点击重置
                                        $("#billForm").get(0).reset();
                                       layer.close(index);
                                    },
                                    btn2: function(index, layero){
                                        t.reload({
                                            where:{
                                                page:1,
                                                limit:10
                                            }
                                        })
                                        layer.closeAll();
                                    }
                                });
                            }

                        });
                        return false;//阻止表单默认提交
                    })

                },
                btn:['确认','取消'],
                btnAlign:"c",
                yes:function (index,layero) {//点击确认时 模拟点击提交
                    $("#submitBtn").click();
                    return  false;//阻止关闭
                }
            });
        });


    })
</script>
</body>
</html>
