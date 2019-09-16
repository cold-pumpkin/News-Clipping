<%@page import="java.util.List"%>
<%@page import="com.coderby.ch.model.PressVO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기사 조건 조회</title>
</head>
<body>
<table>
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
	<td><input type="radio" name="count" id="radio_h"><label for="radio_h">조회수</label>
		<input type="radio" name="count" id="radio_c"><label for="radio_c">댓글수</label>
		<input type="radio" name="count" id="radio_l"><label for="radio_l">좋아요수</label>
	</td>

</tr>
<tr>
	<td>&nbsp;<input type="hidden" name="cmd" value="search"></td>
	<td><input type="submit" value="저 장"> <input type="reset" value="취 소"></td>

</tr>
</table>





</body>
</html>