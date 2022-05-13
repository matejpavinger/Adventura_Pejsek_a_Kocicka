package logika.prikaz;

/**
 * Třída implementující toto rozhraní bude ve hře zpracovávat jeden konkrétní příkaz.
 * Toto rozhraní je součástí jednoduché textové hry.
 *
 * @author Jarmila Pavlickova, Matěj Pavinger
 * @version pro školní rok 2021/2022
 */
public interface IPrikaz {

    /**
     * Metoda pro provedení příkazu ve hře.
     * Počet parametrů je závislý na konkrétním příkazu,
     * např. příkazy konec a napoveda nemají parametry
     * příkazy jdi, seber, polož mají jeden parametr
     * příkaz pouzij může mít dva parametry.
     *
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     * @return text konkrétního příkazu
     */
    public String provedPrikaz(String... parametry);

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     * @return nazev prikazu
     */
    public String getNazev();

    /**
     * @param porovnani s cim se to ma porovnavat
     * @return int vrati pocet stejnych znaku mezi nazvem a porovnanim
     */
    public boolean jeNazevPodobnySRetezcem(String porovnani);

}
