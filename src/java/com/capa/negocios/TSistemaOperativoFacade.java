/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capa.negocios;

import com.capa.datos.TSistemaOperativo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author FREDDY
 */
@Stateless
public class TSistemaOperativoFacade extends AbstractFacade<TSistemaOperativo> {

    @PersistenceContext(unitName = "wa-inventario-jsfPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TSistemaOperativoFacade() {
        super(TSistemaOperativo.class);
    }
    
}
