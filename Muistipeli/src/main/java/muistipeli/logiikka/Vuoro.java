
package muistipeli.logiikka;

import muistipeli.domain.Pelaaja;


public class Vuoro {
    
    private Pelaaja pelaaja;
    private int numero;
    
    public Vuoro(Pelaaja pelaaja) {
        this.pelaaja = pelaaja;
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
