<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="static/js/jquery/jquery-3.2.1.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<h1>Login Page</h1>
	<form action="login" method="post">
		Username:<input type="text" name="username" /> <br />
		<br /> Password:<input type="password" name="password" /> <br />
		<br />
		<%
			if (session.getAttribute("message") != null) {
				out.println(session.getAttribute("message"));
			}
		%>
		<input type="submit" value="login" />
	</form>

</body>
</html>