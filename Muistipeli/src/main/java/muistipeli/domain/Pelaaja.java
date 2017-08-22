
package muistipeli.domain;

/**
 * Luokka tarjoaa muistipelin pelaajiin liittyviä metodeita.
 */
public class Pelaaja implements Comparable<Pelaaja> {
    
    private String nimimerkki;
    private int id;
    private int pisteet;
    
    public Pelaaja(String nimimerkki, int id) {
        this.nimimerkki = nimimerkki;
        this.id = id;
        this.pisteet = 0;
    }
    
    /**
     * Metodi lisää pelaajalle yhden pisteen.
     */
    public void lisaaPiste() {
        this.pisteet++;
    }
    
    public String getNimimerkki() {
        return this.nimimerkki;
    }
    
    public int getId() {
        return this.id;
    }
    
    public int getPisteet() {
        return this.pisteet;
    }
    
    public void setNimimerkki(String nimi) {
        this.nimimerkki = nimi;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setPisteet(int pistemaara) {
        this.pisteet = pistemaara;
    }
    
    @Override
    public String toString() {
        return this.nimimerkki + " (" + this.id + ") : " + this.pisteet + " pistettä";
    }

    @Override
    public int compareTo(Pelaaja o) {
        return o.pisteet * 1000 - o.id - this.pisteet *1000 + this.id;
    }
    
}
