<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Empleado</title>
    </head>
    <body>
        <h1>Editar Empleado:  </h1>

        <h3>Usuario:  <c:out value="${usernameS}"/></h3>
        <form method="POST" action="EditarCliente">
            <input type="hidden" name="usernameS" value="<c:out value="${usernameS}"/>"/>
            <input type="hidden" name="idO" value="<c:out value="${id}"/>"/>

            <table>
                <tr>
                    <td><label>Cedula: </label><input type="text" name="id" value="<c:out value="${id}"/>" /></td>
                    <td><label>Nombre: </label><input type="text" name="nombre" value="<c:out value="${nombre}"/>" /></td>
                </tr>

                <tr>
                    <td>           
                        <br>
                        <label>Direccion: </label><input type="text" name="direccion" value="<c:out value="${direccion}"/>" />
                    </td>
                    <td>       
                        <br>
                        <label>Teléfono: </label><input type="text" name="telefono" value="<c:out value="${telefono}"/>" />
                    </td>
                </tr>
                <tr>
                    <td>           
                        <br>
                        <label>Plan: </label>
                        
                        <select name="plan" required>
                            
                            <option value="" >Seleccione un plan...</option>
                            
                            <c:forEach var="lista" items="${lista}">
                               <option value="${lista.getCodProducto()}" >${lista.getNombre()}</option>
                            </c:forEach>
                            
                        </select>
                    </td>
                </tr>

               <tr>
                    <td>         
                        <br>
                        <label>Estado: </label>
                        <input type="radio" name="estado" value="desactivo" checked>
                        <label>Desactivo</label>
                        <input type="radio" name="estado" value="activo" checked>
                        <label>Activo</label> </td>
                </tr>

            </table>
            <br>
            <input type="submit" value="Editar"/>
        </form>
        <br>
        <form action="BuscarEmpleado" method="post">
            <input type="hidden" name="usernameS" value="<c:out value="${usernameS}"/>"/>
            <input type="submit" value="Atrás"/>
        </form>
    </body>
</html>
