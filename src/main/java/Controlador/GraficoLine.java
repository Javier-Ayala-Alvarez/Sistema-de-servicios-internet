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
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

/**
 *
 * @author 50376
 */
@ManagedBean(name = "vbgr")
@ViewScoped
public class GraficoLine implements Serializable {

    private String sql = "";
    private Query query;
    private List<Object[]> listaContratos = new ArrayList<>();
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
                + "	CONCAT (empleado.nombreempleado,' ',empleado.apellidoempleado) as Empleado, \n"
                + "	\n"
                + "	COUNT(contrato.idcontrato) as 'Numero del contrato'\n"
                + "	\n"
                + "FROM\n"
                + "	empleado\n"
                + "	INNER JOIN\n"
                + "	contrato\n"
                + "	ON \n"
                + "		empleado.id_duiempleado = contrato.idempleado\n"
                + "		\n"
                + "GROUP  BY\n"
                + "		empleado.nombreempleado,\n"
                + "		empleado.apellidoempleado\n"
                + "		";

        this.query = em.createNativeQuery(sql);
        this.listaContratos = this.query.getResultList();
        em.close();
        emf.close();
        return this.listaContratos;

    }


     public LineChartModel initLineModel() {
        this.modelLine = new LineChartModel();
        List<Object[]> listaContratos = this.listaContratos;

        ChartSeries serie = new ChartSeries();

        if (listaContratos != null) {
            for (Object[] contrato : listaContratos) {
                String nombreempleado = contrato[0].toString();
                serie.setLabel(contrato[0].toString());
                int idcontrato = Integer.parseInt(contrato[1].toString());
                serie.set(nombreempleado, idcontrato);
            }
            serie.setLabel("Empleados");
        }
        
       

        this.modelLine.addSeries(serie);
        this.modelLine.setTitle("Contratos por empleados");
        this.modelLine.setLegendPosition("ne");
        this.modelLine.setShowPointLabels(true);
        this.modelLine.setAnimate(true);
        this.modelLine.getAxes().put(AxisType.X, new CategoryAxis("Empleados"));
        Axis yAxis = this.modelLine.getAxis(AxisType.Y);
        yAxis.setLabel("No.Contratos");

        return this.modelLine;
    }


    public GraficoLine() {
    }

}
