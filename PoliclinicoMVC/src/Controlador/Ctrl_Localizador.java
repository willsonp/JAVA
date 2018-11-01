/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
import DAO.*;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.JDialog;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
/**
 *
 * @author rwp0002
 */
public class Ctrl_Localizador extends DatosLocalizador {
    private Connection CNT = null;
    private String SQL = null;
    private ResultSet rs;
    private PreparedStatement Data;
    private InetAddress hostname = null; //para coger el nombre del equipo   
  
    public Ctrl_Localizador() {
    }
    
    public boolean ifExists(int p1, String p2, String tipo, String numero) throws SQLException {
        try {
            CNT = ConexionDB.ConectarMySQL();
            String sql = "Select * from deta_localizador "
                    + "where status='A' and  identidad=" + p1 + ""
                    + " and tipo_entidad='" + p2 + "'  and tipo='" + tipo + "' "
                    + "and numero='" + numero + "'";
            Data = CNT.prepareStatement(sql);
            rs = Data.executeQuery(sql);

        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, e +"\n No es posible Verificar la informaciones", "Error al ejecutar comando"+ e.getMessage(), JOptionPane.ERROR_MESSAGE);
            /*
            Montamos el JOptionPane en un JDialog
            */
            String msg= e +"\n No es posible Verificar la informaciones "+"\n Metodo ifExists";
            JOptionPane opcion = new JOptionPane();
            opcion.setMessage(msg);
            opcion.setMessageType(JOptionPane.ERROR_MESSAGE);
            JDialog newDlg = opcion.createDialog(null, "Error al ejecutar comando.");                     
            newDlg.setLocationRelativeTo(null);
            newDlg.setAlwaysOnTop(true);
            newDlg.setModal(true);
            newDlg.setVisible(true);
            newDlg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
        return rs.next();
    }
    
    public ArrayList<DatosLocalizador> Listar(int p1, String p2) {

        ArrayList<DatosLocalizador> listarlocalizador = new ArrayList<>();
        try {
            CNT = ConexionDB.ConectarMySQL();
            String sql = "Select * from deta_localizador where "
                    + "status='A' and  identidad=" + p1 + "  "
                    + "and tipo_entidad='" + p2 + "'";
            Data = CNT.prepareStatement(sql);
            rs = Data.executeQuery(sql);

            DatosLocalizador localizador;          

            while (rs.next()) {
                localizador = new DatosLocalizador(rs.getInt("idDeta_localizador"),
                        rs.getInt("identidad"),
                        rs.getString("tipo_entidad"),                       
                        rs.getString("tipo"),
                        rs.getString("numero"),
                        rs.getDate("fecha_modi"),
                        rs.getString("user_modi"),
                        rs.getString("status")
                );

                listarlocalizador.add(localizador);
            }
            
            
            CNT.close();
            rs.close();

        } catch (Exception e) {
           // JOptionPane.showMessageDialog(null, e +"\n No es posible Listar la informaciones", "Error al ejecutar comando"+ e.getMessage(), JOptionPane.ERROR_MESSAGE);
           /*
            Montamos el JOptionPane en un JDialog
            */
            String msg= e +"\n No es posible Verificar la informaciones "+"\n Metodo Listar clase Ctrl_Localizador";
            JOptionPane opcion = new JOptionPane();
            opcion.setMessage(msg);
            opcion.setMessageType(JOptionPane.ERROR_MESSAGE);
            JDialog newDlg = opcion.createDialog(null, "Error al ejecutar comando.");                     
            newDlg.setLocationRelativeTo(null);
            newDlg.setAlwaysOnTop(true);
            newDlg.setModal(true);
            newDlg.setVisible(true);
            newDlg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        }
        return listarlocalizador;
    }

    /*   uso del metodo get   */

    public void Guardar() throws SQLException {
        try {
            Date f = new Date(); //Para Poner la Fecha           
            
            try {
                hostname = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                //JOptionPane.showMessageDialog(null, e +"\n No es posible obtener la PC", "Error al ejecutar comando"+ e.getMessage(), JOptionPane.ERROR_MESSAGE);
                /*
            Montamos el JOptionPane en un JDialog
            */
            String msg= e +"\n No es posible Verificar la informaciones "+"\n Metodo Guardar verificar hostname";
            JOptionPane opcion = new JOptionPane();
            opcion.setMessage(msg);
            opcion.setMessageType(JOptionPane.ERROR_MESSAGE);
            JDialog newDlg = opcion.createDialog(null, "Error al ejecutar comando.");                     
            newDlg.setLocationRelativeTo(null);
            newDlg.setAlwaysOnTop(true);
            newDlg.setModal(true);
            newDlg.setVisible(true);
            newDlg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }

            SQL = "insert into deta_localizador (tipo_entidad,identidad,tipo,numero,fecha_modi,user_modi,Status) values(?,?,?,?,?,?,?)";
            CNT = ConexionDB.ConectarMySQL();

            Data = CNT.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
            Data.setString(1, this.getTipo_Entidad());
            Data.setInt(2, this.getIdEntidad());
            Data.setString(3, this.getTipo());
            Data.setString(4, this.getNumero());
            Data.setDate(5, new java.sql.Date(f.getTime()));
            Data.setString(6, hostname.toString());
            Data.setString(7, "A");
            Data.execute();

            Data.close();
            CNT.close();
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, e +"\n No es posible Guardar las informaciones", "Error al ejecutar comando"+ e.getMessage(), JOptionPane.ERROR_MESSAGE);
              /*
            Montamos el JOptionPane en un JDialog
            */
            String msg= e +"\n No es posible Verificar la informaciones "+"\n Metodo Guardar";
            JOptionPane opcion = new JOptionPane();
            opcion.setMessage(msg);
            opcion.setMessageType(JOptionPane.ERROR_MESSAGE);
            JDialog newDlg = opcion.createDialog(null, "Error al ejecutar comando.");                     
            newDlg.setLocationRelativeTo(null);
            newDlg.setAlwaysOnTop(true);
            newDlg.setModal(true);
            newDlg.setVisible(true);
            newDlg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
    }

    public void Editar(int p1, String tipoe, int p2) throws SQLException {
        try {
            Date f = new Date(); //Para Poner la Fecha
                       
            try {             
                hostname = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                //JOptionPane.showMessageDialog(null, e +"\n No es posible Editar la PC", "Error al ejecutar comando"+ e.getMessage(), JOptionPane.ERROR_MESSAGE);
                /*
            Montamos el JOptionPane en un JDialog
            */
            String msg= e +"\n No es posible Verificar la informaciones "+"\n Metodo Editar hostname";
            JOptionPane opcion = new JOptionPane();
            opcion.setMessage(msg);
            opcion.setMessageType(JOptionPane.ERROR_MESSAGE);
            JDialog newDlg = opcion.createDialog(null, "Error al ejecutar comando.");                     
            newDlg.setLocationRelativeTo(null);
            newDlg.setAlwaysOnTop(true);
            newDlg.setModal(true);
            newDlg.setVisible(true);
            newDlg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }

            SQL = "Update deta_localizador set tipo=?, numero=?, fecha_modi=?,user_modi=?,Status=? where idDeta_localizador=" + p1 + " and tipo_entidad='" + tipoe + "'  and identidad=" + p2 + "";
            CNT = ConexionDB.ConectarMySQL();

            Data = CNT.prepareStatement(SQL);
            Data.setString(1, this.getTipo());
            Data.setString(2, this.getNumero());

            Data.setDate(3, new java.sql.Date(f.getTime()));
            Data.setString(4, hostname.toString());
            Data.setString(5, "A");

            Data.execute();
            Data.close();
            CNT.close();
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, e +"\n No es posible Ecitar las informaciones", "Error al ejecutar comando"+ e.getMessage(), JOptionPane.ERROR_MESSAGE);
                /*
            Montamos el JOptionPane en un JDialog
            */
            String msg= e +"\n No es posible Verificar la informaciones "+"\n Metodo Editar";
            JOptionPane opcion = new JOptionPane();
            opcion.setMessage(msg);
            opcion.setMessageType(JOptionPane.ERROR_MESSAGE);
            JDialog newDlg = opcion.createDialog(null, "Error al ejecutar comando.");                     
            newDlg.setLocationRelativeTo(null);
            newDlg.setAlwaysOnTop(true);
            newDlg.setModal(true);
            newDlg.setVisible(true);
            newDlg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
    }

    public void Eliminar(int p1) throws SQLException {
        try {
            Date f = new Date(); //Para Poner la Fecha

            try {
                hostname = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                //JOptionPane.showMessageDialog(null, e +"\n No es posible actualizar la PC al Eliminar", "Error al ejecutar comando"+ e.getMessage(), JOptionPane.ERROR_MESSAGE);
                  /*
            Montamos el JOptionPane en un JDialog
            */
            String msg= e +"\n No es posible Verificar la informaciones "+"\n Metodo Eliminar hostname";
            JOptionPane opcion = new JOptionPane();
            opcion.setMessage(msg);
            opcion.setMessageType(JOptionPane.ERROR_MESSAGE);
            JDialog newDlg = opcion.createDialog(null, "Error al ejecutar comando.");                     
            newDlg.setLocationRelativeTo(null);
            newDlg.setAlwaysOnTop(true);
            newDlg.setModal(true);
            newDlg.setVisible(true);
            newDlg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }

            SQL = "Update deta_localizador set fecha_modi=?,user_modi=?,Status=? where idDeta_localizador=" + p1;
            CNT = ConexionDB.ConectarMySQL();
            Data = CNT.prepareStatement(SQL);
            Data.setDate(1, new java.sql.Date(f.getTime()));
            Data.setString(2, hostname.toString());
            Data.setString(3, "I");
            Data.execute();
            Data.close();
            CNT.close();
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, e +"\n No es posible Eliminar las informaciones", "Error al ejecutar comando"+ e.getMessage(), JOptionPane.ERROR_MESSAGE);
                /*
            Montamos el JOptionPane en un JDialog
            */
            String msg= e +"\n No es posible Verificar la informaciones "+"\n Metodo Eliminar";
            JOptionPane opcion = new JOptionPane();
            opcion.setMessage(msg);
            opcion.setMessageType(JOptionPane.ERROR_MESSAGE);
            JDialog newDlg = opcion.createDialog(null, "Error al ejecutar comando.");                     
            newDlg.setLocationRelativeTo(null);
            newDlg.setAlwaysOnTop(true);
            newDlg.setModal(true);
            newDlg.setVisible(true);
            newDlg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
    }
    
}
