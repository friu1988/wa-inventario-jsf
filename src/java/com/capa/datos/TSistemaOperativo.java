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
@Table(name = "t_sistema_operativo", catalog = "db_inventario_hee", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TSistemaOperativo.findAll", query = "SELECT t FROM TSistemaOperativo t"),
    @NamedQuery(name = "TSistemaOperativo.findBySoSerial", query = "SELECT t FROM TSistemaOperativo t WHERE t.soSerial = :soSerial"),
    @NamedQuery(name = "TSistemaOperativo.findBySoNombre", query = "SELECT t FROM TSistemaOperativo t WHERE t.soNombre = :soNombre"),
    @NamedQuery(name = "TSistemaOperativo.findBySoVersion", query = "SELECT t FROM TSistemaOperativo t WHERE t.soVersion = :soVersion"),
    @NamedQuery(name = "TSistemaOperativo.findBySoEdicion", query = "SELECT t FROM TSistemaOperativo t WHERE t.soEdicion = :soEdicion"),
    @NamedQuery(name = "TSistemaOperativo.findBySoArquitectura", query = "SELECT t FROM TSistemaOperativo t WHERE t.soArquitectura = :soArquitectura"),
    @NamedQuery(name = "TSistemaOperativo.findBySoLicencia", query = "SELECT t FROM TSistemaOperativo t WHERE t.soLicencia = :soLicencia"),
    @NamedQuery(name = "TSistemaOperativo.findBySoCadena", query = "SELECT t FROM TSistemaOperativo t WHERE t.soCadena = :soCadena")})
public class TSistemaOperativo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "so_serial", nullable = false)
    private Integer soSerial;
    @Size(max = 200)
    @Column(name = "so_nombre", length = 200)
    private String soNombre;
    @Size(max = 50)
    @Column(name = "so_version", length = 10)
    private String soVersion;
    @Size(max = 50)
    @Column(name = "so_edicion", length = 10)
    private String soEdicion;
    @Size(max = 10)
    @Column(name = "so_arquitectura", length = 10)
    private String soArquitectura;
    @Column(name = "so_licencia")
    private Boolean soLicencia;
    @Size(max = 250)
    @Column(name = "so_cadena", length = 250)
    private String soCadena;
    @OneToMany(mappedBy = "soSerial")
    private List<TBien> tBienList;

    public TSistemaOperativo() {
    }

    public TSistemaOperativo(Integer soSerial) {
        this.soSerial = soSerial;
    }

    public Integer getSoSerial() {
        return soSerial;
    }

    public void setSoSerial(Integer soSerial) {
        this.soSerial = soSerial;
    }

    public String getSoNombre() {
        return soNombre;
    }

    public void setSoNombre(String soNombre) {
        this.soNombre = soNombre.toUpperCase();
    }

    public String getSoVersion() {
        return soVersion;
    }

    public void setSoVersion(String soVersion) {
        this.soVersion = soVersion.toUpperCase();
    }

    public String getSoEdicion() {
        return soEdicion;
    }

    public void setSoEdicion(String soEdicion) {
        this.soEdicion = soEdicion.toUpperCase();
    }

    public String getSoArquitectura() {
        return soArquitectura;
    }

    public void setSoArquitectura(String soArquitectura) {
        this.soArquitectura = soArquitectura.toLowerCase();
    }

    public Boolean getSoLicencia() {
        return soLicencia;
    }

    public void setSoLicencia(Boolean soLicencia) {
        this.soLicencia = soLicencia;
    }

    public String getSoCadena() {
        return soCadena;
    }

    public void setSoCadena(String soCadena) {
        this.soCadena = soCadena.toUpperCase();
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
        hash += (soSerial != null ? soSerial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TSistemaOperativo)) {
            return false;
        }
        TSistemaOperativo other = (TSistemaOperativo) object;
        if ((this.soSerial == null && other.soSerial != null) || (this.soSerial != null && !this.soSerial.equals(other.soSerial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return soCadena;
    }

}
