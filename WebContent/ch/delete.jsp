<%@page import="com.coderby.ch.model.NewsVO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기사 삭제</title>
</head>
<body>
<h1>기사를 삭제합니다.</h1>
<%
	NewsVO news = (NewsVO)request.getAttribute("news");
%>
<ul>
	<li>신문ID : <%= news.getNewsId() %>
	<li>타이틀 : <%= news.getTitle() %>
	<li>신문사 : <%=news.getPressName() %>
	<li>주제 : <%=news.getTopic() %>
	<li>기자 : <%=news.getReporterName() %>
	<li>기사날짜 : <%=news.getNewsDate() %>
	<li>조회수 : <%=news.getHits()%>
	<li>댓글수 : <%= news.getComments() %>
	<li>좋아요수 : <%= news.getLikes() %>
	<li>링크: <%= news.getLink() %>
</ul>	 
<h2>이 기사의 정보를 삭제하겠습니까?</h2>
<form action="/ch/News.do" method="post">
<input type="hidden" name="newsid" value="<%=news.getNewsId() %>">
<input type="hidden" name="cmd" value="delete">
<input type="submit" value="삭  제">
<input type="reset" value="취  소">
</form>
</body>
</html>