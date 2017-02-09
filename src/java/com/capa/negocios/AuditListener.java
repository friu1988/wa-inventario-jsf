package com.capa.negocios;

import com.capa.datos.THistorial;
import com.capa.datos.TMarca;
import com.capa.datos.TUsuario;
import java.sql.Timestamp;
import javax.faces.context.FacesContext;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

public class AuditListener {

    @PrePersist
    public void onPrePersist(Object object) {
        auditGuardarAtributos(object, "INSERT");
    }

    @PreUpdate
    public void onPreUpdate(Object object) {
        auditGuardarAtributos(object, "UPDATE");
    }

    @PreRemove
    public void onPreRemove(Object object) {
        auditGuardarAtributos(object, "DELETE");
    }

    public void auditGuardarAtributos(Object object, String transaccion) {
        String op = object.getClass().getSimpleName();

        THistorial hist;
        java.util.Date d = new java.util.Date();
        Timestamp timestamp = new Timestamp(d.getTime());
        TUsuario userLogin = (TUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLogin");

        switch (op) {
            case "TUsuario":
                TUsuario user = (TUsuario) object;
                String campoTU = "Usuario: " + user.getUNombre() + " " + user.getUClave() + " " + user.getUTipo();
                hist = new THistorial(userLogin.getUNombre(), timestamp, transaccion, op, campoTU, "", "");
                guardarHistorial(hist);
                break;
            case "TMarca":
                TMarca marca = (TMarca) object;
                String campoTM = "Marca: " + marca.getMNombre();
                hist = new THistorial(userLogin.getUNombre(), timestamp, transaccion, campoTM, "", "", "");
                guardarHistorial(hist);
                break;
            default:
                System.out.println("Error en Auditoria!");
                break;

        }

    }

    public void guardarHistorial(THistorial registro) {
        String sql = "INSERT INTO t_historial (h_usuario, h_fecha, h_transaccion, h_tabla, h_atributo, h_valor_original, h_valor_nuevo) VALUES ('"
                + registro.getHUsuario() + "','" + registro.getHFecha() + "','"
                + registro.getHTransaccion() + "','" + registro.getHTabla() + "','"
                + registro.getHAtributo() + "','" + registro.getHValorOriginal() + "','"
                + registro.getHValorNuevo() + "')";
        try {
            Conexion.conexionDBMySql.insertar(sql);
        } catch (Exception e) {
            System.out.println("Error al INSERTAR: " + e.getMessage());
        }
    }

}
