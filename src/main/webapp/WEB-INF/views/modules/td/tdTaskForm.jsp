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
		<li class="active"><a href="${ctx}/td/tdTask/form?id=${tdTask.id}">任务信息<shiro:hasPermission name="td:tdTask:edit">${not empty tdTask.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="td:tdTask:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tdTask" action="${ctx}/td/tdTask/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
			
		<div class="control-group">
			<label class="control-label">任务内容：</label>
			<div class="controls">
				<form:textarea path="taskContent" htmlEscape="false" rows="2"  maxlength="256" class="input-xlarge "style="width:600px"/>
			</div>
		</div>
		<div class="row">
		<div class="control-group span7"style="width:330px">
			<label class="control-label">四象限：</label>
			<div class="controls">
				<form:select path="fourQuadrant" class="input-xlarge "style="width:120px">
					<form:option value="-1" label="--请选择--"/>
					<form:options items="${fns:getDictList('four_quadrant')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group span7"style="width:330px">
			<label class="control-label">任务开始日期：</label>
			<div class="controls">
				<input name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${tdTask.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" style="width:120px"/>
			</div>
		</div>
		<div class="control-group span7"style="width:330px">
			<label class="control-label">任务结束日期：</label>
			<div class="controls">
				<input name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${tdTask.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" style="width:120px"/>
			</div>
		</div>
		<div class="control-group span7"style="width:330px">
			<label class="control-label">所属群组：</label>
			<div class="controls">
				<form:select path="locationGroup" class="input-xlarge "style="width:120px">
					<form:option value="-1" label="--请选择--"/>
					<form:options items="${groups}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group span7"style="width:330px">
			<label class="control-label">所属项目：</label>
			<div class="controls">
				<form:select path="locationProject" class="input-xlarge "style="width:120px">
					<form:option value="-1" label="--请选择--"/>
					<form:options items="${projects}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group span7"style="width:330px">
			<label class="control-label">任务标签：</label>
			<div class="controls">
				<form:select path="taskLabel" class="input-xlarge "style="width:120px">
					<form:option value="-1" label="--请选择--"/>
					<form:options items="${labels}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group span7"style="width:330px">
			<label class="control-label">任务地点：</label>
			<div class="controls">
				<form:select path="taskSite" class="input-xlarge "style="width:120px">
					<form:option value="-1" label="--请选择--"/>
					<form:options items="${sites}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group span7"style="width:330px">
			<label class="control-label">任务状态：</label>
			<div class="controls">
				<form:select path="taskStatus" class="input-xlarge "style="width:120px">
					<form:option value="-1" label="--请选择--"/>
					<form:options items="${fns:getDictList('task_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group span7"style="width:330px">
			<label class="control-label">是否提醒：</label>
			<div class="controls">
				<form:select path="isRemind" class="input-xlarge "style="width:120px">
					<form:option value="-1" label="--请选择--"/>
					<form:options items="${fns:getDictList('is_remind')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group span7"style="width:330px">
			<label class="control-label">是否重复：</label>
			<div class="controls">
				<form:select path="isRepeat" class="input-xlarge "style="width:120px">
					<form:option value="-1" label="--请选择--"/>
					<form:options items="${fns:getDictList('is_repeat')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group span7"style="width:330px">
			<label class="control-label">任务备注：</label>
			<div class="controls">
				<form:input path="taskRemarks" htmlEscape="false" maxlength="64" class="input-xlarge "style="width:120px"/>
			</div>
		</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务总结：</label>
			<div class="controls">
				<form:textarea path="taskSummary" htmlEscape="false" rows="5"  maxlength="1024" class="input-xlarge "style="width:600px"/>
			</div>
		</div>
		
		<div class="form-actions">
			<shiro:hasPermission name="td:tdTask:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>