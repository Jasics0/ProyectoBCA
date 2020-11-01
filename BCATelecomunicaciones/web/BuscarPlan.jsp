<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BCA | Resultado</title>
    </head>
    <body>
        <h1>Busqueda Plan:  </h1>
        <%
            String username;
            username = request.getParameter("usernameS");
        %>
        <h3>Usuario:  <c:out value="${usernameS}"/></h3>
        <form method="POST" action="OpcionesPlanes">
            <input type="hidden" name="usernameS" value="<c:out value="${usernameS}"/>"/>
            <table>
                <tr>
                    <td><label>Codigo: </label><input type="text" name="id" value="<c:out value="${codigo}"/>" disabled/></td>
                    <td><label>Descripcion: </label><input type="text" name="descripcion" value="<c:out value="${descripcion}"/>" disabled/></td>
                    <td><label>Nombre: </label><input type="text" name="nombre" value="<c:out value="${nombre}"/>" disabled/></td>
                </tr>
                <tr>
                    <td>   
                        <br>
                        <label>Valor: </label><input type="number" name="valor" value="<c:out value="${valor}"/>" disabled/></td>
                </tr>
              
            </table>
            <br>
            <input type="submit" value="Atrás"/>
        </form>
        <br>
    </body>
</html>
