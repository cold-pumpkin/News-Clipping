<%@page import="com.coderby.ch.model.PressVO"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기자 정보 입력</title>
</head>
<body>
<h1>기자 정보 입력</h1>
<form action="/ch/News.do" method="post" >
<table>
<tr>
	<td>기자번호</td>
	<td><input type="number" name="reporterId"></td>
</tr>
<tr>
	<td>기자이름</td>
	<td><input type="text" name="title"></td>
</tr>
<tr>
	<td>이메일</td>
	<td><input type="text" name="email"></td>
</tr>
<tr>
	<td>신문사</td>
	<% List<PressVO> pressList= (List<PressVO>)request.getAttribute("pressList"); %>
	<td><select name="pressId">
	<% for(PressVO press : pressList){ %>
	<option value="<%=press.getPressId() %>"><%=press.getPressName() %>
	<% } %> 
	</select></td>
</tr>


<tr>
	<td>&nbsp;<input type="hidden" name="cmd" value="repinsert"></td>
	<td><input type="submit" value="저 장"> <input type="reset" value="취 소"></td>
</tr>
</table>
</form>
</body>
</html>