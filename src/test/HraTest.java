import logika.Hra;
import logika.Inventar;
import logika.Predmet;
import logika.Prostor;
import org.junit.*;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class HraTest {
    private Hra hra;

    @Before
    public void setUp() {
        hra = new Hra();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testHry() {
        // 1. test metody vratUvitani (úvodní zpráva pro hráče)
        assertEquals("\nVítejte!\n" +
                "Dnes je: " + LocalDate.now() +
                "\nPerfektní den, na zahrání si hry. \n" +
                "Pejsek slaví zítra svátek a kočička narozeniny,\n" +
                "proto jste se se skupinou kamarádů rozhodli uvařit jim dort ze všeho, co mají rádi.\n" +
                "Budete muset posbírat všechny potřebné ingredience, naštěstí vám nedávno pejsek poskytl seznam\n" +
                "všech věcí, které mají rádi.\n" +
                "Kde jen ten seznam může být...(nezapomeňte, že k upečení dortu bude potřeba i mísa, do které dáte\n" +
                "všechny ingredience\n" +
                "Napište 'nápověda', pokud si nevíte rady, jak hrát dál.\n\n" +
                hra.getHerniPlan().getAktualniProstor().dlouhyPopis() + "\n" +
                "Věci v inventáři: " + hra.getInventar().vypisSeznamPredmet(), hra.vratUvitani());

        // 2. test zda hra běží či ne (vrací false pokud hra ještě neskončila)
        assertFalse(hra.konecHry());
        hra.setKonecHry(true);
        assertTrue(hra.konecHry());

        // 3. test metody aktualniProstor
        Prostor testovaciProstor = new Prostor("půda", "půda");
        hra.getHerniPlan().setAktualniProstor(testovaciProstor);
        assertEquals(testovaciProstor, hra.getHerniPlan().getAktualniProstor());

        // 4. test metody vratZaverecnyText
        hra.setZaverecnyText("konec");
        assertEquals("konec", hra.vratZaverecnyText());

        hra.setZaverecnyText("game over");
        assertEquals("game over", hra.vratZaverecnyText());

        // 5. test metody zpracujPrikaz
        assertEquals("tento předmět, tady není...\n" +
                "Věci v inventáři: žádné", hra.zpracujPrikaz("prozkoumej stoleček"));

        // 6. test metody pridejPredmet
        Predmet predmetI = new Predmet("predmetI", true, false);
        hra.getInventar().pridejPredmet(predmetI);
        assertTrue(hra.getInventar().getSeznamPredmetu().containsValue(predmetI));
        assertEquals(1, hra.getInventar().getPocetVeci());

        Predmet predmetII = new Predmet("predmetII", false, true);
        hra.zpracujPrikaz("seber predmetII");
        assertFalse(hra.getInventar().getSeznamPredmetu().containsValue(predmetII));

        // 7. test metody vypisSeznamPredmetu
        assertEquals(" predmetI", hra.getInventar().vypisSeznamPredmet());

        // 8. test metody vyhozeniPredmetu
        assertTrue(hra.getInventar().vyhozeniPredmetu("predmetI"));
        assertFalse(hra.getInventar().vyhozeniPredmetu("predmetII"));

        // 9. test metody getNazev
        assertEquals("predmetI", predmetI.getNazev());

        // 10. test metody isLzeSebrat
        assertTrue(predmetI.isLzeSebrat());
        assertFalse(predmetII.isLzeSebrat());

        // 11. test metody isLzeProzkoumat
        assertFalse(predmetI.isLzeProzkoumat());
        assertTrue(predmetII.isLzeProzkoumat());

        // 12. test metody getSeznamPredmetu
        predmetI.pridejPredmet(predmetII);
        assertFalse(predmetI.getSeznamPredmetu().isEmpty());
        predmetI.vyhozeniPredmetu();
        assertTrue(predmetI.getSeznamPredmetu().isEmpty());

        // 13. test příkazu nápověda
        assertEquals("Tvým úkolem je upéct dort pro pejska a kočičku a donést jim ho.\n" +
                "Nejprve musíš posbírat všechyn ingredience\n" +
                "Můžeš zadat tyto příkazy:\n" +
                "prozkoumej nápověda upeč odlož jdi seber konec \n" +
                "Věci v inventáři: žádné", hra.zpracujPrikaz("nápověda"));

        // 14. test příkazu seber
        Predmet predmetIII = new Predmet("predmetIII", true, true);
        Prostor prostor = new Prostor("prostorI",  "test");
        hra.getHerniPlan().setAktualniProstor(prostor);
        hra.getHerniPlan().getAktualniProstor().pridejPredmet(predmetIII);
        hra.zpracujPrikaz("seber predmetIII");
        assertTrue(hra.getInventar().getSeznamPredmetu().containsValue(predmetIII));


        // 15. test příkazu prozkoumej
        hra.zpracujPrikaz("prozkoumej predmetIII");
        assertTrue(hra.getInventar().getSeznamPredmetu().containsValue(predmetIII));

        // 16. test příkazu pozkoumej u neprozkoumatelného předmětu
        Predmet predmetIV = new Predmet("predmetIV", true, false);
        Prostor prostorII = new Prostor("prostorII",  "test2");
        hra.getHerniPlan().setAktualniProstor(prostor);
        hra.getHerniPlan().getAktualniProstor().pridejPredmet(predmetIV);

        hra.zpracujPrikaz("prozkoumej predmetIV");
        assertFalse(hra.getInventar().getSeznamPredmetu().containsValue(predmetIV));

        // 17. test příkazu odloz
        hra.zpracujPrikaz("odlož predmetIII");
        assertFalse(hra.getInventar().getSeznamPredmetu().containsValue(predmetIII));

        // 18. test příkazu upec
        Inventar inventar = hra.getInventar();
        inventar.pridejPredmet(new Predmet("čokoláda", true, false));
        inventar.pridejPredmet(new Predmet("cibule", true, false));
        inventar.pridejPredmet(new Predmet("česnek", true, false));
        inventar.pridejPredmet(new Predmet("bonbóny", true, false));
        inventar.pridejPredmet(new Predmet("syrečky", true, false));
        inventar.pridejPredmet(new Predmet("mouka", true, false));
        inventar.pridejPredmet(new Predmet("okurky", true, false));
        inventar.pridejPredmet(new Predmet("buřty", true, false));
        inventar.pridejPredmet(new Predmet("mísa", true, false));
        inventar.pridejPredmet(new Predmet("vajíčko", true, false));
        inventar.pridejPredmet(new Predmet("mléko", true, false));
        inventar.pridejPredmet(new Predmet("kost", true, false));

        hra.getHerniPlan().setAktualniProstor(new Prostor("kuchyň", ""));

        hra.zpracujPrikaz("upeč dort");
        assertTrue(inventar.getSeznamPredmetu().containsKey("dort"));

        // 19. test příkazu konec
        assertEquals("hra ukončena příkazem konec\n" +
                "Věci v inventáři:  dort mísa", hra.zpracujPrikaz("konec"));
    }

    @Test
    public void prikazJdi() {
        // 20. test příkazu jdi
        assertEquals("Jsi v prostoru: chodba tvého domečku.\n" +
                "východy: zahrada pokojíček sklep kuchyň\n" +
                "Předměty v prostoru: pískací_hračka\n" +
                "Věci v inventáři: žádné", hra.zpracujPrikaz("jdi chodba"));
    }
}
