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
    
    <!-- Calendar-->
    <link href="<%=request.getContextPath()%>/resources/calendar/calendar.css" rel="stylesheet" media="all">

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
    
    
    <!-- QTIP-->
    <link href="<%=request.getContextPath()%>/resources/qtip/jquery.qtip.min.css" rel="stylesheet" media="all">

</head>

<body class="animsition">
    <div class="page-wrapper">
        
			<jsp:include page="../menu.jsp"/>

            <!-- MAIN CONTENT-->
            <div class="main-content">
                <div class="section__content section__content--p30">
                    <div class="container-fluid">
                    	<div class="row">
                    		<div class="jzdbox1 jzdbasf jzdcal">
								<div class="jzdcalt">
									<c:if test="${not empty previousMonth }"><span onclick="onPreviousMonth(this)" style="cursor:pointer;height:21px;top:7px;position:relative;float:left;line-height:50%;">&lt;</span></c:if>
									<fmt:message key="label.${month}.month" bundle="${lang}"/> ${year}
									<c:if test="${not empty nextMonth}"><span onclick="onNextMonth(this)" style="cursor:pointer;height:22px;top:7px;position:relative;float:right;line-height:50%;">&gt;</span></c:if>
								</div>
								
									<span>Lun</span>
									<span>Mar</span>
									<span>Mer</span>
									<span>Jeu</span>
									<span>Ven</span>
									<span>Sam</span>
									<span>Dim</span>
									
									<c:set var="offsetBeginDate" value="${beginDate.dayOfWeek.value - 1}"/>
									<c:set var="offsetEndDate" value="${(endDate.dayOfWeek.value - 7)*(-1)}"/>
									
									<c:if test="${offsetBeginDate != 0}">
										<c:forEach begin="0" end="${offsetBeginDate -1}" step="1">
											<span class="jzdb"><!--BLANK--></span>
										</c:forEach>
									</c:if>
									<c:forEach begin="1" end="${endDate.dayOfMonth }" step="1" var="day">
										<span id="${day}" class="${not empty mapEvents[day] ? 'calendar-event circle' : ''}">${day}</span>
									</c:forEach>
									
									<c:if test="${offsetEndDate != 0}">
										<c:forEach begin="0" end="${offsetEndDate - 1}" step="1">
											<span class="jzdb"><!--BLANK--></span>
										</c:forEach>
									</c:if>
								</div>
                    	</div>
                    	<br/>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="au-card au-card--no-shadow au-card--no-pad m-b-40">
                                    <div class="au-card-title" style="background-image:url('<%=request.getContextPath()%>/resources/images/bg-title-01.jpg');">
                                        <div class="bg-overlay bg-overlay--blue"></div>
                                        <h3>
                                            <i class="zmdi zmdi-account-calendar"></i><fmt:message key="label.${month}.month" bundle="${lang}"/> ${year}</h3>
                                        <button class="au-btn-plus" onclick="location.href='monthly/add?uuid=${journal.uuid}&month=${month}'">
                                            <i class="zmdi zmdi-plus"></i>
                                        </button>
                                    </div>
                                    <div class="au-task js-list-load">
                                        <div class="au-task__title">
<!--                                             <p>Tasks for John Doe</p> -->
                                        </div>
                                        <div class="au-task-list js-scrollbar3">
                                        	<c:forEach items="${monthlyTasks}" var="task">
	                                        	<div class="au-task__item au-task__item--${task.state == 'DONE' ? 'success' : task.state == 'TO_DO' ? 'danger' : 'warning'}" style="height:4.5em;">
	                                                <div class="au-task__item-inner" style="height:100%;">
	                                                    <h5 class="task" id="${task.uuid }" style="width:50%;float:left;">
	                                                        <c:choose>
			                                                    <c:when test="${task.state == 'DONE'}">
			                                                    	<input onchange="updateState(this)" type="checkbox" class="status--process_${task.state }" style="position:relative;top:0%;" checked/>
			                                                    </c:when>
			                                                    <c:when test="${task.state == 'POSTPONE'}">
			                                                    	<input onchange="updateState(this)" type="checkbox" class="status--process_${task.state }" style="position:relative;top:0%;" disabled="disabled"/>
			                                                    </c:when>
			                                                    <c:otherwise>
			                                                    	<input onchange="updateState(this)" type="checkbox" class="status--process_${task.state }" style="position:relative;top:0%;" />
			                                                    </c:otherwise>
			                                                </c:choose>
			                                                <a href="tasks/monthly/show?uuid=${task.uuid }">${task.description}</a>
	                                                        
	                                                    </h5>
	                                                 <div class="table-data-feature" style="float:right;width:50%;">
		                                                 <c:choose>
		                                                 	<c:when test="${task.state == 'POSTPONE'}">
		                                                 		<img alt="postponed" src="<%=request.getContextPath()%>/resources/images/icon/postponed.png" style="height:30px !important;width:30px;">
		                                                 	</c:when>
		                                                 	<c:when test="${task.state == 'DONE'}">
		                                                 		<img alt="postponed" src="<%=request.getContextPath()%>/resources/images/icon/check.png" style="height:30px !important;width:30px;">
		                                                 	</c:when>
		                                                 	<c:otherwise>
		                                                 		<button class="item" data-toggle="tooltip" data-placement="top" title="Reporter" onclick="updateState(this,'POSTPONE')">
		                                                 			<i class="zmdi zmdi-mail-send"></i>
		                                                 		</button>
		                                                 	</c:otherwise>
		                                                 </c:choose>
		                                                 
	                                                 </div>
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
    
    <!-- QTIP JS-->
    <script src="<%=request.getContextPath()%>/resources/qtip/jquery.qtip.min.js"></script>
   
    <!-- render JS-->
    <script src="<%=request.getContextPath()%>/resources/render/jsrender.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/render/jsviews.min.js"></script>

	<script id="eventsTemplate" type="text/x-jsrender">
		<ul>
            {{for events}}
                <li id="{{:uuid}}">
                    {{:description}}
                </li>
            {{/for}}
		</ul>
	</script>

	<script type="text/javascript">

	var eventByDay = {<c:forEach items="${mapEvents.entrySet()}" var="value" varStatus="vs">
	'${value.key}': [
		<c:forEach items="${value.value}" var="event" varStatus="eventStatus">
				{	uuid: '${event.uuid}',
					date: '<tags:localDate date="${event.date}"/>',
					time: '${event.time}',
					description: '${event.description}',
					place:'${event.place}'
				}<c:if test="${not eventStatus.last}">,</c:if></c:forEach>
	]<c:if test="${not vs.last}">,</c:if></c:forEach>
	};

    var eventsTemplate = $.templates("#eventsTemplate");
    
	$(document).ready(function(){
		$.each($(".calendar-event"),function(item){
			var day = $(this).attr("id");
			var events = eventByDay[day];
			$(".calendar-event").on('mouseover click',function(event){

				$(this).qtip({
    				overwrite: true, // Make sure the tooltip won't be overridden once created
    		    	content: eventsTemplate.render({events : events}),
    		    	
    				style: 'qtip-light menu',
    				show: {ready: true},
    		        hide: {fixed: false, delay:10000000, leave: false},
    			   	position: {
    					viewport: $('body'),
    					target: 'mouse',
    					adjust:{
    						mouse:false
    					}
    				},
    		        events: {
    		        	render: function(event, api) {
    		        	}
    				}
    	   		 }, event);
			});
		});

	});

	var journalUuid = "${journal.uuid}";
	var previousMonth = "${previousMonth}";
	var nextMonth = "${nextMonth}";
	
	function onNextMonth(e)
	{
		window.location.replace("show?uuid="+journalUuid + "&month=" + nextMonth);
	}

	function onPreviousMonth(e)
	{
		window.location.replace("show?uuid="+journalUuid + "&month=" + previousMonth);
	}

	function updateState(task,state){
		var uuid = $(task).parent().siblings().attr("id");
		if(state == null)
		{
			state = $(task).is(':checked') ? "DONE" : "TO_DO";
			uuid = $(task).closest("h5").attr("id");
		}
		
		var urlAjax = 'updateState?uuid=' + uuid;
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
	   			location.reload();
	  	    },
			error: function(jqXHR, status, errorThrown) {
				location.reload();
			}
		});
	}
	
	</script>

</body>

</html>
<!-- end document-->
