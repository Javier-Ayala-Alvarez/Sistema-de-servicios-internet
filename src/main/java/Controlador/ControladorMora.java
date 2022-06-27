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
public class ControladorMora implements Serializable {

    @Inject
    FacturaFacade facturaFacade;

    List<Object[]> ListaMorosos;

    @Inject
    private ContratoFacade contratoFacade;

    @Inject
    private Factura factura;

    @PostConstruct
    public void init() {

        this.ListaMorosos = this.facturaFacade.clientesMorosos();

    }

    public void darBajaContrato(int id) {
        
        

        if (this.facturaFacade.darBajaContratoMora(id)) {
            this.ListaMorosos = this.facturaFacade.clientesMorosos();
            PrimeFaces.current().ajax().update("form:cliente-Mora");

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Se dio de baja"));
            System.out.println("exito");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "A ocurrido un error"));
            System.out.println("error");
        }

        PrimeFaces.current().ajax().update("form:message");

    }

    public void setListaMorosos(List<Object[]> ListaMorosos) {
        this.ListaMorosos = ListaMorosos;
    }

    public List<Object[]> getListaMorosos() {
        return ListaMorosos;
    }

}
