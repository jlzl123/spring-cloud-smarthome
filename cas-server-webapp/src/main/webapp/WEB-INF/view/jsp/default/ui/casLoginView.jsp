<jsp:directive.include file="includes/top.jsp" />

<div class="box" id="login">
	<form:form method="post" id="fm1" commandName="${commandName}"
		htmlEscape="true">
		<form:errors path="*" id="msg" cssClass="errors" element="div" />

		<section class="row">
			<label for="username"><spring:message
					code="screen.welcome.label.netid" /></label>
			<c:choose>
				<c:when test="${not empty sessionScope.openIdLocalId}">
					<strong><c:out value="${sessionScope.openIdLocalId}" /></strong>
					<input type="hidden" id="username" name="username"
						value="<c:out value="${sessionScope.openIdLocalId}" />" />
				</c:when>
				<c:otherwise>
					<spring:message code="screen.welcome.label.netid.accesskey"
						var="userNameAccessKey" />
					<form:input cssClass="required" cssErrorClass="error" id="username"
						size="25" tabindex="1" accesskey="${userNameAccessKey}"
						path="username" autocomplete="off" htmlEscape="true" />
				</c:otherwise>
			</c:choose>
		</section>

		<section class="row">
			<label for="password"><spring:message
					code="screen.welcome.label.password" /></label>
			<spring:message code="screen.welcome.label.password.accesskey"
				var="passwordAccessKey" />
			<form:password cssClass="required" cssErrorClass="error"
				id="password" size="25" tabindex="2" path="password"
				accesskey="${passwordAccessKey}" htmlEscape="true"
				autocomplete="off" />
		</section>



		<section class="row fl-controls-left">
			<label for="captcha"><spring:message
					code="screen.welcome.label.captcha" /></label>
			<spring:message code="screen.welcome.label.captcha.accesskey"
				var="captchaAccessKey" />
			<table>
				<tr>
					<td><form:input cssClass="required" cssErrorClass="error"
							id="captcha" size="10" tabindex="2" path="captcha"
							accesskey="${captchaAccessKey}" htmlEscape="true"
							autocomplete="off" /></td>
					<td align="left" valign="bottom" style="vertical-align: bottom;">
						<img alt="<spring:message code="required.captcha" />"
						onclick="this.src='captcha.jpg?'+Math.random()" width="93"
						height="30" src="captcha.jpg" />
					</td>
				</tr>
			</table>
		</section>




		<section class="row btn-row">
			<input type="hidden" name="lt" value="${loginTicket}" /> <input
				type="hidden" name="execution" value="${flowExecutionKey}" /> <input
				type="hidden" name="_eventId" value="submit" /> <input
				class="btn-submit" name="submit" accesskey="l"
				value="<spring:message code="screen.welcome.button.login" />"
				tabindex="4" type="submit" /> <input class="btn-reset" name="reset"
				accesskey="c"
				value="<spring:message code="screen.welcome.button.clear" />"
				tabindex="5" type="reset" />
		</section>
	</form:form>
</div>

<jsp:directive.include file="includes/bottom.jsp" />
