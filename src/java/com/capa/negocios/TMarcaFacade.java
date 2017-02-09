/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capa.negocios;

import com.capa.datos.TMarca;
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
public class TMarcaFacade extends AbstractFacade<TMarca> {

    @PersistenceContext(unitName = "wa-inventario-jsfPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TMarcaFacade() {
        super(TMarca.class);
    }

     public List<TMarca> getAll() {
        Query sql = em.createNamedQuery("TMarca.findAll");
        List<TMarca> marcas = sql.getResultList();
        return marcas;
    }    
}
