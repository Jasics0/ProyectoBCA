/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Retr0
 */
public class DataBase {

    Connection con = null;

    public DataBase() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bca_telecomunicaciones?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    public ResultSet consulta(String Consulta) {
        ResultSet rs = null;
        try {
            Statement s = con.createStatement();
            rs = s.executeQuery(Consulta);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
        return rs;
    }

    public static void main(String[] args) throws SQLException {
        DataBase b = new DataBase();
    }

    public String ejecutar(String Consulta) {
        try {
            Statement s = con.createStatement();
            s.executeUpdate(Consulta);
            return "Realizado";
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
}
