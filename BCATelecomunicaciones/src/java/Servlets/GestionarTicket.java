/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DAO.TicketsJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DTO.Tickets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Retr0
 */
@WebServlet(name = "GestionarTicket", urlPatterns = {"/GestionarTicket"})
public class GestionarTicket extends HttpServlet {

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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BCATelecomunicacionesPU");
        TicketsJpaController daoTick = new TicketsJpaController(emf);
        Tickets tick = null;
        String prioridad = "";

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>BCA | Error de busqueda</title>");
            out.println("</head>");
            out.println("<body>");
            try {
                int i = 0;
                for (i = 0; i < daoTick.getTicketsCount(); i++) {
                    if (daoTick.findTicketsEntities().get(i).getEstadoTicket() == true && daoTick.findTicketsEntities().get(i).getIdCliente().getIdCliente().equals(request.getParameter("codigo"))) {
                        tick = daoTick.findTicketsEntities().get(i);
                        break;
                    }
                }

                String direccion = request.getParameter("buttom-b").toLowerCase();
                request.setAttribute("usernameS", request.getParameter("usernameS"));
                request.setAttribute("codigo", tick.getCodigoTicket());
                request.setAttribute("descripcion", tick.getServicio());
                request.setAttribute("cedula", tick.getIdCliente().getIdCliente());
                
                switch (tick.getPrioridad().charAt(0)) {
                    case 'u':
                        prioridad = "urgente";
                        break;
                    case 'm':
                        prioridad = "media";
                        break;
                    case 'b':
                        prioridad = "baja";
                        break;
                    default:
                        break;
                }
                request.setAttribute("prioridad", prioridad);
                request.setAttribute("estado", (tick.getEstadoTicket()) ? "Activo" : "Cerrado");
                if (i == (daoTick.getTicketsCount() - 1) && tick == null) {
                    throw new Exception();
                }
                if (direccion.contains("buscar")) {
                    request.getRequestDispatcher("BuscarTicket.jsp").forward(request, response);
                } else {
                    //daoPlan.destroy(plan.getCodProducto());
                    request.getRequestDispatcher("EditarTicket.jsp").forward(request, response);
                }
            } catch (Exception a) {
                out.println("<h1>Error al buscar el plan.</h1>");
                out.println("<label>No se pudo encnotrar este plan, verifique que el plan exista, y los datos estén escritos correctamente.</label><br><br>");
                out.println("<br> <form action=\"PrincipalAdmin.jsp\" method=\"post\">\n"
                        + "            <input type=\"hidden\" name=\"usernameS\" value=\"" + request.getParameter("usernameS") + "\"/>\n"
                        + "            <input type=\"submit\" value=\"Volver al inicio\"/>\n"
                        + "        </form>");
                out.println("<br> <form action=\"OpcionesPlanes\" method=\"post\">\n"
                        + "            <input type=\"hidden\" name=\"usernameS\" value=\"" + request.getParameter("usernameS") + "\"/>\n"
                        + "            <input type=\"submit\" value=\"Volver a la sección de planes\"/>\n"
                        + "        </form>");
            }

            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            System.out.println(ex);
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
