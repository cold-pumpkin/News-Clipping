<%@page import="com.coderby.ch.model.RepVO"%>
<%@page import="java.util.List"%>
<%@page import="com.coderby.ch.model.PressVO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기사 정보 입력</title>
</head>
<body>
<h1>기사 정보 입력</h1>
<form action="/ch/News.do" method="post" >
<table>
<tr>
	<td>기사번호</td>
	<td><input type="number" name="newsId"></td>
</tr>
<tr>
	<td>제목</td>
	<td><input type="text" name="title"></td>
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
	<td>주제</td>
	<td><input type="text" name="topic"></td>
</tr>
<tr>
	<td>기자</td>
	<td><input type="text" name="reporterName"></td>
	<td>
</tr>
<tr>
	<td>기사날짜</td>
	<td><input type="date" name="newsDate"></td>
</tr>
<tr>
	<td>조회수</td>
	<td><input type="number" name="hits">></td>
</tr>
<tr>
	<td>댓글수</td>
	<td><input type="number" name="comments"></td>
</tr>
<tr>
	<td>좋아요수</td>
	<td><input type="number" name="likes"></td>
</tr>
<tr>
	<td>링크</td>
	<td><input type="text" name="link"></td>
</tr>

<tr>
	<td>&nbsp;<input type="hidden" name="cmd" value="insert"></td>
	<td><input type="submit" value="저 장"> <input type="reset" value="취 소"></td>
</tr>
</table>
</form>
</body>
</html>