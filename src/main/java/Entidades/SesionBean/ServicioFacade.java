/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades.SesionBean;

import Entidades.Servicio;
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
public class ServicioFacade extends AbstractFacade<Servicio> {

    @PersistenceContext(unitName = "ProyectoHDP")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServicioFacade() {
        super(Servicio.class);
    }

    public List<Object[]> getQueryGraficosServicios(String Date) {
        List<Object[]> lista;
   
        Query q = em.createNativeQuery("select\n"
                + "  count(cn.idservicio),\n"
                + "  s.servicio,\n"
                + "  EXTRACT(MONTH FROM cn.fechainicio) AS Month\n"
                + "from\n"
                + "  contrato cn\n"
                + "  inner join servicio s on (s.idservicio = cn.idservicio)\n"
                + "where\n"
                + " EXTRACT(YEAR FROM cn.fechainicio)  = #anio\n"
                + "group by\n"
                + "  cn.idservicio,\n"
                + "  s.idservicio,\n"
                + "  EXTRACT(MONTH FROM cn.fechainicio)");
        q.setParameter("anio", Date);

        lista = q.getResultList();
        return lista;

    }

}
