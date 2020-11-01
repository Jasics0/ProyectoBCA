<%-- 
    Document   : AgregarPlanes
    Created on : 31/10/2020, 03:12:16 PM
    Author     : Retr0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BCA | Agregar Plan</title>
    </head>
    <body>
        <%
            String username;
            username = request.getParameter("usernameS");
        %>
        <h1>Plan nuevo:</h1>
        <h3>Usuario: <%= username%></h3>
        <form action="AgregarPlan" method="post">
            <input type="hidden" name="usernameS" value="<%= username%>"/>
            <input type="text" name="nombre" placeholder="Nombre" required/>
            <input type="text" name="descripcion" placeholder="Descripcion" required/>
            <input type="number" name="valor" placeholder="Valor" required/>
            <br><br>
            <div class="g-recaptcha" data-sitekey="6LeCgNoZAAAAAHe3hSqonnQg3EOwHAGZOuaYOlIO"></div>
            <input type="submit" value="Agregar"/>

        </form>

        <form action="OpcionesPlanes" method="post">
            <br>
            <input type="hidden" name="usernameS" value="<%= username%>"/>
            <input type="submit" value="Atrás"/>
        </form>

        <!--js-->
        <script src='https://www.google.com/recaptcha/api.js'></script>
        <script src="js/ContraseñaOcultar.js" type="text/javascript"></script>
    </body>
</html>