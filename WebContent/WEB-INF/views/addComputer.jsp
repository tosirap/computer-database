<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Computer Database</title>
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
<link href="css/addAndEdit.css" rel="stylesheet" media="screen">
<link href="css/dashboard.css" rel="stylesheet" media="screen">

</head>
<body>

	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">

			<a class="navbar-brand"
				href="dashboard?page=1&search=&orderby=computer.id&asc=true"> <spring:message
					code="application.name" text="Computer database">
				</spring:message>
			</a> <a class="navbar-brand"
				href="https://excilys.facebook.com/profile.php?id=100035468145929">
				&Pscr;&ascr;&uscr;&lscr;&ndash;&ascr;&nscr;&dscr;&escr;&ascr; </a> <a class="nav navbar-brand navbar-right"
				href="addComputer?lang=FR"> <img
				src="https://d24irw6hr5upwc.cloudfront.net/1-large_default/drapeau-france-5075-cm.jpg"
				alt="la fronce" /></a> <a class="nav navbar-brand navbar-right" href="addComputer?lang=EN">
				<img
				src="https://www.color-stickers.com/2472-thickbox_default/stickers-drapeau-angleterre.jpg"
				alt="English" />
			</a>

		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">

					<div class="alert alert-danger" role="alert" id="error"></div>
					<c:out value="${error}" />
					<c:if test="${message != null}">
						<div class="alert alert-success" role="alert" id="messageReussite">${message}</div>
					</c:if>
					<c:if test="${messageErreur != null}">
						<div class="alert alert-danger" role="alert" id="messageErreur">${messageErreur}</div>
					</c:if>
					<h1><spring:message
							code="dashboard.add" text="Add Computer">
						</spring:message></h1>
					<form action="${pageContext.request.contextPath}/addComputer" method="POST">
						<fieldset>
							<div class="form-group">
								<label for="computerName"><spring:message code="computer.name" text="Computer name">
							</spring:message></label> <input
									type="text" class="form-control" id="computerName"
									name="computerName" placeholder="<spring:message code="computer.name" text="Computer name">
							</spring:message>">
							</div>
							<div class="form-group">
								<label for="introduced"><spring:message code="computer.introduced"
								text="Introduced date">
							</spring:message></label> <input
									type="date" class="form-control" id="introduced"
									name="introduced" placeholder="Introduced date">
							</div>
							<div class="form-group">
								<label for="discontinued"><spring:message code="computer.discontinued"
								text="Discontinued date">
							</spring:message></label> <input
									type="date" class="form-control" id="discontinued"
									name="discontinued" placeholder="Discontinued date">
							</div>
							<div class="form-group">
								<label for="companyId"><spring:message code="company.name" text="Company">
							</spring:message></label> <select
									class="form-control" id="companyId" name="companyId">

									<option value = 0> <spring:message code="company.unknow" text="Unknow">
							</spring:message></option>
									<c:forEach items="${alCompany}" var="s">

										<option value="${s.id}">${s.name}</option>

									</c:forEach>

								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Add" class="btn btn-primary" id="submit">
							or <a href="dashboard?page=1" class="btn btn-default" >Cancel</a>
						</div>
					</form>
					<c:out value="${request.getContextPath}"></c:out>
				</div>
			</div>
		</div>
	</section>
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/verifComputer.js"></script>
</body>
</html>