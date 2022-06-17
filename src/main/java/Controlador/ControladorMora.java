/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

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
    
    
    @PostConstruct
    void init (){
        
    }
}
