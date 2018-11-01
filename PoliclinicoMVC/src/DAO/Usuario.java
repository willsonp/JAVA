/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Blob;
import java.sql.Date;

/**
 *
 * @author rwp0002
 */
public class Usuario {
    private int idUsuario;
    private String cedula;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String username;
    private String userpassw;
    private String nivel;
    private String sexo;
    private String correo;
    private Blob logo;
    private String rutaimagen;
    private String status;
    private String user_modi;
    private Date fecha_modi;

    public Usuario() {
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassw() {
        return userpassw;
    }

    public void setUserpassw(String userpassw) {
        this.userpassw = userpassw;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Blob getLogo() {
        return logo;
    }

    public void setLogo(Blob logo) {
        this.logo = logo;
    }

    public String getRutaimagen() {
        return rutaimagen;
    }

    public void setRutaimagen(String rutaimagen) {
        this.rutaimagen = rutaimagen;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser_modi() {
        return user_modi;
    }

    public void setUser_modi(String user_modi) {
        this.user_modi = user_modi;
    }

    public Date getFecha_modi() {
        return fecha_modi;
    }

    public void setFecha_modi(Date fecha_modi) {
        this.fecha_modi = fecha_modi;
    }

    public Usuario(int idUsuario, String cedula, String nombre, String apellidos, String direccion, String username, String userpassw, String nivel, String sexo, String correo, Blob logo, String rutaimagen, String status, String user_modi, Date fecha_modi) {
        this.idUsuario = idUsuario;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.username = username;
        this.userpassw = userpassw;
        this.nivel = nivel;
        this.sexo = sexo;
        this.correo = correo;
        this.logo = logo;
        this.rutaimagen = rutaimagen;
        this.status = status;
        this.user_modi = user_modi;
        this.fecha_modi = fecha_modi;
    }
    
       
}
