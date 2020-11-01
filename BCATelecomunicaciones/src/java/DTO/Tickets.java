/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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

    public Tickets(String codigoTicket) {
        this.codigoTicket = codigoTicket;
    }

    public String getCodigoTicket() {
        return codigoTicket;
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
