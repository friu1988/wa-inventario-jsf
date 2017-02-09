package com.capa.presentacion;

import com.capa.datos.TMenu;
import com.capa.datos.TUsuario;
import com.capa.negocios.TMenuFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

@Named(value = "mBControlAcceso")
@SessionScoped
public class MBControlAcceso implements Serializable {

    @EJB
    private TMenuFacade srvMenu;

    private TUsuario usuario;
    private TUsuario us;
    private MenuModel menu;

    private List<TMenu> menus;

    private boolean acceso = false;

    public MBControlAcceso() {
    }

    @PostConstruct
    private void init() {

        menu = new DefaultMenuModel();
        accederMenus();
    }

    public void controlarAccesos() {
        try {
            TUsuario userL = (TUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLogin");
            if (userL == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("./../faces/login.xhtml");
            }
        } catch (Exception e) {
        }

    }

    public void accederMenus() {
        controlarAccesos();
        us = new TUsuario();
        us = (TUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLogin");
        us.getUTipo();
        System.out.println(us);
        setMenus(srvMenu.menus(us));

        for (TMenu itm : menus) {
            if (itm.getMNivel() == 1) {
//                System.out.println("Menus: " + itm.getMMenu());
                DefaultSubMenu subMenu = new DefaultSubMenu(itm.getMNombre());
                for (TMenu itsm : menus) {
                    if ((itsm.getMNivel() == 2) && (itsm.getPadreMSerial().getMSerial() == itm.getMSerial())) {
//                        System.out.println("Items: " + itsm.getMMenu());
                        DefaultMenuItem item = new DefaultMenuItem(itsm.getMNombre());
                        item.setUrl(itsm.getMUrl());
//                        System.out.println("URL: " + item.getUrl());
                        subMenu.addElement(item);
                    }
                }
                menu.addElement(subMenu);
            }

        }

    }

    public void cerrarSesion() {
        System.out.println("Cerrar Session");
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    public TUsuario getUsuario() {
        if (usuario == null) {
            usuario = new TUsuario();
        }
        return usuario;
    }

    public void setUsuario(TUsuario usuario) {
        this.usuario = usuario;
    }

    public List<TMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<TMenu> menus) {
        this.menus = menus;
    }

    public MenuModel getMenu() {
        return menu;
    }

    public void setMenu(MenuModel menu) {
        this.menu = menu;
    }

    public boolean isAcceso() {
        return acceso;
    }

    public void setAcceso(boolean acceso) {
        this.acceso = acceso;
    }

    public TUsuario getUs() {
        return us;
    }

    public void setUs(TUsuario us) {
        this.us = us;
    }

}
