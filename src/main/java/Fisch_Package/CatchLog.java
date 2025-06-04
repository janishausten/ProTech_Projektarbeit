package Fisch_Package;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CatchLog extends JFrame {
    private JComboBox fischart_combobox;
    private JTextField datum_textfeld;
    private JTextField groeße_textfeld;
    private JTextField ort_textfeld;
    private JLabel fischart_label;
    private JTextField gewicht_textfeld;
    private JRadioButton gefanngen_jradiobutton;
    private JButton eintragen_button;
    private JButton ausgabe_textfeld;
    private JButton auswahlLöschen_jbutton;
    private JPanel CatchLog;
    private JLabel datum_label;
    private JLabel groeße_label;
    private JLabel ort_label;
    private JLabel gewicht_label;
    private JLabel gefangen_label;
    private JTextField neueFischart_textfeld;

    //Erstellen der Arraylist, in welcher die Fische gespeichert werden
    private ArrayList<Fisch> FischListe = new ArrayList<Fisch>();

    //Erstellen eines Konstruktors
    public CatchLog() {
        setTitle("Catch Log");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500, 1500);
        setContentPane(CatchLog);
        setVisible(true);


        eintragen_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        auswahlLöschen_jbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        ausgabe_textfeld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


    public static void main(String[] args) {
        new CatchLog();
    }
}
