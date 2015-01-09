/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rentacar;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Dario
 */
public class Helper {
    
    public static boolean isNum(String strNum) {
        boolean ret = true;
        try {
            Double.parseDouble(strNum);
        }catch (NumberFormatException e) {
            ret = false;
        }
        return ret;
    }
    
    public JTextArea createTextAreaFitToText(String message, Dimension minimalSize) throws BadLocationException{

        JTextArea aMessagePanel = new JTextArea();
        aMessagePanel.setText(message);

        /*for modelToView to work, the text area has to be sized. It doesn't matter if it's visible or not.*/
        aMessagePanel.setPreferredSize(minimalSize);
        aMessagePanel.setSize(minimalSize);            

        Rectangle r = aMessagePanel.modelToView(aMessagePanel.getDocument().getLength()); 

        Dimension d = new Dimension(minimalSize.width, r.y + r.height);
        aMessagePanel.setPreferredSize(d);
        return aMessagePanel;

    }
    
    
    public void tableToExcel(JTable table, String file){
        try {
                TableModel model = table.getModel();

                FileOutputStream fos = new FileOutputStream(file);
                OutputStreamWriter output = new OutputStreamWriter(fos, "windows-1250");
                for(int i = 0; i <model.getColumnCount(); i++){
                    output.write(model.getColumnName(i) + "\t");
                }

                output.write("\n");

                for(int k=0;k<model.getRowCount();k++) {
                    for(int j=0;j<model.getColumnCount();j++) {
                        output.write(model.getValueAt(k,j).toString()+"\t");
                    }
                    output.write("\n");
                }
                output.close();
           }
            catch(Exception e) {
                e.printStackTrace();
           }
    }
    
    public void adjustToHeaderCell(JTable table_view){
        for(int i=0;i<table_view.getColumnCount();i++){
          DefaultTableColumnModel colModel = (DefaultTableColumnModel) table_view.getColumnModel();
          TableColumn col = colModel.getColumn(i);
          int width = 0;

          TableCellRenderer renderer = col.getHeaderRenderer();
          if (renderer == null) {
            renderer = table_view.getTableHeader().getDefaultRenderer();
          }
          Component comp = renderer.getTableCellRendererComponent(table_view, col.getHeaderValue(), false,
              false, 0, 0);
          width = comp.getPreferredSize().width;
          col.setPreferredWidth(width+2);
        }
    }
    
    public static void adjustColumnSizes(JTable table, int column, int margin) {
        DefaultTableColumnModel colModel = (DefaultTableColumnModel) table.getColumnModel();
        TableColumn col = colModel.getColumn(column);
        int width;

        TableCellRenderer renderer = col.getHeaderRenderer();
        if (renderer == null) {
            renderer = table.getTableHeader().getDefaultRenderer();
        }
        JLabel comp = (JLabel) renderer.getTableCellRendererComponent(
                table, col.getHeaderValue(), false, false, 0, 0);
        width = comp.getPreferredSize().width;

        for (int r = 0; r < table.getRowCount(); r++) {
            renderer = table.getCellRenderer(r, column);
            comp = (JLabel) renderer.getTableCellRendererComponent(
                    table, table.getValueAt(r, column), false, false, r, column);
            int currentWidth = comp.getPreferredSize().width;
            width = Math.max(width, currentWidth);
        }

        width += 2 * margin;

        col.setPreferredWidth(width);
    }

}

class JTextFieldLimit extends PlainDocument {
  private int limit;
  JTextFieldLimit(int limit) {
    super();
    this.limit = limit;
  }

  JTextFieldLimit(int limit, boolean upper) {
    super();
    this.limit = limit;
  }

  public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
    if (str == null)
      return;

    if ((getLength() + str.length()) <= limit) {
      super.insertString(offset, str, attr);
    }
  }
}

class JTableButtonRenderer implements TableCellRenderer {        
    @Override public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JButton button = (JButton)value;
        return button;  
    }
}