<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

	<head>
		<title>Title</title>

		<script src="<%= request.getContextPath()%>/resources/layui/layui.js"></script>

		<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/resources/layui_ext/dtree/dtree.css">
		<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/resources/layui/css/layui.css">
		<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/resources/layui_ext/dtree/font/dtreefont.css">
	</head>

	<body>
		<div class="layui-fluid">
			<div class="layui-row">
				<div class="layui-col-md3">
					<ul id="demoTree1" class="dtree" data-id="0" ></ul>
				</div>
				<div class="layui-col-md9">
					<table id="menuListId" lay-filter="menuListFilter"></table>
				</div>
			</div>
		</div>

        <%--头工具栏--%>
        <script type="text/html" id="headToolbar">
        <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
        </div>
        </script>
        <%--表格右侧工具栏--%>
        <script type="text/html" id="rightBar">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
        </script>
        <%--添加一行数据--%>
        <script  type="text/html" id="menudata">

                <form class="layui-form"   lay-filter="menudataFilter" action="">
                        <div class="layui-form-item">
                        <div class="layui-input-block">
                        <input type="hidden" name="id" value="0" class="layui-input">
                        </div>
                        </div>

                        <div class="layui-form-item">
                                 <label class="layui-form-label">父菜单id</label>
                                         <div class="layui-input-block">
                                            <ul id="selTree" class="dtree" data-id="0"   ></ul>
                                </div>
                        </div>

                        <div class="layui-form-item">
                        <label class="layui-form-label">标题</label>
                        <div class="layui-input-block">
                        <input type="text" name="title" required  lay-verify="required" autocomplete="off" class="layui-input">
                        </div>
                        </div>

                        <div class="layui-form-item">
                        <label class="layui-form-label">图标</label>
                        <div class="layui-input-block">
                        <input type="text" name="icon" required  lay-verify="required"  autocomplete="off" class="layui-input">
                        </div>
                        </div>

                        <div class="layui-form-item">
                        <label class="layui-form-label">链接</label>
                        <div class="layui-input-block">
                        <input type="text" name="href" required  lay-verify="required"  autocomplete="off" class="layui-input">
                        </div>
                        </div>

                        <div class="layui-form-item">
                        <label class="layui-form-label">是否展开</label>
                        <div class="layui-input-block">
                                <select name="spread" lay-verify="">
                                        <option value="0">不展开</option>
                                        <option value="1">展开</option>
                                 </select>
                        </div>
                        </div>

                        <div class="layui-form-item">
                        <label class="layui-form-label">目标</label>
                        <div class="layui-input-block">
                        <input type="text" name="target" required  lay-verify="required"  autocomplete="off" class="layui-input">
                        </div>
                        </div>

                        <div class="layui-form-item">
                        <label class="layui-form-label">排序</label>
                        <div class="layui-input-block">
                        <input type="text" name="sort" required  lay-verify="required" autocomplete="off" class="layui-input">
                        </div>
                        </div>



                        <div class="layui-form-item">
                        <div class="layui-input-block">
                        <button class="layui-btn" lay-submit lay-filter="addMenuFilter" style="display: none">提交</button>
                        <button class="layui-btn" lay-submit lay-filter="updateMenuFilter" style="display: none">提交</button>
                        </div>
                        </div>
        </form>


        </script>

        <script  type="text/html">


        </script>
		<%--// 你可以在ul标签上指定data-value--%>
		<%--<ul id="selTree3" class="dtree" data-id="0" data-value="001003"></ul></div>--%>

		<script>
			layui.config({
				base: '<%= request.getContextPath()%>/resources/layui_ext/dtree/'
			}).extend({
				dtree: 'dtree'
			}).use(['element', 'layer', 'dtree', 'table','form'], function() {
				var layer = layui.layer,
					dtree = layui.dtree,
					$ = layui.$,
					table = layui.table,
                     form = layui.form;


				//下拉树
				// dtree.renderSelect({
				// elem: "#selTree3",
				// url: "<%= request.getContextPath()%>/resources/json/demoTree1",
				// skin: "laySimple",
				// selectInitVal: "001003", // 你可以在这里指定默认值
				// done: function(){
				// dtree.dataInit("selTree3", "001003");
				// // 也可以在这里指定，第二个参数如果不填，则会自动返显当前选中的数据
				// var selectParam = dtree.selectVal("selTree3");
				// }
				// });
				//普通渲染
				<%--dtree.render({--%>
				<%--elem: "#demoTree1",  //绑定元素--%>
				<%--url: "<%= request.getContextPath()%> / resources / json / demoTree1 "  //异步接口--%>
				<%--});--%>

				//layui风格渲染
				var menuTree  = dtree.render({
					elem: "#demoTree1",
					url: "<%= request.getContextPath()%>/sys/menu/menu.do",
					dataStyle: "layuiStyle", //使用layui风格的数据格式
					// checkbar:true,//配合json中的checkArr字段，可以在树中增加复选框的功能
					response: {
						message: "msg",
						statusCode: 200
					} //修改response中返回数据的定义
				});
				//单击节点 监听事件
				dtree.on("node('demoTree1')", function(param) {
					layer.msg(JSON.stringify(param));
				});
				//渲染table
				var t = table.render({
					elem: '#menuListId',
                    toolbar:'#headToolbar',
					height: 312,
					url: '<%=request.getContextPath()%>/sys/menu/menuList.do' //数据接口
						,
					parseData: function(res) { //res 即为原始返回的数据
						return {
							"code": res.code, //解析接口状态
							"msg": res.msg, //解析提示文本
							"count": res.data.total, //解析数据长度
							"data": res.data.list //解析数据列表
						}
					},
					response: {
						statusCode: 200 //规定成功的状态码，默认：0
					},
					page: true //开启分页
						,
					cols: [
						[ //表头
							{
								field: 'id',
								title: 'ID',
								width: 80,
								sort: true,
								fixed: 'left'
							}, {
								field: 'parentId',
								title: '父菜单ID',
								width: 80
							}, {
								field: 'title',
								title: '标题',
								width: 80,
								sort: true
							}, {
								field: 'icon',
								title: '图标',
								width: 80
							}, {
								field: 'href',
								title: '链接',
								width: 177
							}, {
								field: 'spread',
								title: '是否展开',
								width: 80,
								sort: true
							}, {
								field: 'target',
								title: '目标页面',
								width: 80,
								sort: true
							}, {
								field: 'sort',
								title: '排序值',
								width: 80
							},
        {
        fixed: 'right', title:'操作', toolbar: '#rightBar', width:150
        }
						]
					]
				});
                //添加一条记录
				function add(){

                    layer.open({
				type: 1,
				content: $('#menudata').html(),
                btn:['确认','取消'],
                yes:function(){
				    //模拟点击隐藏的提交按钮
                        $("button[lay-filter=addMenuFilter]").click();
                },
                btn2:function(index,layero){
				            layer.close(index);
                },
                btnAlign: 'c',
				success: function(layero, index) {
					form.render(); //渲染表单,select选项不显示的问题
					//渲染下拉树
					dtree.renderSelect({
						elem: "#selTree",
						url: "<%= request.getContextPath()%>/sys/menu/menu.do",
						dataStyle: "layuiStyle", //使用layui风格的数据格式
						response: {
							message: "msg",
							statusCode: 200
						}, //修改response中返回数据的定义
						selectInputName: {
							nodeId: "parentId",
							context: "context"
						}
					});
					//添加监听
					form.on('submit(addMenuFilter)', function(data) {
						// layer.alert(JSON.stringify(data.field), {
						//      title: '最终的提交信息'
						//   })
						// $.post(url,[data],[fn],[type])
						$.ajax({
							type: "POST",
							url: "<%= request.getContextPath()%>/sys/menu/add.do",
							data: data.field,
							success: function(data) {
								layer.msg(data.msg);
								if(data.code == 200) {
									//更新树状图和表格数据
									dtree.reload(menuTree, {
										url: "<%= request.getContextPath()%>/sys/menu/menu.do",
									});
									t.reload({
										where: {},
										page: {
											curr: 1
										}
									});
									//关闭弹出层
									layer.close(index);
								}

							}
						});

						return false;
					});
				}

			});

                }
                //修改一条记录
                function  edit(data){

                    layer.open({
				type: 1,
				content: $('#menudata').html(),
                btn:['确认','取消'],
                yes:function(){
				    //模拟点击隐藏的提交按钮
                        $("button[lay-filter=updateMenuFilter]").click();
                },
                btn2:function(index,layero){
				            layer.close(index);
                },
                btnAlign: 'c',
				success: function(layero, index) {
					form.render(); //渲染表单,select选项不显示的问题
                        //初始化赋值问题
                        form.val("menudataFilter",data);
					//渲染下拉树,赋值初始值
					dtree.renderSelect({
						elem: "#selTree",
						url: "<%= request.getContextPath()%>/sys/menu/menu.do",
                        selectInitVal: data.parentId+"", // 你可以在这里指定默认值
                                done: function(res, $ul,){
                                                dtree.dataInit("selTree",data.parentId+"");
                                },
						dataStyle: "layuiStyle", //使用layui风格的数据格式
						response: {
							message: "msg",
							statusCode: 200
						}, //修改response中返回数据的定义
						selectInputName: {
							nodeId: "parentId",
							context: "context"
						}
					});
					//添加监听
					form.on('submit(updateMenuFilter)', function(data) {
						// layer.alert(JSON.stringify(data.field), {
						//      title: '最终的提交信息'
						//   })
						// $.post(url,[data],[fn],[type])
						$.ajax({
							type: "POST",
							url: "<%= request.getContextPath()%>/sys/menu/update.do",
							data: data.field,
							success: function(data) {
								layer.msg(data.msg);
								if(data.code == 200) {
									//更新树状图和表格数据
									dtree.reload(menuTree, {
										url: "<%= request.getContextPath()%>/sys/menu/menu.do",
									});
									t.reload({
										where: {},
										page: {
											curr: 1
										}
									});
									//关闭弹出层
									layer.close(index);
								}

							}
						});

						return false;
					});
				}

			});
        }


				//表格头部工具栏监听事件,

                        table.on('toolbar(menuListFilter)', function(obj){
                          var checkStatus = table.checkStatus(obj.config.id);
                          switch(obj.event){
                            case 'add':
                                add();
                              // layer.msg('添加事件');
                            break;
                                }
                          });
                        //监听行工具栏事件
                        table.on('tool(menuListFilter)', function(obj){
                            var data = obj.data;
                         //   console.log(obj)
                            if(obj.event === 'del'){
                              layer.confirm('真的删除行么', function(index){
                                obj.del();
                                layer.close(index);
                              });
                            } else if(obj.event === 'edit'){
                                      edit(data);
                            }
                          });

			});
		</script>
	</body>

</html>