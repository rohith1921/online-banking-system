<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.bank.model.*" %>

<%
List<Account> accounts =
        (List<Account>) request.getAttribute("accounts");
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Transfer · MyBank</title>

  <link rel="stylesheet" href="css/design-system.css">
  <link rel="stylesheet" href="css/base.css">
  <link rel="stylesheet" href="css/components.css">
  <link rel="stylesheet" href="css/dashboard.css">
  <link rel="stylesheet" href="css/transfer.css">
</head>

<body>

<div class="app">

  <%@ include file="sidebar.jsp" %>

  <main class="content">

    <h1>Transfer Money</h1>
    <p class="muted">Move funds securely between accounts</p>

    <div id="message"></div>

    <div class="card transfer-card">

      <form action="transfer" method="post">

        <label>From Account</label>
        <select name="fromAccount" class="input" required>
          <% if (accounts != null) {
               for (Account acc : accounts) { %>
            <option value="<%= acc.getAccountnumber() %>">
              <%= acc.getAccountType() %> · <%= acc.getAccountnumber() %>
            </option>
          <% }} %>
        </select>

        <label>To Account</label>
        <input type="number"
               name="toAccount"
               class="input"
               placeholder="Enter beneficiary account number"
               required>

        <label>Amount</label>
        <input type="number"
               name="amount"
               step="0.01"
               class="input"
               required>

        <button class="btn-primary">
          Continue Transfer
        </button>

      </form>

    </div>

    <p class="muted small" style="margin-top:12px">
      🔒 Transfers are protected and processed securely.
    </p>

  </main>

</div>

<script src="js/message.js"></script>
</body>
</html>
