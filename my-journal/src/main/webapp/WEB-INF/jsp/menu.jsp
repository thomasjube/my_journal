<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="true" %>
<!DOCTYPE html>
<!-- HEADER MOBILE-->
        <header class="header-mobile d-block d-lg-none">
            <div class="header-mobile__bar">
                <div class="container-fluid">
                    <div class="header-mobile-inner">
                        <a class="logo" href="<%=request.getContextPath()%>/home">
                            <img src="<%=request.getContextPath()%>/resources/images/icon/logo.png" alt="CoolAdmin" />
                        </a>
                        <button class="hamburger hamburger--slider" type="button">
                            <span class="hamburger-box">
                                <span class="hamburger-inner"></span>
                            </span>
                        </button>
                    </div>
                </div>
            </div>
            <nav class="navbar-mobile">
                <div class="account-wrap" style="background:white;width:26em;margin-left:1em;">
	                <div class="account-item clearfix js-item-menu">
	                    <div class="image">
	                        <img src="<%=request.getContextPath()%>/resources/images/icon/avatar-01.jpg" alt="John Doe" />
	                    </div>
	                    <div class="content">
	                        <a class="js-acc-btn" href="#">
	                        	<c:choose>
	                        		<c:when test="${not empty sessionScope.loggedAccount.alias}">
	                        			${sessionScope.loggedAccount.alias}
	                        		</c:when>
	                        		<c:otherwise>
	                        			${sessionScope.loggedAccount.firstName}&nbsp;${sessionScope.loggedAccount.lastName}
	                        		</c:otherwise>
	                        	</c:choose>
	                        </a>
	                    </div>
	                    <div class="account-dropdown js-dropdown">
	                        <div class="info clearfix">
	                            <div class="image">
	                                <a href="#">
	                                    <img src="<%=request.getContextPath()%>/resources/images/icon/avatar-01.jpg" alt="John Doe" />
	                                </a>
	                            </div>
	                            <div class="content">
	                                <h5 class="name">
	                                    <a href="#">${sessionScope.loggedAccount.firstName}&nbsp;${sessionScope.loggedAccount.lastName}</a>
	                                </h5>
	                                <span class="email">${sessionScope.loggedAccount.email }</span>
	                            </div>
	                        </div>
	                        <div class="account-dropdown__body">
	                            <div class="account-dropdown__item">
	                                <a href="account/settings">
	                                    <i class="zmdi zmdi-settings"></i>Setting</a>
	                            </div>
	                        </div>
	                        <div class="account-dropdown__footer">
	                            <a href="login/logout">
	                                <i class="zmdi zmdi-power"></i>Logout</a>
	                        </div>
	                    </div>
	                </div>
	            </div>
                <div class="container-fluid">
                    <ul class="navbar-mobile__list list-unstyled">
                        <li class="active has-sub">
                            <a class="js-arrow" href="<%=request.getContextPath()%>/">
                                <i class="fas fa-tachometer-alt"></i>Accueil</a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath()%>/journal">
                                <i class="fas fa-calendar-alt"></i>Journal</a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath()%>/tasks">
                                <i class="fas fa-check-square"></i>Objectifs Mens.</a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath()%>/tracker">
                                <i class="far fa-chart-bar"></i>Tracker</a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath()%>/budget">
                                <i class="fas fa-shopping-basket"></i>Budget</a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath()%>/category">
                                <i class="fas fa-briefcase"></i>Catégories</a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath()%>/objective">
                                <i class="fas fa-table"></i>Bucket list</a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath()%>/wish">
                                <i class="fas fa-map-marker-alt"></i>WishList / ToDOList</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
        <!-- END HEADER MOBILE-->

        <!-- MENU SIDEBAR-->
        <aside class="menu-sidebar d-none d-lg-block">
            <div class="logo">
                <a href="<%=request.getContextPath()%>/home">
                    <img src="<%=request.getContextPath()%>/resources/images/icon/logo.png" alt="Cool Admin" />
                </a>
            </div>
            <div class="menu-sidebar__content js-scrollbar1">
                <div class="account-wrap">
                        <div class="account-item clearfix js-item-menu" style="margin-top:2em;margin-left:2em;">
                            <div class="image">
                                <img src="<%=request.getContextPath()%>/resources/images/icon/avatar-01.jpg" alt="John Doe" />
                            </div>
                            <div class="content">
                                <a class="js-acc-btn" href="#">
                                	<c:choose>
                                		<c:when test="${not empty sessionScope.loggedAccount.alias}">
                                			${sessionScope.loggedAccount.alias}
                                		</c:when>
                                		<c:otherwise>
                                			${sessionScope.loggedAccount.firstName}&nbsp;${sessionScope.loggedAccount.lastName}
                                		</c:otherwise>
                                	</c:choose>
                                </a>
                            </div>
                            <div class="account-dropdown js-dropdown">
                                <div class="info clearfix">
                                    <div class="image">
                                        <a href="#">
                                            <img src="<%=request.getContextPath()%>/resources/images/icon/avatar-01.jpg" alt="John Doe" />
                                        </a>
                                    </div>
                                    <div class="content">
                                        <h5 class="name">
                                            <a href="#">${sessionScope.loggedAccount.firstName}&nbsp;${sessionScope.loggedAccount.lastName}</a>
                                        </h5>
                                        <span class="email">${sessionScope.loggedAccount.email }</span>
                                    </div>
                                </div>
                                <div class="account-dropdown__body">
                                    <div class="account-dropdown__item">
                                        <a href="account/settings">
                                            <i class="zmdi zmdi-settings"></i>Setting</a>
                                    </div>
                                </div>
                                <div class="account-dropdown__footer">
                                    <a href="login/logout">
                                        <i class="zmdi zmdi-power"></i>Logout</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <nav class="navbar-sidebar">
                    <ul class="list-unstyled navbar__list">
                        <li>
                            <a class="js-arrow" href="<%=request.getContextPath()%>/">
                                <i class="fas fa-tachometer-alt"></i>Accueil</a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath()%>/journal">
                                <i class="fas fa-calendar-alt"></i>Journal</a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath()%>/tasks">
                                <i class="fas fa-check-square"></i>Objectifs Mens.</a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath()%>/tracker">
                                <i class="far fa-chart-bar"></i>Tracker</a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath()%>/budget">
                                <i class="fas fa-shopping-basket"></i>Budget</a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath()%>/category">
                                <i class="fas fa-briefcase"></i>Catégories</a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath()%>/objective">
                                <i class="fas fa-table"></i>Bucket list</a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath()%>/wish">
                                <i class="fas fa-map-marker-alt"></i>Wish list / ToDo list</a>
                        </li>
                    </ul>
                </nav>
            </div>
        </aside>
        <!-- END MENU SIDEBAR-->

        <!-- PAGE CONTAINER-->
        <div class="page-container">
            <!-- HEADER DESKTOP-->
<!--             <header class="header-desktop"> -->
<!--                 <div class="section__content section__content--p30"> -->
<!--                     <div class="container-fluid"> -->
<!--                         <div class="header-wrap" style="float:right;"> -->
<!--                             <div class="header-button"> -->
<!--                                 <div class="noti-wrap"> -->
<!--                                     <div class="noti__item js-item-menu"> -->
<!--                                         <i class="zmdi zmdi-notifications"></i> -->
<%--                                         <span class="${not empty sessionScope.todayJournalEvents ? 'quantity' :''}">${not empty sessionScope.todayJournalEvents ? sessionScope.todayJournalEvents.length : ''}</span> --%>
<!--                                         <div class="notifi-dropdown js-dropdown"> -->
<!--                                             <div class="notifi__title"> -->
<%--                                                 <p>Vous avez ${not empty sessionScope.todayJournalEvents ? sessionScope.todayJournalEvents.length : 0} Notifications</p> --%>
<!--                                             </div> -->
<%--                                             <c:forEach items="${sessionScope.todayJournalEvents}" var="journalEvent"> --%>
<!-- 	                                            <div class="notifi__item"> -->
<!-- 	                                                <div class="bg-c1 img-cir img-40"> -->
<!-- 	                                                    <i class="zmdi zmdi-email-open"></i> -->
<!-- 	                                                </div> -->
<!-- 	                                                <div class="content"> -->
<%-- 	                                                    <p>${journalEvent.description }</p> --%>
<%-- 	                                                    <span class="date">${journalEvent.dateTime }</span> --%>
<!-- 	                                                </div> -->
<!-- 	                                            </div> -->
<%--                                             </c:forEach> --%>
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                 </div> -->
                                
<!--                             </div> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             </header> -->
            <!-- HEADER DESKTOP-->