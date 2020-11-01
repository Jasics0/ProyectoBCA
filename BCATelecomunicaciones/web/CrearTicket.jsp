<%-- 
    Document   : CrearTicket
    Created on : 1/11/2020, 10:34:56 AM
    Author     : Retr0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BCA | Crear Ticket</title>
    </head>
    <body>
        <%
            String username;
            username = request.getParameter("usernameS");
        %>
        <h1>Ticket nuevo:</h1>
        <h3>Usuario: <%= username%></h3>
        <form action="CrearTicket" method="post">
            <input type="hidden" name="usernameS" value="<%=username%>"/>
            <input type="text" name="cedula" placeholder="Cedula Cliente" required/>
            <input type="text" name="descripcion" placeholder="Descripcion" required/>
            <br><br>
            <select name="prioridad" required>
                <option value="">Digite una opcion</option>
                <option value="u">Urgente</option>
                <option value="m">Media</option>
                <option value="b">Baja</option>
            </select>
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
