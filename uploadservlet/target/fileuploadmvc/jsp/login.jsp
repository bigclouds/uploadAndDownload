<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<div align="center">
  <div align="center">
    ${message}
  </div>
  <h2 align="center">欢迎登陆</h2>
  <table border="0">
    <form action="${pageContext.request.contextPath}/login" method="post">
      	<tr>
	  <th align="left">
    		Email:
      	  </th>
	  <th align="left">
    		<input type="text" name="email" placeholder="Email">
      	  </th>
	<tr>
      	<tr>
	  <th align="left">
    		Password:
      	  </th>
	  <th align="left">
		<input type="password" name="password" placeholder="Password">
      	  </th>
      	<tr>
      	<tr>
	  <th align="left">
    		<button>Login</button>
      	  </th>
    </form>
	  <th align="left">
			<a href="forgetme.jsp">Forget Password</a>
	  </th>
      	<tr>
</table>
</div>
</body>
</html>
