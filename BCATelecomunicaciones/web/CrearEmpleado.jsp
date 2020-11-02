<%-- 
    Document   : CrearEmpleado
    Created on : 22/10/2020, 12:46:16 AM
    Author     : Retr0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>BCA | Crear Empleado</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="css/CrearEmpleado.css"> 
        <link rel="icon" href="images/Icon.ico">
    </head>
    <body>
        <%
            String username;
            username = request.getParameter("usernameS");
        %>
        <h1>Empleado nuevo:</h1>
        
            <div class="modal-dialog text-center">

                <div class="modal-content ">
                    <h3>Usuario: <%= username%></h3>
                    <form action="CrearEmpleadoServlet" method="post">
                        <input type="hidden" name="usernameS" value="<%= username%>"/>
                        <svg width="1.8em" height="1.8em" viewBox="0 0 16 16" class="bi bi-person-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd" d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
                        </svg>
                        <input type="text" class="campos" name="username" placeholder="Usuario" required/>
                        <svg width="1.5em" height="1.5em" viewBox="0 0 16 16" class="bi bi-lock-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                        <path d="M2.5 9a2 2 0 0 1 2-2h7a2 2 0 0 1 2 2v5a2 2 0 0 1-2 2h-7a2 2 0 0 1-2-2V9z"/>
                        <path fill-rule="evenodd" d="M4.5 4a3.5 3.5 0 1 1 7 0v3h-1V4a2.5 2.5 0 0 0-5 0v3h-1V4z"/>
                        </svg>
                        <input type="password" class="campos" id="password" name="password" placeholder="Contraseña" required/> 
                        <button class="btn btn-primary" id="btn-password" type="button" >👁</button>
                        <br><br>
                        <input type="text" class="campos2" name="id" placeholder="Cedula" required/>
                        <input type="text" class="campos2" name="nombre" placeholder="Nombre" required/>
                        <br><br>
                        <input type="text" class="campos2" name="direccion" placeholder="Direccion"/>
                        <input type="text" class="campos2" name="telefono" placeholder="telefono"/>
                        <br><br>
                        <label class="rolboton">Rol: </label>
                        <label class="rolboton"><input  type="radio" name="rol" value="admin"
                                                        checked>
                            Aministrador</label>
                        <label class="rolboton"><input type="radio" name="rol" value="empleado"
                                                       checked>
                            Empleado</label>
                        <br>
                        <div class="g-recaptcha" data-sitekey="6LeCgNoZAAAAAHe3hSqonnQg3EOwHAGZOuaYOlIO"></div>
                        <button class="btn btn-primary" id="ingresar" type="submit"> Agregar</button>
                    </form>
                </div>
            </div>
            <form  action="Empleados.jsp" method="post">
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
