/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.EmpleadoEmpresaPuesto;

import Entidades.Empresa;
import Entidades.SesionBean.EmpresaFacade;
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
public class ControladorEmpresa {

    private String sql = ""; 

    
    private Query query;

    @Inject
    private EmpresaFacade empresaFacade;
    private Empresa empresaSelect;

    @PostConstruct
    public void init() {
        this.empresaSelect = new Empresa();
        empresaSelect();
    }

    public Empresa empresaSelect() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoHDP");
        EntityManager em = emf.createEntityManager();
        this.sql = "select e from Empresa e";
        this.query = em.createQuery(sql);
        this.empresaSelect = (Empresa) this.query.getSingleResult();
        return this.empresaSelect;
    }

    public void guardarEmpresa() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoHDP");
        EntityManager em = emf.createEntityManager();
        this.sql = "select e from Empresa e";
        this.query = em.createQuery(sql);
        
        
        
        if(this.query.getSingleResult() == null){
            this.empresaFacade.create(empresaSelect);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardado con éxito", String.valueOf(this.empresaSelect.getIdNitempresa()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
        }else{
            this.empresaFacade.edit(empresaSelect);
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Modificación con éxito", String.valueOf(this.empresaSelect.getIdNitempresa()));
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
   
    }

    public Empresa getEmpresaSelect() {
        return empresaSelect;
    }

    public void setEmpresaSelect(Empresa empresaSelect) {
        this.empresaSelect = empresaSelect;
    }

}
