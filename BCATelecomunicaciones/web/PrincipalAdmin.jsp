<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>BCA Telecomunicaciones</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="css/principalAdmi.css">
        <link rel="icon" href="images/Icon.ico">
    </head>
    <body>
        <%
            String usernameS = request.getParameter("usernameS");
        %>
        <h1>BCA Telecomunicaciones</h1>
        <div class="col-sm-8 main-section">
            <h3>Usuario: <c:out value="${usernameS}"/> <%= ((usernameS == null) ? "" : usernameS)%></h3>
            <div class="modal-content text-center">

                <div class="item">
                    <div class="col-12 user-img" >
                        <img id="imagen" src="images/members.png"/>
                    </div>
                    <form action="Empleados.jsp" method="post">
                        <input type="hidden" name="usernameS" value="<c:out value="${usernameS}"/> <%= ((usernameS == null) ? "" : usernameS)%>"/>
                        <button class="btn btn-primary" type="submit" >Empleados</button>
                    </form>
                </div>
                <div class="item2">  
                    <div class="col-12 user-img" >
                        <img id="imagen" src="images/facturacion.png"/>
                    </div>  
                    <form action="Facturas.jsp" method="post">
                        <input type="hidden" name="usernameS" value="<c:out value="${usernameS}"/> <%= ((usernameS == null) ? "" : usernameS)%>"/>
                        <button class="btn btn-primary" type="submit" >Facturas</button>
                    </form>
                </div>
                <div class="item3">  
                    <div class="col-12 user-img" >
                        <img id="imagen" src="images/tikects.png"/>
                    </div>
                    <form action="Tickets" method="post">
                        <input type="hidden" name="usernameS"value="<c:out value="${usernameS}"/> <%= ((usernameS == null) ? "" : usernameS)%>"/>
                        <button class="btn btn-primary" type="submit" >Tickets</button>
                    </form>
                </div>
            </div>
            <div class="modal-content text-center">
                <div class="item4">  
                    <div class="col-12 user-img" >
                        <img id="imagen" src="images/user2.png"/>
                    </div>
                    <form action="Clientes.jsp" method="post">
                        <input type="hidden" name="usernameS" value="<c:out value="${usernameS}"/> <%= ((usernameS == null) ? "" : usernameS)%>"/>
                        <button class="btn btn-primary" type="submit" >Clientes</button>
                    </form>
                    <br>
                </div>
                <div class="item5">  
                    <div class="col-12 user-img" >
                        <img id="imagen" src="images/planes.png"/>
                    </div>
                    <form action="OpcionesPlanes" method="post">
                        <input type="hidden" name="usernameS"value="<c:out value="${usernameS}"/> <%= ((usernameS == null) ? "" : usernameS)%>"/>
                        <button class="btn btn-primary" type="submit" >Planes</button>
                    </form>
                </div>  
            </div>
            <form action="index.jsp" method="post">
                <input type="hidden" name="usernameS"/>
                <button class="btn btn-primary" id="atras" type="submit" >Volver al Login</button>
            </form>
                        <br><br>
        </div>
        <script src="js/ContraseñaOcultar.js" type="text/javascript"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

    </body>
</html>
