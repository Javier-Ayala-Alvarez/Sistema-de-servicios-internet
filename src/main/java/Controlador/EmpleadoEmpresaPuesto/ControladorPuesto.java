/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.EmpleadoEmpresaPuesto;

import Entidades.Puestos;
import Entidades.SesionBean.PuestosFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

@ManagedBean
@ViewScoped

public class ControladorPuesto implements  Serializable{
    
    private String sql = "";
    private Query query;

    @Inject
    private PuestosFacade puestoFacade;

    private List<Puestos> puestoList;
    private Puestos selectPuesto;

    @PostConstruct
    public void init() {
        this.selectPuesto = new Puestos();
        listarPuesto();
    }
    //LLena la lista de los facades

    public List<Puestos> listarPuesto() {
        this.puestoList = this.puestoFacade.findAll();
        return this.puestoList;
    }
    //Nuevo puesto

    public void createPuesto() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoHDP");
        EntityManager em = emf.createEntityManager();
        this.sql = "select max(p.idpuesto) from Puestos p";
        this.query = em.createQuery(sql);
        int max = (int) this.query.getSingleResult();
        //emf.close();
        //em.close();
        this.selectPuesto.setIdpuesto(max + 1);
        this.puestoFacade.create(selectPuesto);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardado con éxito", String.valueOf(this.selectPuesto.getIdpuesto()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

   
    //Modificar

    public void actualizar() {
        if (this.selectPuesto != null) {
            this.puestoFacade.edit(selectPuesto);
        } else {

        }
    }
    //Setter y getter

    public List<Puestos> getPuestoList() {
        return puestoList;
    }

    public void setPuestoList(List<Puestos> puestoList) {
        this.puestoList = puestoList;
    }

    public Puestos getSelectPuesto() {
        return selectPuesto;
    }

    public void setSelectPuesto(Puestos selectPuesto) {
        this.selectPuesto = selectPuesto;
    }

}
