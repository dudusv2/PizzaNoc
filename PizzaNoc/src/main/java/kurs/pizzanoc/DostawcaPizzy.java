package kurs.pizzanoc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Panel dla Doręczyciela pizzy 
public class DostawcaPizzy extends JFrame implements ActionListener {

    private JTextField tforders;

    private JTextArea taid;

    private JButton borderdone;

    DostawcaPizzy() {
        super("PizzaNoc - Baza Danych");

        setLayout(null);
        setSize(450, 600);
        setBackground(new Color(10, 20, 30));

        int style = Font.BOLD;
        Font font = new Font("Garamond", style, 30);
        Font font1 = new Font("Garamond", Font.ITALIC, 20);
        JLabel lza = new JLabel("Zamówienia");
        lza.setFont(font);
        lza.setForeground(Color.RED);
        lza.setBounds(30, 20, 400, 50);
        add(lza);
        tforders = new JTextField();
        tforders.setBounds(20, 80, 400, 400);
        add(tforders);

        JLabel la = new JLabel("ID: ");
        la.setFont(font1);
        la.setForeground(Color.BLUE);
        la.setBounds(20, 500, 60, 50);
        add(la);

        taid = new JTextArea();
        taid.setBounds(60, 510, 150, 30);
        taid.setFont(font1);
        add(taid);

        borderdone = new JButton("Zrealizowano");
        borderdone.addActionListener(this);
        borderdone.setBounds(220, 500, 200, 50);
        add(borderdone);
    }
    // Obsługa zdarzeń
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == borderdone) {  //Dostarczenie zamówienia
            String id = taid.getText();
            //TODO: SQL zmiana realizacji zamówienia nr=id na zakończone
        }
    }
}
