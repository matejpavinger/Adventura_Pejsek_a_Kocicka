package logika;

import logika.prikaz.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

/**
 * Třída Hra - třída představující logiku adventury.
 * <p>
 * Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, která inicializuje prostory hry
 * a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 * Vypisuje uvítací a ukončovací text hry.
 * Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 * @author Michael Kolling, Luboš Pavlíček, Jarmila Pavlíčková, Matěj Pavinger
 * @version pro školní rok 2021/2022
 */

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private boolean konecHry = false;
    private String zaverecnyText = "Hodně štěstí někdy jindy...";
    private Inventar inventar;
    private File soubor;

    /**
     * Konstruktor třídy Hra
     * Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     * Dále  vytváří inventář a složku.
     */
    public Hra() {
        herniPlan = new HerniPlan();
        platnePrikazy = new SeznamPrikazu();
        inventar = new Inventar();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan, this));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazProzkoumej(this));
        platnePrikazy.vlozPrikaz(new PrikazSeber(this));
        platnePrikazy.vlozPrikaz(new PrikazOdloz(this));
        platnePrikazy.vlozPrikaz(new PrikazUpec(this));


        try {  // vytváří soubor (txt ve složce "adventura")
            soubor=new File("Hra "+ LocalDate.now() + ".txt");
            if (!soubor.exists()) {
                soubor.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda
     * Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return "\nVítejte!\n" +
                "Dnes je: " + LocalDate.now() +
                "\nPerfektní den, na zahrání si hry. \n" +
                "Pejsek slaví zítra svátek a kočička narozeniny,\n" +
                "proto jste se se skupinou kamarádů rozhodli uvařit jim dort ze všeho, co mají rádi.\n" +
                "Budete muset posbírat všechny potřebné ingredience, naštěstí vám nedávno pejsek poskytl seznam\n" +
                "všech věcí, které mají rádi.\n" +
                "Kde jen ten seznam může být...(nezapomeňte, že k upečení dortu bude potřeba i mísa, do které dáte\n" +
                "všechny ingredience\n" +
                "Napište 'nápověda', pokud si nevíte rady, jak hrát dál.\n\n" +
                herniPlan.getAktualniProstor().dlouhyPopis() + "\n" +
                "Věci v inventáři: " + inventar.vypisSeznamPredmet();
    }

    /**
     * Metoda
     * Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratZaverecnyText() {
        return zaverecnyText;
    }

    /**
     * Metoda
     * Nastaví závěrečnou zprávu pro hráče.
     * @param zaverecnyText závěrečná zpráva pro hráče.
     */
    public void setZaverecnyText(String zaverecnyText) {
        this.zaverecnyText = zaverecnyText;
    }

    /**
     * Metoda
     * Vrací true, pokud hra skončila.
     */
    public boolean konecHry() {
        return konecHry;
    }

    /**
     * Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     * Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     * Pokud ano spustí samotné provádění příkazu.
     * Kontroluje překlepy.
     *
     * @param radek text, který zadal uživatel jako příkaz do hry.
     * @return vrací se řetězec, který se má vypsat na obrazovku
     */
    public String zpracujPrikaz(String radek) {
        String[] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String[] parametry = new String[slova.length - 1];
        for (int i = 0; i < parametry.length; i++) {
            parametry[i] = slova[i + 1];
        }
        String textKVypsani = " .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.provedPrikaz(parametry);
        } else {
            textKVypsani = "Nevím co tím myslíš? Tento příkaz neznám. ";

            List<String> preklepy = platnePrikazy.ziskejPreklepy(slovoPrikazu);
            if (!preklepy.isEmpty()) {
                textKVypsani += "\n Možná si myslel: " + String.join(",", preklepy);
            }
        }
        String doSouboru = radek + "\n" + textKVypsani + "\n\n"; // vytváří odřádkování v souboru
        try {
            Files.write(soubor.toPath(), doSouboru.getBytes(), StandardOpenOption.APPEND); // vypíše text do souboru
        } catch (IOException e) {
            e.printStackTrace();
        }

        return textKVypsani + "\n" +
                "Věci v inventáři: " + inventar.vypisSeznamPredmet();
    }


    /**
     * Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     * mohou ji použít i další implementace rozhraní Prikaz.
     *
     * @param konecHry hodnota false= konec hry, true = hra pokračuje
     */
    public void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }

    /**
     * Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     * kde se jejím prostřednictvím získává aktualní místnost hry.
     *
     * @return odkaz na herní plán
     */
    public HerniPlan getHerniPlan() {
        return herniPlan;
    }

    /**
     * Metoda vrací inventář
     *
     * @return inventář
     */
    public Inventar getInventar() {
        return inventar;
    }

}

