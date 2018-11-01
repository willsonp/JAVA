/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.*;
import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author rwp0002
 */
public class Ctrl_Usuario extends Usuario{
     private Connection CNT=null;
     private InetAddress hostname=null; //para coger el nombre del equipo
     //private JasperReport jr = null;
     private String SQL=null; 
     //private int IdUsuario;
     private ResultSet rs;
     private PreparedStatement Data;
     
    public Ctrl_Usuario() {
    }
    
    public ArrayList<Usuario> ListarUsuario(String caracteres){
        
        ArrayList<Usuario> listarusuarios =  new ArrayList<>();
       
        try{
            CNT=ConexionDB.ConectarMySQL();
            String sql="Select * from Usuario "
                    + "where Status='A' "
                    + "and  concat_ws('',idusuario,cedula,nombre,apellidos,direccion)"
                    + " like '%"+caracteres+"%'";
            Data=CNT.prepareStatement(sql);
            rs=Data.executeQuery(sql);
        
        Usuario usuario;
        
            while(rs.next()){
                 usuario = new Usuario(rs.getInt("IdUsuario"),
                                            rs.getString("cedula"),
                                            rs.getString("nombre"),
                                            rs.getString("apellidos"),
                                            rs.getString("direccion"),
                                            rs.getString("username"),                         
                                            rs.getString("userpassw"),
                                            rs.getString("nivel"),
                                            rs.getString("Sexo"),
                                            rs.getString("correo"),
                                            rs.getBlob("logo"),
                                            rs.getString("Rutaimagen"),
                                            rs.getString("status"),
                                            rs.getString("user_modi"),
                                            rs.getDate("fecha_modi")                                         
                                            );
                    
                 listarusuarios.add(usuario);
            }
         CNT.close();
         rs.close();
            
    }catch(Exception e){
        //JOptionPane.showMessageDialog(null, e +"\n No es posible Listar los Usuarios", "Error al ejecutar comando"+ e.getMessage(), JOptionPane.ERROR_MESSAGE);
        /*
            Montamos el JOptionPane en un JDialog
            */
            String msg= e +"\n No es posible Listar los Usuarios"+"\n Metodo ListarUsuario";
            JOptionPane opcion = new JOptionPane();
            opcion.setMessage(msg);
            opcion.setMessageType(JOptionPane.ERROR_MESSAGE);
            JDialog newDlg = opcion.createDialog(null, "Error al ejecutar comando..");                     
            newDlg.setLocationRelativeTo(null);
            newDlg.setAlwaysOnTop(true);
            newDlg.setModal(true);
            newDlg.setVisible(true);
            newDlg.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
    
    } 
      return listarusuarios;
    }
    
   /*
   Para mostrar Ultimo ID generado del los Usuarios 
   */ 
    
    public int LastId(){
        int id=0;
        SQL="Select MAX(idusuario) as ultimo from usuario";
        CNT=ConexionDB.ConectarMySQL();
         try {
             Data=CNT.prepareStatement(SQL);
             rs=Data.executeQuery();
             
             if (rs != null && rs.next()){
            id=rs.getInt(1);
            }
            rs.close();
            CNT.close();
         } catch (SQLException e) {
             //JOptionPane.showMessageDialog(null, e +"\n No es posible obtener Ultimo ID Usuario", "Error al ejecutar comando"+ e.getMessage(), JOptionPane.ERROR_MESSAGE);
             /*
            Montamos el JOptionPane en un JDialog
            */
            String msg= e +"\n No es posible obtener Ultimo ID Usuario"+"\n Metodo LASID";
            JOptionPane opcion = new JOptionPane();
            opcion.setMessage(msg);
            opcion.setMessageType(JOptionPane.ERROR_MESSAGE);
            JDialog newDlg = opcion.createDialog(null, "Error al ejecutar comando..");                     
            newDlg.setLocationRelativeTo(null);
            newDlg.setAlwaysOnTop(true);
            newDlg.setModal(true);
            newDlg.setVisible(true);
            newDlg.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
         }
      
        return id;
    }
    /*
    Para mostrar Ultimo ID generado del los Usuarios 
    */
    public int NextId(){
        int id=0;
        SQL="Select MAX(idusuario)+1 as ultimo from usuario";
        CNT=ConexionDB.ConectarMySQL();
         try {
             Data=CNT.prepareStatement(SQL);
             rs=Data.executeQuery();
             
             if (rs != null && rs.next()){
            id=rs.getInt(1);
            }
            rs.close();
            CNT.close();
         } catch (SQLException e) {
            // JOptionPane.showMessageDialog(null, e +"\n No es posible obtener proximo ID Usuario", "Error al ejecutar comando"+ e.getMessage(), JOptionPane.ERROR_MESSAGE);
            /*
            Montamos el JOptionPane en un JDialog
            */
            String msg= e +"\n No es posible obtener Ultimo ID Usuario"+"\n Metodo NextID";
            JOptionPane opcion = new JOptionPane();
            opcion.setMessage(msg);
            opcion.setMessageType(JOptionPane.ERROR_MESSAGE);
            JDialog newDlg = opcion.createDialog(null, "Error al ejecutar comando..");                     
            newDlg.setLocationRelativeTo(null);
            newDlg.setAlwaysOnTop(true);
            newDlg.setModal(true);
            newDlg.setVisible(true);
            newDlg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
         }
      
        return id;
    }
    /*
    Traer un Usuario especifico
    */
    public ArrayList<Usuario> Traerdatos(String p1){
        
        ArrayList<Usuario> llevar =  new ArrayList<>();
       
        try{
            CNT=ConexionDB.ConectarMySQL();
            String sql="Select * from Usuario where status='A' and  cedula='"+p1+"'";
            Data=CNT.prepareStatement(sql);
            rs=Data.executeQuery(sql);
        
        Usuario usuario;
        
            while(rs.next()){
                 usuario = new Usuario(rs.getInt("IdUsuario"),
                                            rs.getString("cedula"),
                                            rs.getString("nombre"),
                                            rs.getString("apellidos"),
                                            rs.getString("direccion"),
                                            rs.getString("username"),                         
                                            rs.getString("userpassw"),
                                            rs.getString("nivel"),
                                            rs.getString("Sexo"),
                                            rs.getString("correo"),
                                            rs.getBlob("logo"),
                                            rs.getString("Rutaimagen"),
                                            rs.getString("status"),
                                            rs.getString("user_modi"),
                                            rs.getDate("fecha_modi")                                         
                                            );
                    
                 llevar.add(usuario);
            }
         CNT.close();
         rs.close();
            
    }catch(Exception e){
      // JOptionPane.showMessageDialog(null, e +"\n No es posible obtener datos de Usuario", "Error al ejecutar comando"+ e.getMessage(), JOptionPane.ERROR_MESSAGE);
            /*
            Montamos el JOptionPane en un JDialog
            */
            String msg= e +"\n No es posible obtener Ultimo ID Usuario"+"\n Metodo TraerDatos";
            JOptionPane opcion = new JOptionPane();
            opcion.setMessage(msg);
            opcion.setMessageType(JOptionPane.ERROR_MESSAGE);
            JDialog newDlg = opcion.createDialog(null, "Error al ejecutar comando..");                     
            newDlg.setLocationRelativeTo(null);
            newDlg.setAlwaysOnTop(true);
            newDlg.setModal(true);
            newDlg.setVisible(true);
            newDlg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    
    } 
      return llevar;
      
    }
    
    
    
   /*
    Metodos de manipulacion de Datos  
   */
    public void Guardar() throws SQLException, IOException
    {   
        try{
            Date f=new Date(); //Para Poner la Fecha
        
                try {
                     hostname = InetAddress.getLocalHost(); 
                } catch (UnknownHostException e) {
                //JOptionPane.showMessageDialog(null, e +"\n No es posible asignar nombre PC en  Usuario", "Error al ejecutar comando"+ e.getMessage(), JOptionPane.ERROR_MESSAGE);
                 /*
                Montamos el JOptionPane en un JDialog
                */
                String msg= e +"\n No es posible asignar nombre PC en  Usuario"+"\n Metodo Guardar clase Ctrl_Usuario hostname";
                JOptionPane opcion = new JOptionPane();
                opcion.setMessage(msg);
                opcion.setMessageType(JOptionPane.ERROR_MESSAGE);
                JDialog newDlg = opcion.createDialog(null, "Error al ejecutar comando..");                     
                newDlg.setLocationRelativeTo(null);
                newDlg.setAlwaysOnTop(true);
                newDlg.setModal(true);
                newDlg.setVisible(true);
                newDlg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                }
         
        SQL="insert into usuario(cedula,nombre,apellidos,direccion,"
                + "username,userpassw,nivel,Sexo,correo,rutaimagen,"
                + "status,logo,user_modi,fecha_modi) "
                + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        CNT=ConexionDB.ConectarMySQL();
        /*
        instanciamos la Clase Usuario para los pase de Datos
        */
                
        Data=CNT.prepareStatement(SQL,PreparedStatement.RETURN_GENERATED_KEYS);
        Data.setString(1, this.getCedula());
        Data.setString(2, this.getNombre());
        Data.setString(3, this.getApellidos());
        Data.setString(4, this.getDireccion());
        Data.setString(5, this.getUsername());       
        Data.setString(6, this.getUserpassw());       
        Data.setString(7, this.getNivel());       
        Data.setString(8, this.getSexo());
        Data.setString(9, this.getCorreo());  
        Data.setString(10, this.getRutaimagen());
        Data.setString(11, "A");   
        
        if (!this.getRutaimagen().isEmpty()){
            try {
                FileInputStream fi = new FileInputStream(this.getRutaimagen());
                Data.setBinaryStream(12,fi);                
            } catch (FileNotFoundException e) {
                //JOptionPane.showMessageDialog(null, e +"\n No es posible Poner imagen de Usuario", "Error al ejecutar comando"+ e.getMessage(), JOptionPane.ERROR_MESSAGE);
             
                //Montamos el JOptionPane en un JDialog
               
                String msg= e +"\n No es posible asignar nombre PC en  Usuario" +"\n Metodo Guardar clase Ctrl_Usuario imagen";
                JOptionPane opcion = new JOptionPane();
                opcion.setMessage(msg);
                opcion.setMessageType(JOptionPane.ERROR_MESSAGE);
                JDialog newDlg = opcion.createDialog(null, "Error al ejecutar comando..");                     
                newDlg.setLocationRelativeTo(null);
                newDlg.setAlwaysOnTop(true);
                newDlg.setModal(true);
                newDlg.setVisible(true);
                newDlg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                }
            }
        
        Data.setString(13, hostname.toString());       
        Data.setDate(14, new java.sql.Date(f.getTime()));
        
        Data.execute(); 
        //Para visualizar el ID generado en el registro asignado
        rs=Data.getGeneratedKeys();
        
        if (rs != null && rs.next()){
            int id=rs.getInt(1);
           // System.out.println(id);
        }
        
        Data.close();
        CNT.close();
        }catch(SQLException e){
            //JOptionPane.showMessageDialog(null, e +"\n No es posible Guardar datos de Usuario", "Error al ejecutar comando"+ e.getMessage(), JOptionPane.ERROR_MESSAGE);
             /*
            Montamos el JOptionPane en un JDialog
            */
            String msg= e +"\n No es posible Insertar los Datos Correctamente"+"\n Metodo Guardar clase Ctrl_Usuario";
            JOptionPane opcion = new JOptionPane();
            opcion.setMessage(msg);
            opcion.setMessageType(JOptionPane.ERROR_MESSAGE);
            JDialog newDlg = opcion.createDialog(null, "Error al ejecutar comando..");                     
            newDlg.setLocationRelativeTo(null);
            newDlg.setAlwaysOnTop(true);
            newDlg.setModal(true);
            newDlg.setVisible(true);
            newDlg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }  finally{
            Data.close();
            CNT.close();            
        }
    }
    
    public void Editar(int parametro) throws SQLException, IOException
    {               
        try{
        Date f=new Date(); //Para Poner la Fecha
        
         try {
             hostname = InetAddress.getLocalHost(); 
         } catch (UnknownHostException e) {
             //JOptionPane.showMessageDialog(null, e +"\n No es posible Asignar Nombre de PC", "Error al ejecutar comando"+ e.getMessage(), JOptionPane.ERROR_MESSAGE);
              /*
            Montamos el JOptionPane en un JDialog
            */
            String msg= e +"\n No es posible asignar nombre PC en  Usuario"+"\n Metodo Editar clase Ctrl_Usuario hostname";
            JOptionPane opcion = new JOptionPane();
            opcion.setMessage(msg);
            opcion.setMessageType(JOptionPane.ERROR_MESSAGE);
            JDialog newDlg = opcion.createDialog(null, "Error al ejecutar comando..");                     
            newDlg.setLocationRelativeTo(null);
            newDlg.setAlwaysOnTop(true);
            newDlg.setModal(true);
            newDlg.setVisible(true);
            newDlg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
         }
            
        SQL="Update usuario set cedula=?,"
                + "nombre=?,apellidos=?,"
                + "direccion=?,username=?,"
                + "userpassw=?,nivel=?,Sexo=?,"
                + "correo=?,rutaimagen=?,status=?,logo=?,"
                + "user_modi=?,fecha_modi=?"
                + " where idusuario="+parametro+"";
        CNT=ConexionDB.ConectarMySQL();      
        
        Data=CNT.prepareStatement(SQL);
        Data.setString(1, this.getCedula());
        Data.setString(2, this.getNombre());
        Data.setString(3, this.getApellidos());
        Data.setString(4, this.getDireccion());
        Data.setString(5, this.getUsername());       
        Data.setString(6, this.getUserpassw());       
        Data.setString(7, this.getNivel());       
        Data.setString(8, this.getSexo());
        Data.setString(9, this.getCorreo());  
        Data.setString(10, this.getRutaimagen());
        Data.setString(11, "A");   
        
         if (!this.getRutaimagen().isEmpty()){
            try {
                FileInputStream fi = new FileInputStream(this.getRutaimagen());
                Data.setBinaryStream(12,fi);                
            } catch (FileNotFoundException e) {
                //JOptionPane.showMessageDialog(null, e +"\n No es posible Poner imagen de Usuario", "Error al ejecutar comando"+ e.getMessage(), JOptionPane.ERROR_MESSAGE);
              
           // Montamos el JOptionPane en un JDialog
           
            String msg= e +"\n No es posible Editar Usuario"+"\n Metodo Editar clase Ctrl_Usuario imagen";
            JOptionPane opcion = new JOptionPane();
            opcion.setMessage(msg);
            opcion.setMessageType(JOptionPane.ERROR_MESSAGE);
            JDialog newDlg = opcion.createDialog(null, "Error al ejecutar comando..");                     
            newDlg.setLocationRelativeTo(null);
            newDlg.setAlwaysOnTop(true);
            newDlg.setModal(true);
            newDlg.setVisible(true);
            newDlg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        }
         
        Data.setString(13, hostname.toString());       
        Data.setDate(14, new java.sql.Date(f.getTime()));
        
        Data.execute();
        
        Data.close();
        CNT.close(); 
        }catch(SQLException e){
           // JOptionPane.showMessageDialog(null, e +"\n No es posible editar los Datos  de Usuario", "Error al ejecutar comando"+ e.getMessage(), JOptionPane.ERROR_MESSAGE);
            /*
            Montamos el JOptionPane en un JDialog
            */
            String msg= e +"\n No es posible asignar nombre PC en  Usuario"+"\n Metodo editar clase Ctrl_Usuario";
            JOptionPane opcion = new JOptionPane();
            opcion.setMessage(msg);
            opcion.setMessageType(JOptionPane.ERROR_MESSAGE);
            JDialog newDlg = opcion.createDialog(null, "Error al ejecutar comando..");                     
            newDlg.setLocationRelativeTo(null);
            newDlg.setAlwaysOnTop(true);
            newDlg.setModal(true);
            newDlg.setVisible(true);
            newDlg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        } finally{
            Data.close();
            CNT.close();    
        }       
    }
    
     public void Elimnar(int parametro) throws SQLException
    {    
        try{
        Date f=new Date(); //Para Poner la Fecha
        
         try {
             hostname = InetAddress.getLocalHost(); 
         } catch (UnknownHostException e) {
            //JOptionPane.showMessageDialog(null, e +"\n No es posible asignar nombre de PC al eliminar Usuario", "Error al ejecutar comando"+ e.getMessage(), JOptionPane.ERROR_MESSAGE);
             /*
            Montamos el JOptionPane en un JDialog
            */
            String msg= e +"\n No es posible asignar nombre PC en  Usuario"+"\n Metodo Eliminar clase Ctrl_Usuario";
            JOptionPane opcion = new JOptionPane();
            opcion.setMessage(msg);
            opcion.setMessageType(JOptionPane.ERROR_MESSAGE);
            JDialog newDlg = opcion.createDialog(null, "Error al ejecutar comando..");                     
            newDlg.setLocationRelativeTo(null);
            newDlg.setAlwaysOnTop(true);
            newDlg.setModal(true);
            newDlg.setVisible(true);
            newDlg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
         }
            
        SQL="Update usuario set fecha_modi=?,user_modi=?,status=? where idusuario="+parametro+"";
        CNT=ConexionDB.ConectarMySQL();
              
        Data=CNT.prepareStatement(SQL);
        Data.setDate(1, new java.sql.Date(f.getTime()));
        Data.setString(2, hostname.toString());       
        Data.setString(3, "I");       
        
        Data.execute();
        Data.close();
        CNT.close(); 
        }catch (SQLException e){
             /*
            Montamos el JOptionPane en un JDialog
            */
            String msg= e +"\n No es posible asignar nombre PC en  Usuario";
            JOptionPane opcion = new JOptionPane();
            opcion.setMessage(msg);
            opcion.setMessageType(JOptionPane.ERROR_MESSAGE);
            JDialog newDlg = opcion.createDialog(null, "Error al ejecutar comando..");                     
            newDlg.setLocationRelativeTo(null);
            newDlg.setAlwaysOnTop(true);
            newDlg.setModal(true);
            newDlg.setVisible(true);
            newDlg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }finally{
            Data.close();
            CNT.close();           
        }
    
    }
    
        public void imprimir() throws SQLException,JRException {
        try{ 
        //instanciamos la conexion de la base de datos        
        CNT=ConexionDB.ConectarMySQL();
        //declaramos la variable path para asignarle la rura del archivo del reporte compilado
        String  path;
        //definimos un objeto de tipo JasperReport y lo inicializamos con null
         //path = "F:\\Java\\AccesoDatos\\AccesoDatos\\src\\Reportes\\rusuario.jasper";
         //path = System.getProperty("user.dir")+"\\src\\Reportes\\rusuario.jrxml";
         path = System.getProperty("user.dir")+"\\src\\Reportes\\rusuario.jasper";
        
        try {                  
            /*
             **al objeto jr declarado anteriormente hacemos un casting con JasperReport
             **y utilizamos el metodo load objet from file, pasando como paremtro
             **la ruta de que asigamos a la variable path
             */
            
            /*
            si deseo compilarlo en runtime pasando 
            */
            //JasperDesign jd = JRXmlLoader.load(path); 
            
            //JasperReport jr = JasperCompileManager.compileReport(jd);
            /*
            Hasta Aqui y pongo en comentario el de abajo
            */           
            JasperReport jr = (JasperReport) JRLoader.loadObjectFromFile(path);
            /*
            **Pase de Parametros
            */
            HashMap parametros = new HashMap();            
            try {
             hostname = InetAddress.getLocalHost(); 
            } catch (UnknownHostException e) {
               /*
            Montamos el JOptionPane en un JDialog
            */
            String msg= e +"\n No es posible asignar nombre PC en  Usuario";
            JOptionPane opcion = new JOptionPane();
            opcion.setMessage(msg);
            opcion.setMessageType(JOptionPane.ERROR_MESSAGE);
            JDialog newDlg = opcion.createDialog(null, "Error al ejecutar comando..");                     
            newDlg.setLocationRelativeTo(null);
            newDlg.setAlwaysOnTop(true);
            newDlg.setModal(true);
            newDlg.setVisible(true);
            newDlg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
            parametros.put("pc",hostname.toString());
            
            
            /*
            **declaramos el objeto jp de tipo JasperPrint y le asignamos
            **con el metodo fillmanager, pasamos el nombre del archivo
            **que lo tiene asignado la variable jr
            **el parametro o los parametros en este caso null por que no lleva
            **y por ultimo la conexionque esta en la variable CNT
            */
            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, CNT);
            
            /*
            **Declaramos un objeto de tipo visor para visualizar el reporte
            */
            JasperViewer jv = new JasperViewer(jp,false);           
            /*
            **le asignamos nombre del reporte qie se va a mostrar el captio de jpanel
            */
            jv.setTitle("Reporte de Usuarios"+path);
            /*
            **mostramos el reporte equivalente al metodo show de otros lenguajes
            */
            
                JDialog jf = new JDialog();
                jf.getContentPane().add(jv.getContentPane());
                jf.validate();
                jf.setAlwaysOnTop(true);                
                jf.setSize(new Dimension(900, 600));
                jf.setLocationRelativeTo(null);
                jf.setTitle("Reporte de Usuarios" + path);
                jf.setModal(true);
                jf.setVisible(true);
                jf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            
            /*
             jv.setAlwaysOnTop(true);
             jv.setVisible(true);
             jv.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            */
            
           
            /*
            **ceramos la conexion
            */
            CNT.close();       
            /*
            **Para evitar que se cierre la ventana de donde se esta llamando
            */
           
            
        } catch (JRException e) {             
           
            /*
            Montamos el JOptionPane en un JDialog
            */
            String msg= e +"\n No es posible Cargar el Archivo de Reporte en  Usuario";
            JOptionPane opcion = new JOptionPane();
            opcion.setMessage(msg);
            opcion.setMessageType(JOptionPane.ERROR_MESSAGE);
            JDialog newDlg = opcion.createDialog(null, "Error al ejecutar comando..");                     
            newDlg.setLocationRelativeTo(null);
            newDlg.setAlwaysOnTop(true);
            newDlg.setModal(true);
            newDlg.setVisible(true);
            newDlg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        } 
        }catch(SQLException e ){
             /*
            Montamos el JOptionPane en un JDialog
            */
            String msg= e +"\n No es posible asignar Datos";
            JOptionPane opcion = new JOptionPane();
            opcion.setMessage(msg);
            opcion.setMessageType(JOptionPane.ERROR_MESSAGE);
            JDialog newDlg = opcion.createDialog(null, "Error al ejecutar comando..");                     
            newDlg.setLocationRelativeTo(null);
            newDlg.setAlwaysOnTop(true);
            newDlg.setModal(true);
            newDlg.setVisible(true);
            newDlg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
        finally{
            CNT.close();   
        }

    } 
     
     
 /*
 Final   
 */   
}
