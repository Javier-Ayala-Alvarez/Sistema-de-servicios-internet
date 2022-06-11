/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.SesionBean;

import Controlador.entidades.Empleado;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author DELL
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
         public void baja(String id, boolean estado) {
        int nuevoEstado;
        if (estado == true) {
            nuevoEstado = 0;
        } else {
            nuevoEstado = 1;
        }

        Query query = this.em.createQuery("UPDATE Empleado set estado= :estado WHERE idDuiempleado = :id");

        query.setParameter("estado", nuevoEstado);
        query.setParameter("id", id);

       
        //emf.close();
        //em.close();
    }
}
