<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BCA | Resultado</title>
    </head>
    <body>
        <h1>Busqueda Empleado:  </h1>
        <%
            String username;
            username = request.getParameter("usernameS");
        %>
        <h3>Usuario:  <c:out value="${usernameS}"/></h3>
        <form method="POST" action="Empleados.jsp">
            <input type="hidden" name="usernameS" value="<c:out value="${usernameS}"/>"/>
            <table>
                <tr>
                    <td><label>Cedula: </label><input type="text" name="id" value="<c:out value="${id}"/>" disabled/></td>
                    <td><label>Usuario: </label><input type="text" name="username" value="<c:out value="${username}"/>" disabled/></td>
                    <td><label>Nombre: </label><input type="text" name="nombre" value="<c:out value="${nombre}"/>" disabled/></td>
                </tr>
                <tr>
                    <td>   
                        <br>
                        <label>Rol: </label><input type="text" name="rol" value="<c:out value="${rol}"/>" disabled/></td>
                </tr>
                <tr>
                    <td>           
                        <br>
                        <label>Direccion: </label><input type="text" name="direccion" value="<c:out value="${direccion}"/>" disabled/>
                    </td>
                    <td>       
                        <br>
                        <label>Teléfono: </label><input type="text" name="telefono" value="<c:out value="${telefono}"/>" disabled/>
                    </td>
                </tr>

                <tr>
                    <td>         
                        <br>
                        <label>Estado: </label><input type="text" name="estado" value="<c:out value="${estado}"/>" disabled/></td>
                    </td>
                </tr>

            </table>
            <br>
            <input type="submit" value="Empleados"/>
        </form>
        <br>
        <form action="BuscarEmpleado" method="post">
            <input type="hidden" name="usernameS" value="<%= username%>"/>
            <input type="submit" value="Atrás"/>
        </form>
    </body>
</html>
