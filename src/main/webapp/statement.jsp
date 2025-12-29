<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.bank.model.*" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<%
DateTimeFormatter fmt =
    DateTimeFormatter.ofPattern("dd MMM yyyy · hh:mm a");
%>

<%
List<Account> accounts = (List<Account>) request.getAttribute("accounts");
List<Transaction> txns = (List<Transaction>) request.getAttribute("transactions");
%>

<!DOCTYPE html>
<html>
<head>
  <title>Statement By Date · MyBank</title>

  <link rel="stylesheet" href="css/design-system.css">
  <link rel="stylesheet" href="css/base.css">
  <link rel="stylesheet" href="css/components.css">
  <link rel="stylesheet" href="css/dashboard.css">
  <link rel="stylesheet" href="css/statement.css">
</head>

<body>

<div class="app">

  <%@ include file="sidebar.jsp" %>

  <main class="content">


	<h1>Full Statement</h1>

<form method="get" action="statement">
  <select name="accountNumber" class="input" required>
    <% for (Account acc : accounts) { %>
      <option value="<%= acc.getAccountnumber() %>">
        <%= acc.getAccountType() %> · <%= acc.getAccountnumber() %>
      </option>
    <% } %>
  </select>
  <button class="btn-primary">View</button>
</form>

<% if (txns != null) { %>
<table class="statement-table">
  <tr>
    <th>Date</th>
    <th>Description</th>
    <th>Credit</th>
    <th>Debit</th>
    <th>Balance</th>
  </tr>

  <% for (Transaction t : txns) { %>
  <tr>
    <td><%= t.getTimestamp().format(fmt) %></td>
    <td><%= t.getDescription() %></td>
    <td><%= "CREDIT".equals(t.getType()) ? t.getAmount() : "-" %></td>
    <td><%= "DEBIT".equals(t.getType()) ? t.getAmount() : "-" %></td>
    <td><%= t.getClosingbalance() %></td>
  </tr>
  <% } %>
</table>
<% } %>

</main>
</div>

</body>
</html>
