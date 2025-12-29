<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String ctx = request.getContextPath();
%>


<!DOCTYPE html>
<html>
<head>
  <title>Admin Dashboard · MyBank</title>

  <link rel="stylesheet" href="<%= ctx %>/css/design-system.css">
<link rel="stylesheet" href="<%= ctx %>/css/base.css">
<link rel="stylesheet" href="<%= ctx %>/css/components.css">
<link rel="stylesheet" href="<%= ctx %>/css/admin.css">

</head>

<body>

<div class="admin-app">

  <%@ include file="admin-sidebar.jsp" %>

  <main class="admin-content">

    <h1>Admin Dashboard</h1>
    <p class="muted">System overview</p>

    <div class="admin-cards">

      <div class="card admin-card">
        <h3>Total Users</h3>
        <p class="admin-metric">${totalUsers}</p>
      </div>

      <div class="card admin-card">
        <h3>Total Accounts</h3>
        <p class="admin-metric">${totalAccounts}</p>
      </div>

      <div class="card admin-card">
        <h3>Pending Accounts</h3>
        <p class="admin-metric warning">${pendingAccounts}</p>
      </div>

      <div class="card admin-card">
        <h3>Total Transactions</h3>
        <p class="admin-metric">${totalTransactions}</p>
      </div>

    </div>

  </main>

</div>

</body>
</html>
