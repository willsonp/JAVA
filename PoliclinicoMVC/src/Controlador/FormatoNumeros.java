/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

//mport Inventario.Formularios.FrmArticulos;
//import static Inventario.Formularios.FrmArticulos.TxtCosto;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Jabnel Contreras
 */
public class FormatoNumeros {
    
    
   
    private int CuentaString(JTextField CajaTexto)
    { 
        int Cuenta=0;
        int CadenaString=0;
        String Cara=CajaTexto.getText();
        char Numerico;
       
        
        if(CajaTexto.getText().length()!=0)
        {
            
          CadenaString=CajaTexto.getText().length();
          for(int Valor=0; Valor<CadenaString; Valor++)
          {
               Numerico=Cara.charAt(Valor);
               
               if(!Character.isDigit(Numerico) &&(Numerico!='.') && (Numerico!=','))
               {
                   Cuenta++;
               }
                     
          }
          return Cuenta;
        }
        
        
        return 0;
        
    }
    
     public boolean isNumerico(JTextField CajaTexto)
    {
      char Caracter;
       

     
      if(CajaTexto.getText().length()!=0)
        {
          String Cara=CajaTexto.getText();
          char Numerico;
          Numerico=Cara.charAt(0);
          try {
              Integer.parseInt(String.valueOf(Numerico));
             return true;
          } catch (NumberFormatException ex){
              return false;
          }
          
        }
        return false;
      
        
    }
     
    public double ObtenerNumero(JTextField CajaTexto)  
            
    {
        boolean IsNumerico = false;
        
        if(CajaTexto.getText().length()!=0)
        {
          String Cara2=CajaTexto.getText();
          char Numerico;
          Numerico=Cara2.charAt(0);
          try {
              //Integer.parseInt(String.valueOf(Numerico));
              //IsNumerico=true;
              IsNumerico = !Character.isLetter(Numerico);
                     
          } catch (NumberFormatException ex){
              //IsNumerico=false;
              JOptionPane.showMessageDialog(null, ex);
          }
                  
        
        if(IsNumerico==true)
        {
        
           
           DecimalFormat Decimal=new DecimalFormat("#,###.##");
           String Valor=CajaTexto.getText();
            try { 
                double Numeros = Decimal.parse(Valor).doubleValue();
                return Numeros;
                
            } catch (ParseException ex) {
                //Logger.getLogger(FormatoNumeros.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error En Datos Numericos");
            }
        }
        else
        {
            try {
                int Valor=CuentaString(CajaTexto);
                int Cadena=CajaTexto.getText().length();
                String Cara=CajaTexto.getText();
                String letra="";
                for(int Cuenta1=Valor; Cuenta1<Cadena; Cuenta1++)
                {
                    letra=letra + Cara.substring(Cuenta1, Cuenta1+1);
                    
                }
                DecimalFormat decimales=new DecimalFormat("#,###.##");
                double Numeros = decimales.parse(letra).doubleValue();
                return Numeros;
            } catch (ParseException ex) {
              //  Logger.getLogger(FormatoNumeros.class.getName()).log(Level.SEVERE, null, ex);
               JOptionPane.showMessageDialog(null, "Error En Datos Numericos");
             //  return ;
            }
            
        }
        
       
        
    }
        return 0;
    }
    
        
    
 public void  FormatoNumeros(JTextField CajaTexto) 
    {
       
        String letra="";
        double Numeros=0.00;
        
        DecimalFormat MF=new DecimalFormat("#0.00");
        
        NumberFormat Formato=NumberFormat.getCurrencyInstance();
        
       if(CajaTexto.getText().length()!=0) 
             {
                  
        int Cadena=CajaTexto.getText().length();
        
     
          String Cara=CajaTexto.getText();
          char Numerico;
          Numerico=Cara.charAt(0);
          
         
        boolean IsNumerico = false;
        
          String Cara2=CajaTexto.getText();
          
          Numerico=Cara2.charAt(0);
          try {
              //Integer.parseInt(String.valueOf(Numerico));
              //IsNumerico=true;
              IsNumerico = !Character.isLetter(Numerico);
                     
          } catch (NumberFormatException ex){
              //IsNumerico=false;
              JOptionPane.showMessageDialog(null, ex);
          }
          
       if(IsNumerico==true)
                        
          {           
            try {
                DecimalFormat Decimal=new DecimalFormat("#,###.##");
                String Valor=CajaTexto.getText();
                Numeros=Decimal.parse(Valor).doubleValue();
                CajaTexto.setText(String.valueOf(Numeros));
                Valor=CajaTexto.getText();
                CajaTexto.setText(Formato.format(Double.parseDouble(Valor)));
            } catch (ParseException ex) {
               // Logger.getLogger(FrmArticulos.class.getName()).log(Level.SEVERE, null, ex);
               JOptionPane.showMessageDialog(null, "Error En Datos Numericos");
            }
          }
          else
     
          {
                try { 
                    /* Este codigo es para obtener el valor sin el string RD$ */
                    int Valor=CuentaString(CajaTexto);
                    
                    for(int Cuenta2=Valor; Cuenta2<Cadena; Cuenta2++)
                    {
                        letra=letra + Cara.substring(Cuenta2, Cuenta2+1);
                        
                    }
                    DecimalFormat decimales=new DecimalFormat("#,###.##");
                    Numeros=decimales.parse(letra).doubleValue();
                    CajaTexto.setText(" ");
                    CajaTexto.setText(String.valueOf(Numeros));
                    letra=CajaTexto.getText();
                    CajaTexto.setText(Formato.format(Double.parseDouble(letra)));
                }
           catch (ParseException ex) {
                   // Logger.getLogger(FrmArticulos.class.getName()).log(Level.SEVERE, null, ex);
                   JOptionPane.showMessageDialog(null, "Error En valor Numerico");
                   CajaTexto.requestFocus();
                }
          }
    }
  }
}
