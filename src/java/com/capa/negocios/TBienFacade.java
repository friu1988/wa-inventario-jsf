/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capa.negocios;

import com.capa.datos.TBien;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author FREDDY
 */
@Stateless
public class TBienFacade extends AbstractFacade<TBien> {

    @PersistenceContext(unitName = "wa-inventario-jsfPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TBienFacade() {
        super(TBien.class);
    }

    public List<TBien> findDetalles(TBien equipo) {
        System.out.println("PK Cabecera" + equipo.getBSerial());

        Query sql = em.createNamedQuery("TBien.findByBSerial");
        sql.setParameter("bSerial", equipo.getBSerial());
        List<TBien> lista = sql.getResultList();
        return lista;
    }

    public List<TBien> buscarDet(TBien equipo) {
        Query sql = em.createNamedQuery("TBien.findByBPadreSerial");
        try {
            sql.setParameter("padreBserial", equipo.getBSerial());
            List<TBien> lista = sql.getResultList();
            return lista;
        } catch (Exception e) {
            System.out.println("Error Buscar detalle >>>>>" + e.getMessage());
        }
        return null;
    }
}
