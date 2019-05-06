<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
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
				<div class="card bg-light" style="margin-top:10px">
					<div class="card-header text-center">
						<h5 class="card-title">Báo cáo tình trạng đóng BHXH</h5>
					</div>
					<div class="card-body">
						<form action="/QLBHXH/generalreportexport">
							<button type="submit" class="btn btn-primary" style="display: block; margin : 0 auto">Xuất báo cáo</button>
						</form>
					</div>
				</div>
				
				<div class="card bg-light" style="margin-top:10px">
					<div class="card-header text-center">
						<h5 class="card-title">Báo cáo lịch sử đóng BHXH của người dùng</h5>
					</div>
					<div class="card-body">
					<%String errorMsg = (String) request.getAttribute("msg");
					String insuranceId = (String) request.getAttribute("insuranceId");
		  			if(errorMsg != null && errorMsg.equals("User not found"))
		  			{
		  				%>
		  				<div class="alert alert-danger" style="margin-top:20px">
		  					Không tìm thấy người dùng có mã BHXH <%=insuranceId %>!
		  				</div>
		  				<%
		  			}
		  			else if(errorMsg != null && errorMsg.equals("No transaction found"))
		  			{
		  				%>
		  				<div class="alert alert-danger" style="margin-top:20px">
		  					Người dùng có mã BHXH <%=insuranceId %> chưa có giao dịch nào!
		  				</div>
		  				<%
		  			}
		  			%>
						<form action="/QLBHXH/transactionlistexport" onsubmit="return validate();">
							<div class="form-group">
								<label for="inscode" class="card-text">Nhập mã bảo hiểm cần tra: </label>
								<input type="text" class="form-control" oninvalid="this.setCustomValidity('Xin hãy điền vào ô này')" oninput="setCustomValidity('')" id="inscode" name="insuranceId" required></input>
							</div>
							<button type="submit" class="btn btn-primary" style="display: block; margin : 0 auto">Xuất báo cáo</button>
						</form>
					</div>
				</div>
				
				<div class="card bg-light" style="margin-top:10px">
					<div class="card-header text-center">
						<h5 class="card-title">Báo cáo danh sách nợ</h5>
					</div>
					<div class="card-body">
						<form action="/QLBHXH/debtlistexport">
							<div class="form-group">
								<label for="namenumber" class="card-text">Thời gian nợ: </label>
								<select id="namenumber" class="form-control" name="daysInDebt">
									<option value="29">1 tháng</option>
									<option value="59">6 tháng</option>
									<option value="364">1 năm</option>
									<option value="0">Tất cả</option>
								</select>
							</div>
							<button type="submit" class="btn btn-primary" style="display: block; margin : 0 auto">Hiện danh sách</button>
						</form>
					</div>
				</div>
				
			</div>
		</div>
		
		<!-- footer -->
		<%@include file="common/footer.jsp"%>
		
	</div>
	
	<script>
	function validate()
	{
		var value = document.getElementById("inscode").value;
		if(value.length != 10)
		{
			alert("Mã BHXH có độ dài 10 kí tự!");
			return false;
		}
		if(isNaN(value))
		{
			alert("Mã BHXH chỉ gồm các số từ 0-9!");
			return false;
		}
		return true;
	}
	</script>
</body>
</html>