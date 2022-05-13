package logika.prikaz;

import logika.Hra;
import logika.Inventar;
import logika.Predmet;
import logika.Prostor;

/**
 * Třída PrikazOdloz implementje pro hru příkaz odlož.
 * Třída je součástí jednoduché textové hry.
 * Odebere z inventáře jednotilvé předměty.
 *
 * @version pro školní rok 2021/2022
 * @author Matěj Pavinger
 */

public class PrikazOdloz extends Prikaz {
    private static final String NAZEV = "odlož";
    private final Hra hra;

    /**
     * Konstruktor třídy
     * @param hra instance hry, která se používá
     */
    public PrikazOdloz(Hra hra) {
        this.hra = hra;
    }

    /**
     * Odloží předmět z inventáře.
     * Kontroluje zda předmět co se má odložit je v inventáři.
     *
     * @param parametry název předmětu co má odložit
     * @return zpráva o odložení
     */
    @Override
    // kontroluje zda hráč zadal co má postava odložit
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "musíš zadat, co mám odložit...";
        }
        // kontroluje zda předmět co se má odložit je v inventáři
        Inventar inventar = hra.getInventar();
        Prostor prostor = hra.getHerniPlan().getAktualniProstor();
        Predmet predmet = inventar.getSeznamPredmetu().get(parametry[0]);
        if (predmet == null) {
            return "Nemůžu odložit předmět, který není v inventáři.";
        }
        // přidává předmět do místnosti a uvolňuje místo v inventáři
        prostor.pridejPredmet(predmet);
        inventar.vyhozeniPredmetu(parametry[0]);
        return "Odložil jsi předmět " + parametry[0] + "\n\n" + prostor.dlouhyPopis();
    }

    /**
     * Metoda vrací název příkazu (odloz...)
     *
     * @return název příkazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
