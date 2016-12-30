<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>群组信息管理</title>
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
		<li class="active"><a href="${ctx}/group/tdGroup/">群组信息列表</a></li>
		<shiro:hasPermission name="group:tdGroup:edit"><li><a href="${ctx}/group/tdGroup/form">群组信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tdGroup" action="${ctx}/group/tdGroup/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>群组名称：</label>
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
				<th>群组名称</th>
				<th>群组说明</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="group:tdGroup:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tdGroup">
			<tr>
				<td><a href="${ctx}/group/tdGroup/form?id=${tdGroup.id}">
					${tdGroup.name}
				</a></td>
				<td>
					${tdGroup.introduction}
				</td>
				<td>
					<fmt:formatDate value="${tdGroup.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${tdGroup.remarks}
				</td>
				<shiro:hasPermission name="group:tdGroup:edit"><td>
    				<a href="${ctx}/group/tdGroup/form?id=${tdGroup.id}">修改</a>
					<a href="${ctx}/group/tdGroup/delete?id=${tdGroup.id}" onclick="return confirmx('确认要删除该群组信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>