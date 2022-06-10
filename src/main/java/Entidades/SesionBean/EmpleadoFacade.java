/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades.SesionBean;

import Entidades.Empleado;
import Util.JSFUtil;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
