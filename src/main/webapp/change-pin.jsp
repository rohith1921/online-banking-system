<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.bank.model.*" %>

<%
List<Account> accounts = (List<Account>) request.getAttribute("accounts");
%>

<!DOCTYPE html>
<html>
<head>
  <title>Change PIN · MyBank</title>

  <link rel="stylesheet" href="css/design-system.css">
  <link rel="stylesheet" href="css/base.css">
  <link rel="stylesheet" href="css/components.css">
  <link rel="stylesheet" href="css/dashboard.css">
  <link rel="stylesheet" href="css/change-pin.css">
</head>

<body>

<div class="app">

  <%@ include file="sidebar.jsp" %>

  <main class="content">

    <h1>Change Account PIN</h1>
    <p class="muted">Keep your account secure</p>

    <div id="message"></div>

    <div class="card settings-card change-pin-card">


      <form action="change-pin" method="post">

        <label>Select Account</label>
        <select name="accountNumber" class="input" required>
          <% if (accounts != null) {
               for (Account acc : accounts) { %>
            <option value="<%= acc.getAccountnumber() %>">
              <%= acc.getAccountType() %> · <%= acc.getAccountnumber() %>
            </option>
          <% }} %>
        </select>

        <label>Old PIN</label>
        <input type="password" name="oldPin"
               class="input" required>

        <label>New PIN</label>
        <input type="password" name="newPin"
               class="input" required>

        <button class="btn-primary">Update PIN</button>

      </form>
      
      <p class="pin-hint">
  Never share your PIN. Bank staff will never ask for it.
</p>
      

    </div>

  </main>

</div>

<script src="js/message.js"></script>
</body>
</html>
