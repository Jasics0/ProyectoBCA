<%-- 
    Document   : CrearTicket
    Created on : 1/11/2020, 10:34:56 AM
    Author     : Retr0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>BCA | Crear Ticket</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        <h1>Ticket nuevo:</h1>
        <div class="modal-dialog text-center">
            <div class="modal-content "> 
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
            <button class="btn btn-primary" id="ingresar" type="submit"> Agregar</button>

        </form>
             </div>
        </div>

        <form action="Tickets" method="post">
            <input type="hidden" name="usernameS" value="<%= username%>"/>
            <button class="btn btn-primary" id="atras" type="submit"> Atrás</button>
        </form>

        <!--js-->
        <script src='https://www.google.com/recaptcha/api.js'></script>
        <script src="js/ContraseñaOcultar.js" type="text/javascript"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

    </body>
</html>
