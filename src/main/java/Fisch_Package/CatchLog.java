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
    private JRadioButton gefangen_jradiobutton;
    private JButton eintragen_button;
    private JButton ausgabe_jbutton;
    private JButton auswahlLöschen_jbutton;
    private JPanel CatchLog;
    private JLabel datum_label;
    private JLabel groeße_label;
    private JLabel ort_label;
    private JLabel gewicht_label;
    private JLabel gefangen_label;

    private ArrayList<Fisch> FischListe = new ArrayList<>();

    public CatchLog() {
        setTitle("Catch Log");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 600);
        setContentPane(CatchLog);
        setVisible(true);

        // Eintragen-Button
        eintragen_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fischart = fischart_combobox.getSelectedItem().toString();
                String datum = datum_textfeld.getText();
                String ort = ort_textfeld.getText();
                boolean gefangen = gefangen_jradiobutton.isSelected();
                double groeße;
                double gewicht;

                // Try catch
                try {
                    groeße = Double.parseDouble(groeße_textfeld.getText());
                    gewicht = Double.parseDouble(gewicht_textfeld.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(CatchLog, "Bitte nur Zahlen für Größe und Gewicht eingeben!", "Fehler", JOptionPane.ERROR_MESSAGE);
                    groeße_textfeld.setText("");
                    gewicht_textfeld.setText("");
                    return;
                }

                // Pflichtfelder prüfen
                if (fischart.isEmpty() || datum.isEmpty() || ort.isEmpty()) {
                    JOptionPane.showMessageDialog(CatchLog, "Bitte alle Felder ausfüllen!", "Fehler", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Fisch fisch = new Fisch(fischart, groeße, gewicht, ort, datum, gefangen);
                FischListe.add(fisch);

                JOptionPane.showMessageDialog(CatchLog, "Fang erfolgreich gespeichert!", "Erfolg", JOptionPane.INFORMATION_MESSAGE);
                clearTextfields();
            }
        });


        auswahlLöschen_jbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTextfields();

            }
        });


        ausgabe_jbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void clearTextfields() {
        datum_textfeld.setText("");
        groeße_textfeld.setText("");
        gewicht_textfeld.setText("");
        ort_textfeld.setText("");
        gefangen_jradiobutton.setSelected(false);
        fischart_combobox.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new CatchLog();
    }
}
