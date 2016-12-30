<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>标签信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/td/tdLabel/">标签信息列表</a></li>
		<shiro:hasPermission name="td:tdLabel:edit"><li><a href="${ctx}/td/tdLabel/form">标签信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tdLabel" action="${ctx}/td/tdLabel/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标签名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>标签名称</th>
				<th>备注信息</th>
				<shiro:hasPermission name="td:tdLabel:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tdLabel" varStatus="var">
			<tr>
				<td>
					${var.index+1}
				</td>
				<td><a href="${ctx}/td/tdLabel/form?id=${tdLabel.id}">
					${tdLabel.name}
				</a></td>
				<td>
					${tdLabel.remarks}
				</td>
				<shiro:hasPermission name="td:tdLabel:edit"><td>
    				<a href="${ctx}/td/tdLabel/form?id=${tdLabel.id}">修改</a>
					<a href="${ctx}/td/tdLabel/delete?id=${tdLabel.id}" onclick="return confirmx('确认要删除该标签信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>