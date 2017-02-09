/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capa.negocios;

import com.capa.datos.TMenu;
import com.capa.datos.TUsuario;
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
public class TMenuFacade extends AbstractFacade<TMenu> {

    @PersistenceContext(unitName = "wa-inventario-jsfPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TMenuFacade() {
        super(TMenu.class);
    }

    public List<TMenu> menus(TUsuario user) {
        Query sql = em.createNamedQuery("TMenu.findByMTipo");
        sql.setParameter("mTipo", user.getUTipo());
        List<TMenu> lista = sql.getResultList();
        return lista;
    }

}
