package Servlets;

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

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BCATelecomunicacionesPU");
        UsuariosJpaController daoUser = new UsuariosJpaController(emf);
        Usuarios user = new Usuarios();
        user.setUsername(request.getParameter("username").toLowerCase());
        user.setContraseña(request.getParameter("password"));
        Empleados emp = user.getEmpleado();
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>BCA | Login</title>");
            out.println("</head>");
            out.println("<body>");

            if (daoUser.findUsuarios(user.getUsername()).getContraseña().equals(user.getContraseña())) {
                if (emp.getEstadoEmpleado() != false) {
                    if (emp.getRol().equals("admin")) {
                        request.setAttribute("usernameS", user.getUsername());
                        request.getRequestDispatcher("PrincipalAdmin.jsp").forward(request, response);

                    } else {
                        request.setAttribute("usernameS", user.getUsername());
                        request.getRequestDispatcher("Principal.jsp").forward(request, response);
                    }
                } else {
                    out.println("<h1>Error al ingresar:</h1>");
                    out.println("<label>Esté usuario ha sido suspendido.</label><br><br>");
                    out.println("<button onclick=\"location.href='index.jsp'\">Volver al Login</button>");
                }

            } else {
                out.println("<h1>Error al ingresar:</h1>");
                out.println("<label>No se pudo ingresar, verifique que su usuario y contraseña estén escritos correctamente.</label><br><br>");
                out.println("<button onclick=\"location.href='index.jsp'\">Volver al Login</button>");
            }
            out.println("</body>");
            out.println("</html>");

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
}
