/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControladorUsuario;

import Entidades.Empleado;
import Entidades.SesionBean.EmpleadoFacade;
import Entidades.SesionBean.UsuariosFacade;
import Entidades.Usuarios;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.primefaces.PrimeFaces;

/**
 *
 * @author PBFCIS-SRC-01
 */
@ManagedBean (name = "ub")
@ViewScoped
public class ControlerUsuario implements Serializable{
    
    private List<Usuarios> usuarios;
    
    private Usuarios selectedUsuario;
    
    private List<Empleado> empleados;
    private List<Empleado> empleados2;
    
    private Empleado selectedEmpleado;
    
    @Inject 
    private UsuariosFacade usuariosFacade;
    
    @Inject
    private EmpleadoFacade empleadoFacade;

    public ControlerUsuario() {
    }
    
    @PostConstruct
    public void init(){
        this.selectedEmpleado = new Empleado();
        this.selectedUsuario = new Usuarios();
        listaprueba();
        listarUsuarios();
    }
    
    public List<Empleado> listarEmpleados(){
        this.empleados = this.empleadoFacade.findAll();
        return this.empleados;
    }
    
    public List<Empleado> listaprueba() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoHDP");
        EntityManager em = emf.createEntityManager();
        String sql = "SELECT *\n"
                + "FROM empleado e WHERE NOT EXISTS\n"
                + "(SELECT u.idempleado FROM usuarios u\n"
                + "WHERE e.id_duiempleado = u.idempleado OR e.estado = FALSE)";
        Query q = em.createNativeQuery(sql,Empleado.class);
        this.empleados2 = q.getResultList();
        return this.empleados2;
    }
    
    public List<Empleado> listarEmpleadosActivos(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoHDP");

        EntityManager em = emf.createEntityManager();

        String sql = "SELECT e.id_duiempleado, CONCAT(e.nombreempleado,\" \", e.apellidoempleado) AS nombre, e.estado\n" +
"                FROM empleado e\n" +
"                WHERE NOT EXISTS\n" +
"                (SELECT * FROM usuarios u\n" +
"                WHERE e.id_duiempleado = u.idempleado OR e.estado = FALSE)";
        
        Query q = em.createNativeQuery(sql, Empleado.class  );
        this.empleados2 = q.getResultList();
        
        return this.empleados2; 
    }
    
    public List<Usuarios> listarUsuarios(){
        this.usuarios = this.usuariosFacade.findAll();
        return this.usuarios;
    }
    
    public void openNew(){
        this.selectedUsuario = new Usuarios();
    }
    
    public void saveUsuario(){
        if (this.selectedUsuario.getIdusuario() == null) {
            this.selectedUsuario.setIdempleado(this.selectedEmpleado);
            this.selectedUsuario.setIdusuario(id());
            this.usuariosFacade.create(this.selectedUsuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario Agregado"));
        }else{
           //this.selectedUsuario.setIdempleado(this.selectedEmpleado);
            this.usuariosFacade.edit(this.selectedUsuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario Actualizado"));
        }
        PrimeFaces.current().executeScript("PF('manageUsuarioDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-usuarios");
    }
    
    public void createUsuario(){
        this.selectedUsuario.setIdempleado(selectedEmpleado);
        this.usuariosFacade.create(selectedUsuario);
    }
    
    public void deleteUsuario(){
        if (this.selectedUsuario != null && this.usuarios.size() > 1) {
            this.usuariosFacade.remove(this.selectedUsuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario eliminado"));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-usuarios");
        }
        else if ("Gerente".equals(this.selectedUsuario.getUsuario())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Imposible eliminar el usuario"));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-usuarios");
        }
    }
    
    public static int id(){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoHDP");

        EntityManager em = emf.createEntityManager();

        String sql = "SELECT  MAX(idusuario)+1 FROM usuarios";

        Query q = em.createNativeQuery(sql);
        Object u = q.getSingleResult();
        String id = String.valueOf(u);
      
        return Integer.valueOf(id);
    }

    public List<Usuarios> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuarios getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(Usuarios selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public Empleado getSelectedEmpleado() {
        return selectedEmpleado;
    }

    public void setSelectedEmpleado(Empleado selectedEmpleado) {
        this.selectedEmpleado = selectedEmpleado;
    }

    public List<Empleado> getEmpleados2() {
        return empleados2;
    }

    public void setEmpleados2(List<Empleado> empleados2) {
        this.empleados2 = empleados2;
    }
    
    
    
}
