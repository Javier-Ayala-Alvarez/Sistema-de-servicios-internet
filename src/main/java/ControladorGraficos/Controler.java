/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorGraficos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author PBFCIS-SRC-01
 */
@ManagedBean(name = "cg")
@ViewScoped
public class Controler implements Serializable{
    private String sql = "";
    private Query query;
    private List<Object[]> listaContratos = new ArrayList<>();
    private List<Object[]> lista = new ArrayList<>();
    private List<Object[]> listalinea = new ArrayList<>();
    private BarChartModel modelBar;
    private PieChartModel modelPie;
    private LineChartModel modelLine;
    
    @PostConstruct
    public void init(){
        listarContratos();
        listar();
        listarLineas();
    }
    
    public List<Object[]> listarContratos(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CD20010ProyectoReportesPU");
        EntityManager em = emf.createEntityManager();
        
        this.sql = "SELECT EXTRACT(YEAR FROM c.fechainicio) AS anio, COUNT(c.idcontrato) AS ncontrato\n" +
                    "FROM contrato c\n" +
                    "INNER JOIN empleado e ON c.idempleado = e.id_duiempleado\n" +
                    "GROUP BY anio\n" +
                    "ORDER BY anio, ncontrato  DESC";
        
        this.query = em.createNativeQuery(sql);
        this.listaContratos = this.query.getResultList();
        
        return this.listaContratos;
    }
    
    public List<Object[]> listar(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CD20010ProyectoReportesPU");
        EntityManager em = emf.createEntityManager();
        
        this.sql = "SELECT COUNT(con.idcliente) AS nContratos, CONCAT(c.nombrecliente,\" \",c.apellidocliente) AS nombre\n" +
                    "FROM cliente c\n" +
                    "INNER JOIN contrato con ON c.id_duicliente = con.idcliente\n" +
                    "GROUP BY nombre \n" +
                    "ORDER BY nContratos DESC";
        
        this.query = em.createNativeQuery(sql);
        this.lista = this.query.getResultList();
        
        return this.lista;
    }
    
    public List<Object[]> listarLineas(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CD20010ProyectoReportesPU");
        EntityManager em = emf.createEntityManager();
        
        this.sql = "SELECT EXTRACT(YEAR FROM c.fechainicio) AS anio, EXTRACT(MONTH FROM c.fechainicio) AS mes, COUNT(s.idservicio) AS contratos\n" +
                    "FROM contrato c\n" +
                    "INNER JOIN servicio s ON c.idservicio = s.idservicio\n" +
                    "GROUP BY mes\n" +
                    "ORDER BY anio, mes, contratos ASC";
        
        this.query = em.createNativeQuery(sql);
        this.listalinea = this.query.getResultList();
        
        return this.listalinea;
    }
    
    public BarChartModel initBarModel(){
        this.modelBar = new BarChartModel();
        List<Object[]> listContratos = this.lista;
        if (listContratos != null) {
            for (Object[] contrato : listContratos) {
                ChartSeries serie = new ChartSeries();
                int nContrato = Integer.parseInt(contrato[0].toString());
                String nombre = contrato[1].toString();
                serie.setLabel(contrato[1].toString());
                serie.set(nombre,nContrato);
                this.modelBar.addSeries(serie);
            }
            this.modelBar.setTitle("Contratos por Cliente");
            this.modelBar.setLegendPosition("ne");
            this.modelBar.setAnimate(true);
            Axis xAxis = this.modelBar.getAxis(AxisType.X);
            xAxis.setLabel("Contratos");
            Axis yAxis = this.modelBar.getAxis(AxisType.Y);
            yAxis.setLabel("# DE CONTRATOS");
        }
        return this.modelBar;
    }
    
    public PieChartModel initPieModel(){
        this.modelPie = new PieChartModel();
        List<Object[]> listContratos = this.listaContratos;
        if (listContratos != null) {
            for (Object[] contrato : listContratos) {
                String anio = contrato[0].toString();
                int ncontrato = Integer.parseInt(contrato[1].toString());
                this.modelPie.set(anio, ncontrato);
                this.modelPie.setTitle("Contratos por año");
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
        LineChartSeries serie = new LineChartSeries();
        List<Object[]> listlinea = this.listalinea;
        if (listlinea != null) {
            serie.setLabel("VENTAS MENSUALES");
            for (Object[] contratos : listlinea) {
                String anio = contratos[0].toString();
                String mes = contratos[1].toString();
                String unido = mes + "-" + anio;
                int nContrato = Integer.parseInt(contratos[2].toString());
                serie.getData().put(mes, nContrato);
//                serie.isShowLine();
            }
            this.modelLine.setTitle("Contratos mensuales");
            this.modelLine.setLegendPosition("e");
            //this.modelLine.setAnimate(true);
            Axis xAxis = this.modelLine.getAxis(AxisType.X);
            xAxis.setLabel("Contratos");
            Axis yAxis = this.modelLine.getAxis(AxisType.Y);
            yAxis.setLabel("# DE CONTRATOS");
            
            modelLine.addSeries(serie);
        }
        return modelLine;
    }

}
