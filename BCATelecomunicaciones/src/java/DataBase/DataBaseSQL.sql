CREATE TABLE usuarios (username VARCHAR(10) PRIMARY KEY, contraseña VARCHAR(100));
CREATE TABLE empleados (id_empleado VARCHAR(10), username VARCHAR(10),nombre VARCHAR(20), rol VARCHAR(10), direccion VARCHAR(15),telefono VARCHAR(10), PRIMARY KEY(id_empleado), FOREIGN KEY (username) REFERENCES usuarios(username));
CREATE TABLE planes (cod_producto VARCHAR(10) PRIMARY KEY, nombre VARCHAR(10), descripcion VARCHAR(150));
CREATE TABLE clientes (id_cliente VARCHAR(10) PRIMARY KEY,nombre VARCHAR(20) NOT NULL,direccion VARCHAR(15), estado_cliente BOOLEAN,plan VARCHAR(10),telefono VARCHAR(10), FOREIGN KEY (plan) REFERENCES planes(cod_producto));
CREATE TABLE facturas(codigo_factura VARCHAR(10), id_cliente VARCHAR(10), id_empleado VARCHAR(10), fecha_inicial DATE,fecha_final DATE, valor DOUBLE, estado BOOLEAN,PRIMARY KEY(codigo_factura),FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente),FOREIGN KEY (id_empleado) REFERENCES empleados(id_empleado));
CREATE TABLE pagos (codigo_pago VARCHAR(10) PRIMARY KEY, codigo_factura VARCHAR(10), medio_pago VARCHAR(12),FOREIGN KEY(codigo_factura) REFERENCES facturas(codigo_factura));
CREATE TABLE tickets (codigo_ticket VARCHAR(10) PRIMARY KEY,id_empleado VARCHAR(10), id_cliente VARCHAR(10), servicio VARCHAR(100), fecha_inicial DATE,fecha_final DATE, prioridad VARCHAR (10), estado_ticket BOOLEAN,FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente),FOREIGN KEY (id_empleado) REFERENCES empleados(id_empleado));

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
                    + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                    + "        <title>BCA | Planes</title>\n"
                    + "    </head>\n"
                    + "    <body>\n"
                    + "        <h1>Planes</h1>\n"
                    + "        <h3>Usuario:" + usuario + "</h3>\n"
                    + "        <form method=\"post\" action=\"GestionarPlan\">\n"
                    + "            <label>Código: </label> <input type=\"text\" name=\"codigo\"/>\n"
                    + "<br><br>\n"
                    + "            <input type=\"submit\" name=\"buttom-b\" value=\"Buscar plan\"/>\n"
                    + "<br><br>\n"
                    + "            <input type=\"submit\" name=\"buttom-b\" value=\"Borrar plan\"/>\n"
                    + "<br><br>\n"
                    + "            <input type=\"hidden\" name=\"usernameS\" value=\"" + usuario + "\"/>\n"
                    + "        </form>\n"
                    + "\n"
                    + "       <br> <form action=\"AgregarPlan.jsp\" method=\"post\">\n"
                    + "            <input type=\"hidden\" name=\"usernameS\" value=\"" + usuario + "\"/>\n"
                    + "            <input type=\"submit\"  value=\"Agregar plan\"/>\n"
                    + "        </form>\n"
                    + "       <br> <form action=\"PrincipalAdmin.jsp\" method=\"post\">\n"
                    + "            <input type=\"hidden\" name=\"usernameS\" value=\"" + usuario + "\"/>\n"
                    + "            <input type=\"submit\" value=\"Atrás\"/>\n"
                    + "        </form>\n"
                    + "<br>\n");
            out.println("<table border><tr><td><label> Codigo </label> <td><label> Plan </label> </td> </tr>");
            for (int i = 0; i < daoPlan.getPlanesCount(); i++) {
                out.println("<tr>");
                out.println("<td><label> " + daoPlan.findPlanesEntities().get(i).getCodProducto() + "</label> </td>");
                out.println("<td><label> " + daoPlan.findPlanesEntities().get(i).getNombre() + "</label> </td>");
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
