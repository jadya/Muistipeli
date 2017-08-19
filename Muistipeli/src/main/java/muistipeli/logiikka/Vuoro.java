
package muistipeli.logiikka;

import muistipeli.domain.Pelaaja;

/**
 * Luokka tarjoaa muistipelin vuoroon liittyviä metodeita.
 */
public class Vuoro {
    
    private Pelaaja pelaaja;
    private int numero;
    
    public Vuoro(Pelaaja pelaaja) {
        this.pelaaja = pelaaja;
        this.numero = 0;
    }
    
    public Pelaaja getPelaaja() {
        return this.pelaaja;
    }
    
    public int getNumero() {
        return this.numero;
    }
    
    public void setPelaaja(Pelaaja pelaaja) {
        this.pelaaja = pelaaja;
    }
    
    public void setNumero(int numero) {
        this.numero = numero;
    }
    
}
