<%-- 
    Document   : Tickets
    Created on : 21/10/2020, 08:48:52 PM
    Author     : Retr0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BCA Telecomunicaciones | Tickets</title>
    </head>
    <body>
        <% 
        String username;
        username=request.getParameter("username");
        %>
        <h1><%= username%></h1>
    </body>
</html>
