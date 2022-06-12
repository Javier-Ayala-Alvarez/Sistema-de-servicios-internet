/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mejia
 */
@Entity
@Table(name = "contrato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contrato.findAll", query = "SELECT c FROM Contrato c"),
    @NamedQuery(name = "Contrato.findByIdcontrato", query = "SELECT c FROM Contrato c WHERE c.idcontrato = :idcontrato"),
    @NamedQuery(name = "Contrato.findByFechainicio", query = "SELECT c FROM Contrato c WHERE c.fechainicio = :fechainicio"),
    @NamedQuery(name = "Contrato.findByVigente", query = "SELECT c FROM Contrato c WHERE c.vigente = :vigente"),
    @NamedQuery(name = "Contrato.findByMotivobaja", query = "SELECT c FROM Contrato c WHERE c.motivobaja = :motivobaja"),
    @NamedQuery(name = "Contrato.findByFechabaja", query = "SELECT c FROM Contrato c WHERE c.fechabaja = :fechabaja"),
    @NamedQuery(name = "Contrato.findByDireccioncontrato", query = "SELECT c FROM Contrato c WHERE c.direccioncontrato = :direccioncontrato")})
public class Contrato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idcontrato")
    private Integer idcontrato;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechainicio")
    @Temporal(TemporalType.DATE)
    private Date fechainicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "vigente")
    private String vigente;
    @Size(max = 100)
    @Column(name = "motivobaja")
    private String motivobaja;
    @Column(name = "fechabaja")
    @Temporal(TemporalType.DATE)
    private Date fechabaja;
    @Size(max = 255)
    @Column(name = "Direccion_contrato")
    private String direccioncontrato;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcontrato")
    private List<Factura> facturaList;
    @JoinColumn(name = "idcliente", referencedColumnName = "id_duicliente")
    @ManyToOne(optional = false)
    private Cliente idcliente;
    @JoinColumn(name = "idempleado", referencedColumnName = "id_duiempleado")
    @ManyToOne(optional = false)
    private Empleado idempleado;
    @JoinColumn(name = "idservicio", referencedColumnName = "idservicio")
    @ManyToOne(optional = false)
    private Servicio idservicio;

    public Contrato() {
    }

    public Contrato(Integer idcontrato) {
        this.idcontrato = idcontrato;
    }

    public Contrato(Integer idcontrato, Date fechainicio, String vigente) {
        this.idcontrato = idcontrato;
        this.fechainicio = fechainicio;
        this.vigente = vigente;
    }

    public Integer getIdcontrato() {
        return idcontrato;
    }

    public void setIdcontrato(Integer idcontrato) {
        this.idcontrato = idcontrato;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public String getVigente() {
        return vigente;
    }

    public void setVigente(String vigente) {
        this.vigente = vigente;
    }

    public String getMotivobaja() {
        return motivobaja;
    }

    public void setMotivobaja(String motivobaja) {
        this.motivobaja = motivobaja;
    }

    public Date getFechabaja() {
        return fechabaja;
    }

    public void setFechabaja(Date fechabaja) {
        this.fechabaja = fechabaja;
    }

    public String getDireccioncontrato() {
        return direccioncontrato;
    }

    public void setDireccioncontrato(String direccioncontrato) {
        this.direccioncontrato = direccioncontrato;
    }

    @XmlTransient
    public List<Factura> getFacturaList() {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList) {
        this.facturaList = facturaList;
    }

    public Cliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Cliente idcliente) {
        this.idcliente = idcliente;
    }

    public Empleado getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Empleado idempleado) {
        this.idempleado = idempleado;
    }

    public Servicio getIdservicio() {
        return idservicio;
    }

    public void setIdservicio(Servicio idservicio) {
        this.idservicio = idservicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcontrato != null ? idcontrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contrato)) {
            return false;
        }
        Contrato other = (Contrato) object;
        if ((this.idcontrato == null && other.idcontrato != null) || (this.idcontrato != null && !this.idcontrato.equals(other.idcontrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Contrato[ idcontrato=" + idcontrato + " ]";
    }
    
}
