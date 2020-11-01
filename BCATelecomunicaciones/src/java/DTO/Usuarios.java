/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DAO.EmpleadosJpaController;
import DataBase.DataBase;
import Logic.Criptografia;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Retr0
 */
@Entity
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
    @NamedQuery(name = "Usuarios.findByUsername", query = "SELECT u FROM Usuarios u WHERE u.username = :username"),
    @NamedQuery(name = "Usuarios.findByContrase\u00f1a", query = "SELECT u FROM Usuarios u WHERE u.contrase\u00f1a = :contrase\u00f1a")})
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Column(name = "contrase\u00f1a")
    private String contraseña;
    @OneToMany(mappedBy = "user")
    private Collection<Empleados> empleadosCollection;

    public Usuarios() {
    }

    public Usuarios(String username) {
        this.username = username;
    }

    public Usuarios(String username, String contraseña) {
        this.username = username;
        this.contraseña = contraseña;
    }

    public void setContraseña(String contraseña) {
        Criptografia cripto = new Criptografia();
        this.contraseña = cripto.encriptar(contraseña);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContraseña() {
        return contraseña;
    }

    @XmlTransient
    public Collection<Empleados> getEmpleadosCollection() {
        return empleadosCollection;
    }

    public void setEmpleadosCollection(Collection<Empleados> empleadosCollection) {
        this.empleadosCollection = empleadosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    public Empleados getEmpleado() {
        DataBase b = new DataBase();
        ResultSet rs = b.consulta("SELECT id_empleado FROM empleados WHERE username = '" + username + "'");
        try {
            rs.next();
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("BCATelecomunicacionesPU");
            EmpleadosJpaController daoEmp = new EmpleadosJpaController(emf);
            Empleados emp = daoEmp.findEmpleados(rs.getString(1));
            return emp;
        } catch (Exception a) {
            return null;
        }

    }

    @Override
    public String toString() {
        return "DTO.Usuarios[ username=" + username + " ]";
    }

}
