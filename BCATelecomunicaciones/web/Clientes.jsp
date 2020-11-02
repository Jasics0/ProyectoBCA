<%-- 
    Document   : Clientes
    Created on : 31/10/2020, 05:12:28 PM
    Author     : Retr0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>BCA | Clientes</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="css/Clientes.css">
              <link rel="icon" href="images/Icon.ico">
    </head>
    <body>
        <%
            String username;
            username = request.getParameter("usernameS");
        %>
        <h1>Clientes</h1>
        <h3>Usuario:<%= username%></h3>
        <div class="col-sm-8 main-section">
            <div class="modal-content text-center">
                <div class="item">
                    <div class="col-12 user-img" >
                        <img id="imagen" src="images/user2.png"/>
                    </div>
                    <form class="col-12" action="Planes" method="post">
                        <input type="hidden" name="usernameS" value="<%= username%>"/>
                        <button class="btn btn-primary" type="submit" >Crear Cliente</button>
                    </form>
                </div>
                <div class="item2">
                    <div class="col-12 user-img" >
                        <img id="imagen" src="images/editar.png"/>
                    </div>
                    <form action="BuscarCliente" method="post">
                        <input type="hidden" name="usernameS" value="<%= username%>"/>
                        <button class="btn btn-primary" type="submit" >Gestionar Clientes</button>
                    </form>
                </div>
            </div> 

            <form action="PrincipalAdmin.jsp" method="post">
                <input type="hidden" name="usernameS" value="<%= username%>"/>
                <button class="btn btn-primary" id="atras" type="submit" >Atrás</button>
            </form>
        </div>
        <script src="js/ContraseñaOcultar.js" type="text/javascript"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
    </body>
</html>