/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Entidades.Empleado;
import Entidades.Empresa;
import Entidades.Puestos;
import Entidades.SesionBean.EmpleadoFacade;
import Entidades.SesionBean.EmpresaFacade;
import Entidades.SesionBean.PuestosFacade;
import Util.JSFUtil;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

@ManagedBean
@RequestScoped
public class ControladorEmpleado {
     private String sql = "";
    private Query query;
    private Empleado empleado;
    private Puestos puesto;
    private Empresa empresa;
    
    private List<Puestos> listPuesto; 
    private List<Empresa> listEmpresa;

   @Inject 
   EmpleadoFacade empleadoFacade;
   @Inject
   PuestosFacade puestoFacade;
   @Inject
   EmpresaFacade empresaFacade;

    public List<Puestos> getListPuesto() {
        return listPuesto;
    }

    public void setListPuesto(List<Puestos> listPuesto) {
        this.listPuesto = listPuesto;
    }

    public List<Empresa> getListEmpresa() {
        return listEmpresa;
    }

    public void setListEmpresa(List<Empresa> listEmpresa) {
        this.listEmpresa = listEmpresa;
    }

  

    public Puestos getPuesto() {
        return puesto;
    }

    public void setPuesto(Puestos puesto) {
        this.puesto = puesto;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

  
    @PostConstruct
    public void init(){
        this.empleado = new Empleado();
        this.empresa = new Empresa();
        this.puesto = new Puestos();
        listarEmpresas();
        listarPuestos();
    }
    public List<Empresa> listarEmpresas(){
        this.listEmpresa = this.empresaFacade.findAll();
        return this.listEmpresa;
        
    }
    public List<Puestos> listarPuestos(){
        this.listPuesto = this.puestoFacade.findAll();
        return this.listPuesto;
    }
    
  
    public void guardarEmpleado(){
     this.empleado.setIdNitempresa(empresa);
     this.empleado.setPuesto(puesto);
       this.empleadoFacade.create(this.empleado);
       
    }
    
 
   
   public void guardarActualizar(){
      
   }
   public List<Empleado> getEmpleadoList(){
        return empleadoFacade.findAll();
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    
}
