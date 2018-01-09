/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kurs.pizzanoc;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.System.exit;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author jakub
 */
public final class PizzaNoc extends JFrame implements ActionListener {

    private final CardLayout card;
    private JButton bLogin, bReset, bExit, bdostawa, bconst, bdodaj, bcofni, bdeleteorder, bcheckorder, bneeded, bedit, border, bwydano, borderdone, bdodaj1;
    private JComboBox cbproducts, cbpizza, cbi, cbi1, cbi2, cbi3, cbi4, cbi5;
    private final Container container;
    private JTextArea login, taprod, tavol, taadres, taid;
    private JTextField tfadd, tfdel, tforder, tforders;
    private JPasswordField password;
    private JCheckBox terminal;
    private final String[] ingridient = {"brak", "Sos", "Ser", "Oliwki", "Kiełbasa", "Jarmuż"};
    private final String[] Pizza = {"Pizza", "Margarita", "Cztery Sery", "Wiejska", "Farmerska", "Hawajska"};
    private JSpinner jsi;
    private List<String> pizze;
    private List<String> dodatki;
    private List<String> orderList;
    private List<String> order;

    PizzaNoc() {
        super("PizzaNoc - Baza Danych");
        card = new CardLayout();
        container = this.getContentPane();
        container.setLayout(card);
        container.add("pLogin", panelLogin());
        container.add("pDostawca", panelDostawca());
        container.add("pRoz", panelRoz());
        container.add("pPracownik", panelPracownik());

    }

    //Panel Logowania
    public JPanel panelLogin() {
        JPanel pLogin = new JPanel();
        pLogin.setLayout(null);
        pLogin.setBackground(new Color(10, 20, 30));
        setSize(620, 450);
        bLogin = new JButton("Login");
        bLogin.addActionListener(this);
        bLogin.setBounds(20, 330, 180, 50);
        bReset = new JButton("Reset");
        bReset.addActionListener(this);
        bReset.setBounds(210, 330, 180, 50);
        bExit = new JButton("Exit");
        bExit.addActionListener(this);
        bExit.setBounds(400, 330, 180, 50);
        pLogin.add(bLogin);
        pLogin.add(bReset);
        pLogin.add(bExit);

        JLabel logo = new JLabel("Witaj w Pizza Noc");
        int style = Font.BOLD;
        Font font = new Font("Garamond", style, 35);
        logo.setFont(font);
        logo.setForeground(Color.RED);
        logo.setBounds(120, 20, 400, 50);

        pLogin.add(logo);

        JLabel log = new JLabel("Login: ");
        log.setFont(font);
        log.setForeground(Color.BLUE);
        log.setBounds(50, 100, 240, 50);
        pLogin.add(log);

        login = new JTextArea();
        login.setBounds(80, 160, 400, 40);
        login.setFont(new Font("Garamond", Font.CENTER_BASELINE, 25));
        login.setSelectedTextColor(Color.red);
        login.setToolTipText("Login");
        pLogin.add(login);

        JLabel lpas = new JLabel("Password: ");
        lpas.setFont(font);
        lpas.setForeground(Color.BLUE);
        lpas.setBounds(50, 220, 240, 50);
        pLogin.add(lpas);

        password = new JPasswordField();
        password.setEchoChar('*');
        password.setToolTipText("Password");
        password.setBounds(80, 270, 400, 40);
        pLogin.add(password);
        return pLogin;
    }

    //Panel dla dostawcy towaru 
    public JPanel panelDostawca() {

        JPanel pDostawca = new JPanel();
        pDostawca.setLayout(null);
        pDostawca.setBackground(new Color(10, 20, 30));
        //  setSize(900, 600);

        JLabel logo = new JLabel("Wprowadz dostawę lub ");
        int style = Font.BOLD;
        Font font = new Font("Garamond", style, 35);
        logo.setFont(font);
        logo.setForeground(Color.RED);
        logo.setBounds(20, 20, 500, 50);
        pDostawca.add(logo);

        bneeded = new JButton("Sprawdz zapotrzebowanie");
        bneeded.addActionListener(this);
        bneeded.setBounds(500, 20, 250, 50);
        pDostawca.add(bneeded);

        bconst = new JButton("Dodaj stałą dostawę");
        bconst.addActionListener(this);
        bconst.setBounds(20, 100, 280, 50);
        pDostawca.add(bconst);

        bedit = new JButton("edytuj stałą dostawę");
        bedit.addActionListener(this);
        bedit.setBounds(320, 100, 280, 50);
        pDostawca.add(bedit);

        Font font1 = new Font("Garamond", Font.ITALIC, 25);
        JLabel lproducts = new JLabel("Produkt:");
        lproducts.setFont(font1);
        lproducts.setForeground(Color.BLUE);
        lproducts.setBounds(20, 170, 250, 50);
        pDostawca.add(lproducts);

        JLabel lvol = new JLabel("Gramatura:");
        lvol.setFont(font1);
        lvol.setForeground(Color.BLUE);
        lvol.setBounds(350, 170, 250, 50);
        pDostawca.add(lvol);

        taprod = new JTextArea();
        taprod.setBounds(130, 170, 200, 40);
        taprod.setFont(new Font("Garamond", Font.CENTER_BASELINE, 25));
        taprod.setSelectedTextColor(Color.red);
        taprod.setToolTipText("wpisz składnik");
        pDostawca.add(taprod);

        tavol = new JTextArea();
        tavol.setBounds(500, 170, 200, 40);
        tavol.setFont(new Font("Garamond", Font.CENTER_BASELINE, 25));
        tavol.setSelectedTextColor(Color.red);
        tavol.setToolTipText("podaj gramature w gramach");
        pDostawca.add(tavol);

        cbproducts = new JComboBox(ingridient);
        cbproducts.setBounds(20, 230, 310, 50);

        cbproducts.addActionListener(this);
        pDostawca.add(cbproducts);

        bdodaj1 = new JButton("Dodaj");
        bdodaj1.addActionListener(this);
        bdodaj1.setBounds(350, 230, 170, 50);
        pDostawca.add(bdodaj1);

        bcofni = new JButton("Cofni");
        bcofni.addActionListener(this);
        bcofni.setBounds(530, 230, 170, 50);
        pDostawca.add(bcofni);

        tfadd = new JTextField();
        tfadd.setBounds(130, 300, 200, 50);
        tfadd.setFont(new Font("Garamond", Font.CENTER_BASELINE, 25));
        tfadd.setSelectedTextColor(Color.red);
        tfadd.setToolTipText("dodany produkt");
        pDostawca.add(tfadd);

        JLabel ladd = new JLabel("Dodano:");
        ladd.setFont(font1);
        ladd.setForeground(Color.BLUE);
        ladd.setBounds(20, 300, 200, 50);
        pDostawca.add(ladd);

        JLabel ldel = new JLabel("Cofnięto:");
        ldel.setFont(font1);
        ldel.setForeground(Color.BLUE);
        ldel.setBounds(340, 300, 200, 50);
        pDostawca.add(ldel);

        tfdel = new JTextField();
        tfdel.setBounds(460, 300, 240, 50);
        tfdel.setFont(new Font("Garamond", Font.CENTER_BASELINE, 25));
        tfdel.setSelectedTextColor(Color.red);
        tfdel.setToolTipText("cofnięty produkt");
        pDostawca.add(tfdel);

        bdostawa = new JButton("Dodaj produkty:");
        bdostawa.addActionListener(this);
        bdostawa.setBounds(20, 380, 250, 80);
        pDostawca.add(bdostawa);

        bdeleteorder = new JButton("Usuń dostawę");
        bdeleteorder.addActionListener(this);
        bdeleteorder.setBounds(280, 380, 250, 80);
        pDostawca.add(bdeleteorder);

        bcheckorder = new JButton("Zobacz listę dostawy");
        bcheckorder.addActionListener(this);
        bcheckorder.setBounds(540, 380, 250, 80);
        pDostawca.add(bcheckorder);

        return pDostawca;
    }

    //Panel dla Doręczyciela pizzy 
    public JPanel panelRoz() {

        JPanel pRoz = new JPanel();
        pRoz.setLayout(null);
        pRoz.setBackground(new Color(10, 20, 30));

        int style = Font.BOLD;
        Font font = new Font("Garamond", style, 30);
        Font font1 = new Font("Garamond", Font.ITALIC, 20);
        JLabel lza = new JLabel("Zamówienia");
        lza.setFont(font);
        lza.setForeground(Color.RED);
        lza.setBounds(30, 20, 400, 50);
        pRoz.add(lza);
        tforders = new JTextField();
        tforders.setBounds(20, 80, 400, 400);
        pRoz.add(tforders);

        JLabel la = new JLabel("ID: ");
        la.setFont(font1);
        la.setForeground(Color.BLUE);
        la.setBounds(20, 500, 60, 50);
        pRoz.add(la);

        taid = new JTextArea();
        taid.setBounds(60, 510, 150, 30);
        taid.setFont(font1);
        pRoz.add(taid);

        borderdone = new JButton("Zrealizowano");
        borderdone.addActionListener(this);
        borderdone.setBounds(220, 500, 200, 50);
        pRoz.add(borderdone);

        return pRoz;
    }

    //Panel kucharza / pracownika
    public JPanel panelPracownik() {

        pizze = new ArrayList<>();
        dodatki = new ArrayList<>();
        orderList = new ArrayList<>();
        order = new ArrayList<>();

        JPanel pPracownik = new JPanel();
        pPracownik.setLayout(null);
        pPracownik.setBackground(new Color(10, 20, 30));

        JLabel logo = new JLabel("Wprowadz zamówienie ");
        int style = Font.BOLD;
        Font font = new Font("Garamond", style, 30);
        logo.setFont(font);
        logo.setForeground(Color.RED);
        logo.setBounds(20, 20, 500, 50);
        pPracownik.add(logo);
        JSeparator js = new JSeparator(JSeparator.VERTICAL);
        js.setBounds(450, 20, 500, 550);
        pPracownik.add(js);

        Font font1 = new Font("Garamond", Font.ITALIC, 20);
        JLabel lpizza = new JLabel("Pizza:");
        lpizza.setFont(font1);
        lpizza.setForeground(Color.BLUE);
        lpizza.setBounds(10, 80, 200, 50);
        pPracownik.add(lpizza);

        JLabel ladd = new JLabel("Dodatek:");
        ladd.setFont(font1);
        ladd.setForeground(Color.BLUE);
        ladd.setBounds(210, 80, 200, 50);
        pPracownik.add(ladd);

        cbi = new JComboBox(ingridient);
        cbi.setBounds(310, 80, 130, 50);
        pPracownik.add(cbi);

        cbpizza = new JComboBox(Pizza);
        cbpizza.setBounds(80, 80, 120, 50);
        pPracownik.add(cbpizza);

        JLabel ldel = new JLabel("Własna kompozycja:");
        ldel.setFont(font1);
        ldel.setForeground(Color.BLUE);
        ldel.setBounds(20, 150, 250, 50);
        pPracownik.add(ldel);

        cbi1 = new JComboBox(ingridient);
        cbi1.setBounds(20, 200, 80, 50);
        pPracownik.add(cbi1);

        cbi2 = new JComboBox(ingridient);
        cbi2.setBounds(100, 200, 80, 50);
        pPracownik.add(cbi2);

        cbi3 = new JComboBox(ingridient);
        cbi3.setBounds(180, 200, 80, 50);
        pPracownik.add(cbi3);

        cbi4 = new JComboBox(ingridient);
        cbi4.setBounds(260, 200, 80, 50);
        pPracownik.add(cbi4);

        cbi5 = new JComboBox(ingridient);
        cbi5.setBounds(340, 200, 80, 50);
        pPracownik.add(cbi5);

        JLabel lil = new JLabel("Ilość:");
        lil.setFont(font1);
        lil.setForeground(Color.BLUE);
        lil.setBounds(20, 270, 200, 50);
        pPracownik.add(lil);

        SpinnerModel model = new SpinnerNumberModel(1, 1, 10, 1);
        jsi = new JSpinner(model);
        jsi.setBounds(80, 270, 50, 50);
        pPracownik.add(jsi);

        bdodaj = new JButton("Dodaj Pizze");
        bdodaj.addActionListener(this);
        bdodaj.setBounds(160, 270, 260, 50);
        bdodaj.addActionListener(this);
        pPracownik.add(bdodaj);

        JLabel lad = new JLabel("Dodano:");
        lad.setFont(font1);
        lad.setForeground(Color.BLUE);
        lad.setBounds(20, 330, 250, 50);
        pPracownik.add(lad);

        tforder = new JTextField();
        tforder.setBounds(120, 330, 300, 50);
        pPracownik.add(tforder);

        JLabel lar = new JLabel("Adres:");
        lar.setFont(font1);
        lar.setForeground(Color.BLUE);
        lar.setBounds(20, 400, 250, 50);
        pPracownik.add(lar);

        taadres = new JTextArea();
        taadres.setBounds(100, 400, 320, 50);
        taadres.setFont(font1);
        pPracownik.add(taadres);

        terminal = new JCheckBox("Terminal");
        terminal.setBounds(20, 460, 100, 50);
        terminal.setBackground(new Color(10, 20, 30));
        terminal.setFont(new Font("Garamond", Font.ITALIC, 15));
        terminal.setForeground(Color.BLUE);
        pPracownik.add(terminal);

        border = new JButton("Złóż zamówienie");
        border.setBounds(140, 460, 270, 50);
        border.addActionListener(this);
        pPracownik.add(border);

        JLabel lza = new JLabel("Zamówienia");
        lza.setFont(font);
        lza.setForeground(Color.RED);
        lza.setBounds(530, 20, 400, 50);
        pPracownik.add(lza);
        tforders = new JTextField();
        tforders.setBounds(480, 80, 380, 400);
        pPracownik.add(tforders);

        JLabel la = new JLabel("ID: ");
        la.setFont(font1);
        la.setForeground(Color.BLUE);
        la.setBounds(470, 500, 60, 50);
        pPracownik.add(la);

        taid = new JTextArea();
        taid.setBounds(520, 510, 150, 30);
        taid.setFont(font1);
        pPracownik.add(taid);

        bwydano = new JButton("Wydano");
        bwydano.addActionListener(this);
        bwydano.setBounds(680, 500, 200, 50);
        pPracownik.add(bwydano);

        return pPracownik;
    }

    public static void main(String[] args) {
        PizzaNoc okno = new PizzaNoc();
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setVisible(true);
        okno.setResizable(false);

    }

    // Obsługa zdarzeń 
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if (o == bLogin) { //Przycisk Login panel logowania
            if (login.getText().contains("dostawca")) {
                card.show(container, "pDostawca");
                setSize(900, 550);
                return;
            }
            if (login.getText().contains("kucharz")) {
                card.show(container, "pPracownik");
                setSize(900, 600);
                return;
            }
            if (login.getText().contains("roz")) {
                card.show(container, "pRoz");
                setSize(450, 600);
                return;
            }
            return;
        }
        if (o == bReset) {  //Przycisk reset panel logowania
            login.setText("");
            password.setText("");
            return;
        }
        if (o == bExit) { //Przycisk exit panel logowania
            exit(0);
            return;
        }

        //******************************************************
        //*****************   Doręczyciel   *************************
        if (o == borderdone) {  //Dostarczenie zamówienia
            String id = taid.getText();
            //TODO: SQL zmiana realizacji zamówienia nr=id na zakończone 
            return;
        }

        //******************************************************
        //*****************   Kucharz   *************************
        if (o == bdodaj) {  //Dodawanie pizzy
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

        if (o == border) { //Składanie zamówienia 
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
        if (o == bwydano) { //Wydawanie pizzy z kuchni
            String id = taid.getText();
            //Todo zmiana statusu zamówienia o nr id id na wydano
            return;
        }

        //******************************************************
        //*****************   Dostawca *************************
        if (o == bneeded) {   // Sprawdza zaopatrzenie dla 
            //Todo stan zapotrzebowania z bazy wyświetlające się w okienku JDialog
            return;
        }
        if (o == bdodaj1) { //Dodanie produktu 
            return;
        }
        if (o == bcofni) {  //Cofnięcie produktu 
            return;
        }

        if (o == bconst) { //Stałe zamówienie
            return;
        }

        if (o == bdeleteorder) { //Usuń zamowienie
            return;
        }
        if (o == bcheckorder) { //Sprawdz zamówienie
            return;
        }
        if (o == bedit) {  //Edytuj stałe zamówienie
            return;
        }
        if (o == bdostawa) {  // Dodaj zamowienie do bazy 
            return;
        }

    }

}
