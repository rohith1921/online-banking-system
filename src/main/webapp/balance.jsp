<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.bank.model.*" %>

<%
List<Account> accounts = (List<Account>) request.getAttribute("accounts");
Double balance = (Double) request.getAttribute("balance");
%>

<!DOCTYPE html>
<html>
<head>
  <title>Balance Enquiry · MyBank</title>

  <link rel="stylesheet" href="css/design-system.css">
  <link rel="stylesheet" href="css/base.css">
  <link rel="stylesheet" href="css/components.css">
  <link rel="stylesheet" href="css/dashboard.css">
  <link rel="stylesheet" href="css/balance.css">
</head>

<body>

<div class="app">

  <%@ include file="sidebar.jsp" %>

  <main class="content">

    <h1>Balance Enquiry</h1>
    <p class="muted">Check your current account balance</p>

    <div class="card balance-card">

      <form method="post" action="balance">

        <label>Select Account</label>
        <select name="accountNumber" class="input" required>
          <% if (accounts != null) {
               for (Account acc : accounts) { %>
            <option value="<%= acc.getAccountnumber() %>">
              <%= acc.getAccountType() %> · <%= acc.getAccountnumber() %>
            </option>
          <% }} %>
        </select>

        <button class="btn-primary">Check Balance</button>

      </form>

      <% if (balance != null) { %>
        <div class="balance-result">
          <span>Available Balance</span>
          <h2>₹ <%= balance %></h2>
          <p class="small muted">
            Last updated just now
          </p>
        </div>
      <% } %>

    </div>

  </main>

</div>

</body>
</html>
