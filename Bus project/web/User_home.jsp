<%-- 
    Document   : User_home
    Created on : 31-May-2024, 11:57:57 pm
    Author     : Jiya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Home</title>
        <link rel="stylesheet" href="styles.css">
</head>
<body>
    <header>
        <h1>Welcome to the Home Page!</h1>
    </header>
    <main>
        <h3>Lets get booking............</h3>
        <% 
            HttpSession hs = request.getSession(false); 
            if (hs != null) {
                String User_name = (String) hs.getAttribute("User_name"); 
                String User_email = (String) hs.getAttribute("User_email"); 
        %>        
            <p>Welcome, <%= User_name %>!</p>
            <p>Your email: <%= User_email %></p>
            
            <form action='Search1.jsp'>
                <select name="ap" required >
                    <option>Arrival Point</option>
                    <option>Indore</option>
                    <option>Dewas</option>
                    <option>Bhopal</option>
                    <option>Delhi</option>
                </select><br>
                             
                <select name="dp" required >
                    <option>Destination Point</option>
                    <option>Indore</option>
                    <option>Dewas</option>
                    <option>Bhopal</option>
                    <option>Delhi</option>
                </select><br>
                <input type="submit" value="Search"> 
            </form>
            
        <% } else { %>
            <h2>Session expired. Please login again.</h2>
        <% } %>
    </main>
    <br>
<br>
<br>
<form action="LogoutServlet" method="post">
<button type="submit">Logout</button>
</form>
</body>
</html>
