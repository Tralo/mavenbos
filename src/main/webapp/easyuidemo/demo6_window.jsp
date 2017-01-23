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
<script type="text/javascript">
	$(function(){
		
	});
	function openWindow(){
		$("#mywindow").window('open');
	}
</script>
</head>
<body>
	<!-- 通过 easyui-window 控制窗口 -->
	<div id="mywindow" class="easyui-window" data-options="title:'标题',closed:true" style="width:250px;height:180px;">窗口显示</div>
	<input type="button" value="打开窗口" onclick="openWindow();" />

</body>
</html>