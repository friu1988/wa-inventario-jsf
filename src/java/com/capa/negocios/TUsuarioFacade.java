/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capa.negocios;

import com.capa.datos.TUsuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author FREDDY
 */
@Stateless
public class TUsuarioFacade extends AbstractFacade<TUsuario> {

    @PersistenceContext(unitName = "wa-inventario-jsfPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TUsuarioFacade() {
        super(TUsuario.class);
    }

    public TUsuario buscarUser(TUsuario user) {
//        System.out.println("User MB: " + user.getUNombre() + " clave: " + user.getUClave());
        try {
            Query sql = em.createNamedQuery("TUsuario.findByUNombre");
            sql.setParameter("uNombre", user.getUNombre());
            TUsuario us = (TUsuario) sql.getSingleResult();
//            System.out.println("User DB: " + us.getUNombre() + " clave: " + us.getUClave());
            return us;
        } catch (Exception e) {
            TUsuario use = new TUsuario();
//            System.out.println("No entra");
            return use;
        }
    }
}
