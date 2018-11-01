package Presentacion;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import DAO.*;
import Controlador.*;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rwp0002
 */
public class VentanaLogin extends javax.swing.JDialog implements KeyListener{

    /**
     * Creates new form VentanaLogin
     * @param parent
     * @param modal
     */
    Connection CNT;
    PreparedStatement data;
    String SQL=null;
    ResultSet rs;
    
    public VentanaLogin(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        btnSalir.setMnemonic(KeyEvent.VK_S);
        btnEntrar.setMnemonic(KeyEvent.VK_E);
        btnCancelar.setMnemonic(KeyEvent.VK_C);
        txtcodigo.requestFocus();
        //KeyListener mienbros
        TxtPassword.addKeyListener(this);
        TxtUsuario.addKeyListener(this);
        btnCancelar.addKeyListener(this);
        btnEntrar.addKeyListener(this);
        btnSalir.addKeyListener(this);      
        jPanel1.addKeyListener(this);
        jPanel2.addKeyListener(this);       
        jPanel3.addKeyListener(this); 
        txtcodigo.addKeyListener(this);
        //llenarCombo();
    }
    
    
    public void cerrar(){
        Object [] opciones ={"Aceptar","Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(rootPane,
                "En realidad desea  cerrar la Aplicacion?",
                "Mensaje de Confirmacion",JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
        if (eleccion == JOptionPane.YES_OPTION)
            {
                System.exit(0);
                //this.dispose();
            }else{
        }
    }
    
    public boolean controlarcampos(){
        if(txtcodigo.getText().isEmpty()){
          JOptionPane.showMessageDialog(rootPane, "Debe introducir informacion en el campo, NO se permite valor en Blanco", "Control Campo en Blanco", JOptionPane.ERROR_MESSAGE);
          txtcodigo.requestFocus();        
          return true;          
        }else if(TxtUsuario.getText().isEmpty()){
          JOptionPane.showMessageDialog(rootPane, "Debe introducir informacion en el campo, NO se permite valor en Blanco", "Control Campo en Blanco", JOptionPane.ERROR_MESSAGE);
          TxtUsuario.requestFocus();        
          return true;          
        }  
        else if(TxtPassword.getPassword().length <= 0){
          JOptionPane.showMessageDialog(rootPane, "Debe introducir informacion en el campo, NO se permite valor en Blanco", "Control Campo en Blanco", JOptionPane.ERROR_MESSAGE);
          TxtPassword.requestFocus();         
          return true;      
        }
      return false;
    }
    
    public void limpiar()
    {
      //  llenarCombo();
        TxtUsuario.setText("");
        TxtPassword.setText("");
        txtcodigo.setText("");
        //Devolver el backColor a Blanco
        TxtUsuario.setBackground(Color.WHITE);
        TxtPassword.setBackground(Color.WHITE);
        //Elnviar el focus del control al Nombre
        txtcodigo.requestFocus();
    }
    
  /*  public void llenarCombo()
    {
       DatosUsuario du= new DatosUsuario();
       cmbEmpresa.removeAllItems();
       for (int i=0;i< du.MostrarUser().size();i++){
         cmbEmpresa.addItem(du.MostrarUser().get(i));
       }
       
    }*/
    
    public void ingresar(){
        String pass="";
        String user,codigo,xuser,xpass,xcodigo;
        Blob img=null;
        
        //Pasar valores otenido en el password
        char [] password = TxtPassword.getPassword();
        //Recorrer la variable de tipo CHAR y pasrla a una tipo String
        for (int x=0; x<password.length; x++ ){
            pass+=password[x];
        }
        
        codigo=txtcodigo.getText();
        
        //Encriptar concatenamos el la clave + idusuario para posteriormente comparar estos valores con los de la base de Datos
        pass=Encriptar.convertToSHA1(pass+codigo);
       
        user=TxtUsuario.getText();
           
        SQL="select idusuario, username, userpassw, logo from usuario where idusuario='"+codigo+"' and  trim(username) = '"+user+"'  and trim(userpassw)='"+pass+"'";
        //JOptionPane.showMessageDialog(rootPane,SQL);
        try {
            CNT=ConexionDB.ConectarMySQL();
            data=CNT.prepareStatement(SQL);
            rs=data.executeQuery();
            xuser="";
            xpass="";
            xcodigo="";
                     
            while (rs.next()==true){
                xuser=rs.getString("username").trim();
                xpass=rs.getString("userpassw").trim();
                xcodigo=rs.getString("idusuario").trim();
                img=rs.getBlob("logo");
            }   
            
            CNT.close();
            rs.close();
               
            if ( codigo.equals(xcodigo) && user.equals(xuser) && pass.equals(xpass) ){
                  JOptionPane.showMessageDialog(rootPane, "Bienvenido al Sistema Sr(a). "+user ,"Control de Acceso",JOptionPane.INFORMATION_MESSAGE);
                 // Menu f = new Menu();
                 // f.mostraruser(img);
                 // f.setTitle(f.getTitle()+ "   Usuario(a) loggeado : "+xcodigo+" , "+xuser);
                 // this.dispose();
                 // f.setAlwaysOnTop(true);
                 // f.setVisible(true);
                 
            }
            else
            {
                JOptionPane.showMessageDialog(rootPane,"Codigo...:"+codigo+ "  y Usuario...:"+user+"  No Autorizado " ,"Control de Acceso",JOptionPane.INFORMATION_MESSAGE);
                txtcodigo.requestFocus();
                
            }
          
          
        } catch (SQLException ex) {
            Logger.getLogger(VentanaLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnEntrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        TxtPassword = new javax.swing.JPasswordField();
        TxtUsuario = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtcodigo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Acceso al Sistema");
        setAlwaysOnTop(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jPanel2.setForeground(new java.awt.Color(153, 204, 255));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/User_128x128.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(46, 46, 46))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(153, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        btnEntrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Key_48x48.png"))); // NOI18N
        btnEntrar.setText("F4=Entrar");
        btnEntrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEntrar.setPreferredSize(new java.awt.Dimension(120, 80));
        btnEntrar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnEntrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });
        btnEntrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnEntrarKeyPressed(evt);
            }
        });
        jPanel3.add(btnEntrar);

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Cancel_48x48.png"))); // NOI18N
        btnCancelar.setText("F6=Cancelar");
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelar.setPreferredSize(new java.awt.Dimension(120, 80));
        btnCancelar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        btnCancelar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCancelarKeyPressed(evt);
            }
        });
        jPanel3.add(btnCancelar);

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Salir_48x48.png"))); // NOI18N
        btnSalir.setText("F10=Salir");
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir.setPreferredSize(new java.awt.Dimension(120, 80));
        btnSalir.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        btnSalir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnSalirKeyPressed(evt);
            }
        });
        jPanel3.add(btnSalir);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jPanel1.setForeground(new java.awt.Color(204, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Código:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 60, -1));

        TxtPassword.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TxtPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TxtPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TxtPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TxtPasswordFocusLost(evt);
            }
        });
        TxtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtPasswordKeyPressed(evt);
            }
        });
        jPanel1.add(TxtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 290, 25));

        TxtUsuario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TxtUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TxtUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TxtUsuarioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TxtUsuarioFocusLost(evt);
            }
        });
        TxtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtUsuarioKeyPressed(evt);
            }
        });
        jPanel1.add(TxtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 290, 25));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Contraseña:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Usuario:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 60, -1));

        txtcodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtcodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtcodigoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtcodigoFocusLost(evt);
            }
        });
        txtcodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcodigoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcodigoKeyTyped(evt);
            }
        });
        jPanel1.add(txtcodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 90, 25));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        if (controlarcampos()==false){
            ingresar();
        }
    }//GEN-LAST:event_btnEntrarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        cerrar();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        cerrar();
    }//GEN-LAST:event_formWindowClosing

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void TxtUsuarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtUsuarioFocusGained
        TxtUsuario.setBackground(Color.YELLOW);
        TxtUsuario.selectAll();
    }//GEN-LAST:event_TxtUsuarioFocusGained

    private void TxtUsuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtUsuarioFocusLost
       TxtUsuario.setBackground(Color.WHITE);
    }//GEN-LAST:event_TxtUsuarioFocusLost

    private void TxtPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtPasswordFocusGained
       TxtPassword.setBackground(Color.YELLOW);
       TxtPassword.selectAll();
    }//GEN-LAST:event_TxtPasswordFocusGained

    private void TxtPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtPasswordFocusLost
       TxtPassword.setBackground(Color.WHITE);
    }//GEN-LAST:event_TxtPasswordFocusLost

    private void TxtUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtUsuarioKeyPressed
       if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         TxtPassword.requestFocus();
        }
    }//GEN-LAST:event_TxtUsuarioKeyPressed

    private void TxtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtPasswordKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            btnEntrar.requestFocus();
        }
    }//GEN-LAST:event_TxtPasswordKeyPressed

    private void btnEntrarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnEntrarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            btnEntrar.doClick();
        }
    }//GEN-LAST:event_btnEntrarKeyPressed

    private void btnCancelarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCancelarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            btnCancelar.doClick();
        }
    }//GEN-LAST:event_btnCancelarKeyPressed

    private void btnSalirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSalirKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            btnSalir.doClick();
        }
    }//GEN-LAST:event_btnSalirKeyPressed

    private void txtcodigoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcodigoFocusGained
        txtcodigo.setBackground(Color.YELLOW);
        txtcodigo.selectAll();
    }//GEN-LAST:event_txtcodigoFocusGained

    private void txtcodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcodigoFocusLost
        txtcodigo.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtcodigoFocusLost

    private void txtcodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodigoKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            TxtUsuario.requestFocus();
        }

    }//GEN-LAST:event_txtcodigoKeyPressed

    private void txtcodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodigoKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9') evt.consume(); //permitir solo numeros
    }//GEN-LAST:event_txtcodigoKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                VentanaLogin dialog = new VentanaLogin(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                       // System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField TxtPassword;
    private javax.swing.JTextField TxtUsuario;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEntrar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtcodigo;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_F4){
          btnEntrar.doClick();
        }
     
        if(e.getKeyCode()==KeyEvent.VK_F6){
          btnCancelar.doClick();
        }
           
        if(e.getKeyCode()==KeyEvent.VK_F10){
          btnSalir.doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
