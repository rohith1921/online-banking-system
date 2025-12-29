<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Create Account · MyBank</title>

  <!-- Fonts -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">

  <!-- CSS -->
  <link rel="stylesheet" href="css/design-system.css">
  <link rel="stylesheet" href="css/base.css">
  <link rel="stylesheet" href="css/components.css">
  <link rel="stylesheet" href="css/dashboard.css">
  <link rel="stylesheet" href="css/create-account.css">
</head>

<body>

<div class="app">

  <!-- Sidebar -->
  <aside class="sidebar">
    <h2 class="logo">MyBank</h2>
    <nav>
      <a href="dashboard">Dashboard</a>
      <a href="create-account" class="active">Create Account</a>
      <a href="deposit">Deposit</a>
      <a href="withdraw">Withdraw</a>
      <a href="transfer">Transfer</a>
      <div class="nav-group">
  <span class="nav-title">Statements</span>
  <a href="mini-statement" class="sub-link">Mini Statement</a>
  <a href="statement" class="sub-link">Full Statement</a>
  <a href="statement-by-date" class="sub-link">By Date</a>
</div>

      <a href="change-pin">Settings</a>
      <a href="logout">Logout</a>
    </nav>
  </aside>

  <!-- Content -->
  <main class="content">

    <h1>Create New Account</h1>
    <p class="muted">Open a new bank account securely</p>

    <div id="message"></div>

    <div class="card create-account-card">

      <form action="create-account" method="post">

        <label>Account Type</label>
        <select name="accountType" class="input" required>
          <option value="SAVINGS">Savings</option>
          <option value="CURRENT">Current</option>
          <option value="FIXED">Fixed Deposit</option>
          <option value="RD">Recurring Deposit</option>
        </select>

        <label>Set PIN</label>
        <input class="input" type="password" name="pin" required>

        <label>Initial Deposit</label>
        <input class="input" type="number" step="0.01"
               name="initialAmount" value="0">

        <button class="btn-primary" type="submit">
          Create Account
        </button>

      </form>

    </div>

  </main>

</div>

<script src="js/message.js"></script>
</body>
</html>
    