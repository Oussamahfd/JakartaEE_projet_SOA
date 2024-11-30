<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
		<%@ page import="DAO.etudao" %>
		<%@ page import="classes.Etud" %>
	<%@ page import="java.sql.ResultSet, java.sql.SQLException" %>
	<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="DAO.etudao"%>
<%@ page import="java.sql.ResultSet, java.sql.SQLException"%>
<%@ page import="classes.Etud"%>
<%@ page import="classes.Absence"%>
<%@ page import="java.sql.ResultSet, java.sql.SQLException"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="classes.Prof" %>
<%@ page import="java.util.Locale"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%Object fullIdObject = session.getAttribute("fullid");
int fullId =0;

if (fullIdObject != null && fullIdObject instanceof Integer) {
    fullId = (Integer) fullIdObject;

} else {
    // G�rez le cas o� la valeur n'est pas pr�sente ou n'est pas un entier
}
%>
	
	<%!
	DAO.etudao et;%>
<%
    et = new DAO.etudao();
String noms = (String) session.getAttribute("fullnm");
String prnms = (String) session.getAttribute("fullpn");
String mls = (String) session.getAttribute("fullmail");
String rl = (String) session.getAttribute("fullrl");
ResultSet rs = et.findemploiprf(fullId);
session.setAttribute("rs", rs);
Integer id = (Integer) session.getAttribute("fullid");
rs = et.matprf(id);
ResultSet rsm = rs;
rs = (ResultSet) session.getAttribute("rs");
String matv = " ";
while (rsm.next()){matv=rsm.getString(1);}


String spc = " ";
String[] substrings = new String[0];
String esspace = " ";
boolean containsValue = false;
List<String> colorList = new ArrayList<>();

colorList.add("rgb(0, 109, 240)");
colorList.add("rgb(169, 169, 169)");
colorList.add("rgb(255, 255, 0)");
noms = (String) session.getAttribute("fullnm");
prnms = (String) session.getAttribute("fullpn");
mls = (String) session.getAttribute("fullmail");
rl = (String) session.getAttribute("fullrl");
%>

<!DOCTYPE html>
<html lang="en">

<head>
<!-- main dashboard -->
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="icon" type="image/png" href="assets/img/logo.png">


<title>Gestion Profs</title>
<!--     Fonts and icons     -->
<link rel="stylesheet" type="text/css"
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900|Roboto+Slab:400,700" />
<!-- Nucleo Icons -->
<link href="assets/css/nucleo-icons.css" rel="stylesheet" />
<link href="assets/css/nucleo-svg.css" rel="stylesheet" />
<!-- Font Awesome Icons -->
<script src="https://kit.fontawesome.com/42d5adcbca.js"
	crossorigin="anonymous"></script>
<!-- Material Icons -->
<link
	href="https://fonts.googleapis.com/icon?family=Material+Icons+Round"
	rel="stylesheet">
<link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css'
	rel='stylesheet'>
<!-- Material Icons/sidebar -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
	integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<!-- CSS Files -->
<link id="pagestyle" href="assets/css/material-dashboard.css?v=3.1.0"
	rel="stylesheet" />
<!-- Nepcha Analytics (nepcha.com) -->
<!-- Nepcha is a easy-to-use web analytics. No cookies and fully compliant with GDPR, CCPA and PECR. -->
<script defer data-site="YOUR_DOMAIN_HERE"
	src="https://api.nepcha.com/js/nepcha-analytics.js"></script>

<!-- sidebar / navbar -->
<link rel="stylesheet" href="style.css">


<!-- Custom CSS -->
<link href="css/style.min.css" rel="stylesheet">
<!--pagination -->
<link rel="stylesheet" href="css/pagination.css">
<!-- btn groupe -->
<link rel="stylesheet" href="css/btngrp.css">

<!-- unused -->
<!-- Boxiocns CDN Link -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="keywords"
	content="wrappixel, admin dashboard, html css dashboard, web dashboard, bootstrap 5 admin, bootstrap 5, css3 dashboard, bootstrap 5 dashboard, Ample lite admin bootstrap 5 dashboard, frontend, responsive bootstrap 5 admin template, Ample admin lite dashboard bootstrap 5 dashboard template">
<meta name="description"
	content="Ample Admin Lite is powerful and clean admin dashboard template, inpired from Bootstrap Framework">
<meta name="robots" content="noindex,nofollow">
<link rel="canonical"
	href="https://www.wrappixel.com/templates/ample-admin-lite/" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />


<!-- button ajouter -->
<link
	href="https://fonts.googleapis.com/css2?family=Bruno+Ace&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Poppins&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="stylebtn.css" />

<!-- menu sidebar -->
        <link rel="stylesheet" href="css/stylemenu.css">
        <link rel="stylesheet" type="text/css"
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900|Roboto+Slab:400,700" />
<!-- Nucleo Icons -->
<link href="assets/css/nucleo-icons.css" rel="stylesheet" />
<link href="assets/css/nucleo-svg.css" rel="stylesheet" />
<!-- Font Awesome Icons -->
<script src="https://kit.fontawesome.com/42d5adcbca.js"
	crossorigin="anonymous"></script>
<!-- Material Icons -->
<link
	href="https://fonts.googleapis.com/icon?family=Material+Icons+Round"
	rel="stylesheet">
<link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css'
	rel='stylesheet'>
<!-- Material Icons/sidebar -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
	integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<!-- CSS Files -->
<link id="pagestyle" href="assets/css/material-dashboard.css?v=3.1.0"
	rel="stylesheet" />
<!-- Nepcha Analytics (nepcha.com) -->
<!-- Nepcha is a easy-to-use web analytics. No cookies and fully compliant with GDPR, CCPA and PECR. -->
<script defer data-site="YOUR_DOMAIN_HERE"
	src="https://api.nepcha.com/js/nepcha-analytics.js"></script>

<!-- sidebar / navbar -->
<link rel="stylesheet" href="style.css">


<!-- Custom CSS -->
<link href="css/style.min.css" rel="stylesheet">
<!--pagination -->
<link rel="stylesheet" href="css/pagination.css">
<!-- btn groupe -->
<link rel="stylesheet" href="css/btngrp.css">

<!-- unused -->
<!-- Boxiocns CDN Link -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="keywords"
	content="wrappixel, admin dashboard, html css dashboard, web dashboard, bootstrap 5 admin, bootstrap 5, css3 dashboard, bootstrap 5 dashboard, Ample lite admin bootstrap 5 dashboard, frontend, responsive bootstrap 5 admin template, Ample admin lite dashboard bootstrap 5 dashboard template">
<meta name="description"
	content="Ample Admin Lite is powerful and clean admin dashboard template, inpired from Bootstrap Framework">
<meta name="robots" content="noindex,nofollow">
<link rel="canonical"
	href="https://www.wrappixel.com/templates/ample-admin-lite/" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />


<!-- button ajouter -->
<link
	href="https://fonts.googleapis.com/css2?family=Bruno+Ace&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Poppins&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="stylebtn.css" />

<!-- menu sidebar -->
<link rel="stylesheet" href="css/stylemenu.css">

<link rel="stylesheet" href="calendar/fullcalendar.min.css">
<link rel="stylesheet" href="calendar/fullcalendar.print.min.css">
<!-- style CSS
		============================================ -->
<link rel="stylesheet" href="style.css">

<style type="text/css">
#header-row {
	background-color: #f0f0f0; /* Exemple de couleur de fond */
	font-weight: bold; /* Exemple de style de police en gras */
}
</style>

</head>

<body class="g-sidenav-show  bg-gray-200">

	<!-- navbar  -->
	
	<div >
		<header class="topbar" style="background-color:black;">
		
		<div class="datetime" style="color:white; margin-right: 600px;z-index:1;"></div>
		
		
			<span class="navigation__group">
                <img class="icon" src="assets/img/notification.svg" alt="Notification">
                <img class="profile" src="assets/img/profileo.png" alt="Joe Doe Picture">
            </span>
            <div class="dropdown__wrapper hide dropdown__wrapper--fade-in none" style="background-color:black;">
                <div class="dropdown__group">
                    <div class="user-name" style="color:white;"><%= prnms%><%= spc %><%= noms %></div>
                    <div class="email" style="color:light-blue;"><%= mls%></div>
                </div>
                <hr class="divider" style="border-color: white; border-style: solid; border-width: 1px;">
                <nav>
                    <ul>
                        <li style="color:white;">
                            <i class="fa-solid fa-user" style="color: #ffffff;"></i> My Profile
                        </li>
                        <li style="color:white;">
                            <i class="fa-solid fa-gear" style="color: #ffffff;"></i> Settings
                        </li>
                    </ul>
                    <hr class="divider" style="color:white;">
                    <ul>
                        <li style="color:white;">
                            <img src="assets/img/tutorials.svg" alt="Tutorials"> Tutorials
                        </li>
                        <li style="color:white;">
                            <i class="fa-solid fa-circle-question" style="color: #ffffff;"></i> Help Center
                        </li>
                    </ul>
                    <hr class="divider" style="border-color: white; border-style: solid; border-width: 1px;">
                    <form action="Disconn" method="post" style="list-style-type: none; padding: 0; margin: 0;">
    <ul style="list-style-type: none; margin-top:10px;">
        <li style="color: #E3452F;">
            <i class="fa-solid fa-right-from-bracket" style="color: #E3452F;"></i>
            <input type="submit" value="Log out" style="background:none; border:none; padding:0; color:inherit; cursor:pointer;">
        </li>
    </ul>
</form>
                </nav>
            </div>
		</header>
	</div>

	<!-- sidebar-->
	<div class="sidebar close" style="background-color:black;">
		<div class="logo-details">
			<i class="fa-solid fa-school" style="color: #ffffff;"></i> <span class="logo_name">EnlightenEd</span>
		</div>
		<ul class="nav-links">
			
			
			<li>
				<div class="iocn-link">
					<a href="premiereprofs.jsp"> <i class='bx bxs-credit-card-alt'></i> <span
						class="link_name">Paiement</span>
					</a> 
					<ul class="sub-menu blank">
					<li><a class="link_name" href="annonces.jsp?page=1">Paiement</a></li>
				</ul>
				</div>
				
			</li>

			
			<li>
			<li>
				<div class="iocn-link">
					<a href="intprofabs.jsp"> <i class='bx bx-stopwatch'></i> <span
						class="link_name">Absences</span>
					</a> 
					<ul class="sub-menu blank">
					<li><a class="link_name" href="annonces.jsp?page=1">Absences</a></li>
				</ul>
				</div>
				
			</li>
			<li>
				<div class="iocn-link" >
					<a href="intprofempl.jsp"> <i class='bx bxs-calendar'></i> <span
						class="link_name">Emploi de temps</span>
						
					</a> 
					<ul class="sub-menu blank">
					<li><a class="link_name" href="annonces.jsp?page=1">Emploi de temps</a></li>
				</ul>
				</div>
				
			</li>

			<li><a href="intprofann.jsp"> <i class='bx bxs-news'></i> <span
					class="link_name">Annonces</span>
			</a>
				<ul class="sub-menu blank">
					<li><a class="link_name" href="annonces.jsp">Annonces</a></li>
				</ul></li>

			<li>
				
				
		</ul>
	</div>


	<!-- main dashboard -->
	<section class="home-section">
		<div class="home-content">
			<i class='bx bx-menu'></i>
			
		</div>

		<!-- button ajouter -->
		<main
			class="main-content position-relative max-height-vh-100 h-100 border-radius-lg ">
			<div style="font-family: 'Red Hat Display', sans-serif;display: flex;align-items: center;margin-left: 73%;font-size:14pt;margin-bottom:13px;"><span>Home/</span><span style="color: #2596be;"></span><span style="color: #2596be; text-decoration: underline;">Emploi de temps</span></div>
			

				<div class="container-fluid py-4">
				<div class="row">
					<div class="col-12">
						<div class="card my-4" style="border-radius:12px;">
							<div
								class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
								<div class=" shadow p-3 border-radius-lg pt-4 pb-3" style="BACKGROUND-COLOR:BLACK;">
									<h6 class="text-white text-capitalize ps-3" style="text-align:center;font-size:18pt;"><b>Table D'absence(Profs)</b></h6>
								</div>
							</div>
							<div class="card-body px-0 pb-2">
								<div class="table-responsive p-0">
									<table class="table align-items-center mb-0">
											<%
														Calendar cCalendar = Calendar.getInstance();

														// Utilisez Locale.Builder pour cr�er un objet Locale
														Locale locale = new Locale.Builder().setLanguage("fr").build();

														// D�finissez le format de date pour obtenir le nom du mois en fran�ais
														SimpleDateFormat sdf = new SimpleDateFormat("MMMM", locale);

														// Obtenez le nom du mois actuel en fran�ais
														String nomMois = sdf.format(cCalendar.getTime());

														// Obtenez l'ann�e actuelle
														int anneeActuelle = cCalendar.get(Calendar.YEAR);
														%>
														<caption class="fsdf"
															style="caption-side: top; text-align: center; background-color: white;">
															<%=nomMois%>
															<%=anneeActuelle%>
														</caption>
	<%
														while (rs.next()) {
														%>
										<tbody class="fc-body">
															<tr>
																<%
																String[] joursSemaine = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"};

																// R�cup�rer la date actuelle
																java.util.Calendar calendar = java.util.Calendar.getInstance();
																calendar.set(java.util.Calendar.DAY_OF_MONTH, 1);

																// Obtenir le jour de la semaine du premier jour du mois
																int firstDayOfWeek = calendar.get(java.util.Calendar.DAY_OF_WEEK);

																// Calculer le nombre de jours dans le mois
																int daysInMonth = calendar.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);

																// Calculer le nombre de lignes n�cessaires pour afficher tous les jours du mois
																int numRows = (int) Math.ceil((daysInMonth + firstDayOfWeek - 1) / 7.0);
																%>
																<!-- Les jours de la semaine seront ajout�s dynamiquement ici -->
																<%
																for (String jour : joursSemaine) {
																%>
																<th><%=jour%></th>
																<%
																}
																%>
															</tr>

															<!-- Les lignes du tableau seront ajout�es dynamiquement ici -->
															<%
															// Boucle pour chaque ligne
															for (int i = 0; i < numRows; i++) {
															%>
															<tr>
																<%
																// Boucle pour chaque jour de la semaine
																for (int j = 1; j <= 7; j++) {
																	// Calculer le jour correspondant dans le mois
																	int day = i * 7 + j - firstDayOfWeek + 2;
																	// V�rifier si le jour est dans le mois
																	if (day > 0 && day <= daysInMonth) {
																%>
																<td style="position: relative;">
																	<div class="date"><%=day%></div> <%
 switch (j) {
 	case 1 :
 		String lundi = rs.getString("lundi");
 		if (lundi.contains(matv)){containsValue = true;}
 %><a
																	class="fc-day-grid-event fc-h-event fc-event fc-start fc-end fc-draggable fc-resizable"
																	style="background-color: ; border-color: ;">
																		<div class="fc-content">
																			<span class="fc-title">
																			<%if(containsValue){ %>
																			
																			<%=lundi %>
																			<%containsValue=false;} %>
																				</span>
																		</div>
																		<div class="fc-resizer fc-end-resizer"></div>
																</a>
																	<%
																	
																	break;
																	case 2 :
																		String mar = rs.getString("mardi");
																 		if (mar.contains(matv)){containsValue = true;}
																	
																	%><a
																	class="fc-day-grid-event fc-h-event fc-event fc-start fc-end fc-draggable fc-resizable"
																	style="background-color: ; border-color: ;">
																		<div class="fc-content">
																			<span class="fc-title">
																				 <%if(containsValue){ %>
																			<%=mar %>
																			<%containsValue=false;} %> </span>
																		</div>
																		<div class="fc-resizer fc-end-resizer"></div>
																</a> <%
 
 break;
 case 3 :
	 String mer = rs.getString("mercredi");
		if (mer.contains(matv)){containsValue = true;}
 
 %><a
																	class="fc-day-grid-event fc-h-event fc-event fc-start fc-end fc-draggable fc-resizable"
																	style="background-color: ; border-color: ;">
																		<div class="fc-content">
																			<span class="fc-title">
																				<%if(containsValue){ %>
																			<%=mer %>
																			<%containsValue=false;} %>
																				
																			</span>
																		</div>
																		<div class="fc-resizer fc-end-resizer"></div>
																</a> <%
 
 break;
 case 4 :
	 String jeu = rs.getString("jeudi");
		if (jeu.contains(matv)){containsValue = true;}
 %><a
																	class="fc-day-grid-event fc-h-event fc-event fc-start fc-end fc-draggable fc-resizable"
																	style="background-color: ; border-color: ;">
																		<div class="fc-content">
																			<span class="fc-title">
																				<%if(containsValue){ %>
																			<%=jeu %>
																			<%containsValue=false;} %>
																			</span>
																		</div>
																		<div class="fc-resizer fc-end-resizer"></div>
																</a> <%
 
 break;
 case 5 :
	 String ven = rs.getString("vendredi");
		if (ven.contains(matv)){containsValue = true;}
 
 %><a
																	class="fc-day-grid-event fc-h-event fc-event fc-start fc-end fc-draggable fc-resizable"
																	style="background-color: ; border-color: ;">
																		<div class="fc-content">
																			<span class="fc-title"> <%if(containsValue){
																				containsValue=false;
																				String[] tableauValeurs7 = rs.getString("vendredi").split(",");
																				int idx=0;
																				for(int c =0;c<tableauValeurs7.length;c++)
																				{ 
																					if(tableauValeurs7[c].contains(matv)){idx=c;}
																				}
																				for(int v=idx;v<tableauValeurs7.length;v++){
																				%>
																			<%=tableauValeurs7[v]%><%=esspace%>
																			
																			<%}} %>
																			</span>
																		</div>
																		<div class="fc-resizer fc-end-resizer"></div>
																</a> <%
 
 break;
 case 6 :
	 String sam = rs.getString("samedi");
		if (sam.contains(matv)){containsValue = true;}
%><a
																	class="fc-day-grid-event fc-h-event fc-event fc-start fc-end fc-draggable fc-resizable"
																	style="background-color: ; border-color: ;">
																		<div class="fc-content">
																			<span class="fc-title">
																				<%if(containsValue){
																				containsValue=false;
																				String[] tableauValeurs7 = rs.getString("samedi").split(",");
																				int idx=0;
																				for(int c =0;c<tableauValeurs7.length;c++)
																				{ 
																					if(tableauValeurs7[c].contains(matv)){idx=c;}
																				}
																				for(int v=idx;v<tableauValeurs7.length;v++){
																				%>
																			<%=tableauValeurs7[v]%><%=esspace%>
																			
																			<%}} %>
																				
																				</span>
																		</div>
																		<div class="fc-resizer fc-end-resizer"></div>
																</a> <%
 
 break;
 case 7 :
 String dim = rs.getString("dimanche");
 		if (dim.contains(matv)){containsValue = true;}%><a
																	class="fc-day-grid-event fc-h-event fc-event fc-start fc-end fc-draggable fc-resizable"
																	style="background-color: ;border-color:  ">
																		<div class="fc-content">
																		<span class="fc-title">
																				<%if(containsValue){
																				containsValue=false;
																				String[] tableauValeurs7 = rs.getString("dimanche").split(",");
																				int idx=0;
																				for(int c =0;c<tableauValeurs7.length;c++)
																				{ 
																					if(tableauValeurs7[c].contains(matv)){idx=c;}
																				}
																				for(int v=idx;v<tableauValeurs7.length;v++){
																				%>
																			<%=tableauValeurs7[v]%><%=esspace%>
																			
																			<%}} %>
																			</span>
																		</div>
																		<div class="fc-resizer fc-end-resizer"></div>
																</a> <%
 
 break;
 // Ajoutez d'autres cas au besoin
 default :

 }
 %>
																</td>
																<%
																} else {
																%>
																<td></td>
																<%
																}
																}
																%>
															</tr>
															<%
															}
															%>
														</tbody>
														<%
														}
														%>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>


			</div>
			

		</main>
		<footer>
		<div class="container-fluid" style="">
			<div class="row align-items-center justify-content-lg-between">
				<div class="col-lg-6 mb-lg-0 mb-4">
					<div class="copyright text-center text-sm text-muted text-lg-start">
						�
						<script>
                document.write(new Date().getFullYear())
              </script>
						made with <i class="fa fa-heart" aria-hidden="true"></i> by <a
							href="https://www.creative-tim.com" class="font-weight-bold"
							target="_blank">Creative Tim</a> for a better web.
					</div>
				</div>
				<div class="col-lg-6">
					<ul
						class="nav nav-footer justify-content-center justify-content-lg-end">
						<li class="nav-item"><a href="https://www.creative-tim.com"
							class="nav-link text-muted" target="_blank">Creative Tim</a></li>
						<li class="nav-item"><a
							href="https://www.creative-tim.com/presentation"
							class="nav-link text-muted" target="_blank">About Us</a></li>
						<li class="nav-item"><a
							href="https://www.creative-tim.com/blog"
							class="nav-link text-muted" target="_blank">Blog</a></li>
						<li class="nav-item"><a
							href="https://www.creative-tim.com/license"
							class="nav-link pe-0 text-muted" target="_blank">License</a></li>
					</ul>
				</div>
			</div>
		</div>
		</footer>



	</section>

	<script src="assets/js/core/popper.min.js"></script>
	<script src="assets/js/core/bootstrap.min.js"></script>
	<script src="assets/js/plugins/perfect-scrollbar.min.js"></script>
	<script src="assets/js/plugins/smooth-scrollbar.min.js"></script>
	<script src="assets/js/plugins/chartjs.min.js"></script>
	
	<script async defer src="https://buttons.github.io/buttons.js"></script>
	<script src="assets/js/material-dashboard.min.js?v=3.1.0"></script>


	<!-- sidebar / navbar -->
	<script src="script.js"></script>
	
	<script src="assets/js/scriptmenu.js"></script>
	<script>
	 function updateDateTime() {
	        var datetimeElement = document.querySelector('.datetime');
	        var now = new Date();
	        var dateOptions = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
	        var timeOptions = { hour: 'numeric', minute: 'numeric', hour12: false }; // Exclure les secondes

	        var dateString = now.toLocaleDateString(undefined, dateOptions);
	        var timeString = now.toLocaleTimeString(undefined, timeOptions);

	        datetimeElement.innerHTML = dateString + ' - ' + timeString;
	    }

	    setInterval(updateDateTime, 1000);
	    updateDateTime();
	</script>
</body>

</html>