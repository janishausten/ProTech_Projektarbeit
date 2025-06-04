package Fisch_Package;

import javax.swing.*;

public class CatchLog extends JFrame {
    private JComboBox fischart_combobox;
    private JTextField datum_textfeld;
    private JTextField groeße_textfeld;
    private JTextField ort_textfeld;
    private JLabel fischart_label;
    private JTextField gewicht_textfeld;
    private JRadioButton gefanngen_jradiobutton;
    private JButton eintragen_button;
    private JButton fangbuchAnzeigenButton;
    private JButton auswahlLöschen_jbutton;
    private JPanel CatchLog;
    private JLabel datum_label;
    private JLabel groeße_label;
    private JLabel ort_label;
    private JLabel gewicht_label;
    private JLabel gefangen_label;
    private JTextField neueFischart_textfeld;


    //Erstellen eines Konstruktors
    public CatchLog() {
        setTitle("Catch Log");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500, 1500);
        setContentPane(CatchLog);
        setVisible(true);
    }


}
