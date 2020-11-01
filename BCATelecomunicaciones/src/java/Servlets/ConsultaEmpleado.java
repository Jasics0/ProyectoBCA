/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DAO.EmpleadosJpaController;
import DTO.Empleados;
import Logic.Criptografia;
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
@WebServlet(name = "ConsultaEmpleado", urlPatterns = {"/ConsultaEmpleado"})
public class ConsultaEmpleado extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BCATelecomunicacionesPU");
        EmpleadosJpaController daoEmp = new EmpleadosJpaController(emf);

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>BCA | Error de busqueda</title>");
            out.println("</head>");
            out.println("<body>");
            try {
                Empleados emp = daoEmp.findEmpleados(request.getParameter("username-empleado"));
                String direccion = request.getParameter("buttom-b").toLowerCase();
                request.setAttribute("usernameS", request.getParameter("usernameS"));
                request.setAttribute("username", emp.getUser().getUsername());
                request.setAttribute("password", new Criptografia().desencriptar(emp.getUser().getContraseña()));
                request.setAttribute("nombre", emp.getNombre());
                request.setAttribute("direccion", emp.getDireccion());
                request.setAttribute("telefono", emp.getTelefono());
                request.setAttribute("id", emp.getIdEmpleado());
                request.setAttribute("rol", emp.getRol());

                request.setAttribute("estado", (emp.getEstadoEmpleado()) ? "Activo" : "Desactivo");

                if (direccion.contains("buscar")) {
                    request.getRequestDispatcher("BuscarEmpleado.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("EditarEmpleado.jsp").forward(request, response);
                }
            } catch (Exception a) {
                out.println("<h1>Error al buscar empleado.</h1>");
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

}
