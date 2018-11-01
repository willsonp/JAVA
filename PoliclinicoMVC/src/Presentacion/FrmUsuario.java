/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;
import DAO.*;
import Controlador.*;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
//import net.sf.jasperreports.engine.JRException;
//import GridJtable.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author Jabnel Contreras y Willson Perez
 */
public class FrmUsuario extends javax.swing.JDialog implements KeyListener {
    
    /**
     * Creates new form FrmUsuario
     * @param parent
     * @param modal
     */
    /**
     * Creates new form FrmUsuario
     */
    boolean edit=false;
    DefaultTableModel modelo,mlocalizador;
    Connection CNT;
    PreparedStatement data;
    String SQL=null;
    int codigo=0;
    String  xnivel,xsexo=null;
    String rutaimagen=null;
    
    public FrmUsuario(java.awt.Frame parent, boolean modal){
         super(parent,modal);
         initComponents();
         this.setTitle("Mantenimiento de Usuarios");
         txtcedula.requestFocus(); 
         
        //asociadr botones con Alt+Letra
         btnGuardar.setMnemonic(KeyEvent.VK_G);
         btnCancelar.setMnemonic(KeyEvent.VK_C);
         btnEliminar.setMnemonic(KeyEvent.VK_E);
         btnSalir.setMnemonic(KeyEvent.VK_S);
         btnBuscar.setMnemonic(KeyEvent.VK_B);
         btnImprimir.setMnemonic(KeyEvent.VK_I);
         admin.setMnemonic(KeyEvent.VK_D);
         user.setMnemonic(KeyEvent.VK_O);
         supervisor.setMnemonic(KeyEvent.VK_V);
         masculino.setMnemonic(KeyEvent.VK_M);
         femenino.setMnemonic(KeyEvent.VK_F);
         
         btnagrega.setMnemonic(KeyEvent.VK_A);
         btnborrar.setMnemonic(KeyEvent.VK_R);
         btnactualiza.setMnemonic(KeyEvent.VK_U);
         btnElegir.setMnemonic(KeyEvent.VK_L);
        
         //GridColor();
         //llenarCombo();
         
         /*
         Instanciar y llamar el metodo MostrarEmpreas y asignaro a cmbEmpresa
         para Llenarlo
         cabe destacar que este componente tiene la Propiedad Typed Parameter = <DatosCompania>
         que es la sociadcion de la entidad a la que se quiere listar
         */
         
         /*
         DatosCompania datoscompania = new DatosCompania();
         datoscompania.MostrarEmpresas(cmbEmpresa);
         */
         Buscar();
        
         //Asociar al Evento KeyListener para Ejecutar un Accion Rapida
         TxtNombre.addKeyListener(this);
         TxtApellido.addKeyListener(this);
         TxtDireccion.addKeyListener(this);
         txtBuscar.addKeyListener(this);
         btnGuardar.addKeyListener(this);
         btnCancelar.addKeyListener(this);
         btnEliminar.addKeyListener(this);
         btnSalir.addKeyListener(this);
         btnBuscar.addKeyListener(this);
         btnImprimir.addKeyListener(this);
         jTable1.addKeyListener(this);
         txtuserpassw.addKeyListener(this);
         txtUsername.addKeyListener(this);
         masculino.addKeyListener(this);         
         femenino.addKeyListener(this);
         admin.addKeyListener(this);
         supervisor.addKeyListener(this);
         user.addKeyListener(this);
         txtcorreo.addKeyListener(this);
         txtcedula.addKeyListener(this);
         btnactualiza.addKeyListener(this);
         btnborrar.addKeyListener(this);
         btnagrega.addKeyListener(this);
         cmbSeleccion.addKeyListener(this);
         txtnumero.addKeyListener(this);
         tablaLocalizadores.addKeyListener(this);
         lblimagen.addKeyListener(this);
         btnElegir.addKeyListener(this);
       
        //*******************************************
        //Components add to actionlistener
        btnborrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               int i = tablaLocalizadores.getSelectedRow();
               Ctrl_Localizador cdl = new Ctrl_Localizador();
               if (i>=0 ){
                   //***para verificar si de esta borrando en modo de ediccion
                   
                    Object [] opciones ={"Aceptar","Cancelar"};
                    int eleccion = JOptionPane.showOptionDialog(rootPane,"En realidad desea  borrar el Registro?","Mensaje de Confirmacion",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
                    
                    if (eleccion == JOptionPane.YES_OPTION)
                    { 
                     //Si es editando y elije Borrar Localizador   
                     if (edit==true && codigo>0){
                       Ctrl_Localizador eliminaloc = new Ctrl_Localizador();
                       
                       ArrayList<DatosLocalizador> xcampo=eliminaloc.Listar(codigo,"U");
                         try {
                             eliminaloc.Eliminar(xcampo.get(i).getIdDeta_localizador());                             
                         } catch (SQLException ex) {
                             Logger.getLogger(FrmUsuario.class.getName()).log(Level.SEVERE, null, ex);
                         }
                        
                     }   
                     //Removerlo del Table Localizadores
                     mlocalizador.removeRow(i);
                     
                     
                    }else{
                    }
                   //***********
               }else{
                   //System.out.println("Error al Borrar");
                   JOptionPane.showMessageDialog(rootPane, "Debe Seleccionar un Regitro para Borrar");
                   tablaLocalizadores.requestFocus();
                   if(tablaLocalizadores.getRowCount()!=0){
                        tablaLocalizadores.setRowSelectionInterval(0, 0);
                        tablaLocalizadores.setColumnSelectionInterval(0, 1);
                    }                    
               }
               //Poner Index 
               cmbSeleccion.setSelectedIndex(0);
            }
        });
        
        btnagrega.addActionListener(new ActionListener() {
            @Override
           
            public void actionPerformed(ActionEvent e) {
               if(!verficarexiste(cmbSeleccion.getSelectedItem().toString(),txtnumero.getText())) 
                  localizadores(cmbSeleccion.getSelectedItem().toString(),txtnumero.getText()); 
               
               cmbSeleccion.setSelectedIndex(0);
            }  
            
        });
        btnactualiza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // i = the index of the selected row
                if (cmbSeleccion.getSelectedIndex()!=0 && !txtnumero.getText().isEmpty()){
                  //************************
                    int i = tablaLocalizadores.getSelectedRow();
                    if(i >= 0){ 
                        mlocalizador.setValueAt(cmbSeleccion.getSelectedItem(), i, 0);
                        mlocalizador.setValueAt(txtnumero.getText(), i, 1);
                    }
                    else{
                         JOptionPane.showMessageDialog(rootPane, "Debe Seleccionar un Regitro para Actualizar");                     
                         tablaLocalizadores.requestFocus();
                         if(tablaLocalizadores.getRowCount()!=0){
                            tablaLocalizadores.setRowSelectionInterval(0, 0);
                            tablaLocalizadores.setColumnSelectionInterval(0, 1);
                         }
                        
                     }
                   }
                    else{
                         JOptionPane.showMessageDialog(rootPane, "Debe Seleccionar un Regitro para Actualizar");                     
                         tablaLocalizadores.requestFocus();
                         if(tablaLocalizadores.getRowCount()!=0){
                            tablaLocalizadores.setRowSelectionInterval(0, 0);
                            tablaLocalizadores.setColumnSelectionInterval(0, 1);
                         }
                        }
                cmbSeleccion.setSelectedIndex(0);
             }
        });
        tablaLocalizadores.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                llevardatoslocalizar();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
      
    }
    
       public void GridColor() {
        jTable1.setDefaultRenderer(Object.class, new ModeloGrid());
        jTable1.setShowHorizontalLines(true);
        jTable1.setShowVerticalLines(true);    
        
       }
       
   /* public void llenarCombo(){
       DatosCompania dcomp= new DatosCompania();
       cmbEmpresa.removeAllItems();
       cmbEmpresa.addItem("Seleccione una Empresa Aquí");
       for (int i=0;i< dcomp.LlenarCMB().size();i++){
         cmbEmpresa.addItem(dcomp.LlenarCMB().get(i).getIdCompania()+" | "+dcomp.LlenarCMB().get(i).getNombre()+" | "+dcomp.LlenarCMB().get(i).getRNC());
       }
       
    }*/
   
    //Abrir Imagen desde la base de Datos y mostrarla en un JLabel
    public void MostrarImgDB(byte [] bytes, JLabel lbl){
        BufferedImage img = null;
        InputStream in = new ByteArrayInputStream(bytes);
        try {
            img = ImageIO.read(in);
            ImageIcon imgi = new ImageIcon(img.getScaledInstance(lbl.getWidth(),lbl.getHeight(), Image.SCALE_SMOOTH));
            lbl.setIcon(imgi);
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage()+" \n Este Usuario no tiene foto agregado ");                     
        }            
    }
     
    //Abrir Imagen desde una ubicacion o disco local y mostrarla en un JLabel   
    public void mostrarimagen(File ruta, JLabel lbl){
            ImageIcon imagen = new ImageIcon(ruta.getAbsolutePath());
            rutaimagen=ruta.getAbsolutePath();
            //Las tres lineas debajo son para ajustar la imagen al tamaño del componente
            Image convertir = imagen.getImage();
            Image tamano = convertir.getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon fin  = new ImageIcon(tamano);
            //ya esta en tamaño 130x130
            lbl.setIcon(fin);          
            
    }       
//****************************************************************
    public void limparlocalizador(){
            mlocalizador = (DefaultTableModel) tablaLocalizadores.getModel();
            mlocalizador.setColumnIdentifiers(new Object []{"Tipo Localizador","# Localizador"});
            mlocalizador.setRowCount(0);
            tablaLocalizadores.setModel(mlocalizador);
            cmbSeleccion.setSelectedIndex(0);
            
    }   
       
    public void localizadores(String tipo, String numero){
          if (cmbSeleccion.getSelectedIndex()!=0 && !txtnumero.getText().isEmpty()){
            try{
                mlocalizador = (DefaultTableModel) tablaLocalizadores.getModel();
                mlocalizador.setColumnIdentifiers(new Object []{"Tipo Localizador","# Localizador"});
                Object [] row = new Object[2];  
                row[0]=tipo;
                row[1]=numero;
                
                mlocalizador.addRow(row);
              
                tablaLocalizadores.setModel(mlocalizador); 
            }catch (HeadlessException e) {
              e.getMessage();
            }
           } else{
            if (cmbSeleccion.getSelectedIndex()==0){
                JOptionPane.showMessageDialog(rootPane, "Debe Elegir un un tipo de Localizador");
                cmbSeleccion.requestFocus();
            }else{
                JOptionPane.showMessageDialog(rootPane, "Debe Introducir un Numero de localizador");
                txtnumero.requestFocus();
            }
          }
              
    }   
    public void llevardatoslocalizar(){
        if (tablaLocalizadores.getRowCount()!=0){
            int i = tablaLocalizadores.getSelectedRow();
            cmbSeleccion.setSelectedItem(mlocalizador.getValueAt(i, 0).toString());
            txtnumero.setText(mlocalizador.getValueAt(i, 1).toString());
        }
    }
    public boolean verficarexiste(String tipo, String numero){
    //***************************
                for (int x=0 ; x < tablaLocalizadores.getRowCount();x++){
                     if (tablaLocalizadores.getValueAt(x, 0).equals(tipo)  && tablaLocalizadores.getValueAt(x, 1).equals(numero)){
                      JOptionPane.showMessageDialog(rootPane, "Ya existe este tipo de Localizador : "+cmbSeleccion.getSelectedItem() +"  con este Numero :"+txtnumero.getText()+ " Cantidad de Registros"+x);   
                     // txtnumero.requestFocus();
                      return true;                    
                     }
                }
                //******************************
     return false;           
    }

//****************************************************************       
       
    public void Buscar(){
        try {
              Ctrl_Usuario usr = new Ctrl_Usuario();
              
               ArrayList<Usuario> usuario =  usr.ListarUsuario(txtBuscar.getText());
               modelo = new DefaultTableModel();
               modelo.setColumnIdentifiers(new Object []{"Codigo","Cedula","Nombres","Apellidos","Direccion","Sexo"});
               Object [] row = new Object[6]; 
          
               for (int i=0 ; i< usuario.size();i++) {
                    row[0]=usuario.get(i).getIdUsuario();                    
                    row[1]=usuario.get(i).getCedula();
                    row[2]=usuario.get(i).getNombre();
                    row[3]=usuario.get(i).getApellidos();
                    row[4]=usuario.get(i).getDireccion();
                    row[5]=usuario.get(i).getSexo();
                    modelo.addRow(row);
                }
               
                jTable1.setModel(modelo);
                
                // Controla el tamano de las celdas 
                jTable1.getColumnModel().getColumn(0).setPreferredWidth(11);
                jTable1.getColumnModel().getColumn(1).setPreferredWidth(30);
                jTable1.getColumnModel().getColumn(2).setPreferredWidth(50);
                jTable1.getColumnModel().getColumn(3).setPreferredWidth(50);
                jTable1.getColumnModel().getColumn(4).setPreferredWidth(100);
                jTable1.getColumnModel().getColumn(5).setPreferredWidth(10);
                
                        
               // CNT.close(); 
              
        } catch (HeadlessException e) {
            e.getMessage();
        }
    }
 
    public void limpiar()
    {
        edit=false; //para saber si es Editar
        TxtNombre.setText("");
        TxtApellido.setText("");
        TxtDireccion.setText("");
     
        txtBuscar.setText("");
        //Devolver el backColor a Blanco
        TxtNombre.setBackground(Color.WHITE);
        TxtApellido.setBackground(Color.WHITE);
        TxtDireccion.setBackground(Color.WHITE);
     
        
        //Elnviar el focus del control al Nombre
        codigo=0; //para tener el valor del IdCliente que se va a editar
        Buscar();//para actualizar la Busqueda
     //   llenarCombo(); //Para las empresas
        
        jTabbedPane2.setSelectedIndex(0);
        
        txtcedula.requestFocus();
        txtUsername.setText("");
        txtuserpassw.setText("");        
        masculino.setSelected(true);
        admin.setSelected(true);
        txtcorreo.setText("");
        txtcedula.setText("");
        
        //para quitar la imagen al momento de limpiar la imagen
         lblimagen.setIcon(new ImageIcon());
         lblimagen.setText("");
         rutaimagen=null;
         
        limparlocalizador();
        txtnumero.setText("");
        cmbSeleccion.setSelectedIndex(0);        
    }
     
    public void cerrar(){
        Object [] opciones ={"Aceptar","Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(rootPane,"En realidad desea  cerrar la ventana?","Mensaje de Confirmacion",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
        if (eleccion == JOptionPane.YES_OPTION)
            {
                System.exit(0);
                //this.dispose();
                edit=false;
            }else{
        }
    }
     
    public void confirmaeliminar(){
        if(codigo>0){
        
            Object [] opciones ={"Aceptar","Cancelar"};
            int eleccion = JOptionPane.showOptionDialog(rootPane,"En realidad desea  Eliminar el Registro?","Mensaje de Confirmacion",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
        
                if (eleccion == JOptionPane.YES_OPTION){
                    try{
                        Ctrl_Usuario usr = new Ctrl_Usuario(); //instaciamos la clase DatosCliete
                        usr.Elimnar(codigo);
                        limpiar();
                    }
                    catch(Exception e){
                        JOptionPane.showMessageDialog(rootPane, e+"\n No se pudo Ejecutar el metodo Eliminar", "Control de Sistema", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                }
        }else{
            JOptionPane.showMessageDialog(rootPane, "Debe Seleccionar un registro para Eliminarlo", "Control de Sistema", JOptionPane.ERROR_MESSAGE);
            jTabbedPane2.setSelectedIndex(1);
            txtBuscar.requestFocus();
        }
    }
    
    public boolean controlarcampos(){
      
        if(txtcedula.getText().isEmpty()){
          JOptionPane.showMessageDialog(rootPane, "Debe introducir informacion en el campo, NO se permite valor en Blanco", "Control Campo en Blanco", JOptionPane.ERROR_MESSAGE);
          txtcedula.requestFocus();
          jTabbedPane2.setSelectedIndex(0);
          return true;          
      } 
      else if(TxtNombre.getText().isEmpty()){
          JOptionPane.showMessageDialog(rootPane, "Debe introducir informacion en el campo, NO se permite valor en Blanco", "Control Campo en Blanco", JOptionPane.ERROR_MESSAGE);
          TxtNombre.requestFocus();
          jTabbedPane2.setSelectedIndex(0);
          return true;          
      } 
      else if(TxtApellido.getText().isEmpty()){
          JOptionPane.showMessageDialog(rootPane, "Debe introducir informacion en el campo, NO se permite valor en Blanco", "Control Campo en Blanco", JOptionPane.ERROR_MESSAGE);
          TxtApellido.requestFocus();
          jTabbedPane2.setSelectedIndex(0);
          return true;
      }else if(TxtDireccion.getText().isEmpty()){
          JOptionPane.showMessageDialog(rootPane, "Debe introducir informacion en el campo, NO se permite valor en Blanco", "Control Campo en Blanco", JOptionPane.ERROR_MESSAGE);
          TxtDireccion.requestFocus();
          jTabbedPane2.setSelectedIndex(0);
          return true;      
      }else if(txtUsername.getText().isEmpty()){
          JOptionPane.showMessageDialog(rootPane, "Debe introducir informacion en el campo, NO se permite valor en Blanco", "Control Campo en Blanco", JOptionPane.ERROR_MESSAGE);
          txtUsername.requestFocus();
          jTabbedPane2.setSelectedIndex(0);
          return true;
      }else if(txtuserpassw.getPassword().toString().isEmpty()){
          JOptionPane.showMessageDialog(rootPane, "Debe introducir informacion en el campo, NO se permite valor en Blanco", "Control Campo en Blanco", JOptionPane.ERROR_MESSAGE);
          txtuserpassw.requestFocus();
          jTabbedPane2.setSelectedIndex(0);
          return true;
      }else if(rutaimagen==null){
          JOptionPane.showMessageDialog(rootPane, "Debe Seleccionar Una Imagen, NO se permite valor en Blanco", "Control Campo en Blanco", JOptionPane.ERROR_MESSAGE);
         btnElegir.requestFocus();
          jTabbedPane2.setSelectedIndex(0);
          return true;
      }
      return false;
    } 
    
    public void llevardatos(){
         int fila=jTable1.getSelectedRow();
         codigo=(int) jTable1.getValueAt(fila, 0);
         edit=true;
         
         PreparedStatement st;         
         ResultSet rs;
        try{
            CNT=ConexionDB.ConectarMySQL();
            String sql="Select * from Usuario where status='A' and  idUsuario="+codigo;
            st=CNT.prepareStatement(sql);        
            rs=st.executeQuery(sql);
            rs.next();
             txtcedula.setText(rs.getString("cedula"));
             TxtNombre.setText(rs.getString("Nombre"));
             TxtApellido.setText(rs.getString("Apellidos"));
             TxtDireccion.setText(rs.getString("direccion"));             
             txtUsername.setText(rs.getString("username"));
             txtuserpassw.setText(rs.getString("userpassw")); 
             txtcorreo.setText(rs.getString("correo")); 
             rutaimagen=rs.getString("rutaimagen");
             //Llevar el valor Real de la tabla al ComboBox NO el Idex
             /*
             if(rs.getInt("IdCompania")!=0){
                 for (int x=0;x<cmbEmpresa.getItemCount(); x++){
                     if(cmbEmpresa.getItemAt(x).getIdCompania()==rs.getInt("IdCompania"))
                     {
                         cmbEmpresa.setSelectedIndex(x);
                         //JOptionPane.showMessageDialog(rootPane, cmbEmpresa.getItemAt(x));
                     }
                 }
                }
              */
             xnivel=rs.getString("nivel");
            
             if(xnivel!=null){             
                if (xnivel.equals("A")){
                    admin.setSelected(true);
                }
                else if (xnivel.equals("S")){
                    supervisor.setSelected(true);
                }
                else if (xnivel.equals("U")){
                    user.setSelected(true);
                }
             }
             
             xsexo=rs.getString("sexo");
             
             if(xsexo!=null){
             
                if (xsexo.equals("M")){
                    masculino.setSelected(true);
                }
                else if (xsexo.equals("F")){
                    femenino.setSelected(true);
                }
             }
             
             //Para mostrar la Imagen en el JLabel
             /*
             if(rutaimagen!=null){
                Blob img = rs.getBlob("logo");
                byte [] data= img.getBytes(1, (int)img.length());
                BufferedImage imgb = null;
                try{
                    imgb = ImageIO.read(new ByteArrayInputStream(data));                    
                }catch(Exception e){                  
                }
                ImageIcon logo = new ImageIcon(imgb);
                //Traspasamos para ajustar la imagen al tamaño del componente
                Image convertir = logo.getImage();
                Image tamano = convertir.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
                ImageIcon fin  = new ImageIcon(tamano);                
                
                lblimagen.setIcon(fin);                
            } else
                lblimagen.setIcon(new ImageIcon());
            */
            //mostrar otra imagen
             MostrarImgDB(rs.getBytes("logo"),lblimagen);
            //*******************
            //JOptionPane.showMessageDialog(rootPane, rs.getString("nombre").toString());
            
             CNT.close();
             rs.close();
             
              //Para Mostrar la informacion de los telefonos**
              limparlocalizador();
              Ctrl_Localizador cdl = new Ctrl_Localizador();
              ArrayList<DatosLocalizador> localizador =  cdl.Listar(codigo,"U");
              for (int x=0; x<localizador.size();x++){
                  try{
                        mlocalizador = (DefaultTableModel) tablaLocalizadores.getModel();
                        mlocalizador.setColumnIdentifiers(new Object []{"Tipo Localizador","# Localizador"});
                        Object [] row = new Object[2];  
                        row[0]=localizador.get(x).getTipo();
                        row[1]=localizador.get(x).getNumero(); 
                        mlocalizador.addRow(row);
              
                        tablaLocalizadores.setModel(mlocalizador); 
                }catch (HeadlessException e) {
                    e.getMessage();
                   JOptionPane.showMessageDialog(rootPane, "Probando");
                }                 
                  
              }
              
             jTabbedPane2.setSelectedIndex(0);
        }
        catch(Exception e){
            e.getMessage();
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

        GrupoNivel = new javax.swing.ButtonGroup();
        GrupoSexo = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TxtApellido = new javax.swing.JTextField();
        TxtDireccion = new javax.swing.JTextField();
        TxtNombre = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        masculino = new javax.swing.JRadioButton();
        femenino = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        admin = new javax.swing.JRadioButton();
        supervisor = new javax.swing.JRadioButton();
        user = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtuserpassw = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        txtcorreo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtcedula = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaLocalizadores = new javax.swing.JTable();
        cmbSeleccion = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtnumero = new javax.swing.JTextField();
        btnborrar = new javax.swing.JButton();
        btnactualiza = new javax.swing.JButton();
        btnagrega = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        lblimagen = new javax.swing.JLabel();
        btnElegir = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnImprimir = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Save_48x48.png"))); // NOI18N
        btnGuardar.setText("F4=Guardar");
        btnGuardar.setToolTipText("Click,Utilice la Tecla indicada o la combinacion Alt+G aqui para Guardar/Editar los cambios");
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardar.setPreferredSize(new java.awt.Dimension(120, 80));
        btnGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        btnGuardar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnGuardarKeyPressed(evt);
            }
        });
        jPanel2.add(btnGuardar);

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Cancel_48x48.png"))); // NOI18N
        btnCancelar.setText("F6=Cancelar");
        btnCancelar.setToolTipText("Click,Utilice la Tecla indicada o la combinacion Alt+C aqui para Cancelar los Cambios");
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelar.setMaximumSize(new java.awt.Dimension(107, 41));
        btnCancelar.setMinimumSize(new java.awt.Dimension(107, 41));
        btnCancelar.setPreferredSize(new java.awt.Dimension(120, 80));
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
        jPanel2.add(btnCancelar);

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Remove_48x48.png"))); // NOI18N
        btnEliminar.setText("F8=Eliminar");
        btnEliminar.setToolTipText("Click,Utilice la Tecla indicada o la combinacion Alt+E aqui para Eliminar un regristro");
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEliminar.setPreferredSize(new java.awt.Dimension(120, 80));
        btnEliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        btnEliminar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnEliminarKeyPressed(evt);
            }
        });
        jPanel2.add(btnEliminar);

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Salir_48x48.png"))); // NOI18N
        btnSalir.setText("F10=Salir");
        btnSalir.setToolTipText("Click,Utilice la Tecla indicada o la combinacion Alt+S aqui para Salir de la Ventana");
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir.setMaximumSize(new java.awt.Dimension(107, 41));
        btnSalir.setMinimumSize(new java.awt.Dimension(107, 41));
        btnSalir.setPreferredSize(new java.awt.Dimension(120, 80));
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
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnSalirKeyTyped(evt);
            }
        });
        jPanel2.add(btnSalir);

        jTabbedPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane2MouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Cedula:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel2.setText("Apellido:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jLabel3.setText("Direccion:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        TxtApellido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TxtApellidoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TxtApellidoFocusLost(evt);
            }
        });
        TxtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtApellidoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtApellidoKeyTyped(evt);
            }
        });
        jPanel1.add(TxtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 460, -1));

        TxtDireccion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TxtDireccionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TxtDireccionFocusLost(evt);
            }
        });
        TxtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtDireccionKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtDireccionKeyTyped(evt);
            }
        });
        jPanel1.add(TxtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 460, -1));

        TxtNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TxtNombreFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TxtNombreFocusLost(evt);
            }
        });
        TxtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtNombreKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtNombreKeyTyped(evt);
            }
        });
        jPanel1.add(TxtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 460, -1));

        jPanel4.setBackground(new java.awt.Color(153, 204, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sexo", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));

        masculino.setBackground(new java.awt.Color(153, 204, 255));
        GrupoSexo.add(masculino);
        masculino.setSelected(true);
        masculino.setText("Masculino");
        masculino.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                masculinoMouseClicked(evt);
            }
        });
        masculino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masculinoActionPerformed(evt);
            }
        });
        jPanel4.add(masculino);

        femenino.setBackground(new java.awt.Color(153, 204, 255));
        GrupoSexo.add(femenino);
        femenino.setText("Femenino");
        femenino.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                femeninoMouseClicked(evt);
            }
        });
        femenino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                femeninoActionPerformed(evt);
            }
        });
        jPanel4.add(femenino);

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 250, 60));

        jPanel6.setBackground(new java.awt.Color(153, 204, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos para Acceder a la Aplicacion", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
        jPanel6.setToolTipText("Aquí puedes agregar informacion como la cuenta para el usuario y la contraseña del mismo para posteriormente poder ingresar y tener acceso a las opciones del Sistema");
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(153, 204, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nivel de Acceso", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));

        admin.setBackground(new java.awt.Color(153, 204, 255));
        GrupoNivel.add(admin);
        admin.setSelected(true);
        admin.setText("Administrador");
        admin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adminMouseClicked(evt);
            }
        });
        admin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminActionPerformed(evt);
            }
        });
        jPanel5.add(admin);

        supervisor.setBackground(new java.awt.Color(153, 204, 255));
        GrupoNivel.add(supervisor);
        supervisor.setText("Supervisor");
        supervisor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                supervisorMouseClicked(evt);
            }
        });
        supervisor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supervisorActionPerformed(evt);
            }
        });
        jPanel5.add(supervisor);

        user.setBackground(new java.awt.Color(153, 204, 255));
        GrupoNivel.add(user);
        user.setText("Usuario");
        user.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userMouseClicked(evt);
            }
        });
        user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userActionPerformed(evt);
            }
        });
        jPanel5.add(user);

        jPanel6.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 122, 360, 64));

        jLabel7.setText("User Name:");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });
        txtUsername.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUsernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUsernameFocusLost(evt);
            }
        });
        txtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsernameKeyPressed(evt);
            }
        });
        jPanel6.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 270, -1));

        jLabel4.setText("User Password:");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 59, -1, -1));

        txtuserpassw.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtuserpasswFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtuserpasswFocusLost(evt);
            }
        });
        txtuserpassw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtuserpasswKeyPressed(evt);
            }
        });
        jPanel6.add(txtuserpassw, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 270, -1));

        jLabel8.setText("Correo:");
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 88, -1, -1));

        txtcorreo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtcorreoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtcorreoFocusLost(evt);
            }
        });
        txtcorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcorreoKeyPressed(evt);
            }
        });
        jPanel6.add(txtcorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 270, -1));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 380, 230));

        jLabel9.setText("Nombre:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        txtcedula.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtcedulaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtcedulaFocusLost(evt);
            }
        });
        txtcedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcedulaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcedulaKeyTyped(evt);
            }
        });
        jPanel1.add(txtcedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 130, -1));

        jPanel7.setBackground(new java.awt.Color(153, 204, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "F1=Localizadores (Telefonos, Fax & otros)", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
        jPanel7.setToolTipText("Aquí puedes agregar Telefónos, Celulares y Fax de los Usuarios");
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaLocalizadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo Localizador", "# Localizador"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaLocalizadores.setToolTipText("Puede presionar la tecla F1 o hacer Click en el registro deseado");
        tablaLocalizadores.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tablaLocalizadoresFocusGained(evt);
            }
        });
        tablaLocalizadores.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaLocalizadoresKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tablaLocalizadoresKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(tablaLocalizadores);

        jPanel7.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 260, 120));

        cmbSeleccion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione Aquí", "TRABAJO", "HOGAR", "FAX", "CELULAR" }));
        cmbSeleccion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbSeleccionMouseClicked(evt);
            }
        });
        cmbSeleccion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSeleccionItemStateChanged(evt);
            }
        });
        cmbSeleccion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbSeleccionFocusGained(evt);
            }
        });
        jPanel7.add(cmbSeleccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 130, 25));

        jLabel10.setText("Número Localizador:");
        jPanel7.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jLabel11.setText("F7=Tipo Localizador:");
        jPanel7.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        txtnumero.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtnumeroFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtnumeroFocusLost(evt);
            }
        });
        txtnumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnumeroKeyTyped(evt);
            }
        });
        jPanel7.add(txtnumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 130, -1));

        btnborrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Remove_24x24.png"))); // NOI18N
        btnborrar.setText("Borrar");
        btnborrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnborrar.setMaximumSize(new java.awt.Dimension(75, 60));
        btnborrar.setMinimumSize(new java.awt.Dimension(75, 60));
        btnborrar.setPreferredSize(new java.awt.Dimension(75, 60));
        btnborrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnborrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnborrarActionPerformed(evt);
            }
        });
        jPanel7.add(btnborrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 150, 90, -1));

        btnactualiza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Sync_24x24.png"))); // NOI18N
        btnactualiza.setText("Actualiza");
        btnactualiza.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnactualiza.setMaximumSize(new java.awt.Dimension(75, 60));
        btnactualiza.setMinimumSize(new java.awt.Dimension(75, 60));
        btnactualiza.setPreferredSize(new java.awt.Dimension(75, 60));
        btnactualiza.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel7.add(btnactualiza, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 90, -1));

        btnagrega.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Add_24x24.png"))); // NOI18N
        btnagrega.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnagrega.setLabel("Agregar");
        btnagrega.setMaximumSize(new java.awt.Dimension(75, 60));
        btnagrega.setMinimumSize(new java.awt.Dimension(75, 60));
        btnagrega.setPreferredSize(new java.awt.Dimension(75, 60));
        btnagrega.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnagrega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregaActionPerformed(evt);
            }
        });
        jPanel7.add(btnagrega, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, 90, -1));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 230, 410, 230));

        jPanel8.setBackground(new java.awt.Color(153, 204, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Foto", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblimagen.setToolTipText("El Formato de la Imagen se recomienda sea .JPG, .JPEG or.PNG y dimension 128x128, Tamaño Maximo de 64kb");
        lblimagen.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        lblimagen.setMaximumSize(new java.awt.Dimension(130, 130));
        lblimagen.setMinimumSize(new java.awt.Dimension(130, 130));
        lblimagen.setPreferredSize(new java.awt.Dimension(130, 130));
        jPanel8.add(lblimagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 21, 130, 130));

        btnElegir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Check_24x24.png"))); // NOI18N
        btnElegir.setText("Elegir");
        btnElegir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnElegirActionPerformed(evt);
            }
        });
        jPanel8.add(btnElegir, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 158, -1, -1));

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, 160, 200));

        jTabbedPane2.addTab("F2 = Mantenimiento de Usuarios", jPanel1);

        jPanel3.setBackground(new java.awt.Color(153, 204, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnImprimir.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Print_32x32.png"))); // NOI18N
        btnImprimir.setText("F12=Imprimir");
        btnImprimir.setToolTipText("Click,Utilice la Tecla indicada o la combinacion Alt+I aqui para Imprimir los datos");
        btnImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnImprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });
        btnImprimir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnImprimirKeyPressed(evt);
            }
        });
        jPanel3.add(btnImprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, 100, 80));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Introduzca caracteres a Buscar");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        txtBuscar.setToolTipText("Aqui introduce los Caracteres a Buscar, Luego puede hacer Click en el registro deseado");
        txtBuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarFocusGained(evt);
            }
        });
        txtBuscar.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtBuscarInputMethodTextChanged(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });
        jPanel3.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 370, -1));

        btnBuscar.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Search_48x48.png"))); // NOI18N
        btnBuscar.setText("F5=Buscar");
        btnBuscar.setToolTipText("Click,Utilice la Tecla indicada o la combinacion Alt+B aqui para buscar");
        btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        btnBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnBuscarKeyPressed(evt);
            }
        });
        jPanel3.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 100, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setToolTipText("Puede hacer Click en el registro deseado");
        jTable1.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTable1KeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 760, 290));

        jTabbedPane2.addTab("F3 = Buscar Usuarios", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 815, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE))
        );

        jTabbedPane2.getAccessibleContext().setAccessibleName("F2 = Mantenimiento de Usuarios");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
       cerrar();
    }//GEN-LAST:event_formWindowClosing

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        cerrar();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        //Para pasar los valores del password a string
        String pass="";
        char [] password = txtuserpassw.getPassword();
        
        for (int x=0; x<password.length; x++ ){
            pass+=password[x];
        }
        //Usuario du = new Usuario();
        Ctrl_Usuario cdu = new Ctrl_Usuario();
        Ctrl_Localizador cdl = new Ctrl_Localizador();
       // DatosLocalizador dl = new DatosLocalizador();
 
        /*
        Para Tomar el valor del campo clave y sumarselo al password y evitar que el valor del password se repita
        aunque tengan la misma contrasena
        //Encriptar
        */
        
        if (edit=true && codigo>0){
            pass=Encriptar.convertToSHA1(pass+codigo); //Para saber si esta editando
        }else
        {
            pass=Encriptar.convertToSHA1(pass+cdu.LastId());// si es nuevo ejecuta el metodo LasId de la entidad DatosUsuarios
        }
        //hasta aqui valores del password
       
        //pasamos parametros para Ejecutar el Metodo Guardar
        cdu.setCedula(txtcedula.getText());
        cdu.setNombre(TxtNombre.getText());
        cdu.setApellidos(TxtApellido.getText());
        cdu.setDireccion(TxtDireccion.getText());
        //du.setIdCompania(cmbEmpresa.getItemAt(cmbEmpresa.getSelectedIndex()).getIdCompania()); // Aqui se guarda justamente el codifo de la empresa..NO el Item Idex del componente
        cdu.setUsername(txtUsername.getText());
        cdu.setUserpassw(pass); 
        cdu.setRutaimagen(rutaimagen);  
        
        //du.setLogo(lblimagen.getgetText().getBytes());
       
        
        //Activarlo para validar correo
        boolean email=validarEmail.validateEmail(txtcorreo.getText());
        if (email==true)         
          cdu.setCorreo(txtcorreo.getText()); 
         
        else{
           JOptionPane.showMessageDialog(rootPane, "Direccion de Correo Electronico NO cumple el Formato permitido");
            txtcorreo.requestFocus();
            txtcorreo.selectAll();
        }
      
        
        //verificar valor para nivel de acceso
            if(admin.isSelected()){
               xnivel="A";
            }else if(supervisor.isSelected()){
               xnivel="S";
            }else if(user.isSelected()){
                xnivel="U";
            }
        
        cdu.setNivel(xnivel);
        
        //verificar valor para el sexo
            if(masculino.isSelected()){
                xsexo="M";
            }else
                xsexo="F";         
        
        cdu.setSexo(xsexo);
        
        
       if (controlarcampos()==false){ 
        try {
            //si vamos a Editar
            if (edit=true && codigo>0){
                cdu.Editar(codigo); //llamamos el metodo Editar de la clase DatosCliente
                
                /*
                para verificar si al momento de guardar el registro de la compañia no guardaron telefono
                significa que es insertar nuevo registro de todas manera en detalle localizar
                */
                ArrayList<DatosLocalizador> xcampo=cdl.Listar(codigo,"U");
                if(tablaLocalizadores.getRowCount()!=0){                     
                       for (int i=0 ; i < tablaLocalizadores.getRowCount();i++){
                            cdl.setTipo_Entidad("U");//para identificar el tipo entidad como negocio o empresa
                            cdl.setIdEntidad(codigo);
                            cdl.setTipo(tablaLocalizadores.getValueAt(i, 0).toString());
                            cdl.setNumero(tablaLocalizadores.getValueAt(i, 1).toString());
                           
                            if(cdl.ifExists(codigo,"U", tablaLocalizadores.getValueAt(i, 0).toString(), tablaLocalizadores.getValueAt(i, 1).toString()))
                               cdl.Editar(xcampo.get(i).getIdDeta_localizador(),"U", codigo);
                            else
                              cdl.Guardar();  
                           
                        }
                }   
                /*
                hasta aqui para lo de la ediccion de localizadores
                */
 
                
            }
            else{
               
                cdu.Guardar();//llamamos el metodo Guardar de la clase DatosCliente
               
               /*
                 Aqui para guardar los localizadores agregados
                 */
                int xid= cdu.LastId(); //cogemos el ID de registro guardado
                for (int i=0 ; i< tablaLocalizadores.getRowCount();i++) {
                    cdl.setTipo_Entidad("U");//para identificar el tipo entidad como negocio o empresa
                    cdl.setIdEntidad(xid);
                    cdl.setTipo(tablaLocalizadores.getValueAt(i, 0).toString());
                    cdl.setNumero(tablaLocalizadores.getValueAt(i, 1).toString());
                    cdl.Guardar();
                }
                /*
                hasta aqui agregar localizadores 
                */
               
            }            
            limpiar(); //llamamos el metodo Limpiar de la clase FrmProbando
        } catch (SQLException ex) {
            Logger.getLogger(FrmUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }   catch (IOException ex) {
                Logger.getLogger(FrmUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void TxtApellidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtApellidoKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         TxtDireccion.requestFocus();
                    
        }
    }//GEN-LAST:event_TxtApellidoKeyPressed

    private void TxtDireccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtDireccionKeyPressed
       if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         txtUsername.requestFocus();                    
        }
    }//GEN-LAST:event_TxtDireccionKeyPressed

    private void TxtApellidoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtApellidoFocusLost
       TxtApellido.setBackground(Color.WHITE);
    }//GEN-LAST:event_TxtApellidoFocusLost

    private void TxtDireccionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtDireccionFocusLost
        TxtDireccion.setBackground(Color.WHITE);
    }//GEN-LAST:event_TxtDireccionFocusLost

    private void btnSalirKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSalirKeyTyped
      if(evt.getKeyCode()==KeyEvent.VK_F10){
          cerrar();
      }
    }//GEN-LAST:event_btnSalirKeyTyped

    private void TxtApellidoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtApellidoFocusGained
        // TODO add your handling code here:
        TxtApellido.setBackground(Color.YELLOW);
        TxtApellido.selectAll();
    }//GEN-LAST:event_TxtApellidoFocusGained

    private void TxtDireccionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtDireccionFocusGained
        // TODO add your handling code here:
        TxtDireccion.setBackground(Color.YELLOW);
        TxtDireccion.selectAll();
    }//GEN-LAST:event_TxtDireccionFocusGained

    private void TxtApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtApellidoKeyTyped
        // TODO add your handling code here:
         int Limite=45;
        char C=evt.getKeyChar(); 
        
        if(Character.isLowerCase(C))
        {
            String Cad=("" + C).toUpperCase(); 
            C=Cad.charAt(0);
            evt.setKeyChar(C);
        }
        
        if(TxtApellido.getText().length()==Limite)
        {
            evt.consume();
        }
    }//GEN-LAST:event_TxtApellidoKeyTyped

    private void TxtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtDireccionKeyTyped
        // TODO add your handling code here:
         int Limite=200;
        char C=evt.getKeyChar(); 
        
        if(Character.isLowerCase(C))
        {
            String Cad=("" + C).toUpperCase(); 
            C=Cad.charAt(0);
            evt.setKeyChar(C);
        }
        
        if(TxtDireccion.getText().length()==Limite)
        {
            evt.consume();
        }
    }//GEN-LAST:event_TxtDireccionKeyTyped

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        edit=true;
    }//GEN-LAST:event_formWindowActivated

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
       
        this.setModal(false);
        //**instaciamos la Clase DatosCliente a dc
        Ctrl_Usuario du = new Ctrl_Usuario();
        try {
            du.imprimir();
           
        } catch (SQLException | JRException e) {
           Logger.getLogger(FrmUsuario.class.getName()).log(Level.SEVERE, null, e);
        }
        this.setModal(true);
       
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
         Buscar();       
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (jTable1.getSelectedRow()!=-1)  
          llevardatos();
    }//GEN-LAST:event_jTable1MouseClicked

    private void txtBuscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarFocusGained
        txtBuscar.setBackground(Color.YELLOW);
        txtBuscar.selectAll();
    }//GEN-LAST:event_txtBuscarFocusGained

    private void txtBuscarInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtBuscarInputMethodTextChanged
       // Buscar();
    }//GEN-LAST:event_txtBuscarInputMethodTextChanged

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
         Buscar();
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void jTabbedPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane2MouseClicked
      if(jTabbedPane2.getSelectedIndex()==1){
          txtBuscar.requestFocus();
      }else
      {
         txtcedula.requestFocus();
      }
    }//GEN-LAST:event_jTabbedPane2MouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
       confirmaeliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
       if(evt.getKeyCode()==KeyEvent.VK_ENTER || evt.getKeyCode()==KeyEvent.VK_DOWN){  
         if(jTable1.getRowCount()!=0){
                jTable1.setRowSelectionInterval(0, 0);
                jTable1.setColumnSelectionInterval(0, 5);               
                jTable1.requestFocus();
            }
      }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER) {    
           if (jTable1.getSelectedRow()!=-1) 
              llevardatos();
        }
    }//GEN-LAST:event_jTable1KeyPressed

    private void jTable1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyTyped
        if (jTable1.getSelectedRow()!=-1){
            evt.consume();
            llevardatos();
        } 
           
    }//GEN-LAST:event_jTable1KeyTyped

    private void btnGuardarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGuardarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER) { 
            btnGuardar.doClick();
        }
    }//GEN-LAST:event_btnGuardarKeyPressed

    private void btnCancelarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCancelarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER) { 
            btnCancelar.doClick();
        }
    }//GEN-LAST:event_btnCancelarKeyPressed

    private void btnEliminarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnEliminarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER) { 
            btnEliminar.doClick();
        }
    }//GEN-LAST:event_btnEliminarKeyPressed

    private void btnSalirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSalirKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER) { 
            btnSalir.doClick();
        }
    }//GEN-LAST:event_btnSalirKeyPressed

    private void btnBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBuscarKeyPressed
         if(evt.getKeyCode()==KeyEvent.VK_ENTER) { 
            btnBuscar.doClick();
        }
    }//GEN-LAST:event_btnBuscarKeyPressed

    private void btnImprimirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnImprimirKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER) { 
            btnImprimir.doClick();
        }
    }//GEN-LAST:event_btnImprimirKeyPressed

    private void TxtNombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtNombreFocusGained
        TxtNombre.setBackground(Color.YELLOW);
        TxtNombre.selectAll();
    }//GEN-LAST:event_TxtNombreFocusGained

    private void TxtNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtNombreFocusLost
        TxtNombre.setBackground(Color.WHITE);
    }//GEN-LAST:event_TxtNombreFocusLost

    private void TxtNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtNombreKeyPressed
       if(evt.getKeyCode()==KeyEvent.VK_ENTER) { 
           TxtApellido.requestFocus();
       }
    }//GEN-LAST:event_TxtNombreKeyPressed

    private void TxtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtNombreKeyTyped
        // TODO add your handling code here:
         int Limite=45;
        char C=evt.getKeyChar(); 
        
        if(Character.isLowerCase(C))
        {
            String Cad=("" + C).toUpperCase(); 
            C=Cad.charAt(0);
            evt.setKeyChar(C);
        }
        if(TxtNombre.getText().length()==Limite)
        {
            evt.consume();
        }
    }//GEN-LAST:event_TxtNombreKeyTyped

    private void txtUsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER) { 
             txtuserpassw.requestFocus();
        }
    }//GEN-LAST:event_txtUsernameKeyPressed

    private void txtuserpasswKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtuserpasswKeyPressed
         if(evt.getKeyCode()==KeyEvent.VK_ENTER) { 
            txtcorreo.requestFocus();
         }
    }//GEN-LAST:event_txtuserpasswKeyPressed

    private void txtuserpasswFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtuserpasswFocusLost
       txtuserpassw.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtuserpasswFocusLost

    private void txtuserpasswFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtuserpasswFocusGained
        txtuserpassw.setBackground(Color.YELLOW);
        txtuserpassw.selectAll();
    }//GEN-LAST:event_txtuserpasswFocusGained

    private void txtUsernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsernameFocusLost
        txtUsername.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtUsernameFocusLost

    private void txtUsernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsernameFocusGained
       txtUsername.setBackground(Color.YELLOW);
       txtUsername.selectAll();
    }//GEN-LAST:event_txtUsernameFocusGained

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        
    }//GEN-LAST:event_jPanel1MouseClicked

    private void masculinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masculinoActionPerformed
       xsexo="M";
    }//GEN-LAST:event_masculinoActionPerformed

    private void femeninoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_femeninoActionPerformed
       xsexo="F";
    }//GEN-LAST:event_femeninoActionPerformed

    private void masculinoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masculinoMouseClicked
      xsexo="M";
    }//GEN-LAST:event_masculinoMouseClicked

    private void femeninoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_femeninoMouseClicked
       xsexo="F";
    }//GEN-LAST:event_femeninoMouseClicked

    private void adminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminMouseClicked
         xnivel="A";
    }//GEN-LAST:event_adminMouseClicked

    private void adminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminActionPerformed
         xnivel="A";
    }//GEN-LAST:event_adminActionPerformed

    private void supervisorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supervisorMouseClicked
        xnivel="S";
    }//GEN-LAST:event_supervisorMouseClicked

    private void supervisorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supervisorActionPerformed
         xnivel="S";
    }//GEN-LAST:event_supervisorActionPerformed

    private void userMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userMouseClicked
        xnivel="U";
    }//GEN-LAST:event_userMouseClicked

    private void userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userActionPerformed
         xnivel="U";
    }//GEN-LAST:event_userActionPerformed

    private void txtcorreoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcorreoKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER) { 
            btnGuardar.requestFocus();
            txtcorreo.setBackground(Color.WHITE);
        }
        
    }//GEN-LAST:event_txtcorreoKeyPressed

    private void txtcorreoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcorreoFocusLost
         txtcorreo.setBackground(Color.WHITE);
        
    }//GEN-LAST:event_txtcorreoFocusLost

    private void txtcorreoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcorreoFocusGained
        txtcorreo.setBackground(Color.YELLOW);
        txtcorreo.selectAll();
    }//GEN-LAST:event_txtcorreoFocusGained

    private void txtcedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcedulaKeyTyped
        char c = evt.getKeyChar();
        if(txtcedula.getText().length()==11 || (c<'0' || c>'9')) evt.consume(); //permitir solo numeros

    }//GEN-LAST:event_txtcedulaKeyTyped

    private void txtcedulaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcedulaFocusGained
          txtcedula.setBackground(Color.YELLOW);
          txtcedula.selectAll();
    }//GEN-LAST:event_txtcedulaFocusGained

    private void txtcedulaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcedulaFocusLost
        txtcedula.setBackground(Color.white);
    }//GEN-LAST:event_txtcedulaFocusLost

    private void txtcedulaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcedulaKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER) { 
            
             Ctrl_Usuario usr = new Ctrl_Usuario();
            ArrayList<Usuario> xcampo=usr.Traerdatos(txtcedula.getText());
            
                                  
            TxtNombre.requestFocus();
        }
    }//GEN-LAST:event_txtcedulaKeyPressed

    private void tablaLocalizadoresFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tablaLocalizadoresFocusGained
        if (tablaLocalizadores.getRowCount()!=0)
        llevardatoslocalizar();
    }//GEN-LAST:event_tablaLocalizadoresFocusGained

    private void tablaLocalizadoresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaLocalizadoresKeyPressed

    }//GEN-LAST:event_tablaLocalizadoresKeyPressed

    private void tablaLocalizadoresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaLocalizadoresKeyTyped
        if(evt.getKeyCode()==KeyEvent.VK_UP || evt.getKeyCode()==KeyEvent.VK_DOWN){
            if(tablaLocalizadores.getRowCount()!=0)
            llevardatoslocalizar();
        }
        txtnumero.requestFocus();
    }//GEN-LAST:event_tablaLocalizadoresKeyTyped

    private void cmbSeleccionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbSeleccionMouseClicked

    }//GEN-LAST:event_cmbSeleccionMouseClicked

    private void cmbSeleccionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSeleccionItemStateChanged
        txtnumero.requestFocus();
    }//GEN-LAST:event_cmbSeleccionItemStateChanged

    private void cmbSeleccionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbSeleccionFocusGained
        cmbSeleccion.setBackground(Color.YELLOW);
    }//GEN-LAST:event_cmbSeleccionFocusGained

    private void txtnumeroFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtnumeroFocusGained
        txtnumero.setBackground(Color.YELLOW);
        txtnumero.selectAll();
    }//GEN-LAST:event_txtnumeroFocusGained

    private void txtnumeroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtnumeroFocusLost
        txtnumero.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtnumeroFocusLost

    private void txtnumeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnumeroKeyTyped
        char c = evt.getKeyChar();
        if(txtnumero.getText().length()==10 || (c<'0' || c>'9')) evt.consume(); //permitir solo numeros
    }//GEN-LAST:event_txtnumeroKeyTyped

    private void btnborrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnborrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnborrarActionPerformed

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameActionPerformed

    private void btnagregaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnagregaActionPerformed

    private void btnElegirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnElegirActionPerformed
        //**********
        JFileChooser file = new JFileChooser();
        //Indicamos las extensiones que desamos filtrar
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPEG file","jpg", "jpeg","png");
        //pasamos el filtro al archivo
        file.setFileFilter(filtro);
        //*******************
        int opcion = file.showOpenDialog(this);
        if(opcion==JFileChooser.APPROVE_OPTION){
            /*
            comparamos el tamano de la  imagen para segurarnos que puede ser almacenada en la DB
            */
            if (file.getSelectedFile().length()>65535){
                  JOptionPane.showMessageDialog(rootPane,"\n"+
                          "El Archivo Excede el tamaño pemitido que es :[65535 bytes]"+"\n y tiene : ["
                          + file.getSelectedFile().length() +" bytes] \n"
                          +"Sobrepasando con : ["+(file.getSelectedFile().length()-65535)+" bytes]");
            }else{
                 /*
                 Llamamos el metodo mostrar imagen le pasamos el archivo y el componente JLabel
                */
                mostrarimagen(file.getSelectedFile(),lblimagen);
            }
        }

    }//GEN-LAST:event_btnElegirActionPerformed
    
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
            //java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            //java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            //java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            //java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
      /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FrmUsuario dialog = new FrmUsuario(new javax.swing.JFrame(), true);
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
    private javax.swing.ButtonGroup GrupoNivel;
    private javax.swing.ButtonGroup GrupoSexo;
    private javax.swing.JTextField TxtApellido;
    private javax.swing.JTextField TxtDireccion;
    private javax.swing.JTextField TxtNombre;
    private javax.swing.JRadioButton admin;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnElegir;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnactualiza;
    private javax.swing.JButton btnagrega;
    private javax.swing.JButton btnborrar;
    private javax.swing.JComboBox<String> cmbSeleccion;
    private javax.swing.JRadioButton femenino;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblimagen;
    private javax.swing.JRadioButton masculino;
    private javax.swing.JRadioButton supervisor;
    private javax.swing.JTable tablaLocalizadores;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtUsername;
    private javax.swing.JTextField txtcedula;
    private javax.swing.JTextField txtcorreo;
    private javax.swing.JTextField txtnumero;
    private javax.swing.JPasswordField txtuserpassw;
    private javax.swing.JRadioButton user;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
      
        if(e.getKeyCode()==KeyEvent.VK_F3){
          jTabbedPane2.setSelectedIndex(1);
          txtBuscar.requestFocus();
          jTabbedPane2.setSelectedIndex(1);
          if(jTable1.getRowCount()!=0){
                jTable1.setRowSelectionInterval(0, 0);
                jTable1.setColumnSelectionInterval(0, 5);
          }      
        }
      
        if(e.getKeyCode()==KeyEvent.VK_F2){
          jTabbedPane2.setSelectedIndex(0);
          TxtNombre.requestFocus();
        }
            
        if(e.getKeyCode()==KeyEvent.VK_F4){
          btnGuardar.doClick();
        }
     
        if(e.getKeyCode()==KeyEvent.VK_F6){
          btnCancelar.doClick();
        }
        if(e.getKeyCode()==KeyEvent.VK_F8){
          btnEliminar.doClick();
        }
      
        if(e.getKeyCode()==KeyEvent.VK_F10){
          btnSalir.doClick();
        }
      
        if(e.getKeyCode()==KeyEvent.VK_F5){
          btnBuscar.doClick();
        }
      
        if(e.getKeyCode()==KeyEvent.VK_F12){
          btnImprimir.doClick();
        }
        if(e.getKeyCode()==KeyEvent.VK_F11)
        {
            if(jTable1.getRowCount()!=0){
                jTable1.setRowSelectionInterval(0, 0);
                jTable1.setColumnSelectionInterval(0, 5);
                jTabbedPane2.setSelectedIndex(1);
                jTable1.requestFocus();
            }
        }  
        
        if(e.getKeyCode()==KeyEvent.VK_F7)
        {
            cmbSeleccion.requestFocus();
            cmbSeleccion.setPopupVisible(true);
        }
         if(e.getKeyCode()==KeyEvent.VK_F1){
            if(tablaLocalizadores.getRowCount()!=0){
                tablaLocalizadores.setRowSelectionInterval(0, 0);
                tablaLocalizadores.setColumnSelectionInterval(0, 1);
                tablaLocalizadores.requestFocus();
            } 
         }
       /*
         if(e.getKeyCode()==KeyEvent.VK_F9)
        {
            cmbEmpresa.requestFocus();
            cmbEmpresa.setPopupVisible(true);
        } 
        */
    }

    @Override
    public void keyReleased(KeyEvent e) {
       
    }
}
