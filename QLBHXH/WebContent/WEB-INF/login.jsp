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
  	
  	<div class="container-fluid row">
  	
	  	<div class="container col-sm-4">
	  		<h2 class="display-4" style="text-align:center">Đăng nhập</h2>
	  		<form action="/QLBHXH/doLogin" method="post">
		  		<div class="form-group">
			  		<label for="usr">Tên tài khoản: </label>
			  		<input type="text" class="form-control" id="usr" name="username" oninvalid="this.setCustomValidity('Xin hãy điền vào ô này')" oninput="setCustomValidity('')" required autofocus>
			  	</div>
			  	<div class="form-group">
			  		<label for="pwd">Mật khẩu: </label>
			  		<input type="password" class="form-control" id="pwd" name="password" oninvalid="this.setCustomValidity('Xin hãy điền vào ô này')" oninput="setCustomValidity('')" required>
			  	</div>
			  	<button type="submit" style="display: block; margin : 0 auto" class="btn btn-primary">Đăng nhập</button>
	  		</form>
	  		<%String errorMsg = (String) request.getAttribute("errorMsg");
	  			if(errorMsg != null)
	  			{
	  				%>
	  				<div class="alert alert-danger" style="margin-top:20px">
	  					<%=errorMsg %>
	  				</div>
	  				<%
	  			}
	  			%>
	  	</div>
  	</div>
  </body>
</html>