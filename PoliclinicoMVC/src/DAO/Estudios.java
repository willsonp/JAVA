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
public class Estudios {
    private int numero;
    private String tipo;
    private int numero_p;
    private int maestroe;
    private int medico;
    private String estudio;
    private Date fecha;
    private String status;
    private String user_modi;
    private Date fecha_modi;

    public Estudios() {
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNumero_p() {
        return numero_p;
    }

    public void setNumero_p(int numero_p) {
        this.numero_p = numero_p;
    }

    public int getMaestroe() {
        return maestroe;
    }

    public void setMaestroe(int maestroe) {
        this.maestroe = maestroe;
    }

    public int getMedico() {
        return medico;
    }

    public void setMedico(int medico) {
        this.medico = medico;
    }

    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public Estudios(int numero, String tipo, int numero_p, int maestroe, int medico, String estudio, Date fecha, String status, String user_modi, Date fecha_modi) {
        this.numero = numero;
        this.tipo = tipo;
        this.numero_p = numero_p;
        this.maestroe = maestroe;
        this.medico = medico;
        this.estudio = estudio;
        this.fecha = fecha;
        this.status = status;
        this.user_modi = user_modi;
        this.fecha_modi = fecha_modi;
    }
    
    
}
