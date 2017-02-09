package com.capa.presentacion;

import com.capa.datos.TBien;
import com.capa.datos.TMarca;

import com.capa.datos.TResponsable;
import com.capa.datos.TServicio;
import com.capa.datos.TSistemaOperativo;
import com.capa.datos.TTipoBien;
import com.capa.negocios.TBienFacade;
import com.capa.negocios.TMarcaFacade;
import com.capa.negocios.TResponsableFacade;
import com.capa.negocios.TServicioFacade;
import com.capa.negocios.TSistemaOperativoFacade;
import com.capa.negocios.TTipoBienFacade;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "mBBienes")
@SessionScoped
public class MBBienes implements Serializable {

    @EJB
    private TTipoBienFacade srvCatBien;
    @EJB
    private TServicioFacade srvDep;
    @EJB
    private TResponsableFacade srvREsp;
    @EJB
    private TSistemaOperativoFacade srvSis;
    @EJB
    private TBienFacade srvEq;
    @EJB
    private TMarcaFacade srvMar;

    @ManagedProperty(value = "#{tBienController}")
    private TBienController mB;

    private List<TServicio> servicios;
    private List<TResponsable> responsables;
    private List<TSistemaOperativo> sistemas;
    private List<TBien> equipos;
    private List<TMarca> marcas;

    private TTipoBien categoria;
    private TServicio servicio;
    private TResponsable responsabe;
    private TSistemaOperativo sistema;
    private TBien equipo;
    private TMarca marca;

    private boolean ipAddress;
    private boolean bol_tab = false;
    private boolean bol_det = false;

    public MBBienes() {
    }

    @PostConstruct
    private void init() {
        setServicios(srvDep.findAll());
        setResponsables(srvREsp.findAll());
        setSistemas(srvSis.findAll());
        setMarcas(srvMar.findAll());
        setEquipos(srvEq.findAll());

    }

    public void crearDetalle() {
        bol_tab = true;
        TBien detalle = new TBien();

        detalle.setTbSerial(categoria);
        detalle.setSSerial(servicio);
        detalle.setRSerial(responsabe);
        detalle.setSoSerial(sistema);
        detalle.setMSerial(marca);
        detalle.setPadreBserial(equipo);
        detalle.setBCodigoHee(equipo.getBCodigoHee());

        try {
            srvEq.create(detalle);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dispositivo ingresado: " + detalle.getBSerie(), null));
            setEquipos(srvEq.findDetalles(equipo));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "NO Ingresado: " + e.getMessage(), null));
        }

    }

    public void limpiar() {
        bol_det = false;
        bol_tab = false;

        equipo = new TBien();
        categoria = new TTipoBien();
        servicio = new TServicio();
        responsabe = new TResponsable();
        sistema = new TSistemaOperativo();
        marca = new TMarca();
        TBienController.tbl_det = false;
    }

    public void crearEquipo() {
        Calendar cal = Calendar.getInstance();
        equipo.setTbSerial(categoria);
        equipo.setSSerial(servicio);
        equipo.setRSerial(responsabe);
        equipo.setSoSerial(sistema);
        equipo.setMSerial(marca);
        equipo.setbRegistro(cal.getTime());

        try {
            srvEq.create(equipo);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("equipoPK", equipo);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Equipo ingresado: " + equipo.getBSerial(), null));
            bol_det = true;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Código HEE DUPLICADO" + e.getMessage(), null));
        }
    }

    public List<TServicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<TServicio> servicios) {
        servicios.get(0).getSNombre();
        this.servicios = servicios;
    }

    public List<TResponsable> getResponsables() {
        return responsables;
    }

    public void setResponsables(List<TResponsable> responsables) {
        this.responsables = responsables;
    }

    public List<TSistemaOperativo> getSistemas() {
        return sistemas;
    }

    public void setSistemas(List<TSistemaOperativo> sistemas) {
        this.sistemas = sistemas;
    }

    public List<TBien> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<TBien> equipos) {
        this.equipos = equipos;
    }

    public List<TMarca> getMarcas() {
        return marcas;
    }

    public void setMarcas(List<TMarca> marcas) {
        this.marcas = marcas;
    }

    public TServicio getServicio() {
        if (servicio == null) {
            servicio = new TServicio();
        }
        return servicio;
    }

    public void setServicio(TServicio servicio) {
        this.servicio = servicio;
    }

    public TResponsable getResponsabe() {
        if (responsabe == null) {
            responsabe = new TResponsable();
        }
        return responsabe;
    }

    public void setResponsabe(TResponsable responsabe) {
        this.responsabe = responsabe;
    }

    public TSistemaOperativo getSistema() {
        if (sistema == null) {
            sistema = new TSistemaOperativo();
        }
        return sistema;
    }

    public void setSistema(TSistemaOperativo sistema) {
        this.sistema = sistema;
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

    public TBien getEquipo() {
        if (equipo == null) {
            equipo = new TBien();
        }
        return equipo;
    }

    public void setEquipo(TBien equipo) {
        this.equipo = equipo;
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

    public boolean isIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(boolean ipAddress) {
        this.ipAddress = ipAddress;
    }

    public boolean isBol_tab() {
        return bol_tab;
    }

    public void setBol_tab(boolean bol_tab) {
        this.bol_tab = bol_tab;
    }

    public boolean isBol_det() {
        return bol_det;
    }

    public void setBol_det(boolean bol_det) {
        this.bol_det = bol_det;
    }

    public TBienController getmB() {
        return mB;
    }

    public void setmB(TBienController mB) {
        this.mB = mB;
    }

}
