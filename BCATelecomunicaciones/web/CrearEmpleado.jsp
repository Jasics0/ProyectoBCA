<%-- 
    Document   : CrearEmpleado
    Created on : 22/10/2020, 12:46:16 AM
    Author     : Retr0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BCA | Crear Empleado</title>
    </head>
    <body>
        <%
            String username;
            username = request.getParameter("usernameS");
        %>
        <h1>Empleado nuevo:</h1>
        <h3>Usuario: <%= username%></h3>
        <form action="CrearEmpleadoServlet" method="post">
            <input type="hidden" name="usernameS" value="<%= username%>"/>
            <input type="text" name="username" placeholder="Usuario" required/>
            <input type="password" id="password" name="password" placeholder="Contraseña" required/> <button id="btn-password" type="button" >👁</button>
            <br><br>
            <input type="text" name="id" placeholder="Cedula" required/>
            <input type="text" name="nombre" placeholder="Nombre" required/>
            <br><br>
            <input type="text" name="direccion" placeholder="Direccion"/>
            <input type="text" name="telefono" placeholder="telefono"/>
            <br><br>
            <label>Rol: </label>
            <input type="radio" name="rol" value="admin"
                   checked>
            <label>Aministrador</label>
            <input type="radio" name="rol" value="empleado"
                   checked>
            <label>Empleado</label>
            <br><br>
            <div class="g-recaptcha" data-sitekey="6LeCgNoZAAAAAHe3hSqonnQg3EOwHAGZOuaYOlIO"></div>
            <input type="submit" value="Agregar"/>

        </form>

        <form action="Empleados.jsp" method="post">
            <br>
            <input type="hidden" name="usernameS" value="<%= username%>"/>
            <input type="submit" value="Atrás"/>
        </form>

        <!--js-->
        <script src='https://www.google.com/recaptcha/api.js'></script>
        <script src="js/ContraseñaOcultar.js" type="text/javascript"></script>
    </body>
</html>
