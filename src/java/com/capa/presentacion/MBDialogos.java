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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

@Named(value = "mBDialogo")
@RequestScoped
public class MBDialogos implements Serializable {

    @EJB
    private TTipoBienFacade srvCat;
    @EJB
    private TSistemaOperativoFacade srvSO;
    @EJB
    private TServicioFacade srvServicio;
    @EJB
    private TResponsableFacade srvResp;
    @EJB
    private TMarcaFacade srvMarca;
    @EJB
    private TBienFacade srvBien;

    private TTipoBien categoria;
    private TSistemaOperativo sistema;
    private TResponsable personal;
    private TServicio servicio;
    private TMarca marca;
    private TBien equipoPK;
    private TBien detalle;

    private List<TMarca> marcas;
    private List<TBien> detalles;

    @ManagedProperty("#{mBBienes}")
    private MBBienes mBBienes;

    private boolean tbl_det = false;

    public MBDialogos() {
    }

    @PostConstruct
    public void init() {
        setMarcas(srvMarca.findAll());
        equipoPK = (TBien) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("equipoPK");
    }

    public void crearDetalle() {
        detalle.setPadreBserial(equipoPK);
        detalle.setSSerial(equipoPK.getSSerial());
        detalle.setRSerial(equipoPK.getRSerial());
        detalle.setSoSerial(equipoPK.getSoSerial());
        detalle.setTbSerial(categoria);
        detalle.setMSerial(marca);

        try {
            srvBien.create(detalle);
            tbl_det = true;
            detalle = new TBien();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dispositivo ingresado con Éxito ", null));
//            try {
//                setDetalles(srvBien.findDetalles(equipoPK));
//            } catch (Exception e) {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al buscar la lista: " + e.getMessage(), null));
//            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Código HEE DUPLICADO: " + e.getMessage(), null));
        }
    }

    public void crearTipoBien() {
        try {
            srvCat.create(categoria);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Categoria ingresada: " + categoria.toString(), null));
            categoria = new TTipoBien();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Intente con otro Nombre: " + e.getMessage(), null));
        }
    }

    public void crearSO() {
        try {
            sistema.setSoCadena(sistema.getSoNombre() + " " + sistema.getSoVersion() + " " + sistema.getSoEdicion() + " " + sistema.getSoArquitectura());
            srvSO.create(sistema);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SO ingresado: " + sistema.toString(), null));
            sistema = new TSistemaOperativo();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fallo el ingreso del SO: " + e.getMessage(), null));
        }
    }

    public void crearServicio() {
        try {
            srvServicio.create(servicio);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Servicio ingresado: " + servicio.getSNombre(), null));
            servicio = new TServicio();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Servicio no ingresado: " + e.getMessage(), null));
        }
    }

    public void crearResponsable() {
        try {
            srvResp.create(personal);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Personal a cargo ingresado: " + personal.getRNombres(), null));
            personal = new TResponsable();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario no ingresado: " + e.getMessage(), null));
        }
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

    public TSistemaOperativo getSistema() {
        if (sistema == null) {
            sistema = new TSistemaOperativo();
        }
        return sistema;
    }

    public void setSistema(TSistemaOperativo sistema) {
        this.sistema = sistema;
    }

    public TResponsable getPersonal() {
        if (personal == null) {
            personal = new TResponsable();
        }
        return personal;
    }

    public void setPersonal(TResponsable personal) {
        this.personal = personal;
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

    public TMarca getMarca() {
        if (marca == null) {
            marca = new TMarca();
        }
        return marca;
    }

    public void setMarca(TMarca marca) {
        this.marca = marca;
    }

    public MBBienes getmBBienes() {
        return mBBienes;
    }

    public void setmBBienes(MBBienes mBBienes) {
        this.mBBienes = mBBienes;
    }

    public List<TMarca> getMarcas() {
        return marcas;
    }

    public void setMarcas(List<TMarca> marcas) {
        this.marcas = marcas;
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

    public List<TBien> getDetalles() {
        if (detalles == null) {
            detalles = srvBien.findDetalles(equipoPK);
        }
        return detalles;
    }

    public void setDetalles(List<TBien> detalles) {
        this.detalles = detalles;
    }

    public TBien getEquipoPK() {
        if (equipoPK == null) {
            equipoPK = new TBien();
        }

        return equipoPK;
    }

    public void setEquipoPK(TBien equipoPK) {
        this.equipoPK = equipoPK;
    }

    public TTipoBienFacade getSrvCat() {
        return srvCat;
    }

    public void setSrvCat(TTipoBienFacade srvCat) {
        this.srvCat = srvCat;
    }

    public boolean isTbl_det() {
        return tbl_det;
    }

    public void setTbl_det(boolean tbl_det) {
        this.tbl_det = tbl_det;
    }

}
