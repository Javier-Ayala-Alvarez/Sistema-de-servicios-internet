/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades.SesionBean;

import Entidades.Contrato;
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
public class ContratoFacade extends AbstractFacade<Contrato> {

    @PersistenceContext(unitName = "ProyectoHDP")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContratoFacade() {
        super(Contrato.class);
    }

//    public List<Object[]> sqlContratoListTrue() {
//        List<Object[]> listaC;
//        Query q = em.createNativeQuery("SELECT cont.idcontrato, clie.id_duicliente, clie.nombrecliente, serv.servicio, cont.`Direccion_contrato`,cont.fechainicio, cont.fechabaja, cont.motivobaja, empl.nombreempleado, cont.vigente\n"
//                + "FROM contrato cont INNER JOIN cliente clie ON cont.idcliente = clie.id_duicliente\n"
//                + "INNER JOIN servicio serv ON cont.idservicio = serv.idservicio\n"
//                + "INNER JOIN empleado empl ON cont.idempleado = empl.id_duiempleado\n"
//                + "WHERE cont.vigente = 1\n"
//                + "ORDER BY cont.fechainicio ASC");
//        listaC = q.getResultList();
//        return listaC;
//    }
//
//    public List<Object[]> sqlContratoListFalse() {
//        List<Object[]> listaC;
//        Query q = em.createNativeQuery("SELECT cont.idcontrato, clie.id_duicliente, clie.nombrecliente, serv.servicio, cont.`Direccion_contrato`,cont.fechainicio, cont.fechabaja, cont.motivobaja, empl.nombreempleado\n"
//                + "FROM contrato cont INNER JOIN cliente clie ON cont.idcliente = clie.id_duicliente\n"
//                + "INNER JOIN servicio serv ON cont.idservicio = serv.idservicio\n"
//                + "INNER JOIN empleado empl ON cont.idempleado = empl.id_duiempleado\n"
//                + "WHERE cont.vigente = 0\n"
//                + "ORDER BY cont.fechabaja ASC");
//        listaC = q.getResultList();
//        return listaC;
//    }
    
    public Object sqlIdForContrato(){
        Object a=0;
        Query q = em.createNativeQuery("SELECT MAX(idcontrato)+1 FROM contrato");
        a = q.getSingleResult();
        return a;
    }

}
