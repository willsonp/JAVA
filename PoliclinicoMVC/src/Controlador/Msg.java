/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author rwp0002
 */
public class Msg extends JDialog {
     /**
     * Creates new form VentanaLogin
     * @param parent
     * @param modal
     */
    
    public Msg(Frame parent, boolean modal) {
        super(parent, modal);        
    }    
     
   
    public static void error(String msg_detail, String msg_title) {
        JOptionPane.showMessageDialog(null,msg_detail,msg_title,0);
    }
   /*
    public  static void info(String msg_detail, String msg_title) {
        JOptionPane.showMessageDialog(null,msg_detail,msg_title,1);
    }
    public  static void alerta(String msg_detail, String msg_title) {
        JOptionPane.showMessageDialog(null,msg_detail,msg_title,2);
    }
    public  static void pregunta(String msg_detail, String msg_title) {
        JOptionPane.showMessageDialog(null,msg_detail,msg_title,3);
    }
   */
    
}
