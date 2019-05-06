<%@page import="qlbhxh.model.Transaction"%>
<%@page import="qlbhxh.model.User"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.SimpleDateFormat" %>
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
					<h4 class="display-4 text-center">Danh sách giao dịch</h4>
				</div>
				<div class="container" style="margin-top:10px;">
					<table class="table table-bordered table-hover">
						<thead class="thead-light">
						<%List<Transaction> listTransaction = (List<Transaction>) request.getAttribute("listTransaction");
						String pattern = "###,###,###";
						DecimalFormat decimalFormat = new DecimalFormat(pattern);
						%>
							<tr class="text-center">
								<th>Mã giao dịch</th>
								<th>Mã BHXH</th>
								<th>Họ và tên</th>
								<th>Đơn vị đóng</th>
								<th>Ngày đóng</th>
								<th>Số tháng đóng</th>
								<th>Số tiền đóng</th>
								<th>Nhân viên giao dịch</th>
							</tr>
						</thead>
						<tbody>
							<%for(Transaction t : listTransaction)
								{
									out.print("<tr>");
									out.print("<td>" + t.getID() + "</td>");
									out.print("<td>" + t.getUser().getInsuranceId() + "</td>");
									out.print("<td>" + t.getUser().getName() + "</td>");
									if(t.getCompany().getCompanyName() != null)
										out.print("<td>" + t.getCompany().getCompanyName() + "</td>");
									else
										out.print("<td>" + "Tự đóng" + "</td>");
									out.print("<td>" + new SimpleDateFormat("dd-MM-yyyy").format(t.getDate()) + "</td>");
									out.print("<td>" + t.getDuration() + "</td>");
									out.print("<td>" + decimalFormat.format(t.getAmount()) + " VND" + "</td>");
									out.print("<td>" + t.getStaff().getName() + "</td>");
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