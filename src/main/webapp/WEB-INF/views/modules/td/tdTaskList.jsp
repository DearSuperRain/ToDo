<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>任务信息管理</title>
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
		<li class="active"><a href="${ctx}/td/tdTask/">任务信息列表</a></li>
		<shiro:hasPermission name="td:tdTask:edit"><li><a href="${ctx}/td/tdTask/form">任务信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tdTask" action="${ctx}/td/tdTask/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>任务内容：</label>
				<form:input path="taskContent" htmlEscape="false" maxlength="10" class="input-medium" style="width:95px"/>
			</li>
			<li><label>四象限：</label>
				<form:select path="fourQuadrant" class="input-medium" style="width:110px;">
					<form:option value="-1" label="--请选择--"/>
					<form:options items="${fns:getDictList('four_quadrant')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label style="width:110px;">任务开始日期：</label>
				<input name="beginDate" type="text" readonly="readonly" maxlength="10" class="input-medium Wdate"
					value="<fmt:formatDate value="${tdTask.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>所属群组：</label>
				<form:select path="locationGroup" class="input-xlarge "style="width:120px">
					<form:option value="-1" label="--请选择--"/>
					<form:options items="${groups}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
		</ul>
		<ul class="ul-form">
			<li><label>所属项目：</label>
				<form:select path="locationProject" class="input-xlarge "style="width:120px">
					<form:option value="-1" label="--请选择--"/>
					<form:options items="${projects}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>任务状态：</label>
				<form:select path="taskStatus" class="input-medium" style="width:110px;">
					<form:option value="-1" label="--请选择--"/>
					<form:options items="${fns:getDictList('task_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label style="width:110px">是否提醒：</label>
				<form:select path="isRemind" class="input-medium" style="width:110px;">
					<form:option value="-1" label="--请选择--"/>
					<form:options items="${fns:getDictList('is_remind')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label style="width:145px">是否重复：</label>
				<form:select path="isRepeat" class="input-medium" style="width:110px;">
					<form:option value="-1" label="--请选择--"/>
					<form:options items="${fns:getDictList('is_repeat')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>任务内容</th>
				<th>四象限</th>
				<th>任务开始日期</th>
				<th>任务结束日期</th>
				<th>所属群组</th>
				<th>所属项目</th>
				<th>任务标签</th>
				<th>任务地点</th>
				<th>任务状态</th>
				<shiro:hasPermission name="td:tdTask:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tdTask" varStatus="var">
			<tr>
				<td>
					${var.index+1}
				</td>
				<td><a href="${ctx}/td/tdTask/form?id=${tdTask.id}">
					${tdTask.taskContent}
				</a></td>
				<td>
					${fns:getDictLabel(tdTask.fourQuadrant, 'four_quadrant', '')}
				</td>
				<td>
					<fmt:formatDate value="${tdTask.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${tdTask.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${tdTask.groupName}
				</td>
				<td>
					${tdTask.projectName}
				</td>
				<td>
					${tdTask.labelName}
				</td>
				<td>
					${tdTask.siteName}
				</td>
				<td>
					${fns:getDictLabel(tdTask.taskStatus, 'task_status', '')}
				</td>
				<shiro:hasPermission name="td:tdTask:edit"><td>
    				<a href="${ctx}/td/tdTask/form?id=${tdTask.id}">修改</a>
					<a href="${ctx}/td/tdTask/delete?id=${tdTask.id}" onclick="return confirmx('确认要删除该任务信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>