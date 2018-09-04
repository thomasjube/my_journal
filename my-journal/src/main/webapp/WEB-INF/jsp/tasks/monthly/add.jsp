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
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="au theme template">
    <meta name="author" content="Hau Nguyen">
    <meta name="keywords" content="au theme template">

    <title>Ajout d'une envie</title>
    <link href="<%=request.getContextPath()%>/resources/css/font-face.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/resources/vendor/font-awesome-5/css/fontawesome-all.min.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/resources/vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
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
		<jsp:include page="../../menu.jsp"/>
			<div class="main-content">
    	    	<div class="section__content section__content--p30">
        	    	<div class="container-fluid">
            	    	<div class="row">
                	    	 <div class="col-md-12">
                    	    	<div class="card">
                        	    	<div class="card-header">
                        	    		<c:choose>
                        	    			<c:when test="${not empty monthlyTask}"><strong>Edition d'une tâche mensuelle</strong></c:when>
                        	    			<c:otherwise><strong>Ajout d'une tâche mensuelle</strong></c:otherwise>
                        	    		</c:choose>
									</div>
                                    <div class="card-body card-block">
                                        <form action="" method="post" name="monthlyTaskAddForm">
                                        	<input type="hidden" name="uuid" value="${monthlyTaskAddForm.uuid }"/>
			                                <input type="hidden" name="journalUuid" value="${monthlyTaskAddForm.journalUuid }"/>
			                                <input type="hidden" name="month" value="${monthlyTaskAddForm.month }"/>
			                                <div class="form-group">
			                                    <label>Nom</label>
			                                    <input class="au-input au-input--full" type="text" name="description" required="required" value="${monthlyTaskAddForm.description }">
			                                </div>
			                                <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="select" class=" form-control-label">Catégorie lié</label>
                                                </div>
                                                <div class="col-12 col-md-9">
                                                    <select name="categoryTaskUuid" class="form-control">
                                                    <option value="">Please select</option>
                                                    <c:forEach items="${categoryTasks}" var="category">
														<option value="${category.uuid}">${category.description}</option>                                                    	
                                                    </c:forEach>
                                                    </select>
                                                </div>
                                             </div>
			                                <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="select" class=" form-control-label">Objectif lié</label>
                                                </div>
                                                <div class="col-12 col-md-9">
                                                    <select name="objectiveUuid" class="form-control">
                                                    <option value="">Please select</option>
                                                    <c:forEach items="${objectives}" var="objective">
														<option value="${objective.uuid}">${objective.name}</option>                                                    	
                                                    </c:forEach>
                                                    </select>
                                                </div>
                                             </div>
                                              <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="select" class=" form-control-label">Envie lié</label>
                                                </div>
                                                <div class="col-12 col-md-9">
                                                    <select name="wishUuid" class="form-control">
                                                    <option value="">Please select</option>
                                                    <c:forEach items="${wishLists}" var="wishList">
														<optgroup label="${wishList.description }">
															<c:forEach items="${wishList.wishes}" var="wish">
																<option value="${wish.uuid}">${wish.description}</option>
															</c:forEach>   
														</optgroup>                                                 	
                                                    </c:forEach>
                                                    </select>
                                                </div>
                                             </div>
<!-- 			                                <div class="form-group"> -->
<!-- 			                                    <div class="col col-md-3"> -->
<!--                                                     <label for="select" class=" form-control-label">Unité</label> -->
<!--                                                 </div> -->
<!--                                                 <div class="col-12 col-md-9"> -->
<!--                                                     <select name="masterObjectiveUuid" class="form-control"> -->
<!--                                                     <option value="">Please select</option> -->
<%--                                                     <c:forEach items="${unitValues}" var="unit"> --%>
<%-- 														<option value="${unit}"><fmt:message key="label.${unit}.taskUnit" bundle="${lang }"/></option>                                                    	 --%>
<%--                                                     </c:forEach> --%>
<!--                                                     </select> -->
<!--                                                 </div> -->
<!-- 			                                </div> -->
<!-- 			                                <div class="form-group"> -->
<!-- 			                                    <label>Valeur</label> -->
<%-- 			                                    <input class="au-input au-input--full" type="text" name="price" required="required" value="${monthlyTaskAddForm.value }"> --%>
<!-- 			                                </div> -->
	                                    <div class="card-footer">
	                                        <button type="submit" class="btn btn-primary btn-sm"><i class="fa fa-dot-circle-o"></i>
	                                        <c:choose>
		                                        <c:when test="${not empty monthlyTask }"><strong>Mettre à jour</strong></c:when>
	                        	    			<c:otherwise><strong>Enregistrer</strong></c:otherwise>
	                        	    		</c:choose>
	                                        </button>
	                                    </div>
	                           		</form>
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
       </div>

    <script src="<%=request.getContextPath()%>/resources/vendor/jquery-3.2.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/vendor/bootstrap-4.1/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/vendor/bootstrap-4.1/bootstrap.min.js"></script>
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
    <script src="<%=request.getContextPath()%>/resources/js/main.js"></script>

</body>
</html>