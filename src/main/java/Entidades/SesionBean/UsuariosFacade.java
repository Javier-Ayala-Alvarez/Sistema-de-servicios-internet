/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades.SesionBean;

import Entidades.Usuarios;
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
public class UsuariosFacade extends AbstractFacade<Usuarios> {

    @PersistenceContext(unitName = "ProyectoHDP")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }

    public Usuarios comprobarUsuario(Usuarios u) {
        try {
            Query q = em.createNamedQuery("findByPasswordUser");
            q.setParameter("password", u.getPassword());
            q.setParameter("usuario", u.getUsuario());

            return !q.getResultList().isEmpty() ? ((Usuarios) q.getResultList().get(0)) : null;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}
