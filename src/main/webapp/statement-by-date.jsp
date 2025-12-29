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
List<Transaction> transactions =
        (List<Transaction>) request.getAttribute("transactions");
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

    <h1>Statement By Date</h1>
    <p class="muted">Filter transactions by date range</p>

    <!-- Filter Form -->
    <form method="post" action="statement-by-date" class="filter-form">

      <label>Select Account</label>
      <select name="accountNumber" class="input" required>
        <% if (accounts != null) {
             for (Account acc : accounts) { %>
          <option value="<%= acc.getAccountnumber() %>">
            <%= acc.getAccountType() %> · <%= acc.getAccountnumber() %>
          </option>
        <% }} %>
      </select>

      <label>Start Date</label>
      <input type="date" name="startDate" class="input" required>

      <label>End Date</label>
      <input type="date" name="endDate" class="input" required>

      <button class="btn-primary">View Statement</button>
    </form>

    <!-- Results -->
    <% if (transactions != null) { %>

      <% if (transactions.isEmpty()) { %>
        <p class="muted">No transactions found for this date range.</p>
      <% } else { %>

        <table class="statement-table">
          <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Credit</th>
            <th>Debit</th>
            <th>Balance</th>
          </tr>

          <% for (Transaction t : transactions) { %>
          <tr>
            <td><%= t.getTimestamp().format(fmt) %></td>
            <td><%= t.getDescription() %></td>
            <td>
              <%= "CREDIT".equals(t.getType()) ? t.getAmount() : "-" %>
            </td>
            <td>
              <%= "DEBIT".equals(t.getType()) ? t.getAmount() : "-" %>
            </td>
            <td>₹<%= t.getClosingbalance() %></td>
          </tr>
          <% } %>
        </table>

      <% } %>
    <% } %>

  </main>
</div>

</body>
</html>
