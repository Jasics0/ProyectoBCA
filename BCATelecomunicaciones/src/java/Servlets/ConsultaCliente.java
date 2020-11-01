/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DAO.ClientesJpaController;
import DAO.PlanesJpaController;
import DTO.Clientes;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "ConsultaCliente", urlPatterns = {"/ConsultaCliente"})
public class ConsultaCliente extends HttpServlet {

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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BCATelecomunicacionesPU");
        ClientesJpaController daoClient = new ClientesJpaController(emf);
        PlanesJpaController daoPlan= new PlanesJpaController(emf);

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>BCA | Error de busqueda</title>");
            out.println("</head>");
            out.println("<body>");
            try {
                Clientes client = daoClient.findClientes(request.getParameter("id"));
                String direccion = request.getParameter("buttom-b").toLowerCase();
                request.setAttribute("usernameS", request.getParameter("usernameS"));
                request.setAttribute("nombre", client.getNombre());
                request.setAttribute("direccion", client.getDireccion());
                request.setAttribute("telefono", client.getTelefono());
                request.setAttribute("id", client.getIdCliente());
                request.setAttribute("plan", (client.getPlan()==null) ? "Sin plan" : client.getPlan().getNombre());
                request.setAttribute("estado", (client.getEstadoCliente()) ? "Activo" : "Desactivo");
                request.setAttribute("lista",daoPlan.findPlanesEntities());

                if (direccion.contains("buscar")) {
                    request.getRequestDispatcher("BuscarCliente.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("EditarCliente.jsp").forward(request, response);
                }
            } catch (Exception a) {
                out.println("<h1>Error al buscar cliente.</h1>");
                out.println("<label>No se pudo encnotrar este empleado, verifique que el empleado exista, y los datos estén escritos correctamente.</label><br><br>");
                out.println("  <form action=\"BuscarEmpleado\" method=\"post\">\n"
                        + "            <input type=\"hidden\" name=\"usernameS\" value=\"" + request.getParameter("usernameS") + "\"/>\n"
                        + "            <input type=\"submit\" value=\"Volver a la busqueda\"/>\n"
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
