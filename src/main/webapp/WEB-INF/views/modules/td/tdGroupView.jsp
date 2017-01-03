<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>群组信息管理</title>
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
		<li><a href="${ctx}/td/tdGroup/">群组信息列表</a></li>
		<li class="active"><a href="${ctx}/td/tdGroup/view?id=${tdGroup.id}">群组信息<shiro:hasPermission name="td:tdGroup:view">${not empty tdGroup.id?'查看':'添加'}</shiro:hasPermission><shiro:lacksPermission name="td:tdGroup:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tdGroup" action="${ctx}/td/tdGroup/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">群组名称：</label>
			<div class="controls">
				${tdGroup.name}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">群组说明：</label>
			<div class="controls">
				<textarea htmlEscape="false" rows="5"  maxlength="512" class="input-xlarge "style="background:transparent;border-style:none; resize:none;" readonly>${tdGroup.introduction}</textarea>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>