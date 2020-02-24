<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>出租列表</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css" />
</head>
<body style="padding-left: 10px;padding-top: 10px">
    <form class="layui-form layui-form-pane">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label" for="carNum">
                    车牌号
                </label>
                <div class="layui-input-inline">
                    <input class="layui-input" placeholder="车牌号" id="carNum" />
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label" for="idCard">
                    客户身份证
                </label>
                <div class="layui-input-inline">
                    <input class="layui-input" placeholder=" 客户身份证" id="idCard" />
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
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label" for="minRentTime">
                    出租时间
                </label>
                <div class="layui-input-inline" style="width: 100px;">
                    <input class="layui-input" placeholder="起租时间" id="minRentTime" />
                </div>
                <div class="layui-form-mid">-</div>
                <div class="layui-input-inline" style="width: 100px;">
                    <input class="layui-input" placeholder="还车时间" id="maxRentTime" />
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label" for="flag">
                    还车状态
                </label>
                <div class="layui-input-inline">
                    <select id="flag">
                        <option value="">请选择还车状态</option>
                        <option value="1">未归还</option>
                        <option value="2">已归还</option>
                    </select>
                </div>
            </div>
            <button type="button" class="layui-btn" id="searchBtn">搜索</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </form>
    <table id="dataTable" lay-filter="dataTableFilter"></table>

    <script type="text/html" id="returnCarForm">
        <form class="layui-form layui-form-pane" style="padding-left: 10px;padding-top: 5px" lay-filter="returnCarFormFilter">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">
                        出租单号
                    </label>
                    <div class="layui-input-inline">
                        <input class="layui-input"  readonly name="rentNo"  />
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">
                        检查时间
                    </label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="checkTime" id="checkTime" readonly  />
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">
                        问题
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input"  name="problem"  />
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">
                        赔付金额
                    </label>
                    <div class="layui-input-inline">
                        <input class="layui-input"  name="payMoney" value="0"  lay-verify="number|required" lay-text="请输入赔付金额" />
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-block">
                    <label class="layui-form-label">
                        介绍
                    </label>
                    <div class="layui-input-block">
                        <textarea style="width: 512px" class="layui-textarea"  name="desc"></textarea>
                    </div>
                </div>
                <button  style="display: none" id="submitBtn" lay-filter="submitBtnFilter" lay-submit />
            </div>
        </form>


    </script>

    <script type="text/html" id="rowBtns">
        <button class="layui-btn layui-btn-normal layui-btn-sm" lay-event="returnCar">还车</button>
    </script>

    <%-- 引入layui的js --%>
    <script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
    <script>
        layui.use(['table','jquery','layer','form','laydate'],function () {
            let table = layui.table;
            let $ = layui.jquery;
            let layer = layui.layer;
            let form = layui.form;
            let laydate = layui.laydate;
            //初始化时间控件
            laydate.render({
                elem:"#minRentTime",
                type:'date'
            });
            laydate.render({
                elem:"#maxRentTime",
                type:'date'
            });


            //初始化数据表格
            var t = table.render({
                id:"dataTableId",
                elem:"#dataTable",
                page:true,//开启分页
                url:"${pageContext.request.contextPath}/bussi/rent/list.do",//数据接口
                toolbar: true,//头工具栏
                cols:[[
                    {type:"checkbox"},
                    {field:"rentNo",title:"出租单号"},
                    {field:"carNum",title:"车牌号"},
                    {field:"idCard",title:"客户身份证"},
                    {field:"price",title:"出租金额"},
                    {field:"beginTime",title:"开始时间"},
                    {field:"returnTime",title:"还车时间"},
                    {field:"flag",title:"还车状态",templet:function (d) {
                        let s = d.flag;
                        if(s == 1){
                            return "<b color='green'>未归还</b>";
                        }else if (s == 2){
                            return "<b color='red'>已归还</b>";
                        }
                        }},
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
                let carNum = $("#carNum").val();
                let flag = $("#flag").val();
                let idCard = $("#idCard").val();
                let rentNo = $("#rentNo").val();
                let minRentTime = $("#minRentTime").val();
                let maxRentTime = $("#maxRentTime").val();
                t.reload({
                    where:{
                        page:1,
                        limit:10,
                        carNum:carNum,
                        flag:flag,
                        idCard:idCard,
                        rentNo:rentNo,
                        minRentTime:minRentTime,
                        maxRentTime:maxRentTime
                    }
                })
            });

            //行监听事件
            table.on("tool(dataTableFilter)",function (d) {
                let event = d.event;
                if(event == "returnCar"){
                    //重置的的方法
                    returnCar(d.data);
                }
            });
            function  returnCar(rent) {
                layer.open({
                    type:1,
                    content:$("#returnCarForm").html(),
                    area:['680px','400px'],
                    success:function(layero,index){//为表单绑定提交监听事件
                        //表单赋值
                        form.val("returnCarFormFilter",{
                            rentNo :rent.rentNo,
                        })
                        //初始化时间控件选择器
                        laydate.render({
                            elem:"#checkTime",
                            type: 'date',
                            value:new Date()
                        });
                        //ajax 只能提交form表单的文本数据
                        //为表单绑定提交事件
                        form.on("submit(submitBtnFilter)",function (d) {
                            let data = d.field;
                            $.post('${pageContext.request.contextPath}/bussi/checks/add.do',data,function (rs) {
                                if(rs.code != 200){
                                    layer.msg(rs.msg);
                                    return false;
                                }
                                //刷新表格数据
                                $("#searchBtn").click();
                                //关闭弹层
                                layer.close(index);
                            })
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




        })
    </script>
</body>
</html>
