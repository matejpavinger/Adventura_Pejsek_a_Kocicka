package logika.prikaz;

import logika.Hra;
import logika.Inventar;
import logika.Predmet;
import logika.Prostor;

/**
 * Třída PrikazSeber implementuje pro hru příkaz seber.
 * Tato třída je součástí jednoduché textové hry.
 * Přidává předmět do inventáře a odebere ho z prostoru, kde se nachází.
 *
 * @author Matěj Pavinger
 */

final public class PrikazSeber extends Prikaz {

    private static final String NAZEV = "seber";
    private final Hra hra;

    /**
     * Konstruktor třídy
     * @param hra instance hry, která se používá
     */
    public PrikazSeber(Hra hra) {
        this.hra = hra;
    }

    /**
     * Sebere předmět z místnosti a přidá ho do inventáře.
     * Kontroluje, zda předmět co chce hráč sebrat je v prostoru a zda lze předmět sebrat.
     * Kontroluje, zda má hráč místo v inventáři.
     *
     * @param parametry název předmětu co má sebrat
     * @return zpráva o sebrání předmětu
     */
    @Override
    // kontroluje zda hráč zadal co má sebrat
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "musíš zadat, co mám sebrat...";
        }
        // kontroluje zda se předmět nachází v prostoru
        Inventar inventar = hra.getInventar();
        Prostor prostor = hra.getHerniPlan().getAktualniProstor();
        Predmet predmet = prostor.getPredmet(parametry[0]);
        if (predmet == null) {
            return "tento předmět tady není";
        }
        // kontroluje zda předmět lze sebrat
        if (!predmet.isLzeSebrat()) {
            return "tento předmět nelze sebrat";
        }
        // kontroluje zda je v inventáři místo (inventář je omezen na 16 předmětů)
        if (inventar.getPocetVeci() >= 16) {
            return "V inventáři už není místo. Musíš nejprve něco odložit.";
        }
        // přidává předmět do inventáře a odebírá ho z místnosti
        inventar.pridejPredmet(predmet);
        prostor.vyhozeniPredmetu(parametry[0]);
        return "Předmět se ti přidal do inventáře.\n\n" + prostor.dlouhyPopis();
    }

    /**
     * Metoda vrací název příkazu (seber...)
     *
     * @return název příkazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
