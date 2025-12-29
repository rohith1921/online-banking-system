<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.time.format.DateTimeFormatter" %>
<%@ page import="com.bank.model.Transaction" %>

<%
String ctx = request.getContextPath();
List<Transaction> txns = (List<Transaction>) request.getAttribute("transactions");

DateTimeFormatter fmt =
    DateTimeFormatter.ofPattern("dd MMM yyyy · hh:mm a");
%>

<!DOCTYPE html>
<html>
<head>
  <title>Admin · Transactions</title>

  <link rel="stylesheet" href="<%= ctx %>/css/design-system.css">
  <link rel="stylesheet" href="<%= ctx %>/css/base.css">
  <link rel="stylesheet" href="<%= ctx %>/css/components.css">
  <link rel="stylesheet" href="<%= ctx %>/css/admin.css">
</head>

<body>

<div class="admin-app">

  <%@ include file="admin-sidebar.jsp" %>

  <main class="admin-content">

    <h1>Transactions</h1>
    <p class="muted">Monitor all system transactions</p>

    <!-- FILTER -->
    <div class="card filter-card">
      <form method="get" action="<%= ctx %>/admin/transactions">

        <input class="input"
               type="number"
               name="accountNumber"
               placeholder="Account number"
               value="<%= request.getAttribute("accountNumber") != null ?
                         request.getAttribute("accountNumber") : "" %>">

        <input class="input" type="date" name="from"
               value="<%= request.getAttribute("from") != null ?
                         request.getAttribute("from") : "" %>">

        <input class="input" type="date" name="to"
               value="<%= request.getAttribute("to") != null ?
                         request.getAttribute("to") : "" %>">

        <button class="btn-primary">Filter</button>
      </form>
    </div>

    <!-- TABLE -->
    <div class="card">

      <% if (txns == null || txns.isEmpty()) { %>
        <p class="muted">No transactions found.</p>
      <% } else { %>

      <table class="admin-table">
        <tr>
          <th>ID</th>
          <th>Date</th>
          <th>Account</th>
          <th>Type</th>
          <th>Amount</th>
          <th>Balance</th>
        </tr>

        <% for (Transaction t : txns) { %>
        <tr>
          <td><%= t.getTxnid() %></td>
          <td><%= t.getTimestamp().format(fmt) %></td>
          <td><%= t.getAccount().getAccountnumber() %></td>
          <td>
            <span class="status <%= t.getType().toLowerCase() %>">
              <%= t.getType() %>
            </span>
          </td>
          <td>₹<%= t.getAmount() %></td>
          <td>₹<%= t.getClosingbalance() %></td>
        </tr>
        <% } %>

      </table>

      <% } %>

    </div>

  </main>

</div>

</body>
</html>
