/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DAO.PlanesJpaController;
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
@WebServlet(name = "OpcionesPlanes", urlPatterns = {"/OpcionesPlanes"})
public class OpcionesPlanes extends HttpServlet {

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
            String usuario = request.getParameter("usernameS");
            out.println("<!DOCTYPE html>");
            out.println("<html>\n"
                    + "    <head>\n"
                    + "        <title>BCA | Planes</title>\n"
                    + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                    + "        <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">"
                    + "        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/OpcionesPlanesServlets.css\">"
                    + "        <link rel=\"icon\" href=\"images/Icon.ico\">"
                    + "    </head>\n"
                    + "    <body>\n"
                    + "        <h1>Planes</h1>\n"
                    + "        <h3>Usuario:" + usuario + "</h3>\n"
                    + "        <form class=\"pantalla text-center\" method=\"post\" action=\"GestionarPlan\">\n"
                    + "        <div class=\"campos\">\n"        
                    + "            <label>Código: </label> <input type=\"text\" name=\"codigo\"/>\n"
                    + "        </div>\n"
                    + "<br>\n"
                    +"         <button class=\"btn btn-primary\" name=\"buttom-b\" id=\"ingresar\" type=\"submit\" value=\"Buscar plan\"> Buscar plan</button>"
                    +"         <button class=\"btn btn-primary\" name=\"buttom-b\" id=\"ingresar\" type=\"submit\" value=\"Borrar plan\"> Borrar plan</button>"
                    + "        <input type=\"hidden\" name=\"usernameS\" value=\"" + usuario + "\"/>\n"
                    + "\n");
            out.println("<div><table border><<thead><tr><th scope=\"col\"> Codigo</th><th scope=\"col\"> Plan </th></tr></thead><tbody>");
            for (int i = 0; i < daoPlan.getPlanesCount(); i++) {
                out.println("<tr>");
                out.println("<td><label> " + daoPlan.findPlanesEntities().get(i).getCodProducto() + "</label> </td>");
                out.println("<td><label> " + daoPlan.findPlanesEntities().get(i).getNombre() + "</label> </td>");
                out.println("</tr>");
            }
            out.println("</tbody></table></div></form>"
                    + "        <form action=\"AgregarPlan.jsp\" method=\"post\">\n"
                    + "            <input type=\"hidden\" name=\"usernameS\" value=\"" + usuario + "\"/>\n"
                    + "            <button class=\"btn btn-primary\" id=\"atras\" type=\"submit\"> Agregar plan </button>\n"         
                    + "        </form>\n"
                    + "       <form action=\"PrincipalAdmin.jsp\" method=\"post\">\n"
                    + "            <input type=\"hidden\" name=\"usernameS\" value=\"" + usuario + "\"/>\n"
                    + "            <button class=\"btn btn-primary\" id=\"atras\" type=\"submit\"> Atrás</button>\n"
                    + "        </form>\n"
                    + " <script src=\"js/ContraseñaOcultar.js\" type=\"text/javascript\"></script>"
                    + "<script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\" integrity=\"sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj\" crossorigin=\"anonymous\"></script>"
                    + "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx\" crossorigin=\"anonymous\"></script>"
                    + "</body> </html>");
                   

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
