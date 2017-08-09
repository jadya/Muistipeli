package muistipeli.logiikka;

import java.util.ArrayList;
import muistipeli.domain.Pelaaja;
import muistipeli.domain.PeliKortti;
import muistipeli.domain.Pelialusta;

public class Peli {

    private Pelialusta alusta;
    private ArrayList<Pelaaja> pelaajat;
    private Vuoro vuoro;
    private boolean kaynnissa;

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
        this.kaynnissa = false;
    }

    public void lisaaPelaaja(Pelaaja pelaaja) {
        this.pelaajat.add(pelaaja);
    }

    public void aloitaPeli() {
        this.vuoro.setPelaaja(this.pelaajat.get(0));
        int korttipareja = this.alusta.getKorkeus()*this.alusta.getLeveys()/2;
        for(int i = 1 ; i <= korttipareja ; i++) {
            this.alusta.lisaaKortti(new PeliKortti(i));
            this.alusta.lisaaKortti(new PeliKortti(i));
        }
        this.kaynnissa = true;
        
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

    public String tilanne() {
        String tilanne = "Pisteet \n";
        for (Pelaaja pelaaja : this.pelaajat) {
            tilanne += pelaaja.getNimimerkki() + " : " + pelaaja.getPisteet() + "\n";
        }
        tilanne += "Vuoro: " + this.pelaajat.get(this.vuoro.getNumero()).getNimimerkki();
        return tilanne;
    }
    
    public boolean kaynnissa() {
        return this.kaynnissa;
    }
    
    public void lopeta() {
        this.kaynnissa = false;
    }
    
    public ArrayList<Pelaaja> johdossa() {
        ArrayList<Pelaaja> enitenPisteita = new ArrayList<>();
        int pisteet = 0;
        for(Pelaaja p : this.pelaajat) {
            if(p.getPisteet() > pisteet) {
                enitenPisteita = new ArrayList<>();
                enitenPisteita.add(p);
                pisteet = p.getPisteet();
            } else if(p.getPisteet() == pisteet) {
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

}
