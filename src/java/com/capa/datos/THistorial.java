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
@Table(name = "t_historial", catalog = "db_inventario_hee", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "THistorial.findAll", query = "SELECT t FROM THistorial t"),
    @NamedQuery(name = "THistorial.findByHSerial", query = "SELECT t FROM THistorial t WHERE t.hSerial = :hSerial"),
    @NamedQuery(name = "THistorial.findByHUsuario", query = "SELECT t FROM THistorial t WHERE t.hUsuario = :hUsuario"),
    @NamedQuery(name = "THistorial.findByHFecha", query = "SELECT t FROM THistorial t WHERE t.hFecha = :hFecha"),
    @NamedQuery(name = "THistorial.findByHTransaccion", query = "SELECT t FROM THistorial t WHERE t.hTransaccion = :hTransaccion"),
    @NamedQuery(name = "THistorial.findByHTabla", query = "SELECT t FROM THistorial t WHERE t.hTabla = :hTabla"),
    @NamedQuery(name = "THistorial.findByHAtributo", query = "SELECT t FROM THistorial t WHERE t.hAtributo = :hAtributo"),
    @NamedQuery(name = "THistorial.findByHValorOriginal", query = "SELECT t FROM THistorial t WHERE t.hValorOriginal = :hValorOriginal"),
    @NamedQuery(name = "THistorial.findByHValorNuevo", query = "SELECT t FROM THistorial t WHERE t.hValorNuevo = :hValorNuevo")})
public class THistorial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "h_serial", nullable = false)
    private Integer hSerial;
    @Size(max = 200)
    @Column(name = "h_usuario", length = 200)
    private String hUsuario;
    @Column(name = "h_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hFecha;
    @Size(max = 200)
    @Column(name = "h_transaccion", length = 200)
    private String hTransaccion;
    @Size(max = 200)
    @Column(name = "h_tabla", length = 200)
    private String hTabla;
    @Size(max = 200)
    @Column(name = "h_atributo", length = 200)
    private String hAtributo;
    @Size(max = 200)
    @Column(name = "h_valor_original", length = 200)
    private String hValorOriginal;
    @Size(max = 200)
    @Column(name = "h_valor_nuevo", length = 200)
    private String hValorNuevo;

    public THistorial() {
    }

    public THistorial(String hUsuario, Date hFecha, String hTransaccion, String hTabla, String hAtributo, String hValorOriginal, String hValorNuevo) {
        this.hUsuario = hUsuario;
        this.hFecha = hFecha;
        this.hTransaccion = hTransaccion;
        this.hTabla = hTabla;
        this.hAtributo = hAtributo;
        this.hValorOriginal = hValorOriginal;
        this.hValorNuevo = hValorNuevo;
    }

    public THistorial(Integer hSerial) {
        this.hSerial = hSerial;
    }

    public Integer getHSerial() {
        return hSerial;
    }

    public void setHSerial(Integer hSerial) {
        this.hSerial = hSerial;
    }

    public String getHUsuario() {
        return hUsuario;
    }

    public void setHUsuario(String hUsuario) {
        this.hUsuario = hUsuario;
    }

    public Date getHFecha() {
        return hFecha;
    }

    public void setHFecha(Date hFecha) {
        this.hFecha = hFecha;
    }

    public String getHTransaccion() {
        return hTransaccion;
    }

    public void setHTransaccion(String hTransaccion) {
        this.hTransaccion = hTransaccion;
    }

    public String getHTabla() {
        return hTabla;
    }

    public void setHTabla(String hTabla) {
        this.hTabla = hTabla;
    }

    public String getHAtributo() {
        return hAtributo;
    }

    public void setHAtributo(String hAtributo) {
        this.hAtributo = hAtributo;
    }

    public String getHValorOriginal() {
        return hValorOriginal;
    }

    public void setHValorOriginal(String hValorOriginal) {
        this.hValorOriginal = hValorOriginal;
    }

    public String getHValorNuevo() {
        return hValorNuevo;
    }

    public void setHValorNuevo(String hValorNuevo) {
        this.hValorNuevo = hValorNuevo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hSerial != null ? hSerial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof THistorial)) {
            return false;
        }
        THistorial other = (THistorial) object;
        if ((this.hSerial == null && other.hSerial != null) || (this.hSerial != null && !this.hSerial.equals(other.hSerial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "THistorial{" + "hSerial=" + hSerial + ", hUsuario=" + hUsuario + ", hFecha=" + hFecha + ", hTransaccion=" + hTransaccion + ", hTabla=" + hTabla + ", hAtributo=" + hAtributo + ", hValorOriginal=" + hValorOriginal + ", hValorNuevo=" + hValorNuevo + '}';
    }
}
