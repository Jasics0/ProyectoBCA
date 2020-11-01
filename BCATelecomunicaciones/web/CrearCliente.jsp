<%-- 
    Document   : CrearCliente
    Created on : 31/10/2020, 01:53:47 PM
    Author     : Retr0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BCA | Crear Cliente</title>
    </head>
    <body>
        <%
            String username;
            username = request.getParameter("usernameS");
        %>
        <h1>Cliente nuevo:</h1>
        <h3>Usuario: <%= username%></h3>
        <form action="CrearCliente" method="post">
            <input type="hidden" name="usernameS" value="<%= username%>"/>
            <input type="text" name="id" placeholder="Cedula" required/>
            <input type="text" name="nombre" placeholder="Nombre" required/>
            <br><br>
            <input type="text" name="direccion" placeholder="Direccion"/>
            <input type="text" name="telefono" placeholder="telefono"/>
            <br><br>
            <select name="plan" required>

                <option value="" >Seleccione un plan...</option>

                <c:forEach var="lista" items="${lista}">
                    <option value="${lista.getCodProducto()}" >${lista.getNombre()}</option>
                </c:forEach>
            </select>
            <br><br>
            <div class="g-recaptcha" data-sitekey="6LeCgNoZAAAAAHe3hSqonnQg3EOwHAGZOuaYOlIO"></div>
            <br><br>
            <input type="submit" value="Agregar"/>

        </form>

        <form action="Clientes.jsp" method="post">
            <br>
            <input type="hidden" name="usernameS" value="<%= username%>"/>
            <input type="submit" value="Atrás"/>
        </form>

        <!--js-->
        <script src='https://www.google.com/recaptcha/api.js'></script>
    </body>
</html>

