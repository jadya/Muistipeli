
package muistipeli.domain;

import java.util.ArrayList;
import java.util.Random;

public class Pelialusta {
    
    private int leveys;
    private int korkeus;
    private int[][] korttienSijainnit;
    private ArrayList<PeliKortti> kortit;
    
    public Pelialusta(int korkeus, int leveys) {
        this.korkeus = korkeus;
        this.leveys = leveys;
        this.korttienSijainnit = new int[korkeus][leveys];
        for(int i = 0 ; i < this.korkeus ; i++) {
            for(int j = 0 ; j < this.leveys ; j++) {
                this.korttienSijainnit[i][j] = -99;
            }
        }
        this.kortit = new ArrayList<>();
    }
    
    public void lisaaKortti(PeliKortti kortti) {
        int[] kohta = this.arvoKohta();
        while(this.korttienSijainnit[kohta[0]][kohta[1]] != -99) {
            kohta = this.arvoKohta();
        }
        kortti.setX(kohta[0]);
        kortti.setY(kohta[1]);
        this.kortit.add(kortti);
    }
    
    public void poistaKortti(PeliKortti kortti) {
        this.korttienSijainnit[kortti.getX()][kortti.getY()] = -99;
        this.kortit.remove(kortti);
    }
    
    public int[] arvoKohta() {
        int[] kohta = new int[2];
        Random random = new Random();
        kohta[0] = random.nextInt(leveys) - 1;
        kohta[1] = random.nextInt(korkeus) - 1;
        return kohta;
    }
    
    public boolean taynna() {
        return this.kortit.size() == this.korkeus * this.leveys;
    }
    
    public boolean tyhja() {
        return this.kortit.isEmpty();
    }
    
    public int paikkojaJaljella() {
        return this.leveys * this.korkeus - this.kortit.size();
    }
    
    public int getKorkeus() {
        return this.korkeus;
    }
    
    public int getLeveys() {
        return this.leveys;
    }
    
    public int[][] getKorttienSijainnit() {
        return this.korttienSijainnit;
    }
    
    public void setKorkeus(int korkeus) {
        this.korkeus = korkeus;
    }
    
    public void setLeveys(int leveys) {
        this.leveys = leveys;
    }
    
    public void setKorttienSijainnit(int[][] sijainnit) {
        this.korttienSijainnit = sijainnit;
    }
    
}
