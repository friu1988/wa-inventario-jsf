package com.capa.presentacion;

import com.capa.datos.TUsuario;
import com.capa.presentacion.util.JsfUtil;
import com.capa.presentacion.util.JsfUtil.PersistAction;
import com.capa.negocios.TUsuarioFacade;

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
import org.apache.commons.codec.digest.DigestUtils;
//import org.apache.commons.codec.digest.DigestUtils;

@Named("tUsuarioController")
@SessionScoped
public class TUsuarioController implements Serializable {

    @EJB
    private com.capa.negocios.TUsuarioFacade ejbFacade;
    private List<TUsuario> items = null;
    private TUsuario selected;

    public TUsuarioController() {
    }

    public TUsuario getSelected() {
        return selected;
    }

    public void setSelected(TUsuario selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TUsuarioFacade getFacade() {
        return ejbFacade;
    }

    public TUsuario prepareCreate() {
        selected = new TUsuario();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Recursos").getString("TUsuarioCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Recursos").getString("TUsuarioUpdated"));
    }

    public void destroy() {
        selected.setUClave(DigestUtils.md5Hex(getSelected().getUClave()));
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Recursos").getString("TUsuarioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TUsuario> getItems() {
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

    public TUsuario getTUsuario(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<TUsuario> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TUsuario> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TUsuario.class)
    public static class TUsuarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TUsuarioController controller = (TUsuarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tUsuarioController");
            return controller.getTUsuario(getKey(value));
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
            if (object instanceof TUsuario) {
                TUsuario o = (TUsuario) object;
                return getStringKey(o.getUSerial());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TUsuario.class.getName()});
                return null;
            }
        }

    }

}
