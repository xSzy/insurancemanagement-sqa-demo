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
				<div class="card-deck">
					<div class="card bg-light">
						<div class="card-header text-center">
							<h5 class="card-title">Danh sách người dùng</h5>
						</div>
						<div class="card-body">
							<form action="/QLBHXH/userlist" method="GET">
								<div class="form-group">
									<label for="namenumber" class="card-text">Loại bảo hiểm: </label>
									<select id="namenumber" class="form-control" name="insuranceType">
										<option value="required">Bắt buộc</option>
										<option value="willing">Tự nguyện</option>
									</select>
								</div>
								<button type="submit" class="btn btn-primary" style="display: block; margin : 0 auto">Hiện danh sách</button>
							</form>
						</div>
					</div>
					
					<div class="card bg-light">
						<div class="card-header text-center">
							<h5 class="card-title">Danh sách giao dịch</h5>
						</div>
						<div class="card-body">
							<form action="/QLBHXH/transactionlist" method="GET">
								<div class="form-group">
									<label for="timelength" class="card-text">Thời gian: </label>
									<select id="timelength" class="form-control" name="days">
										<option value="0">Trong ngày</option>
										<option value="6">1 tuần</option>
										<option value="29">1 tháng</option>
									</select>
								</div>
								<button type="submit" class="btn btn-primary" style="display: block; margin : 0 auto">Hiện danh sách</button>
							</form>
						</div>
					</div>
					
					<div class="card bg-light">
						<div class="card-header text-center">
							<h5 class="card-title">Danh sách nợ</h5>
						</div>
						<div class="card-body">
							<form action="/QLBHXH/debtlist" method="GET">
								<div class="form-group">
									<label for="debt" class="card-text">Thời gian nợ: </label>
									<select id="debt" class="form-control" name="daysInDebt">
										<option value="29">1 tháng</option>
										<option value="179">6 tháng</option>
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
		</div>
		
		<!-- footer -->
		<%@include file="common/footer.jsp"%>
		
	</div>
	
</body>
</html>