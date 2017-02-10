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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "t_menu", catalog = "db_inventario_hee", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TMenu.findAll", query = "SELECT t FROM TMenu t"),
    @NamedQuery(name = "TMenu.findByMSerial", query = "SELECT t FROM TMenu t WHERE t.mSerial = :mSerial"),
    @NamedQuery(name = "TMenu.findByMOrden", query = "SELECT t FROM TMenu t WHERE t.mOrden = :mOrden"),
    @NamedQuery(name = "TMenu.findByMNombre", query = "SELECT t FROM TMenu t WHERE t.mNombre = :mNombre"),
    @NamedQuery(name = "TMenu.findByMUrl", query = "SELECT t FROM TMenu t WHERE t.mUrl = :mUrl"),
    @NamedQuery(name = "TMenu.findByMTipo", query = "SELECT t FROM TMenu t WHERE t.mTipo = :mTipo ORDER BY t.mOrden"),
    @NamedQuery(name = "TMenu.findByMNivel", query = "SELECT t FROM TMenu t WHERE t.mNivel = :mNivel")})
public class TMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "m_serial", nullable = false)
    private Integer mSerial;
    @Column(name = "m_orden")
    private Integer mOrden;
    @Size(max = 150)
    @Column(name = "m_nombre", length = 150)
    private String mNombre;
    @Size(max = 200)
    @Column(name = "m_url", length = 200)
    private String mUrl;
    @Size(max = 2)
    @Column(name = "m_tipo", length = 2)
    private String mTipo;
    @Size(max = 5)
    @Column(name = "m_nivel", length = 5)
    private Integer mNivel;
    @OneToMany(mappedBy = "padreMSerial")
    private List<TMenu> tMenuList;
    @JoinColumn(name = "padre_m_serial", referencedColumnName = "m_serial")
    @ManyToOne
    private TMenu padreMSerial;

    public TMenu() {
    }

    public TMenu(Integer mSerial) {
        this.mSerial = mSerial;
    }

    public Integer getMSerial() {
        return mSerial;
    }

    public void setMSerial(Integer mSerial) {
        this.mSerial = mSerial;
    }

    public Integer getMOrden() {
        return mOrden;
    }

    public void setMOrden(Integer mOrden) {
        this.mOrden = mOrden;
    }

    public String getMNombre() {
        return mNombre;
    }

    public void setMNombre(String mNombre) {
        this.mNombre = mNombre;
    }

    public String getMUrl() {
        return mUrl;
    }

    public void setMUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getMTipo() {
        return mTipo;
    }

    public void setMTipo(String mTipo) {
        this.mTipo = mTipo;
    }

    public Integer getMNivel() {
        return mNivel;
    }

    public void setMNivel(Integer mNivel) {
        this.mNivel = mNivel;
    }

    @XmlTransient
    public List<TMenu> getTMenuList() {
        return tMenuList;
    }

    public void setTMenuList(List<TMenu> tMenuList) {
        this.tMenuList = tMenuList;
    }

    public TMenu getPadreMSerial() {
        return padreMSerial;
    }

    public void setPadreMSerial(TMenu padreMSerial) {
        this.padreMSerial = padreMSerial;
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
        if (!(object instanceof TMenu)) {
            return false;
        }
        TMenu other = (TMenu) object;
        if ((this.mSerial == null && other.mSerial != null) || (this.mSerial != null && !this.mSerial.equals(other.mSerial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.capa.datos.TMenu[ mSerial=" + mSerial + " ]";
    }

}
