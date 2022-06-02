/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author mejia
 */
@Entity
@Table(name = "puestos")
@NamedQueries({
    @NamedQuery(name = "Puestos.findAll", query = "SELECT p FROM Puestos p"),
    @NamedQuery(name = "Puestos.findByIdpuesto", query = "SELECT p FROM Puestos p WHERE p.idpuesto = :idpuesto"),
    @NamedQuery(name = "Puestos.findByNombrepuesto", query = "SELECT p FROM Puestos p WHERE p.nombrepuesto = :nombrepuesto"),
    @NamedQuery(name = "Puestos.findBySueldo", query = "SELECT p FROM Puestos p WHERE p.sueldo = :sueldo"),
    @NamedQuery(name = "Puestos.findByEstado", query = "SELECT p FROM Puestos p WHERE p.estado = :estado")})
public class Puestos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpuesto")
    private Integer idpuesto;
    @Size(max = 40)
    @Column(name = "nombrepuesto")
    private String nombrepuesto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "sueldo")
    private Double sueldo;
    @Column(name = "estado")
    private Boolean estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "puesto")
    private List<Empleado> empleadoList;

    public Puestos() {
    }

    public Puestos(Integer idpuesto) {
        this.idpuesto = idpuesto;
    }

    public Integer getIdpuesto() {
        return idpuesto;
    }

    public void setIdpuesto(Integer idpuesto) {
        this.idpuesto = idpuesto;
    }

    public String getNombrepuesto() {
        return nombrepuesto;
    }

    public void setNombrepuesto(String nombrepuesto) {
        this.nombrepuesto = nombrepuesto;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpuesto != null ? idpuesto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Puestos)) {
            return false;
        }
        Puestos other = (Puestos) object;
        if ((this.idpuesto == null && other.idpuesto != null) || (this.idpuesto != null && !this.idpuesto.equals(other.idpuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Puestos[ idpuesto=" + idpuesto + " ]";
    }
    
}
