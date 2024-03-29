/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades.SesionBean;

import Entidades.Factura;
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
public class FacturaFacade extends AbstractFacade<Factura> {

    @PersistenceContext(unitName = "ProyectoHDP")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacturaFacade() {
        super(Factura.class);
    }

    public List<Object[]> getFactura(int idContrato) {
        List<Object[]> lista;

        Query q = em.createNativeQuery(" \n"
                + "select\n"
                + "  c.idcontrato,\n"
                + "  cli.nombrecliente,\n"
                + "  cli.id_duicliente as DUI,\n"
                + "  (\n"
                + "    select\n"
                + "      count(f.idfactura) as facturasPendientes\n"
                + "    from\n"
                + "      factura f\n"
                + "    where\n"
                + "      f.idcontrato = c.idcontrato\n"
                + "      and f.estado = 1\n"
                + "  )  as facturasPendientes,\n"
                + "  (\n"
                + "    select\n"
                + "      s.mora * (if (facturasPendientes > 1, facturasPendientes-1, 0))\n"
                + "    from\n"
                + "      servicio s\n"
                + "    where\n"
                + "      s.idservicio = c.idservicio\n"
                + "  ) as mora,\n"
                + "  (\n"
                + "    select\n"
                + "      s.precioservicio * (facturasPendientes )\n"
                + "    from\n"
                + "      servicio s\n"
                + "    where\n"
                + "      s.idservicio = c.idservicio\n"
                + "  ) as deuda,\n"
                + "  (\n"
                + "    select\n"
                + "      deuda + mora\n"
                + "  ) as total\n"
                + "from\n"
                + "  contrato c\n"
                + "  inner join cliente cli on cli.id_duicliente = c.idcliente\n"
                + "  where c.idcontrato = #idContrato");

        q.setParameter("idContrato", idContrato);

        lista = q.getResultList();

        return lista;

    }

    public List<Object[]> clientesMorosos() {
        List<Object[]> lista;

        String sql = " \n"
                + "select\n"
                + "  c.idcontrato,\n"
                + "  \n"
                + "  cli.nombrecliente,\n"
                + "  cli.apellidocliente,\n"
                + "  cli.id_duicliente as DUI,\n"
                + "  (\n"
                + "    select\n"
                + "      count(f.idfactura) as facturasPendientes\n"
                + "    from\n"
                + "      factura f\n"
                + "    where\n"
                + "      f.idcontrato = c.idcontrato\n"
                + "      and f.estado = 1\n"
                + "  )  as facturasPendientes,\n"
                + "  (\n"
                + "    select\n"
                + "      s.mora * (if (facturasPendientes > 1,facturasPendientes -1, 0))\n"
                + "    from\n"
                + "      servicio s\n"
                + "    where\n"
                + "      s.idservicio = c.idservicio\n"
                + "  ) as mora,\n"
                + "  (\n"
                + "    select\n"
                + "      s.precioservicio * (facturasPendientes )\n"
                + "    from\n"
                + "      servicio s\n"
                + "    where\n"
                + "      s.idservicio = c.idservicio\n"
                + "  ) as deuda,\n"
                + "  (\n"
                + "    select\n"
                + "      deuda + mora\n"
                + "  ) as total,\n"
                + "  c.vigente\n"
                + "from\n"
                + "  contrato c\n"
                + "  inner join cliente cli on cli.id_duicliente = c.idcliente\n"
                + "where\n"
                + "  (\n"
                + "    (\n"
                + "      select\n"
                + "        count(f.idfactura) as facturasPendientes\n"
                + "      from\n"
                + "        factura f\n"
                + "      where\n"
                + "        f.idcontrato = c.idcontrato\n"
                + "        and f.estado = 1\n"
                + "    ) \n"
                + "  ) >= 1 \n"
                + "  order by c.vigente desc";

        Query q = em.createNativeQuery(sql);

        lista = q.getResultList();

        return lista;
    }

    public boolean ModificarFacturasPago(String idempleado, int idcontrato) {

        try {
            Query q = em.createNativeQuery("UPDATE factura\n"
                    + "SET factura.fechapagofactura = (DATE_ADD(NOW(), INTERVAL -6 HOUR)), factura.idempleado = #idempleado,estado=0\n"
                    + "WHERE factura.idcontrato = #idcontrato");
            q.setParameter("idempleado", idempleado);
            q.setParameter("idcontrato", idcontrato);
            return (q.executeUpdate() > 0);
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean darBajaContratoMora(int idContrato) {

        try {
            Query q = em.createNativeQuery("update\n"
                    + "  contrato c\n"
                    + "set\n"
                    + "  c.fechabaja = (DATE_ADD(NOW(), INTERVAL -6 HOUR)),\n"
                    + "  c.vigente = 0,\n"
                    + "  c.motivobaja = \"Mora\"\n"
                    + "where\n"
                    + "  c.idcontrato = #idContrato");

            q.setParameter("idContrato", idContrato);

            return (q.executeUpdate() > 0);

        } catch (Exception e) {
        }

        return false;
    }

    public Object totalFacturasPagadas() {
        try {
            String sql = "  select count(f.idfactura) from Factura f where f.estado = \"0\"";

            Query q = em.createQuery(sql);

            return q.getSingleResult();

        } catch (Exception e) {
            System.out.println(e);
        }

        return -1;
    }

    public Object totalFacturasPagadasAhora() {
        try {
            String sql = "select count(f.idfactura) from factura f where f.estado = 0 \n"
                    + "and  DATEDIFF( f.fechafactura, DATE_ADD(NOW(), INTERVAL -6 HOUR)) = 0";

            Query q = em.createNativeQuery(sql);

            return q.getSingleResult();

        } catch (Exception e) {
            System.out.println(e);
        }

        return -1;
    }

}
