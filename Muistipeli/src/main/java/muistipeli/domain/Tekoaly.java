
package muistipeli.domain;

import java.util.ArrayList;
import java.util.Random;
import muistipeli.logiikka.Peli;

public class Tekoaly extends Pelaaja {
    
    private String nimimerkki;
    private int id;
    private int pisteet;
    private int[][] muisti;
    private boolean onTekoaly;
    private ArrayList<Integer> tiedostetutKuvat;
    private ArrayList<Integer> seuraavatSiirrot;
    private Peli peli;
    
    public Tekoaly(String nimimerkki, int id, Peli peli) {
        super(nimimerkki,id);
        this.muisti = new int[peli.getPelialusta().getLeveys()][peli.getPelialusta().getKorkeus()];
        this.seuraavatSiirrot = new ArrayList<>();
        this.tiedostetutKuvat = new ArrayList<>();
        this.peli = peli;
        super.setOnTekoaly(true);
    }
    
    public int[] teeSiirto() {
        int[] kaannettavanKortinKohta;
        if(!this.seuraavatSiirrot.isEmpty()) {
            kaannettavanKortinKohta = this.etsiMuistista(seuraavatSiirrot.get(0));
            this.seuraavatSiirrot.remove(0);
        } else {
            kaannettavanKortinKohta = satunnainenKohta();
            if(this.tiedostetutKuvat.contains(peli.getPelialusta().getKortti(kaannettavanKortinKohta[0], kaannettavanKortinKohta[1]).getKuvanNumero())){
                this.seuraavatSiirrot.add(peli.getPelialusta().getKortti(kaannettavanKortinKohta[0], kaannettavanKortinKohta[1]).getKuvanNumero());
            }
            
        }
        return kaannettavanKortinKohta;
    }
    
    public int[] satunnainenKohta() {
        Random random = new Random();
        int[] kohta = new int[]{random.nextInt(this.peli.getPelialusta().getLeveys()), random.nextInt(this.peli.getPelialusta().getKorkeus())};
        while(this.muisti[kohta[0]][kohta[1]] == -99) {
            kohta = new int[]{random.nextInt(this.peli.getPelialusta().getLeveys()), random.nextInt(this.peli.getPelialusta().getKorkeus())};
        }
        return kohta;
    }
    
    public int[] etsiMuistista(int kuva) {
        int[] kohta = new int[2];
        for (int i = 0; i < peli.getPelialusta().getKorkeus(); i++) {
            for (int j = 0; j < peli.getPelialusta().getLeveys(); j++) {
                if(this.muisti[j][i]==kuva) {
                    kohta[0] = j;
                    kohta[1] = i;
                    this.muisti[j][i]=-99;
                }
            }
        }
        return kohta;
    }
    
    public void lisaaMuistiin(PeliKortti kortti) {
        this.muisti[kortti.getX()][kortti.getY()] = kortti.getKuvanNumero();
        if(this.tiedostetutKuvat.contains(kortti.getKuvanNumero())) {
            this.seuraavatSiirrot.add(kortti.getKuvanNumero());
            this.tiedostetutKuvat.remove(tiedostetutKuvat.indexOf(kortti.getKuvanNumero()));
        } else {
            this.tiedostetutKuvat.add(kortti.getKuvanNumero());
        }
    }
    
    public void poistaMuistista(PeliKortti kortti) {
        this.muisti[kortti.getX()][kortti.getY()] = -99;
        if(this.tiedostetutKuvat.contains(kortti.getKuvanNumero())) {
            this.tiedostetutKuvat.remove(tiedostetutKuvat.indexOf(kortti.getKuvanNumero()));
        } 
        if (this.seuraavatSiirrot.contains(kortti.getKuvanNumero())) {
            this.seuraavatSiirrot.remove(seuraavatSiirrot.indexOf(kortti.getKuvanNumero()));
        }
    }
    
    public void mieti() {
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            
        }
    }
    
    public int[][] getMuisti() {
        return this.muisti;
    }
    
    public ArrayList<Integer> getSeuraavatSiirrot() {
        return this.seuraavatSiirrot;
    }
    
    public ArrayList<Integer> getTiedostetutKuvat() {
        return this.tiedostetutKuvat;
    }
    
    public Peli getPeli() {
        return this.peli;
    }
    
}
