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
public class TipoEstudio {
    private String codigo;
    private String descripcion;
    private String status;
    private String user_modi;
    private Date fecha_modi;

    public TipoEstudio() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public TipoEstudio(String codigo, String descripcion, String status, String user_modi, Date fecha_modi) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.status = status;
        this.user_modi = user_modi;
        this.fecha_modi = fecha_modi;
    }
    
}
