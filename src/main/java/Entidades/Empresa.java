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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author mejia
 */
@Entity
@Table(name = "empresa")
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e"),
    @NamedQuery(name = "Empresa.findByIdNitempresa", query = "SELECT e FROM Empresa e WHERE e.idNitempresa = :idNitempresa"),
    @NamedQuery(name = "Empresa.findByNombreempresa", query = "SELECT e FROM Empresa e WHERE e.nombreempresa = :nombreempresa"),
    @NamedQuery(name = "Empresa.findByTelefonoempresa", query = "SELECT e FROM Empresa e WHERE e.telefonoempresa = :telefonoempresa"),
    @NamedQuery(name = "Empresa.findByDireccionempresa", query = "SELECT e FROM Empresa e WHERE e.direccionempresa = :direccionempresa")})
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "id_nitempresa")
    private String idNitempresa;
    @Size(max = 45)
    @Column(name = "nombreempresa")
    private String nombreempresa;
    @Size(max = 15)
    @Column(name = "telefonoempresa")
    private String telefonoempresa;
    @Size(max = 50)
    @Column(name = "direccionempresa")
    private String direccionempresa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNitempresa")
    private List<Empleado> empleadoList;

    public Empresa() {
    }

    public Empresa(String idNitempresa) {
        this.idNitempresa = idNitempresa;
    }

    public String getIdNitempresa() {
        return idNitempresa;
    }

    public void setIdNitempresa(String idNitempresa) {
        this.idNitempresa = idNitempresa;
    }

    public String getNombreempresa() {
        return nombreempresa;
    }

    public void setNombreempresa(String nombreempresa) {
        this.nombreempresa = nombreempresa;
    }

    public String getTelefonoempresa() {
        return telefonoempresa;
    }

    public void setTelefonoempresa(String telefonoempresa) {
        this.telefonoempresa = telefonoempresa;
    }

    public String getDireccionempresa() {
        return direccionempresa;
    }

    public void setDireccionempresa(String direccionempresa) {
        this.direccionempresa = direccionempresa;
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
        hash += (idNitempresa != null ? idNitempresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.idNitempresa == null && other.idNitempresa != null) || (this.idNitempresa != null && !this.idNitempresa.equals(other.idNitempresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Empresa[ idNitempresa=" + idNitempresa + " ]";
    }
    
}
