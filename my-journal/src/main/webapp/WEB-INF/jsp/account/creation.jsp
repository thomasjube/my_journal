<!DOCTYPE html>
<html lang="fr">
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	    <meta name="description" content="au theme template">
	    <meta name="author" content="Hau Nguyen">
	    <meta name="keywords" content="au theme template">
	
	    <title>Création</title>
	
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
	        <div class="page-content--bge5">
	            <div class="container">
	                <div class="login-wrap">
	                    <div class="login-content">
	                        <div class="login-logo">
	                            <a href="#">
	                                <img src="<%=request.getContextPath()%>/resources/images/icon/logo.png" alt="Logo">
	                            </a>
	                        </div>
	                        <div class="login-form">
	                            <form action="" method="post">
									<div class="form-group">
	                                    <label>Nom</label>
	                                    <input class="au-input au-input--full" type="text" name="lastName" placeholder="Nom">
	                                </div>
	                            	
	                            	<div class="form-group">
	                                    <label>Prénom</label>
	                                    <input class="au-input au-input--full" type="text" name="firstName" placeholder="Prénom">
	                                </div>
	                                
	                                <div class="form-group">
	                                    <label>Pseudo</label>
	                                    <input class="au-input au-input--full" type="text" name="alias" placeholder="pseudo">
	                                </div>
	                                
	                                <div class="form-group">
	                                    <label>Date de naissance</label>
	                                    <input class="au-input au-input--full" type="date" name="birthDate" id="birthDateInput">
	                                </div>
	                                
	                                <div class="form-group">
	                                    <label>Adresse mail</label>
	                                    <input class="au-input au-input--full" type="email" name="email" placeholder="Email">
	                                </div>
	                                <div class="form-group">
	                                    <label>Mot de passe</label>
	                                    <input class="au-input au-input--full" type="password" name="password" placeholder="Password">
	                                </div>
	                                <button class="au-btn au-btn--block au-btn--green m-b-20" type="submit">Enregistrer</button>
	                            </form>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	
	    <script src="<%=request.getContextPath()%>/resources/vendor/jquery-3.2.1.min.js"></script>
	    <script src="<%=request.getContextPath()%>/resources/vendor/bootstrap-4.1/popper.min.js"></script>
	    <script src="<%=request.getContextPath()%>/resources/vendor/bootstrap-4.1/bootstrap.min.js"></script>
	    <script src="<%=request.getContextPath()%>/resources/vendor/slick/slick.min.js"></script>
	    <script src="<%=request.getContextPath()%>/resources/vendor/wow/wow.min.js"></script>
	    <script src="<%=request.getContextPath()%>/resources/vendor/animsition/animsition.min.js"></script>
	    <script src="<%=request.getContextPath()%>/resources/vendor/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
	    <script src="<%=request.getContextPath()%>/resources/vendor/counter-up/jquery.waypoints.min.js"></script>
	    <script src="<%=request.getContextPath()%>/resources/vendor/counter-up/jquery.counterup.min.js"></script>
	    <script src="<%=request.getContextPath()%>/resources/vendor/circle-progress/circle-progress.min.js"></script>
	    <script src="<%=request.getContextPath()%>/resources/vendor/perfect-scrollbar/perfect-scrollbar.js"></script>
	    <script src="<%=request.getContextPath()%>/resources/vendor/chartjs/Chart.bundle.min.js"></script>
	    <script src="<%=request.getContextPath()%>/resources/vendor/select2/select2.min.js"></script>
	    <script src="<%=request.getContextPath()%>/resources/js/main.js"></script>
	</body>
</html>