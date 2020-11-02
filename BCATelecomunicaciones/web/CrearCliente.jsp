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
        <title>BCA | Crear Cliente</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="css/CrearCliente.css">
              <link rel="icon" href="images/Icon.ico">
    </head>
    <body>
        <%
            String username;
            username = request.getParameter("usernameS");
        %>
        <h1>Cliente nuevo:</h1>

        <div class="modal-dialog text-center">
            <div class="modal-content "> 
                <h3>Usuario: <%= username%></h3>
                <form action="CrearCliente" method="post">
                    <input type="hidden" name="usernameS" value="<%= username%>"/>
                    <input type="text" class="campos2" name="id" placeholder="Cedula" required/>
                    <input type="text" class="campos2" name="nombre" placeholder="Nombre" required/>
                    <br><br>
                    <input type="text" class="campos2" name="direccion" placeholder="Direccion"/>
                    <input type="text" class="campos2" name="telefono" placeholder="telefono"/>
                    <br><br>
                    <select name="plan" required>

                        <option value="" >Seleccione un plan...</option>
                        
                        <c:forEach var="lista" items="${lista}">
                            <option value="${lista.getCodProducto()}" >${lista.getNombre()}</option>
                        </c:forEach>
                    </select>
                    <br><br>
                    <div class="g-recaptcha"  data-sitekey="6LeCgNoZAAAAAHe3hSqonnQg3EOwHAGZOuaYOlIO"></div>
                    <br>
                    <button class="btn btn-primary" id="ingresar" type="submit"> Agregar</button>
                </form>
            </div>
        </div>

        <form action="Clientes.jsp" method="post">
            <input type="hidden" name="usernameS" value="<%= username%>"/>
            <button class="btn btn-primary" id="atras" type="submit"> Atrás</button>
        </form>

        <!--js-->
        <script src='https://www.google.com/recaptcha/api.js'></script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

    </body>
</html>

