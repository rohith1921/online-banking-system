<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.bank.model.Account" %>

<%
String ctx = request.getContextPath();
List<Account> accounts = (List<Account>) request.getAttribute("accounts");
%>

<!DOCTYPE html>
<html>
<head>
  <title>Admin · Accounts</title>

  <link rel="stylesheet" href="<%= ctx %>/css/design-system.css">
  <link rel="stylesheet" href="<%= ctx %>/css/base.css">
  <link rel="stylesheet" href="<%= ctx %>/css/components.css">
  <link rel="stylesheet" href="<%= ctx %>/css/admin.css">
</head>

<body>

<div class="admin-app">

  <%@ include file="admin-sidebar.jsp" %>

  <main class="admin-content">

    <h1>Accounts</h1>
    <p class="muted">Manage bank accounts</p>

    <div class="card">

      <% if (accounts == null || accounts.isEmpty()) { %>
        <p class="muted">No accounts found.</p>
      <% } else { %>

      <table class="admin-table">
        <tr>
          <th>Account No</th>
          <th>User ID</th>
          <th>Type</th>
          <th>Balance</th>
          <th>Status</th>
          <th>Action</th>
        </tr>

        <% for (Account a : accounts) { %>
        <tr>
          <td><%= a.getAccountnumber() %></td>
          <td><%= a.getUser().getUserid() %></td>
          <td><%= a.getAccountType() %></td>
          <td>₹<%= a.getBalance() %></td>
          <td>
            <span class="status <%= a.getStatus().toLowerCase() %>">
              <%= a.getStatus() %>
            </span>
          </td>
          <td>

            <% if ("PENDING".equals(a.getStatus())) { %>
              <form action="<%= ctx %>/admin/account-action" method="post">
                <input type="hidden" name="accountNumber" value="<%= a.getAccountnumber() %>">
                <input type="hidden" name="action" value="approve">
                <button class="btn-primary btn-sm">Approve</button>
              </form>
            <% } else if ("ACTIVE".equals(a.getStatus())) { %>
              <form action="<%= ctx %>/admin/account-action" method="post">
                <input type="hidden" name="accountNumber" value="<%= a.getAccountnumber() %>">
                <input type="hidden" name="action" value="close">
                <button class="btn-danger btn-sm">Close</button>
              </form>
            <% } else { %>
              —
            <% } %>

          </td>
        </tr>
        <% } %>
      </table>

      <% } %>

    </div>

  </main>

</div>

</body>
</html>
