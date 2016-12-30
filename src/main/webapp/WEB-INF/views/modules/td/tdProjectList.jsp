<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目信息管理</title>
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
		<li class="active"><a href="${ctx}/td/tdProject/">项目信息列表</a></li>
		<shiro:hasPermission name="td:tdProject:edit"><li><a href="${ctx}/td/tdProject/form">项目信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tdProject" action="${ctx}/td/tdProject/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>项目名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>是否归档：</label>
				<form:select path="isArchive" class="input-medium">
					<form:option value="-1" label="--请选择--"/>
					<form:options items="${fns:getDictList('is_archive')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<th>项目名称</th>
				<th>是否归档</th>
				<th>项目说明</th>
				<shiro:hasPermission name="td:tdProject:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tdProject" varStatus="var">
			<tr>
				<td>
					${var.index+1}
				</td>
				<td><a href="${ctx}/td/tdProject/form?id=${tdProject.id}">
					${tdProject.name}
				</a></td>
				<td>
					${fns:getDictLabel(tdProject.isArchive, 'is_archive', '')}
				</td>
				<td>
					${tdProject.introduction}
				</td>
				<shiro:hasPermission name="td:tdProject:edit"><td>
    				<a href="${ctx}/td/tdProject/form?id=${tdProject.id}">修改</a>
					<a href="${ctx}/td/tdProject/delete?id=${tdProject.id}" onclick="return confirmx('确认要删除该项目信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>