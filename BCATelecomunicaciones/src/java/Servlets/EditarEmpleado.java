/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DAO.EmpleadosJpaController;
import DAO.UsuariosJpaController;
import DTO.Empleados;
import DTO.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Retr0
 */
@WebServlet(name = "EditarEmpleado", urlPatterns = {"/EditarEmpleado"})
public class EditarEmpleado extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
try (PrintWriter out = response.getWriter()) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("BCATelecomunicacionesPU");
            UsuariosJpaController daoUser = new UsuariosJpaController(emf);
            EmpleadosJpaController daoEmp = new EmpleadosJpaController(emf);
            Usuarios user = new Usuarios();
            user.setUsername(request.getParameter("username").toLowerCase());
            user.setContraseña(request.getParameter("password"));
            String id=request.getParameter("idO");
            String username=request.getParameter("usernameO");
            Empleados empleado = new Empleados(request.getParameter("id"), request.getParameter("nombre").toLowerCase(), request.getParameter("rol"), request.getParameter("direccion"), ( request.getParameter("estado").equals("activo")) ? true : false, request.getParameter("telefono"), user);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Editar Empleado</title>"
                    + "        <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">"
                    + "        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/consultaClienteServlet.css\">"
                    + "        <link rel=\"icon\" href=\"images/Icon.ico\">");
            out.println("</head>");
            out.println("<body>");

            try {
                daoEmp.destroy(id);
                daoUser.destroy(username);
                daoUser.create(user);
                daoEmp.create(empleado);
                request.setAttribute("usernameS", request.getParameter("usernameS"));
                request.getRequestDispatcher("Empleados.jsp").forward(request, response);
            } catch (Exception a) {
                out.println("<h1>Error al registrar empleado.</h1>");
                out.println("<div class=\"pantalla text-center\">"
                        + "        <h3>" + a.getMessage() + id + "</h3>"
                        + "        <h4>No se pudo editar este cliente, verifique que el cliente no esté repetido, y los datos estén escritos correctamente.</h4>"
                        + "        <button class=\"btn btn-primary\" id=\"ingresar\" onclick=\"location.href='CrearEmpleado.jsp'\">Volver al registro</button>"
                        + "        <button class=\"btn btn-primary\" id=\"ingresar\" onclick=\"location.href='PrincipalAdmin.jsp'\">Volver al inicio</button>"
                        + "        <button class=\"btn btn-primary\" id=\"ingresar\" onclick=\"location.href='Empleados.jsp'\">Volver a la seccion empleados</button>"        
                        + "  </div>");
                        request.setAttribute("usernameS", request.getParameter("usernameS"));

            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
