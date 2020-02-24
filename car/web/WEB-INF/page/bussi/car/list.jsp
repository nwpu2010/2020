<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>车辆列表</title>
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
            <label class="layui-form-label" for="type">
                车型
            </label>
            <div class="layui-input-inline">
                <input class="layui-input" placeholder="小轿车/SUV" id="type" />
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label" for="color">
                颜色
            </label>
            <div class="layui-input-inline">
                <input class="layui-input" placeholder="汽车颜色" id="color" />
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label" for="minRentPrice">
                出租价格
            </label>
            <div class="layui-input-inline" style="width: 100px;">
                <input class="layui-input" placeholder="最低价格" id="minRentPrice" />
            </div>
            <div class="layui-form-mid">-</div>
            <div class="layui-input-inline" style="width: 100px;">
                <input class="layui-input" placeholder="最高价格" id="maxRentPrice" />
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label" for="isRent">
                出租状态
            </label>
            <div class="layui-input-inline">
                <select id="isRent">
                    <option value="">请选择出租状态</option>
                    <option value="1">未出租</option>
                    <option value="2">已出租</option>
                </select>
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
</script>
<script type="text/html" id="rowBtns">
    <button class="layui-btn layui-btn-normal layui-btn-sm" lay-event="rent">出租</button>
</script>
<script type="text/html" id="updateForm">
    <form class="layui-form layui-form-pane" style="padding-left: 10px;padding-top: 5px" lay-filter="updateFormFilter">
        <div>
            <div class="layui-form-item" style="width: 320px;float: left">
                <div class="layui-inline">
                    <label class="layui-form-label">
                        车牌号
                    </label>
                    <div class="layui-input-inline">
                        <input class="layui-input" placeholder="请输入车牌号" id="addCarNum" name="carNum"  lay-verify="required" lay-reqText="车牌号不能为空" />
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">
                        车型
                    </label>
                    <div class="layui-input-inline">
                        <input class="layui-input" id="addType" name="type" placeholder="轿车/SUV" lay-verify="required" lay-reqText="车型不能为空"  />
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">
                        颜色
                    </label>
                    <div class="layui-input-inline">
                        <input class="layui-input" id="addColor" name="color" placeholder="颜色" lay-verify="required" lay-reqText="颜色不能为空"  />
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">
                        价格
                    </label>
                    <div class="layui-input-inline">
                        <input class="layui-input" id="addPrice" name="price" lay-verify="required|number" lay-reqText="价格不能为空"  />
                    </div>
                </div>
            </div>
            <img id="carImg" style="width:295px;height: 174px;float: left" src="${pageContext.request.contextPath}/resources/images/ckpng.jpg"/>
            <button type="button" id="uploadBtn" style="display: none">上传图片</button>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">
                    出租价格
                </label>
                <div class="layui-input-inline">
                    <input class="layui-input"  id="addRentPrice" name="rentPrice" lay-verify="required|number" lay-reqText="出租价格不能为空"  />
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">
                    押金
                </label>
                <div class="layui-input-inline">
                    <input class="layui-input" id="addDeposit" name="deposit" lay-verify="required|number" lay-reqText="押金不能为空"  />
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-block">
                <label class="layui-form-label">
                    介绍
                </label>
                <div class="layui-input-block">
                    <textarea style="width: 512px" class="layui-textarea" placeholder="车辆信息" id="addDesc" name="desc"></textarea>
                </div>
            </div>
            <button  style="display: none" id="submitBtn" lay-filter="submitBtnFilter" lay-submit />
        </div>
    </form>
</script>
<%-- 汽车出租表单 --%>
<script type="text/html" id="rentForm">
    <form class="layui-form layui-form-pane" style="padding-left: 10px;padding-top: 5px" lay-filter="rentFormFilter">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">
                    车牌号
                </label>
                <div class="layui-input-inline">
                    <input class="layui-input"  readonly name="carNum"  />
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">
                    客户身份证
                </label>
                <div class="layui-input-inline">
                    <input class="layui-input" name="idCard" lay-verify="identity"  />
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">
                    出租价格
                </label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input"  name="price" readonly  />
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">
                    出租时间
                </label>
                <div class="layui-input-inline">
                    <input class="layui-input" id="rentTime"  name="rentTime" readonly lay-verify="required" lay-reqText="请选择车辆出租时间"  />
                </div>
            </div>
        </div>
        <button  style="display: none" id="submitBtn" lay-filter="submitBtnFilter" lay-submit />
    </form>
</script>


<%-- 引入layui的js --%>
<script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script>

    //显示汽车图片
    function  showImg(imgPath) {
        debugger;
        var json = {
            "title": "汽车图片", //相册标题
            "start": 0, //初始显示的图片序号，默认0
            "data": [   //相册包含的图片，数组格式
                {
                    "alt": "汽车图片",
                    "src": "${pageContext.request.contextPath}/"+imgPath, //原图地址
                }
            ]
        };
        layer.photos({
            photos: json
            ,anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
        });
    }

    layui.use(['table','jquery','layer','form','upload','laydate'],function () {
        let table = layui.table;
        let $ = layui.jquery;
        let layer = layui.layer;
        let form = layui.form;
        let upload = layui.upload;
        let laydate = layui.laydate;
        //初始化数据表格
        var t = table.render({
            id:"dataTableId",
            elem:"#dataTable",
            page:true,//开启分页
            url:"${pageContext.request.contextPath}/bussi/car/list.do",//数据接口
            toolbar: "#headerBtns",//头工具栏
            cols:[[
                {type:"checkbox"},
                {field:"carNum",title:"车牌号"},
                {field:"type",title:"车型"},
                {field:"color",title:"颜色"},
                {field:"price",title:"价格"},
                {field:"rentPrice",title:"出租价格"},
                {field:"deposit",title:"押金"},
                {field:"img",title:"查看图片",templet:function (d) {
                        let str = d.img;
                        return "<button class=layui-btn layui-btn-sm onclick=showImg('"+str+"')>查看</button>";
                    }
                },
                {field:"isRent",title:"出租状态",templet:function (d) {
                        let s = d.isRent;
                        if(s == 1){
                            return "<b color='green'>未出租</b>";
                        }else if (s == 2){
                            return "<b color='red'>已出租</b>";
                        }
                    }},
                {title:"操作",toolbar:"#rowBtns"}
            ]],
            parseData:function (rs) {
                if(rs.code == 200){
                    return {
                        "code": rs.code, //解析接口状态
                        "msg": rs.msg, //解析提示文本
                        "count": rs.data.total, //解析数据长度
                        "data": rs.data.list //解析数据列表
                    }
                }

            },
            response:{
                statusCode: 200 //规定成功的状态码，默认：0
            }
        });
        //搜索功能
        $("#searchBtn").click(function () {
            let carNum = $("#carNum").val();
            let type = $("#type").val();
            let color = $("#color").val();
            let minRentPrice = $("#minRentPrice").val();
            let maxRentPrice = $("#maxRentPrice").val();
            let isRent = $("#isRent").val();
            t.reload({
                where:{
                    page:1,
                    limit:10,
                    carNum:carNum,
                    type:type,
                    color:color,
                    minRentPrice:minRentPrice,
                    maxRentPrice:maxRentPrice,
                    isRent:isRent
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
                area:['680px','500px'],
                success:function(layero,index){//为表单绑定提交监听事件
                    let formData = null;
                    //图片文件上传监听
                    upload.render({
                        elem:"#carImg",
                        url: '${pageContext.request.contextPath}/bussi/car/add.do', //上传接口
                        auto: false, //选择文件后不自动上传
                        data:{
                            carNum:function () {
                                return $('#addCarNum').val();
                            },
                            type:function () {
                                return $('#addType').val();
                            },
                            color:function () {
                                return $('#addColor').val();
                            },
                            price:function () {
                                return $('#addPrice').val();
                            },
                            rentPrice:function () {
                                return $('#addRentPrice').val();
                            },
                            deposit:function () {
                                return $('#addDeposit').val();
                            },
                            desc:function () {
                                return $('#addDesc').val();
                            },
                        },
                        bindAction:"#uploadBtn",//绑定文件上传的按钮
                        field:"carImg",
                        done: function(rs){//上传完毕后的回调
                            //上传完毕回调
                            if (rs.code != 200){
                                layer.msg(rs.msg);
                                return false;
                            }
                            //刷新表格数据
                            $("#searchBtn").click();
                            layer.close(index);
                        },
                        choose: function(obj){//选择完 文件后的回调
                            //获取表单数据
                            //文件预览
                            obj.preview(function(index, file, result){
                                $("#carImg").attr("src",result);
                            });
                        }
                    });
                    //ajax 只能提交form表单的文本数据
                    //为表单绑定提交事件
                    form.on("submit(submitBtnFilter)",function (d) {
                        let data = d.field;
                        $("#uploadBtn").click();// 触发文件上传
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
            if(event == "rent"){
                //重置的的方法
                rent(d.data);
            }
        });
        function  rent(car) {
            layer.open({
                type:1,
                content:$("#rentForm").html(),
                area:['680px','400px'],
                success:function(layero,index){//为表单绑定提交监听事件
                    //表单赋值
                    form.val("rentFormFilter",{
                        carNum :car.carNum,
                        price :car.rentPrice
                    })
                    //初始化时间控件选择器
                    laydate.render({
                        elem:"#rentTime",
                        type: 'date',
                        range: "~" //或 range: '~' 来自定义分割字符
                    });
                    //ajax 只能提交form表单的文本数据
                    //为表单绑定提交事件
                    form.on("submit(submitBtnFilter)",function (d) {
                        let data = d.field;
                        let time = data.rentTime.split("~");
                        data.beginTime = time[0].trim(); //起租时间
                        data.returnTime = time[1].trim(); //预计还车时间
                        $.post('${pageContext.request.contextPath}/bussi/rent/rent.do',data,function (rs) {
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
