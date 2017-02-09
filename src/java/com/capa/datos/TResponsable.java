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
@Table(name = "t_responsable", catalog = "db_inventario_hee", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TResponsable.findAll", query = "SELECT t FROM TResponsable t ORDER BY t.rNombres"),
    @NamedQuery(name = "TResponsable.findByRSerial", query = "SELECT t FROM TResponsable t WHERE t.rSerial = :rSerial ORDER BY t.rNombres"),
    @NamedQuery(name = "TResponsable.findByRNombres", query = "SELECT t FROM TResponsable t WHERE t.rNombres = :rNombres ORDER BY t.rNombres"),
    @NamedQuery(name = "TResponsable.findByRCargo", query = "SELECT t FROM TResponsable t WHERE t.rCargo = :rCargo ORDER BY t.rNombres")})
public class TResponsable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "r_serial", nullable = false)
    private Integer rSerial;
    @Size(max = 250)
    @Column(name = "r_nombres", length = 250)
    private String rNombres;
    @Size(max = 250)
    @Column(name = "r_cargo", length = 250)
    private String rCargo;
    @OneToMany(mappedBy = "rSerial")
    private List<TBien> tBienList;

    public TResponsable() {
    }

    public TResponsable(Integer rSerial) {
        this.rSerial = rSerial;
    }

    public Integer getRSerial() {
        return rSerial;
    }

    public void setRSerial(Integer rSerial) {
        this.rSerial = rSerial;
    }

    public String getRNombres() {
        return rNombres;
    }

    public void setRNombres(String rNombres) {
        this.rNombres = rNombres;
    }

    public String getRCargo() {
        return rCargo;
    }

    public void setRCargo(String rCargo) {
        this.rCargo = rCargo;
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
        hash += (rSerial != null ? rSerial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TResponsable)) {
            return false;
        }
        TResponsable other = (TResponsable) object;
        if ((this.rSerial == null && other.rSerial != null) || (this.rSerial != null && !this.rSerial.equals(other.rSerial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return rNombres;
    }

}
