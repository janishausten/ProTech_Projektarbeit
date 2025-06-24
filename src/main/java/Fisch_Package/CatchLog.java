package Fisch_Package;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern; // für Datum
import java.awt.Color; // für Farben

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
    private JLabel behalten_label;
    private JTextArea ausgabe_textarea;
    private JButton nurBehalten_jbutton;
    private JButton nurZurueckgesetzt_jbutton;

    private ArrayList<Fisch> FischListe = new ArrayList<>();

    public CatchLog() {
        setTitle("CatchLog");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 600);
        setContentPane(CatchLog);
        CatchLog.setBackground(new Color(200, 220, 240));  // helles Angelblau

        // Output text area wird read-only
        ausgabe_textarea.setEditable(false);

        // Placeholder für datum_textfeld
        datum_textfeld.setText("TT.MM.JJJJ");
        datum_textfeld.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (datum_textfeld.getText().equals("TT.MM.JJJJ")) {
                    datum_textfeld.setText("");
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (datum_textfeld.getText().isEmpty()) {
                    datum_textfeld.setText("TT.MM.JJJJ");
                }
            }
        });

        // Button-Styling
        eintragen_button.setBackground(new Color(70, 130, 180));
        eintragen_button.setForeground(Color.BLACK);

        ausgabe_jbutton.setBackground(new Color(70, 130, 180));
        ausgabe_jbutton.setForeground(Color.BLACK);

        auswahlLöschen_jbutton.setBackground(new Color(70, 130, 180));
        auswahlLöschen_jbutton.setForeground(Color.BLACK);

        nurBehalten_jbutton.setBackground(new Color(100, 160, 200));
        nurZurueckgesetzt_jbutton.setBackground(new Color(100, 160, 200));

        setVisible(true);
        initObjekte();

        // Eintragen
        eintragen_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eintragenFisch();
            }
        });

        // Alle Fische anzeigen
        ausgabe_jbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anzeigenAlleFische();
            }
        });

        // Nur behaltene Fische anzeigen
        nurBehalten_jbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anzeigenBehalteneFische();
            }
        });

        // Nur zurückgesetzte Fische anzeigen
        nurZurueckgesetzt_jbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anzeigenZurueckgesetzteFische();
            }
        });

        // Eingaben löschen
        auswahlLöschen_jbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTextfields();
                datum_textfeld.requestFocusInWindow();
            }
        });
    }

    // Eingabefelder zurücksetzen
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

    private void eintragenFisch() {
        String fischart = (fischart_combobox.getSelectedItem() != null) ? fischart_combobox.getSelectedItem().toString().trim() : "";
        String datum = datum_textfeld.getText().trim();
        String ort = ort_textfeld.getText().trim();

        // Wurden alle Felder befüllt?
        if (fischart.isEmpty() || datum.isEmpty() || ort.isEmpty() || groeße_textfeld.getText().trim().isEmpty() || gewicht_textfeld.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(CatchLog, "Bitte alle Felder ausfüllen!", "Fehler", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 'Ort' enthält mindestens einen Buchstaben, nicht nur Zahlen
        if (!Pattern.matches(".*[a-zA-ZäöüÄÖÜß].*", ort)) {
            JOptionPane.showMessageDialog(CatchLog, "Bitte einen gültigen Ort eingeben (muss Text enthalten)!", "Fehler", JOptionPane.ERROR_MESSAGE);
            ort_textfeld.requestFocusInWindow();
            return;
        }

        // Datum auf Format prüfen
        if (!Pattern.matches("\\d{2}\\.\\d{2}\\.\\d{4}", datum)) {
            JOptionPane.showMessageDialog(CatchLog, "Bitte gib das Datum im Format TT.MM.JJJJ ein!", "Fehler", JOptionPane.ERROR_MESSAGE);
            datum_textfeld.setText("");
            datum_textfeld.requestFocusInWindow();
            return;
        }

        boolean gefangen = gefangen_jradiobutton.isSelected();
        double groeße;
        double gewicht;

        // Größe u. Gewicht Zahlen überprüfen und > 0 prüfen
        try {
            groeße = Double.parseDouble(groeße_textfeld.getText());
            gewicht = Double.parseDouble(gewicht_textfeld.getText());
            if (groeße <= 0 || gewicht <= 0) {
                throw new NumberFormatException("Größe und Gewicht müssen positiv sein.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(CatchLog, "Bitte gültige positive Zahlen für Größe und Gewicht eingeben!", "Fehler", JOptionPane.ERROR_MESSAGE);
            groeße_textfeld.setText("");
            gewicht_textfeld.setText("");
            groeße_textfeld.requestFocusInWindow();
            return;
        }

        Fisch fisch = new Fisch(fischart, groeße, gewicht, ort, datum, gefangen);
        FischListe.add(fisch);

        JOptionPane.showMessageDialog(CatchLog, "Fang erfolgreich gespeichert!", "Erfolg", JOptionPane.INFORMATION_MESSAGE);
        clearTextfields();
        datum_textfeld.requestFocusInWindow();
    }

    private void anzeigenAlleFische() {
        ausgabe_textarea.setText("");
        if (FischListe.isEmpty()) {
            ausgabe_textarea.setText("Noch keine Fänge eingetragen.");
            return;
        }
        for (Fisch fisch : FischListe) {
            ausgabe_textarea.append(fisch.ausgeben() + "\n");
        }
    }

    private void anzeigenBehalteneFische() {
        ausgabe_textarea.setText("");
        for (Fisch fisch : FischListe) {
            if (fisch.isBehalten()) {
                ausgabe_textarea.append(fisch.ausgeben() + "\n");
            }
        }
    }

    private void anzeigenZurueckgesetzteFische() {
        ausgabe_textarea.setText("");
        for (Fisch fisch : FischListe) {
            if (!fisch.isBehalten()) {
                ausgabe_textarea.append(fisch.ausgeben() + "\n");
            }
        }
    }

    public static void main(String[] args) {
        new CatchLog();
    }
}
