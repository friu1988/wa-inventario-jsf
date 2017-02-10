/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capa.datos;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "t_tipo_bien", catalog = "db_inventario_hee", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"tb_nombre"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TTipoBien.findAll", query = "SELECT t FROM TTipoBien t ORDER BY t.tbNombre"),
    @NamedQuery(name = "TTipoBien.findAlls", query = "SELECT t FROM TTipoBien t ORDER BY t.tbTipo"),
    @NamedQuery(name = "TTipoBien.findByTbSerial", query = "SELECT t FROM TTipoBien t WHERE t.tbSerial = :tbSerial"),
    @NamedQuery(name = "TTipoBien.findByTbNombre", query = "SELECT t FROM TTipoBien t WHERE t.tbNombre = :tbNombre ORDER BY t.tbNombre"),
    @NamedQuery(name = "TTipoBien.findByTbTipo", query = "SELECT t FROM TTipoBien t WHERE t.tbTipo = :tbTipo ORDER BY t.tbNombre")})
public class TTipoBien implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tb_serial", nullable = false)
    private Integer tbSerial;
    @Size(max = 150)
    @Column(name = "tb_nombre", length = 150)
    private String tbNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "tb_tipo", nullable = false, length = 11)
    private String tbTipo;
    @OneToMany(mappedBy = "tbSerial")
    private List<TBien> tBienList;

    public TTipoBien() {
    }

    public TTipoBien(Integer tbSerial) {
        this.tbSerial = tbSerial;
    }

    public TTipoBien(Integer tbSerial, String tbTipo) {
        this.tbSerial = tbSerial;
        this.tbTipo = tbTipo;
    }

    public Integer getTbSerial() {
        return tbSerial;
    }

    public void setTbSerial(Integer tbSerial) {
        this.tbSerial = tbSerial;
    }

    public String getTbNombre() {
        return tbNombre;
    }

    public void setTbNombre(String tbNombre) {
        this.tbNombre = tbNombre.toUpperCase();
    }

    public String getTbTipo() {
        return tbTipo;
    }

    public void setTbTipo(String tbTipo) {
        this.tbTipo = tbTipo.toUpperCase();
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
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.tbNombre);
        hash = 59 * hash + Objects.hashCode(this.tbTipo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TTipoBien other = (TTipoBien) obj;
        if (!Objects.equals(this.tbNombre, other.tbNombre)) {
            return false;
        }
        if (!Objects.equals(this.tbTipo, other.tbTipo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return tbTipo;
    }

}
