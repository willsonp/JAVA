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
public class Medico {
   private int numero;
   private String nombre;
   private int especialidad;
   private int piso;
   private int extension;
   private String status;
   private String user_modi;
   private Date fecha_mod;

    public Medico() {
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

    public int getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(int especialidad) {
        this.especialidad = especialidad;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public int getExtension() {
        return extension;
    }

    public void setExtension(int extension) {
        this.extension = extension;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
   
    public Medico(int numero, String nombre, int especialidad, int piso, int extension,String status, String user_modi, Date fecha_mod) {
        this.numero = numero;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.piso = piso;
        this.extension = extension;
        this.status = status;
        this.user_modi = user_modi;
        this.fecha_mod = fecha_mod;
        
    }
        
}
