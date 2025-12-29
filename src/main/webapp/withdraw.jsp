<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.bank.model.*" %>

<%
List<Account> accounts =
        (List<Account>) request.getAttribute("accounts");
%>

<!DOCTYPE html>
<html>
<head>
  <title>Withdraw · MyBank</title>

  <link rel="stylesheet" href="css/design-system.css">
  <link rel="stylesheet" href="css/base.css">
  <link rel="stylesheet" href="css/components.css">
  <link rel="stylesheet" href="css/dashboard.css">
  <link rel="stylesheet" href="css/withdraw.css">
</head>

<body>
<div class="app">

  <%@ include file="sidebar.jsp" %>

  <main class="content">
    <h1>Withdraw</h1>
    <p class="muted">Withdraw money from your account</p>

    <div class="alert alert-warning">
      Ensure sufficient balance before withdrawing.
    </div>

    <div id="message"></div>

    <div class="card form-card">
      <form action="withdraw" method="post">

        <label>Select Account</label>
        <select name="accountNumber" class="input" required>
          <% if (accounts != null) {
               for (Account acc : accounts) { %>
            <option value="<%= acc.getAccountnumber() %>">
              <%= acc.getAccountType() %> · <%= acc.getAccountnumber() %>
            </option>
          <% }} %>
        </select>

        <label>Amount</label>
        <input type="number" step="0.01"
               name="amount" class="input" required>

        <button class="btn-primary">Withdraw</button>

      </form>
    </div>
  </main>

</div>

<script src="js/message.js"></script>
</body>
</html>
    