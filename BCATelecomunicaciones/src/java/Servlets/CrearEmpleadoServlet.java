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
@WebServlet(name = "CrearEmpleadoServlet", urlPatterns = {"/CrearEmpleadoServlet"})
public class CrearEmpleadoServlet extends HttpServlet {

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
            Empleados empleado = new Empleados(request.getParameter("id"), request.getParameter("nombre").toLowerCase(), request.getParameter("rol"), request.getParameter("direccion"), true, request.getParameter("telefono"), user);

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CrearEmpleadoServlet</title>");
            out.println("</head>");
            out.println("<body>");

            try {
                daoUser.create(user);
                daoEmp.create(empleado);
                request.setAttribute("usernameS", request.getParameter("usernameS"));
                request.getRequestDispatcher("Empleados.jsp").forward(request, response);
            } catch (Exception a) {
                out.println("<h1>Error al registrar empleado.</h1>");
                out.println("<label>No se pudo registrar este empleado, verifique que el empleado no esté repetido, y los datos estén escritos correctamente.</label><br><br>");
                out.println("  <form action=\"CrearEmpleado.jsp\" method=\"post\">\n"
                        + "            <input type=\"hidden\" name=\"usernameS\" value=\"" + request.getParameter("usernameS") + "\"/>\n"
                        + "            <input type=\"submit\" value=\"Volver al registro\"/>\n"
                        + "        </form>");
                out.println("<br> <form action=\"PrincipalAdmin.jsp\" method=\"post\">\n"
                        + "            <input type=\"hidden\" name=\"usernameS\" value=\"" + request.getParameter("usernameS") + "\"/>\n"
                        + "            <input type=\"submit\" value=\"Volver al inicio\"/>\n"
                        + "        </form>");
                out.println("<br> <form action=\"Empleados.jsp\" method=\"post\">\n"
                        + "            <input type=\"hidden\" name=\"usernameS\" value=\"" + request.getParameter("usernameS") + "\"/>\n"
                        + "            <input type=\"submit\" value=\"Volver a la sección de empleados\"/>\n"
                        + "        </form>");

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
