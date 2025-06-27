package Fisch_Package;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern; // für Datum
import java.awt.Color; // für Farben

public class CatchLog extends JFrame {

    // GUI-Komponenten
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

    // Liste für alle gespeicherten Fische
    private ArrayList<Fisch> FischListe = new ArrayList<>();

    public CatchLog() {
        setTitle("CatchLog");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 600);
        setContentPane(CatchLog);
        CatchLog.setBackground(new Color(200, 220, 240));  // Hintergrundfarbe

        ausgabe_textarea.setEditable(false); // Textfeld nur lesbar

        // Platzhalter für Datum
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

        // Buttons einfärben
        eintragen_button.setBackground(new Color(70, 130, 180));
        ausgabe_jbutton.setBackground(new Color(70, 130, 180));
        auswahlLöschen_jbutton.setBackground(new Color(70, 130, 180));
        nurBehalten_jbutton.setBackground(new Color(100, 160, 200));
        nurZurueckgesetzt_jbutton.setBackground(new Color(100, 160, 200));

        setVisible(true);

        // Beispielobjekte laden
        initObjekte();

        // ActionListener setzen
        eintragen_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eintragenFisch();
            }
        });

        ausgabe_jbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anzeigenAlleFische();
            }
        });

        nurBehalten_jbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anzeigenBehalteneFische();
            }
        });

        nurZurueckgesetzt_jbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anzeigenZurueckgesetzteFische();
            }
        });

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

    // Initial drei Fische hinzufügen
    private void initObjekte() {
        Fisch f1 = new Fisch("Zander", 45.0, 1200.0, "Bodensee", "01.05.2024", true);
        Fisch f2 = new Fisch("Hecht", 78.0, 3500.0, "Zellersee", "12.04.2024", false);
        Fisch f3 = new Fisch("Karpfen", 60.0, 5000.0, "Baggersee Sattenbeuren", "20.03.2024", true);
        FischListe.add(f1);
        FischListe.add(f2);
        FischListe.add(f3);
    }

    // Neuen Fisch eintragen
    private void eintragenFisch() {
        String fischart = (fischart_combobox.getSelectedItem() != null) ? fischart_combobox.getSelectedItem().toString().trim() : "";
        String datum = datum_textfeld.getText().trim();
        String ort = ort_textfeld.getText().trim();

        // Pflichtfelder prüfen
        if (fischart.isEmpty() || datum.isEmpty() || ort.isEmpty() ||
                groeße_textfeld.getText().trim().isEmpty() || gewicht_textfeld.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(CatchLog, "Bitte alle Felder ausfüllen!", "Fehler", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Ort muss Text enthalten
        if (!Pattern.matches(".*[a-zA-ZäöüÄÖÜß].*", ort)) {
            JOptionPane.showMessageDialog(CatchLog, "Bitte einen gültigen Ort eingeben!", "Fehler", JOptionPane.ERROR_MESSAGE);
            ort_textfeld.requestFocusInWindow();
            return;
        }

        // Datum prüfen
        if (!Pattern.matches("\\d{2}\\.\\d{2}\\.\\d{4}", datum)) {
            JOptionPane.showMessageDialog(CatchLog, "Datum bitte im Format TT.MM.JJJJ angeben.", "Fehler", JOptionPane.ERROR_MESSAGE);
            datum_textfeld.requestFocusInWindow();
            return;
        }

        // Tag/Monat prüfen
        String[] parts = datum.split("\\.");
        int tag = Integer.parseInt(parts[0]);
        int monat = Integer.parseInt(parts[1]);
        if (tag < 1 || tag > 31 || monat < 1 || monat > 12) {
            JOptionPane.showMessageDialog(CatchLog, "Tag 1–31 und Monat 1–12 angeben!", "Fehler", JOptionPane.ERROR_MESSAGE);
            datum_textfeld.requestFocusInWindow();
            return;
        }

        boolean gefangen = gefangen_jradiobutton.isSelected();
        double groeße;
        double gewicht;

        try {
            groeße = Double.parseDouble(groeße_textfeld.getText());
            gewicht = Double.parseDouble(gewicht_textfeld.getText());
            if (groeße <= 0 || gewicht <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(CatchLog, "Größe und Gewicht nur als positive Zahl!", "Fehler", JOptionPane.ERROR_MESSAGE);
            groeße_textfeld.requestFocusInWindow();
            return;
        }

        // Fisch speichern
        Fisch fisch = new Fisch(fischart, groeße, gewicht, ort, datum, gefangen);
        FischListe.add(fisch);

        JOptionPane.showMessageDialog(CatchLog, "Fang erfolgreich gespeichert!", "Erfolg", JOptionPane.INFORMATION_MESSAGE);
        clearTextfields();
        datum_textfeld.requestFocusInWindow();
    }

    // Alle Fische anzeigen
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

    // nur behaltene anzeigen
    private void anzeigenBehalteneFische() {
        ausgabe_textarea.setText("");
        for (Fisch fisch : FischListe) {
            if (fisch.isBehalten()) {
                ausgabe_textarea.append(fisch.ausgeben() + "\n");
            }
        }
    }

    // nur zurückgesetzte anzeigen
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
