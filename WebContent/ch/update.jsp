<%@page import="java.util.List"%>
<%@page import="com.coderby.ch.model.PressVO"%>
<%@page import="com.coderby.ch.model.NewsVO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기사 정보 수정</title>
</head>
<body>
<h1>기사 정보 입력</h1>
<form action="/ch/News.do" method="post" >
<%
	NewsVO news = (NewsVO)request.getAttribute("news");
%>
<table>
<tr>
	<td>기사번호</td>
	<td><input type="number" name="newsId" value="<%=news.getNewsId()%>" readonly></td>
</tr>
<tr>
	<td>제목</td>
	<td><input type="text" name="title" value="<%=news.getTitle()%>"></td>
</tr>
<tr>
	<td>신문사</td>
	<% List<PressVO> pressList= (List<PressVO>)request.getAttribute("pressList"); %>
	<td><select name="pressId">
	<% for(PressVO press : pressList){ %>
	<option value="<%=press.getPressId() %>"><%=press.getPressName() %>
	<% } %> <!-- 직무 타이틀을 선택하면 직무ID가 값으로 선택된다. -->
	</select></td>
</tr>
<tr>
	<td>주제</td>
	<td><input type="text" name="topic" value="<%=news.getTopic()%>"></td>
</tr>
<tr>
	<td>기자</td>
	<td><input type="text" name="reporterName" value="<%=news.getReporterName()%>"></td>
</tr>
<tr>
	<td>기사날짜</td>
	<td><input type="date" name="newsDate" value="<%=news.getNewsDate()%>"></td>
</tr>
<tr>
	<td>조회수</td>
	<td><input type="number" name="hits" value=<%=news.getHits() %>></td>
</tr>
<tr>
	<td>댓글수</td>
	<td><input type="number" name="comments" value=<%=news.getComments() %>></td>
</tr>
<tr>
	<td>좋아요수</td>
	<td><input type="number" name="likes" value=<%=news.getLikes() %>></td>
</tr>
<tr>
	<td>링크</td>
	<td><input type="text" name="link" value=<%=news.getLink() %>></td>
</tr>
<tr>
	<td>&nbsp;<input type="hidden" name="cmd" value="update"></td>
	<td><input type="submit" value="저 장"> <input type="reset" value="취 소"></td>
</tr>
</table>
</form>
</body>
</html>