package com.capa.presentacion;

import com.capa.datos.TBien;
import com.capa.datos.TUsuario;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "mBEquipos")
@SessionScoped
public class MBEquipos implements Serializable {

    @EJB
    private com.capa.negocios.TBienFacade srvEq;
    private List<TBien> equipos = null;
    private List<TBien> detalles = null;
    private List<TBien> dispositivos= null;
    private TBien selected;
    private TUsuario us;
    private boolean btnCrear = true;
    private boolean btnActualizar = true;
    private boolean btnEliminar = true;

    public MBEquipos() {
        restringirAcceso();
    }

    public void restringirAcceso() {
        us = new TUsuario();
        us = (TUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLogin");
        if (us.getUTipo().equals("U")) {
            setBtnEliminar(false);
        } else if (us.getUTipo().equals("C")) {
            setBtnCrear(false);
            setBtnEliminar(false);
            setBtnActualizar(false);
        }

    }

    public void pasarCabecera() throws IOException {
        System.out.println("Cabecera enviada>>>" + selected.getBCodigoHee());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cabecera", selected);
        //FacesContext.getCurrentInstance().getExternalContext().redirect("./../tBien/editar_equipos.xhtml");
        selected = null;

    }

    public void destroy() {
        try {
            srvEq.remove(selected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dispositivo ELIMINADO! ", null));
            selected = null;
            equipos = null;
            dispositivos=null;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Eliminar primero los Detalles! " + e.getMessage(), null));
        }
    }

    public List<TBien> cargarEquipos() {
        List<TBien> temporal = srvEq.findAll();
        List<TBien> resultado = new LinkedList<>();

        for (TBien tBien : temporal) {
            if (tBien.getPadreBserial() == null) {
                resultado.add(tBien);
            }
            
        }
        return resultado;
    }
 public List<TBien> cargarDispositivos() {
        List<TBien> temporal = srvEq.findDispositivos();
        List<TBien> resultado = new LinkedList<>();

        for (TBien tBien : temporal) {
            if(tBien.getTbSerial().getTbTipo().equals("DISPOSITIVO"))
                resultado.add(tBien);
            
        }
        return resultado;
    }

    
    public List<TBien> getEquipos() {
        if (equipos == null) {
            equipos = cargarEquipos();
        }
        return equipos;
    }

    public void setEquipos(List<TBien> equipos) {
        this.equipos = equipos;
    }

    public List<TBien> getDispositivos() {
        if (dispositivos == null) {
            dispositivos = cargarDispositivos();
        }
        return dispositivos;
    }

    public void setDispositivos(List<TBien> dispositivos) {
        this.dispositivos = dispositivos;
    }
    

    public TBien getSelected() {
//        if (selected == null) {
//            selected = new TBien();
//        }
        return selected;
    }

    public void setSelected(TBien selected) {
        this.selected = selected;
    }

    public List<TBien> getDetalles() {
        if (selected != null) {
            detalles = srvEq.buscarDet(selected);
        }
        return detalles;
    }

    public void setDetalles(List<TBien> detalles) {
        this.detalles = detalles;
    }

    public TUsuario getUs() {
        return us;
    }

    public void setUs(TUsuario us) {
        this.us = us;
    }

    public boolean isBtnCrear() {
        return btnCrear;
    }

    public void setBtnCrear(boolean btnCrear) {
        this.btnCrear = btnCrear;
    }

    public boolean isBtnActualizar() {
        return btnActualizar;
    }

    public void setBtnActualizar(boolean btnActualizar) {
        this.btnActualizar = btnActualizar;
    }

    public boolean isBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(boolean btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

}
