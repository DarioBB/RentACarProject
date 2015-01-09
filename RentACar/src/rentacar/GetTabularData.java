/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rentacar;

/**
 *
 * @author Dario
 */

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;


public class GetTabularData {
    
 Connection connection;
 DatabaseManager m;
 JPanel p;
 String content;
 String sql_table;
 String sql_command;
 Statement statement;
 JTable table;
 
 public GetTabularData(){
    m = new DatabaseManager();
    connection = m.getConnection();
    m.createTables();
    JTable table;
 }
 
 public GetTabularData(String sql_table){
    m = new DatabaseManager();
    connection = m.getConnection();
    m.createTables();
    sql_table = sql_table;
    JTable table;
 }
 
 public void setTable(){
     this.sql_table = sql_table;
 }
 public String getTable(){
     return sql_table;
 }
 
 public JTable getTabularData(String tablica) {

    DefaultTableModel dm = new DefaultTableModel();
    Vector columnNames = new Vector();
    Vector data = new Vector();
            
        content = "";
        sql_command = "select registarska_oznaka as `Registarska oznaka`, "
                + "marka as `Marka`, oznaka as `Oznaka`, snaga_motora as `Snaga motora`, "
                + "boja as `Boja`, godina_proizvodnje as `Godina proizvodnje`, "
                + "cijena_pri_kupnji as `Cijena pri kupnji` "
                + "from "+tablica+" order by id desc";
        try {
            statement = connection.createStatement();
            //ResultSet rs = statement.executeQuery(sql_command);
  
            ResultSet resultSet = statement.executeQuery(sql_command);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columns = metaData.getColumnCount();
            for (int i = 1; i <= columns; i++) {
                columnNames.addElement(metaData.getColumnLabel(i));
            }
            columnNames.addElement("Spremi");
            columnNames.addElement("Obriši");
            while (resultSet.next()) {
                Vector row = new Vector(columns);
                for (int i = 1; i <= columns; i++) {
                    row.addElement(resultSet.getObject(i));
                }
                row.addElement("Spremi");
                row.addElement("Obriši");
                data.addElement(row);
            }
            resultSet.close();
            statement.close();
   
        } catch (SQLException ex) {
            System.out.println("Upit se nije uspješno izvršio.");
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            System.out.println("Broj pogrešnog formata.");
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    table = new JTable(data, columnNames) {
        @Override
        public boolean isCellEditable(int row, int col) {
            switch(col){
                case 0:
                    return false;
                default:
                     return true;
            }

        }
    };
    TableColumn column;
    for (int i = 0; i < table.getColumnCount(); i++) {
        column = table.getColumnModel().getColumn(i);
        table.getColumnModel().getColumn(i).setMinWidth(100);
        column.setMaxWidth(250);
    }

    table.setRowHeight(30);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            
    Helper h = new Helper();
    h.adjustToHeaderCell(table);
    
    table.getColumn("Spremi").setCellRenderer(new ButtonRenderer());
    table.getColumn("Spremi").setCellEditor(
        new ButtonEditor(new JCheckBox()));
    table.getColumn("Obriši").setCellRenderer(new ButtonRenderer());
    table.getColumn("Obriši").setCellEditor(
        new ButtonEditor(new JCheckBox()));

    getIdWithMouseListener(table, tablica);
    
    return table;
  }
 
   public void getIdWithMouseListener(final JTable table, final String tablica){
    table.addMouseListener(new MouseAdapter()
    {
      @Override
      public void mouseClicked(final MouseEvent e)
      {
        if (e.getClickCount() == 1)
        {
          final JTable target = (JTable)e.getSource();
          final int row = target.getSelectedRow();
          final int column = target.getSelectedColumn();
          //Cast to ur Object type
            String cl = target.getValueAt(row, 0).toString();
            String column_name = target.getColumnName(column);
            
            //String z = target.getValueAt(row, column).toString();
            //JOptionPane.showMessageDialog(null, cl);
            //JOptionPane.showMessageDialog(null, column_name);
          // TODO WHAT U WAAANT, ouhh
            if(column_name.equals("Obriši")){
                String message = "Da li želite obrisati ovu stavku?";
                String title = "Brisanje stavke";
                Object[] options = {"Da", "Ne"};
                int reply = JOptionPane.showOptionDialog(null, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, 
                  null, options, options[1]);
                if (reply == JOptionPane.YES_OPTION) {
                    m.deleteEntry(tablica, cl);
                    DefaultTableModel tbm = (DefaultTableModel) table.getModel();
                    int row2 = table.getSelectedRow();
                    tbm.removeRow(row2);
                    //JOptionPane.showMessageDialog(null, "Stavka je uspješno obrisana");
                }
                else {
                   //JOptionPane.showMessageDialog(null, "GOODBYE");
                   //System.exit(0);
                }
            }
            else if(column_name.equals("Spremi")){
                //JOptionPane.showMessageDialog(null, "Spremi!"+row+", "+column+", "+column_name+"");
                m.editEntry(tablica, cl, target);
                JOptionPane.showMessageDialog(null, "Stavka je uspješno promijenjena");
            }
         }
       }
    });
  }
 
}
/**
 * @version 1.0 11/09/98
 */

class ButtonRenderer extends JButton implements TableCellRenderer {

  public ButtonRenderer() {
    setOpaque(true);
  }

  public Component getTableCellRendererComponent(JTable table, Object value,
      boolean isSelected, boolean hasFocus, int row, int column) {
    if (isSelected) {
      setForeground(table.getSelectionForeground());
      setBackground(table.getSelectionBackground());
    } else {
      setForeground(table.getForeground());
      setBackground(UIManager.getColor("Brisanje.background"));
    }
    setText((value == null) ? "" : value.toString());
    return this;
  }
}

/**
 * @version 1.0 11/09/98
 */

class ButtonEditor extends DefaultCellEditor {
  protected JButton button;

  private String label;

  private boolean isPushed;

  public ButtonEditor(JCheckBox checkBox) {
    super(checkBox);
    button = new JButton();
    button.setOpaque(true);
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        fireEditingStopped();
      }
    });
  }

  public Component getTableCellEditorComponent(JTable table, Object value,
      boolean isSelected, int row, int column) {
    if (isSelected) {
      button.setForeground(table.getSelectionForeground());
      button.setBackground(table.getSelectionBackground());
    } else {
      button.setForeground(table.getForeground());
      button.setBackground(table.getBackground());
    }
    label = (value == null) ? "" : value.toString();
    button.setText(label);
    isPushed = true;
    return button;
  }

  public Object getCellEditorValue() {
    if (isPushed) {
      // 
      // 
      //JOptionPane.showMessageDialog(button, label+": Ouch!");
      // System.out.println(label + ": Ouch!");
      /*if(label.equals("Obriši")){
          GetTabularData g = new GetTabularData("automobili");
          JTable table_view;
          table_view = g.getTabularData("automobili");
            int index = table_view.getSelectedColumnCount();
            Object val = table_view.getValueAt(0, 0);
            JOptionPane.showMessageDialog(null, "val: "+index);
          //g.m.deleteEntry(g.getTable());
      }*/
      /*if(label.equals("Obriši")){
          GetTabularData g = new GetTabularData("automobili");
          JTable table_view = g.getTabularData("automobili");
          
          //getIdWithMouseListener(table_view);
      }*/
    }
    isPushed = false;
    return new String(label);
  }

  public boolean stopCellEditing() {
    isPushed = false;
    return super.stopCellEditing();
  }

  protected void fireEditingStopped() {
    super.fireEditingStopped();
  }
  
}

