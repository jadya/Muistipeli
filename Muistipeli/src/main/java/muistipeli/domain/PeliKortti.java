
package muistipeli.domain;


/**
 * Luokka tarjoaa muistipelin kortteihin liittyviä metodeita.
 */
public class PeliKortti {
    
    private int kuva;
    private int x;
    private int y;
    
    /**
     * Konstruktori alustaa PeliKortti-olion.
     * @param kuvanNumero luotavan kortin kuvaa vastaava numero
     */
    public PeliKortti(int kuvanNumero) {
        this.kuva = kuvanNumero;
        this.x = -99;
        this.y = -99;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getKuvanNumero() {
        return this.kuva;
    }
    
    public void setKuva(int kuvanNumero) {
        this.kuva = kuvanNumero;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
}
