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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "empleado")
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e"),
    @NamedQuery(name = "Empleado.findByIdDuiempleado", query = "SELECT e FROM Empleado e WHERE e.idDuiempleado = :idDuiempleado"),
    @NamedQuery(name = "Empleado.findByNombreempleado", query = "SELECT e FROM Empleado e WHERE e.nombreempleado = :nombreempleado"),
    @NamedQuery(name = "Empleado.findByApellidoempleado", query = "SELECT e FROM Empleado e WHERE e.apellidoempleado = :apellidoempleado"),
    @NamedQuery(name = "Empleado.findByTelefonoempleado", query = "SELECT e FROM Empleado e WHERE e.telefonoempleado = :telefonoempleado"),
    @NamedQuery(name = "Empleado.findByDireccionempleado", query = "SELECT e FROM Empleado e WHERE e.direccionempleado = :direccionempleado"),
    @NamedQuery(name = "Empleado.findByEstado", query = "SELECT e FROM Empleado e WHERE e.estado = :estado")})
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "id_duiempleado")
    private String idDuiempleado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "nombreempleado")
    private String nombreempleado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "apellidoempleado")
    private String apellidoempleado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "telefonoempleado")
    private String telefonoempleado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "direccionempleado")
    private String direccionempleado;
    @Column(name = "estado")
    private Boolean estado;
    @OneToMany(mappedBy = "idempleado")
    private List<Factura> facturaList;
    @JoinColumn(name = "id_nitempresa", referencedColumnName = "id_nitempresa")
    @ManyToOne(optional = false)
    private Empresa idNitempresa;
    @JoinColumn(name = "puesto", referencedColumnName = "idpuesto")
    @ManyToOne(optional = false)
    private Puestos puesto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idempleado")
    private List<Contrato> contratoList;
    @OneToMany(mappedBy = "idempleado")
    private List<Usuarios> usuariosList;

    public Empleado() {
    }

    public Empleado(String idDuiempleado) {
        this.idDuiempleado = idDuiempleado;
    }

    public Empleado(String idDuiempleado, String nombreempleado, String apellidoempleado, String telefonoempleado, String direccionempleado) {
        this.idDuiempleado = idDuiempleado;
        this.nombreempleado = nombreempleado;
        this.apellidoempleado = apellidoempleado;
        this.telefonoempleado = telefonoempleado;
        this.direccionempleado = direccionempleado;
    }

    public String getIdDuiempleado() {
        return idDuiempleado;
    }

    public void setIdDuiempleado(String idDuiempleado) {
        this.idDuiempleado = idDuiempleado;
    }

    public String getNombreempleado() {
        return nombreempleado;
    }

    public void setNombreempleado(String nombreempleado) {
        this.nombreempleado = nombreempleado;
    }

    public String getApellidoempleado() {
        return apellidoempleado;
    }

    public void setApellidoempleado(String apellidoempleado) {
        this.apellidoempleado = apellidoempleado;
    }

    public String getTelefonoempleado() {
        return telefonoempleado;
    }

    public void setTelefonoempleado(String telefonoempleado) {
        this.telefonoempleado = telefonoempleado;
    }

    public String getDireccionempleado() {
        return direccionempleado;
    }

    public void setDireccionempleado(String direccionempleado) {
        this.direccionempleado = direccionempleado;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public List<Factura> getFacturaList() {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList) {
        this.facturaList = facturaList;
    }

    public Empresa getIdNitempresa() {
        return idNitempresa;
    }

    public void setIdNitempresa(Empresa idNitempresa) {
        this.idNitempresa = idNitempresa;
    }

    public Puestos getPuesto() {
        return puesto;
    }

    public void setPuesto(Puestos puesto) {
        this.puesto = puesto;
    }

    public List<Contrato> getContratoList() {
        return contratoList;
    }

    public void setContratoList(List<Contrato> contratoList) {
        this.contratoList = contratoList;
    }

    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDuiempleado != null ? idDuiempleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.idDuiempleado == null && other.idDuiempleado != null) || (this.idDuiempleado != null && !this.idDuiempleado.equals(other.idDuiempleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Empleado[ idDuiempleado=" + idDuiempleado + " ]";
    }
    
}
