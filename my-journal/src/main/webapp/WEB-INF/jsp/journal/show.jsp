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
        
			<jsp:include page="../menu.jsp"/>

            <!-- MAIN CONTENT-->
            <div class="main-content">
                <div class="section__content section__content--p30">
                    <div class="container-fluid">
<!--                         <div class="row"> -->
<!--                             <div class="col-md-12"> -->
<!--                                 <div class="overview-wrap"> -->
<!--                                     <h2 class="title-1">overview</h2> -->
<!--                                     <button class="au-btn au-btn-icon au-btn--blue"> -->
<!--                                         <i class="zmdi zmdi-plus"></i>add item</button> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                         <div class="row m-t-25"> -->
<!--                             <div class="col-sm-6 col-lg-3"> -->
<!--                                 <div class="overview-item overview-item--c1"> -->
<!--                                     <div class="overview__inner"> -->
<!--                                         <div class="overview-box clearfix"> -->
<!--                                             <div class="icon"> -->
<!--                                                 <i class="zmdi zmdi-account-o"></i> -->
<!--                                             </div> -->
<!--                                             <div class="text"> -->
<!--                                                 <h2>10368</h2> -->
<!--                                                 <span>members online</span> -->
<!--                                             </div> -->
<!--                                         </div> -->
<!--                                         <div class="overview-chart"> -->
<!--                                             <canvas id="widgetChart1"></canvas> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                             <div class="col-sm-6 col-lg-3"> -->
<!--                                 <div class="overview-item overview-item--c2"> -->
<!--                                     <div class="overview__inner"> -->
<!--                                         <div class="overview-box clearfix"> -->
<!--                                             <div class="icon"> -->
<!--                                                 <i class="zmdi zmdi-shopping-cart"></i> -->
<!--                                             </div> -->
<!--                                             <div class="text"> -->
<!--                                                 <h2>388,688</h2> -->
<!--                                                 <span>items solid</span> -->
<!--                                             </div> -->
<!--                                         </div> -->
<!--                                         <div class="overview-chart"> -->
<!--                                             <canvas id="widgetChart2"></canvas> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                             <div class="col-sm-6 col-lg-3"> -->
<!--                                 <div class="overview-item overview-item--c3"> -->
<!--                                     <div class="overview__inner"> -->
<!--                                         <div class="overview-box clearfix"> -->
<!--                                             <div class="icon"> -->
<!--                                                 <i class="zmdi zmdi-calendar-note"></i> -->
<!--                                             </div> -->
<!--                                             <div class="text"> -->
<!--                                                 <h2>1,086</h2> -->
<!--                                                 <span>this week</span> -->
<!--                                             </div> -->
<!--                                         </div> -->
<!--                                         <div class="overview-chart"> -->
<!--                                             <canvas id="widgetChart3"></canvas> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                             <div class="col-sm-6 col-lg-3"> -->
<!--                                 <div class="overview-item overview-item--c4"> -->
<!--                                     <div class="overview__inner"> -->
<!--                                         <div class="overview-box clearfix"> -->
<!--                                             <div class="icon"> -->
<!--                                                 <i class="zmdi zmdi-money"></i> -->
<!--                                             </div> -->
<!--                                             <div class="text"> -->
<!--                                                 <h2>$1,060,386</h2> -->
<!--                                                 <span>total earnings</span> -->
<!--                                             </div> -->
<!--                                         </div> -->
<!--                                         <div class="overview-chart"> -->
<!--                                             <canvas id="widgetChart4"></canvas> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                         <div class="row"> -->
<!--                             <div class="col-lg-6"> -->
<!--                                 <div class="au-card recent-report"> -->
<!--                                     <div class="au-card-inner"> -->
<!--                                         <h3 class="title-2">recent reports</h3> -->
<!--                                         <div class="chart-info"> -->
<!--                                             <div class="chart-info__left"> -->
<!--                                                 <div class="chart-note"> -->
<!--                                                     <span class="dot dot--blue"></span> -->
<!--                                                     <span>products</span> -->
<!--                                                 </div> -->
<!--                                                 <div class="chart-note mr-0"> -->
<!--                                                     <span class="dot dot--green"></span> -->
<!--                                                     <span>services</span> -->
<!--                                                 </div> -->
<!--                                             </div> -->
<!--                                             <div class="chart-info__right"> -->
<!--                                                 <div class="chart-statis"> -->
<!--                                                     <span class="index incre"> -->
<!--                                                         <i class="zmdi zmdi-long-arrow-up"></i>25%</span> -->
<!--                                                     <span class="label">products</span> -->
<!--                                                 </div> -->
<!--                                                 <div class="chart-statis mr-0"> -->
<!--                                                     <span class="index decre"> -->
<!--                                                         <i class="zmdi zmdi-long-arrow-down"></i>10%</span> -->
<!--                                                     <span class="label">services</span> -->
<!--                                                 </div> -->
<!--                                             </div> -->
<!--                                         </div> -->
<!--                                         <div class="recent-report__chart"> -->
<!--                                             <canvas id="recent-rep-chart"></canvas> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                             <div class="col-lg-6"> -->
<!--                                 <div class="au-card chart-percent-card"> -->
<!--                                     <div class="au-card-inner"> -->
<!--                                         <h3 class="title-2 tm-b-5">char by %</h3> -->
<!--                                         <div class="row no-gutters"> -->
<!--                                             <div class="col-xl-6"> -->
<!--                                                 <div class="chart-note-wrap"> -->
<!--                                                     <div class="chart-note mr-0 d-block"> -->
<!--                                                         <span class="dot dot--blue"></span> -->
<!--                                                         <span>products</span> -->
<!--                                                     </div> -->
<!--                                                     <div class="chart-note mr-0 d-block"> -->
<!--                                                         <span class="dot dot--red"></span> -->
<!--                                                         <span>services</span> -->
<!--                                                     </div> -->
<!--                                                 </div> -->
<!--                                             </div> -->
<!--                                             <div class="col-xl-6"> -->
<!--                                                 <div class="percent-chart"> -->
<!--                                                     <canvas id="percent-chart"></canvas> -->
<!--                                                 </div> -->
<!--                                             </div> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </div> -->
                        <div class="row">
                            <div class="col-lg-9">
                                <h2 class="title-1 m-b-25">Journal du <tags:localDate date="${journal.beginDate}"/> au <tags:localDate date="${journal.endDate}"/></h2>
                                <div class="table-responsive table--no-card m-b-40">
                                    <table class="table table-borderless table-striped table-earning">
                                        <thead>
                                            <tr>
                                                <th>date</th>
                                                <th class="text-right">tâches effectuées</th>
                                                <th class="text-right">tâches completées</th>
                                                <th class="text-right">budget</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>2018-09-29 05:57</td>
                                                <td class="text-right">45</td>
                                                <td class="text-right">45/50 (90%)</td>
                                                <td class="text-right">225/250€ (90%)</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
<!--                             <div class="col-lg-3"> -->
<!--                                 <h2 class="title-1 m-b-25">Top countries</h2> -->
<!--                                 <div class="au-card au-card--bg-blue au-card-top-countries m-b-40"> -->
<!--                                     <div class="au-card-inner"> -->
<!--                                         <div class="table-responsive"> -->
<!--                                             <table class="table table-top-countries"> -->
<!--                                                 <tbody> -->
<!--                                                     <tr> -->
<!--                                                         <td>United States</td> -->
<!--                                                         <td class="text-right">$119,366.96</td> -->
<!--                                                     </tr> -->
<!--                                                     <tr> -->
<!--                                                         <td>Australia</td> -->
<!--                                                         <td class="text-right">$70,261.65</td> -->
<!--                                                     </tr> -->
<!--                                                     <tr> -->
<!--                                                         <td>United Kingdom</td> -->
<!--                                                         <td class="text-right">$46,399.22</td> -->
<!--                                                     </tr> -->
<!--                                                     <tr> -->
<!--                                                         <td>Turkey</td> -->
<!--                                                         <td class="text-right">$35,364.90</td> -->
<!--                                                     </tr> -->
<!--                                                     <tr> -->
<!--                                                         <td>Germany</td> -->
<!--                                                         <td class="text-right">$20,366.96</td> -->
<!--                                                     </tr> -->
<!--                                                     <tr> -->
<!--                                                         <td>France</td> -->
<!--                                                         <td class="text-right">$10,366.96</td> -->
<!--                                                     </tr> -->
<!--                                                     <tr> -->
<!--                                                         <td>Australia</td> -->
<!--                                                         <td class="text-right">$5,366.96</td> -->
<!--                                                     </tr> -->
<!--                                                     <tr> -->
<!--                                                         <td>Italy</td> -->
<!--                                                         <td class="text-right">$1639.32</td> -->
<!--                                                     </tr> -->
<!--                                                 </tbody> -->
<!--                                             </table> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                         <div class="row"> -->
<!--                             <div class="col-lg-6"> -->
<!--                                 <div class="au-card au-card--no-shadow au-card--no-pad m-b-40"> -->
<%--                                     <div class="au-card-title" style="background-image:url('<%=request.getContextPath()%>/resources/images/bg-title-01.jpg');"> --%>
<!--                                         <div class="bg-overlay bg-overlay--blue"></div> -->
<!--                                         <h3> -->
<%--                                             <i class="zmdi zmdi-account-calendar"></i>${today}</h3> --%>
<!--                                         <button class="au-btn-plus" onclick="location.href='tasks/daily/new"> -->
<!--                                             <i class="zmdi zmdi-plus"></i> -->
<!--                                         </button> -->
<!--                                     </div> -->
<!--                                     <div class="au-task js-list-load"> -->
<!--                                         <div class="au-task__title"> -->
<!-- <!--                                             <p>Tasks for John Doe</p> --> -->
<!--                                         </div> -->
<!--                                         <div class="au-task-list js-scrollbar3"> -->
<%--                                         	<c:forEach items="${dailyTasks}" var="task"> --%>
<!-- 	                                        	<div class="au-task__item au-task__item--danger"> -->
<!-- 	                                                <div class="au-task__item-inner"> -->
<!-- 	                                                    <h5 class="task"> -->
<%-- 	                                                        <a href="tasks/daily/show?uuid=${task.uuid }">${task.description}</a> --%>
<!-- 	                                                    </h5> -->
<%-- <%-- 	                                                    <span class="${task}">10:00 AM</span> --%> 
<!-- 	                                                </div> -->
<!-- 	                                            </div> -->
<%--                                         	</c:forEach> --%>
                                            
<!--                                         </div> -->
<!-- <!--                                         <div class="au-task__footer"> --> -->
<!-- <!--                                             <button class="au-btn au-btn-load js-load-btn">load more</button> --> -->
<!-- <!--                                         </div> --> -->
<!--                                     </div> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                             <div class="col-lg-6"> -->
<!--                                 <div class="au-card au-card--no-shadow au-card--no-pad m-b-40"> -->
<%--                                     <div class="au-card-title" style="background-image:url('<%=request.getContextPath()%>/resources/images/bg-title-02.jpg');"> --%>
<!--                                         <div class="bg-overlay bg-overlay--blue"></div> -->
<!--                                         <h3> -->
<!--                                             <i class="zmdi zmdi-comment-text"></i>New Messages</h3> -->
<!--                                         <button class="au-btn-plus"> -->
<!--                                             <i class="zmdi zmdi-plus"></i> -->
<!--                                         </button> -->
<!--                                     </div> -->
<!--                                     <div class="au-inbox-wrap js-inbox-wrap"> -->
<!--                                         <div class="au-message js-list-load"> -->
<!--                                             <div class="au-message__noti"> -->
<!--                                                 <p>You Have -->
<!--                                                     <span>2</span> -->

<!--                                                     new messages -->
<!--                                                 </p> -->
<!--                                             </div> -->
<!--                                             <div class="au-message-list"> -->
<!--                                                 <div class="au-message__item unread"> -->
<!--                                                     <div class="au-message__item-inner"> -->
<!--                                                         <div class="au-message__item-text"> -->
<!--                                                             <div class="avatar-wrap"> -->
<!--                                                                 <div class="avatar"> -->
<%--                                                                     <img src="<%=request.getContextPath()%>/resources/images/icon/avatar-02.jpg" alt="John Smith"> --%>
<!--                                                                 </div> -->
<!--                                                             </div> -->
<!--                                                             <div class="text"> -->
<!--                                                                 <h5 class="name">John Smith</h5> -->
<!--                                                                 <p>Have sent a photo</p> -->
<!--                                                             </div> -->
<!--                                                         </div> -->
<!--                                                         <div class="au-message__item-time"> -->
<!--                                                             <span>12 Min ago</span> -->
<!--                                                         </div> -->
<!--                                                     </div> -->
<!--                                                 </div> -->
<!--                                                 <div class="au-message__item unread"> -->
<!--                                                     <div class="au-message__item-inner"> -->
<!--                                                         <div class="au-message__item-text"> -->
<!--                                                             <div class="avatar-wrap online"> -->
<!--                                                                 <div class="avatar"> -->
<%--                                                                     <img src="<%=request.getContextPath()%>/resources/images/icon/avatar-03.jpg" alt="Nicholas Martinez"> --%>
<!--                                                                 </div> -->
<!--                                                             </div> -->
<!--                                                             <div class="text"> -->
<!--                                                                 <h5 class="name">Nicholas Martinez</h5> -->
<!--                                                                 <p>You are now connected on message</p> -->
<!--                                                             </div> -->
<!--                                                         </div> -->
<!--                                                         <div class="au-message__item-time"> -->
<!--                                                             <span>11:00 PM</span> -->
<!--                                                         </div> -->
<!--                                                     </div> -->
<!--                                                 </div> -->
<!--                                                 <div class="au-message__item"> -->
<!--                                                     <div class="au-message__item-inner"> -->
<!--                                                         <div class="au-message__item-text"> -->
<!--                                                             <div class="avatar-wrap online"> -->
<!--                                                                 <div class="avatar"> -->
<%--                                                                     <img src="<%=request.getContextPath()%>/resources/images/icon/avatar-04.jpg" alt="Michelle Sims"> --%>
<!--                                                                 </div> -->
<!--                                                             </div> -->
<!--                                                             <div class="text"> -->
<!--                                                                 <h5 class="name">Michelle Sims</h5> -->
<!--                                                                 <p>Lorem ipsum dolor sit amet</p> -->
<!--                                                             </div> -->
<!--                                                         </div> -->
<!--                                                         <div class="au-message__item-time"> -->
<!--                                                             <span>Yesterday</span> -->
<!--                                                         </div> -->
<!--                                                     </div> -->
<!--                                                 </div> -->
<!--                                                 <div class="au-message__item"> -->
<!--                                                     <div class="au-message__item-inner"> -->
<!--                                                         <div class="au-message__item-text"> -->
<!--                                                             <div class="avatar-wrap"> -->
<!--                                                                 <div class="avatar"> -->
<%--                                                                     <img src="<%=request.getContextPath()%>/resources/images/icon/avatar-05.jpg" alt="Michelle Sims"> --%>
<!--                                                                 </div> -->
<!--                                                             </div> -->
<!--                                                             <div class="text"> -->
<!--                                                                 <h5 class="name">Michelle Sims</h5> -->
<!--                                                                 <p>Purus feugiat finibus</p> -->
<!--                                                             </div> -->
<!--                                                         </div> -->
<!--                                                         <div class="au-message__item-time"> -->
<!--                                                             <span>Sunday</span> -->
<!--                                                         </div> -->
<!--                                                     </div> -->
<!--                                                 </div> -->
<!--                                                 <div class="au-message__item js-load-item"> -->
<!--                                                     <div class="au-message__item-inner"> -->
<!--                                                         <div class="au-message__item-text"> -->
<!--                                                             <div class="avatar-wrap online"> -->
<!--                                                                 <div class="avatar"> -->
<%--                                                                     <img src="<%=request.getContextPath()%>/resources/images/icon/avatar-04.jpg" alt="Michelle Sims"> --%>
<!--                                                                 </div> -->
<!--                                                             </div> -->
<!--                                                             <div class="text"> -->
<!--                                                                 <h5 class="name">Michelle Sims</h5> -->
<!--                                                                 <p>Lorem ipsum dolor sit amet</p> -->
<!--                                                             </div> -->
<!--                                                         </div> -->
<!--                                                         <div class="au-message__item-time"> -->
<!--                                                             <span>Yesterday</span> -->
<!--                                                         </div> -->
<!--                                                     </div> -->
<!--                                                 </div> -->
<!--                                                 <div class="au-message__item js-load-item"> -->
<!--                                                     <div class="au-message__item-inner"> -->
<!--                                                         <div class="au-message__item-text"> -->
<!--                                                             <div class="avatar-wrap"> -->
<!--                                                                 <div class="avatar"> -->
<%--                                                                     <img src="<%=request.getContextPath()%>/resources/images/icon/avatar-05.jpg" alt="Michelle Sims"> --%>
<!--                                                                 </div> -->
<!--                                                             </div> -->
<!--                                                             <div class="text"> -->
<!--                                                                 <h5 class="name">Michelle Sims</h5> -->
<!--                                                                 <p>Purus feugiat finibus</p> -->
<!--                                                             </div> -->
<!--                                                         </div> -->
<!--                                                         <div class="au-message__item-time"> -->
<!--                                                             <span>Sunday</span> -->
<!--                                                         </div> -->
<!--                                                     </div> -->
<!--                                                 </div> -->
<!--                                             </div> -->
<!--                                             <div class="au-message__footer"> -->
<!--                                                 <button class="au-btn au-btn-load js-load-btn">load more</button> -->
<!--                                             </div> -->
<!--                                         </div> -->
<!--                                         <div class="au-chat"> -->
<!--                                             <div class="au-chat__title"> -->
<!--                                                 <div class="au-chat-info"> -->
<!--                                                     <div class="avatar-wrap online"> -->
<!--                                                         <div class="avatar avatar--small"> -->
<%--                                                             <img src="<%=request.getContextPath()%>/resources/images/icon/avatar-02.jpg" alt="John Smith"> --%>
<!--                                                         </div> -->
<!--                                                     </div> -->
<!--                                                     <span class="nick"> -->
<!--                                                         <a href="#">John Smith</a> -->
<!--                                                     </span> -->
<!--                                                 </div> -->
<!--                                             </div> -->
<!--                                             <div class="au-chat__content"> -->
<!--                                                 <div class="recei-mess-wrap"> -->
<!--                                                     <span class="mess-time">12 Min ago</span> -->
<!--                                                     <div class="recei-mess__inner"> -->
<!--                                                         <div class="avatar avatar--tiny"> -->
<%--                                                             <img src="<%=request.getContextPath()%>/resources/images/icon/avatar-02.jpg" alt="John Smith"> --%>
<!--                                                         </div> -->
<!--                                                         <div class="recei-mess-list"> -->
<!--                                                             <div class="recei-mess">Lorem ipsum dolor sit amet, consectetur adipiscing elit non iaculis</div> -->
<!--                                                             <div class="recei-mess">Donec tempor, sapien ac viverra</div> -->
<!--                                                         </div> -->
<!--                                                     </div> -->
<!--                                                 </div> -->
<!--                                                 <div class="send-mess-wrap"> -->
<!--                                                     <span class="mess-time">30 Sec ago</span> -->
<!--                                                     <div class="send-mess__inner"> -->
<!--                                                         <div class="send-mess-list"> -->
<!--                                                             <div class="send-mess">Lorem ipsum dolor sit amet, consectetur adipiscing elit non iaculis</div> -->
<!--                                                         </div> -->
<!--                                                     </div> -->
<!--                                                 </div> -->
<!--                                             </div> -->
<!--                                             <div class="au-chat-textfield"> -->
<!--                                                 <form class="au-form-icon"> -->
<!--                                                     <input class="au-input au-input--full au-input--h65" type="text" placeholder="Type a message"> -->
<!--                                                     <button class="au-input-icon"> -->
<!--                                                         <i class="zmdi zmdi-camera"></i> -->
<!--                                                     </button> -->
<!--                                                 </form> -->
<!--                                             </div> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </div> -->
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
