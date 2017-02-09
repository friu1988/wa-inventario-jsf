/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capa.datos;

import com.capa.negocios.AuditListener;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author FREDDY
 */
@EntityListeners(AuditListener.class)
@Entity
@Table(name = "t_marca", catalog = "db_inventario_hee", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"m_nombre"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TMarca.findAll", query = "SELECT t FROM TMarca t ORDER BY t.mNombre"),
    @NamedQuery(name = "TMarca.findByMSerial", query = "SELECT t FROM TMarca t WHERE t.mSerial = :mSerial"),
    @NamedQuery(name = "TMarca.findByMNombre", query = "SELECT t FROM TMarca t WHERE t.mNombre = :mNombre")})
public class TMarca implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "m_serial", nullable = false)
    private Integer mSerial;
    @Size(max = 150)
    @Column(name = "m_nombre", length = 150)
    private String mNombre;
    @OneToMany(mappedBy = "mSerial")
    private List<TBien> tBienList;

    public TMarca() {
    }

    public TMarca(Integer mSerial) {
        this.mSerial = mSerial;
    }

    public Integer getMSerial() {
        return mSerial;
    }

    public void setMSerial(Integer mSerial) {
        this.mSerial = mSerial;
    }

    public String getMNombre() {
        return mNombre;
    }

    public void setMNombre(String mNombre) {
        this.mNombre = mNombre.toUpperCase();
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
        hash += (mSerial != null ? mSerial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TMarca)) {
            return false;
        }
        TMarca other = (TMarca) object;
        if ((this.mSerial == null && other.mSerial != null) || (this.mSerial != null && !this.mSerial.equals(other.mSerial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return mNombre;
    }

}
