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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.primefaces.PrimeFaces;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author gerso
 */
@ManagedBean
@ViewScoped

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

    //Para las gráficas
    private String sql1 = "";
    private String sql2 = "";
    private String sql3 = "";
    private Query query1;
    private Query query2;
    private Query query3;
    private List<Object[]> listaContratos1 = new ArrayList<>();
    private List<Object[]> listaContratos2 = new ArrayList<>();
    private List<Object[]> listaContratos3 = new ArrayList<>();
    private BarChartModel modelBar;
    private PieChartModel modelPie;
    private LineChartModel modelLine;

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

        listarContratos();
        listarQueryBarras();
        listarLineas();
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
            if (b > 0) {
                lista2.remove(i);
                i--;
            }
        }
        lista2.remove(lista2.size() - 1);
        this.contratosInactivosFindAll = lista2;
    }

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

    private void darBajaAContrato() {
        if (this.contratoObjFA.getFechabaja() == null) {
            this.contratoObjFA.setFechabaja(new Date());
        }
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
        this.darBajaAContrato();
        this.contratoObjFA.setVigente("0");
        if (this.contratoObjFA != null) {
            this.contratoFacade.edit(this.contratoObjFA);
            FacesMessage msg = new FacesMessage("Acción ejecutada (baja) con éxito");
            FacesContext.getCurrentInstance().addMessage("msgs", msg);
        } else {
            FacesMessage msg = new FacesMessage("Acción no ejecutada (baja)");
            FacesContext.getCurrentInstance().addMessage("msgs", msg);
            init();
        }
    }

    public List<Object[]> listarContratos() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoHDP");
        EntityManager em = emf.createEntityManager();
        //SQL OK GRAFICO DE PASTEL
        this.sql1 = "SELECT cli.nombrecliente, SUM(ser.periodo_mes) as Cantidad FROM cliente cli\n"
                + "INNER JOIN contrato con ON cli.id_duicliente = con.idcliente\n"
                + "INNER JOIN servicio ser ON con.idservicio = ser.idservicio\n"
                + "GROUP BY cli.nombrecliente\n"
                + "order by SUM(ser.periodo_mes) desc";
        this.query1 = em.createNativeQuery(sql1);
        this.listaContratos1 = this.query1.getResultList();
        em.close();
        emf.close();
        return this.listaContratos1;
    }

    public List<Object[]> listarQueryBarras() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoHDP");
        EntityManager em = emf.createEntityManager();
        //SQL OK GRAFICO DE BARRAS
        this.sql2 = "select emp.nombreempleado, COUNT(con.idcontrato) as TotalContratos from contrato con\n"
                + "inner join empleado emp ON con.idempleado = emp.id_duiempleado\n"
                + "inner join servicio ser ON ser.idservicio = con.idservicio\n"
                + "where ser.precioservicio >=10\n"
                + "GROUP BY emp.nombreempleado\n"
                + "ORDER BY COUNT(con.idcontrato) DESC";
        this.query2 = em.createNativeQuery(sql2);
        this.listaContratos2 = this.query2.getResultList();
        em.close();
        emf.close();
        return this.listaContratos2;
    }

    public List<Object[]> listarLineas() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoHDP");
        EntityManager em = emf.createEntityManager();
        //SQL OK GRAFICO DE BARRAS
        this.sql3 = "SELECT emp.nombreempleado, COUNT(cli.nombrecliente) FROM empleado emp\n"
                + "inner join contrato con ON emp.id_duiempleado = con.idempleado\n"
                + "INNER JOIN cliente cli ON con.idcliente = cli.id_duicliente\n"
                + "GROUP BY emp.nombreempleado";
        this.query3 = em.createNativeQuery(sql3);
        this.listaContratos3 = this.query3.getResultList();
        em.close();
        emf.close();
        return this.listaContratos3;
    }

    public PieChartModel initPieModel() {
        this.modelPie = new PieChartModel();
        List<Object[]> listCont = this.listaContratos1;
        if (listCont != null) {
            for (Object[] obj : listCont) {
                String nombreCliente = obj[0].toString();
                double cantidadMeses = Integer.parseInt(obj[1].toString());
                this.modelPie.set(nombreCliente, cantidadMeses);
                this.modelPie.setTitle("Grafico de Meses adquiridos por cliente");
                this.modelPie.setLegendPosition("e");
                this.modelPie.setFill(false);
                this.modelPie.setShowDataLabels(true);
                this.modelPie.setDiameter(200);
            }
        }
        return this.modelPie;
    }

    public BarChartModel initBarModel() {
        this.modelBar = new BarChartModel();
        List<Object[]> listContratos = this.listaContratos2;
        if (listContratos != null) {
            for (Object[] contr : listContratos) {
                ChartSeries serie = new ChartSeries();
                String nombreEmp = contr[0].toString();
                serie.setLabel(contr[0].toString());
                double totalFactura = Integer.parseInt(contr[1].toString());
                serie.set(nombreEmp, totalFactura);
                this.modelBar.addSeries(serie);
            }
            this.modelBar.setTitle("Contratos mayores a $10.00 por Empleados");
            this.modelBar.setLegendPosition("ne");
            this.modelBar.setAnimate(true);
            Axis xAxis = this.modelBar.getAxis(AxisType.X);
            xAxis.setLabel("EMPLEADO");
            Axis yAxis = this.modelBar.getAxis(AxisType.Y);
            yAxis.setLabel("CANTIDAD");
        }
        return this.modelBar;
    }

    public LineChartModel initLineChartModel() {
        this.modelLine = new LineChartModel();
        List<Object[]> listContrato = this.listaContratos3;
        if (listContrato != null) {
            for (Object[] var : listContrato) {
                ChartSeries series = new ChartSeries();
                String nombreEmpleado = var[0].toString();
                int cantidadClientes = Integer.parseInt(var[1].toString());
                series.setLabel(var[0].toString());
                series.set(nombreEmpleado, cantidadClientes);
                this.modelLine.getAxes().put(AxisType.X, new CategoryAxis(nombreEmpleado));
                this.modelLine.addSeries(series);
            }
            this.modelLine.setTitle("Atenciones de Empleados a Clientes");
            this.modelLine.setLegendPosition("e");
            this.modelLine.setShowPointLabels(true);
            //
            Axis xAxis = this.modelLine.getAxis(AxisType.X);
            xAxis.setLabel("EMPLEADOS");
            //
            Axis yAxis = this.modelLine.getAxis(AxisType.Y);
            yAxis.setLabel("ATENCIONES HECHAS");
            yAxis.setMin(0);
            yAxis.setMax(20);
        }
        return this.modelLine;
    }

}
