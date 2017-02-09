/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capa.negocios;

import com.capa.datos.TTipoBien;
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
public class TTipoBienFacade extends AbstractFacade<TTipoBien> {

    @PersistenceContext(unitName = "wa-inventario-jsfPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TTipoBienFacade() {
        super(TTipoBien.class);
    }

    public List<TTipoBien> equipos() {
        Query sql = em.createNamedQuery("TTipoBien.findByTbTipo");
        sql.setParameter("tbTipo", "EQUIPO");
        List<TTipoBien> equipos = sql.getResultList();
        return equipos;
    }

    public List<TTipoBien> dispositivos() {
        Query sql = em.createNamedQuery("TTipoBien.findByTbTipo");
        sql.setParameter("tbTipo", "DISPOSITIVO");
        List<TTipoBien> dispositivos = sql.getResultList();
        return dispositivos;
    }
    
    
}
