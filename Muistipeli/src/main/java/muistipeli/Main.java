
package muistipeli;

import java.util.Arrays;
import muistipeli.domain.Pelaaja;
import muistipeli.logiikka.Peli;

public class Main {

    public static void main(String[] args) {
        System.out.println("Muistipeli");
        Peli peli = new Peli(2,4);
        peli.lisaaPelaaja(new Pelaaja("a", 1));
        peli.lisaaPelaaja(new Pelaaja("b", 2));
        peli.aloitaPeli();
        System.out.println(peli.tilanne());
    }
    
}
