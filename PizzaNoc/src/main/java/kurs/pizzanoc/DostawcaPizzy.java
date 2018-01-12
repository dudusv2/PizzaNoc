package kurs.pizzanoc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Panel dla Doręczyciela pizzy 
public class DostawcaPizzy extends JFrame implements ActionListener {

    private OrdersTable zamowienia;
    private JButton refresh;

    private PizzaNoc pizzanoc;
    DostawcaPizzy(PizzaNoc p) {
        super("PizzaNoc - Baza Danych");
        this.pizzanoc = p;

        setLayout(null);
        setSize(550, 600);
        setBackground(new Color(10, 20, 30));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                pizzanoc.mysql().disconnect();
                e.getWindow().dispose();
            }
        });

        int style = Font.BOLD;
        Font font = new Font("Garamond", style, 30);
        Font font1 = new Font("Garamond", Font.ITALIC, 20);
        JLabel lza = new JLabel("Zamówienia");
        lza.setFont(font);
        lza.setForeground(Color.RED);
        lza.setBounds(30, 20, 400, 50);
        add(lza);

        zamowienia = new OrdersTable();
        zamowienia.setBounds(20, 80, 500, 500);
        add(zamowienia);

        refresh = new JButton("REFRESH");
        refresh.setBounds(350, 20, 150, 50);
        refresh.addActionListener(this);
        add(refresh);
    }
    // Obsługa zdarzeń
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == refresh) {
            //TODO: Refresh orders table
        }
    }
}
