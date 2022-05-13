package logika.prikaz;

import logika.HerniPlan;
import logika.Hra;
import logika.Prostor;

/**
 * Třída PrikazJdi implementuje pro hru příkaz jdi.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author Jarmila Pavlickova, Luboš Pavlíček, Matěj Pavinger
 * @version pro školní rok 2021/2022
 */
final public class PrikazJdi extends Prikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;
    private final Hra hra;

    /**
     * Konstruktor třídy
     *
     * @param plan herní plán, ve kterém se bude ve hře "chodit"
     * @param hra  instance hry
     */
    public PrikazJdi(HerniPlan plan, Hra hra) {
        this.plan = plan;
        this.hra = hra;
    }

    /**
     * Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     * existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     * (východ) není, vypíše se chybové hlášení.
     * Kontroluje, zda má hráč upečený dort, jinak ho to nepustí do vyherniProstor
     *
     * @param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                  do kterého se má jít.
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Kam mám jít? Musíš zadat jméno východu";
        }

        String smer = parametry[0];

        // zkoušíme přejít do sousedního prostoru
        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);

        if (sousedniProstor == null) {
            return "Tam se odsud jít nedá!";
        }
        if (smer.equals("domeček")) {
            if (plan.getAktualniProstor().getPredmet("pískací_hračka") == null) {
                return "Zlý pes tě dál nepustí, musíš nějak odlákat jehot pozornost";
            }
            if (!hra.getInventar().getSeznamPredmetu().containsKey("dort")) {
                return "Ještě nemáš upečený dort. To budou pejsek s kočičkou moc smutní. Vrať se až s dortem...";
            }
        }
        plan.setAktualniProstor(sousedniProstor);
        if (plan.getAktualniProstor().equals(plan.getVyherniProstor())) {
            hra.setZaverecnyText("Úspěšně jsi pesjkovi s kočičkou předal dort. Měli ohromnou radost, společeně\n" +
                    "jste oslavili narozeniny a svátek. Gratuluji k dokončení hry. :)");
            hra.setKonecHry(true);
        }
        return sousedniProstor.dlouhyPopis();
    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     * @return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}
