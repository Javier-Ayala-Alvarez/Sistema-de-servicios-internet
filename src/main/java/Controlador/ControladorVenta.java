/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Entidades.Factura;
import Entidades.SesionBean.FacturaFacade;
import Entidades.SesionBean.PuestosFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author mejia
 */
@ManagedBean
@SessionScoped
public class ControladorVenta implements  Serializable{
    
    //Variables.
    Factura factura ;
    int idContrato;
    @Inject
    FacturaFacade facturaFacade;
    
    @PostConstruct
    public  void init (){
        
    }
    
    public void searchByIdContrato(){
        System.out.println(this.idContrato);
      
    }

    public int getIdContrato() {
        return idContrato;
    }

    //setter y getter.
    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }
    
    
    
    
    
    
    
    
    
    
    

  

    

    
    
}
