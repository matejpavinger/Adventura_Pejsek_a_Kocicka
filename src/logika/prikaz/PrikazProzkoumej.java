package logika.prikaz;

import logika.Hra;
import logika.Predmet;

import java.util.Map;

/**
 * Třída Prikaz Prozkoumej implementuje pro hru příkaz prozkoumej.
 * Tato třída je součástí jednoduché textové hry.
 * Prozkoumá prozkoumatelný předmět v aktuálním prostoru. (vypíše seznam předmětů, co se nachází v seznamu předmětů)
 *
 * @author Matěj Pavinger
 * @version pro školní rok 2021/2022
 */
final public class PrikazProzkoumej extends Prikaz {

    private static final String NAZEV = "prozkoumej";
    private final Hra hra;

    /**
     * Konstruktor třídy
     * @param hra instance hry, která se používá
     */
    public PrikazProzkoumej(Hra hra) {
        this.hra = hra;
    }

    /**
     * Zjistí, zda daný předmět lze prozkoumat a vypíše seznam předmětů v předmětu.
     * Kontroluje, zda hráč zadal, co se má prozkoumat.
     * V případě: prozkoumej seznam_ingrediencí vypíše seznam ingrediencí (věci, co mají pejsek s kočičkou rádi a co je
     * potřeba nasbírat pro úspěšné dokončení hry)
     * Kontroluje, zda daný předmět je v aktualním prostoru.
     * Kontroluje, zda je předmět prozkoumatelný.
     *
     * @param parametry název předmětu, co se má prozkoumat
     * @return zprávu o prozkoumání a seznamPredmetu
     */
    @Override
    // kontroluje zda hráč zadal, co má prozkoumat
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "musíš zadat, co mám prohledat...";
        }
        // kontroluje zda hráč zadal prozkoumej seznam_veci
        if (parametry[0].equals("seznam_ingrediencí") && hra.getInventar().getSeznamPredmetu().containsKey("seznam_ingrediencí")) {
            return "SEZNAM VĚCÍ, CO MAJÍ PEJSEK S KOČIČKOU RÁDI\n\n" +
                    "-čokoláda\n" +
                    "-cibule\n" +
                    "-česnek\n" +
                    "-bonbóny\n" +
                    "-mouka\n" +
                    "-okurky\n" +
                    "-syrečky\n" +
                    "-buřty\n" +
                    "-vajíčko\n" +
                    "-mléko\n" +
                    "-kost\n";
        }
        // kontroluje zda se předmět, který se má prozkoumat nachází v aktuálním prostoru
        Predmet predmet = hra.getHerniPlan().getAktualniProstor().getPredmet(parametry[0]);
        if (predmet == null) {
            return "tento předmět, tady není...";
        }
        // kontroluje zda je předmět prozkoumatelný
        if (!predmet.isLzeProzkoumat()) {
            return "věc nelze prozkoumat";
        }
        // vypisuje pokud v předmětu nic není
        Map<String, Predmet> seznamVeci = predmet.getSeznamPredmetu();
        if (seznamVeci == null) {
            return "tady nic není...";
        }
        // vypisuje pokud v předmětu něco je
        for (String vec : seznamVeci.keySet()) {
            hra.getHerniPlan().getAktualniProstor().pridejPredmet(seznamVeci.get(vec));
        }
        String nalezeneVeci = predmet.vypisSeznamPredmet();
        predmet.getSeznamPredmetu().clear();
        return "nalezl jsi tyto předměty: " + nalezeneVeci + "\n\n" + hra.getHerniPlan().getAktualniProstor().dlouhyPopis();
    }

    /**
     * Metoda vrací název příkazu (prozkoumej...)
     *
     * @return název příkazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
