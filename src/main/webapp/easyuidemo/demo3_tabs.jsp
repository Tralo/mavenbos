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
			<div data-options="title:'基本功能',iconCls:'icon-add'">面板一</div><!-- 这里每个div就是一个面板 -->
			<div data-options="title:'高级功能',iconCls:'icon-cut'">面板二</div>
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