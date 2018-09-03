<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">

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
		<jsp:include page="../../../menu.jsp"/>
			<div class="main-content">
    	    	<div class="section__content section__content--p30">
        	    	<div class="container-fluid">
            	    	<div class="row">
                	    	 <div class="col-md-12">
                    	    	<div class="card">
                        	    	<div class="card-header">
                        	    		<c:choose>
                        	    			<c:when test="${not empty wish}"><strong>Edition d'une envie</strong></c:when>
                        	    			<c:otherwise><strong>Ajout d'une envie</strong></c:otherwise>
                        	    		</c:choose>
									</div>
                                    <div class="card-body card-block">
                                        <form action="" method="post" name="wishWishForm">
                                        	<input type="hidden" name="uuid" value="${wishWishForm.uuid }"/>
                               				<div class="row form-group">
			                                    <div class="col col-md-3"><label class="form-control-label">Liste : ${wishList.description }</label></div>
			                                    <div class="col-12 col-md-9"><input type="hidden" name="wishListUuid" value="${wishWishForm.wishListUuid }"/></div>
			                                </div>
			                                <div class="row form-group">
			                                    <div class="col col-md-3"><label class="form-control-label">Nom</label></div>
			                                    <div class="col-12 col-md-9"><input  class="form-control" type="text" name="description" required="required" value="${wishWishForm.description }"></div>
			                                </div>
			                                <div class="row form-group">
			                                    <div class="col col-md-3"><label class="form-control-label">Catégorie</label></div>
			                                    <div class="col-12 col-md-9">
			                                    	<select name="categoryUuid" class="form-control">
				                                    	<option value ="">Sélectionnez une catégorie</option>
				                                    	<c:forEach items="${categories}" var="category">
	<%-- 			                                    		<c:choose> --%>
	<%-- 			                                    			<c:when test="${wishWishForm.categoryUuid == category.uuid }"> --%>
				                                    				<option ${wishWishForm.categoryUuid == category.uuid ? 'selected="selected"' : '' } selected="selected" value="${category.uuid}">${category.description}</option>
	<%-- 			                                    			</c:when> --%>
	<%-- 			                                    			<c:otherwise> --%>
	<%-- 			                                    				<option value="${category.uuid}">${category.uuid}</option> --%>
	<%-- 			                                    			</c:otherwise> --%>
	<%-- 			                                    		</c:choose> --%>
				                                    	</c:forEach>
				                                    </select>
			                                    </div>
			                                </div>
			                                <div class="row form-group">
			                                   <div class="col col-md-3"><label>Prix</label></div>
			                                   <div class="col-12 col-md-9"><input class="au-input au-input--full" type="text" name="price" required="required" value="${wishWishForm.price }"></div>
			                                </div>
	                                    <div class="card-footer">
	                                        <button type="submit" class="btn btn-primary btn-sm"><i class="fa fa-dot-circle-o"></i>
	                                        <c:choose>
		                                        <c:when test="${not empty wish }"><strong>Mettre à jour</strong></c:when>
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