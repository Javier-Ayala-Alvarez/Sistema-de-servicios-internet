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
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author 50376
 */
@ManagedBean(name = "vbgra")
@ViewScoped
public class VentasBeanGrafico implements Serializable {

    private String sql = "";
    private Query query;
    private List<Object[]> listaServicios = new ArrayList<>();
    private BarChartModel modelBar;
    private PieChartModel modelPie;

    @PostConstruct
    public void init() {
        listarServicios();
    }

    public List<Object[]> listarServicios() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoHDP");
        EntityManager em = emf.createEntityManager();

        //SQL OK GRAFICO DE BARRAS
        this.sql = "SELECT\n"
                + "	s.servicio,\n"
                + "	\n"
                + "	COUNT(cliente.id_duicliente) as \"Numero de contrataciones\"\n"
                + "	\n"
                + "FROM\n"
                + "	cliente\n"
                + "	INNER JOIN\n"
                + "	contrato\n"
                + "	\n"
                + "	ON \n"
                + "		cliente.id_duicliente = contrato.idcliente\n"
                + "		\n"
                + "		INNER JOIN \n"
                + "		servicio as s\n"
                + "		ON\n"
                + "      s.idservicio = contrato.idservicio\n"
                + "		\n"
                + "		GROUP BY\n"
                + "		s.servicio";

        this.query = em.createNativeQuery(sql);
        this.listaServicios = this.query.getResultList();
        em.close();
        emf.close();
        return this.listaServicios;

    }

    public BarChartModel initBarModel() {
        this.modelBar = new BarChartModel();
        List<Object[]> listServicios = this.listaServicios;
        if (listServicios != null) {
            for (Object[] servicios : listServicios) {
                ChartSeries serie = new ChartSeries();
                String fechaVenta = servicios[0].toString();
                serie.setLabel(servicios[0].toString());
                double valorVenta = Double.parseDouble(servicios[1].toString());
                serie.set(fechaVenta, valorVenta);
                this.modelBar.addSeries(serie);
            }
            this.modelBar.setTitle("Numeros de contrataciones");
            this.modelBar.setLegendPosition("ne");
            this.modelBar.setAnimate(true);
            Axis xAxis = this.modelBar.getAxis(AxisType.X);
            xAxis.setLabel("Fechas");
            Axis yAxis = this.modelBar.getAxis(AxisType.Y);
            yAxis.setLabel("Numeros contrataciones (US $)");
        }
        return this.modelBar;
    }

    public PieChartModel initPieModel() {
        this.modelPie = new PieChartModel();
        List<Object[]> listServicios = this.listaServicios;
        if (listServicios != null) {
            for (Object[] servicios : listServicios) {
                String fechaServicios = servicios[0].toString();
                int valorServicios = Integer.parseInt(servicios[1].toString());
                this.modelPie.set(fechaServicios, valorServicios);
                this.modelPie.setTitle("Veces de contrataciones de los servicios");
                this.modelPie.setLegendPosition("e");
                this.modelPie.setFill(false);
                this.modelPie.setShowDataLabels(true);
                this.modelPie.setDiameter(200);
            }
        }
        return this.modelPie;
    }


    public VentasBeanGrafico() {
    }

}
