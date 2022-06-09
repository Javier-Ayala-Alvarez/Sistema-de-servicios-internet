/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Entidades.SesionBean.PuestosFacade;
import java.io.Serializable;
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
    
        
    @Inject
    PuestosFacade puestosFacade;

  

    

    
    
}
