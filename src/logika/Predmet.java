package logika;

import java.util.HashMap;
import java.util.Map;

/**
 * Třída Predmet definuje jednotlivé věci a předměty ve hře
 *
 * @author Matěj Pavinger
 * @version pro školní rok 2021/2022
 */
public class Predmet {                          //proměnné se kterými pracuji ve třídě Predmet
    private String nazev;
    private boolean lzeSebrat;
    private boolean lzeProzkoumat;
    private Map<String, Predmet> seznamPredmetu;

    /**
     * Konstruktor třídy
     *
     * @param nazev         název předmětu
     * @param lzeSebrat     boolean prokud lze předmět sebrat
     * @param lzeProzkoumat boolean pokud lze předmět prozkoumat
     */

    public Predmet(String nazev, boolean lzeSebrat, boolean lzeProzkoumat) {
        this.nazev = nazev;
        this.lzeSebrat = lzeSebrat;
        this.lzeProzkoumat = lzeProzkoumat;
        this.seznamPredmetu = new HashMap<String, Predmet>();

    }

    /**
     * Metoda vrací název předmětu
     *
     * @return název předmětu
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * Metoda vrací boolean jestli lze předmět sebrat
     *
     * @return boolean jestli předmět lze sebrat
     */

    public boolean isLzeSebrat() {
        return lzeSebrat;
    }

    /**
     * Metoda vrací boolean jestli lze předmět prozkoumat
     *
     * @return boolean jestli lze předmět prozkoumat
     */

    public boolean isLzeProzkoumat() {
        return lzeProzkoumat;
    }

    /**
     * Metoda vrací odkaz na seznam předmětů uvnitř předmětu
     *
     * @return odkaz na seznam předmětů uvnitř předmětu
     */

    public Map<String, Predmet> getSeznamPredmetu() {
        return seznamPredmetu;
    }

    /**
     * Metoda vloží do předmětu jiný předmět
     *
     * @param predmet Předmět, který chci vložit
     */

    public void pridejPredmet(Predmet predmet) {
        seznamPredmetu.put(predmet.getNazev(), predmet);
    }

    /**
     * Metoda vyprázdní předmět
     *
     * @return Boolean jesttli v daném předmětu jsou jiné předměty
     */

    public boolean vyhozeniPredmetu() {
        if (seznamPredmetu.isEmpty()) {
            return false;
        } else {
            seznamPredmetu.clear();
            return true;
        }
    }

    /**
     * Metoda vrací string předmětů uložených v seznamu předměů
     *
     * @return seznam předmětů ve stringu
     */
    public String vypisSeznamPredmet() {
        String seznam = "";
        if (seznamPredmetu.isEmpty()) {
            return "0";
        }
        for (String s : seznamPredmetu.keySet()) {
            seznam += " " + seznamPredmetu.get(s).getNazev();
        }
        return seznam;
    }
}
