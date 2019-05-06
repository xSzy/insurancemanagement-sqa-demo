<%@page import="qlbhxh.model.Fund"%>
<%@page import="qlbhxh.model.InsuranceConfig"%>

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
			<%InsuranceConfig cfg = (InsuranceConfig) request.getAttribute("config");
			Fund fund = (Fund) request.getAttribute("fund");
			%>
			<div class="container col-sm-10" style="min-height:400px;">
				<%Boolean result = (Boolean) request.getAttribute("result");
				if(result != null)
				{
					if(result == true)
					{
						%>
						<div class="alert alert-success" style="margin-top:10px">
							Cấu hình mới đã được lưu thành công!
						</div>
						<%
					}
					else
					{
						%>
						<div class="alert alert-danger" style="margin-top:10px">
							Đã có lỗi xảy ra trong quá trình lưu cấu hình, xin hãy thử lại sau!
						</div>
						<%
					}
				}%>
				
				<div class="card" style="margin-top:10px">
					<div class="card-header text-center">
						<h5 class="card-title">Cấu hình</h5>
					</div>
					<div class="card-body">
						<form action="/QLBHXH/saveConfig" onsubmit="return validate();">
							<div class="card-deck">
							
								<div class="card bg-light">
									<div class="card-header text-center">
										<label class="card-text">Chung </label>
									</div>
									<div class="card-body">
										<div class="form-group">
											<label for="txtinterest" class="card-text">Lãi suất tới hạn (%)</label>
											<input type="text" maxlength="5" oninvalid="this.setCustomValidity('Xin hãy điền vào ô này')" oninput="setCustomValidity('')" id="txtinterest" name="interestRate" class="form-control" required value="<%=cfg.getInterestRate() %>">
											</input>
										</div>
										<div class="form-group">
											<label for="txtmaxsal" class="card-text">Mức lương tối thiểu (VND)</label>
											<input type="text" maxlength="10" oninvalid="this.setCustomValidity('Xin hãy điền vào ô này')" oninput="setCustomValidity('')" id="txtminsal" name="minSal" class="form-control" required value="<%=cfg.getMinimumIncome() %>">
											</input>
										</div>
										<div class="form-group">
											<label for="txtminsal" class="card-text">Mức lương tối đa (VND)</label>
											<input type="text" maxlength="10" oninvalid="this.setCustomValidity('Xin hãy điền vào ô này')" oninput="setCustomValidity('')" id="txtmaxsal" name="maxSal" class="form-control" required value="<%=cfg.getMaximumIncome() %>">
											</input>
										</div>
									</div>
								</div>
								
								<div class="card bg-light">
									<div class="card-header text-center">
										<label class="card-text">Quỹ ốm đau và thai sản </label>
									</div>
									<div class="card-body">
										<div class="form-group">
											<label for="txtODTSrequired" class="card-text">Mức % lương đối với người lao động bắt buộc</label>
											<input type="text" maxlength="6" oninvalid="this.setCustomValidity('Xin hãy điền vào ô này')" oninput="setCustomValidity('')" id="txtODTSrequired" name="ODTSrequired" class="form-control" required value="<%=fund.getRequiredODTS() %>">
											
											</input>
										</div>
										<div class="form-group">
											<label for="txtODTScompany" class="card-text">Mức % lương đối với doanh nghiệp</label>
											<input type="text" maxlength="6" oninvalid="this.setCustomValidity('Xin hãy điền vào ô này')" oninput="setCustomValidity('')" id="txtODTScompany" name="ODTScompany" class="form-control" required value="<%=fund.getCompanyODTS() %>">
											</input>
										</div>
										<div class="form-group">
											<label for="txtODTSwilling" class="card-text">Mức % lương đối với người lao động tự nguyện</label>
											<input type="text" maxlength="6" oninvalid="this.setCustomValidity('Xin hãy điền vào ô này')" oninput="setCustomValidity('')" id="txtODTSwilling" name="ODTSwilling" class="form-control" required value="<%=fund.getWillingODTS() %>">
											</input>
										</div>
									</div>
								</div>
							</div>
							
							<div class="card-deck" style="margin-top: 25px;margin-bottom: 25px;">
								<div class="card bg-light">
									<div class="card-header text-center">
										<label class="card-text">Quỹ tai nạn lao động, bệnh nghề nghiệp </label>
									</div>
									<div class="card-body">
										<div class="form-group">
											<label for="txtTNLDrequired" class="card-text">Mức % lương đối với người lao động bắt buộc</label>
											<input type="text" maxlength="6" oninvalid="this.setCustomValidity('Xin hãy điền vào ô này')" oninput="setCustomValidity('')" id="txtTNLDrequired" name="TNLDrequired" class="form-control" required value="<%=fund.getRequiredTNLD() %>">
											</input>
										</div>
										<div class="form-group">
											<label for="txtTNLDcompany" class="card-text">Mức % lương đối với doanh nghiệp</label>
											<input type="text" maxlength="6" oninvalid="this.setCustomValidity('Xin hãy điền vào ô này')" oninput="setCustomValidity('')" id="txtTNLDcompany" name="TNLDcompany" class="form-control" required value="<%=fund.getCompanyTNLD() %>">
											</input>
										</div>
										<div class="form-group">
											<label for="txtTNLDwilling" class="card-text">Mức % lương đối với người lao động tự nguyện</label>
											<input type="text" maxlength="6" oninvalid="this.setCustomValidity('Xin hãy điền vào ô này')" oninput="setCustomValidity('')" id="txtTNLDwilling" name="TNLDwilling" class="form-control" required value="<%=fund.getWillingTNLD() %>">
											</input>
										</div>
									</div>
								</div>
								
								<div class="card bg-light">
									<div class="card-header text-center">
										<label class="card-text">Quỹ hưu trí và tử tuất</label>
									</div>
									<div class="card-body">
										<div class="form-group">
											<label for="txtHTTTrequired" class="card-text">Mức % lương đối với người lao động bắt buộc</label>
											<input type="text" maxlength="6" oninvalid="this.setCustomValidity('Xin hãy điền vào ô này')" oninput="setCustomValidity('')" id="txtHTTTrequired" name="HTTTrequired" class="form-control" required value="<%=fund.getRequiredHTTT() %>">											
											</input>
										</div>
										<div class="form-group">
											<label for="txtHTTTcompany" class="card-text">Mức % lương đối với doanh nghiệp</label>
											<input type="text" maxlength="6" oninvalid="this.setCustomValidity('Xin hãy điền vào ô này')" oninput="setCustomValidity('')" id="txtHTTTcompany" name="HTTTcompany" class="form-control" required value="<%=fund.getCompanyHTTT() %>">
											</input>
										</div>
										<div class="form-group">
											<label for="txtHTTTwilling" class="card-text">Mức % lương đối với người lao động tự nguyện</label>
											<input type="text" maxlength="6" oninvalid="this.setCustomValidity('Xin hãy điền vào ô này')" oninput="setCustomValidity('')" id="txtHTTTwilling" name="HTTTwilling" class="form-control" required value="<%=fund.getWillingHTTT() %>">			
											</input>
										</div>
									</div>
								</div>
								
								
								
							</div>
							
							<button type="submit" class="btn btn-primary" style="display: block; margin : 0 auto">Lưu</button>
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
		var value = document.getElementById("txtinterest").value;
		if(isNaN(value))
		{
			alert("Lãi suất tới hạn phải là giá trị số!");
			document.getElementById("txtinterest").focus();
			return false;
		}
		if(value < 0 || value > 100)
		{
			alert("Lãi suất tới hạn phải có giá trị trong khoảng 0-100!");
			document.getElementById("txtinterest").focus();
			return false;
		}
		value = document.getElementById("txtminsal").value;
		if(isNaN(value))
		{
			alert("Lương tối thiểu phải là giá trị số!");
			document.getElementById("txtminsal").focus();
			return false;
		}
		if(value < 0 || value > 1000000000)
		{
			alert("Lương tối thiểu phải có giá trị trong khoảng 0-1000000000!");
			document.getElementById("txtminsal").focus();
			return false;
		}
		value = document.getElementById("txtmaxsal").value;
		if(isNaN(value))
		{
			alert("Lương tối đa phải là giá trị số!");
			document.getElementById("txtmaxsal").focus();
			return false;
		}
		if(value < 0 || value > 1000000000)
		{
			alert("Lương tối đa phải có giá trị trong khoảng 0-1000000000!");
			document.getElementById("txtmaxsal").focus();
			return false;
		}
		value = document.getElementById("txtODTSrequired").value;
		if(isNaN(value))
		{
			alert("Tỉ lệ đóng quỹ ốm đau, thai sản phải là giá trị số!");
			document.getElementById("txtODTSrequired").focus();
			return false;
		}
		if(value < 0 || value > 100)
		{
			alert("Tỉ lệ đóng quỹ ốm đau, thai sản phải có giá trị trong khoảng 0-100!");
			document.getElementById("txtODTSrequired").focus();
			return false;
		}
		value = document.getElementById("txtODTScompany").value;
		if(isNaN(value))
		{
			alert("Tỉ lệ đóng quỹ ốm đau, thai sản phải là giá trị số!");
			document.getElementById("txtODTScompany").focus();
			return false;
		}
		if(value < 0 || value > 100)
		{
			alert("Tỉ lệ đóng quỹ ốm đau, thai sản phải có giá trị trong khoảng 0-100!");
			document.getElementById("txtODTScompany").focus();
			return false;
		}
		value = document.getElementById("txtODTSwilling").value;
		if(isNaN(value))
		{
			alert("Tỉ lệ đóng quỹ ốm đau, thai sản phải là giá trị số!");
			document.getElementById("txtODTSwilling").focus();
			return false;
		}
		if(value < 0 || value > 100)
		{
			alert("Tỉ lệ đóng quỹ ốm đau, thai sản phải có giá trị trong khoảng 0-100!");
			document.getElementById("txtODTSwilling").focus();
			return false;
		}
		value = document.getElementById("txtTNLDrequired").value;
		if(isNaN(value))
		{
			alert("Tỉ lệ đóng quỹ tai nạn lao động, bệnh nghề nghiệp phải là giá trị số!");
			document.getElementById("txtTNLDrequired").focus();
			return false;
		}
		if(value < 0 || value > 100)
		{
			alert("Tỉ lệ đóng quỹ tai nạn lao động, bệnh nghề nghiệp phải có giá trị trong khoảng 0-100!");
			document.getElementById("txtTNLDrequired").focus();
			return false;
		}
		value = document.getElementById("txtTNLDcompany").value;
		if(isNaN(value))
		{
			alert("Tỉ lệ đóng quỹ tai nạn lao động, bệnh nghề nghiệp phải là giá trị số!");
			document.getElementById("txtTNLDcompany").focus();
			return false;
		}
		if(value < 0 || value > 100)
		{
			alert("Tỉ lệ đóng quỹ tai nạn lao động, bệnh nghề nghiệp phải có giá trị trong khoảng 0-100!");
			document.getElementById("txtTNLDcompany").focus();
			return false;
		}
		value = document.getElementById("txtTNLDwilling").value;
		if(isNaN(value))
		{
			alert("Tỉ lệ đóng quỹ tai nạn lao động, bệnh nghề nghiệp phải là giá trị số!");
			document.getElementById("txtTNLDwilling").focus();
			return false;
		}
		if(value < 0 || value > 100)
		{
			alert("Tỉ lệ đóng quỹ tai nạn lao động, bệnh nghề nghiệp phải có giá trị trong khoảng 0-100!");
			document.getElementById("txtTNLDwilling").focus();
			return false;
		}
		value = document.getElementById("txtHTTTrequired").value;
		if(isNaN(value))
		{
			alert("Tỉ lệ đóng quỹ hưu trí, tử tuất phải là giá trị số!");
			document.getElementById("txtHTTTrequired").focus();
			return false;
		}
		if(value < 0 || value > 100)
		{
			alert("Tỉ lệ đóng quỹ tai nạn lao động, bệnh nghề nghiệp phải có giá trị trong khoảng 0-100!");
			document.getElementById("txtHTTTrequired").focus();
			return false;
		}
		value = document.getElementById("txtHTTTcompany").value;
		if(isNaN(value))
		{
			alert("Tỉ lệ đóng quỹ hưu trí, tử tuất phải là giá trị số!");
			document.getElementById("txtHTTTcompany").focus();
			return false;
		}
		if(value < 0 || value > 100)
		{
			alert("Tỉ lệ đóng quỹ tai nạn lao động, bệnh nghề nghiệp phải có giá trị trong khoảng 0-100!");
			document.getElementById("txtHTTTcompany").focus();
			return false;
		}
		value = document.getElementById("txtHTTTwilling").value;
		if(isNaN(value))
		{
			alert("Tỉ lệ đóng quỹ hưu trí, tử tuất phải là giá trị số!");
			document.getElementById("txtHTTTwilling").focus();
			return false;
		}
		if(value < 0 || value > 100)
		{
			alert("Tỉ lệ đóng quỹ tai nạn lao động, bệnh nghề nghiệp phải có giá trị trong khoảng 0-100!");
			document.getElementById("txtHTTTwilling").focus();
			return false;
		}
	    return confirm('Bạn có chắc chắn muốn thay đổi cấu hình không?');
	}
	</script>
</body>
</html>