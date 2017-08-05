package muistipeli.logiikka;

import java.util.ArrayList;
import muistipeli.domain.Pelaaja;
import muistipeli.domain.Pelialusta;

public class Peli {

    private Pelialusta alusta;
    private ArrayList<Pelaaja> pelaajat;
    private Vuoro vuoro;

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
        this.alusta = new Pelialusta(leveys, korkeus);
        this.pelaajat = new ArrayList<>();
        this.vuoro = new Vuoro(null);
    }

    public void lisaaPelaaja(Pelaaja pelaaja) {
        this.pelaajat.add(pelaaja);
    }

    public void aloitaPeli() {
        this.vuoro.setPelaaja(this.pelaajat.get(0));

    }

    public void vaihdaVuoro() {
        int numero = this.vuoro.getNumero();
        numero++;
        if (numero >= this.pelaajat.size()) {
            numero = 0;
        }
        this.vuoro.setPelaaja(this.pelaajat.get(numero));
        this.vuoro.setNumero(numero);
    }

    public String tilanne() {
        String tilanne = "Pisteet \n";
        for (Pelaaja pelaaja : this.pelaajat) {
            tilanne += pelaaja.getNimimerkki() + " : " + pelaaja.getPisteet() + "\n";
        }
        tilanne += "Vuoro: " + this.pelaajat.get(this.vuoro.getNumero()).getNimimerkki();
        return tilanne;
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

}
