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
		<li class="active"><a href="${ctx}/task/tdTask/">任务信息列表</a></li>
		<shiro:hasPermission name="task:tdTask:edit"><li><a href="${ctx}/task/tdTask/form">任务信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tdTask" action="${ctx}/task/tdTask/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>任务内容：</label>
				<form:input path="taskContent" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>四象限：</label>
				<form:select path="fourQuadrant" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('four_quadrant')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>任务开始日期：</label>
				<input name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${tdTask.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>所属群组：</label>
				<form:select path="locationGroup" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>所属项目：</label>
				<form:select path="locationProject" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>任务状态：</label>
				<form:select path="taskStatus" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('task_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>是否提醒：</label>
				<form:select path="isRemind" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('is_remind')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>是否重复：</label>
				<form:select path="isRepeat" class="input-medium">
					<form:option value="" label=""/>
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
				<th>任务内容</th>
				<th>四象限</th>
				<th>任务开始日期</th>
				<th>任务结束日期</th>
				<th>所属群组</th>
				<th>所属项目</th>
				<th>任务标签</th>
				<th>任务地点</th>
				<th>任务状态</th>
				<th>是否提醒</th>
				<th>是否重复</th>
				<th>任务备注</th>
				<th>任务总结</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="task:tdTask:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tdTask">
			<tr>
				<td><a href="${ctx}/task/tdTask/form?id=${tdTask.id}">
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
					${fns:getDictLabel(tdTask.locationGroup, '', '')}
				</td>
				<td>
					${fns:getDictLabel(tdTask.locationProject, '', '')}
				</td>
				<td>
					${fns:getDictLabel(tdTask.taskLabel, '', '')}
				</td>
				<td>
					${fns:getDictLabel(tdTask.taskSite, '', '')}
				</td>
				<td>
					${fns:getDictLabel(tdTask.taskStatus, 'task_status', '')}
				</td>
				<td>
					${fns:getDictLabel(tdTask.isRemind, 'is_remind', '')}
				</td>
				<td>
					${fns:getDictLabel(tdTask.isRepeat, 'is_repeat', '')}
				</td>
				<td>
					${tdTask.taskRemarks}
				</td>
				<td>
					${tdTask.taskSummary}
				</td>
				<td>
					<fmt:formatDate value="${tdTask.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${tdTask.remarks}
				</td>
				<shiro:hasPermission name="task:tdTask:edit"><td>
    				<a href="${ctx}/task/tdTask/form?id=${tdTask.id}">修改</a>
					<a href="${ctx}/task/tdTask/delete?id=${tdTask.id}" onclick="return confirmx('确认要删除该任务信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>