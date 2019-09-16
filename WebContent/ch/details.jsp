<%@page import="com.coderby.ch.model.NewsVO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기사 보기</title>
</head>
<h1></h1>
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
<a href="/hr/Emp.do?cmd=update&newsid=<%=news.getNewsId()%>">기사 수정</a>
<a href="/hr/Emp.do?cmd=delete&newsid=<%=news.getNewsId()%>">사원정보 삭제</a>
</body>
</html>