/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capa.negocios;

import com.capa.datos.TResponsable;
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
public class TResponsableFacade extends AbstractFacade<TResponsable> {

    @PersistenceContext(unitName = "wa-inventario-jsfPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TResponsableFacade() {
        super(TResponsable.class);
    }
    
     public List<TResponsable> getAll() {
        Query sql = em.createNamedQuery("TResponsable.findAll");
        List<TResponsable> responsables = sql.getResultList();
        return responsables;
    }
}
