/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capa.datos;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author FREDDY
 */
@Entity
@Table(name = "t_bien", catalog = "db_inventario_hee", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TBien.findAll", query = "SELECT t FROM TBien t"),
    @NamedQuery(name = "TBien.findDispositivos", query = "SELECT t FROM TBien t, TTipoBien tb WHERE t.tbSerial.tbSerial= tb.tbSerial AND T.tbSerial.tbTipo='DISPOSITIVO'"),
    @NamedQuery(name = "TBien.findByBSerial", query = "SELECT t FROM TBien t WHERE t.padreBserial.bSerial = :bSerial"),
    @NamedQuery(name = "TBien.findByBPadreSerial", query = "SELECT t FROM TBien t WHERE t.padreBserial.bSerial = :padreBserial"),
    @NamedQuery(name = "TBien.findByBCodigoHee", query = "SELECT t FROM TBien t WHERE t.bCodigoHee = :bCodigoHee"),
    @NamedQuery(name = "TBien.findByBIpAdress", query = "SELECT t FROM TBien t WHERE t.bIpAdress = :bIpAdress"),
    @NamedQuery(name = "TBien.findByBModelo", query = "SELECT t FROM TBien t WHERE t.bModelo = :bModelo"),
    @NamedQuery(name = "TBien.findByBSerie", query = "SELECT t FROM TBien t WHERE t.bSerie = :bSerie"),
    @NamedQuery(name = "TBien.findByBTipo", query = "SELECT t FROM TBien t WHERE t.bTipo = :bTipo"),
    @NamedQuery(name = "TBien.findByBCapacidad", query = "SELECT t FROM TBien t WHERE t.bCapacidad = :bCapacidad"),
    @NamedQuery(name = "TBien.findByBDescripcion", query = "SELECT t FROM TBien t WHERE t.bDescripcion = :bDescripcion"),
    @NamedQuery(name = "TBien.findByBEstado", query = "SELECT t FROM TBien t WHERE t.bEstado = :bEstado"),
    @NamedQuery(name = "TBien.findByBRegistro", query = "SELECT t FROM TBien t WHERE t.bRegistro = :bRegistro")})
public class TBien implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "b_serial", nullable = false)
    private Integer bSerial;
    @Size(max = 50)
    @Column(name = "b_codigo_hee", length = 50)
    private String bCodigoHee;
    @Size(max = 18)
    @Column(name = "b_ip_adress", length = 18)
    private String bIpAdress;
    @Size(max = 50)
    @Column(name = "b_modelo", length = 50)
    private String bModelo;
    @Size(max = 150)
    @Column(name = "b_serie", length = 150)
    private String bSerie;
    @Size(max = 50)
    @Column(name = "b_tipo", length = 50)
    private String bTipo;
    @Size(max = 50)
    @Column(name = "b_capacidad", length = 50)
    private String bCapacidad;
    @Size(max = 250)
    @Column(name = "b_descripcion", length = 250)
    private String bDescripcion;
    @Column(name = "b_estado")
    private Boolean bEstado;
    @Column(name = "b_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bRegistro;
    @JoinColumn(name = "m_serial", referencedColumnName = "m_serial")
    @ManyToOne
    private TMarca mSerial;
    @OneToMany(mappedBy = "padreBserial")
    private List<TBien> tBienList;
    @JoinColumn(name = "padre_bserial", referencedColumnName = "b_serial")
    @ManyToOne
    private TBien padreBserial;
    @JoinColumn(name = "s_serial", referencedColumnName = "s_serial")
    @ManyToOne
    private TServicio sSerial;
    @JoinColumn(name = "so_serial", referencedColumnName = "so_serial")
    @ManyToOne
    private TSistemaOperativo soSerial;
    @JoinColumn(name = "r_serial", referencedColumnName = "r_serial")
    @ManyToOne
    private TResponsable rSerial;
    @JoinColumn(name = "tb_serial", referencedColumnName = "tb_serial")
    @ManyToOne
    private TTipoBien tbSerial;
    @OneToMany(mappedBy = "bSerial")
    private List<TMantenimiento> tMantenimientoList;

    public TBien() {
    }

    public TBien(Integer bSerial) {
        this.bSerial = bSerial;
    }

    public Integer getBSerial() {
        return bSerial;
    }

    public void setBSerial(Integer bSerial) {
        this.bSerial = bSerial;
    }

    public String getBCodigoHee() {
        return bCodigoHee;
    }

    public void setBCodigoHee(String bCodigoHee) {
        this.bCodigoHee = bCodigoHee.toUpperCase();
    }

    public String getBIpAdress() {
        return bIpAdress;
    }

    public void setBIpAdress(String bIpAdress) {
        this.bIpAdress = bIpAdress;
    }

    public String getBModelo() {
        return bModelo;
    }

    public void setBModelo(String bModelo) {
        this.bModelo = bModelo.toUpperCase();
    }

    public String getBSerie() {
        return bSerie;
    }

    public void setBSerie(String bSerie) {
        this.bSerie = bSerie.toUpperCase();
    }

    public String getBTipo() {
        return bTipo;
    }

    public void setBTipo(String bTipo) {
        this.bTipo = bTipo.toUpperCase();
    }

    public String getBCapacidad() {
        return bCapacidad;
    }

    public void setBCapacidad(String bCapacidad) {
        this.bCapacidad = bCapacidad.toUpperCase();
    }

    public String getBDescripcion() {
        return bDescripcion;
    }

    public void setBDescripcion(String bDescripcion) {
        this.bDescripcion = bDescripcion.toUpperCase();
    }

    public Boolean getBEstado() {
        return bEstado;
    }

    public void setBEstado(Boolean bEstado) {
        this.bEstado = bEstado;
    }

    public TMarca getMSerial() {
        return mSerial;
    }

    public void setMSerial(TMarca mSerial) {
        this.mSerial = mSerial;
    }

    @XmlTransient
    public List<TBien> getTBienList() {
        return tBienList;
    }

    public void setTBienList(List<TBien> tBienList) {
        this.tBienList = tBienList;
    }

    public TBien getPadreBserial() {
        return padreBserial;
    }

    public void setPadreBserial(TBien padreBserial) {
        this.padreBserial = padreBserial;
    }

    public TServicio getSSerial() {
        return sSerial;
    }

    public void setSSerial(TServicio sSerial) {
        this.sSerial = sSerial;
    }

    public TSistemaOperativo getSoSerial() {
        return soSerial;
    }

    public void setSoSerial(TSistemaOperativo soSerial) {
        this.soSerial = soSerial;
    }

    public TResponsable getRSerial() {
        return rSerial;
    }

    public void setRSerial(TResponsable rSerial) {
        this.rSerial = rSerial;
    }

    public TTipoBien getTbSerial() {
        return tbSerial;
    }

    public void setTbSerial(TTipoBien tbSerial) {
        this.tbSerial = tbSerial;
    }

    @XmlTransient
    public List<TMantenimiento> getTMantenimientoList() {
        return tMantenimientoList;
    }

    public void setTMantenimientoList(List<TMantenimiento> tMantenimientoList) {
        this.tMantenimientoList = tMantenimientoList;
    }

    public Date getbRegistro() {
        return bRegistro;
    }

    public void setbRegistro(Date bRegistro) {
        this.bRegistro = bRegistro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bSerial != null ? bSerial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TBien)) {
            return false;
        }
        TBien other = (TBien) object;
        if ((this.bSerial == null && other.bSerial != null) || (this.bSerial != null && !this.bSerial.equals(other.bSerial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Serial=" + bSerial + ", Código HEE=" + bCodigoHee;
    }

}
