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
    
    <!-- Calendar-->
    <link href="<%=request.getContextPath()%>/resources/calendar/calendar.css" rel="stylesheet" media="all">
    
    <!-- QTIP-->
    <link href="<%=request.getContextPath()%>/resources/qtip/jquery.qtip.min.css" rel="stylesheet" media="all">

</head>

<body class="animsition">
    <div class="page-wrapper">
        
			<jsp:include page="../menu.jsp"/>

            <!-- MAIN CONTENT-->
            <div class="main-content">
                <div class="section__content section__content--p30">
                	<h2 class="title-1 m-b-25">Tracker : ${tracker.name }</h2>
                    <div class="container-fluid">
                        <div class="row">
	                        <div class="jzdbox1 jzdbasf jzdcal" style="background:#c7c7c7 !important;">
							<div class="jzdcalt"><fmt:message key="label.${month}.month" bundle="${lang}"/> ${year}
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
										<c:set var="test2" value="${mapDayState[day].color}"/>
										<c:set var="test" value="style='background:${test2};'"/>
										<span id="${day}" class="day" ${mapDayState[day] != null ? test : ''}>${day}</span>
									</c:forEach>
									
									<c:if test="${offsetEndDate != 0}">
										<c:forEach begin="0" end="${offsetEndDate - 1}" step="1">
											<span class="jzdb"><!--BLANK--></span>
										</c:forEach>
									</c:if>
								</div>
	                    	</div>
	                    	<br/>
        		            <br/>
	                    	<div class="row"> 
                            <div class="col-lg-6">
                                <div class="table table--no-card m-b-40">
                                    <table class="table table-borderless table-striped table-earning">
                                        <thead>
                                            <tr>
                                                <th>Legende</th>
                                                <th class="text-right"></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        	<c:forEach items="${tracker.states}" var="state">
                                        		<tr>
                                        			<td>${state.name}</td>
                                        			<td><input type="color" value="${state.color}" disabled="disabled"></td>
                                        		</tr>
                                        	</c:forEach>
                                        </tbody>
                                    </table>
                                </div>
<!--                                 <div class="table-responsive table--no-card m-b-40"> -->
<!--                                     <table class="table table-borderless table-striped table-earning"> -->
<!--                                         <thead> -->
<!--                                             <tr> -->
<!--                                                 <th>date</th> -->
<!--                                                 <th class="text-right">resultat</th> -->
<!--                                             </tr> -->
<!--                                         </thead> -->
<!--                                         <tbody> -->
<%--                                         	<c:forEach items="${tracker.dailyTasks}" var="task"> --%>
<!--                                         		<tr> -->
<%--                                         			<td><tags:localDate date="${task.date}"/></td> --%>
<%--                                         			<td class="text-center">${task.state}</td> --%>
<!--                                         		</tr> -->
<%--                                         	</c:forEach> --%>
<!--                                         </tbody> -->
<!--                                     </table> -->
<!--                                 </div> -->
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

 	<!-- QTIP JS-->
    <script src="<%=request.getContextPath()%>/resources/qtip/jquery.qtip.min.js"></script>
    
    <!-- render JS-->
    <script src="<%=request.getContextPath()%>/resources/render/jsrender.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/render/jsviews.min.js"></script>
    
    <!-- Main JS-->
    <script src="<%=request.getContextPath()%>/resources/js/main.js"></script>
    
    <script id="menuTemplate" type="text/x-jsrender">
		<table>
			<tbody>
            	<c:forEach items="${tracker.states}" var="state">
                	<tr>
                    	<td>${state.name}</td>
                    	<td><input type="color" value="${state.color}" disabled="disabled"></td>
                	</tr>
            	</c:forEach>
        	</tbody>
		</table>
	</script>
    
    
    <script type="text/javascript">


    var menuTemplate = $.templates("#menuTemplate");
    
	$(document).on('click',$(".day"),function(e){
		showMenu(e,$(this));
	});
    
    function showMenu(event,day) 
    {	
        day = $("#1");
    	var app={};
    		$('.qtip').remove();
    			$(day.$el).qtip({
    				overwrite: true, // Make sure the tooltip won't be overridden once created
    		    	content: menuTemplate.render(app),
    		    	
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
    		    			$(document).on('click', function(e){
    		    				e.preventDefault();
    		    				if (!$(e.target).parents(".qtip").length) {
        							var tooltips = $(day.$el).qtip({});
        							var api = tooltips.qtip('api');
    	    						if(api){
    	    							api.destroy();
    	    						}
    	    						else if($('.context-menu').length > 0)
    	    							$('.context-menu').parent().parent().remove();
    	    						currentRange = null;
    		    						
    		    				}
    		    			});
    		            	$('.state-choices li', api.elements.content).click(function(e) {
    		            		clickMenu($(this).attr("id"),$(day).attr("id"));
    		            		api.destroy();
    		                });
    		            	$('.deletes', api.elements.content).click(function(e) {
    		            		clickMenuDelete($(day).attr("id"));
    		            		api.destroy();
    		                });
    		        	}
    				}
    	   		 }, event);
    }

	//UPDATE SCHEDULE FOR DAY - ADMINISTRATION
	function clickMenu(dayId,stateUuid){
		var urlAjax = '<spring:url value="day/state/update" javaScriptEscape="true"/>';
		var dataAjax = {'dayId' : dayId, 'stateUuid' : stateUuid};
		$.ajax({
	    	url: urlAjax,
	   		type: 'PUT',
	   		contentType: 'application/json',
	   		data: JSON.stringify(dataAjax),
	   		success: function(data, status, jqXHR) {
	  	    },
			error: function(jqXHR, status, errorThrown) {
			}
		});
	}

	//DELETE SCHEDULE FOR DAY - ADMINISTRATION
	function clickMenuDelete(dayId,stateUuid){
		var urlAjax = '<spring:url value="day/state/delete" javaScriptEscape="true"/>';
		var dataAjax = {'dayId' : dayId, 'stateUuid' : stateUuid};
		
		$.ajax({
	    	url: urlAjax,
	   		type: 'DELETE',
	   		contentType: 'application/json',
	   		data: JSON.stringify(dataAjax),
	   		success: function(data, status, jqXHR) {
	  	    },
			error: function(jqXHR, status, errorThrown) {
			}
		});
	}
    </script>

</body>

</html>
<!-- end document-->
