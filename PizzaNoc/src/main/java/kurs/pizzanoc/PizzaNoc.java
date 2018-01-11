package kurs.pizzanoc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author jakub
 * @author Amadeusz
 *
 */

public class PizzaNoc {

    private StartingWindow start;
    private DostawcaTowaru dostawcaTowaru;
    private DostawcaPizzy dostawcaPizzy;
    private Kucharz kucharz;

    private MySQLConnect mysqlConnect;

    PizzaNoc() {
        mysqlConnect = new MySQLConnect();

        start = new StartingWindow(this);
        start.setLocationRelativeTo(null);
        start.setVisible(true);
        start.setResizable(false);
    }

    public MySQLConnect mysql() {
        return this.mysqlConnect;
    }

    public void showDostawcaPizzy() {
        dostawcaPizzy = new DostawcaPizzy(this);
        dostawcaPizzy.setLocationRelativeTo(null);
        dostawcaPizzy.setVisible(true);
        dostawcaPizzy.setResizable(false);
    }

    public void showKucharz() {
        kucharz = new Kucharz(this);
        kucharz.setLocationRelativeTo(null);
        kucharz.setVisible(true);
        kucharz.setResizable(false);
    }

    public void showDostawcaTowaru() {
        dostawcaTowaru = new DostawcaTowaru(this);
        dostawcaTowaru.setLocationRelativeTo(null);
        dostawcaTowaru.setVisible(true);
        dostawcaTowaru.setResizable(false);
    }

    public static void main(String[] args) {
        new PizzaNoc();
    }
}
