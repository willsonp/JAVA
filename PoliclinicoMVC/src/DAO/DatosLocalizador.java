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
public class DatosLocalizador {
    
    private int idDeta_localizador;
    private int IdEntidad; 
    private String Tipo_Entidad;
    private String Tipo;
    private String Numero; 
    private Date fecha_modi;    
    private String user_modi;
    private String status;

    public DatosLocalizador() {
    }

    public String getTipo_Entidad() {
        return Tipo_Entidad;
    }

    public void setTipo_Entidad(String Tipo_Entidad) {
        this.Tipo_Entidad = Tipo_Entidad;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String Numero) {
        this.Numero = Numero;
    }

    public int getIdDeta_localizador() {
        return idDeta_localizador;
    }

    public void setIdDeta_localizador(int idDeta_localizador) {
        this.idDeta_localizador = idDeta_localizador;
    }

    public int getIdEntidad() {
        return IdEntidad;
    }

    public void setIdEntidad(int IdEntidad) {
        this.IdEntidad = IdEntidad;
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

    public DatosLocalizador(int idDeta_localizador, int IdEntidad, String Tipo_Entidad, String Tipo, String Numero, Date fecha_modi, String user_modi, String status) {
        this.idDeta_localizador = idDeta_localizador;
        this.IdEntidad = IdEntidad;
        this.Tipo_Entidad = Tipo_Entidad;
        this.Tipo = Tipo;
        this.Numero = Numero;
        this.fecha_modi = fecha_modi;
        this.user_modi = user_modi;
        this.status = status;
    }
    
    
}
