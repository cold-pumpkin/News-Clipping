<%@page import="java.util.List"%>
<%@page import="com.coderby.ch.model.NewsVO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>기사 목록</h1>
<table border="1">
<tr>
	<th>기사번호</th>
	<th>신문사</th>
	<th>주제</th>
	<th>헤드라인</th>
	<th>기자</th>
	<th>기사날짜</th>
	<th>조회수</th>
	<th>댓글수</th>
	<th>좋아요수</th>
	<th>링크</th>
</tr>
<%
	List<NewsVO> newsList = (List<NewsVO>)request.getAttribute("newsList");
	for(NewsVO news : newsList){   
%>
<tr align=left>
	<td><nobr><a href="/ch/News.do?cmd=details&newsid=<%=news.getNewsId()%>">
	<%= news.getTitle() %></a></nobr></td><!--  사원번호를 클릭하면 상세 조회가 되도록 -->
	<td><nobr><%= news.getNewsId() %></nobr></td>
	<td><nobr><%= news.getPressName() %></nobr></td>
	<td><nobr><%= news.getTopic() %></nobr></td>
	<td><nobr><%= news.getReporterName() %></nobr></td>
	<td><nobr><%= news.getNewsDate() %></nobr></td>
	<td><nobr><%= news.getHits() %></nobr></td>
	<td><nobr><%= news.getComments() %></nobr></td>
	<td><nobr><%= news.getLikes() %></nobr></td>
	<td><nobr><%= news.getLink() %></nobr></td>
<%
	}
%>
</table>
</body>
</html>