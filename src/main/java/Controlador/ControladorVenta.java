/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Entidades.Factura;
import Entidades.SesionBean.FacturaFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author mejia
 */
@ManagedBean
@RequestScoped
public class ControladorVenta implements  Serializable{
    
    //Variables.
    int idContrato;
    
    //injeccion de dependencias 
    @Inject
    FacturaFacade facturaFacade;
    @Inject
    Factura factura ;
    
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
