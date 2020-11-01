<%-- 
    Document   : Empleados
    Created on : 21/10/2020, 08:19:20 PM
    Author     : Retr0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BCA | Empleados</title>
    </head>
    <body>
        <%
            String username;
            username = request.getParameter("usernameS");
        %>
        <h1>Empleados</h1>
        <h3>Usuario:<%= username%></h3>
        <form action="CrearEmpleado.jsp" method="post">
            <input type="hidden" name="usernameS" value="<%= username%>"/>
            <input type="submit" value="Crear Empleado"/>
        </form>
        <br>
        <form action="BuscarEmpleado" method="post">
            <input type="hidden" name="usernameS" value="<%= username%>"/>
            <input type="submit" value="Gestionar Empleados"/>
        </form>
        <br>
        <form action="PrincipalAdmin.jsp" method="post">
            <input type="hidden" name="usernameS" value="<%= username%>"/>
            <input type="submit" value="Atrás"/>
        </form>

    </body>
</html>