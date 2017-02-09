/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capa.negocios;

import com.capa.datos.TServicio;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author FREDDY
 */
@Stateless
public class TServicioFacade extends AbstractFacade<TServicio> {

    @PersistenceContext(unitName = "wa-inventario-jsfPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TServicioFacade() {
        super(TServicio.class);
    }

    public TServicio getDefault() {
        Query sql = em.createNamedQuery("TServicio.findBySNombre");
        sql.setParameter("sNombre", "NO ASIGNADO");
        TServicio servicio = (TServicio) sql.getSingleResult();
        return servicio;
    }

}
