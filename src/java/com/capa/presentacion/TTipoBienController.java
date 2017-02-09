package com.capa.presentacion;

import com.capa.datos.TTipoBien;
import com.capa.presentacion.util.JsfUtil;
import com.capa.presentacion.util.JsfUtil.PersistAction;
import com.capa.negocios.TTipoBienFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("tTipoBienController")
@SessionScoped
public class TTipoBienController implements Serializable {

    @EJB
    private com.capa.negocios.TTipoBienFacade ejbFacade;
    private List<TTipoBien> items = null;
    private List<TTipoBien> equipos = null;
    private List<TTipoBien> dispositvos = null;

    private TTipoBien selected;

    public TTipoBienController() {
    }

    public TTipoBien getSelected() {
        return selected;
    }

    public void setSelected(TTipoBien selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TTipoBienFacade getFacade() {
        return ejbFacade;
    }

    public TTipoBien prepareCreate() {
        selected = new TTipoBien();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Recursos").getString("TTipoBienCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            equipos = null;
            dispositvos = null;
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Recursos").getString("TTipoBienUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Recursos").getString("TTipoBienDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            equipos = null;
            dispositvos = null;
        }
    }

    public List<TTipoBien> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Recursos").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Recursos").getString("PersistenceErrorOccured"));
            }
        }
    }

    public TTipoBien getTTipoBien(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<TTipoBien> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TTipoBien> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TTipoBien.class)
    public static class TTipoBienControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TTipoBienController controller = (TTipoBienController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tTipoBienController");
            return controller.getTTipoBien(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof TTipoBien) {
                TTipoBien o = (TTipoBien) object;
                return getStringKey(o.getTbSerial());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TTipoBien.class.getName()});
                return null;
            }
        }

    }

    public List<TTipoBien> getEquipos() {
        if (equipos == null) {
            equipos = getFacade().equipos();
        }
        return equipos;
    }

    public void setEquipos(List<TTipoBien> equipos) {
        this.equipos = equipos;
    }

    public List<TTipoBien> getDispositvos() {
        if (dispositvos == null) {
            dispositvos = getFacade().dispositivos();
        }
        return dispositvos;
    }

    public void setDispositvos(List<TTipoBien> dispositvos) {
        this.dispositvos = dispositvos;
    }

}
