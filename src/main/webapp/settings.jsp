<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.bank.model.User" %>

<%
User user = (User) request.getAttribute("user");
%>

<!DOCTYPE html>
<html>
<head>
  <title>Settings · MyBank</title>

  <link rel="stylesheet" href="css/design-system.css">
  <link rel="stylesheet" href="css/base.css">
  <link rel="stylesheet" href="css/components.css">
  <link rel="stylesheet" href="css/dashboard.css">
  <link rel="stylesheet" href="css/settings.css">
</head>

<body>

<div class="app">

  <%@ include file="sidebar.jsp" %>

  <main class="content">

    <h1>Settings & Security</h1>
    <p class="muted">Manage your profile and security</p>

    <div class="card settings-card">

      <h3>Profile</h3>

      <div class="profile-row">
        <span>Name</span>
        <strong><%= user.getName() %></strong>
      </div>

      <div class="profile-row">
        <span>Email</span>
        <strong><%= user.getEmail() %></strong>
      </div>

      <div class="profile-row">
        <span>Mobile</span>
        <strong><%= user.getMobilenumber() %></strong>
      </div>

    </div>

    <div class="card settings-card security-card">

      <h3>Security</h3>

      <a href="change-pin" class="btn-secondary security-btn">
        Change Account PIN
      </a>

      <p class="small muted security-note" style="margin-top:8px">
        Last login and session info can be added here.
      </p>

    </div>

  </main>

</div>

</body>
</html>
