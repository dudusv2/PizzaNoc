/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kurs.pizzanoc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

/**
 *
 * @author jakub
 */
public class PizzaNoc {

    private StartingWindow start;
    private DostawcaTowaru dostawcaTowaru;
    private DostawcaPizzy dostawcaPizzy;
    private Kucharz kucharz;

    PizzaNoc() {
        start = new StartingWindow(this);
        start.setLocationRelativeTo(null);
        start.setVisible(true);
        start.setResizable(false);
    }

    public void showDostawcaPizzy() {
        dostawcaPizzy = new DostawcaPizzy();
        dostawcaPizzy.setLocationRelativeTo(null);
        dostawcaPizzy.setVisible(true);
        dostawcaPizzy.setResizable(false);
    }

    public void showKucharz() {
        kucharz = new Kucharz();
        kucharz.setLocationRelativeTo(null);
        kucharz.setVisible(true);
        kucharz.setResizable(false);
    }

    public void showDostawcaTowaru() {
        dostawcaTowaru = new DostawcaTowaru();
        dostawcaTowaru.setLocationRelativeTo(null);
        dostawcaTowaru.setVisible(true);
        dostawcaTowaru.setResizable(false);
    }

    public static void main(String[] args) {
        new PizzaNoc();
    }
}
