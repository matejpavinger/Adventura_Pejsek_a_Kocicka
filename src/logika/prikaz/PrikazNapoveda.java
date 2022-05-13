package logika.prikaz;

import logika.SeznamPrikazu;

/**
 *  Třída PrikazNapoveda implementuje pro hru příkaz napoveda.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Jarmila Pavlickova, Luboš Pavlíček, Matěj Pavinger
 *@version    pro školní rok 2021/2022
 *
 */
final public class PrikazNapoveda extends Prikaz {

    private static final String NAZEV = "nápověda";
    private SeznamPrikazu platnePrikazy;


    /**
     *  Konstruktor třídy
     *
     *  @param platnePrikazy seznam příkazů,
     *                       které je možné ve hře použít,
     *                       aby je nápověda mohla zobrazit uživateli.
     */
    public PrikazNapoveda(SeznamPrikazu platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }

    /**
     *  Vrací základní nápovědu po zadání příkazu "napoveda". Nyní se vypisuje
     *  vcelku primitivní zpráva a seznam dostupných příkazů.
     *
     *  @return napoveda ke hre
     */
    @Override
    public String provedPrikaz(String... parametry) {
        return "Tvým úkolem je upéct dort pro pejska a kočičku a donést jim ho.\n"
                + "Nejprve musíš posbírat všechyn ingredience"
                + "\n"
                + "Můžeš zadat tyto příkazy:\n"
                + platnePrikazy.vratNazvyPrikazu();
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     *  @return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

    @Override
    public boolean jeNazevPodobnySRetezcem(String porovnani) {
        if (porovnani.toLowerCase().equals("pomoc")) { //pokud uživatel napsal pomoc misto napoveda
            return true; //varatit true
        }
        return super.jeNazevPodobnySRetezcem(porovnani);
    }
}
