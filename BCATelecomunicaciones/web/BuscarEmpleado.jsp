<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>BCA | Resultado</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="css/BuscarEmpleado.css"> 
              <link rel="icon" href="images/Icon.ico">
    </head>
    <body>
        <h1>Busqueda Empleado:  </h1>
        <%
            String username;
            username = request.getParameter("usernameS");
        %>
        <h3>Usuario:  <c:out value="${usernameS}"/></h3>

        <form class="pantalla text-center" method="POST" action="Empleados.jsp">
            <input type="hidden" name="usernameS" value="<c:out value="${usernameS}"/>"/>

            <div class="campos">
                <label>Cedula: </label><input type="text" name="id" value="<c:out value="${id}"/>" disabled/>
                <label>Usuario: </label><input type="text" name="username" value="<c:out value="${username}"/>" disabled/>
                <br>
                <label>Nombre: </label><input id="cajaNombre" type="text" name="nombre" value="<c:out value="${nombre}"/>" disabled/>
            </div>
            <div class="campos">
                <label>Rol: </label><input type="text" name="rol" value="<c:out value="${rol}"/>" disabled/>
                <label>Direccion: </label><input type="text" name="direccion" value="<c:out value="${direccion}"/>" disabled/>
            </div>
            <div class="campos">
                <label>Teléfono: </label><input type="text" name="telefono" value="<c:out value="${telefono}"/>" disabled/>
                <label>Estado: </label><input type="text" name="estado" value="<c:out value="${estado}"/>" disabled/></td>
            </div>
            <button class="btn btn-primary" id="ingresar" type="submit"> Empleados</button>
        </form>
        <br> 
        <form action="BuscarEmpleado" method="post">
            <input type="hidden" name="usernameS" value="<%= username%>"/>
            <button class="btn btn-primary" id="atras" type="submit"> Atrás</button>
        </form>
        <script src="js/ContraseñaOcultar.js" type="text/javascript"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

    </body>
</html>
