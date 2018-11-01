/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author rwp0002
 */
public class ConexionDB {
   
    private static String DriverName; 
    private static String ServerName; 
    private static String DBName; 
    private static String UsrName;
    private static String Pwd; 
    
    //Leer el Archivo
    public static void getfile(){
         try{
            Properties p = new Properties();
            p.load(new FileInputStream(System.getProperty("user.dir") +"\\ConfigPath.ini"));           
            DriverName = p.getProperty("DriverName");
            ServerName = p.getProperty("ServerName");
            DBName = p.getProperty("DBName");
            UsrName = p.getProperty("UsrName");
            Pwd = p.getProperty("Pwd");
         }catch(Exception e){
            /*
            Montamos el JOptionPane en un JDialog
            */
            String msg= e +"\n Error al Leer el Archivo de la ruta de la  la BD ";
            JOptionPane opcion = new JOptionPane();
            opcion.setMessage(msg);
            opcion.setMessageType(JOptionPane.INFORMATION_MESSAGE);
            JDialog newDlg = opcion.createDialog(null, "Error al Leer Archivo..");                     
            newDlg.setLocationRelativeTo(null);
            newDlg.setAlwaysOnTop(true);
            newDlg.setModal(true);
            newDlg.setVisible(true);
            newDlg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);            
            
         }
     }
    
    public static Connection ConectarMySQL()
    {
        //Ejecutar el metodo para leer el archivo
        getfile();
        
        Connection Conexion=null;         
        try {
           
          // Class.forName("com.mysql.jdbc.Driver");
           Class.forName(DriverName);
         //  DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
             
         //  Conexion=DriverManager.getConnection("jdbc:odbc:GMGBD","gmg","gmg");
         //    Conexion=DriverManager.getConnection("jdbc:postgresql://localhost:5432/GMG_BD","postgres","jcontreras");
           //  Conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/wjdb","root","Development");
            Conexion=DriverManager.getConnection(ServerName+DBName,UsrName,Pwd);
             //Conexion=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","GMG_BD","jcontreras");
         //    JOptionPane.showMessageDialog(null,"Conectado con la BD ");
        } catch (Exception e) {
            /*
            Montamos el JOptionPane en un JDialog
            */
            String msg= e +"\n Error al Leer el Archivo de la ruta de la  la BD ";
            JOptionPane opcion = new JOptionPane();
            opcion.setMessage(msg);
            opcion.setMessageType(JOptionPane.INFORMATION_MESSAGE);
            JDialog newDlg = opcion.createDialog(null, "Error al Abrir la Conexion de la DB..");                     
            newDlg.setLocationRelativeTo(null);
            newDlg.setAlwaysOnTop(true);
            newDlg.setModal(true);
            newDlg.setVisible(true);
            newDlg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);          
            
     
            Conexion=null;
        }
        return Conexion;    
}
}
