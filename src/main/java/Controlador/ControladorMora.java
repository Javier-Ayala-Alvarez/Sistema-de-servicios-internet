/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Entidades.Factura;
import Entidades.SesionBean.ContratoFacade;
import Entidades.SesionBean.FacturaFacade;
import java.io.Serializable;
import java.util.List;
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
public class ControladorMora  implements Serializable{
    
    @Inject 
    FacturaFacade facturaFacade;
    
    List<Object[]> ListaMorosos ;
    
    @Inject
    private ContratoFacade contratoFacade;

    @Inject
    private Factura factura;
    
    
    @PostConstruct
    public void init (){
        
        this.ListaMorosos = this.facturaFacade.clientesMorosos();
        
    }

    
    public void setListaMorosos(List<Object[]> ListaMorosos) {
        this.ListaMorosos = ListaMorosos;
    }
    
    
    public List<Object[]> getListaMorosos() {
        return ListaMorosos;
    }
    
    
}
