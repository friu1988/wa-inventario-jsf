package com.capa.presentacion;

import com.capa.datos.TBien;
import com.capa.negocios.TBienFacade;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@Named
@SessionScoped
public class TreeTableManagedBean implements Serializable {

    @EJB
    private TBienFacade servicio;
    private TBien equipo;
    private TBien detalle;
    private List<TBien> equipos = null;
    private List<TBien> detalles = null;

    private TreeNode root = null;

    public TreeTableManagedBean() {
    }

    public void eliminarDet() {

        try {
            servicio.remove(detalle);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dispositivo ELIMINADO! ", null));
            detalle = new TBien();
            root = null;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Eliminar primero los Detalles! " + e.getMessage(), null));
        }

    }

    public void cargarRaiz() {
        for (TBien it : equipos) {
            if (it.getPadreBserial() == null) {
                TreeNode raiz = new DefaultTreeNode(it, this.root);
                raiz.setExpanded(true);
//                System.out.println("Equipo: " + it.getBSerial());
                cargarNodo(it, raiz);
            }
        }
    }

    public void cargarNodo(TBien it, TreeNode raiz) {
        try {
            detalles = servicio.buscarDet(it);
            for (TBien it1 : detalles) {
//                System.out.println("Detalle: " + it1.getPadreBserial());
                TreeNode nodo = new DefaultTreeNode(it1, raiz);
            }
        } catch (Exception e) {
            System.out.println("Error al cargar nodos" + e.getMessage());
        }
    }

    public TreeNode getRoot() {
        if (root == null) {
            root = new DefaultTreeNode();
            root.setExpanded(true);
            setEquipos(servicio.findAll());
            cargarRaiz();
        }
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public TBien getEquipo() {
        if (equipo == null) {
            equipo = new TBien();
        }
        return equipo;
    }

    public void setEquipo(TBien equipo) {
        this.equipo = equipo;
    }

    public TBien getDetalle() {
        if (detalle == null) {
            detalle = new TBien();

        }
        return detalle;
    }

    public void setDetalle(TBien detalle) {
        this.detalle = detalle;
    }

    public List<TBien> getEquipos() {
//        if (equipos == null) {
//            equipos = new ArrayList<>();
//        }
        return equipos;
    }

    public void setEquipos(List<TBien> equipos) {
        this.equipos = equipos;
    }

    public List<TBien> getDetalles() {
        if (detalles == null) {
            detalles = new ArrayList<>();
        }
        return detalles;
    }

    public void setDetalles(List<TBien> detalles) {
        this.detalles = detalles;
    }

}
