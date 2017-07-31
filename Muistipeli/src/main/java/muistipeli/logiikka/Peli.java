
package muistipeli.logiikka;

import java.util.ArrayList;
import muistipeli.domain.Pelaaja;
import muistipeli.domain.Pelialusta;

public class Peli {
    
    private Pelialusta alusta;
    private ArrayList<Pelaaja> pelaajat;
    private Vuoro vuoro;
    
    public Peli(int leveys, int korkeus) {
        if((leveys*korkeus)%2 != 0) {
            leveys++;
        }
        this.alusta = new Pelialusta(leveys,korkeus);
        this.pelaajat = new ArrayList<>();
        this.vuoro = null;
    }
    
    public void lisaaPelaaja(Pelaaja pelaaja) {
        this.pelaajat.add(pelaaja);
    }
    
    public ArrayList<Pelaaja> getPelaajat() {
        return this.pelaajat;
    }
    
    public Pelialusta getPelialusta() {
        return this.alusta;
    }
    
    public void aloitaPeli() {
        this.vuoro.setPelaaja(this.pelaajat.get(0));
        
    }
    
    public void vaihdaVuoro() {
        int numero = this.vuoro.getNumero();
        numero++;
        if(numero >= this.pelaajat.size()) {
            numero = 0;
        }
        this.vuoro.setPelaaja(this.pelaajat.get(numero));
        this.vuoro.setNumero(numero);
    }
    
    public Vuoro getVuoro() {
        return this.vuoro;
    }
    
}
