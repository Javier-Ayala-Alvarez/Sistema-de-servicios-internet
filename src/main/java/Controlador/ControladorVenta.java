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
public class ControladorVenta implements Serializable {

    //Variables.
    int idContrato = 0;

    //injeccion de dependencias 
    @Inject
    private FacturaFacade facturaFacade;

    @Inject
    private ContratoFacade contratoFacade;

    @Inject
    private Factura factura;

    private List<Object[]> facturaPendiente;

   

    @PostConstruct
    public void init() {

    }

    public void searchByIdContrato() {
        if (this.idContrato > 0 && contratoFacade.find(this.idContrato) != null) {
            this.facturaPendiente = this.facturaFacade.getFactura(this.idContrato);
            PrimeFaces.current().ajax().update("form:messages", "form:dt-facturas");
            PrimeFaces.current().executeScript("PF('modalFactura').show()");
            System.out.println("true");

        } else {
            System.out.println("error");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Id de contrato no encontrado", "Id de contrato no encontrado"));

        }

    }
    public void cancelarFactura(){
        if(this.idContrato > 0){
            
        }
       
    }

    public int getIdContrato() {
        return idContrato;
    }

    public List<Object[]> getFacturaPendiente() {
        return facturaPendiente;
    }

    //setter y getter.
    public void setFacturaPendiente(List<Object[]> facturaPendiente) {
        this.facturaPendiente = facturaPendiente;
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
