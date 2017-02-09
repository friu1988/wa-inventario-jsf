/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capa.datos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author FREDDY
 */
@Entity
@Table(name = "t_mantenimiento", catalog = "db_inventario_hee", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TMantenimiento.findAll", query = "SELECT t FROM TMantenimiento t"),
    @NamedQuery(name = "TMantenimiento.findByMSerial", query = "SELECT t FROM TMantenimiento t WHERE t.mSerial = :mSerial"),
    @NamedQuery(name = "TMantenimiento.findByMTrabajoRealizado", query = "SELECT t FROM TMantenimiento t WHERE t.mTrabajoRealizado = :mTrabajoRealizado"),
    @NamedQuery(name = "TMantenimiento.findByMFecha", query = "SELECT t FROM TMantenimiento t WHERE t.mFecha = :mFecha"),
    @NamedQuery(name = "TMantenimiento.findByMResponsable", query = "SELECT t FROM TMantenimiento t WHERE t.mResponsable = :mResponsable")})
public class TMantenimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "m_serial", nullable = false)
    private Integer mSerial;
    @Size(max = 255)
    @Column(name = "m_trabajo_realizado", length = 255)
    private String mTrabajoRealizado;
    @Column(name = "m_fecha")
    @Temporal(TemporalType.DATE)
    private Date mFecha;
    @Size(max = 200)
    @Column(name = "m_responsable", length = 200)
    private String mResponsable;
    @JoinColumn(name = "b_serial", referencedColumnName = "b_serial")
    @ManyToOne
    private TBien bSerial;
    @JoinColumn(name = "u_serial", referencedColumnName = "u_serial")
    @ManyToOne
    private TUsuario uSerial;

    public TMantenimiento() {
    }

    public TMantenimiento(Integer mSerial) {
        this.mSerial = mSerial;
    }

    public Integer getMSerial() {
        return mSerial;
    }

    public void setMSerial(Integer mSerial) {
        this.mSerial = mSerial;
    }

    public String getMTrabajoRealizado() {
        return mTrabajoRealizado;
    }

    public void setMTrabajoRealizado(String mTrabajoRealizado) {
        this.mTrabajoRealizado = mTrabajoRealizado;
    }

    public Date getMFecha() {
        return mFecha;
    }

    public void setMFecha(Date mFecha) {
        this.mFecha = mFecha;
    }

    public String getMResponsable() {
        return mResponsable;
    }

    public void setMResponsable(String mResponsable) {
        this.mResponsable = mResponsable;
    }

    public TBien getBSerial() {
        return bSerial;
    }

    public void setBSerial(TBien bSerial) {
        this.bSerial = bSerial;
    }

    public TUsuario getUSerial() {
        return uSerial;
    }

    public void setUSerial(TUsuario uSerial) {
        this.uSerial = uSerial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mSerial != null ? mSerial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TMantenimiento)) {
            return false;
        }
        TMantenimiento other = (TMantenimiento) object;
        if ((this.mSerial == null && other.mSerial != null) || (this.mSerial != null && !this.mSerial.equals(other.mSerial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.capa.datos.TMantenimiento[ mSerial=" + mSerial + " ]";
    }
    
}
