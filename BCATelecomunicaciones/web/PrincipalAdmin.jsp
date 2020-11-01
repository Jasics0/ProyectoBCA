<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BCA Telecomunicaciones</title>
    </head>
    <body>
        <%
            String usernameS = request.getParameter("usernameS");
        %>
        <h1>BCA Telecomunicaciones</h1>
        <h3>Usuario: <c:out value="${usernameS}"/> <%= ((usernameS == null) ? "" : usernameS)%></h3>
        <form action="DashboardFinanzas.jsp" method="post">
            <input type="hidden" name="usernameS" value="<c:out value="${usernameS}"/>  <%= ((usernameS == null) ? "" : usernameS)%>"/>
            <input type="submit" value="Finanzas"/>
        </form>
        <br>
        <form action="Empleados.jsp" method="post">
            <input type="hidden" name="usernameS" value="<c:out value="${usernameS}"/> <%= ((usernameS == null) ? "" : usernameS)%>"/>
            <input type="submit" value="Empleados"/>
        </form>
        <br>
        <form action="Facturas.jsp" method="post">
            <input type="hidden" name="usernameS" value="<c:out value="${usernameS}"/> <%= ((usernameS == null) ? "" : usernameS)%>"/>
            <input type="submit" value="Facturas"/>
        </form>
        <br>
        <form action="Tickets" method="post">
            <input type="hidden" name="usernameS"value="<c:out value="${usernameS}"/> <%= ((usernameS == null) ? "" : usernameS)%>"/>
            <input type="submit" value="Tickets"/>
        </form>
        <br>
        <form action="Clientes.jsp" method="post">
            <input type="hidden" name="usernameS" value="<c:out value="${usernameS}"/> <%= ((usernameS == null) ? "" : usernameS)%>"/>
            <input type="submit" value="Clientes"/>
        </form>
        <br>
        <form action="Pagos.jsp" method="post">
            <input type="hidden" name="usernameS" value="<c:out value="${usernameS}"/> <%= ((usernameS == null) ? "" : usernameS)%>"/>
            <input type="submit" value="Pagos"/>
        </form>
        <br>
        <form action="OpcionesPlanes" method="post">
            <input type="hidden" name="usernameS"value="<c:out value="${usernameS}"/> <%= ((usernameS == null) ? "" : usernameS)%>"/>
            <input type="submit" value="Planes"/>
        </form>
    </body>
</html>
