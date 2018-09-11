<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="true" %>
<fmt:setLocale value="fr" />
<fmt:setBundle basename="com.tjube.i18n.text" var="lang"/>
<!DOCTYPE html>
<html lang="fr">

<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="au theme template">
    <meta name="author" content="Hau Nguyen">
    <meta name="keywords" content="au theme template">

    <!-- Title Page-->
    <title>Dashboard</title>

    <!-- Fontfaces CSS-->
    <link href="<%=request.getContextPath()%>/resources/css/font-face.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/resources/vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/resources/vendor/font-awesome-5/css/fontawesome-all.min.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/resources/vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">

    <!-- Bootstrap CSS-->
    <link href="<%=request.getContextPath()%>/resources/vendor/bootstrap-4.1/bootstrap.min.css" rel="stylesheet" media="all">

    <!-- Vendor CSS-->
    <link href="<%=request.getContextPath()%>/resources/vendor/animsition/animsition.min.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/resources/vendor/bootstrap-progressbar/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/resources/vendor/wow/animate.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/resources/vendor/css-hamburgers/hamburgers.min.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/resources/vendor/slick/slick.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/resources/vendor/select2/select2.min.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/resources/vendor/perfect-scrollbar/perfect-scrollbar.css" rel="stylesheet" media="all">

    <!-- Main CSS-->
    <link href="<%=request.getContextPath()%>/resources/css/theme.css" rel="stylesheet" media="all">

</head>

<body class="animsition">
    <div class="page-wrapper">
        
			<jsp:include page="../menu.jsp"/>

            <!-- MAIN CONTENT-->
            <div class="main-content">
                <div class="section__content section__content--p30">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-lg-9">
                                <h2 class="title-1 m-b-25">Journal du <tags:localDate date="${journal.beginDate}"/> au <tags:localDate date="${journal.endDate}"/></h2>
	                                <div class="table-responsive table--no-card m-b-40">
	                                    <table class="table table-borderless table-striped table-earning">
	                                        <thead>
	                                            <tr>
	                                                <th>date</th>
	<!--                                                 <th class="text-right">tâches quotidiennes</th> -->
	                                                <th class="text-right">tâches mensuelles</th>
	                                                <th class="text-right">budget</th>
	                                            </tr>
	                                        </thead>
	                                        <tbody>
	                                        	<c:forEach items="${months}" var="month">
	                                        	<c:set var="monthlyStat" value="${mapMonthStats[month]}"/>
	                                        		<tr>
	                                        			<td><fmt:message key="label.${month}.month" bundle="${lang}"/></td>
	                                        			<td class="text-center">${monthlyStat.finishMonthlyTasks}/${monthlyStat.allMonthlyTasks} (${monthlyStat.allMonthlyTasksPercent}%)</td>
	                                        			<td class="text-center">${monthlyStat.usedBudget}/${monthlyStat.allbudget} (${monthlyStat.allbudgetPercent}%)</td>
	                                        		</tr>
	                                        	</c:forEach>
	                                        </tbody>
	                                    </table>
	                                </div>
	                                <div class="table-responsive table--no-card m-b-40">
                                        <button class="au-btn-plus-event" onclick="location.href='event/creation?uuid=${journal.uuid}'">
                                            <i class="zmdi zmdi-plus"></i>
                                        </button>
                                    </div>
                                    <table class="table table-borderless table-striped table-earning">
	                                        <thead>
	                                            <tr>
	                                                <th>date</th>
	                                                <th>heure</th>
	                                                <th>description</th>
	                                                <th>lieu</th>
	                                                <th class="text-right"></th>
	                                            </tr>
	                                        </thead>
	                                        <tbody>
	                                        	<c:forEach items="${journal.journalEvents}" var="event">
	                                        		<tr>
	                                        			<td><tags:localDate date="${event.date}"/></td>
	                                        			<td>${event.time}</td>
	                                        			<td>${event.description }</td>
	                                        			<td>${event.place }</td>
	                                        			<td>
	                                        				<div class="table-data-feature">
		                                                        <button class="item" data-toggle="tooltip" data-placement="top" title="Edit" onclick="location.href='event/update?uuid=${event.uuid }';">
		                                                            <i class="zmdi zmdi-edit"></i>
		                                                        </button>
		                                                        <button class="item" data-toggle="tooltip" data-placement="top" title="Delete" onclick="location.href='event/delete?uuid=${event.uuid }';">
		                                                            <i class="zmdi zmdi-delete"></i>
		                                                        </button>
		                                                    </div>
	                                        			</td>
	                                        		</tr>
	                                        	</c:forEach>
	                                        </tbody>
	                                    </table>
	                                </div>
                            </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="copyright">
                                    <p>Copyright © 2018 Colorlib. All rights reserved. Template by <a href="https://colorlib.com">Colorlib</a>.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- END MAIN CONTENT-->
            <!-- END PAGE CONTAINER-->
        </div>

    </div>

    <!-- Jquery JS-->
    <script src="<%=request.getContextPath()%>/resources/vendor/jquery-3.2.1.min.js"></script>
    <!-- Bootstrap JS-->
    <script src="<%=request.getContextPath()%>/resources/vendor/bootstrap-4.1/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/vendor/bootstrap-4.1/bootstrap.min.js"></script>
    <!-- Vendor JS       -->
    <script src="<%=request.getContextPath()%>/resources/vendor/slick/slick.min.js">
    </script>
    <script src="<%=request.getContextPath()%>/resources/vendor/wow/wow.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/vendor/animsition/animsition.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/vendor/bootstrap-progressbar/bootstrap-progressbar.min.js">
    </script>
    <script src="<%=request.getContextPath()%>/resources/vendor/counter-up/jquery.waypoints.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/vendor/counter-up/jquery.counterup.min.js">
    </script>
    <script src="<%=request.getContextPath()%>/resources/vendor/circle-progress/circle-progress.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/vendor/perfect-scrollbar/perfect-scrollbar.js"></script>
    <script src="<%=request.getContextPath()%>/resources/vendor/chartjs/Chart.bundle.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/vendor/select2/select2.min.js">
    </script>

    <!-- Main JS-->
    <script src="<%=request.getContextPath()%>/resources/js/main.js"></script>

</body>

</html>
<!-- end document-->
