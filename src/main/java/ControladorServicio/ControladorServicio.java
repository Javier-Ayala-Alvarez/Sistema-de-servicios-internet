/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Entidades.Servicio;
import Entidades.SesionBean.ServicioFacade;
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
import org.primefaces.PrimeFaces;

/**
 *
 * @author PBFCIS-SRC-01
 */
@ManagedBean(name = "sb")
@ViewScoped
public class ControladorServicio implements Serializable{
    private List<Servicio> servicios;
    
    private Servicio selectedServicio;
    
    private List<Servicio> selectedServicios;
    
    @Inject
    private ServicioFacade servicioFacade;
    
    @PostConstruct
    public void init(){
        this.servicios = this.servicioFacade.findAll();
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    public Servicio getSelectedServicio() {
        return selectedServicio;
    }

    public void setSelectedServicio(Servicio selectedServicio) {
        this.selectedServicio = selectedServicio;
    }

    public List<Servicio> getSelectedServicios() {
        return selectedServicios;
    }

    public void setSelectedServicios(List<Servicio> selectedServicios) {
        this.selectedServicios = selectedServicios;
    }
    
    public void openNew(){
        this.selectedServicio = new Servicio();
    }
    
    public void saveServicio(){
        if (this.selectedServicio.getIdservicio() == null) {
            this.selectedServicio.setIdservicio(id());
            this.selectedServicio.setEstado(Boolean.TRUE);
            this.servicioFacade.create(this.selectedServicio);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Servicio Agregado"));
        }
        else{
            this.servicioFacade.edit(this.selectedServicio);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Servicio Actualizado"));
        }
        PrimeFaces.current().executeScript("PF('manageServicioDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-servicio");
    }
    
    public void updateServicio() {
        if (this.selectedServicio != null && this.selectedServicio.getEstado() == false) {
            this.selectedServicio.setEstado(Boolean.TRUE);
            this.servicioFacade.edit(this.selectedServicio);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Servicio Habilitado"));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-servicios");
        }
        else if(this.selectedServicio != null && this.selectedServicio.getEstado() == true){
            this.selectedServicio.setEstado(Boolean.FALSE);
            this.servicioFacade.edit(this.selectedServicio);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Servicio deshabilitado"));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-servicios");
        }
    }
    
    public int id(){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoHDP");

        EntityManager em = emf.createEntityManager();

        String sql = "SELECT  MAX(s.idservicio)+1 as id FROM servicio s";

        Query q = em.createNativeQuery(sql);
        Object s = q.getSingleResult();
        String id = String.valueOf(s);
      
        return Integer.valueOf(id);
    }
}
