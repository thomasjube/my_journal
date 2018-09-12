<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="au theme template">
    <meta name="author" content="Hau Nguyen">
    <meta name="keywords" content="au theme template">

    <!-- Title Page-->
    <title>Forms</title>

    <!-- Fontfaces CSS-->
    <link href="<%=request.getContextPath()%>/resources/css/font-face.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/resources/vendor/font-awesome-5/css/fontawesome-all.min.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/resources/vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
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
                            <div class="col-lg-6">
                                <div class="card">
                                    <div class="card-header">
                                        <strong>Nouveau Tracker</strong>
                                    </div>
                                    <div class="card-body card-block">
                                        <form action="" method="post">
			                                <input type="hidden" name="month" value="${form.month }">
			                                <input type="hidden" name="journalUuid" value="${form.journalUuid }">
			                                <div class="form-group">
			                                    <label>Nom</label>
			                                    <input class="au-input au-input--full" type="text" name="name" id="nameInput">
			                                </div>
<!-- 			                                ajouter ici pour ajouter des states -->
											<div class="table-responsive table--no-card m-b-40">
												<table class="table table-borderless table-striped table-earning">
								                 	<thead>
									                 	<th>Etat</th>
									                 	<th>Couleur</th>
									                 	<th><button type="button" class="au-btn-plus-tracker" id="add-state" ><i class="zmdi zmdi-plus"></i></button></th>
								                 	</thead>
								                 	<tbody id="stateBody">
										                 	<c:forEach items="${tracker.states}" var="state" varStatus="status">
										                 		<tr id="tr-state-${status.index}">
											                 		<td class="state-name"><input type="text" name="stateName[${status.index}]" value="${state.name}"></td>
											                 		<td class="state-color"><input type="color" name="stateColor[${status.index}]" value="${state.color }"></td>
											                 		<td><input type="button" class="removeState table-button" value="Effacer"></td>
										                 		</tr>
										                 	</c:forEach>
									                 </tbody>
								                 </table>
											 </div>											
	                                    <div class="card-footer">
	                                        <button type="submit" class="btn btn-primary btn-sm">
	                                            <i class="fa fa-dot-circle-o"></i> Submit
	                                        </button>
	                                    </div>
	                           		</form>
                                </div>
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
	
	$("#add-state").click(function(e){

		var indexMax = $(".state-name").length;
		var index = indexMax-1;
		
		var tr = "<tr id='tr-state-"+indexMax+"'>";
		tr += "<td class='stateName'><input id='stateName"+indexMax+"' name='stateName["+indexMax+"]' class='state-name' type='text'></td>";
		tr += "<td class='stateColor'><input id='stateColor"+indexMax+"' name='stateColor["+indexMax+"]' class='state-color' type='color'></td>";
		tr += "<td><input type='button' class='removeState table-button' value='Effacer'></td>";
		
		if(indexMax == 0)
			$("#stateBody").append(tr);	
		else
			$("#tr-state-"+index).after(tr);
	});

	$(document).on('click','.removeState',function(e){
		$(this).parent().parent().remove();
	});
	
	</script>

</body>

</html>
<!-- end document-->
