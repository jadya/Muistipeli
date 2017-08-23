package muistipeli.logiikka;

import muistipeli.domain.Pelaaja;
import muistipeli.domain.Pelialusta;
import muistipeli.domain.Tekoaly;


public class Nakyma {
    
    private Pelialusta alusta;
    
    public Nakyma(Pelialusta alusta) {
        this.alusta = alusta;
    }
    
    /**
     * Metodi kertoo, onko pelialustalla käännettynä kaksi samanlaisella kuvalla
     * varustettua korttia.
     *
     * @return totuusarvo väitteelle näkyvillä on kaksi samaa kuvaa
     */
    public boolean tarkistaPari() {
        int a = -99;
        int ai = -99;
        int aj = -99;
        int b = -99;
        int bi = -99;
        int bj = -99;
        for (int i = 0; i < alusta.getKorkeus(); i++) {
            for (int j = 0; j < alusta.getLeveys(); j++) {
                if (alusta.getKaantotilanne()[j][i] == 1) {
                    if (a == -99) {
                        a = alusta.getKorttienSijainnit()[j][i];
                        ai = i;
                        aj = j;
                    } else {
                        b = alusta.getKorttienSijainnit()[j][i];
                        bi = i;
                        bj = j;
                    }
                }
            }
        }
        if (a == b) {
            alusta.poistaKortti(alusta.getKortti(aj, ai));
            alusta.poistaKortti(alusta.getKortti(bj, bi));
            for(Tekoaly p : this.alusta.getPeli().getTekoalyt()) {
                p.poistaMuistista(aj,ai,a);
                p.poistaMuistista(bj,bi,b);
            }
            return true;
        } else {
            alusta.kaannaKortti(aj, ai);
            alusta.kaannaKortti(bj, bi);
            for(Tekoaly p : this.alusta.getPeli().getTekoalyt()) {
                p.lisaaMuistiin(alusta.getKortti(aj, ai));
                p.lisaaMuistiin(alusta.getKortti(bj, bi));
            }
            return false;
        }
    }
    
    /**
     * Metodi kertoo kuinka monta pelialustalla olevista korteista on
     * käännettynä niin, että niiden kuvat ovat näkyvillä.
     *
     * @return näkyvillä olevien kuvien määrä
     */
    public int kuviaNakyvilla() {
        int maara = 0;
        for (int i = 0; i < alusta.getKorkeus(); i++) {
            for (int j = 0; j < alusta.getLeveys(); j++) {
                if (alusta.getKaantotilanne()[j][i] == 1) {
                    maara++;
                }
            }
        }
        return maara;
    }
    
    

}
