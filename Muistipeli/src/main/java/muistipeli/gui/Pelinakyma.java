package muistipeli.gui;

public class Pelinakyma {

    private int korkeus;
    private int leveys;
    private int pelaajia;

    public Pelinakyma() {
        this.korkeus = 2;
        this.leveys = 2;
        this.pelaajia = 2;

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

    public int getKorkeus() {
        return this.korkeus;
    }

    public int getLeveys() {
        return this.leveys;
    }

    public int getPelaajienMaara() {
        return this.pelaajia;
    }

}
