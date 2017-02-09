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
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author FREDDY
 */
@EntityListeners(AuditListener.class)
@Entity
@Table(name = "t_usuario", catalog = "db_inventario_hee", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"u_nombre"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TUsuario.findAll", query = "SELECT t FROM TUsuario t"),
    @NamedQuery(name = "TUsuario.findByUSerial", query = "SELECT t FROM TUsuario t WHERE t.uSerial = :uSerial"),
    @NamedQuery(name = "TUsuario.findByUNombre", query = "SELECT t FROM TUsuario t WHERE t.uNombre = :uNombre"),
    @NamedQuery(name = "TUsuario.findByUClave", query = "SELECT t FROM TUsuario t WHERE t.uClave = :uClave"),
    @NamedQuery(name = "TUsuario.findByUEstado", query = "SELECT t FROM TUsuario t WHERE t.uEstado = :uEstado"),
    @NamedQuery(name = "TUsuario.findByUTipo", query = "SELECT t FROM TUsuario t WHERE t.uTipo = :uTipo")})
public class TUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "u_serial", nullable = false)
    private Integer uSerial;
    @Size(max = 50)
    @Column(name = "u_nombre", length = 50)
    private String uNombre;
    @Size(max = 32)
    @Column(name = "u_clave", length = 32)
    private String uClave;
    @Column(name = "u_estado")
    private Boolean uEstado;
    @Size(max = 2)
    @Column(name = "u_tipo", length = 2)
    private String uTipo;
    @OneToMany(mappedBy = "uSerial")
    private List<TMantenimiento> tMantenimientoList;

    public TUsuario() {
    }

    public TUsuario(Integer uSerial) {
        this.uSerial = uSerial;
    }

    public Integer getUSerial() {
        return uSerial;
    }

    public void setUSerial(Integer uSerial) {
        this.uSerial = uSerial;
    }

    public String getUNombre() {
        return uNombre;
    }

    public void setUNombre(String uNombre) {
        this.uNombre = uNombre;
    }

    public String getUClave() {
        return uClave;
    }

    public void setUClave(String uClave) {
        if (!uClave.isEmpty()) {
            this.uClave = DigestUtils.md5Hex(uClave);
        }

    }

    public Boolean getUEstado() {
        return uEstado;
    }

    public void setUEstado(Boolean uEstado) {
        this.uEstado = uEstado;
    }

    public String getUTipo() {
        return uTipo;
    }

    public void setUTipo(String uTipo) {
        this.uTipo = uTipo;
    }

    @XmlTransient
    public List<TMantenimiento> getTMantenimientoList() {
        return tMantenimientoList;
    }

    public void setTMantenimientoList(List<TMantenimiento> tMantenimientoList) {
        this.tMantenimientoList = tMantenimientoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uSerial != null ? uSerial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TUsuario) {
            TUsuario usuario = (TUsuario) obj;
            if (usuario.getUClave().equals(this.uClave)) {
                return true;
            }
        } else {
            return false;
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "TUsuario{" + "uSerial=" + uSerial + ", uNombre=" + uNombre + ", uClave=" + uClave + ", uEstado=" + uEstado + ", uTipo=" + uTipo + '}';
    }

}
