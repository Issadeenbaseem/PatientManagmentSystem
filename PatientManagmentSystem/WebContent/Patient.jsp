
<%@ page import="model.PatientModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	//Insert paitent----------------------------------
	if (request.getParameter("patientEmail") != null) {
		PatientModel patientObj = new PatientModel();
		String stsMsg = patientObj.insert(request.getParameter("patientEmail"), request.getParameter("patientName"),
				request.getParameter("patientAge"), request.getParameter("patientdis"),request.getParameter("patientAdds"), request.getParameter("patientgen"));
		session.setAttribute("statusMsg", stsMsg);
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Patient Management</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://bootswatch.com/4/sketchy/bootstrap.min.css">
</head>
<body>
	<center>
		<h1>Patient Management</h1>
		<form method="post" action="Patient.jsp">
			Patient Email: <input name="patientEmail" type="text"><br>
			Patient Name: <input name="patientName" type="text"><br>
			Patient Age:<input name="patientAge" type="text"><br>
			
			Patient Disease: <input name="patientdis" type="text"><br>
			Patient Address: <input name="patientAdds" type="text"><br>
			Patient Gender: <input name="patientgen" type="text"><br>


			<input name="btnSubmit" type="submit" value="Save">
		</form>
		<%
			out.print(session.getAttribute("statusMsg"));
		%>
		<br>
		<%
			PatientModel patientObj = new PatientModel();
			out.print(patientObj.patientItems());
		%>
	</center>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>
</body>
</html>