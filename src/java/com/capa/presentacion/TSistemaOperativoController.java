package com.capa.presentacion;

import com.capa.datos.TSistemaOperativo;
import com.capa.presentacion.util.JsfUtil;
import com.capa.presentacion.util.JsfUtil.PersistAction;
import com.capa.negocios.TSistemaOperativoFacade;

import java.io.Serializable;
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

@Named("tSistemaOperativoController")
@SessionScoped
public class TSistemaOperativoController implements Serializable {

    @EJB
    private com.capa.negocios.TSistemaOperativoFacade ejbFacade;
    private List<TSistemaOperativo> items = null;
    private TSistemaOperativo selected;

    public TSistemaOperativoController() {
    }

    public TSistemaOperativo getSelected() {
        return selected;
    }

    public void setSelected(TSistemaOperativo selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TSistemaOperativoFacade getFacade() {
        return ejbFacade;
    }

    public TSistemaOperativo prepareCreate() {
        selected = new TSistemaOperativo();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        selected.setSoCadena(selected.getSoNombre() + " " + selected.getSoVersion() + " " + selected.getSoEdicion() + " " + selected.getSoArquitectura());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Recursos").getString("TSistemaOperativoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Recursos").getString("TSistemaOperativoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Recursos").getString("TSistemaOperativoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TSistemaOperativo> getItems() {
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

    public TSistemaOperativo getTSistemaOperativo(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<TSistemaOperativo> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TSistemaOperativo> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TSistemaOperativo.class)
    public static class TSistemaOperativoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TSistemaOperativoController controller = (TSistemaOperativoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tSistemaOperativoController");
            return controller.getTSistemaOperativo(getKey(value));
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
            if (object instanceof TSistemaOperativo) {
                TSistemaOperativo o = (TSistemaOperativo) object;
                return getStringKey(o.getSoSerial());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TSistemaOperativo.class.getName()});
                return null;
            }
        }

    }

}
