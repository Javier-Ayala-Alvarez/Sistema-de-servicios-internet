/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades.SesionBean;

import Entidades.Puestos;
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
public class PuestosFacade extends AbstractFacade<Puestos> {

    @PersistenceContext(unitName = "ProyectoHDP")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PuestosFacade() {
        super(Puestos.class);
    }
    
     public List<Object[]> getQueryGraficoPuesto() {
        List<Object[]> lista;
        Query q = em.createNativeQuery("select\n"
                + "  count(e.puesto) as total,\n"
                + "  p.nombrepuesto,\n"
                + "  EXTRACT(MONTH from e.fecha_contrato) as mes\n"
                + "from\n"
                + "  empleado e\n"
                + "  INNER JOIN puestos p on p.idpuesto = e.puesto\n"
                + "  where EXTRACT(MONTH from e.fecha_contrato) = 3\n"
                + "GROUP BY\n"
                + "  p.nombrepuesto,\n"
                + "  EXTRACT(MONTH from e.fecha_contrato)");

        lista = q.getResultList();
        return lista;

    }
    
}
