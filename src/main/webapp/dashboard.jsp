<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.bank.model.*" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<%
DateTimeFormatter fmt =
    DateTimeFormatter.ofPattern("dd MMM yyyy · hh:mm a");
%>

<%
User user = (User) request.getAttribute("user");
String avatar = "?";

if(user != null && user.getName() != null && !user.getName().isEmpty()) {
	avatar = user.getName().substring(0,1).toUpperCase();
}

List<Account> accounts = (List<Account>) request.getAttribute("accounts");
List<Transaction> recentTxns =
        (List<Transaction>) request.getAttribute("recentTransactions");
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Dashboard · MyBank</title>

  <!-- Fonts -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">

  <!-- CSS -->
  <link rel="stylesheet" href="css/design-system.css">
  <link rel="stylesheet" href="css/base.css">
  <link rel="stylesheet" href="css/components.css">
  <link rel="stylesheet" href="css/dashboard.css">
</head>

<body>

<div class="app">

  <!-- Sidebar -->
  <aside class="sidebar">
    <h2 class="logo">MyBank</h2>
    <nav>
      <a href="dashboard" class="active">Dashboard</a>
      <a href="create-account">Create Account</a>
      <a href="deposit">Deposit</a>
      <a href="withdraw">Withdraw</a>
      <a href="transfer">Transfer</a>
      <a href="balance">Balance</a>
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

  <!-- Main content -->
  <main class="content">

    <!-- Topbar -->
    <div class="topbar">
      <div class="hamburger" onclick="toggleSidebar()">☰</div>
      <input class="input search" placeholder="Search transactions…">
      <div class="top-actions">
        🔔
        <div class="profile-menu">
        <div class="avatar" onclick="toggleProfileMenu()">
          <%= avatar %>
        </div>
        <div id="profileDropdown" class="profile-dropdown">
        	<a href="settings">Profile</a>
        	<a href="change-pin">Change PIN</a>
        	<hr>
        	<a href="logout" class="danger">Logout</a>
        </div>
        </div>
      </div>
    </div>

    <h1>Welcome, <%= user.getName() %></h1>
    <p class="muted">Here’s your banking overview</p>

    <!-- ACCOUNTS -->
    <h2>Your Accounts</h2>

    <% if (accounts == null || accounts.isEmpty()) { %>
      <p class="muted">No accounts found.</p>
    <% } else { %>
      <div class="cards-grid">
        <% for (Account acc : accounts) { %>
          <div class="card account-card">
            <h3>
              <%= acc.getAccountType() %> ·
              <%= acc.getAccountnumber() %>
            </h3>
            <div class="balance">₹<%= acc.getBalance() %></div>
            <p class="muted">Available balance</p>

            <a class="btn-secondary"
               href="transfer?fromAccount=<%= acc.getAccountnumber() %>">
              Transfer
            </a>
          </div>
        <% } %>
      </div>
    <% } %>

    <!-- TRANSACTIONS -->
    <div class="card">
    <h2>Recent Transactions</h2>

    <% if (recentTxns == null || recentTxns.isEmpty()) { %>
      <p class="muted">No recent transactions found.</p>
    <% } else { %>
      
        <table class="statement-table">
		  <tr>
		    <th>Date</th>
		    <th>Description</th>
		    <th>Credit</th>
		    <th>Debit</th>
		    <th>Balance</th>
		  </tr>
		
		  <% for (Transaction t : recentTxns) { %>
		  <tr>
		    <td><%= t.getTimestamp().format(fmt) %></td>
		    <td><%= t.getDescription() %></td>
		    <td><%= "CREDIT".equals(t.getType()) ? t.getAmount() : "-" %></td>
		    <td><%= "DEBIT".equals(t.getType()) ? t.getAmount() : "-" %></td>
		    <td><%= t.getClosingbalance() %></td>
		  </tr>
		  <% } %>
		</table>
		<a href="statement" class="link-primary">View all</a>
		<% } %>
    </div>

  </main>

</div>

<script>
	function toggleProfileMenu() {
		const menu = document.getElementById("profileDropdown");
		menu.style.display = menu.style.display === "block" ? "none" : "block";
	}
	
	document.addEventListener("click", function(e) {
		const menu = document.getElementById("profileDropdown");
		if(!e.target.closest(".profile-menu")) {
			menu.style.display = "none";
		}
		
	});
	
	function toggleSidebar() {
		document.querySelector(".sidebar").classList.toggle("active");
	}
	
	document.addEventListener("click", function (e) {
		  const sidebar = document.querySelector(".sidebar");
		  const hamburger = document.querySelector(".hamburger");

		  if (
		    sidebar.classList.contains("active") &&
		    !sidebar.contains(e.target) &&
		    !hamburger.contains(e.target)
		  ) {
		    sidebar.classList.remove("active");
		  }
		});
</script>

</body>
</html>
