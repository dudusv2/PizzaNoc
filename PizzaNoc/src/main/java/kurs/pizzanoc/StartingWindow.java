package kurs.pizzanoc;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

public class StartingWindow extends JFrame implements ActionListener{
    private JButton bLogin;
    private JButton bReset;
    private JButton bExit;
    private JButton test;

    private JTextArea login;

    private JPasswordField password;

    private PizzaNoc pizzanoc;

    public StartingWindow(PizzaNoc p) {
        super("PizzaNoc - Baza Danych");
        this.pizzanoc = p;

        this.setBackground(new Color(10, 20, 30));
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


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
        add(bLogin);
        add(bReset);
        add(bExit);

        test = new JButton("TEST");
        test.addActionListener(this);
        test.setBounds(400, 50, 180, 50);
        add(test);

        JLabel logo = new JLabel("Witaj w Pizza Noc");
        int style = Font.BOLD;
        Font font = new Font("Garamond", style, 35);
        logo.setFont(font);
        logo.setForeground(Color.RED);
        logo.setBounds(120, 20, 400, 50);

        add(logo);

        JLabel log = new JLabel("Login: ");
        log.setFont(font);
        log.setForeground(Color.BLUE);
        log.setBounds(50, 100, 240, 50);
        add(log);

        login = new JTextArea();
        setUpTraversalPolicy(login);
        login.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    tryLogin();
                }
            }

        });

        login.getDocument().putProperty("filterNewlines", Boolean.TRUE);
        login.setBounds(80, 160, 400, 40);
        login.setFont(new Font("Garamond", Font.CENTER_BASELINE, 25));
        login.setSelectedTextColor(Color.red);
        login.setToolTipText("Login");
        add(login);

        JLabel lpas = new JLabel("Password: ");
        lpas.setFont(font);
        lpas.setForeground(Color.BLUE);
        lpas.setBounds(50, 220, 240, 50);
        add(lpas);

        password = new JPasswordField();
        setUpTraversalPolicy(password);
        password.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    tryLogin();
                }
            }

        });

        password.setEchoChar('*');
        password.setToolTipText("Password");
        password.setBounds(80, 270, 400, 40);
        add(password);
    }

    public void tryLogin() {
        String usr = login.getText();
        String pass = String.valueOf(password.getPassword());
        String check = this.pizzanoc.mysql().correctData(usr, pass);
        if( !(check.equals("WRONG")) ) {
            if (login.getText().contains("DostawcaTowaru")) {
                pizzanoc.showDostawcaTowaru();
                this.dispose();
                return;
            }
            if (login.getText().contains("Kucharz")) {
                pizzanoc.showKucharz();
                this.dispose();
                return;
            }
            if (login.getText().contains("DostawcaPizzy")) {
                pizzanoc.showDostawcaPizzy();
                this.dispose();
                return;
            }
            return;
        } else {
            JOptionPane.showMessageDialog(null, "Wrong login or password!", "Login Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == test) { //Przycisk Login panel logowania
            pizzanoc.showKucharz();
            this.dispose();
            return;
        }

        if (o == bLogin) { //Przycisk Login panel logowania
            tryLogin();
        }

        if (o == bReset) {  //Przycisk reset panel logowania
            login.setText("");
            password.setText("");
            return;
        }
        if (o == bExit) { //Przycisk exit panel logowania
            exit(0);
        }
    }

    public void setUpTraversalPolicy(final JComponent c) {
        // ustawiam przechodzenie klawiszem tab do przodu
        final Set<KeyStroke> forewardStrokes = new HashSet<KeyStroke>(
                Arrays.asList(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0)));
        c.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,
                forewardStrokes);
        // ustawiam przechodzenie klawiszem shift+tab do tyłu
        final Set<KeyStroke> backwardStrokes = new HashSet<KeyStroke>(
                Arrays.asList(KeyStroke.getKeyStroke(KeyEvent.VK_TAB,
                        KeyEvent.SHIFT_DOWN_MASK)));
        c.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS,
                backwardStrokes);
    }
}
