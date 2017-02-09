/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capa.datos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author FREDDY
 */
@Entity
@Table(name = "t_servicio", catalog = "db_inventario_hee", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TServicio.findAll", query = "SELECT t FROM TServicio t"),
    @NamedQuery(name = "TServicio.findBySSerial", query = "SELECT t FROM TServicio t WHERE t.sSerial = :sSerial"),
    @NamedQuery(name = "TServicio.findBySCodigo", query = "SELECT t FROM TServicio t WHERE t.sCodigo = :sCodigo"),
    @NamedQuery(name = "TServicio.findBySNombre", query = "SELECT t FROM TServicio t WHERE t.sNombre = :sNombre"),
    @NamedQuery(name = "TServicio.findBySUbicacion", query = "SELECT t FROM TServicio t WHERE t.sUbicacion = :sUbicacion"),
    @NamedQuery(name = "TServicio.findBySDescripcion", query = "SELECT t FROM TServicio t WHERE t.sDescripcion = :sDescripcion"),
    @NamedQuery(name = "TServicio.findBySEstado", query = "SELECT t FROM TServicio t WHERE t.sEstado = :sEstado")})
public class TServicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "s_serial", nullable = false)
    private Integer sSerial;
    @Size(max = 100)
    @Column(name = "s_codigo", length = 100)
    private String sCodigo;
    @Size(max = 200)
    @Column(name = "s_nombre", length = 200)
    private String sNombre;
    @Size(max = 200)
    @Column(name = "s_ubicacion", length = 200)
    private String sUbicacion;
    @Size(max = 200)
    @Column(name = "s_descripcion", length = 200)
    private String sDescripcion;
    @Column(name = "s_estado")
    private Boolean sEstado;
    @OneToMany(mappedBy = "sSerial")
    private List<TBien> tBienList;

    public TServicio() {
    }

    public TServicio(Integer sSerial) {
        this.sSerial = sSerial;
    }

    public Integer getSSerial() {
        return sSerial;
    }

    public void setSSerial(Integer sSerial) {
        this.sSerial = sSerial;
    }

    public String getSCodigo() {
        return sCodigo;
    }

    public void setSCodigo(String sCodigo) {
        this.sCodigo = sCodigo.toUpperCase();
    }

    public String getSNombre() {
        return sNombre;
    }

    public void setSNombre(String sNombre) {
        this.sNombre = sNombre.toUpperCase();
    }

    public String getSUbicacion() {
        return sUbicacion;
    }

    public void setSUbicacion(String sUbicacion) {
        this.sUbicacion = sUbicacion.toUpperCase();
    }

    public String getSDescripcion() {
        return sDescripcion;
    }

    public void setSDescripcion(String sDescripcion) {
        this.sDescripcion = sDescripcion.toUpperCase();
    }

    public Boolean getSEstado() {
        return sEstado;
    }

    public void setSEstado(Boolean sEstado) {
        this.sEstado = sEstado;
    }

    @XmlTransient
    public List<TBien> getTBienList() {
        return tBienList;
    }

    public void setTBienList(List<TBien> tBienList) {
        this.tBienList = tBienList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sSerial != null ? sSerial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TServicio)) {
            return false;
        }
        TServicio other = (TServicio) object;
        if ((this.sSerial == null && other.sSerial != null) || (this.sSerial != null && !this.sSerial.equals(other.sSerial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return sNombre;
    }

}
