/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Entidades.Contrato;
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
public class ControladorVenta implements Serializable {

    //Variables.
    int idContrato;

    //injeccion de dependencias 
    @Inject
    FacturaFacade facturaFacade;
    @Inject
    ContratoFacade contratoFacade;
    @Inject
    Factura factura;

    @Inject
    Contrato contrato;
    
    private Object[] listaTabla;

    @PostConstruct
    public void init() {

    }

    public void searchByIdContrato() {
        if (this.idContrato > 0 && contratoFacade.find(this.idContrato) != null) {
            this.listaTabla =  this.facturaFacade.getFactura(this.idContrato).get(0);
            PrimeFaces.current().executeScript("PF('modalFactura').show()");
            System.out.println("true");
            
            System.out.println(listaTabla[1]);

        } else {
            System.out.println("error");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Id de contrato no encontrado", "Id de contrato no encontrado"));
           
        }
        

    }

    public int getIdContrato() {
        return idContrato;
    }

    //setter y getter.

    public Object[] getListaTabla() {
        return listaTabla;
    }

    public void setListaTabla(Object[] listaTabla) {
        this.listaTabla = listaTabla;
    }
    

    public FacturaFacade getFacturaFacade() {
        return facturaFacade;
    }

    public void setFacturaFacade(FacturaFacade facturaFacade) {
        this.facturaFacade = facturaFacade;
    }
    
    
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
