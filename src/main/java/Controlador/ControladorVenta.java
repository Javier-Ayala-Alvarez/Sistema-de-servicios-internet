/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Entidades.SesionBean.PuestosFacade;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author mejia
 */
@ManagedBean
@RequestScoped
public class ControladorVenta implements  Serializable{
    
        
    @Inject
    PuestosFacade puestosFacade;

    private PieChartModel modelPie;

    public PieChartModel initPieModel() {
        this.modelPie = new PieChartModel();

        if (puestosFacade.getQueryGraficoPuesto() != null) {

            for (Object[] puesto : puestosFacade.getQueryGraficoPuesto()) {

                String nombrePuesto = puesto[1].toString();
                Integer numero = Integer.parseInt(puesto[0].toString());
                this.modelPie.set(nombrePuesto, numero);
                this.modelPie.setTitle("Empleados contratados en el mes: " + puesto[2].toString());
                this.modelPie.setLegendPosition("e");
                this.modelPie.setFill(false);
                this.modelPie.setShowDataLabels(true);
                this.modelPie.setDiameter(200);
            }

        }
        return this.modelPie;
    }

    
    
}
