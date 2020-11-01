/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Retr0
 */
@Entity
@Table(name = "empleados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleados.findAll", query = "SELECT e FROM Empleados e"),
    @NamedQuery(name = "Empleados.findByIdEmpleado", query = "SELECT e FROM Empleados e WHERE e.idEmpleado = :idEmpleado"),
    @NamedQuery(name = "Empleados.findByNombre", query = "SELECT e FROM Empleados e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Empleados.findByRol", query = "SELECT e FROM Empleados e WHERE e.rol = :rol"),
    @NamedQuery(name = "Empleados.findByDireccion", query = "SELECT e FROM Empleados e WHERE e.direccion = :direccion"),
    @NamedQuery(name = "Empleados.findByEstadoEmpleado", query = "SELECT e FROM Empleados e WHERE e.estadoEmpleado = :estadoEmpleado"),
    @NamedQuery(name = "Empleados.findByTelefono", query = "SELECT e FROM Empleados e WHERE e.telefono = :telefono")})
public class Empleados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_empleado")
    private String idEmpleado;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "rol")
    private String rol;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "estado_empleado")
    private Boolean estadoEmpleado;
    @Column(name = "telefono")
    private String telefono;
    @OneToMany(mappedBy = "idEmpleado")
    private Collection<Tickets> ticketsCollection;
    @OneToMany(mappedBy = "idEmpleado")
    private Collection<Facturas> facturasCollection;
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ManyToOne
    private Usuarios user;

    public Empleados() {
    }

    public Empleados(String idEmpleado, String nombre, String rol, String direccion, Boolean estadoEmpleado, String telefono, Usuarios username) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.rol = rol;
        this.direccion = direccion;
        this.estadoEmpleado = estadoEmpleado;
        this.telefono = telefono;
        this.user = username;
    }

    public Empleados(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        String nombreBien = "";
        for (int i = 0; i < nombre.length(); i++) {
            if (i == 0) {
                nombreBien += (nombre.charAt(i) + "").toUpperCase();

            } else if (nombre.charAt(i) == ' ') {
                i++;
                nombreBien += " ";
                nombreBien += (nombre.charAt(i) + "").toUpperCase();
            } else {
                nombreBien += nombre.charAt(i);
            }
        }
        return nombreBien;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Boolean getEstadoEmpleado() {
        return estadoEmpleado;
    }

    public void setEstadoEmpleado(Boolean estadoEmpleado) {
        this.estadoEmpleado = estadoEmpleado;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @XmlTransient
    public Collection<Tickets> getTicketsCollection() {
        return ticketsCollection;
    }

    public void setTicketsCollection(Collection<Tickets> ticketsCollection) {
        this.ticketsCollection = ticketsCollection;
    }

    @XmlTransient
    public Collection<Facturas> getFacturasCollection() {
        return facturasCollection;
    }

    public void setFacturasCollection(Collection<Facturas> facturasCollection) {
        this.facturasCollection = facturasCollection;
    }

    public Usuarios getUser() {
        return user;
    }

    public void setUser(Usuarios username) {
        this.user = username;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpleado != null ? idEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleados)) {
            return false;
        }
        Empleados other = (Empleados) object;
        if ((this.idEmpleado == null && other.idEmpleado != null) || (this.idEmpleado != null && !this.idEmpleado.equals(other.idEmpleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Empleados[ idEmpleado=" + idEmpleado + " ]";
    }

}
