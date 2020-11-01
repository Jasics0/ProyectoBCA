/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DAO.ClientesJpaController;
import DAO.PlanesJpaController;
import DTO.Clientes;
import com.mysql.cj.xdevapi.Client;
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
@WebServlet(name = "EditarCliente", urlPatterns = {"/EditarCliente"})
public class EditarCliente extends HttpServlet {

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
            String id = request.getParameter("idO");
            ClientesJpaController daoClient = new ClientesJpaController(emf);
            PlanesJpaController daoPlan = new PlanesJpaController(emf);
            Clientes client = new Clientes(request.getParameter("id"), request.getParameter("nombre"), request.getParameter("direccion"), (request.getParameter("estado").equals("activo")) ? true : false, request.getParameter("telefono"), daoPlan.findPlanes(request.getParameter("plan")));
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Editar Empleado</title>");
            out.println("</head>");
            out.println("<body>");

            try {
                daoClient.destroy(id);
                daoClient.create(client);
                request.setAttribute("usernameS", request.getParameter("usernameS"));
                request.getRequestDispatcher("Clientes.jsp").forward(request, response);
            } catch (Exception a) {
                out.println("<h1>Error al editar cliente..</h1>");
                out.println("<h1>" + a.getMessage() + id + "</h1>");

                out.println("<label>No se pudo editar este cliente, verifique que el cliente no esté repetido, y los datos estén escritos correctamente.</label><br><br>");
                out.println("<button onclick=\"location.href='EditarCliente.jsp'\">Volver al registro</button>");
                out.println("<button onclick=\"location.href='PrincipalAdmin.jsp'\">Volver al inicio</button>");
                out.println("<button onclick=\"location.href='Clientes.jsp'\">Volver a la seccion clientes</button>");
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
