<%-- 
    Document   : Clientes
    Created on : 31/10/2020, 05:12:28 PM
    Author     : Retr0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BCA | Clientes</title>
    </head>
    <body>
        <%
            String username;
            username = request.getParameter("usernameS");
        %>
        <h1>Clientes</h1>
        <h3>Usuario:<%= username%></h3>
        <form action="Planes" method="post">
            <input type="hidden" name="usernameS" value="<%= username%>"/>
            <input type="submit" value="Crear Cliente"/>
        </form>
        <br>
        <form action="BuscarCliente" method="post">
            <input type="hidden" name="usernameS" value="<%= username%>"/>
            <input type="submit" value="Gestionar Clientes"/>
        </form>
        <br>
        <form action="PrincipalAdmin.jsp" method="post">
            <input type="hidden" name="usernameS" value="<%= username%>"/>
            <input type="submit" value="Atrás"/>
        </form>

    </body>
</html>