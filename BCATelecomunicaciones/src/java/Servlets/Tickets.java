/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DAO.TicketsJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
@WebServlet(name = "Tickets", urlPatterns = {"/Tickets"})
public class Tickets extends HttpServlet {


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
            String usuario = request.getParameter("usernameS");
            out.println("<!DOCTYPE html>");
            out.println("<html>\n"
                    + "    <head>\n"
                    + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                    + "        <title>BCA | Tckets</title>\n"
                    + "    </head>\n"
                    + "    <body>\n"
                    + "        <h1>Tickets</h1>\n"
                    + "        <h3>Usuario:" + usuario + "</h3>\n"
                    + "        <form method=\"post\" action=\"GestionarPlan\">\n"
                    + "            <label>Cedula Cliente: </label> <input type=\"text\" name=\"codigo\"/>\n"
                    + "<br><br>\n"
                    + "            <input type=\"submit\" name=\"buttom-b\" value=\"Buscar ticket\"/>\n"
                    + "<br><br>\n"
                    + "            <input type=\"submit\" name=\"buttom-b\" value=\"Editar ticket\"/>\n"
                    + "<br><br>\n"
                    + "            <input type=\"hidden\" name=\"usernameS\" value=\"" + usuario + "\"/>\n"
                    + "        </form>\n"
                    + "\n"
                    + "       <br> <form action=\"CrearTicket.jsp\" method=\"post\">\n"
                    + "            <input type=\"hidden\" name=\"usernameS\" value=\"" + usuario + "\"/>\n"
                    + "            <input type=\"submit\"  value=\"Crear ticket\"/>\n"
                    + "        </form>\n"
                    + "       <br> <form action=\"PrincipalAdmin.jsp\" method=\"post\">\n"
                    + "            <input type=\"hidden\" name=\"usernameS\" value=\"" + usuario + "\"/>\n"
                    + "            <input type=\"submit\" value=\"Atrás\"/>\n"
                    + "        </form>\n"
                    + "<br>\n");
            out.println("<table border><tr><td><label> Codigo </label> <td><label> Cedula </label> </td>  <td><label> Usuario </label> </td> </tr>");
            for (int i = 0; i < daoTick.getTicketsCount(); i++) {
                out.println("<tr>");
                out.println("<td><label> " + daoTick.findTicketsEntities().get(i).getCodigoTicket() + "</label> </td>");
                out.println("<td><label> " + daoTick.findTicketsEntities().get(i).getIdCliente().getIdCliente() + "</label> </td>");
                out.println("<td><label> " + daoTick.findTicketsEntities().get(i).getIdCliente().getNombre() + "</label> </td>");

                out.println("</tr>");

            }
            out.println("</table></body> </html>");

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
