<%@page import="qlbhxh.model.Staff"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div class="container-fluid row"
	style="background: url(/QLBHXH/resources/images/nen_bg.png) center center">
	<div class="container col-sm-8">
		<a href="/QLBHXH/index" class="text-light"> <img
			src="/QLBHXH/resources/images/logo.svg"
			style="width: auto; height: 75px; margin-top: 10px; margin-bottom: 10px;"></img>
			<img src="/QLBHXH/resources/images/banner_chu.svg"
			style="width: auto; height: 75px; margin-top: 10px; margin-bottom: 10px;"></img>
		</a>
	</div>
	<div class="container col-sm-2 text-weight-bold text-center"
		style="margin-top: 10px; margin-bottom: 10px;">
		<p style="margin-top:20px;">
		<%Staff staff = (Staff) session.getAttribute("staff");
		if(staff != null)
			out.print("Xin chào, " + staff.getName() + "!");
		else
			out.print("Xin chào, " + "Khách" + "!");%>
		</p>
	</div>
	<div class="container col-sm-2 text-weight-bold text-center"
		style="margin-top: 10px; margin-bottom: 10px; padding-top: 20px;">
		<%if(staff != null)
			out.print("<a href=\"/QLBHXH/logout\" style=\"margin-top:20px;\" >Đăng xuất</a>");
		else
			out.print("<a href=\"/QLBHXH/login\" style=\"margin-top:20px;\" >Đăng nhập</a>");%>
	</div>
</div>