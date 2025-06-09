package Fisch_Package;

public class Fisch {
    private String fischart;
    private double groeßeInCm;
    private double gewichtInGramm;
    private String ort;
    private String datum;
    private boolean gefangen;


    public Fisch(String fischart, double groeßeInCm, double gewichtInGramm, String ort, String datum, boolean gefangen) {
        this.fischart = fischart;
        this.groeßeInCm = groeßeInCm;
        this.gewichtInGramm = gewichtInGramm;
        this.ort = ort;
        this.datum = datum;
        this.gefangen = gefangen;
    }

    public boolean isBehalten() {
        return gefangen;
    }

    public String ausgeben() {
        return "Fischart: " + fischart +
                ", Größe: " + groeßeInCm + " cm" +
                ", Gewicht: " + gewichtInGramm + " g" +
                ", Ort: " + ort +
                ", Datum: " + datum +
                ", Gefangen: " + (gefangen ? "behalten" : "zurückgesetzt");
    }
}
