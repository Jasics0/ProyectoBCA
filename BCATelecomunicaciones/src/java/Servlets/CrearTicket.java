/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DAO.TicketsJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DTO.Tickets;

/**
 *
 * @author Retr0
 */
@WebServlet(name = "CrearTicket", urlPatterns = {"/CrearTicket"})
public class CrearTicket extends HttpServlet {

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
            TicketsJpaController daoTick = new TicketsJpaController(emf);
            Date date = new Date();
            Tickets ticket = new Tickets(request.getParameter("descripcion"), date, null, request.getParameter("prioridad"), true, request.getParameter("cedula"), request.getParameter("usernameS"));
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AgregarPlan</title>");
            out.println("</head>");
            out.println("<body>");

            try {
                daoTick.create(ticket);
                request.setAttribute("usernameS", request.getParameter("usernameS"));
                request.getRequestDispatcher("Tickets").forward(request, response);
            } catch (Exception a) {
                out.println("<h1>Error al crear el ticket.</h1>");
                out.println("<label>No se pudo crear este ticket, verifique que los datos estén escritos correctamente.</label><br><br>");
                out.println("  <form action=\"CrearTicket.jsp\" method=\"post\">\n"
                        + "            <input type=\"hidden\" name=\"usernameS\" value=\"" + request.getParameter("usernameS") + "\"/>\n"
                        + "            <input type=\"submit\" value=\"Volver al registro\"/>\n"
                        + "        </form>");
                out.println("<br> <form action=\"PrincipalAdmin.jsp\" method=\"post\">\n"
                        + "            <input type=\"hidden\" name=\"usernameS\" value=\"" + request.getParameter("usernameS") + "\"/>\n"
                        + "            <input type=\"submit\" value=\"Volver al inicio\"/>\n"
                        + "        </form>");
                out.println("<br> <form action=\"Tickets\" method=\"post\">\n"
                        + "            <input type=\"hidden\" name=\"usernameS\" value=\"" + request.getParameter("usernameS") + "\"/>\n"
                        + "            <input type=\"submit\" value=\"Volver a la sección de planes\"/>\n"
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
