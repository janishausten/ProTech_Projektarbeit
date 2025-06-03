package Fisch_Package;

import javax.swing.*;

public class CatchLog extends JFrame {
    private JComboBox comboBox1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JLabel FIschart_textField;
    private JTextField textField4;
    private JRadioButton jaRadioButton;
    private JButton eintragenButton;
    private JButton fangbuchAnzeigenButton;
    private JButton auswahlLöschenButton;
    private JPanel CatchLog;


    //Erstellen eines Konstruktors
    public CatchLog() {
        setTitle("Catch Log");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500, 1500);
        setContentPane(CatchLog);
        setVisible(true);
    }

}
