<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@	page import="java.util.List" %>
<%@	page import="com.javaex.vo.GuestVo" %>
<%
List<GuestVo> guestList = (List<GuestVo>)request.getAttribute("guestList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/mysite3/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="/mysite3/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>

	<%
    // 전달받은 파라미터 가져오기
    String no = request.getParameter("no");

	String password = request.getParameter("password");
	
    int id = Integer.parseInt(no);
    
	%>
	
	<br><br>
	
	<div id="wrap">

		<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>
		
		<!-- //header -->

		<div id="nav">
			<ul class="clearfix">
				<li><a href="">입사지원서</a></li>
				<li><a href="">게시판</a></li>
				<li><a href="">갤러리</a></li>
				<li><a href="/mysite3/gbc?action=list">방명록</a></li>
			</ul>
		</div>
		<!-- //nav -->

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>방명록</h2>
				<ul>
					<li>일반방명록</li>
					<li>ajax방명록</li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">
			
				<div id="content-head">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->
	
				<div id="guestbook">
					<form action="http://localhost:8080/mysite3/gbc" method="">
						<table id="guestDelete">
							<colgroup>
								<col style="width: 10%;">
								<col style="width: 40%;">
								<col style="width: 25%;">
								<col style="width: 25%;">
							</colgroup>
							<tr>  
								<input type="hidden" name="no" value="<%=no %>">
								<td>비밀번호</td>
								<td><input type="password" name="inputPassword" value=""></td>
								<td class="text-left"><button type="submit">삭제</button></td>
								<td><a href="/mysite3/main">[메인으로 돌아가기]</a></td>
							</tr>
						</table>
						<input type='hidden' name="action" value="delete">
						<input type='hidden' name="password" value="<%=password %>">
					</form>
					
				</div>
				<!-- //guestbook -->
			</div>
			<!-- //content  -->

		</div>
		
		<!-- //container  -->
		
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
		<!-- //footer -->

	</div>
	<!-- //wrap -->
	
</body>
</html>