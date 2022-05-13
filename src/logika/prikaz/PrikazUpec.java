package logika.prikaz;

import logika.Hra;
import logika.Inventar;
import logika.Predmet;
import logika.Prostor;

import java.util.Map;

/**
 * Třída PrikazUpec implementuje pro hru příkaz upec.
 * Tato třída je součástí jednoduché textové hry.
 * Upeče dort, který je potřeba pro úspěšné dokončení hry.
 *
 * @author Matěj Pavinger
 * @version pro školní rok 2021/2022
 */

final public class PrikazUpec extends Prikaz {
    private static final String NAZEV = "upeč";
    private final Hra hra;

    /**
     * Konstruktor třídy
     * @param hra instance hry, která se používá
     */
    public PrikazUpec(Hra hra) {
        this.hra = hra;
    }

    /**
     * Kontroluje, zda je hráč v kuchyni.
     * Kontroluje, zda má hráč všechny ingredience, pokud ano, vyhodí je z inventáře a hráč obdrží do inventáře dort.
     *
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     * @return text po zadání příkazu upeč
     */
    @Override
    // kontrola prostoru
    public String provedPrikaz(String... parametry) {
        Inventar inventar = hra.getInventar();
        Map<String, Predmet> veci = inventar.getSeznamPredmetu();
        Prostor prostor = hra.getHerniPlan().getAktualniProstor();
        if (!prostor.getNazev().equals("kuchyň")) {
            return "dort jde přece upéct jenom v kuchyni";
        }
        // kontrola ingrediencí
        if (veci.containsKey("čokoláda") && veci.containsKey("cibule") && veci.containsKey("česnek")
                && veci.containsKey("bonbóny") && veci.containsKey("syrečky")
                && veci.containsKey("mouka") && veci.containsKey("okurky")
                && veci.containsKey("buřty") && veci.containsKey("mísa")
                && veci.containsKey("vajíčko") && veci.containsKey("mléko")
                && veci.containsKey("kost")) {
            // vyhození předmětů a vkládání dortu do invetáře
            inventar.vyhozeniPredmetu("čokoláda");
            inventar.vyhozeniPredmetu("cibule");
            inventar.vyhozeniPredmetu("česnek");
            inventar.vyhozeniPredmetu("bonbóny");
            inventar.vyhozeniPredmetu("syrečky");
            inventar.vyhozeniPredmetu("mouka");
            inventar.vyhozeniPredmetu("okurky");
            inventar.vyhozeniPredmetu("buřty");
            inventar.vyhozeniPredmetu("vajíčko");
            inventar.vyhozeniPredmetu("mléko");
            inventar.vyhozeniPredmetu("kost");

            inventar.pridejPredmet(new Predmet("dort", true, false));
            return "upekl jsi dort";
        }
        return "ještě ti něco chybí, takhle dort nebude kompletní";
    }

    /**
     * Metoda vrací název příkazu (upeč dort)
     *
     * @return název příkazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
