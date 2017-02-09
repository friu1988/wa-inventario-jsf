package com.capa.presentacion;

import com.capa.datos.TBien;
import com.capa.datos.TMarca;
import com.capa.datos.TTipoBien;
import com.capa.datos.TUsuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "mBEdicionEquipos")
@SessionScoped
public class MBEdicionEquipos implements Serializable {

    @EJB
    private com.capa.negocios.TBienFacade srvDetalles;
    private List<TBien> detalles;
    private TBien cabecera;
    private TBien detalle;
    private TTipoBien dispositivo;
    private TMarca marca;

    public MBEdicionEquipos() {
    }

    public TBien prepareCreate() {
        detalle = new TBien();
        return detalle;
    }

    public void crearDetalle() {
        Calendar cal = Calendar.getInstance();

        detalle.setPadreBserial(cabecera);
        detalle.setSSerial(cabecera.getSSerial());
        detalle.setRSerial(cabecera.getRSerial());
        detalle.setTbSerial(dispositivo);
        detalle.setMSerial(marca);
        detalle.setbRegistro(cal.getTime());
        try {
            if (detalle.getBCodigoHee().equals("")) {
                detalle.setBCodigoHee(cabecera.getBCodigoHee());
            }
            srvDetalles.create(detalle);
            detalles = null;
            marca = new TMarca();
            dispositivo = new TTipoBien();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dispositivo ingresado con Éxito ", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Código HEE DUPLICADO: " + e.getMessage(), null));
        }
    }

    public void actualizarCabecera() {

        try {
            srvDetalles.edit(cabecera);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cabecera actualizada: " + cabecera.getBSerial(), null));
            cabecera = null;
            detalles = null;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar" + e.getMessage(), null));
        }

    }

    public void actualizarDetalle() {
        try {
            srvDetalles.edit(detalle);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dispositivo ACTUALIZADO! ", null));
            detalle = null;
            detalles = null;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar!" + e.getMessage(), null));
        }
    }

    public void eliminarDetalle() {
        try {
            srvDetalles.remove(detalle);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dispositivo ELIMINADO! ", null));
            detalle = null;
            detalles = null;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Eliminar! " + e.getMessage(), null));
        }
    }

    public List<TBien> getDetalles() {
        detalles = srvDetalles.buscarDet(cabecera);
        return detalles;
    }

    public void setDetalles(List<TBien> detalles) {
        this.detalles = detalles;
    }

    public TBien getDetalle() {
        return detalle;
    }

    public void setDetalle(TBien detalle) {
        this.detalle = detalle;
    }

    public TBien getCabecera() {
        cabecera = (TBien) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cabecera");
        return cabecera;
    }

    public void setCabecera(TBien cabecera) {
        this.cabecera = cabecera;
    }

    public TTipoBien getDispositivo() {
        if (dispositivo == null) {
            dispositivo = new TTipoBien();
        }
        return dispositivo;
    }

    public void setDispositivo(TTipoBien dispositivo) {
        this.dispositivo = dispositivo;
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

}
