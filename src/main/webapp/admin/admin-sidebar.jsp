<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<aside class="admin-sidebar">

  <h2 class="logo">MyBank Admin</h2>

  <nav>
    <a href="<%= ctx %>/admin/dashboard">Dashboard</a>
<a href="<%= ctx %>/admin/users">Users</a>
<a href="<%= ctx %>/admin/accounts">Accounts</a>
<a href="<%= ctx %>/admin/transactions">Transactions</a>
<a href="<%= ctx %>/admin/logout" class="danger">Logout</a>

  </nav>

</aside>
	
</body>
</html>