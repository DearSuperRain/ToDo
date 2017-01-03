<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目信息管理</title>
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
		<li><a href="${ctx}/td/tdProject/">项目信息列表</a></li>
		<li class="active"><a href="${ctx}/td/tdProject/view?id=${tdProject.id}">项目信息<shiro:hasPermission name="td:tdProject:view">${not empty tdProject.id?'查看':'添加'}</shiro:hasPermission><shiro:lacksPermission name="td:tdProject:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tdProject" action="${ctx}/td/tdProject/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">项目名称：</label>
			<div class="controls">
				${tdProject.name}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否归档：</label>
			<div class="controls">
				${fns:getDictLabel(tdProject.isArchive, 'is_archive', '')}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目说明：</label>
			<div class="controls">
				<textarea htmlEscape="false" rows="3"  maxlength="64" class="input-xlarge "style="background:transparent;border-style:none; resize:none;" readonly>${tdProject.introduction}</textarea>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>