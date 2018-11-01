/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Date;

/**
 *
 * @author rwp0002
 */
public class Paciente {
    private int numero ;
    private String nombre ;
    private String apellido ;
    private Date fechaN ;
    private String cedula ;
    private String direccion;
    private int edad ;
    private String secuencia;
    private String status;
    private String user_modi;
    private Date fecha_mod;

    public Paciente() {
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaN() {
        return fechaN;
    }

    public void setFechaN(Date fechaN) {
        this.fechaN = fechaN;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
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

    public Date getFecha_mod() {
        return fecha_mod;
    }

    public void setFecha_mod(Date fecha_mod) {
        this.fecha_mod = fecha_mod;
    }

    public Paciente(int numero, String nombre, String apellido, Date fechaN, String cedula, String direccion, int edad, String secuencia, String status, String user_modi, Date fecha_mod) {
        this.numero = numero;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaN = fechaN;
        this.cedula = cedula;
        this.direccion = direccion;
        this.edad = edad;
        this.secuencia = secuencia;
        this.status = status;
        this.user_modi = user_modi;
        this.fecha_mod = fecha_mod;
    }        
}
