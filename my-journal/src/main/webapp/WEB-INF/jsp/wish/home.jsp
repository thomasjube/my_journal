<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="au theme template">
    <meta name="author" content="Hau Nguyen">
    <meta name="keywords" content="au theme template">
    <title>Liste d'envies</title>
    <link href="<%=request.getContextPath()%>/resources/css/font-face.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/resources/vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/resources/vendor/font-awesome-5/css/fontawesome-all.min.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/resources/vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/resources/vendor/bootstrap-4.1/bootstrap.min.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/resources/vendor/animsition/animsition.min.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/resources/vendor/bootstrap-progressbar/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/resources/vendor/wow/animate.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/resources/vendor/css-hamburgers/hamburgers.min.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/resources/vendor/slick/slick.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/resources/vendor/select2/select2.min.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/resources/vendor/perfect-scrollbar/perfect-scrollbar.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/resources/css/theme.css" rel="stylesheet" media="all">

</head>

<body class="animsition">
    <div class="page-wrapper">
        <jsp:include page="../menu.jsp"/>
            <div class="main-content">
                <div class="section__content section__content--p30">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-12">
                                <!-- DATA TABLE -->
                                <h2 class="title-5 m-b-35">Vos listes d'envies</h2>
                                <div class="table-responsive table-responsive-data2">
                                    <c:choose>
										<c:when test="${empty wishLists}">Aucune liste</c:when>
										<c:otherwise>
		                                	<table class="table table-data2" id="tableList">
				                            	<thead>
				                                	<tr>
				                                		<th>Nom</th>
				                                		<th>Nombre d'envie</th>
				                                		<th>Prix</th>
				                                		<th></th>
													</tr>
												</thead>
												<tbody>
			                                    	<c:forEach items="${wishLists}" var="wishList">
														<tr class="tr-shadow" id="${wishList.uuid }">
					                                    	<td>${wishList.description }</td>
					                                        <td>${wishList.wishes.size() }</td>
					                                        <td>${wishList.price } €</td>
															<td>
					                                        	<div class="table-data-feature">
					                                            	<button class="item" data-toggle="tooltip" data-placement="top" title="Editer" onclick="location.href='list/edit?uuid=${wishList.uuid }';">
																	<i class="zmdi zmdi-edit"></i></button>
					                                                <button class="item" data-toggle="tooltip" data-placement="top" title="Supprimer" onclick="location.href='list/remove?uuid=${wishList.uuid }';">
					                                                 <i class="zmdi zmdi-delete"></i>
					                                                </button>
																</div>
															</td>
														</tr>
														<tr class="spacer"></tr>
													</c:forEach>
		                                        </tbody>
		                                    </table>
                                       	</c:otherwise>
                                  	</c:choose>
                                </div>
                            	<div class="table-data__tool">
                                    <div class="table-data__tool-right">
										<a style="color:white;" href="list/add"><button class="au-btn au-btn-icon au-btn--green au-btn--small"><i class="zmdi zmdi-plus"></i>Ajouter</button></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="copyright">
                                    <p>Copyright © 2018 Thomas' project.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    <script src="<%=request.getContextPath()%>/resources/vendor/jquery-3.2.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/vendor/bootstrap-4.1/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/vendor/bootstrap-4.1/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/vendor/wow/wow.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/vendor/animsition/animsition.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/vendor/counter-up/jquery.waypoints.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/vendor/circle-progress/circle-progress.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/vendor/perfect-scrollbar/perfect-scrollbar.js"></script>
    <script src="<%=request.getContextPath()%>/resources/vendor/chartjs/Chart.bundle.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/main.js"></script>
    
    <script type="text/javascript">
    	$("table#tableList tbody tr").click(function(e){
    		e.preventDefault();
    		var uuid = $(this).attr('id');
    		if(typeof uuid !='undefined' && uuid != "")
    			window.location.href = 'list/show?uuid=' + uuid;
    	});
    </script>
</body>
</html>