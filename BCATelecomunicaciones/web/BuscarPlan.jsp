<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>BCA | Resultado</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="css/BuscarPlan.css">
        <link rel="icon" href="images/Icon.ico">
    </head>
    <body>
        <h1>Busqueda Plan:  </h1>
        <%
            String username;
            username = request.getParameter("usernameS");
        %>
        <h3>Usuario:  <c:out value="${usernameS}"/></h3>
        <form class="pantalla text-center" method="POST" action="OpcionesPlanes">
            <input type="hidden" name="usernameS" value="<c:out value="${usernameS}"/>"/>
             
           <div class="campos">
                    <label>Codigo: </label><input type="text" name="id" value="<c:out value="${codigo}"/>" disabled/>
                    <label>Descripcion: </label><input type="text" name="descripcion" value="<c:out value="${descripcion}"/>" disabled/>
                    <br>
                    <label>Nombre: </label><input type="text" name="nombre" value="<c:out value="${nombre}"/>" disabled/>
                    <label>Valor: </label><input type="number" name="valor" value="<c:out value="${valor}"/>" disabled/>
             </div>
            <br>
            <button class="btn btn-primary" id="atras" type="submit"> Atrás</button>
        </form>
        
    </body>
</html>
