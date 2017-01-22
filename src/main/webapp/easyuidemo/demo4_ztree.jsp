<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 先引入jquery的js -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js" ></script>
<!-- 引入easyui的js -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<!-- 引入国际化的js -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<!-- 引入默认样式css -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css"/>
<!-- 引入图标css -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css"/>

<!-- 引入 ztree -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css" />
<script type="text/javascript">
	$(function(){//页面加载完毕后加载
		var setting = {};
		var zNodes = [
			{"name":"菜单一"},//每个{}就是一个节点
			{"name":"菜单二","children":
				[
					{"name":"子菜单一"},{"name":"子菜单二"}
				]
			}
		];
		//初始化树
		$.fn.zTree.init($("#basicTree"),setting,zNodes);
		//设置树参数
		var setting = {
				data:{
					simpleData:{
						enable:true
					}
				}
		};
		var zNodes = [
			{ "id":"11", "pId":"0", "name":"基础数据",  "click":false},
			{ "id":"112", "pId":"11", "name":"收派标准",  "page":"page_base_standard.action"},
			{ "id":"113", "pId":"11", "name":"取派员设置",  "page":"page_base_staff.action"},
			{ "id":"114", "pId":"11", "name":"区域设置","page":"page_base_region.action"},
			{ "id":"115", "pId":"11", "name":"管理分区", "page":"page_base_subarea.action"},
			{ "id":"116", "pId":"11", "name":"管理定区/调度排班","page":"page_base_decidedzone.action"},
			{ "id":"12", "pId":"0", "name":"受理","click":false },
			{ "id":"121", "pId":"12", "name":"业务受理" ,"page":"page_qupai_noticebill_add.action"},
			{ "id":"122", "pId":"12", "name":"工作单快速录入" ,"page":"page_qupai_quickworkorder.action"},
			{ "id":"124", "pId":"12", "name":"工作单导入" ,"page":"page_qupai_workorderimport.action"},
			{ "id":"13", "pId":"0", "name":"调度","click":false },
			{ "id":"131", "pId":"13", "name":"查台转单","page":""},
			{ "id":"132", "pId":"13", "name":"人工调度","page":"page_qupai_diaodu.action"},
			{ "id":"14", "pId":"0" , "name":"中转配送流程管理","click":false },
			{ "id":"141", "pId":"14", "name":"工作单审核" ,"page":"page_zhongzhuan_check.action"},
			{ "id":"142", "pId":"14", "name":"查看个人任务" ,"page":"page_workflow_personaltask.action"},
			{ "id":"143", "pId":"14", "name":"查看组任务","page":"page_workflow_grouptask.action"}
		];
		//初始化树
		$.fn.zTree.init($("#simpleTree"),setting,zNodes);
		
	});
</script>

</head>

<body class="easyui-layout"> <!-- 使用layout -->
	<!-- 只有center区域是必须的 -->
	<div data-options="region:'north',title:'北部面板'" style="height:150px;">北部</div>
	<div data-options="region:'south',title:'南部面板'" style="height:150px;">南部</div>
	<div data-options="region:'west',title:'西部面板'" style="width:150px;">
		<!-- 折叠面板 -->
		<!-- fit属性，使当前div的大小沾满父容器 -->
		<div class="easyui-accordion" data-options="fit:true">
		<!-- 通过iconCls设置图标 -->
			<div data-options="title:'基本功能',iconCls:'icon-add'">
				<ul id="basicTree" class="ztree"></ul>
			
			</div><!-- 这里每个div就是一个面板 -->
			<div data-options="title:'高级功能',iconCls:'icon-cut'">
				<ul id="simpleTree" class="ztree"></ul>
			</div>
			<div data-options="title:'管理员功能',iconCls:'icon-tip'">面板三</div>
		</div>
	</div>
	<div data-options="region:'center'">
		<!-- 选项卡面板 -->
		<div class="easyui-tabs" data-options="fit:true">
			<!-- closable表示选项卡可关闭 -->
			<div data-options="title:'选项卡一'">内容一</div><!-- 这里每个div就是一个选项卡 -->
			<div data-options="title:'选项卡二',closable:true">内容二</div>
			<div data-options="title:'选项卡三'">内容三</div>
		</div>
	</div>
	<div data-options="region:'east',title:'东部面板'" style="width:150px;">东部</div>
</body>

</html>