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
        
			<jsp:include page="menu.jsp"/>

            <!-- MAIN CONTENT-->
            <div class="main-content">
                <div class="section__content section__content--p30">
                    <div class="container-fluid">
                            <c:forEach items="${trackers}" var="tracker">
	                            <div class="col-lg-3">
	                                <div class="au-card m-b-30">
	                                    <div class="au-card-inner">
	                                        <h3 class="title-2 m-b-40">${tracker.name}</h3>
	                                        <canvas id="pieChart"></canvas>
	                                    </div>
	                                </div>
	                            </div>
                            </c:forEach>
                            <div class="col-lg-12">
                                <div class="au-card au-card--no-shadow au-card--no-pad m-b-40">
                                    <div class="au-card-title" style="background-image:url('<%=request.getContextPath()%>/resources/images/bg-title-01.jpg');">
                                        <div class="bg-overlay bg-overlay--blue"></div>
                                        <h3>
                                            <i class="zmdi zmdi-account-calendar"></i>${today}</h3>
                                        <button class="au-btn-plus" onclick="location.href='tasks/daily/new">
                                            <i class="zmdi zmdi-plus"></i>
                                        </button>
                                    </div>
                                    <div class="au-task js-list-load">
                                        <div class="au-task__title">
<!--                                             <p>Tasks for John Doe</p> -->
                                        </div>
                                        <div class="au-task-list js-scrollbar3">
                                        	<c:forEach items="${dailyTasks}" var="task">
	                                        	<div class="au-task__item au-task__item--danger">
	                                                <div class="au-task__item-inner">
	                                                    <h5 class="task">
	                                                        <a href="tasks/daily/show?uuid=${task.uuid }">${task.tracker.name}</a>
	                                                    </h5>
<%-- 	                                                    <span class="${task}">10:00 AM</span> --%>
	                                                </div>
	                                            </div>
                                        	</c:forEach>
                                            
                                        </div>
<!--                                         <div class="au-task__footer"> -->
<!--                                             <button class="au-btn au-btn-load js-load-btn">load more</button> -->
<!--                                         </div> -->
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

	<script type="text/javascript">

	var trackers = {<c:forEach items="${trackers}" var="tracker" varStatus="trackerStatus">
		'${tracker.uuid}':
		{
			data:['${mapTrackerData[tracker.uuid]}'],
			colors:['${mapTrackerColors[tracker.uuid]}'],
			labels:['${mapTrackerLabels[tracker.uuid]}']
		}
	<c:if test="${not trackerStatus.last}">,</c:if></c:forEach>};

	var ctx = document.getElementById("pieChart");
    if (ctx) {
      ctx.height = 200;
      var myChart = new Chart(ctx, {
        type: 'pie',
        data: {
          datasets: [{
            data: trackers[].data,
            backgroundColor: trackers[].colors,
            hoverBackgroundColor: [
              "rgba(0, 123, 255,0.9)",
              "rgba(0, 123, 255,0.7)",
              "rgba(0, 123, 255,0.5)",
              "rgba(0,0,0,0.07)"
            ]

          }],
          labels: trackers[].labels
        },
        options: {
          legend: {
            position: 'top',
            labels: {
              fontFamily: 'Poppins'
            }

          },
          responsive: true
        }
      });
	
	</script>

</body>

</html>
<!-- end document-->
