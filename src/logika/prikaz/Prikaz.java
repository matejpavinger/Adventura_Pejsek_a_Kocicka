package logika.prikaz;

/**
 * Třída implementuje příkaz pocetStejnychZnaku
 *
 * @version pro školní rok 2021/2022
 * @author Matěj Pavinger
 */
abstract public class Prikaz implements IPrikaz {
    @Override
    public boolean jeNazevPodobnySRetezcem(String porovnani) {
        int celkemVyskyt = 0;
        for (char ch : this.getNazev().toCharArray()) { //prochazi kazde pismeno z  nazvu prikazu
            if (porovnani.contains("" + ch)) {
                celkemVyskyt++;
            }
        }
        // zjisti jestli porovnani (zadany prikaz uzivatelem) je podobny jako nazev prikazu

        // zjisti tak, ze pokud rozdil delky mensi nez 2 a pocet stenych znaku je alesppon jako pulka zadanych
        return celkemVyskyt > porovnani.length() / 2
                && Math.abs(porovnani.length() - this.getNazev().length()) < 2;
    }
}
