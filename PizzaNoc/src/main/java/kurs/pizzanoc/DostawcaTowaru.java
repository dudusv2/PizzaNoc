package kurs.pizzanoc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

//Panel dla dostawcy towaru
public class DostawcaTowaru extends JFrame implements ActionListener {
    private JButton bneeded;
    private JButton bconst;
    private JButton bedit;
    private JButton bdodaj1;
    private JButton bcofni;
    private JButton bdostawa;
    private JButton bdeleteorder;
    private JButton bcheckorder;

    private JTextField tfadd;
    private JTextField tfdel;

    private JTextArea taprod;
    private JTextArea tavol;

    private JComboBox cbproducts;

    private final String[] ingridient = {"brak", "Sos", "Ser", "Oliwki", "Kiełbasa", "Jarmuż"};

    private PizzaNoc pizzanoc;
    DostawcaTowaru(PizzaNoc p) {
        super("PizzaNoc - Baza Danych");
        this.pizzanoc = p;

        setLayout(null);
        setSize(820, 530);
        setBackground(new Color(10, 20, 30));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                pizzanoc.mysql().disconnect();
                e.getWindow().dispose();
            }
        });

        JLabel logo = new JLabel("Wprowadz dostawę lub ");
        int style = Font.BOLD;
        Font font = new Font("Garamond", style, 35);
        logo.setFont(font);
        logo.setForeground(Color.RED);
        logo.setBounds(20, 20, 500, 50);
        add(logo);

        bneeded = new JButton("Sprawdz zapotrzebowanie");
        bneeded.addActionListener(this);
        bneeded.setBounds(500, 20, 250, 50);
        add(bneeded);

        bconst = new JButton("Dodaj stałą dostawę");
        bconst.addActionListener(this);
        bconst.setBounds(20, 100, 280, 50);
        add(bconst);

        bedit = new JButton("edytuj stałą dostawę");
        bedit.addActionListener(this);
        bedit.setBounds(320, 100, 280, 50);
        add(bedit);

        Font font1 = new Font("Garamond", Font.ITALIC, 25);
        JLabel lproducts = new JLabel("Produkt:");
        lproducts.setFont(font1);
        lproducts.setForeground(Color.BLUE);
        lproducts.setBounds(20, 170, 250, 50);
        add(lproducts);

        JLabel lvol = new JLabel("Gramatura:");
        lvol.setFont(font1);
        lvol.setForeground(Color.BLUE);
        lvol.setBounds(350, 170, 250, 50);
        add(lvol);

        taprod = new JTextArea();
        taprod.setBounds(130, 170, 200, 40);
        taprod.setFont(new Font("Garamond", Font.CENTER_BASELINE, 25));
        taprod.setSelectedTextColor(Color.red);
        taprod.setToolTipText("wpisz składnik");
        add(taprod);

        tavol = new JTextArea();
        tavol.setBounds(500, 170, 200, 40);
        tavol.setFont(new Font("Garamond", Font.CENTER_BASELINE, 25));
        tavol.setSelectedTextColor(Color.red);
        tavol.setToolTipText("podaj gramature w gramach");
        add(tavol);

        cbproducts = new JComboBox(ingridient);
        cbproducts.setBounds(20, 230, 310, 50);

        cbproducts.addActionListener(this);
        add(cbproducts);

        bdodaj1 = new JButton("Dodaj");
        bdodaj1.addActionListener(this);
        bdodaj1.setBounds(350, 230, 170, 50);
        add(bdodaj1);

        bcofni = new JButton("Cofni");
        bcofni.addActionListener(this);
        bcofni.setBounds(530, 230, 170, 50);
        add(bcofni);

        tfadd = new JTextField();
        tfadd.setBounds(130, 300, 200, 50);
        tfadd.setFont(new Font("Garamond", Font.CENTER_BASELINE, 25));
        tfadd.setSelectedTextColor(Color.red);
        tfadd.setToolTipText("dodany produkt");
        add(tfadd);

        JLabel ladd = new JLabel("Dodano:");
        ladd.setFont(font1);
        ladd.setForeground(Color.BLUE);
        ladd.setBounds(20, 300, 200, 50);
        add(ladd);

        JLabel ldel = new JLabel("Cofnięto:");
        ldel.setFont(font1);
        ldel.setForeground(Color.BLUE);
        ldel.setBounds(340, 300, 200, 50);
        add(ldel);

        tfdel = new JTextField();
        tfdel.setBounds(460, 300, 240, 50);
        tfdel.setFont(new Font("Garamond", Font.CENTER_BASELINE, 25));
        tfdel.setSelectedTextColor(Color.red);
        tfdel.setToolTipText("cofnięty produkt");
        add(tfdel);

        bdostawa = new JButton("Dodaj produkty:");
        bdostawa.addActionListener(this);
        bdostawa.setBounds(20, 380, 250, 80);
        add(bdostawa);

        bdeleteorder = new JButton("Usuń dostawę");
        bdeleteorder.addActionListener(this);
        bdeleteorder.setBounds(280, 380, 250, 80);
        add(bdeleteorder);

        bcheckorder = new JButton("Zobacz listę dostawy");
        bcheckorder.addActionListener(this);
        bcheckorder.setBounds(540, 380, 250, 80);
        add(bcheckorder);
    }
    // Obsługa zdarzeń
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
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