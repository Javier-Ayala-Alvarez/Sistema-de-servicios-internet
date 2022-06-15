/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.EmpleadoEmpresaPuesto;

import Entidades.Puestos;
import Entidades.SesionBean.PuestosFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

@ManagedBean
@ViewScoped
public class ControladorPuesto {
    @Inject
    private PuestosFacade puestoFacade;
    
    private List<Puestos> puestoList;
    private Puestos selectPuesto;
    
   @PostConstruct
   public void init(){
       this.selectPuesto = new Puestos();
       listarPuesto();
   }
   //LLena la lista de los facades
   public List<Puestos> listarPuesto(){
       this.puestoList = this.puestoFacade.findAll();
       return this.puestoList;
   }
   //Nuevo puesto
   public void createPuesto(){
       this.puestoFacade.create(selectPuesto);
   }
   //Modificar
   public void actualizar(){
       if(this.selectPuesto != null){
           this.puestoFacade.edit(selectPuesto);
       }else{
       
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
