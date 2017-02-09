package com.capa.presentacion;

import com.capa.datos.TUsuario;
import com.capa.negocios.TUsuarioFacade;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "mBUsuario")
@SessionScoped
public class MBUsuario implements Serializable {

    @EJB
    private TUsuarioFacade servicio;
    private TUsuario user;

    public MBUsuario() {
    }

    public String crearUser() {

        try {
            servicio.create(user);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario ingresado: " + user.getUNombre(), null));
            return "template-hee";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "YA EXISTE este Usuario!" + e.getMessage(), null));
            return "login";
        }

    }

    public void loguearUser() throws IOException {
        TUsuario usDB = servicio.buscarUser(user);
        if (usDB.equals(user)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Existe este User: " + user.getUNombre(), null));
            if (usDB.getUEstado()) {
                String op = usDB.getUTipo();
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userLogin", usDB);
                switch (op) {
                    case "A":
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cuenta Administrador", null));
                        FacesContext.getCurrentInstance().getExternalContext().redirect("./../faces/usuarios-tic/bienvenida.xhtml");
                        break;
                    case "U":
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cuenta Usuarios", null));
                        FacesContext.getCurrentInstance().getExternalContext().redirect("./../faces/usuarios-tic/bienvenida.xhtml");
                        break;
                    case "C":
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cuenta Consultas!", null));
                        FacesContext.getCurrentInstance().getExternalContext().redirect("./../faces/usuarios-tic/bienvenida.xhtml");
                        break;
                    default:
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en el APP", null));
                        break;
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cuenta Desactivada!", null));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario o Clave INCORRECTA", null));
        }
    }

    public TUsuario getUser() {
        if (user == null) {
            user = new TUsuario();
        }
        return user;
    }

    public void setUser(TUsuario user) {
        this.user = user;
    }

}
