/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rentacar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RentACar extends JFrame {
    
    private JButton button_prikazi_automobile;
    private JButton button_dodaj_automobile;
    private JButton button_prikazi_prihode;
    private JButton button_dodaj_prihode;
    private JButton button_prikazi_troskove;
    private JButton button_dodaj_troskove;
    
    private JComboBox combo_godina_izvjesca;
    private String[] years = {"2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"};
    private JButton button_prikazi_izvjesce;
    
    private JLabel rezultati;
    private JLabel podnozje_poruka;
    
    private JLabel label_registarska_oznaka;
    private JTextField registarska_oznaka;
    
    private JLabel label_marka;
    private JTextField marka;
    
    private JLabel label_oznaka;
    private JTextField oznaka;
    
    private JLabel label_snaga_motora;
    private JTextField snaga_motora;
    
    private JLabel label_boja;
    private JTextField boja;
    
    private JLabel label_godina_proizvodnje;
    private JTextField godina_proizvodnje;
    
    private JLabel label_cijena_pri_kupnji;
    private JTextField cijena_pri_kupnji;
    
    private String[] registration_numbers;
    private JLabel label_combo_registarska_oznaka;
    private JComboBox combo_registarska_oznaka;
    private JLabel label_datum;
    private JTextField datum;
    private JLabel label_datum_vracanja;
    private JTextField datum_vracanja;
    private JLabel label_osoba_tvrtka;
    private JTextArea osoba_tvrtka;
    private JLabel label_km_pocetak;
    private JTextField km_pocetak;
    private JLabel label_km_kraj;
    private JTextField km_kraj;
    private JLabel label_vrsta_prihoda;
    private JTextField vrsta_prihoda;
    private JLabel label_opis;
    private JTextArea opis;
    private JLabel label_iznos;
    private JTextField iznos;
    JScrollPane scrollPane_textarea;
    
    private JLabel label_do_datuma;
    private JTextField do_datuma;
    private JLabel label_vrsta_troska;
    private JTextField vrsta_troska;
    
    private String html_dod_1;
    private String html_dod_2;
    private JLabel prazni_redak;
    private JLabel prazni_redak_2;
    private JLabel poruka_spremanje;
    
    private JButton spremi_automobile;
    private JButton spremi_prihode;
    private JButton spremi_troskove;
    private JButton button_prikazi_excell_izvjesce;
    private JTable table_view;
    
    Container c;
    private JPanel menu;
    private JPanel sredisnji_dio;
    private JPanel sredisnji_dio2;
    private JPanel podnozje;
    
    private JPanel menu_container;
    
    private JScrollPane scroller;
    
    public Connection connection;
    DatabaseManager m;
    
    GetTabularData tabular_data;
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        RentACar m = new RentACar();
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m.pack();
        m.setSize(860, 680);
        m.setVisible(true);
        //JFrame f = new JFrame();
        
    }
    
    public RentACar(){
        super("Rent A Car aplikacija");
        
        //connection = null;
        m = new DatabaseManager();
        connection = m.getConnection();
        m.createTables();
        tabular_data = new GetTabularData();
        
        //Container c;
        c = getContentPane();
        c.setLayout(new BorderLayout());
        //c.setBackground(Color.WHITE);
        
        menu = new JPanel(new GridLayout(0,1,3,3));
        sredisnji_dio = new JPanel(new FlowLayout());
        sredisnji_dio2 = new JPanel(new FlowLayout());
        podnozje = new JPanel(new FlowLayout());
        
        //menu.setBackground(Color.WHITE);
        sredisnji_dio.setBackground(Color.LIGHT_GRAY);
        sredisnji_dio2.setBackground(Color.LIGHT_GRAY);
        
        menu_container = new JPanel(new BorderLayout());
        //menu_container.setBackground(Color.WHITE);
        
        scroller = new JScrollPane();
        //sredisnji_dio.setLayout(null);
        sredisnji_dio.setLayout(new FlowLayout(FlowLayout.LEFT));
        sredisnji_dio2.setLayout(new FlowLayout(FlowLayout.LEFT));
        menu_container.add(menu, BorderLayout.NORTH);
        c.add(menu_container, BorderLayout.WEST);
        c.add(sredisnji_dio, BorderLayout.CENTER);
        c.add(podnozje, BorderLayout.SOUTH);
        
        //setLayout(new FlowLayout(FlowLayout.LEFT));
        button_prikazi_automobile = new JButton("Prikaži automobile");
        button_dodaj_automobile = new JButton("Dodaj automobile");
        button_prikazi_prihode = new JButton("Prikaži prihode");
        button_dodaj_prihode = new JButton("Dodaj prihode");
        button_dodaj_troskove = new JButton("Dodaj troškove");
        button_prikazi_troskove = new JButton("Prikaži troškove");
        combo_godina_izvjesca = new JComboBox(years);
        combo_godina_izvjesca.setSelectedItem("2014");
        button_prikazi_izvjesce = new JButton("Prikaži izvješće");
        rezultati = new JLabel("<html><br />Poštovani, <br /><br />dobrodošli na aplikaciju RentACar.<br /> Za rad sa aplikacijom molimo odaberite željenu stavku menu-a. <br /><br />Aplikacija omogućava:<br /><br />- dodavanje, pregled, brisanje i ažuriranje unešenih automobila<br />- dodavanje, pregled, brisanje i ažuriranje unešenih troškova<br />- dodavanje, pregled, brisanje i ažuriranje unešenih prihoda<br />- generiranje izvješća za svaku godinu</html>");
        podnozje_poruka = new JLabel("Dario Benšić - izrada za LIBUSOFT CICOM d.o.o. Sva prava pridržana.");
        
        //html_dod_1 = "";
        //html_dod_2 = "";
        html_dod_1 = "<html><div style='width:640px;'>";
        html_dod_2 = "</div></html>";
        
        label_registarska_oznaka = new JLabel(""+html_dod_1+"Registarska oznaka:"+html_dod_2+"");
        registarska_oznaka = new JTextField(30);
        registarska_oznaka.setDocument(new JTextFieldLimit(10));

        label_marka = new JLabel(""+html_dod_1+"Marka:"+html_dod_2+"");
        marka = new JTextField(30);
        marka.setDocument(new JTextFieldLimit(100));

        label_oznaka = new JLabel(""+html_dod_1+"Oznaka:"+html_dod_2+"");
        oznaka = new JTextField(30);
        oznaka.setDocument(new JTextFieldLimit(100));

        label_snaga_motora = new JLabel(""+html_dod_1+"Snaga motora:"+html_dod_2+"");
        snaga_motora = new JTextField(30);
        snaga_motora.setDocument(new JTextFieldLimit(50));

        label_boja = new JLabel(""+html_dod_1+"Boja:"+html_dod_2+"");
        boja = new JTextField(30);
        boja.setDocument(new JTextFieldLimit(50));

        label_godina_proizvodnje = new JLabel(""+html_dod_1+"Godina proizvodnje:"+html_dod_2+"");
        godina_proizvodnje = new JTextField(30);
        godina_proizvodnje.setDocument(new JTextFieldLimit(10));
        
        label_cijena_pri_kupnji = new JLabel(""+html_dod_1+"Cijena pri kupnji (kn) (format upisa samo sa<br /> decimalnom točkom ili bez decimalnog znaka):"+html_dod_2+"");
        cijena_pri_kupnji = new JTextField(30);
        cijena_pri_kupnji.setDocument(new JTextFieldLimit(20));
        
        //m.getRegistrationNumbers();
        label_combo_registarska_oznaka = new JLabel(""+html_dod_1+"Registarska oznaka:"+html_dod_2+"");
        registration_numbers = m.getRegistrationNumbers();
        combo_registarska_oznaka = new JComboBox(registration_numbers);
        label_datum = new JLabel(""+html_dod_1+"Datum:"+html_dod_2+"");
        datum = new JTextField(30);
        datum.setDocument(new JTextFieldLimit(10));
        
        label_datum_vracanja = new JLabel(""+html_dod_1+"Datum vraćanja:"+html_dod_2+"");
        datum_vracanja = new JTextField(30);
        datum_vracanja.setDocument(new JTextFieldLimit(10));
        
        label_osoba_tvrtka = new JLabel(""+html_dod_1+"Osoba/tvrtka:"+html_dod_2+"");
        osoba_tvrtka = new JTextArea(5, 30);
        osoba_tvrtka.setDocument(new JTextFieldLimit(255));
        //scrollPane_textarea = new JScrollPane(osoba_tvrtka);
        osoba_tvrtka.setColumns(30);
        osoba_tvrtka.setLineWrap(true);
        osoba_tvrtka.setWrapStyleWord(false);
        osoba_tvrtka.setDocument(new JTextFieldLimit(255));
        
        label_km_pocetak = new JLabel(""+html_dod_1+"KM početak:"+html_dod_2+"");
        km_pocetak = new JTextField(30);
        km_pocetak.setDocument(new JTextFieldLimit(20));
        
        label_km_kraj = new JLabel(""+html_dod_1+"KM kraj:"+html_dod_2+"");
        km_kraj = new JTextField(30);
        km_kraj.setDocument(new JTextFieldLimit(20));
        
        label_vrsta_prihoda = new JLabel(""+html_dod_1+"Vrsta prihoda:"+html_dod_2+"");
        vrsta_prihoda = new JTextField(30);
        vrsta_prihoda.setDocument(new JTextFieldLimit(150));
        
        label_opis = new JLabel(""+html_dod_1+"Opis:"+html_dod_2+"");
        opis = new JTextArea(5, 30);
        opis.setColumns(30);
        opis.setLineWrap(true);
        opis.setWrapStyleWord(false);
        opis.setDocument(new JTextFieldLimit(255));
        
        label_iznos = new JLabel(""+html_dod_1+"Iznos:"+html_dod_2+"");
        iznos = new JTextField(30);
        iznos.setDocument(new JTextFieldLimit(20));

        label_do_datuma = new JLabel(""+html_dod_1+"Do datuma:"+html_dod_2+"");
        do_datuma = new JTextField(30);
        do_datuma.setDocument(new JTextFieldLimit(10));
        
        label_vrsta_troska = new JLabel(""+html_dod_1+"Vrsta troška:"+html_dod_2+"");
        vrsta_troska = new JTextField(30);
        vrsta_troska.setDocument(new JTextFieldLimit(150));
        
        prazni_redak = new JLabel(""+html_dod_1+html_dod_2+"");
        prazni_redak_2 = new JLabel("");
        spremi_automobile = new JButton("Spremi"); 
        spremi_prihode = new JButton("Spremi"); 
        spremi_troskove = new JButton("Spremi"); 
        button_prikazi_excell_izvjesce = new JButton("Izvješće"); 
        
        poruka_spremanje = new JLabel(""+html_dod_1+"Rezultati su uspješno spremljeni"+html_dod_2+"");
        
        //rezultati.setBounds(300, 300, 300, 300);
        //button_prikazi_automobile.setPreferredSize(new Dimension(200, 20));
        menu.add(button_dodaj_automobile);
        menu.add(button_prikazi_automobile);
        menu.add(button_dodaj_prihode);
        menu.add(button_prikazi_prihode);
        menu.add(button_dodaj_troskove);
        menu.add(button_prikazi_troskove);
        menu.add(prazni_redak_2);
        menu.add(combo_godina_izvjesca);
        menu.add(button_prikazi_izvjesce);
        sredisnji_dio.add(rezultati);
        podnozje.add(podnozje_poruka);
        
        ActionListenerHandler handler = new ActionListenerHandler();
        button_prikazi_automobile.addActionListener(handler);
        button_dodaj_automobile.addActionListener(handler);
        button_prikazi_prihode.addActionListener(handler);
        button_dodaj_prihode.addActionListener(handler);
        button_prikazi_troskove.addActionListener(handler);
        button_dodaj_troskove.addActionListener(handler);
        
        spremi_automobile.addActionListener(handler);
        spremi_prihode.addActionListener(handler);
        spremi_troskove.addActionListener(handler);
        button_prikazi_izvjesce.addActionListener(handler);
        button_prikazi_excell_izvjesce.addActionListener(handler);
        
    }
    
    private class ActionListenerHandler implements ActionListener{
        public void actionPerformed(ActionEvent event) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            //JOptionPane.showMessageDialog(null, String.format("%s ", event.getActionCommand()));
            //JOptionPane.showMessageDialog(null, String.format("%s ", event.getSource()));
            if(event.getSource() == button_prikazi_automobile){
                //JOptionPane.showMessageDialog(null, String.format("%s ", "prikazani automobili"));
                getCarsList();
            }
            else if(event.getSource() == button_dodaj_automobile){
                //JOptionPane.showMessageDialog(null, String.format("%s ", "dodaj automobile"));
                addCars();
            }
            else if(event.getSource() == spremi_automobile){
               // event.paramString();
                //JOptionPane.showMessageDialog(null, String.format("%s ", event.getActionCommand()));
                if(registarska_oznaka.getText().length() > 3)
                {
                    saveCars();
                }
                else {
                    //sredisnji_dio.remove(scroller);
                    //scroller.revalidate();
                    //scroller.repaint();
                    
                    poruka_spremanje.setText(""+html_dod_1+"Niste unijeli registarsku oznaku"+html_dod_2+"");
                    sredisnji_dio.add(poruka_spremanje);
                    sredisnji_dio.revalidate();
                    sredisnji_dio.repaint();
                }
            }
            else if(event.getSource() == button_dodaj_prihode){
                //JOptionPane.showMessageDialog(null, String.format("%s ", "dodaj prihode"));
                addRevenues();
            }
            if(event.getSource() == button_prikazi_prihode){
                //JOptionPane.showMessageDialog(null, String.format("%s ", "prikazani automobili"));
                getRevenuesList();
            }
            else if(event.getSource() == button_dodaj_troskove){
                //JOptionPane.showMessageDialog(null, String.format("%s ", "dodaj prihode"));
                addExpenses();
            } 
            else if(event.getSource() == button_prikazi_troskove){
                //JOptionPane.showMessageDialog(null, String.format("%s ", "dodaj prihode"));
                getExpensesList();
            }
            else if(event.getSource() == spremi_prihode){
               // event.paramString();
                //JOptionPane.showMessageDialog(null, String.format("%s ", event.getActionCommand()));
                //System.out.println(combo_registarska_oznaka.getSelectedItem().toString().length());
                if(combo_registarska_oznaka.getSelectedItem().toString().length() > 3)
                {
                    saveRevenues();
                }
                else 
                {
                    poruka_spremanje.setText(""+html_dod_1+"Niste unijeli registarsku oznaku"+html_dod_2+"");
                    sredisnji_dio.add(poruka_spremanje);
                    sredisnji_dio.revalidate();
                    sredisnji_dio.repaint();
                }
            }
            else if(event.getSource() == spremi_troskove){
               // event.paramString();
                //JOptionPane.showMessageDialog(null, String.format("%s ", event.getActionCommand()));
                if(combo_registarska_oznaka.getSelectedItem().toString().length() > 3)
                {
                    saveExpenses();
                }
                else 
                {
                    poruka_spremanje.setText(""+html_dod_1+"Niste unijeli registarsku oznaku"+html_dod_2+"");
                    sredisnji_dio.add(poruka_spremanje);
                    sredisnji_dio.revalidate();
                    sredisnji_dio.repaint();
                }
            }
            else if(event.getSource() == button_prikazi_izvjesce){
                //JOptionPane.showMessageDialog(null, String.format("%s ", "dodaj prihode"));
                getReport(combo_godina_izvjesca.getSelectedItem().toString());
            }
            else if(event.getSource() == button_prikazi_excell_izvjesce){
                //JOptionPane.showMessageDialog(null, String.format("%s ", "dodaj prihode"));
                getExcellReport(combo_godina_izvjesca.getSelectedItem().toString());
            }
        }
    }
    
    public void getCarsList(){
        c.removeAll();
        sredisnji_dio.removeAll();

        table_view = tabular_data.getTabularData("automobili");
        sredisnji_dio.add(table_view);
        sredisnji_dio.setPreferredSize(new Dimension(680, 560));
        //sredisnji_dio.setPreferredSize(c.getPreferredSize());
        
        scroller = new JScrollPane(table_view, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroller.setPreferredSize(new Dimension(680, 560));
        //scroller.setPreferredSize(c.getPreferredSize());
        sredisnji_dio.add(scroller);
        
        c.add(menu_container, BorderLayout.WEST);
        c.add(sredisnji_dio, BorderLayout.CENTER);
        c.add(podnozje, BorderLayout.SOUTH);
        c.validate();
        c.repaint();
    }
    
    public void getRevenuesList(){
        c.removeAll();
        sredisnji_dio.removeAll();
        
        /**/
        
        poruka_spremanje.setText(""+html_dod_1+"Pregled za ovaj sadržaj je trenutno nedostupan."+html_dod_2+"");
        sredisnji_dio.add(poruka_spremanje);
        
        c.add(menu_container, BorderLayout.WEST);
        c.add(sredisnji_dio, BorderLayout.CENTER);
        c.add(podnozje, BorderLayout.SOUTH);
        c.validate();
        c.repaint();
    }
    
    public void getExpensesList(){
        c.removeAll();
        sredisnji_dio.removeAll();
        
        /**/
        poruka_spremanje.setText(""+html_dod_1+"Pregled za ovaj sadržaj je trenutno nedostupan."+html_dod_2+"");
        sredisnji_dio.add(poruka_spremanje);
        
        c.add(menu_container, BorderLayout.WEST);
        c.add(sredisnji_dio, BorderLayout.CENTER);
        c.add(podnozje, BorderLayout.SOUTH);
        c.validate();
        c.repaint();
    }
    
    public void addCars(){
        //System.out.println("test test..");
        sredisnji_dio.remove(scroller);
        sredisnji_dio.removeAll();
        
        sredisnji_dio.add(label_registarska_oznaka);
        sredisnji_dio.add(registarska_oznaka);

        sredisnji_dio.add(label_marka);
        sredisnji_dio.add(marka);

        sredisnji_dio.add(label_oznaka);
        sredisnji_dio.add(oznaka);

        sredisnji_dio.add(label_snaga_motora);
        sredisnji_dio.add(snaga_motora);

        sredisnji_dio.add(label_boja);
        sredisnji_dio.add(boja);

        sredisnji_dio.add(label_godina_proizvodnje);
        sredisnji_dio.add(godina_proizvodnje);
        
        sredisnji_dio.add(label_cijena_pri_kupnji);
        sredisnji_dio.add(cijena_pri_kupnji);
        
        sredisnji_dio.add(prazni_redak);
        sredisnji_dio.add(spremi_automobile);
        
        //sredisnji_dio.revalidate();
        //sredisnji_dio.repaint();
        
        c.add(menu_container, BorderLayout.WEST);
        c.add(sredisnji_dio, BorderLayout.CENTER);
        c.add(podnozje, BorderLayout.SOUTH);
        c.validate();
        c.repaint();
    }
    
    public void addExpenses(){
        sredisnji_dio.remove(scroller);
        sredisnji_dio.removeAll();
        
        registration_numbers = m.getRegistrationNumbers();
        combo_registarska_oznaka = new JComboBox(registration_numbers);
        
        sredisnji_dio.add(label_registarska_oznaka);
        sredisnji_dio.add(combo_registarska_oznaka);

        sredisnji_dio.add(label_datum);
        sredisnji_dio.add(datum);

        sredisnji_dio.add(label_do_datuma);
        sredisnji_dio.add(do_datuma);

        sredisnji_dio.add(label_osoba_tvrtka);
        sredisnji_dio.add(osoba_tvrtka);

        sredisnji_dio.add(label_km_pocetak);
        sredisnji_dio.add(km_pocetak);

        sredisnji_dio.add(label_km_kraj);
        sredisnji_dio.add(km_kraj);
        
        sredisnji_dio.add(label_vrsta_troska);
        sredisnji_dio.add(vrsta_troska);
        
        sredisnji_dio.add(label_opis);
        sredisnji_dio.add(opis);
        
        sredisnji_dio.add(label_iznos);
        sredisnji_dio.add(iznos);
        
        sredisnji_dio.add(prazni_redak);
        sredisnji_dio.add(spremi_troskove);
        
        //sredisnji_dio.revalidate();
        //sredisnji_dio.repaint();
        
        c.add(menu_container, BorderLayout.WEST);
        c.add(sredisnji_dio, BorderLayout.CENTER);
        c.add(podnozje, BorderLayout.SOUTH);
        c.validate();
        c.repaint();
    }
    
    public void addRevenues(){
        //System.out.println("test test..");
        sredisnji_dio.remove(scroller);
        sredisnji_dio.removeAll();
        
        registration_numbers = m.getRegistrationNumbers();
        combo_registarska_oznaka = new JComboBox(registration_numbers);
        
        sredisnji_dio.add(label_registarska_oznaka);
        sredisnji_dio.add(combo_registarska_oznaka);

        sredisnji_dio.add(label_datum);
        sredisnji_dio.add(datum);

        sredisnji_dio.add(label_datum_vracanja);
        sredisnji_dio.add(datum_vracanja);

        sredisnji_dio.add(label_osoba_tvrtka);
        sredisnji_dio.add(osoba_tvrtka);

        sredisnji_dio.add(label_km_pocetak);
        sredisnji_dio.add(km_pocetak);

        sredisnji_dio.add(label_km_kraj);
        sredisnji_dio.add(km_kraj);
        
        sredisnji_dio.add(label_vrsta_prihoda);
        sredisnji_dio.add(vrsta_prihoda);
        
        sredisnji_dio.add(label_opis);
        sredisnji_dio.add(opis);
        
        sredisnji_dio.add(label_iznos);
        sredisnji_dio.add(iznos);
        
        sredisnji_dio.add(prazni_redak);
        sredisnji_dio.add(spremi_prihode);
        
        c.add(menu_container, BorderLayout.WEST);
        c.add(sredisnji_dio, BorderLayout.CENTER);
        c.add(podnozje, BorderLayout.SOUTH);
        c.validate();
        c.repaint();
    }
    
    public void saveCars(){
        sredisnji_dio.removeAll();
        sredisnji_dio.remove(scroller);
        
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("registarska_oznaka", registarska_oznaka.getText());
        map.put("marka", marka.getText());
        map.put("oznaka", oznaka.getText());
        map.put("snaga_motora", snaga_motora.getText());
        map.put("boja", boja.getText());
        map.put("godina_proizvodnje", godina_proizvodnje.getText());
        map.put("cijena_pri_kupnji", cijena_pri_kupnji.getText());
        
        m.insertQuery("automobili", map);
        
        sredisnji_dio.add(label_registarska_oznaka);
        registarska_oznaka.setText("");
        sredisnji_dio.add(registarska_oznaka);

        sredisnji_dio.add(label_marka);
        marka.setText("");
        sredisnji_dio.add(marka);

        sredisnji_dio.add(label_oznaka);
        oznaka.setText("");
        sredisnji_dio.add(oznaka);

        sredisnji_dio.add(label_snaga_motora);
        snaga_motora.setText("");
        sredisnji_dio.add(snaga_motora);

        sredisnji_dio.add(label_boja);
        boja.setText("");
        sredisnji_dio.add(boja);

        sredisnji_dio.add(label_godina_proizvodnje);
        godina_proizvodnje.setText("");
        sredisnji_dio.add(godina_proizvodnje);
        
        sredisnji_dio.add(label_cijena_pri_kupnji);
        cijena_pri_kupnji.setText("");
        sredisnji_dio.add(cijena_pri_kupnji);
        
        sredisnji_dio.add(prazni_redak);
        sredisnji_dio.add(spremi_automobile);
        
        poruka_spremanje.setText(""+html_dod_1+"Rezultati su uspješno spremljeni"+html_dod_2+"");
        sredisnji_dio.add(poruka_spremanje);
        
        c.validate();
        sredisnji_dio.revalidate();
        sredisnji_dio.repaint();
    }
    
    
    public void saveRevenues(){
        sredisnji_dio.removeAll();
        sredisnji_dio.remove(scroller);
        
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("registarska_oznaka", combo_registarska_oznaka.getSelectedItem().toString());
        map.put("datum", datum.getText());
        map.put("datum_vracanja", datum_vracanja.getText());
        map.put("osoba_tvrtka", osoba_tvrtka.getText());
        map.put("km_pocetak", km_pocetak.getText());
        map.put("km_kraj", km_kraj.getText());
        map.put("vrsta_prihoda", vrsta_prihoda.getText());
        map.put("opis", opis.getText());
        map.put("iznos", iznos.getText());
        
        m.insertQuery("prihodi", map);
        
        sredisnji_dio.add(label_combo_registarska_oznaka);
        combo_registarska_oznaka.setSelectedItem("");
        sredisnji_dio.add(combo_registarska_oznaka);
        

        sredisnji_dio.add(label_datum);
        datum.setText("");
        sredisnji_dio.add(datum);

        sredisnji_dio.add(label_datum_vracanja);
        datum_vracanja.setText("");
        sredisnji_dio.add(datum_vracanja);

        sredisnji_dio.add(label_osoba_tvrtka);
        osoba_tvrtka.setText("");
        sredisnji_dio.add(osoba_tvrtka);

        sredisnji_dio.add(label_km_pocetak);
        km_pocetak.setText("");
        sredisnji_dio.add(km_pocetak);

        sredisnji_dio.add(label_km_kraj);
        km_kraj.setText("");
        sredisnji_dio.add(km_kraj);
        
        sredisnji_dio.add(label_vrsta_prihoda);
        vrsta_prihoda.setText("");
        sredisnji_dio.add(vrsta_prihoda);
        
        sredisnji_dio.add(label_opis);
        opis.setText("");
        sredisnji_dio.add(opis);
        
        sredisnji_dio.add(label_iznos);
        iznos.setText("");
        sredisnji_dio.add(iznos);
        
        sredisnji_dio.add(prazni_redak);
        sredisnji_dio.add(spremi_prihode);
        
        poruka_spremanje.setText(""+html_dod_1+"Rezultati su uspješno spremljeni"+html_dod_2+"");
        sredisnji_dio.add(poruka_spremanje);
        
        c.validate();
        sredisnji_dio.revalidate();
        sredisnji_dio.repaint();
    }
    
    
    public void saveExpenses(){
        sredisnji_dio.removeAll();
        sredisnji_dio.remove(scroller);
        //scroller.revalidate();
        //scroller.repaint();
        
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("registarska_oznaka", combo_registarska_oznaka.getSelectedItem().toString());
        map.put("datum", datum.getText());
        map.put("do_datuma", do_datuma.getText());
        map.put("osoba_tvrtka", osoba_tvrtka.getText());
        map.put("km_pocetak", km_pocetak.getText());
        map.put("km_kraj", km_kraj.getText());
        map.put("vrsta_troskova", vrsta_troska.getText());
        map.put("opis", opis.getText());
        map.put("iznos", iznos.getText());
        
        m.insertQuery("troskovi", map);
        
        sredisnji_dio.add(label_combo_registarska_oznaka);
        combo_registarska_oznaka.setSelectedItem("");
        sredisnji_dio.add(combo_registarska_oznaka);

        sredisnji_dio.add(label_datum);
        datum.setText("");
        sredisnji_dio.add(datum);

        sredisnji_dio.add(label_do_datuma);
        do_datuma.setText("");
        sredisnji_dio.add(do_datuma);

        sredisnji_dio.add(label_osoba_tvrtka);
        osoba_tvrtka.setText("");
        sredisnji_dio.add(osoba_tvrtka);

        sredisnji_dio.add(label_km_pocetak);
        km_pocetak.setText("");
        sredisnji_dio.add(km_pocetak);

        sredisnji_dio.add(label_km_kraj);
        km_kraj.setText("");
        sredisnji_dio.add(km_kraj);
        
        sredisnji_dio.add(label_vrsta_troska);
        vrsta_troska.setText("");
        sredisnji_dio.add(vrsta_troska);
        
        sredisnji_dio.add(label_opis);
        opis.setText("");
        sredisnji_dio.add(opis);
        
        sredisnji_dio.add(label_iznos);
        iznos.setText("");
        sredisnji_dio.add(iznos);
        
        sredisnji_dio.add(prazni_redak);
        sredisnji_dio.add(spremi_prihode);
        
        poruka_spremanje.setText(""+html_dod_1+"Rezultati su uspješno spremljeni"+html_dod_2+"");
        sredisnji_dio.add(poruka_spremanje);
        
        c.validate();
        sredisnji_dio.revalidate();
        sredisnji_dio.repaint();
    }
    
    public void getReport(String year){
        sredisnji_dio.removeAll();
        sredisnji_dio.remove(scroller);
        
        table_view = m.getReport(combo_godina_izvjesca.getSelectedItem().toString());
        sredisnji_dio.add(table_view);
        sredisnji_dio.setPreferredSize(new Dimension(680, 560));
        //sredisnji_dio.setPreferredSize(c.getPreferredSize());
        
        scroller = new JScrollPane(table_view, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroller.setPreferredSize(new Dimension(680, 560));
        //scroller.setPreferredSize(c.getPreferredSize());
        sredisnji_dio.add(scroller);
        
        //sredisnji_dio.add(rezultati);
        //c.add(pn);
        poruka_spremanje.setText("Izvješće možete pogledati u Excel čitaču ovdje: ");
        sredisnji_dio.add(poruka_spremanje);
        sredisnji_dio.add(button_prikazi_excell_izvjesce);
        
        c.validate();
        sredisnji_dio.revalidate();
        sredisnji_dio.repaint();
    }
    
    public void getExcellReport(String year){
        try {  
             Desktop.getDesktop().open(new File("report_"+year+".xls"));  
        } catch (IOException e) {
            e.printStackTrace();
        }  
    }
}

