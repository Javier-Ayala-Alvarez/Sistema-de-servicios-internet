/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Entidades.Usuarios;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author mejia
 */

@ManagedBean
@ViewScoped
public class ControladorSesion {
    
    
    public void verificarAdmin() throws IOException{
        Usuarios user = obtenerUsuarioSesion();
        if (user  == null) {
            redirigir();   
        }else if(!user.getIdempleado().getPuesto().getNombrepuesto().equals("Gerente") || !user.getIdempleado().getEstado()){
            cerrarSesion();
            redirigir();
        }
        
        
        
    }
    public void redirigir() throws IOException{
        
         FacesContext.getCurrentInstance().getExternalContext().redirect("/Sistema-de-servicios-internet/faces/login.xhtml");
    }
    
    Usuarios obtenerUsuarioSesion(){
        Usuarios user = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return user != null ? user:null;
    }
    
    public void cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
}
