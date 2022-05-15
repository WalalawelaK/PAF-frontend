<%@page import="model.PowerDetails"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	// SAVE
	if (request.getParameter("pdCusName") != null) {	
		
		PowerDetails powerdetails = new PowerDetails();
		String statusMsg = "";
		
		String cusname = request.getParameter("pdCusName");
		String accno = request.getParameter("pdAccNo");
		String unit = request.getParameter("pdUnit");
		String date = request.getParameter("pdDate");
		String pay = request.getParameter("pdPay");
		
		if (request.getParameter("hidpdIDSave") == "") {
			statusMsg = powerdetails.insertPowerDetails(cusname, accno, unit, date,pay);
		} else {
			statusMsg = powerdetails.updatePowerDetails(request.getParameter("hidpdIDSave"),cusname, accno, unit, date, pay);
		}
		
		
		session.setAttribute("statusMsg", statusMsg);
	}

	// DELETE
	if (request.getParameter("hidpdIDDelete") != null) {
		PowerDetails powerdetails = new PowerDetails();
		String statusMsg = powerdetails.deletePowerDetails(request.getParameter("hidpdIDDelete"));
		session.setAttribute("statusMsg", statusMsg);
	}
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<script src="Components/jquery-3.2.1.min.js"></script>
        <script src="Components/items.js"></script>
		<title>Items Management</title>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col">
					<h1>Items Management</h1>
					<form id="formPowerDetails" accno="formPowerDetails" method="POST" action="items.jsp">
						PowerDetails cusname: 
						<input 
							id="pdCusName" 
							name="pdCusName" 
							type="text" 
							class="form-control form-control-sm"
						><br>

						PowerDetails accno: 
						<input 
							id="pdAccNo"
							name="pdAccNo" 
							type="text" 
							class="form-control form-control-sm"
						><br>

						PowerDetails unit: 
						<input 
							id="pdUnit" 
							name="pdUnit" 
							type="text" 
							class="form-control form-control-sm"
						><br>

						PowerDetails description: 
						<input 
							id="pdDate" 
							name="pdDate" 
							type="text" 
							class="form-control form-control-sm"
						><br>
						
						PowerDetails pay: 
						<input 
							id="pdPay" 
							name="pdPay" 
							type="text" 
							class="form-control form-control-sm"
						><br>

						<input 
							id="btnSave" 
							name="btnSave" 
							type="button" 
							value="Save" 
							class="btn btn-primary"
						>

						<input type="hidden" name="hidpdIDSave" id="hidpdIDSave" value="">
					</form>
				
					<br>
					<div id="alertSuccess" class="alert alert-success">
						<% out.print(session.getAttribute("statusMsg")); %>
					</div>
					<div id="alertError" class="alert alert-danger"></div>
					<br>
					<%
						PowerDetails powerdetails = new PowerDetails(); 
						out.print(powerdetails.readPowerDetails());
					%>
				</div>
			</div>
		</div>
	</body>
</html>