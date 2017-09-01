package muistipeli.logiikka;

import java.util.ArrayList;
import muistipeli.domain.Pelaaja;
import muistipeli.domain.PeliKortti;
import muistipeli.domain.Pelialusta;
import muistipeli.domain.Tekoaly;

/**
 * Luokka tarjoaa pelilogiikkaan liittyviä metodeita.
 */
public class Peli {

    private final Pelialusta alusta;
    private final ArrayList<Pelaaja> pelaajat;
    private final ArrayList<Tekoaly> tekoalyt;
    private final Vuoro vuoro;
    private boolean kaynnissa;
    
    /**
     * Konstruktori alkuarvojen asettamiseen.
     * @param leveys Pelin pelialustalle leveys
     * @param korkeus Pelin pelialustalle korkeus
     */
    public Peli(int leveys, int korkeus) {
        if ((leveys * korkeus) % 2 != 0) {
            leveys++;
        }
        if (leveys <= 1) {
            leveys = 2;
        }
        if (korkeus <= 1) {
            korkeus = 2;
        }
        this.alusta = new Pelialusta(this, leveys, korkeus);
        this.pelaajat = new ArrayList<>();
        this.tekoalyt = new ArrayList<>();
        this.vuoro = new Vuoro(null);
        this.kaynnissa = false;
    }

    /**
     * Metodi lisää annetun pelaajan peliin.
     * @param pelaaja peliin lisättävä pelaaja
     */
    public void lisaaPelaaja(Pelaaja pelaaja) {
        this.pelaajat.add(pelaaja);
        if (pelaaja.getOnTekoaly()) {
            this.tekoalyt.add((Tekoaly) pelaaja);
        }
    }
    
    /**
     * Metodi aloittaa pelin asettamalla vuoroon ensimmäisen pelaajan, jakamalla
     * kortit pelialustalle ja asettamalla kaynnissa-atribuutin arvoon true.
     */
    public void aloitaPeli() {
        this.vuoro.setPelaaja(this.pelaajat.get(0));
        int korttipareja = this.alusta.getKorkeus() * this.alusta.getLeveys() / 2;
        for (int i = 1; i <= korttipareja; i++) {
            this.alusta.lisaaKortti(new PeliKortti(i));
            this.alusta.lisaaKortti(new PeliKortti(i));
        }
        this.kaynnissa = true;
    }
    
    /**
     * Metodi lisää pisteen vuorossa olevalle pelaajalle, jos pelialustalla on 
     * korttipari näkyvillä, tai kutsuu vaihdaVuoro-metodia, jos ei ole.
     */
    public void kierros() {
        if (alusta.getNakyma().kuviaNakyvilla() == 2 && alusta.getNakyma().tarkistaPari()) {
            vuoro.getPelaaja().lisaaPiste();
        } else {
            vaihdaVuoro();
        }
    }

    /**
     * Metodi vaihtaa vuoroon seuraavan pelaajan.
     */
    public void vaihdaVuoro() {
        int numero = this.vuoro.getNumero();
        numero++;
        if (numero >= this.pelaajat.size()) {
            numero = 0;
        }
        this.vuoro.setPelaaja(this.pelaajat.get(numero));
        this.vuoro.setNumero(numero);
    }
    
    /**
     * Metodi palauttaa pelinäkymän kokonaislukutaulukkomuodossa.
     * @return kokonaislukutaulukko, joka kuvaa näkymää pelialustalla
     */
    public int[][] nakyma() {
        int leveys = this.alusta.getLeveys();
        int korkeus = this.alusta.getKorkeus();
        int[][] nakyma = new int[leveys][korkeus];
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                if (this.alusta.getKaantotilanne()[j][i] == 1) {
                    nakyma[j][i] = this.alusta.getKorttienSijainnit()[j][i];
                } else {
                    nakyma[j][i] = this.alusta.getKaantotilanne()[j][i];
                }
            }
        }
        return nakyma;
    }
    
    /**
     * Metodi palauttaa pelin senhetkisen tilannekuvauksen.
     * @return pelaajien pisteet ja vuorossa olevan pelaajan nimimerkki
     */
    public String tilanne() {
        String tilanne = "Pisteet \n";
        tilanne = this.pelaajat.stream().map((pelaaja) -> pelaaja.getNimimerkki() + " : " + pelaaja.getPisteet() + "\n").reduce(tilanne, String::concat);
        tilanne += "Vuoro: " + this.pelaajat.get(this.vuoro.getNumero()).getNimimerkki();
        return tilanne;
    }

    public boolean getKaynnissa() {
        return this.kaynnissa;
    }
    
    /**
     * Metodi lopettaa pelin asettamalla kaynnissa atribuutin arvoon false.
     */
    public void lopetaPeli() {
        this.kaynnissa = false;
    }

    /**
     * Metodi kertoo, keillä pelaajista on eniten pisteitä.
     * @return lista pelaajista, joilla on eniten pisteitä
     */
    public ArrayList<Pelaaja> johdossa() {
        ArrayList<Pelaaja> enitenPisteita = new ArrayList<>();
        int pisteet = 0;
        for (Pelaaja p : this.pelaajat) {
            if (p.getPisteet() > pisteet) {
                enitenPisteita = new ArrayList<>();
                enitenPisteita.add(p);
                pisteet = p.getPisteet();
            } else if (p.getPisteet() == pisteet) {
                enitenPisteita.add(p);
            }
        }
        return enitenPisteita;
    }

    public Vuoro getVuoro() {
        return this.vuoro;
    }

    public ArrayList<Pelaaja> getPelaajat() {
        return this.pelaajat;
    }

    public Pelialusta getPelialusta() {
        return this.alusta;
    }

    public ArrayList<Tekoaly> getTekoalyt() {
        return this.tekoalyt;
    }
    
}
