/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author jcontreras
 */
public class ModeloGrid extends DefaultTableCellRenderer {
    
     public Component getTableCellRendererComponent(JTable Table,Object value, boolean isSelected,boolean hasFocus,int row, int column)
  {
      //JLabel cell=(JLabel) super.getTableCellRendererComponent(Table, value, isSelected, hasFocus, row, column);
      JLabel cell=(JLabel) super.getTableCellRendererComponent(Table, value, isSelected, hasFocus, row, column);
      if(row%2==0)
      {
          cell.setBackground(new Color(255,255,255));
          //cell.setForeground(new Color(0,0,0));
          
      }
      else
      {
          cell.setBackground(new Color(155,204,255));
         // cell.setForeground(new Color(0,0,0));
      }
      
      return cell;
      
          
  }
     
     
    public boolean isCellEditable (int column)
   {
       // Aquí devolvemos true o false según queramos que una celda
       // identificada por fila,columna (row,column), sea o no editable
       if (column == 3 && column==1 && column==2 )
          return true;
       return false;
   }
    
}
