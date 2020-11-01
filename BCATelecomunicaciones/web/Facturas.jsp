<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BCA | Facturas</title>
    </head>
    <body>
        <% 
        String username;
        username=request.getParameter("usernameS");
        %>
        <h1>Facturas</h1>
        <h3>Usario: <%= username%></h3>
    </body>
</html>
