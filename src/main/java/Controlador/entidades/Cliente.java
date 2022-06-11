/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.entidades;

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByIdDuicliente", query = "SELECT c FROM Cliente c WHERE c.idDuicliente = :idDuicliente"),
    @NamedQuery(name = "Cliente.findByNombrecliente", query = "SELECT c FROM Cliente c WHERE c.nombrecliente = :nombrecliente"),
    @NamedQuery(name = "Cliente.findByApellidocliente", query = "SELECT c FROM Cliente c WHERE c.apellidocliente = :apellidocliente"),
    @NamedQuery(name = "Cliente.findByTelefonocliente", query = "SELECT c FROM Cliente c WHERE c.telefonocliente = :telefonocliente"),
    @NamedQuery(name = "Cliente.findByDireccioncliente", query = "SELECT c FROM Cliente c WHERE c.direccioncliente = :direccioncliente"),
    @NamedQuery(name = "Cliente.findByEstado", query = "SELECT c FROM Cliente c WHERE c.estado = :estado")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "id_duicliente")
    private String idDuicliente;
    @Size(max = 30)
    @Column(name = "nombrecliente")
    private String nombrecliente;
    @Size(max = 30)
    @Column(name = "apellidocliente")
    private String apellidocliente;
    @Size(max = 10)
    @Column(name = "telefonocliente")
    private String telefonocliente;
    @Size(max = 40)
    @Column(name = "direccioncliente")
    private String direccioncliente;
    @Column(name = "estado")
    private Boolean estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcliente")
    private List<Contrato> contratoList;

    public Cliente() {
    }

    public Cliente(String idDuicliente) {
        this.idDuicliente = idDuicliente;
    }

    public String getIdDuicliente() {
        return idDuicliente;
    }

    public void setIdDuicliente(String idDuicliente) {
        this.idDuicliente = idDuicliente;
    }

    public String getNombrecliente() {
        return nombrecliente;
    }

    public void setNombrecliente(String nombrecliente) {
        this.nombrecliente = nombrecliente;
    }

    public String getApellidocliente() {
        return apellidocliente;
    }

    public void setApellidocliente(String apellidocliente) {
        this.apellidocliente = apellidocliente;
    }

    public String getTelefonocliente() {
        return telefonocliente;
    }

    public void setTelefonocliente(String telefonocliente) {
        this.telefonocliente = telefonocliente;
    }

    public String getDireccioncliente() {
        return direccioncliente;
    }

    public void setDireccioncliente(String direccioncliente) {
        this.direccioncliente = direccioncliente;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
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
        hash += (idDuicliente != null ? idDuicliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.idDuicliente == null && other.idDuicliente != null) || (this.idDuicliente != null && !this.idDuicliente.equals(other.idDuicliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Controlador.entidades.Cliente[ idDuicliente=" + idDuicliente + " ]";
    }
    
}
