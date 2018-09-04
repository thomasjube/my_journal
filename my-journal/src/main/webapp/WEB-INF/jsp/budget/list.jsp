<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">

<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="au theme template">
    <meta name="author" content="Hau Nguyen">
    <meta name="keywords" content="au theme template">

    <!-- Title Page-->
    <title>Tables</title>

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
    
    <!-- Circle CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css-circle/css/circle.css">

</head>

<body class="animsition">
    <div class="page-wrapper">

		<jsp:include page="../menu.jsp"/>
		
            <!-- MAIN CONTENT-->
            <div class="main-content">
                <div class="section__content section__content--p30">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-12">
                                <!-- DATA TABLE -->
                                <h3 class="title-5 m-b-35">Budgets</h3>
                                <div class="table-data__tool">
                                    <div class="table-data__tool-right">
                                        <button class="au-btn au-btn-icon au-btn--green au-btn--small">
                                            <i class="zmdi zmdi-plus"></i><a style="color:white;" href="creation?uuid=${uuid}&month=${month}">Ajouter</a></button>
                                    </div>
                                </div>
                                <div class="table-responsive table-responsive-data2">
                                    <c:choose>
                                        <c:when test="${not empty budgets }">
                                        	<c:forEach items="${budgets}" var="budget">
	                                        	<div class="col-lg-12">
					                                <div class="au-card chart-percent-card">
					                                    <div class="au-card-inner">
					                                        <h3 class="title-2 tm-b-5">${budget.description}</h3>
					                                        <div class="row no-gutters">
					                                            <div class="col-xl-6">
					                                                <div class="chart-note-wrap">
					                                                    <div class="chart-note mr-0 d-block">
					                                                        <span class="dot dot--blue"></span>
					                                                        <span>Budget utilisé</span>
					                                                    </div>
					                                                    <div class="chart-note mr-0 d-block">
					                                                        <span class="dot dot--red"></span>
					                                                        <span>Dépassement du budget</span>
					                                                    </div>
					                                                </div>
					                                            </div>
					                                            
					                                            <div class="${budget.percentage > 100 ? 'c100plus' : 'c100'} p${budget.percentage > 100 ? Math.round(budget.percentage%100) : budget.percentage}">
												                    <span>${budget.percentage}%</span>
												                    <div class="slice">
												                        <div class="bar"></div>
												                        <div class="fill"></div>
												                    </div>
												                </div>
					                                            
<!-- 					                                            <div class="col-xl-6"> -->
<!-- 					                                                <div class="percent-chart"> -->
<%-- 					                                                    <canvas class="percent-chart-budget" data-balance="${budget.budgetBalance }" data-taken="${budget.budgetTaken}" data-total="${budget.budgetTotal }"></canvas> --%>
<!-- 					                                                </div> -->
<!-- 					                                            </div> -->
					                                        </div>
					                                    </div>
					                                </div>
					                            </div>
					                        </c:forEach>
<!-- 		                                    <table class="table table-data2"> -->
<!-- 		                                        <thead> -->
<!-- 		                                            <tr> -->
<!-- 		                                                <th>description</th> -->
<!-- 		                                                <th>catégorie</th> -->
<!-- 		                                                <th>budget</th> -->
<!-- 		                                                <th></th> -->
<!-- 		                                            </tr> -->
<!-- 		                                        </thead> -->
<!-- 		                                        <tbody> -->
<%-- 		                                            <c:forEach items="${budgets}" var="budget"> --%>
<%-- 			                                            <tr id="${budget.uuid }" class="tr-shadow"> --%>
<%-- 			                                                <td><c:out value="${budget.description}"/></td> --%>
<%-- 			                                                <td><c:out value="${budget.categoryTask.description}"/></td> --%>
<%-- 			                                                <td><c:out value="${budget.budgetTotal}€"/></td> --%>
<!-- 			                                                <td style="vertical-align:top !important;"> -->
<!-- 			                                                    <div class="table-data-feature"> -->
<%-- 			                                                        <button class="item" data-toggle="tooltip" data-placement="top" title="Edit" onclick="location.href='objective/update?uuid=${objective.uuid }';"> --%>
<!-- 			                                                            <i class="zmdi zmdi-edit"></i> -->
<!-- 			                                                        </button> -->
<%-- 			                                                        <button class="item" data-toggle="tooltip" data-placement="top" title="Delete" onclick="location.href='objective/delete?uuid=${objective.uuid }';"> --%>
<!-- 			                                                            <i class="zmdi zmdi-delete"></i> -->
<!-- 			                                                        </button> -->
<!-- 			                                                    </div> -->
<!-- 			                                                </td> -->
<!-- 			                                            </tr> -->
<!-- 		                                            <tr class="spacer"></tr> -->
<%-- 		                                            </c:forEach> --%>
<!-- 		                                        </tbody> -->
<!-- 		                                    </table> -->
                                     	</c:when>
                                        <c:otherwise>
                                        	<span><c:out value="Aucun budgets"/></span>
                                        </c:otherwise>
                                   </c:choose>
                                </div>
                                <!-- END DATA TABLE -->
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
	<script type="text/javascript">

	function updateState(objective){
		var uuid = $(objective).closest("tr").attr("id");
		var state = $(objective).is(':checked') ? "DONE" : "TO_DO"; 
		var urlAjax = 'objective/updateState?uuid=' + uuid;
		var dataAjax = {'state' : state};
		
		$.ajax({
			headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
		    },
	    	url: urlAjax,
	   		type: 'POST',
	   		contentType: 'application/json',
	   		dataType: "json",
	   		data: JSON.stringify(dataAjax),
	   		success: function(data, status, jqXHR) {
	  	    },
			error: function(jqXHR, status, errorThrown) {
			}
		});
	}

	$(".tr-shadow").click(function(e){
		window.location.href = "show?uuid=" + $(this).attr("id");
	});

	</script>

</body>

</html>
<!-- end document-->
