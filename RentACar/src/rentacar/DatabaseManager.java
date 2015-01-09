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
import java.awt.Component;
import java.awt.Dimension;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.sql.*;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
/*import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;*/
public class DatabaseManager {
    
    public Connection connection;
    public Statement statement;
    public PreparedStatement prepared_statement;
    
    private static String _JDBC_DRIVER;
    private static String _USERNAME;
    private static String _PASSWORD;
    private String _DATABASE;
    private String _CONNECTION_STRING;
    
    private String sql_command;
    private String content;
    
    DatabaseManager(){
        connection = null;
        statement = null;
        _JDBC_DRIVER = "com.mysql.jdbc.Driver";
        _USERNAME = "root";
        _PASSWORD = "";
        _DATABASE = "rent_a_car";
        _CONNECTION_STRING = "jdbc:mysql://localhost/"+_DATABASE+"?useUnicode=true&characterEncoding=UTF-8\",\""+_USERNAME+"\",\""+_PASSWORD+"\"";
        
        System.out.println("Testiranje spoja na MySQL...");
        try {
            Class.forName(_JDBC_DRIVER);
            System.out.println("Driver za MySQL "+_JDBC_DRIVER+" je registrian i konekcije su omogućene ");
        } catch (ClassNotFoundException e){
            System.out.println("Ne postoji JDBC driver za MySQL na serveru ili vašem računalu.");
            e.printStackTrace();
        }
    }
    
    public Connection getConnection(){
        
        connection = null;
        
        try {
            //connection = DriverManager.getConnection("jdbc:mysql://localhost/"+_DATABASE+"?useUnicode=true&characterEncoding=UTF-8\",\""+_USERNAME+"\",\""+_PASSWORD+"\"");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/",_USERNAME,_PASSWORD);
        } catch (SQLException e){
            System.out.println("Konekcija se nije izvršila: provjerite output konzolu.");
        } finally {
            if(connection != null){
                System.out.println("Konekcija na bazu je uspješno ostvarena.");
            } else {
                System.out.println("Konekcija na bazu se nije izvršila: provjerite da li je vaša baza uopće postoji.");
            }
        }
        
        try {
            //e.printStackTrace();
            statement = connection.createStatement();
            sql_command = "CREATE DATABASE IF NOT EXISTS "+_DATABASE+"";
            //System.out.println(sql_command);
            try {
                statement.executeUpdate(sql_command);
                System.out.println("Baza "+_DATABASE+" je uspješno postavljena.");
                
                //createTables();
                        
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("SQL upit za kreiranje baze "+_DATABASE+" se nije izvršio");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Greška kod zadavanja SQL zahtjeva");
        }

        return connection;
        
    }
    
    public void createTables(){
        
        sql_command = "USE "+_DATABASE+"";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql_command);
        } catch (SQLException ex) {
            System.out.println("Spajanje na bazu "+_DATABASE+" nije uspjelo");
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        sql_command = "CREATE TABLE IF NOT EXISTS `automobili` (`id` int(10) unsigned NOT NULL AUTO_INCREMENT,`kreirano` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,`registarska_oznaka` varchar(12) NOT NULL,`marka` varchar(100) NOT NULL,`oznaka` varchar(100) NOT NULL,`snaga_motora` varchar(50) NOT NULL,`boja` varchar(50) NOT NULL,`godina_proizvodnje` varchar(10) NOT NULL,`cijena_pri_kupnji` double(12,2) NOT NULL,PRIMARY KEY (`id`)) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql_command);
        } catch (SQLException ex) {
            System.out.println("Kreiranje tablica nije uspjelo.");
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sql_command = "CREATE TABLE IF NOT EXISTS `prihodi` (`id` int(10) unsigned NOT NULL AUTO_INCREMENT,`kreirano` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,`registarska_oznaka` varchar(12) NOT NULL,`datum` date NOT NULL,`datum_vracanja` date NOT NULL,`osoba_tvrtka` varchar(255) NOT NULL,`km_pocetak` varchar(20) NOT NULL,`km_kraj` varchar(20) NOT NULL,`vrsta_prihoda` varchar(150) NOT NULL,`opis` varchar(255) NOT NULL,`iznos` double(12,2) NOT NULL,PRIMARY KEY (`id`)) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql_command);
        } catch (SQLException ex) {
            System.out.println("Kreiranje tablica nije uspjelo.");
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sql_command = "CREATE TABLE IF NOT EXISTS `troskovi` (`id` int(10) unsigned NOT NULL AUTO_INCREMENT,`kreirano` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,`registarska_oznaka` varchar(12) NOT NULL,`datum` date NOT NULL,`do_datuma` date NOT NULL,`osoba_tvrtka` varchar(255) NOT NULL,`km_pocetak` varchar(20) NOT NULL,`km_kraj` varchar(20) NOT NULL,`vrsta_troskova` varchar(150) NOT NULL,`opis` varchar(255) NOT NULL,`iznos` double(12,2) NOT NULL,PRIMARY KEY (`id`)) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql_command);
        } catch (SQLException ex) {
            System.out.println("Kreiranje tablica nije uspjelo.");
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertQuery(String table, Map<String, String> map){

        sql_command = "";
        //prepared_statement = connection.prepareStatement(sql_command);
        Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
        int i = 0;
        String z_dod;
        String string;
        Double cijena_double;
        Helper h = new Helper();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            z_dod = (i > 0) ? "," : "";
            string = entry.getValue();
            if( (entry.getKey().equals("cijena_pri_kupnji")) || (entry.getKey().equals("iznos")) ){
                //cijena_double = Double.parseDouble(string);
                if(h.isNum(string)){
                    //string
                }
                else 
                {
                    string = "0.00";
                }
                sql_command += ""+z_dod+entry.getKey()+"='"+string+"'";
            }
            else 
            {
                sql_command += ""+z_dod+entry.getKey()+"='"+string+"'";
            }
            //sql_command += ""+z_dod+entry.getKey()+"='"+entry.getValue()+"'";
            i++;
        }

        sql_command = "insert into "+table+" set "+sql_command+"";
        //System.out.println(sql_command);
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql_command);
        } catch (SQLException ex) {
            System.out.println("Punjenje tablice nije uspjelo.");
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            System.out.println("Broj pogrešnog formata.");
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public String[] getRegistrationNumbers(){
        int numrows = 0;
        sql_command = "select count(registarska_oznaka) as cnt "
            + "from automobili "
            + "where registarska_oznaka != '' "
            + "order by registarska_oznaka asc, id desc";
        //System.out.println(sql_command);
        try {
            statement = connection.createStatement();
            ResultSet rs2 = statement.executeQuery(sql_command);
            String count = "";
            while(rs2.next()){
                count = rs2.getString("cnt");
            }
            numrows = Integer.parseInt(count);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] content = new String[numrows+1];
        
        sql_command = "select registarska_oznaka "
                + "from automobili "
                + "where registarska_oznaka != '' "
                + "order by registarska_oznaka asc, id desc";
        //System.out.println(sql_command);
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql_command);
            content[0] = "";
            int i = 1;
            while(rs.next()){
                //System.out.println("reg oz: "+rs.getString("registarska_oznaka"));
                content[i] = rs.getString("registarska_oznaka");
                i++;
            }
            
        } catch (SQLException ex) {
            System.out.println("Upit se nije uspješno izvršio.");
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return content;
    }
    
    
    public JTable getReport(String year){
        JTable table_view = null;
        content = "";
        sql_command = "select DISTINCT automobili.registarska_oznaka, automobili.marka, "
                + "automobili.oznaka, automobili.snaga_motora, automobili.boja, automobili.cijena_pri_kupnji "
                + "from automobili "
                + "LEFT JOIN prihodi "
                + "ON automobili.registarska_oznaka = prihodi.registarska_oznaka "
                + "LEFT JOIN troskovi "
                + "ON automobili.registarska_oznaka = troskovi.registarska_oznaka "
                + "WHERE 1 = 1 "
                + "AND ( "
                + "((prihodi.datum LIKE '%"+year+"%') OR (prihodi.datum_vracanja LIKE '%"+year+"%')) "
                + "OR ((troskovi.datum LIKE '%"+year+"%') OR (troskovi.do_datuma LIKE '%"+year+"%')) "
                + ") "
                + "AND automobili.registarska_oznaka != '' "
                + "order by automobili.registarska_oznaka asc ";
        //System.out.println(sql_command);
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql_command);
            
            String[] columnNames = {"Automobil registracija","Opis","Kilometara tijekom godine","Stanje kilometara na kraju godine","Postotak neto prihoda s obzirom na kupovnu cijenu","Vanredni prihodi i troškovi"};
            int col_count = columnNames.length;
            
            DefaultTableModel dtm = new DefaultTableModel(columnNames, 0);
            dtm.setColumnCount(col_count);
            while(rs.next()){
                
                 sql_command = "SELECT IFNULL(SUM( (km_kraj - km_pocetak) ),0) AS kilometara_tokom_godine "
                    + "FROM `prihodi` "
                    + "WHERE 1 = 1 "
                    + "AND km_kraj != '' "
                    + "AND km_pocetak != ''"
                    + "AND ( "
                    + "((prihodi.datum LIKE '%"+year+"%') OR (prihodi.datum_vracanja LIKE '%"+year+"%')) "
                    + ") "
                    + "AND prihodi.registarska_oznaka = '"+rs.getString("registarska_oznaka")+"' "
                    + "";

                int kilometara_tokom_godine = 0;
                statement = connection.createStatement();
                ResultSet rs2 = statement.executeQuery(sql_command);  
                if (rs2.next()){
                    kilometara_tokom_godine = rs2.getInt("kilometara_tokom_godine");
                }
                
                sql_command = "SELECT IFNULL(MAX(km_kraj),0) AS kilometara_na_kraju_godine "
                    + "FROM `prihodi` "
                    + "WHERE 1 = 1 "
                    + "AND km_kraj != '' "
                    + "AND ( "
                    + "((prihodi.datum LIKE '%"+year+"%') OR (prihodi.datum_vracanja LIKE '%"+year+"%')) "
                    + ") "
                    + "AND prihodi.registarska_oznaka = '"+rs.getString("registarska_oznaka")+"'";
                //System.out.println(sql_command);
                int kilometara_na_kraju_godine = 0;
                statement = connection.createStatement();
                ResultSet rs3 = statement.executeQuery(sql_command);  
                if (rs3.next()){
                    kilometara_na_kraju_godine = rs3.getInt("kilometara_na_kraju_godine");
                }
                
                double automobili_cijena_pri_kupnji = rs.getDouble("cijena_pri_kupnji");
                
                sql_command = "select IFNULL(SUM(prihodi.iznos),0) as ukupni_prihodi "
                    + "from prihodi "
                    + "WHERE 1 = 1 "
                    + "AND ( ((prihodi.datum LIKE '%"+year+"%') OR (prihodi.datum_vracanja LIKE '%"+year+"%')) ) "
                    + "AND registarska_oznaka = '"+rs.getString("registarska_oznaka")+"' ";
                //System.out.println(sql_command);
                double ukupni_prihodi = 0;
                statement = connection.createStatement();
                ResultSet rs4 = statement.executeQuery(sql_command);  
                if (rs4.next()){
                    ukupni_prihodi = rs4.getDouble("ukupni_prihodi");
                }
                //System.out.println("Ukupni prihodi: "+ukupni_prihodi);
                
                sql_command = "select IFNULL(SUM(troskovi.iznos),0) as ukupni_troskovi "
                    + "from troskovi "
                    + "WHERE 1 = 1 AND ( ((troskovi.datum LIKE '%"+year+"%') OR (troskovi.do_datuma LIKE '%"+year+"%')) ) "
                    + "AND registarska_oznaka = '"+rs.getString("registarska_oznaka")+"' ";
                double ukupni_troskovi = 0;
                statement = connection.createStatement();
                ResultSet rs5 = statement.executeQuery(sql_command);  
                if (rs5.next()){
                    ukupni_troskovi = rs5.getDouble("ukupni_troskovi");
                }
                //System.out.println("Ukupni troškovi: "+ukupni_troskovi);
                
                double postotak_neto_prihoda_s_obzirom_na_kupovnu_cijenu = (ukupni_prihodi - ukupni_troskovi)/automobili_cijena_pri_kupnji;
                
                sql_command = "("
                   + "SELECT prihodi.opis as opis, prihodi.iznos as iznos, 'prihodi' as vrsta "
                   + "FROM prihodi "
                   + "WHERE prihodi.registarska_oznaka = '"+rs.getString("registarska_oznaka")+"' "
                   + "AND vrsta_prihoda LIKE '%vanredn%' "
                   + "AND ( "
                   + "(prihodi.datum LIKE '%"+year+"%') OR (prihodi.datum_vracanja LIKE '%"+year+"%')) "
                   + ") "
                   + "UNION "
                   + "( "
                   + "SELECT troskovi.opis as opis, troskovi.iznos as iznos, 'troškovi' as vrsta "
                   + "FROM troskovi "
                   + "where troskovi.registarska_oznaka = '"+rs.getString("registarska_oznaka")+"' "
                   + "AND vrsta_troskova LIKE '%vanredn%' "
                   + "AND ((troskovi.datum LIKE '%"+year+"%') OR (troskovi.do_datuma LIKE '%"+year+"%')) "
                   + ") "
                   + "ORDER BY 'prihodi' asc, 'troškovi' asc ";
                String vanredni_prihodi_i_troskovi = "";
                statement = connection.createStatement();
                ResultSet rs6 = statement.executeQuery(sql_command);  
                while(rs6.next()){
                    vanredni_prihodi_i_troskovi += "Vrsta: "+rs6.getString("vrsta")+"; opis: "+rs6.getString("opis")+" (iznos: "+rs6.getDouble("iznos")+"). ";
                }
                
                dtm.addRow(new Object[]{
                    rs.getString("registarska_oznaka"),
                    rs.getString("marka")+", "+rs.getString("oznaka")+", "+rs.getString("snaga_motora")+", "+rs.getString("boja"),
                    ""+kilometara_tokom_godine+"",
                    ""+kilometara_na_kraju_godine+"",
                    ""+postotak_neto_prihoda_s_obzirom_na_kupovnu_cijenu+"",
                    ""+vanredni_prihodi_i_troskovi+"",
                });
            }
            //content += "</table></body></html>";
            table_view = new JTable(dtm);
            /*table_view.setColumnSelectionAllowed(false);  
            table_view.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            for(int i = 0; i < col_count; i++){
                table_view.getColumnModel().getColumn(i).setPreferredWidth(400);
            }
            table_view.setRowHeight(50);
            table_view.getTableHeader().setPreferredSize(new Dimension(400, 50));*/
            
            for(int i = 0; i < col_count; i++){
                table_view.getColumnModel().getColumn(i).setMinWidth(200);
            }
            table_view.setRowHeight(30);
            table_view.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            //table_view.setFillsViewportHeight(true);

            Helper h = new Helper();
            h.adjustToHeaderCell(table_view);
            /*for (int i = 0; i < table_view.getColumnCount(); i++) {
                h.adjustColumnSizes(table_view, i, 2);
            }*/

            h.tableToExcel(table_view,"report_"+year+".xls");
            
        } catch (SQLException ex) {
            System.out.println("Upit se nije uspješno izvršio.");
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return table_view;
    }
    
    
    public void deleteEntry(String table, String id){
        sql_command = "delete from "+table+" where registarska_oznaka = '"+id+"' ";
        //System.out.println(sql_command);
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql_command);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editEntry(String table, String id, JTable target){
        
        int row = target.getSelectedRow();
        int column = target.getSelectedColumn();
        String fields[] = new String[column];
        for(int i = 0; i < column; i++){
            fields[i] = target.getValueAt(row, i).toString();
            //System.out.println(fields[i]);
        }
        sql_command = "update "+table+" set registarska_oznaka = '"+fields[0]+"', "
                + "marka = '"+fields[1]+"', oznaka = '"+fields[2]+"', "
                + "snaga_motora = '"+fields[3]+"', boja = '"+fields[4]+"', "
                + "godina_proizvodnje = '"+fields[5]+"', "
                + "cijena_pri_kupnji = '"+fields[6]+"' "
                + "where registarska_oznaka = '"+fields[0]+"' ";
        //System.out.println(sql_command);
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql_command);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public JTable getQuery_OLD(String table){
        //System.out.println(table);
        //JButton button_update = new JButton("Promijeni");
        //JButton button_delete = new JButton("Izbriši");
        JTable table_view = null;
        content = "";
        sql_command = "select * from "+table+" order by id desc";
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql_command);
            
            String[] columnNames = {"Registarska oznaka","Marka","Oznaka","Snaga motora","Boja","Godina proizvodnje","Cijena pri kupnji","Opcije","Brisanje"};
            int col_count = columnNames.length;
            
            DefaultTableModel dtm = new DefaultTableModel(columnNames, 0){
                @Override public Class<?> getColumnClass(int column) {
                  return getValueAt(0, column).getClass();
                }
              };

            dtm.setColumnCount(col_count);
            for (String x : columnNames)
            {
                //dtm.addColumn(x);
            }
            /*content += "<html><head>"
                    + "<style type='text/css'>"
                    + "table, td {border:1px solid black;}"
                    + "</style>"
                    + "</head><body><table width='600' bgcolor='white' valign='top'>";
            */
            while(rs.next()){
                //String registarska_oznaka = rs.getString("registarska_oznaka");
                //System.out.println("reg oz: "+registarska_oznaka);
                /*content += "<tr>"
                            + "<td>"+rs.getString("registarska_oznaka")+"</td>"
                            + "<td>"+rs.getString("marka")+"</td>"
                            + "<td>"+rs.getString("oznaka")+"</td>"
                            + "<td>"+rs.getString("snaga_motora")+"</td>"
                            + "<td>"+rs.getString("boja")+"</td>"
                            + "<td>"+rs.getString("godina_proizvodnje")+"</td>"
                            + "<td>"+rs.getDouble("cijena_pri_kupnji")+"</td>"
                            + "<td>"+button_update.getText()+"</td>"
                            + "<td>"+button_delete.getText()+"</td>"
                        + "</tr>";*/
                dtm.addRow(new Object[]{
                    rs.getString("registarska_oznaka"),
                    rs.getString("marka"),
                    rs.getString("oznaka"),
                    rs.getString("snaga_motora"),
                    rs.getString("boja"),
                    rs.getString("godina_proizvodnje"),
                    rs.getString("cijena_pri_kupnji"),
                    new Boolean(false),
                    new JButton("Obriši")
                });
            }
            //content += "</table></body></html>";
            table_view = new JTable(dtm);
            TableCellRenderer buttonRenderer = new JTableButtonRenderer();
            table_view.getColumn("Brisanje").setCellRenderer(buttonRenderer);

            //table_view.setSize(new Dimension(900, 800));
            for(int i = 0; i < columnNames.length; i++){
                table_view.getColumnModel().getColumn(i).setPreferredWidth(100);
            }
            
        } catch (SQLException ex) {
            System.out.println("Upit se nije uspješno izvršio.");
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            System.out.println("Broj pogrešnog formata.");
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return table_view;
            
    }
    
    /*public void insertQuery(String table, Map<String, String> map) throws SQLException, NumberFormatException {
        try {
            sql_command = "";
            Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
            int i = 0;
            String z_dod;
            String string;
            Double cijena_double;
            while (entries.hasNext()) {
                Map.Entry<String, String> entry = entries.next();
                //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                z_dod = (i > 0) ? "," : "";
                string = entry.getValue();
                if(entry.getKey().equals("cijena_pri_kupnji")){
                    cijena_double = Double.parseDouble(string);
                    sql_command += ""+z_dod+entry.getKey()+"='"+cijena_double+"'";
                }
                else 
                {
                    sql_command += ""+z_dod+entry.getKey()+"='"+entry.getValue()+"'";
                }
                //sql_command += ""+z_dod+entry.getKey()+"='"+entry.getValue()+"'";
                i++;
            }

            sql_command = "insert into "+table+" set "+sql_command+"";
            //System.out.println(sql_command);
            //try {
                statement = connection.createStatement();
                statement.executeUpdate(sql_command);
            //} 

        } catch (NumberFormatException ex) {
            System.out.println("Broj pogrešnog formata.");
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.out.println("Punjenje tablice nije uspjelo.");
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }*/
    
}
