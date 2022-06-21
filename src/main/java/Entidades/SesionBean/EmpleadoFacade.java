/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades.SesionBean;

import Entidades.Empleado;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author mejia
 */
@Stateless
public class EmpleadoFacade extends AbstractFacade<Empleado> {

    @PersistenceContext(unitName = "ProyectoHDP")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpleadoFacade() {
        super(Empleado.class);
    }
    
    
    public List<Object[]> getQueryGraficosEmpleado() {
        List<Object[]> lista;
        Query q = em.createNativeQuery("select\n"
                + "  count(cn.idcliente),\n"
                + "  em.nombreempleado,\n"
                + "  em.apellidoempleado, EXTRACT(MONTH FROM cn.fechainicio) AS \"Month\"\n"
                + "from\n"
                + "  contrato cn\n"
                + "  inner join cliente c on (c.id_duicliente = cn.idcliente)\n"
                + "  inner join empleado em on (em.id_duiempleado = cn.idempleado)\n"
                + "  where EXTRACT(MONTH FROM cn.fechainicio)= 5 and EXTRACT(YEAR FROM cn.fechainicio)= 2022\n"
                + "\n"
                + "group by\n"
                + "  em.nombreempleado,\n"
                + "  em.apellidoempleado,EXTRACT(MONTH FROM cn.fechainicio)\n"
                + "  ");

        lista = q.getResultList();
        return lista;

    }
    
}
