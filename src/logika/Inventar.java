package logika;

import java.util.HashMap;
import java.util.Map;

/**
 * Třída inventář implementuje do hry inventář.
 *
 * @author Matěj Pavinger
 * @version pro školní rok 2021/2022
 */
public class Inventar {
    private Map<String, Predmet> seznamPredmetu;
    private int pocetVeci;

    /**
     * Konstruktor pro třídu inventář.
     * Nastavuje proměnnou pocetVeci, ta nastavuje počet věcí v inventáři.
     */
    public Inventar() {
        this.seznamPredmetu = new HashMap<String, Predmet>();
        pocetVeci = 0;
    }

    /**
     * Metoda vloží do inventáře jiný předmět, zároveň pracuje s proměnnou pocetVeci.
     *
     * @param predmet Předmět, který chci vložit
     */

    public void pridejPredmet(Predmet predmet) {
        seznamPredmetu.put(predmet.getNazev(), predmet);
        pocetVeci++;
    }

    /**
     * Metoda odstraní jeden konkrétní předmět z inventáře.
     * Kontroluje zda věc v inventáři skutečně je.
     *
     * @param nazev název předmětu
     * @return Boolean jesttli v inventáři věč je nebo není
     */

    public boolean vyhozeniPredmetu(String nazev) {
        if (seznamPredmetu.containsKey(nazev)) {
            seznamPredmetu.remove(nazev);
            pocetVeci--;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metoda vrací string předmětů uložených v inventáři.
     *
     * @return seznam předmětů ve inventáři
     */
    public String vypisSeznamPredmet() {
        String seznam = "";
        if (seznamPredmetu.isEmpty()) {
            return "žádné";
        }
        for (String s : seznamPredmetu.keySet()) {
            seznam += " " + seznamPredmetu.get(s).getNazev();
        }
        return seznam;
    }

    /**
     * Metoda vrací odkaz na seznam předmětů uvnitř předmětu.
     *
     * @return odkaz na seznam předmětů uvnitř předmětu
     */

    public Map<String, Predmet> getSeznamPredmetu() {
        return seznamPredmetu;
    }

    /**
     * Metoda vrací počet předmětů v inventáři.
     *
     * @return počet předmětů v inventáři
     */
    public int getPocetVeci() {
        return pocetVeci;
    }
}
