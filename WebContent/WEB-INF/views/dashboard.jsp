<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
<link href="css/dashboard.css" rel="stylesheet" media="screen">
<title>Computer database</title>
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> Application - Computer
				Database </a>
		</div>
	</header>

	<section id="main">

<!-- Definition des URl -->
	<c:if test="${search == null }"><c:set var = "searchUrl" scope = "session" value=""/>   </c:if>
	<c:if test="${search != null }"><c:set var = "searchUrl" scope = "session" value="&search=${search}"/>  </c:if>
	
		<div class="container">
			<h1 id="homeTitle">${total}
				résultats trouvés
				<c:if test="${search != null}"> pour "${search}"</c:if>
			</h1>
			
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm"
						action="dashboard?page=1&PCparPage=${PCparPage}" method="GET"
						class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name"
							required="required" /> <input type="submit" id="searchsubmit"
							value="Filter by name" class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addComputer">Add
						Computer</a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="delete" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>



						<th>Computer name <a
							href="dashboard?page=${page}&PCparPage=${PCparPage}${searchUrl}&orderby=computer.name&asc=true">
								<i class="fa fa-arrow-circle-o-down"></i>
						</a> <a
							href="${mode}?page=${page}&PCparPage=${PCparPage}${searchUrl}&orderby=computer.name&asc=false">
								<i class="fa fa-arrow-circle-o-up"></i>
						</a></th>
						<th>Introduced date <a
							href="dashboard?page=${page}&PCparPage=${PCparPage}${searchUrl}&orderby=computer.introduced&asc=true">
								<i class="fa fa-arrow-circle-o-down"></i>
						</a><a
							href="dashboard?page=${page}&PCparPage=${PCparPage}${searchUrl}&orderby=computer.introduced&asc=false">
								<i class="fa fa-arrow-circle-o-up"></i>
						</a></th>
						<!-- Table header for Discontinued Date -->
						<th>Discontinued date <a
							href="dashboard?page=${page}&PCparPage=${PCparPage}${searchUrl}&orderby=computer.discontinued&asc=true">
								<i class="fa fa-arrow-circle-o-down"></i>
						</a><a
							href="dashboard?page=${page}&PCparPage=${PCparPage}${searchUrl}&orderby=computer.discontinued&asc=false">
								<i class="fa fa-arrow-circle-o-up"></i>
						</a></th>
						<!-- Table header for Company -->
						<th>Company <a
							href="dashboard?page=${page}&PCparPage=${PCparPage}${searchUrl}&orderby=company.name&asc=true">
								<i class="fa fa-arrow-circle-o-down"></i>
						</a><a
							href="dashboard?page=${page}&PCparPage=${PCparPage}${searchUrl}&orderby=company.name&asc=false">
								<i class="fa fa-arrow-circle-o-up"></i>
						</a></th>



					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach items="${listComputer}" var="s">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${s.id}"></td>
							<td><a
								href="editComputer?id=${s.id}&name=${s.name}&intro=${s.introduced}&discon=${s.discontinuted}&company=${s.companyName}"
								onclick="">${s.name}</a></td>
							<td>${s.introduced}</td>
							<td>${s.discontinuted}</td>
							<td>${s.companyName}</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">

				<li><a
					href="dashboard?page=${page-1}&PCparPage=${PCparPage}${searchUrl}&orderby=${orderby}&asc=${asc}"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>

				<c:forEach begin="${begin}" end="${end}" varStatus="loop">
					<li><a
						href="dashboard?page=${loop.index}&PCparPage=${PCparPage}${searchUrl}&orderby=${orderby}&asc=${asc}"
						<c:if test="${page == loop.index}">
						class= "uncheckable"
						</c:if>>${loop.index}</a></li>
				</c:forEach>

				<li><a
					href="dashboard?page=${page+1}&PCparPage=${PCparPage}${searchUrl}&orderby=${orderby}&asc=${asc}"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>


			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">

				<a
					href="dashboard?page=1&PCparPage=10${searchUrl}&orderby=${orderby}&asc=${asc}"
					class="btn btn-default">10</a> <a
					href="dashboard?page=1&PCparPage=50${searchUrl}&orderby=${orderby}&asc=${asc}"
					class="btn btn-default">50</a> <a
					href="dashboard?page=1&PCparPage=100${searchUrl}&orderby=${orderby}&asc=${asc}"
					class="btn btn-default">100</a>

			</div>
		</div>
	</footer>
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/dashboard.js"></script>

</body>
</html>