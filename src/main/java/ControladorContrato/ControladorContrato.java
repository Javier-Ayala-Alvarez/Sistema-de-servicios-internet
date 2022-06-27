/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControladorContrato;

import Entidades.Cliente;
import Entidades.Contrato;
import Entidades.Empleado;
import Entidades.Servicio;
import Entidades.SesionBean.ClienteFacade;
import Entidades.SesionBean.ContratoFacade;
import Entidades.SesionBean.EmpleadoFacade;
import Entidades.SesionBean.ServicioFacade;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;

/**
 *
 * @author gerso
 */
@ManagedBean
@ViewScoped
//@RequestScoped

public class ControladorContrato implements Serializable {

    @Inject
    private ContratoFacade contratoFacade;

    @Inject
    private ServicioFacade servicioFacade;

    @Inject
    private ClienteFacade clienteFacade;

    @Inject
    private EmpleadoFacade empleadoFacade;

    private List<Servicio> servicioList;
    private List<Cliente> clienteList;
    private List<Empleado> empleadoList;
    private List<Contrato> contratosActivosFindAll;
    private List<Contrato> contratosInactivosFindAll;
    
    private Contrato contratoObjFA;
    private Servicio servicio;
    private Cliente cliente;
    private Empleado empleado;
    
    private String varVigencia = "";

    public List<Contrato> getContratosActivosFindAll() {
        return contratosActivosFindAll;
    }

    public void setContratosActivosFindAll(List<Contrato> contratosActivosFindAll) {
        this.contratosActivosFindAll = contratosActivosFindAll;
    }

    public List<Contrato> getContratosInactivosFindAll() {
        return contratosInactivosFindAll;
    }

    public void setContratosInactivosFindAll(List<Contrato> contratosInactivosFindAll) {
        this.contratosInactivosFindAll = contratosInactivosFindAll;
    }

    public Contrato getContratoObjFA() {
        return contratoObjFA;
    }

    public void setContratoObjFA(Contrato contratoObjFA) {
        this.contratoObjFA = contratoObjFA;
    }

    public List<Servicio> getServicioList() {
        return servicioList;
    }

    public void setServicioList(List<Servicio> servicioList) {
        this.servicioList = servicioList;
    }

    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public ControladorContrato() {
    }

    @PostConstruct
    public void init() {
        this.contratoObjFA = new Contrato();
        this.servicio = new Servicio();
        this.cliente = new Cliente();
        this.empleado = new Empleado();
        
        this.listarContratosActivosFindAll();
        this.listarContratosInactivosFindAll();
        this.listarServicios();
        this.listarClientes();
        this.listarEmpleados();
    }

    public List<Contrato> listarContratosActivosFindAll() {
        List<Contrato> lista1;
        lista1 = this.contratoFacade.findAll();
        for (int i = 0; i < (lista1.size() - 1); i++) {
            int a = 0;
            a = Integer.parseInt(lista1.get(i).getVigente());
            if (a < 1) {
                lista1.remove(i);
                i--;
            }
        }
        this.contratosActivosFindAll = lista1;
        return this.contratosActivosFindAll;
    }

    public void listarContratosInactivosFindAll() {
        List<Contrato> lista2;
        lista2 = this.contratoFacade.findAll();
        
        for (int i = 0; i < (lista2.size() - 1); i++) {
            int b = 0;
            b = Integer.parseInt(lista2.get(i).getVigente());
            if (b>0) {
                lista2.remove(i);
                i--;
            }
        }
        lista2.remove(lista2.size()-1);
        /*0
        1*/
        this.contratosInactivosFindAll = lista2;
        PrimeFaces.current().ajax().update("form:dtprincipal");
    }

//    public List<Object[]> listarContratosTrue(){
//        this.contratoList = this.contratoFacade.sqlContratoListTrue();
////        PrimeFaces.current().ajax().update("form:tablacontratosactivos");
////        PrimeFaces.current().executeScript("PF('modalFactura').show()");
//        return this.contratoList;
//    }
    public List<Servicio> listarServicios() {
        this.servicioList = this.servicioFacade.findAll();
        return this.servicioList;
    }

    public List<Cliente> listarClientes() {
        this.clienteList = this.clienteFacade.findAll();
        return this.clienteList;
    }

    public List<Empleado> listarEmpleados() {
        this.empleadoList = this.empleadoFacade.findAll();
        return this.empleadoList;
    }

    public String vigencia(Object contra) {
        if ((contra.getClass().getName()).equals("java.lang.String")) {
            this.varVigencia = "Vigente";
        }
        return this.varVigencia;
    }

    private void idForContrato() {
        Object d = this.contratoFacade.sqlIdForContrato();
        String h = String.valueOf(d);
        int g = Integer.parseInt(h);
        this.contratoObjFA.setIdcontrato(g);
    }

    public void asignarFechaParaBaja() {
        Date d = new Date();
        this.contratoObjFA.setFechabaja(d);
    }

    public void createContratoWithFindAll() {
        this.idForContrato();
        this.contratoObjFA.setVigente("1");
        this.contratoObjFA.setMotivobaja("");
        this.contratoObjFA.setIdservicio(this.servicio);
        this.contratoObjFA.setIdcliente(this.cliente);
        this.contratoObjFA.setIdempleado(this.empleado);
        this.contratoFacade.create(this.contratoObjFA);
        FacesMessage msg = new FacesMessage("Contrato registrado exitosamente");
        FacesContext.getCurrentInstance().addMessage("msgs", msg);
    }

    public void guardarActualizar() {
        FacesMessage mensaje = new FacesMessage();
        mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
        this.contratoObjFA.setIdcliente(this.cliente);
        this.contratoObjFA.setIdservicio(this.servicio);
        this.contratoObjFA.setIdempleado(this.empleado);
        if (this.contratoObjFA != null) {
            this.contratoFacade.edit(this.contratoObjFA);
            FacesMessage msg = new FacesMessage("Acción ejecutada (baja) con éxito");
            FacesContext.getCurrentInstance().addMessage("msgs", msg);
//            mensaje.setSummary("Cambios realizados con éxito");
        } else {
            FacesMessage msg = new FacesMessage("Acción no ejecutada (baja) con éxito");
            FacesContext.getCurrentInstance().addMessage("msgs", msg);
//            mensaje.setSummary("Cambios no realizados");
            init();
//            mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
//            FacesContext.getCurrentInstance().addMessage("Mensaje", mensaje);
        }
    }
    
    
}
