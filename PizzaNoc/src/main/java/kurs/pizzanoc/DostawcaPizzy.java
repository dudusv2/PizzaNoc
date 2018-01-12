package kurs.pizzanoc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Panel dla Doręczyciela pizzy 
public class DostawcaPizzy extends JFrame implements ActionListener {

    private OrdersTable zamowienia;
    private JButton refresh,bodebrane,bnodebrane;

    private PizzaNoc pizzanoc;
    DostawcaPizzy(PizzaNoc p) {
        super("PizzaNoc - Baza Danych");
        this.pizzanoc = p;

          try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
          
        } 
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
        
        bodebrane = new JButton("odebrane");
        bodebrane.setBounds(40, 520, 150, 50);
        bodebrane.addActionListener(this);
        add(bodebrane);
        
        bnodebrane = new JButton("nieodebrane");
        bnodebrane.setBounds(220, 520, 150, 50);
        bnodebrane.addActionListener(this);
        add(bnodebrane);
        
        
    }
      public void selectOrd() {
        int numOfRows = pizzanoc.mysql().getRowNumbers("orders");
        zamowienia.model.setData(pizzanoc.mysql().getOrders("SELECT * FROM orders", numOfRows));
    }
    // Obsługa zdarzeń
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        
        if(o==bodebrane){
            pizzanoc.mysql().updateOrders("odebrane", zamowienia.getIDofSelectedRow());
            selectOrd();
        }
        if(o==bnodebrane){
            pizzanoc.mysql().updateOrders("nieodebrane", zamowienia.getIDofSelectedRow());
            selectOrd();
        }
        
    }
}
