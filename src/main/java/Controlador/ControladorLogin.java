/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Entidades.SesionBean.UsuariosFacade;
import Entidades.Usuarios;
import java.io.IOException;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;

/**
 *
 * @author mejia
 */
@ManagedBean
@RequestScoped
public class ControladorLogin implements Serializable {

    //variables
    @Inject
    UsuariosFacade usuariosFacade;
    @Inject
    Usuarios usuario;

    @PostConstruct
    void init() {

    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public void comprobarUsuario() throws IOException {
        if (!this.usuario.getUsuario().isEmpty()) {

            Usuarios getUsuario = this.usuariosFacade.comprobarUsuario(usuario);
            String redireccioString = "";
            if (getUsuario != null) {
                System.out.println(getUsuario.getIdusuario());
                //System.out.println(getUsuario.getIdempleado().getPuesto().getNombrepuesto());
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", getUsuario);

                switch (getUsuario.getIdempleado().getPuesto().getNombrepuesto()) {
                    case "Gerente":
                        redireccioString = "/Sistema-de-servicios-internet/faces/index.xhtml";
                        break;
                    case "Cobrador":
                         redireccioString = "/Sistema-de-servicios-internet/faces/View Factura/pagoFactura.xhtml";
                        break;
                    default:
                        throw new AssertionError();

                }

                FacesContext.getCurrentInstance().getExternalContext().redirect(redireccioString);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Usuario o contraseña no encontrado", ""));
            }
        }
        PrimeFaces.current().ajax().update("form:messages");

    }
}
