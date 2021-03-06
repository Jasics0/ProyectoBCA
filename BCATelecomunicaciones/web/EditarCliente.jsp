<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Editar Empleado</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="css/EditarEmpleados.css">
              <link rel="icon" href="images/Icon.ico">
    </head>
    <body>
        <h1>Editar Cliente:  </h1>

        <h3>Usuario:  <c:out value="${usernameS}"/></h3>

        <form class="pantalla text-center" method="POST" action="EditarCliente">
            <input type="hidden" name="usernameS" value="<c:out value="${usernameS}"/>"/>
            <input type="hidden" name="idO" value="<c:out value="${id}"/>"/>

            <div class="campos">
                <label>Cedula: </label><input type="text" name="id" value="<c:out value="${id}"/>" />
                <label>Nombre: </label><input type="text" name="nombre" value="<c:out value="${nombre}"/>" />
            </div>
            <div class="campos">
                <label>Direccion: </label><input type="text" name="direccion" value="<c:out value="${direccion}"/>" />
                <label>Teléfono: </label><input type="text" name="telefono" value="<c:out value="${telefono}"/>" />
            </div>

            <div class="escoger">
                <label>Plan: </label>

                <select name="plan" required>

                    <option value="" >Seleccione un plan...</option>

                    <c:forEach var="lista" items="${lista}">
                        <option value="${lista.getCodProducto()}" >${lista.getNombre()}</option>
                    </c:forEach>

                </select>
            </div>

            <div class="escoger">
                <label>Estado: </label>
                <input type="radio" name="estado" value="desactivo" checked>
                <label>Desactivo</label>
                <input type="radio" name="estado" value="activo" checked>
            </div>
            <button class="btn btn-primary" id="ingresar" type="submit"> Editar</button>
        </form>
        <br>
        <form action="BuscarCliente" method="post">
            <input type="hidden" name="usernameS" value="<c:out value="${usernameS}"/>"/>
            <button class="btn btn-primary" id="atras" type="submit"> Atrás</button>
        </form>

        <script src="js/ContraseñaOcultar.js" type="text/javascript"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

    </body>
</html>
