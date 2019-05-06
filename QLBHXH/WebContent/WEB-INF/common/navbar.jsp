<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div class="container col-sm-2" style="margin-top: 10px;">
	<%if(session.getAttribute("staff") != null)
		{
		%>
		<ul class="nav flex-column nav-tabs" role="tablist">
			<li class="nav-item"><a class="nav-link" href="/QLBHXH/list">Xem danh
					sách</a></li>
			<li class="nav-item"><a class="nav-link" href="/QLBHXH/reports">Xuất báo
					cáo</a></li>
			<li class="nav-item"><a class="nav-link" href="/QLBHXH/configuration">Cấu hình</a></li>
		</ul>
		<%}%>
	
</div>