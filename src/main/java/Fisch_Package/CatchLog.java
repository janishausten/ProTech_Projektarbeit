package Fisch_Package;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern; // Datum
import java.awt.Color; // Farben

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
    private JTextArea ausgabe_textarea;

    private ArrayList<Fisch> FischListe = new ArrayList<>();

    public CatchLog() {
        setTitle("Catch Log");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 600);
        setContentPane(CatchLog);
        // Hintergrundfarbe für das Panel
        CatchLog.setBackground(new Color(200, 220, 240));  // helles Angelblau

        // Button-Farben direkt setzen
        eintragen_button.setBackground(new Color(70, 130, 180));   // Blau
        eintragen_button.setForeground(Color.BLACK);

        ausgabe_jbutton.setBackground(new Color(70, 130, 180));
        ausgabe_jbutton.setForeground(Color.BLACK);

        auswahlLöschen_jbutton.setBackground(new Color(70, 130, 180));
        auswahlLöschen_jbutton.setForeground(Color.BLACK);
        setVisible(true);
        initObjekte();

        // Eintragen-Button
        eintragen_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fischart = fischart_combobox.getSelectedItem().toString();
                String datum = datum_textfeld.getText();
                if (!Pattern.matches("\\d{2}\\.\\d{2}\\.\\d{4}", datum)) {
                    JOptionPane.showMessageDialog(CatchLog, "Bitte gib das Datum im Format TT.MM.JJJJ ein!", "Fehler", JOptionPane.ERROR_MESSAGE);
                    datum_textfeld.setText("");
                    return;
                }
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
                ausgabe_textarea.setText(""); // vorherige Ausgabe löschen

                if (FischListe.isEmpty()) {
                    ausgabe_textarea.setText("Noch keine Fänge eingetragen.");
                    return;
                }

                for (Fisch fisch : FischListe) {
                    ausgabe_textarea.setText(ausgabe_textarea.getText() + fisch.ausgeben() + "\n");
                }
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

    private void initObjekte() {
        Fisch f1 = new Fisch("Zander", 45.0, 1200.0, "Bodensee", "01.05.2024", true);
        Fisch f2 = new Fisch("Hecht", 78.0, 3500.0, "Zellersee", "12.04.2024", false);
        Fisch f3 = new Fisch("Karpfen", 60.0, 5000.0, "Baggersee Sattenbeuren", "20.03.2024", true);

        FischListe.add(f1);
        FischListe.add(f2);
        FischListe.add(f3);
    }

    public static void main(String[] args) {
        new CatchLog();
    }
}
