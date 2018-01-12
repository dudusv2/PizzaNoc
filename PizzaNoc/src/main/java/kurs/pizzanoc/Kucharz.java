package kurs.pizzanoc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.util.Arrays;

import javax.swing.table.TableColumnModel;




//Panel kucharza / pracownika
public class Kucharz extends JFrame implements ActionListener {
    private List<String> pizze;
    private List<String> dodatki;
    private List<String> orderList;
    private List<String> order;

    private JTextField tforder;
    private JTextField tforders;

    private JTextArea taadres;
    private JTextArea taid;

    private JCheckBox terminal;

    private JComboBox cbpizza;
    private JComboBox cbi;
    private JComboBox cbi1;
    private JComboBox cbi2;
    private JComboBox cbi3;
    private JComboBox cbi4;
    private JComboBox cbi5;

    private JSpinner jsi;

    private JButton bdodaj;
    private JButton border;
    private JButton bwydano;
    private JButton wyslaneZam;
    private JButton odmowioneZam;
    private JButton odebraneZam;
    private JButton nieodebraneZam;

    private JButton checkOrder;

    private OrdersTable zamowienia;

    private final String[] ingridient = {"brak", "Sos", "Ser", "Oliwki", "Kiełbasa", "Jarmuż"};
    private final String[] Pizza = {"Pizza", "Margarita", "Cztery Sery", "Wiejska", "Farmerska", "Hawajska"};

    private PizzaNoc pizzanoc;

    Kucharz(PizzaNoc p) {
        super("PizzaNoc - Baza Danych");
        this.pizzanoc = p;

        pizze = new ArrayList<>();
        dodatki = new ArrayList<>();
        orderList = new ArrayList<>();
        order = new ArrayList<>();

        setLayout(null);
        setBackground(new Color(10, 20, 30));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                pizzanoc.mysql().disconnect();
                e.getWindow().dispose();
            }
        });

        setSize(1200, 600);

        JLabel logo = new JLabel("Wprowadz zamówienie ");
        int style = Font.BOLD;
        Font font = new Font("Garamond", style, 30);
        logo.setFont(font);
        logo.setForeground(Color.RED);
        logo.setBounds(20, 20, 500, 50);
        add(logo);
        JSeparator js = new JSeparator(JSeparator.VERTICAL);
        js.setBounds(450, 20, 500, 550);
        add(js);

        Font font1 = new Font("Garamond", Font.ITALIC, 20);
        JLabel lpizza = new JLabel("Pizza:");
        lpizza.setFont(font1);
        lpizza.setForeground(Color.BLUE);
        lpizza.setBounds(10, 80, 200, 50);
        add(lpizza);

        JLabel ladd = new JLabel("Dodatek:");
        ladd.setFont(font1);
        ladd.setForeground(Color.BLUE);
        ladd.setBounds(210, 80, 200, 50);
        add(ladd);

        cbi = new JComboBox(ingridient);
        cbi.setBounds(310, 80, 130, 50);
        add(cbi);

        cbpizza = new JComboBox(Pizza);
        cbpizza.setBounds(80, 80, 120, 50);
        add(cbpizza);

        /*JLabel ldel = new JLabel("Własna kompozycja:");
        ldel.setFont(font1);
        ldel.setForeground(Color.BLUE);
        ldel.setBounds(20, 150, 250, 50);
        add(ldel);

        cbi1 = new JComboBox(ingridient);
        cbi1.setBounds(20, 200, 80, 50);
        add(cbi1);

        cbi2 = new JComboBox(ingridient);
        cbi2.setBounds(100, 200, 80, 50);
        add(cbi2);

        cbi3 = new JComboBox(ingridient);
        cbi3.setBounds(180, 200, 80, 50);
        add(cbi3);

        cbi4 = new JComboBox(ingridient);
        cbi4.setBounds(260, 200, 80, 50);
        add(cbi4);

        cbi5 = new JComboBox(ingridient);
        cbi5.setBounds(340, 200, 80, 50);
        add(cbi5);*/

        JLabel lil = new JLabel("Ilość:");
        lil.setFont(font1);
        lil.setForeground(Color.BLUE);
        lil.setBounds(20, 270, 200, 50);
        add(lil);

        SpinnerModel model = new SpinnerNumberModel(1, 1, 10, 1);
        jsi = new JSpinner(model);
        jsi.setBounds(80, 270, 50, 50);
        add(jsi);

        bdodaj = new JButton("Dodaj Pizze");
        bdodaj.addActionListener(this);
        bdodaj.setBounds(160, 270, 260, 50);
        bdodaj.addActionListener(this);
        add(bdodaj);

        JLabel lad = new JLabel("Dodano:");
        lad.setFont(font1);
        lad.setForeground(Color.BLUE);
        lad.setBounds(20, 330, 250, 50);
        add(lad);

        tforder = new JTextField();
        tforder.setBounds(120, 330, 300, 50);
        add(tforder);

        JLabel lar = new JLabel("Adres:");
        lar.setFont(font1);
        lar.setForeground(Color.BLUE);
        lar.setBounds(20, 400, 250, 50);
        add(lar);

        taadres = new JTextArea();
        taadres.setBounds(100, 400, 320, 50);
        taadres.setFont(font1);
        add(taadres);

        terminal = new JCheckBox("Terminal");
        terminal.setBounds(20, 460, 100, 50);
        terminal.setFont(new Font("Garamond", Font.ITALIC, 15));
        terminal.setForeground(Color.BLUE);
        add(terminal);

        border = new JButton("Złóż zamówienie");
        border.setBounds(140, 460, 270, 50);
        border.addActionListener(this);
        add(border);






        JLabel lza = new JLabel("Zamówienia");
        lza.setFont(font);
        lza.setForeground(Color.RED);
        lza.setBounds(530, 20, 400, 50);
        add(lza);

        zamowienia = new OrdersTable();
        zamowienia.setBounds(450, 100, 500, 500);
        add(zamowienia);
        selectOrd();

        wyslaneZam = new JButton("WYSłANE");
        wyslaneZam.setBounds(1000, 200, 150, 50);
        wyslaneZam.addActionListener(this);
        add(wyslaneZam);
        odmowioneZam = new JButton("ODMÓWIONE");
        odmowioneZam.setBounds(1000, 260, 150, 50);
        odmowioneZam.addActionListener(this);
        add(odmowioneZam);
        odebraneZam = new JButton("ODEBRANE");
        odebraneZam.setBounds(1000, 320, 150, 50);
        odebraneZam.addActionListener(this);
        add(odebraneZam);
        nieodebraneZam = new JButton("NIEODEBRANE");
        nieodebraneZam.setBounds(1000, 380, 150, 50);
        nieodebraneZam.addActionListener(this);
        add(nieodebraneZam);

        checkOrder = new JButton("SZCZEGOLY");
        checkOrder.setBounds(1000, 50, 150, 50);
        checkOrder.addActionListener(this);
        add(checkOrder);
    }

    public void selectOrd() {
        int numOfRows = pizzanoc.mysql().getRowNumbers("orders");
        zamowienia.model.setData(pizzanoc.mysql().getOrders("SELECT * FROM orders", numOfRows));
    }

    // Obsługa zdarzeń
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if(o == checkOrder) {
            String tempID = String.valueOf(zamowienia.getIDofSelectedRow());
            System.out.println(tempID);
            String ordered_products_id[] = pizzanoc.mysql().getOrderedProductsID(
                    "SELECT product_ID FROM ordered_products WHERE order_id = " + tempID);

            int count;
            for(int i=0;;i++) {
                if (ordered_products_id[i].substring(0, 1).equals("n")) {
                    count = Integer.parseInt(ordered_products_id[i].substring(2));
                    break;
                }
            }

            String ord_products="";
            for(int i=0;i<count;i++) {
                ord_products += pizzanoc.mysql().getProducts("SELECT name, price, diameter FROM products WHERE ID = " + ordered_products_id[i]);
                ord_products += "\n";
            }

            JFrame frame = new JFrame("Szczegoly zamowienia");
            JOptionPane.showMessageDialog(frame,ord_products);
        }

        if (o == wyslaneZam){
            pizzanoc.mysql().updateOrders("wysłane", zamowienia.getIDofSelectedRow());
            selectOrd();
        }if (o == odmowioneZam){
            pizzanoc.mysql().updateOrders("odmówione", zamowienia.getIDofSelectedRow());
            selectOrd();
        }if (o == odebraneZam){
            pizzanoc.mysql().updateOrders("odebrane", zamowienia.getIDofSelectedRow());
            selectOrd();
        }if (o == nieodebraneZam){
            pizzanoc.mysql().updateOrders("nieodebrane", zamowienia.getIDofSelectedRow());
            selectOrd();
        }



        if (o == bdodaj){  //Dodawanie pizzy
            pizze.removeAll(pizze);
            dodatki.removeAll(dodatki);

            if (!cbpizza.getSelectedItem().toString().contains("Pizza")) {
                String pizza = cbpizza.getSelectedItem().toString();
                pizze.add(pizza);
                if (!cbi.getSelectedItem().toString().contains("brak")) {
                    String dodatek = cbi.getSelectedItem().toString();
                    dodatki.add(dodatek);
                    String order1 = pizza + " " + dodatek;
                    orderList.add(order1);
                    //TODO: do dodania gotowa pizza + dodatek
                    return;
                }
                orderList.add(pizza);
                return;
            }
            String dod = "";
            if (!cbi1.getSelectedItem().toString().contains("brak")) {
                String dodatek = cbi1.getSelectedItem().toString();
                dodatki.add(dodatek);
                dod += " " + dodatek;
            }
            if (!cbi2.getSelectedItem().toString().contains("brak")) {
                String dodatek = cbi2.getSelectedItem().toString();
                dodatki.add(dodatek);
                dod += " " + dodatek;
            }
            if (!cbi3.getSelectedItem().toString().contains("brak")) {
                String dodatek = cbi3.getSelectedItem().toString();
                dodatki.add(dodatek);
                dod += " " + dodatek;
            }
            if (!cbi4.getSelectedItem().toString().contains("brak")) {
                String dodatek = cbi4.getSelectedItem().toString();
                dodatki.add(dodatek);
                dod += " " + dodatek;
            }
            if (!cbi5.getSelectedItem().toString().contains("brak")) {
                String dodatek = cbi5.getSelectedItem().toString();
                dodatki.add(dodatek);
                dod += " " + dodatek;
            }
            orderList.add(" basic " + dod);
            return;

        }

        if (o == border)

        { //Składanie zamówienia
            String sOrder = orderList.toString() + " ";
            String adres = taadres.getText();
            boolean t = terminal.isSelected();
            sOrder += " " + adres;
            if (t) {
                sOrder += " " + "terminal";
            }
            order.add(sOrder);
            System.out.println("Order" + sOrder);
            return;

        }
        if (o == bwydano)

        { //Wydawanie pizzy z kuchni
            String id = taid.getText();
            //Todo zmiana statusu zamówienia o nr id id na wydano
        }
    }
}