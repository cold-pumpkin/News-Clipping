<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>error page</title>
</head>
<body>
<h1>에러 발생</h1>
<h2><%= request.getAttribute("message") %></h2>
</body>
</html>