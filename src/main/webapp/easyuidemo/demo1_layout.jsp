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
	<div data-options="region:'north',title:'北部面板'" style="height:100px;">北部</div>
	<div data-options="region:'south',title:'南部面板'" style="height:100px;">南部</div>
	<div data-options="region:'west',title:'西部面板'" style="width:100px;">西部</div>
	<div data-options="region:'center',title:'中部面板'">中部</div>
	<div data-options="region:'east',title:'东部面板'" style="width:100px;">东部</div>
</body>
</html>