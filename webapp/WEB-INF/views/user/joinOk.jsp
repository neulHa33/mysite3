<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="/mysite3/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="/mysite3/assets/css/user.css" rel="stylesheet" type="text/css">
</head>

<body>
	<div id="wrap">

		<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>
		<!-- //header -->

		<div id="nav">
			<ul class="clearfix">
				<li><a href="">�Ի�������</a></li>
				<li><a href="">�Խ���</a></li>
				<li><a href="">������</a></li>
				<li><a href="/mysite3/gbc?action=list">����</a></li>
			</ul>
		</div>
		<!-- //nav -->

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>ȸ��</h2>
				<ul>
					<li>ȸ������</li>
					<li>�α���</li>
					<li>ȸ������</li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">
			
				<div id="content-head">
					<h3>ȸ������</h3>
					<div id="location">
						<ul>
							<li>Ȩ</li>
							<li>ȸ��</li>
							<li class="last">ȸ������</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->
	
				<div id="user">
					<div id="joinOK">
					
						<p class="text-large bold">
							ȸ�������� �����մϴ�.<br>
							<br>
							<a href="/mysite3/user?action=loginForm" >[�α����ϱ�]</a>
						</p>
							
					</div>
					<!-- //joinOK -->
				</div>
				<!-- //user -->
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