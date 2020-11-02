/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DAO.PlanesJpaController;
import DTO.Planes;
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
@WebServlet(name = "AgregarPlan", urlPatterns = {"/AgregarPlan"})
public class AgregarPlan extends HttpServlet {

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
            PlanesJpaController daoPlan = new PlanesJpaController(emf);
            Planes plan = new Planes(request.getParameter("nombre"), request.getParameter("descripcion"), Double.parseDouble(request.getParameter("valor")));

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AgregarPlan</title>"
                    + "        <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">"
                    + "        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/consultaClienteServlet.css\">"
                    + "        <link rel=\"icon\" href=\"images/Icon.ico\">");
            out.println("</head>");
            out.println("<body>");

            try {
                daoPlan.create(plan);
                request.setAttribute("usernameS", request.getParameter("usernameS"));
                request.getRequestDispatcher("OpcionesPlanes").forward(request, response);
            } catch (Exception a) {
                out.println("<h1>Error al agregrar el plan.</h1>");
                out.println("<div class=\"pantalla text-center\">"
                        + "           <h4>No se pudo agregar este plan, verifique que los datos estén escritos correctamente.</h4>"
                        + "           <form action=\"AgregarPlan.jsp\" method=\"post\">\n"
                        + "                  <input type=\"hidden\" name=\"usernameS\" value=\"" + request.getParameter("usernameS") + "\"/>\n"
                        + "                  <button class=\"btn btn-primary\" id=\"ingresar\" type=\"submit\"> Volver al registro</button>\n"
                        + "            </form>"
                        + "            <br>"
                        + "            <form action=\"PrincipalAdmin.jsp\" method=\"post\">\n"
                        + "                 <input type=\"hidden\" name=\"usernameS\" value=\"" + request.getParameter("usernameS") + "\"/>\n"
                        + "                 <button class=\"btn btn-primary\" id=\"ingresar\" type=\"submit\"> Volver al inicio</button>\n"
                        + "        </form>"
                        + "        <br> "
                        + "        <form action=\"OpcionesPlanes\" method=\"post\">\n"
                        + "            <input type=\"hidden\" name=\"usernameS\" value=\"" + request.getParameter("usernameS") + "\"/>\n"
                        + "            <button class=\"btn btn-primary\" id=\"ingresar\" type=\"submit\"> Volver a la sección de planes</button>\n"
                        + "        </form>"
                        + "  </div>");
                
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
