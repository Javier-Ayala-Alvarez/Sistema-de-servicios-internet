/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mejia
 */
@Entity
@Table(name = "servicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servicio.findAll", query = "SELECT s FROM Servicio s"),
    @NamedQuery(name = "Servicio.findByIdservicio", query = "SELECT s FROM Servicio s WHERE s.idservicio = :idservicio"),
    @NamedQuery(name = "Servicio.findByServicio", query = "SELECT s FROM Servicio s WHERE s.servicio = :servicio"),
    @NamedQuery(name = "Servicio.findByPrecioservicio", query = "SELECT s FROM Servicio s WHERE s.precioservicio = :precioservicio"),
    @NamedQuery(name = "Servicio.findByEstado", query = "SELECT s FROM Servicio s WHERE s.estado = :estado"),
    @NamedQuery(name = "Servicio.findByPeriodoMes", query = "SELECT s FROM Servicio s WHERE s.periodoMes = :periodoMes"),
    @NamedQuery(name = "Servicio.findByMora", query = "SELECT s FROM Servicio s WHERE s.mora = :mora"),
    @NamedQuery(name = "Servicio.findBySancion", query = "SELECT s FROM Servicio s WHERE s.sancion = :sancion")})
public class Servicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idservicio")
    private Integer idservicio;
    @Size(max = 30)
    @Column(name = "servicio")
    private String servicio;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precioservicio")
    private BigDecimal precioservicio;
    @Column(name = "estado")
    private Boolean estado;
    @Column(name = "periodo_mes")
    private Integer periodoMes;
    @Column(name = "mora")
    private BigDecimal mora;
    @Column(name = "sancion")
    private BigDecimal sancion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idservicio")
    private List<Contrato> contratoList;

    public Servicio() {
    }

    public Servicio(Integer idservicio) {
        this.idservicio = idservicio;
    }

    public Integer getIdservicio() {
        return idservicio;
    }

    public void setIdservicio(Integer idservicio) {
        this.idservicio = idservicio;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public BigDecimal getPrecioservicio() {
        return precioservicio;
    }

    public void setPrecioservicio(BigDecimal precioservicio) {
        this.precioservicio = precioservicio;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Integer getPeriodoMes() {
        return periodoMes;
    }

    public void setPeriodoMes(Integer periodoMes) {
        this.periodoMes = periodoMes;
    }

    public BigDecimal getMora() {
        return mora;
    }

    public void setMora(BigDecimal mora) {
        this.mora = mora;
    }

    public BigDecimal getSancion() {
        return sancion;
    }

    public void setSancion(BigDecimal sancion) {
        this.sancion = sancion;
    }

    @XmlTransient
    public List<Contrato> getContratoList() {
        return contratoList;
    }

    public void setContratoList(List<Contrato> contratoList) {
        this.contratoList = contratoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idservicio != null ? idservicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicio)) {
            return false;
        }
        Servicio other = (Servicio) object;
        if ((this.idservicio == null && other.idservicio != null) || (this.idservicio != null && !this.idservicio.equals(other.idservicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Servicio[ idservicio=" + idservicio + " ]";
    }
    
}
