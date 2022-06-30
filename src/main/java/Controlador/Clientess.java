/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Entidades.Cliente;
import Entidades.SesionBean.ClienteFacade;
import java.io.Serializable;
import java.util.ArrayList;
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
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean
@ViewScoped
public class Clientess implements Serializable {

    private String sql = "";
    private Query query;
    private Cliente cliente;
    private List<Cliente> ClienteExiste;
    private BarChartModel modelBar;
    private PieChartModel modelPie;

    private Cliente selectedCliente;
    private List<Object[]> listarClientesConContratos = new ArrayList<>();
    @Inject
    ClienteFacade clienteFacade;

    @PostConstruct
    public void init() {

        this.cliente = new Cliente();
//        listarCliente();

    }

    public void eliminar() {
        if (this.cliente != null) {
            cliente.setEstado(false);
            this.clienteFacade.edit(this.cliente);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente Eliminado"));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-cliente");
            init();
        }
    }

    public boolean validacionExistencia(String dui) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoHDP");
        EntityManager em = emf.createEntityManager();
        String sql = "	SELECT * FROM cliente WHERE id_duicliente = '" + dui + "'";
        this.query = em.createNativeQuery(sql);

        this.ClienteExiste = this.query.getResultList();

        if (this.ClienteExiste.size() == 0) {
            return true;
        } else {
            return false;
        }

    }

    public void guardarCliente() {
        if (validacionExistencia(this.cliente.getIdDuicliente()) == true) {
            cliente.setEstado(true);
            this.clienteFacade.create(this.cliente);

            FacesMessage msg = new FacesMessage("Cliente Guardado con éxito", String.valueOf(this.cliente.getIdDuicliente()));
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Codigo ya existe", String.valueOf(this.cliente.getIdDuicliente()));
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        init();
    }

    public void actualizar() {

        FacesMessage mensaje = new FacesMessage();
        mensaje.setSeverity(FacesMessage.SEVERITY_INFO);

        if (this.cliente != null) {
            this.clienteFacade.edit(this.cliente);
            mensaje.setSummary("Cambios guardados");
        } else {
            mensaje.setSummary("Cambios no guardados");
            init();
            mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage("Mensaje", mensaje);
        }
        init();
    }

    public Clientess() {
    }

    //Graficos
    public List<Object[]> listarClientesConContratos() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoHDP");
        EntityManager em = emf.createEntityManager();
        this.sql = "SELECT\n"
                + "	CONCAT(e.nombrecliente,' ',e.apellidocliente) AS Cliente,(\n"
                + "	SELECT\n"
                + "		count( c.idcliente ) AS total \n"
                + "	FROM\n"
                + "		 contrato c WHERE c.idcliente = e.id_duicliente \n"
                + "	) as total\n"
                + "  FROM\n"
                + "	cliente e GROUP BY total desc \n"
                + "	";
        this.query = em.createNativeQuery(sql);
        this.listarClientesConContratos = new ArrayList<>();
        this.listarClientesConContratos = this.query.getResultList();
        emf.close();
        em.close();
        return this.listarClientesConContratos;
    }

    public BarChartModel initBarCharModel() {
        this.modelBar = new BarChartModel();
        List<Object[]> listarContratoServicioGraf = listarClientesConContratos();

        if (listarContratoServicioGraf != null) {

            for (Object[] contratos : listarContratoServicioGraf) {
                ChartSeries serie = new ChartSeries();
                String contrato = contratos[0].toString();
                serie.setLabel(contrato);
                double total = Double.parseDouble(contratos[1].toString());
                serie.set(contrato, total);
                this.modelBar.addSeries(serie);
            }
            this.modelBar.setTitle("Record de clientes con más contratos");
            this.modelBar.setLegendPosition("ne");
            this.modelBar.setAnimate(true);
            Axis xAxis = this.modelBar.getAxis(AxisType.X);
            xAxis.setLabel("Clientes");

            Axis yAxis = this.modelBar.getAxis(AxisType.Y);
            yAxis.setLabel("Total de contratos");
        }
        return this.modelBar;
    }

    public PieChartModel initPieModel() {
        this.modelPie = new PieChartModel();
        List<Object[]> listarClientesPuestoaGraf = listarClientesConContratos();
        if (listarClientesPuestoaGraf != null) {
            for (Object[] puestos : listarClientesPuestoaGraf) {
                String puesto = puestos[0].toString();
                double total = Double.parseDouble(puestos[1].toString());
                this.modelPie.set(puesto, total);
                this.modelPie.setTitle("clientes con más contratos");
                this.modelPie.setLegendPosition("e");
                this.modelPie.setFill(false);
                this.modelPie.setShowDataLabels(true);
                this.modelPie.setDiameter(200);
            }

        }
        return this.modelPie;
    }

    //fin de graficos
    public List<Cliente> getClienteList() {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoHDP");
        EntityManager em = emf.createEntityManager();

        String sql = "SELECT * FROM cliente P\n"
                + "WHERE P.estado=1";

        Query query = em.createNativeQuery(sql, Cliente.class);
        List<Cliente> lista =  query.getResultList();
        em.close();
        emf.close();
        return lista;

    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getClienteExiste() {
        return ClienteExiste;
    }

    public void setClienteExiste(List<Cliente> ClienteExiste) {
        this.ClienteExiste = ClienteExiste;
    }

    public ClienteFacade getClienteFacade() {
        return clienteFacade;
    }

    public void setClienteFacade(ClienteFacade clienteFacade) {
        this.clienteFacade = clienteFacade;
    }

    private boolean hasSelectedCliente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
