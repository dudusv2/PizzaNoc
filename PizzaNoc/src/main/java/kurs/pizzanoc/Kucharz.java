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

    private JTextArea zamArea;
    private JTextArea miastoArea;
    private JTextArea ulicaArea;
    private JTextArea mieszkanieArea;
    private JTextArea domArea;
    private JTextArea phoneArea;

    private JComboBox cbterminal;
    private JComboBox cbpizza;
    private JComboBox cbrozmiar;

    private JSpinner jsi;

    private JButton bdodaj;
    private JButton border;
    private JButton bwyczysc;
    private JButton wyslaneZam;
    private JButton odmowioneZam;
    private JButton odebraneZam;
    private JButton nieodebraneZam;

    private JButton checkOrder;

    private OrdersTable zamowienia;

    //private final String[] ingridient = {"brak", "Sos", "Ser", "Oliwki", "Kiełbasa", "Jarmuż"};
    private final String[] Pizza = {"Margherita", "Funghi", "Prosciutto", "Salami", "Capriciosa",
                                    "Hawajska", "Gambino", "Nocny Marek", "Vegetariana", "Kolorowa",
                                    "Chilli", "Droga Mleczna", "Księżycowa", "Cztery Sery", "Mamma Mia!"};
    private final String[] rozmiar = {"32", "45"};

    private final String[] termBox = {"tak", "nie"};

    private PizzaNoc pizzanoc;

    private String tempText;

    Kucharz(PizzaNoc p) {
        super("PizzaNoc - Baza Danych");
        this.pizzanoc = p;

        pizze = new ArrayList<>();
        dodatki = new ArrayList<>();
        orderList = new ArrayList<>();
        order = new ArrayList<>();

        setLayout(null);
        this.setBackground(new Color(10, 20, 30));
        this.getContentPane().setBackground(new Color(10, 20, 30));

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

        cbpizza = new JComboBox(Pizza);
        cbpizza.setBounds(80, 80, 120, 50);
        add(cbpizza);

        cbrozmiar = new JComboBox(rozmiar);
        cbrozmiar.setBounds(210, 80, 40, 50);
        add(cbrozmiar);

        bdodaj = new JButton("Dodaj Pizze");
        bdodaj.setBounds(260, 80, 150, 50);
        bdodaj.addActionListener(this);
        add(bdodaj);

        JLabel zamLabel = new JLabel("Zamowienie:");
        zamLabel.setFont(font1);
        zamLabel.setForeground(Color.BLUE);
        zamLabel.setBounds(10, 150, 100, 50);
        add(zamLabel);

        this.tempText="/";
        zamArea = new JTextArea();
        JScrollPane sp = new JScrollPane(zamArea);
        sp.setBounds(100, 150, 320, 50);
        zamArea.setEditable(false);
        getContentPane().add(sp);
        zamArea.setText(this.tempText);

        JLabel miasto = new JLabel("Miasto:");
        miasto.setFont(font1);
        miasto.setForeground(Color.BLUE);
        miasto.setBounds(10, 200, 100, 50);
        add(miasto);

        miastoArea = new JTextArea();
        miastoArea.setBounds(100, 215, 320, 30);
        miastoArea.setText("Jelenia Góra");
        add(miastoArea);

        JLabel ulica = new JLabel("Ulica:");
        ulica.setFont(font1);
        ulica.setForeground(Color.BLUE);
        ulica.setBounds(10, 260, 100, 30);
        add(ulica);

        ulicaArea = new JTextArea();
        ulicaArea.setBounds(100, 265, 320, 30);
        add(ulicaArea);

        JLabel dom = new JLabel("Nr Domu:");
        dom.setFont(font1);
        dom.setForeground(Color.BLUE);
        dom.setBounds(10, 320, 100, 30);
        add(dom);

        domArea = new JTextArea();
        domArea.setBounds(100, 320, 50, 30);
        add(domArea);

        JLabel mieszkanie = new JLabel("Nr Mieszkania:");
        mieszkanie.setFont(font1);
        mieszkanie.setForeground(Color.BLUE);
        mieszkanie.setBounds(190, 320, 150, 30);
        add(mieszkanie);

        mieszkanieArea = new JTextArea();
        mieszkanieArea.setBounds(320, 320, 50, 30);
        add(mieszkanieArea);

        JLabel telefon = new JLabel("Nr Telefonu:");
        telefon.setFont(font1);
        telefon.setForeground(Color.BLUE);
        telefon.setBounds(10, 370, 150, 30);
        add(telefon);

        phoneArea = new JTextArea();
        phoneArea.setBounds(100, 370, 320, 30);
        add(phoneArea);

        JLabel terminal = new JLabel("TERMINAL");
        terminal.setFont(font1);
        terminal.setForeground(Color.BLUE);
        terminal.setBounds(10, 425, 150, 30);
        add(terminal);

        cbterminal = new JComboBox(termBox);
        cbterminal.setBounds(130, 420, 100, 40);
        cbterminal.setSelectedIndex(1);
        add(cbterminal);

        bwyczysc = new JButton("Wyczysc");
        bwyczysc.setBounds(10, 490, 120, 50);
        bwyczysc.addActionListener(this);
        add(bwyczysc);

        border = new JButton("Dodaj zamówienie");
        border.setBounds(150, 490, 270, 50);
        border.addActionListener(this);
        add(border);

        JLabel lza = new JLabel("Zamówienia");
        lza.setFont(font);
        lza.setForeground(Color.RED);
        lza.setBounds(530, 20, 400, 50);
        add(lza);

        zamowienia = new OrdersTable();
        zamowienia.setBounds(475, 100, 468, 440);
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

    public void clearPanel() {
        zamArea.setText("/");
        miastoArea.setText("Jelenia Góra");
        ulicaArea.setText("");
        mieszkanieArea.setText("");
        domArea.setText("");
        phoneArea.setText("");
        this.tempText="/";
    }

    // Obsługa zdarzeń
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if(o == checkOrder) {
            String tempID = String.valueOf(zamowienia.getIDofSelectedRow());
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
            String pizza = (String) cbpizza.getSelectedItem();
            String rozmiar = (String) cbrozmiar.getSelectedItem();

            this.tempText += pizza;
            this.tempText += ",";
            this.tempText += rozmiar;
            this.tempText += "/";

            zamArea.setText(tempText);
        }


        if(o == border) {
            //Adres:
            String miasto = miastoArea.getText();
            String ulica = ulicaArea.getText();
            String dom = domArea.getText();
            String mieszkanie = mieszkanieArea.getText();
            String telefon = phoneArea.getText();

            String terminal = (String) cbterminal.getSelectedItem();
            String zamowienie = zamArea.getText();

            String adress = ulica+"/"+dom+"/"+mieszkanie+"/"+miasto;
            pizzanoc.mysql().addOrder(adress, telefon, terminal, zamowienie);

            selectOrd();
            clearPanel();
        }

        if(o == bwyczysc) {
            clearPanel();
        }
    }
}