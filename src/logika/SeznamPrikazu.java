package logika;

import logika.prikaz.IPrikaz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class SeznamPrikazu - eviduje seznam přípustných příkazů adventury.
 * Používá se pro rozpoznávání příkazů
 * a vrácení odkazu na třídu implementující konkrétní příkaz.
 * Každý nový příkaz (instance implementující rozhraní Prikaz) se
 * musí do seznamu přidat metodou vlozPrikaz.
 * <p>
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Matěj Pavinger
 * @version pro školní rok 2021/2022
 */
public class SeznamPrikazu {
    // mapa pro uložení přípustných příkazů
    private Map<String, IPrikaz> mapaSPrikazy;


    /**
     * Konstruktor
     */
    public SeznamPrikazu() {
        mapaSPrikazy = new HashMap<>();
    }


    /**
     * Vkládá nový příkaz.
     *Encapsulation
     * @param prikaz Instance třídy implementující rozhraní IPrikaz
     */
    public void vlozPrikaz(IPrikaz prikaz) {
        mapaSPrikazy.put(prikaz.getNazev(), prikaz);
    }

    /**
     * Vrací odkaz na instanci třídy implementující rozhraní IPrikaz,
     * která provádí příkaz uvedený jako parametr.
     *
     * @param retezec klíčové slovo příkazu, který chce hráč zavolat
     * @return instance třídy, která provede požadovaný příkaz
     */
    public IPrikaz vratPrikaz(String retezec) {
        if (mapaSPrikazy.containsKey(retezec)) {
            return mapaSPrikazy.get(retezec);
        } else {
            return null;
        }
    }

    /**
     * Kontroluje, zda zadaný řetězec je přípustný příkaz.
     *
     * @param retezec Řetězec, který se má otestovat, zda je přípustný příkaz
     * @return Vrací hodnotu true, pokud je zadaný
     * řetězec přípustný příkaz
     */
    public boolean jePlatnyPrikaz(String retezec) {
        return mapaSPrikazy.containsKey(retezec);
    }

    /**
     * Vrací seznam přípustných příkazů, jednotlivé příkazy jsou odděleny mezerou.
     *
     * @return Řetězec, který obsahuje seznam přípustných příkazů
     */
    public String vratNazvyPrikazu() {
        String seznam = "";
        for (String slovoPrikazu : mapaSPrikazy.keySet()) {
            seznam += slovoPrikazu + " ";
        }
        return seznam;
    }

    /**
     * Vací seznam možných správných příkazů (při překlepu)
     * @param zadanyRetezec slovo příkazu, který uživatel zadal
     * @return seznam použitelných příkazů
     */
    public List<String> ziskejPreklepy(String zadanyRetezec) {
        List<String> preklepy = new ArrayList<>();
        // projde vsecny prikazy a zavola na nich metodu datDoNapovedy, ktera vrati ano/ne a podle toho prida do list preklepu
        for(IPrikaz prikaz : this.mapaSPrikazy.values()) {
            if (!prikaz.jeNazevPodobnySRetezcem(zadanyRetezec)) {
                continue; // pokud jeNazevPodobnySRetezcem vrati FALSE -> Přeškočit cyklus/iteraci = pokračuj
            }
            preklepy.add(prikaz.getNazev());
        }
        return preklepy;
    }

}

