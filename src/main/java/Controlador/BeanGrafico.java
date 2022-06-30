/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.chart.Axis;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author 50376
 */
@ManagedBean(name = "Barra")
@ViewScoped
public class BeanGrafico implements Serializable {

    private String sql = "";
    private Query query;
    private List<Object[]> listaContratos = new ArrayList<>();
    private BarChartModel modelBar;
    private PieChartModel modelPie;
    private LineChartModel modelLine;

    @PostConstruct
    public void init() {
        listarContratos();
    }

    public List<Object[]> listarContratos() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoHDP");
        EntityManager em = emf.createEntityManager();

        //SQL OK GRAFICO DE BARRAS
        this.sql = "SELECT\n"
                + "	CONCAT (cliente.nombrecliente,' ',cliente.apellidocliente) as cliente,\n"
                + "	\n"
                + "	COUNT(contrato.idcontrato) as contratos\n"
                + "	\n"
                + "FROM\n"
                + "	cliente\n"
                + "	INNER JOIN\n"
                + "	contrato\n"
                + "	ON \n"
                + "		cliente.id_duicliente = contrato.idcliente\n"
                + "		\n"
                + "		GROUP BY\n"
                + "		cliente.nombrecliente,\n"
                + "		cliente.apellidocliente";

        this.query = em.createNativeQuery(sql);
        this.listaContratos = this.query.getResultList();
        em.close();
        emf.close();
        return this.listaContratos;

    }

    public BarChartModel initBarModel() {
        this.modelBar = new BarChartModel();
        List<Object[]> listContratos = this.listaContratos;
        if (listContratos != null) {
            for (Object[] contratos : listContratos) {
                ChartSeries serie = new ChartSeries();
                String nombrecliente = contratos[0].toString();
                serie.setLabel(contratos[0].toString());
                int valorContratos = Integer.parseInt(contratos[1].toString());
                serie.set(nombrecliente, valorContratos);
                this.modelBar.addSeries(serie);
            }
            this.modelBar.setTitle("Contratos por clientes");
            this.modelBar.setLegendPosition("ne");
            this.modelBar.setAnimate(true);
            Axis xAxis = this.modelBar.getAxis(AxisType.X);
            xAxis.setLabel("Contrato");
            Axis yAxis = this.modelBar.getAxis(AxisType.Y);
            yAxis.setLabel("Contratos (US $)");
        }
        return this.modelBar;
    }

     public PieChartModel initPieModel() {
        this.modelPie = new PieChartModel();
        List<Object[]> listContratos = this.listaContratos;
        if (listContratos != null) {
            for (Object[] contratos : listContratos) {
                String nombrecliente = contratos[0].toString();
                int valorContratos = Integer.parseInt(contratos[1].toString());
                this.modelPie.set(nombrecliente, valorContratos);
                this.modelPie.setTitle("Ventas por años");
                this.modelPie.setLegendPosition("e");
                this.modelPie.setFill(false);
                this.modelPie.setShowDataLabels(true);
                this.modelPie.setDiameter(200);
            }
        }
        return this.modelPie;
    }

    public LineChartModel initLineModel() {
        this.modelLine = new LineChartModel();
        List<Object[]> listContratos = this.listaContratos;
        if (listContratos != null) {
            for (Object[] contratos : listContratos) {
                ChartSeries serie = new ChartSeries();
                String nombrecliente = contratos[0].toString();
                serie.setLabel(contratos[0].toString());
                int valorContratos = Integer.parseInt(contratos[1].toString());
                serie.set(nombrecliente, valorContratos);
                this.modelLine.addSeries(serie);
            }
            this.modelLine.setTitle("Ventas por Fecha");
            this.modelLine.setLegendPosition("ne");
            this.modelLine.setAnimate(true);
            Axis xAxis = this.modelLine.getAxis(AxisType.X);
            xAxis.setLabel("Fechas");
            Axis yAxis = this.modelLine.getAxis(AxisType.Y);
            yAxis.setLabel("VENTAS DIARIAS (US $)");
        }
        return this.modelLine;
    }

    public BeanGrafico() {
    }

}
