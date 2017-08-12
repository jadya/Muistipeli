package muistipeli.domain;

import java.util.ArrayList;
import java.util.Random;

public class Pelialusta {

    private int leveys;
    private int korkeus;
    private int[][] korttienSijainnit;
    private int[][] kaantotilanne;
    private ArrayList<PeliKortti> kortit;

    public Pelialusta(int leveys, int korkeus) {
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.korttienSijainnit = new int[leveys][korkeus];
        this.kaantotilanne = new int[leveys][korkeus];
        for (int i = 0; i < this.korkeus; i++) {
            for (int j = 0; j < this.leveys; j++) {
                this.korttienSijainnit[j][i] = -99;
                this.kaantotilanne[j][i] = -99;
            }
        }
        this.kortit = new ArrayList<>();
    }

    public boolean lisaaKortti(PeliKortti kortti) {
        if (!this.taynna()) {
            int[] kohta = this.arvoKohta();
            while (this.korttienSijainnit[kohta[0]][kohta[1]] != -99) {
                kohta = this.arvoKohta();
            }
            kortti.setX(kohta[0]);
            kortti.setY(kohta[1]);
            this.kortit.add(kortti);
            this.korttienSijainnit[kohta[0]][kohta[1]] = kortti.getKuvanNumero();
            this.kaantotilanne[kohta[0]][kohta[1]] = 0;
            return true;
        }
        return false;
    }

    public void poistaKortti(PeliKortti kortti) {
        this.korttienSijainnit[kortti.getX()][kortti.getY()] = -99;
        this.kaantotilanne[kortti.getX()][kortti.getY()] = -99;
        this.kortit.remove(kortti);
    }
    
    public PeliKortti kortti(int x, int y) {
        for (PeliKortti kortti : this.kortit) {
            if(kortti.getX() == x && kortti.getY() == y) {
                return kortti;
            }
        }
        return null;
    }

    public boolean kaannaKortti(int x, int y) {
        if (this.kaantotilanne[x][y] != -99) {
            this.kaantotilanne[x][y] = Math.abs(this.kaantotilanne[x][y] - 1);
            return true;
        }
        return false;
    }
    
    public boolean tarkistaPari() {
        int a = -99;
        int ai = -99;
        int aj = -99;
        int b = -99;
        int bi = -99;
        int bj = -99;
        for (int i = 0; i < this.korkeus; i++) {
            for (int j = 0; j < this.leveys; j++) {
                if(this.kaantotilanne[j][i] == 1) {
                    if(a == -99){
                        a = this.korttienSijainnit[j][i];
                        ai = i;
                        aj = j;
                    } else {
                        b = this.korttienSijainnit[j][i];
                        bi = i;
                        bj = j;
                    }
                } 
            }
        }
        if(a == b) {
            this.poistaKortti(kortti(aj, ai));
            this.poistaKortti(kortti(bj, bi));
            return true;
        } else {
            this.kaannaKortti(aj, ai);
            this.kaannaKortti(bj, bi);
            return false;
        }
    }

    public int[] arvoKohta() {
        int[] kohta = new int[2];
        Random random = new Random();
        kohta[0] = random.nextInt(leveys);
        kohta[1] = random.nextInt(korkeus);
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
    
    public int kuviaNakyvilla() {
        int maara = 0;
        for (int i = 0; i < this.korkeus; i++) {
            for (int j = 0; j < this.leveys; j++) {
                if(this.kaantotilanne[j][i]==1) {
                    maara++;
                }
            }
        }
        return maara;
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

    public int[][] getKaantotilanne() {
        return this.kaantotilanne;
    }

    public ArrayList<PeliKortti> getKortit() {
        return this.kortit;
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

    public void setKaantotilanne(int[][] tilanne) {
        this.kaantotilanne = tilanne;
    }
    
    public void setKortit(ArrayList<PeliKortti> korttiLista) {
        if (korttiLista.size() <= this.korkeus * this.leveys) {
            for(PeliKortti kortti : korttiLista) {
                this.lisaaKortti(kortti);
            }
        }
    }

}
