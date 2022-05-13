package logika;

/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 * <p>
 * Tato třída inicializuje prvky ze kterých se hra skládá:
 * vytváří všechny prostory,
 * propojuje je vzájemně pomocí východů
 * a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 * @author Michael Kolling, Luboš Pavlicek, Jarmila Pavlíčková, Matěj Pavinger
 * @version pro školní rok 2021/2022
 */
public class HerniPlan {

    private Prostor aktualniProstor;
    private Prostor vyherniProstor;

    /**
     * Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     * Jako výchozí aktuální prostor nastaví pokojíček.
     */
    public HerniPlan() {
        zalozProstoryHry();

    }

    /**
     * Vytváří jednotlivé prostory a propojuje je pomocí východů.
     * Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor zahrada = new Prostor("zahrada", "zahrada kolem tvého domu");
        Prostor chodba = new Prostor("chodba", "chodba tvého domečku");
        Prostor sklep = new Prostor("sklep", "starý plesnivý sklep");
        Prostor pokojicek = new Prostor("pokojíček", "tvůj pokojíček");
        Prostor kuchyn = new Prostor("kuchyň", "kuchyň tvého domečku");
        Prostor kurnik = new Prostor("kurník", "kurník, ve kterém bydlí slepičky");
        Prostor rozcestnik = new Prostor("rozcestník", "rozcestník za tvým domem");
        Prostor kravin = new Prostor("kravín", "kravín, ve kterém bydlí kravičky");
        Prostor hrbitov = new Prostor("hřbitov", "strašidelný zvířecí hřbitov");
        Prostor cesta = new Prostor("cesta", "cesta k domčeku, kde bydlí pejsek s kočičkou, číhá zde zlý pes");
        Prostor domecek = new Prostor("domeček", "domeček kde bydlí pejsek s kočičkou");

        // přiřazují se průchody mezi prostory (sousedící prostory)
        zahrada.setVychod(chodba);
        zahrada.setVychod(cesta);
        zahrada.setVychod(rozcestnik);

        chodba.setVychod(zahrada);
        chodba.setVychod(sklep);
        chodba.setVychod(pokojicek);
        chodba.setVychod(kuchyn);

        sklep.setVychod(chodba);

        pokojicek.setVychod(chodba);

        kuchyn.setVychod(chodba);


        rozcestnik.setVychod(zahrada);
        rozcestnik.setVychod(kravin);
        rozcestnik.setVychod(hrbitov);
        rozcestnik.setVychod(kurnik);

        kravin.setVychod(rozcestnik);

        hrbitov.setVychod(rozcestnik);

        cesta.setVychod(zahrada);
        cesta.setVychod(domecek);

        domecek.setVychod(cesta);

        kurnik.setVychod(rozcestnik);

        aktualniProstor = pokojicek;  // hra začíná v pokojíčku
        vyherniProstor = domecek;   // hra končí v domečku

        //vytvářejí se jednotlivé předměty

        // Pokojíček
        Predmet stolecek = new Predmet("stoleček", false, true);
        Predmet seznamIngredienci = new Predmet("seznam_ingrediencí", true, false);
        Predmet cokolada = new Predmet("čokoláda", true, false);

        // Chodba
        Predmet piskaciHracka = new Predmet("pískací_hračka", true, false);

        // Sklep
        Predmet police = new Predmet("police", false, true);
        Predmet cibule = new Predmet("cibule", true, false);
        Predmet cesnek = new Predmet("česnek", true, false);

        // Kuchyň
        Predmet skrinka = new Predmet("skříňka", false, true);
        Predmet bonbony = new Predmet("bonbóny", true, false);
        Predmet mouka = new Predmet("mouka", true, false);

        Predmet lednice = new Predmet("lednice", false, true);
        Predmet okurky = new Predmet("okurky", true, false);
        Predmet syrecky = new Predmet("syrečky", true, false);
        Predmet burty = new Predmet("buřty", true, false);

        Predmet spajz = new Predmet("špajz", false, true);
        Predmet misa = new Predmet("mísa", true, false);

        Predmet kuchynskaLinka = new Predmet("kuchyňská_linka", false, true);

        // Kůrník
        Predmet slepice = new Predmet("slepice", false, true);
        Predmet vajicko = new Predmet("vajíčko", true, false);

        // Kravín
        Predmet krava = new Predmet("kráva", false, true);
        Predmet mleko = new Predmet("mléko", true, false);

        // Zvířecí hřbitov
        Predmet hrob = new Predmet("hrob", false, true);
        Predmet kost = new Predmet("kost", true, false);


        //vkládá do prozkoumatelných předmět jiné neprozkoumatelné předměty
        stolecek.pridejPredmet(seznamIngredienci);
        stolecek.pridejPredmet(cokolada);

        police.pridejPredmet(cibule);
        police.pridejPredmet(cesnek);

        skrinka.pridejPredmet(bonbony);
        skrinka.pridejPredmet(mouka);

        lednice.pridejPredmet(okurky);
        lednice.pridejPredmet(syrecky);
        lednice.pridejPredmet(burty);

        spajz.pridejPredmet(misa);

        slepice.pridejPredmet(vajicko);

        krava.pridejPredmet(mleko);

        hrob.pridejPredmet(kost);

        //vkládá předměty do místností
        pokojicek.pridejPredmet(stolecek);
        chodba.pridejPredmet(piskaciHracka);
        sklep.pridejPredmet(police);
        kuchyn.pridejPredmet(skrinka);
        kuchyn.pridejPredmet(lednice);
        kuchyn.pridejPredmet(spajz);
        kuchyn.pridejPredmet(kuchynskaLinka);
        kurnik.pridejPredmet(slepice);
        kravin.pridejPredmet(krava);
        hrbitov.pridejPredmet(hrob);
    }

    /**
     * Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     * @return aktuální prostor
     */

    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }

    /**
     * Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory.
     *
     * @param prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
        aktualniProstor = prostor;
    }

    /**
     * Metoda vrací výherní prostor.
     * @return výherní prostor
     */
    public Prostor getVyherniProstor() {
        return vyherniProstor;
    }
}
