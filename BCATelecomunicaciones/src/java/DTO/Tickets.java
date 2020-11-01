/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DAO.ClientesJpaController;
import DAO.EmpleadosJpaController;
import DataBase.DataBase;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Retr0
 */
@Entity
@Table(name = "tickets")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tickets.findAll", query = "SELECT t FROM Tickets t"),
    @NamedQuery(name = "Tickets.findByCodigoTicket", query = "SELECT t FROM Tickets t WHERE t.codigoTicket = :codigoTicket"),
    @NamedQuery(name = "Tickets.findByServicio", query = "SELECT t FROM Tickets t WHERE t.servicio = :servicio"),
    @NamedQuery(name = "Tickets.findByFechaInicial", query = "SELECT t FROM Tickets t WHERE t.fechaInicial = :fechaInicial"),
    @NamedQuery(name = "Tickets.findByFechaFinal", query = "SELECT t FROM Tickets t WHERE t.fechaFinal = :fechaFinal"),
    @NamedQuery(name = "Tickets.findByPrioridad", query = "SELECT t FROM Tickets t WHERE t.prioridad = :prioridad"),
    @NamedQuery(name = "Tickets.findByEstadoTicket", query = "SELECT t FROM Tickets t WHERE t.estadoTicket = :estadoTicket")})
public class Tickets implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigo_ticket")
    private String codigoTicket;
    @Column(name = "servicio")
    private String servicio;
    @Column(name = "fecha_inicial")
    @Temporal(TemporalType.DATE)
    private Date fechaInicial;
    @Column(name = "fecha_final")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    @Column(name = "prioridad")
    private String prioridad;
    @Column(name = "estado_ticket")
    private Boolean estadoTicket;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    @ManyToOne
    private Clientes idCliente;
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    @ManyToOne
    private Empleados idEmpleado;

    public Tickets() {
    }

    public Tickets(String servicio, Date fechaInicial, Date fechaFinal, String prioridad, Boolean estadoTicket, String idCliente, String username) {
        generarCod();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BCATelecomunicacionesPU");
        this.servicio = servicio;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.prioridad = prioridad;
        this.estadoTicket = estadoTicket;
        this.idCliente = new ClientesJpaController(emf).findClientes(idCliente);
        this.idEmpleado=encontrarEmpleado(username, emf);
    }

    public Tickets(String codigoTicket) {
        this.codigoTicket = codigoTicket;
    }

    public void generarCod() {
        DataBase db = new DataBase();
        ResultSet rs = db.consulta("select * from tickets order by codigo_ticket desc limit 1");
        ResultSet rs2 = db.consulta("select count(*) from tickets");
        try {
            rs.next();
            rs2.next();

            if (Integer.parseInt(rs2.getString(1)) == 0) {
                codigoTicket = "T0001";
            } else {
                int i = Integer.parseInt(rs.getString(1).charAt((rs.getString(1).length()) - 1) + "");
                codigoTicket = "T000" + (i + 1);
            }
        } catch (SQLException ex) {

        }
    }

    public String getCodigoTicket() {
        return codigoTicket;
    }

    public  Empleados encontrarEmpleado(String username, EntityManagerFactory emf) {
        String usuario="";
        
        for (int i = 0; i < username.length(); i++) {
            if(username.charAt(i)!=' '){
            usuario+=username.charAt(i);
            }
        }
        DataBase db = new DataBase();
        ResultSet rs = db.consulta("select * from empleados WHERE (username='" + usuario + "')");
        try {
            rs.next();
            Empleados emp = new EmpleadosJpaController(emf).findEmpleados(rs.getString(1));
            return emp;
        } catch (SQLException ex) {
            return null;
        }
    }

    public void setCodigoTicket(String codigoTicket) {
        this.codigoTicket = codigoTicket;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public Boolean getEstadoTicket() {
        return estadoTicket;
    }

    public void setEstadoTicket(Boolean estadoTicket) {
        this.estadoTicket = estadoTicket;
    }

    public Clientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Clientes idCliente) {
        this.idCliente = idCliente;
    }

    public Empleados getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleados idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoTicket != null ? codigoTicket.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tickets)) {
            return false;
        }
        Tickets other = (Tickets) object;
        if ((this.codigoTicket == null && other.codigoTicket != null) || (this.codigoTicket != null && !this.codigoTicket.equals(other.codigoTicket))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Tickets[ codigoTicket=" + codigoTicket + " ]";
    }


}
