package com.capa.presentacion;

import com.capa.datos.TBien;
import com.capa.datos.TMarca;
import com.capa.datos.TTipoBien;
import com.capa.presentacion.util.JsfUtil;
import com.capa.presentacion.util.JsfUtil.PersistAction;
import com.capa.negocios.TBienFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("tBienController")
@SessionScoped
public class TBienController implements Serializable {

    @EJB
    private com.capa.negocios.TBienFacade ejbFacade;

    private List<TBien> items = null;
    private TBien selected;

    private List<TBien> detalles = null;
//    private List<TBien> detallesEditar = null;

    private List<TBien> cabeceras = null;

    private TBien detalle;
    private TBien equipoPK;

    private TTipoBien categoria;
    private TMarca marca;
    public static boolean tbl_det;

    public TBienController() {
        tbl_det = false;
    }

    public void eliminarDet() {
        try {
            System.out.println("Eliminar detalle------------> " + detalle.toString());
            getFacade().remove(detalle);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dispositivo ELIMINADO! ", null));
            detalle = new TBien();
//            detallesEditar = null;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "NO eliminado: " + e.getMessage(), null));
        }
    }

    public void actualizarDet() {
        detalles = null;
        try {
            getFacade().edit(detalle);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dispositivo ACTUALIZADO! ", null));
            detalle = new TBien();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "NO eliminado: " + e.getMessage(), null));
        }

    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TBienFacade getFacade() {
        return ejbFacade;
    }

    public TBien prepareCreate() {
        selected = new TBien();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Recursos").getString("TBienCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            cabeceras = null;
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Recursos").getString("TBienUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Recursos").getString("TBienDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            cabeceras = null;
        }

    }

    public List<TBien> getItems() {
        if (items == null) {
            items = new ArrayList<>();
            padresCabecera();
        }
        return items;
    }

    public List<TBien> padresCabecera() {
        List<TBien> temporal = getFacade().findAll();
        List<TBien> resultado = new LinkedList<>();

        for (TBien tBien : temporal) {
            if (tBien.getPadreBserial() == null) {
                resultado.add(tBien);
            }
        }
        return resultado;
    }

    public List<TBien> getDetalles() {
        detalles = getFacade().findDetalles(equipoPK);
        return detalles;
    }

    public void setDetalles(List<TBien> detalles) {
        this.detalles = detalles;
    }

//    public List<TBien> getDetallesEditar() {
//        return detallesEditar = getFacade().buscarDet(selected);
//    }
    public void crearDetalle() {
        Calendar cal = Calendar.getInstance();

        detalle.setPadreBserial(equipoPK);
        detalle.setSSerial(equipoPK.getSSerial());
        detalle.setRSerial(equipoPK.getRSerial());
        detalle.setSoSerial(equipoPK.getSoSerial());
        detalle.setTbSerial(categoria);
        detalle.setMSerial(marca);
        detalle.setbRegistro(cal.getTime());
        try {
            if (detalle.getBCodigoHee().equals("")) {
                detalle.setBCodigoHee(equipoPK.getBCodigoHee());
            }
            getFacade().create(detalle);
            tbl_det = true;
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dispositivo ingresado con Éxito ", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Código HEE DUPLICADO: " + e.getMessage(), null));
        }
    }

    public void limpiar() {
        detalle = new TBien();
        categoria = new TTipoBien();
        marca = new TMarca();
        detalles = null;
    }

    public void limpiarBol() {
        tbl_det = false;
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

    @FacesConverter(forClass = TBien.class)
    public static class TBienControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TBienController controller = (TBienController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tBienController");
            return controller.getTBien(getKey(value));
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
            if (object instanceof TBien) {
                TBien o = (TBien) object;
                return getStringKey(o.getBSerial());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TBien.class.getName()});
                return null;
            }
        }

    }

    public TBien getTBien(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<TBien> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TBien> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public TBien getSelected() {
        return selected;
    }

    public void setSelected(TBien selected) {
        this.selected = selected;
    }

    public TBien getEquipoPK() {
        equipoPK = (TBien) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("equipoPK");
        return equipoPK;
    }

    public void setEquipoPK(TBien equipoPK) {
        this.equipoPK = equipoPK;
    }

    public TTipoBien getCategoria() {
        if (categoria == null) {
            categoria = new TTipoBien();
        }
        return categoria;
    }

    public void setCategoria(TTipoBien categoria) {
        this.categoria = categoria;
    }

    public TMarca getMarca() {
        if (marca == null) {
            marca = new TMarca();
        }
        return marca;
    }

    public void setMarca(TMarca marca) {
        this.marca = marca;
    }

    public TBien getDetalle() {
        if (detalle == null) {
            detalle = new TBien();
        }
        return detalle;
    }

    public void setDetalle(TBien detalle) {
        this.detalle = detalle;
    }

    public boolean isTbl_det() {
        return tbl_det;
    }

    public void setTbl_det(boolean tbl_det) {
        TBienController.tbl_det = tbl_det;
    }

    public List<TBien> getCabeceras() {
        if (cabeceras == null) {
            cabeceras = padresCabecera();
        }
        return cabeceras;
    }

    public void setCabeceras(List<TBien> cabeceras) {
        this.cabeceras = cabeceras;
    }

}
