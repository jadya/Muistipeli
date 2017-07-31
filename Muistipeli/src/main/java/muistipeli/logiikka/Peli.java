
package muistipeli.logiikka;

import muistipeli.domain.Pelialusta;

public class Peli {
    
    private Pelialusta alusta;
    
    public Peli(int leveys, int korkeus) {
        int koko = leveys * korkeus;
        if(koko%2 != 0) {
            leveys++;
        }
        this.alusta = new Pelialusta(leveys,korkeus);
    }
    
}
