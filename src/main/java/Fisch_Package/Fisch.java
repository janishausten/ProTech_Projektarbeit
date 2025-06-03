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

    public String ausgeben (){
        System.out.println("Fischart: " + fischart);
        System.out.println("Größe: " + groeßeInCm + " cm");
        System.out.println("Gewicht: " + gewichtInGramm + " g");
        System.out.println("Ort: " + ort);
        System.out.println("Datum: " + datum);
        System.out.println("Gefangen: " + (gefangen ? "behalten" : "zurückgesetzt"));

    }
}
