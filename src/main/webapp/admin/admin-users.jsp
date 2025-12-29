<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.bank.model.User" %>

<%
String ctx = request.getContextPath();
List<User> users = (List<User>) request.getAttribute("users");
%>

<!DOCTYPE html>
<html>
<head>
  <title>Admin · Users</title>

  <link rel="stylesheet" href="<%= ctx %>/css/design-system.css">
  <link rel="stylesheet" href="<%= ctx %>/css/base.css">
  <link rel="stylesheet" href="<%= ctx %>/css/components.css">
  <link rel="stylesheet" href="<%= ctx %>/css/admin.css">
</head>

<body>

<div class="admin-app">

  <%@ include file="admin-sidebar.jsp" %>

  <main class="admin-content">

    <h1>Users</h1>
    <p class="muted">All registered users</p>

    <div class="card">

      <% if (users == null || users.isEmpty()) { %>
        <p class="muted">No users found.</p>
      <% } else { %>

        <table class="admin-table">
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Mobile</th>
            <th>Created</th>
          </tr>

          <% for (User u : users) { %>
          <tr>
            <td><%= u.getUserid() %></td>
            <td><%= u.getName() %></td>
            <td><%= u.getEmail() %></td>
            <td><%= u.getMobilenumber() %></td>
            <td><%= u.getCreatedAt() %></td>
          </tr>
          <% } %>
        </table>

      <% } %>

    </div>

  </main>

</div>

</body>
</html>
