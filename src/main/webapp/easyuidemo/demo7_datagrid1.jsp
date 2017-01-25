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



</script>
</head>
<body>
	<h1>将Datagrid 应用 HTML 数据上</h1>
	<!-- 对 table 元素添加 easyui-datagrid -->
	<!-- 使用 thead tbody标记，对每个标题列，设置 field 属性 -->
	<table class="easyui-datagrid" data-options="singleSelect:true,rownumbers:true,pagination:true,striped:true">
		<thead>
			<tr>
				<th data-options="field:'code'">商品编号</th>
				<th data-options="field:'name',width:200">商品名称</th>
				<th data-options="field:'price'">商品价格</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>001</td>
				<td>冰箱</td>
				<td>3000</td>
			</tr>
			<tr>
				<td>002</td>
				<td>洗衣机</td>
				<td>850</td>
			</tr>
		</tbody>
	</table>
	<hr>
	<h1>加载远程数据</h1>
	<table class="easyui-datagrid" data-options="singleSelect:true,rownumbers:true,pagination:true,striped:true,url:'data.json'">
		<thead>
			<tr>
				<th data-options="field:'code'">商品编号</th>
				<th data-options="field:'name',width:200">商品名称</th>
				<th data-options="field:'price'">商品价格</th>
			</tr>
		</thead>
		
	</table>
</body>
</html>