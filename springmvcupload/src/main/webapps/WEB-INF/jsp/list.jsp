<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
	<h1>File List</h1>
	<c:forEach items="${filelist}" var="f">
		<a href="${f}">${f}</a>
		<br />
	</c:forEach>
<br><h1> click to download files: </h1><br>
<table>
	<c:forEach items="${map}" var="tpermiss">     
	    <tr> 
		    <td><a href="download.do?filename=${tpermiss.key}">${tpermiss.key}</a></td>
		    <td>${tpermiss.value}</td>  
	    </tr>
	</c:forEach>  
</table>
</body>
<html>
