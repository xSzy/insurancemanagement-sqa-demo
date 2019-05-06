<%@page import="qlbhxh.model.Insurance"%>
<%@page import="qlbhxh.model.User"%>
<%@page import="qlbhxh.model.UserType"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
  	<title>Hệ thống bảo hiểm xã hội Việt Nam</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/QLBHXH/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
	<link href="/QLBHXH/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="/QLBHXH/resources/bootstrap/css/simple-sidebar.css" rel="stylesheet">
	<!-- <link href="/resources/font-awesome/css/all.min.css" rel="stylesheet"> -->
  </head>
<body>
	<div class="card">
		<!-- header -->
		<%@include file="common/header.jsp"%>
		
		<!-- body -->
		<div class="container-fluid row card-body">
			<!-- menu -->
			<%@include file="common/navbar.jsp"%>
			
			<!-- main page -->
			<div class="container col-sm-10" style="min-height:400px;">
				<div class="container-fluid">
					<h4 class="display-4 text-center">Danh sách người dùng</h4>
				</div>
				<div class="container" style="margin-top:10px;">
					<table class="table table-bordered table-hover">
						<thead class="thead-light">
						<%List<Insurance> listDebt = (List<Insurance>) request.getAttribute("listDebt");
						%>
							<tr class="text-center">
								<th>Mã BHXH</th>
								<th>Họ và tên</th>
								<th>Địa chỉ</th>
								<th>Ngày sinh</th>
								<th>Giới tính</th>
								<th>Diện người dùng</th>
								<th>Ngày hết hạn</th>
								<th>Số tháng nợ</th>
							</tr>
						</thead>
						<tbody>
							<%for(Insurance i : listDebt)
								{
									User u = i.getUser();
									out.print("<tr>");
									out.print("<td>" + u.getInsuranceId() + "</td>");
									out.print("<td>" + u.getName() + "</td>");
									out.print("<td>" + u.getAddress() + "</td>");
									out.print("<td>" + new SimpleDateFormat("dd-MM-yyyy").format(u.getDob()) + "</td>");
									out.print("<td>" + (u.getSex() == true ? "Nam" : "Nữ") + "</td>");
									out.print("<td>" + u.getType().getName() + "</td>");
									out.print("<td>" + new SimpleDateFormat("dd-MM-yyyy").format(i.getDateEnd()) + "</td>");
									out.print("<td>" + i.getDaysInDebt()/30 + "</td>");
									out.print("</tr>");
								}%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		
		<!-- footer -->
		<%@include file="common/footer.jsp"%>
		
	</div>
	
</body>
</html>