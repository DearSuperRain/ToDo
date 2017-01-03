<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>任务信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/td/tdTask/">任务信息列表</a></li>
		<li class="active"><a href="${ctx}/td/tdTask/view?id=${tdTask.id}">任务信息<shiro:hasPermission name="td:tdTask:view">${not empty tdTask.id?'查看':'添加'}</shiro:hasPermission><shiro:lacksPermission name="td:tdTask:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tdTask" action="${ctx}/td/tdTask/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
			
		<div class="control-group">
			<label class="control-label">任务内容：</label>
			<div class="controls">
				${tdTask.taskContent}
			</div>
		</div>
		<div class="row">
		<div class="control-group span7"style="width:330px">
			<label class="control-label">四象限：</label>
			<div class="controls">
				${fns:getDictLabel(tdTask.fourQuadrant, 'four_quadrant', '')}
			</div>
		</div>
		<div class="control-group span7"style="width:330px">
			<label class="control-label">任务开始日期：</label>
			<div class="controls">
				<fmt:formatDate value="${tdTask.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</div>
		</div>
		<div class="control-group span7"style="width:330px">
			<label class="control-label">任务结束日期：</label>
			<div class="controls">
				<fmt:formatDate value="${tdTask.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</div>
		</div>
		<div class="control-group span7"style="width:330px">
			<label class="control-label">所属群组：</label>
			<div class="controls">
				${tdTask.groupName}
			</div>
		</div>
		<div class="control-group span7"style="width:330px">
			<label class="control-label">所属项目：</label>
			<div class="controls">
				${tdTask.projectName}
			</div>
		</div>
		<div class="control-group span7"style="width:330px">
			<label class="control-label">任务标签：</label>
			<div class="controls">
				${tdTask.labelName}
			</div>
		</div>
		<div class="control-group span7"style="width:330px">
			<label class="control-label">任务地点：</label>
			<div class="controls">
				${tdTask.siteName}
			</div>
		</div>
		<div class="control-group span7"style="width:330px">
			<label class="control-label">任务状态：</label>
			<div class="controls">
				${fns:getDictLabel(tdTask.taskStatus, 'task_status', '')}
			</div>
		</div>
		<div class="control-group span7"style="width:330px">
			<label class="control-label">是否提醒：</label>
			<div class="controls">
				${fns:getDictLabel(tdTask.isRemind, 'isRemind', '')}
			</div>
		</div>
		<div class="control-group span7"style="width:330px">
			<label class="control-label">是否重复：</label>
			<div class="controls">
				${fns:getDictLabel(tdTask.isRepeat, 'isRepeat', '')}
			</div>
		</div>
		<div class="control-group span7"style="width:330px">
			<label class="control-label">任务备注：</label>
			<div class="controls">
				${tdTask.taskRemarks}
			</div>
		</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务总结：</label>
			<div class="controls">
				<textarea htmlEscape="false" rows="8"  maxlength="1024" class="input-xlarge "style="width:600px;background:transparent;border-style:none; resize:none;" readonly>${tdTask.taskSummary}</textarea>
			</div>
		</div>
		
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>