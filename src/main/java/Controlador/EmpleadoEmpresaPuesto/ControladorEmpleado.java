/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.EmpleadoEmpresaPuesto;

import Entidades.Empleado;
import Entidades.Empresa;
import Entidades.Puestos;
import Entidades.SesionBean.EmpleadoFacade;
import Entidades.SesionBean.EmpresaFacade;
import Entidades.SesionBean.PuestosFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean
@RequestScoped
public class ControladorEmpleado implements  Serializable{

    private String sql = "";
    private Query query;
    private Empleado empleado;
    private List<Empleado> empleadoExiste;
    private Puestos puesto;
    private Empresa empresa;

    private List<Puestos> listPuesto;
    private List<Empresa> listEmpresa;

    private List<Object[]> listarEmpleadoConContratos = new ArrayList<>();
    private List<Object[]> listaEmpleadoConUsuariosActivos = new ArrayList<>();
    private List<Object[]> listarEmpleadosPuestos = new ArrayList<>();
    private BarChartModel modelBar;
    private PieChartModel modelPie;

    @Inject
    EmpleadoFacade empleadoFacade;
    @Inject
    PuestosFacade puestoFacade;
    @Inject
    EmpresaFacade empresaFacade;

    @PostConstruct
    public void init() {

        this.empleado = new Empleado();
        this.empresa = new Empresa();
        this.puesto = new Puestos();
        listarEmpresas();
        listarPuestos();
        listarEmpleadoConContratos();
        listarEmpleadosPuesto();
        listaEmpleadoConUsuariosActivos();
    }

    public void onRowEdit(RowEditEvent<Empleado> event) {
        this.empleado.setIdDuiempleado(event.getObject().getIdDuiempleado());
        this.empleado.setNombreempleado(event.getObject().getNombreempleado());
        this.empleado.setApellidoempleado(event.getObject().getApellidoempleado());
        this.empleado.setTelefonoempleado(event.getObject().getTelefonoempleado());
        this.empleado.setDireccionempleado(event.getObject().getDireccionempleado());
        this.empleado.setEstado(event.getObject().getEstado());
        this.empleado.setFechaContrato(event.getObject().getFechaContrato());

        this.empleado.setIdNitempresa(event.getObject().getIdNitempresa());
        this.empleado.setPuesto(event.getObject().getPuesto());
        FacesMessage msg = new FacesMessage("Empleado modificado", String.valueOf(event.getObject().getIdDuiempleado()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
        this.empleadoFacade.edit(this.empleado);

    }

    public boolean validacionExistencia(String dui) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoHDP");
        EntityManager em = emf.createEntityManager();
        String sql = "	SELECT * FROM empleado WHERE id_duiempleado = '" + dui + "'";
        this.query = em.createNativeQuery(sql);

        this.empleadoExiste = this.query.getResultList();

        if (this.empleadoExiste.size() == 0) {
            return true;
        } else {
            return false;
        }

    }

    public void guardarEmpleado() {
        if (validacionExistencia(this.empleado.getIdDuiempleado()) == true) {
            this.empleado.setIdNitempresa(empresa);
            this.empleado.setPuesto(puesto);
            this.empleado.setEstado(true);

            this.empleadoFacade.create(this.empleado);
            FacesMessage msg = new FacesMessage("Empleado Guardado con éxito", String.valueOf(this.empleado.getIdDuiempleado()));
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Codigo ya existe", String.valueOf(this.empleado.getIdDuiempleado()));
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public void onRowCancel(RowEditEvent<Empleado> event) {

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Modificación cancelada", String.valueOf(event.getObject().getIdDuiempleado()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public ControladorEmpleado() {
    }

    //Consulta sql}
    public List<Object[]> listaEmpleadoConUsuariosActivos() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoHDP");
        EntityManager em = emf.createEntityManager();
        String sql = "SELECT\n"
                + "	e.id_duiempleado, CONCAT( e.nombreempleado, ' ', e.apellidoempleado), u.usuario AS nombre \n"
                + "   FROM\n"
                + "	empleado e\n"
                + "	INNER JOIN usuarios u ON e.id_duiempleado = u.idempleado WHERE e.estado = 1;\n"
                + "	";
        this.query = em.createNativeQuery(sql);
        this.listaEmpleadoConUsuariosActivos = this.query.getResultList();
        //emf.close();
        //em.close();
        return this.listaEmpleadoConUsuariosActivos;
    }

    //FIn sql
    //Graficos
    public List<Object[]> listarEmpleadoConContratos() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoHDP");
        EntityManager em = emf.createEntityManager();
        this.sql = "SELECT\n"
                + "	CONCAT(e.nombreempleado,' ',e.apellidoempleado),(\n"
                + "	SELECT\n"
                + "		count( c.idempleado ) AS total \n"
                + "	FROM\n"
                + "		 contrato c WHERE c.idempleado = e.id_duiempleado \n"
                + "	) as total\n"
                + "  FROM\n"
                + "	empleado e GROUP BY total desc \n"
                + "	";
        this.query = em.createNativeQuery(sql);
        this.listarEmpleadoConContratos = this.query.getResultList();
        //emf.close();
        //em.close();
        return this.listarEmpleadoConContratos;
    }

    public List<Object[]> listarEmpleadosPuesto() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoHDP");
        EntityManager em = emf.createEntityManager();
        this.sql = "SELECT p.nombrepuesto, sum(e.puesto) as total FROM empleado e INNER JOIN puestos p on e.puesto = p.idpuesto\n"
                + " GROUP BY p.nombrepuesto";

        this.query = em.createNativeQuery(sql);
        this.listarEmpleadosPuestos = this.query.getResultList();
        //emf.close();
        //em.close();
        return this.listarEmpleadosPuestos;

    }

    public BarChartModel initBarCharModel() {
        this.modelBar = new BarChartModel();
        List<Object[]> listarContratoServicioGraf = this.listarEmpleadoConContratos;

        if (listarContratoServicioGraf != null) {

            for (Object[] contratos : listarContratoServicioGraf) {
                ChartSeries serie = new ChartSeries();
                String contrato = contratos[0].toString();
                serie.setLabel(contrato);
                double total = Double.parseDouble(contratos[1].toString());
                serie.set(contrato, total);
                this.modelBar.addSeries(serie);
            }
            this.modelBar.setTitle("Record de empleados con más contratos realizados");
            this.modelBar.setLegendPosition("ne");
            this.modelBar.setAnimate(true);
            Axis xAxis = this.modelBar.getAxis(AxisType.X);
            xAxis.setLabel("Empleados");

            Axis yAxis = this.modelBar.getAxis(AxisType.Y);
            yAxis.setLabel("Total de contrato");
        }
        return this.modelBar;
    }

    public PieChartModel initPieModel() {
        this.modelPie = new PieChartModel();
        List<Object[]> listarEmpleadosPuestoaGraf = this.listarEmpleadosPuestos;

        if (listarEmpleadosPuestoaGraf != null) {

            for (Object[] puestos : listarEmpleadosPuestoaGraf) {

                String puesto = puestos[0].toString();
                double total = Double.parseDouble(puestos[1].toString());
                this.modelPie.set(puesto, total);
                this.modelPie.setTitle("Puestos con más empleados");
                this.modelPie.setLegendPosition("e");
                this.modelPie.setFill(false);
                this.modelPie.setShowDataLabels(true);
                this.modelPie.setDiameter(200);
            }

        }
        return this.modelPie;
    }

    //fin de graficos
    public List<Puestos> getListPuesto() {
        return listPuesto;
    }

    public void setListPuesto(List<Puestos> listPuesto) {
        this.listPuesto = listPuesto;
    }

    public List<Empresa> getListEmpresa() {
        return listEmpresa;
    }

    public void setListEmpresa(List<Empresa> listEmpresa) {
        this.listEmpresa = listEmpresa;
    }

    public Puestos getPuesto() {
        return puesto;
    }

    public void setPuesto(Puestos puesto) {
        this.puesto = puesto;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Empresa> listarEmpresas() {
        this.listEmpresa = this.empresaFacade.findAll();
        return this.listEmpresa;

    }

    public List<Puestos> listarPuestos() {
        this.listPuesto = this.puestoFacade.findAll();
        return this.listPuesto;
    }

    public void guardarActualizar() {

    }

    public List<Empleado> getEmpleadoList() {
        return empleadoFacade.findAll();
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

}
