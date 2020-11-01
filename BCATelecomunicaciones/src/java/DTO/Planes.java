/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DataBase.DataBase;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "planes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Planes.findAll", query = "SELECT p FROM Planes p"),
    @NamedQuery(name = "Planes.findByCodProducto", query = "SELECT p FROM Planes p WHERE p.codProducto = :codProducto"),
    @NamedQuery(name = "Planes.findByNombre", query = "SELECT p FROM Planes p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Planes.findByDescripcion", query = "SELECT p FROM Planes p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Planes.findByValor", query = "SELECT p FROM Planes p WHERE p.valor = :valor")})
public class Planes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cod_producto")
    private String codProducto;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private Double valor;
    @OneToMany(mappedBy = "plan")
    private Collection<Clientes> clientesCollection;

    public Planes() {
    }

    public Planes(String codProducto) {
        this.codProducto = codProducto;
    }

    public Planes(String nombre, String descripcion, Double valor) {
        generarCod();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void generarCod() {
        DataBase db = new DataBase();
        ResultSet rs = db.consulta("select * from planes order by cod_producto desc limit 1");
        ResultSet rs2 = db.consulta("select count(*) from planes");
        try {
            rs.next();
            rs2.next();

            if (Integer.parseInt(rs2.getString(1)) == 0) {
                codProducto = "P0001";
            } else {
                int i = Integer.parseInt(rs.getString(1).charAt((rs.getString(1).length()) - 1) + "");
                codProducto = "P000" + (i + 1);
            }
        } catch (SQLException ex) {

        }
    }

    @XmlTransient
    public Collection<Clientes> getClientesCollection() {
        return clientesCollection;
    }

    public void setClientesCollection(Collection<Clientes> clientesCollection) {
        this.clientesCollection = clientesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codProducto != null ? codProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Planes)) {
            return false;
        }
        Planes other = (Planes) object;
        if ((this.codProducto == null && other.codProducto != null) || (this.codProducto != null && !this.codProducto.equals(other.codProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Planes[ codProducto=" + codProducto + " ]";
    }

}
