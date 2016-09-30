<%@page import="pack.business.DataDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="processDao" class="pack.business.ProcessDao" />
<%ArrayList<DataDto> list = (ArrayList<DataDto>)processDao.selectdataAll();%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<table>
	<tr><th>코드</th><th>품명</th><th>수량</th><th>단가</th></tr>
	<c:forEach var="s" items="<%=list %>">
		<tr>
			<td>${s.code }</td>
			<td>${s.sang }</td>
			<td>${s.su }</td>
			<td>${s.dan }</td>
		</tr>
	</c:forEach>
</table>

</head>
<body>
</body>
</html>