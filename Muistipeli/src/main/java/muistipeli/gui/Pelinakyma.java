package muistipeli.gui;

/**
 * Luokka tarjoaa metodeita käyttöliittymän pelinäkymän asetuksiin liittyen.
 */
public class Pelinakyma {

    private int korkeus;
    private int leveys;
    private int pelaajia;
    private int tekoalyja;
    
    /**
     * Konstruktori pelinäkymälle. Asettaa pelinäkymään oletusarvot.
     */
    public Pelinakyma() {
        this.korkeus = 2;
        this.leveys = 2;
        this.pelaajia = 2;
        this.tekoalyja = 0;
    }

    public void setKorkeus(int korkeus) {
        this.korkeus = korkeus;
    }

    public void setLeveys(int leveys) {
        this.leveys = leveys;
    }

    public void setPelaajienMaara(int pelaajienMaara) {
        this.pelaajia = pelaajienMaara;
    }
    
    public void setTekoalyjenMaara(int maara) {
        this.tekoalyja = maara;
    }

    public int getKorkeus() {
        return this.korkeus;
    }

    public int getLeveys() {
        return this.leveys;
    }

    public int getPelaajienMaara() {
        return this.pelaajia;
    }
    
    public int getTekoalyjenMaara() {
        return this.tekoalyja;
    }
    
}
