/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Entidades.SesionBean.EmpleadoFacade;
import Entidades.SesionBean.ServicioFacade;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

/**
 *
 * @author mejia
 */
@ManagedBean
@ViewScoped

public class ControladorGrafico implements Serializable {

    private LineChartModel GraficoLinea;
    Date date = new Date();
    private List<Object[]> listaEmpleados;
    BarChartModel modelBar;

    SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");

    @Inject
    ServicioFacade servicioFacade;
    
    @Inject 
    EmpleadoFacade empleadoFacade;

    @PostConstruct
    public void init() {
        createLineModels();
        this.listaEmpleados = empleadoFacade.getQueryGraficosEmpleado();
    }

    private void createLineModels() {
        Axis yAxis;
        GraficoLinea = initCategoryModel();
        GraficoLinea.setTitle("Contratos generados durante el " + this.getYearFormat.format(this.date));
        GraficoLinea.setLegendPosition("e");
        GraficoLinea.setShowPointLabels(true);
        GraficoLinea.getAxes().put(AxisType.X, new CategoryAxis("Mes"));
        yAxis = GraficoLinea.getAxis(AxisType.Y);
        yAxis.setLabel("Cantidad de ventas");
        yAxis.setMin(0);
        yAxis.setMax(5);
    }

    private LineChartModel initCategoryModel() {
        String labelAux = "";
        int contador = 0;
        ChartSeries contenido = new ChartSeries();
        LineChartModel model = new LineChartModel();
        for (Object[] servicio : servicioFacade.getQueryGraficosServicios(this.getYearFormat.format(this.date))) {
            System.out.println(servicio[1].toString() + "  " + (!labelAux.equals(servicio[1].toString())));
            if (contador == 0) {
                labelAux = servicio[1].toString();
                contenido.setLabel(labelAux);

            } else if (!labelAux.equals(servicio[1].toString())) {
                model.addSeries(contenido);
                contenido = new ChartSeries();
                labelAux = servicio[1].toString();
                contenido.setLabel(labelAux);
            }
            contenido.set("Mes " + servicio[2].toString(), Integer.parseInt(servicio[0].toString()));
            contador++;
        }
        model.addSeries(contenido);
        return model;
    }

    public BarChartModel initBarCharModel() {
        this.modelBar = new BarChartModel();

        if (this.listaEmpleados != null) {
            String mes = "";
            for (Object[] empleado : listaEmpleados) {
                ChartSeries serie = new ChartSeries();
                String nombreEmpleado = empleado[1].toString() + " " + empleado[2].toString();
                serie.setLabel(nombreEmpleado);
                int numeroVentas = Integer.parseInt(empleado[0].toString());
                serie.set(" ", numeroVentas);
                this.modelBar.addSeries(serie);
                mes = empleado[3].toString();
            }
            this.modelBar.setTitle("Total de contratos generados en el mes: " + mes);
            this.modelBar.setLegendPosition("ne");
            this.modelBar.setAnimate(true);
            Axis xAxis = this.modelBar.getAxis(AxisType.X);
            xAxis.setLabel("Empleados");

            Axis yAxis = this.modelBar.getAxis(AxisType.Y);
            yAxis.setLabel("Numero de Clientes");
        }
        return this.modelBar;
    }

    public LineChartModel getGraficoLinea() {
        return GraficoLinea;
    }

    public void setGraficoLinea(LineChartModel GraficoLinea) {
        this.GraficoLinea = GraficoLinea;
    }

}
