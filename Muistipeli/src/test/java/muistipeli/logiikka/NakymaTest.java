
package muistipeli.logiikka;

import java.util.ArrayList;
import muistipeli.domain.PeliKortti;
import muistipeli.domain.Pelialusta;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class NakymaTest {
    
    public NakymaTest() {
    }
    
    @Test
    public void kaannettyPariLoytyyAlustaaTarkistettaessaJaPoistuuAlustalta() {
        Pelialusta alusta = new Pelialusta(4,4);
        for(int i = 0 ; i < 16 ; i++) {
            PeliKortti kortti = new PeliKortti(4);
            alusta.lisaaKortti(kortti);
        }
        int[][] tilanne = new int[4][4];
        for(int i = 0 ; i < 4 ; i++) {
            for(int j = 0 ; j < 4 ; j++) {
                tilanne[i][j] = 0;
            }
        }
        tilanne[0][0] = 1;
        tilanne[1][1] = 1;
        alusta.setKaantotilanne(tilanne);
        assertTrue(alusta.getNakyma().tarkistaPari());
        assertEquals(alusta.getKaantotilanne()[0][0], -99);
        assertEquals(alusta.getKaantotilanne()[1][1], -99);
        assertEquals(alusta.getKorttienSijainnit()[0][0], -99);
        assertEquals(alusta.getKorttienSijainnit()[1][1], -99);
    }
    
    @Test
    public void kortitJotkaEivatMuodostaPariaEivatNaytaPariltaAlustaaTarkistettaessa() {
        Pelialusta alusta = new Pelialusta(4,4);
        for(int i = 1 ; i <= 16 ; i++) {
            PeliKortti kortti = new PeliKortti(i);
            alusta.lisaaKortti(kortti);
        }
        int[][] tilanne = new int[4][4];
        for(int i = 0 ; i < 4 ; i++) {
            for(int j = 0 ; j < 4 ; j++) {
                tilanne[i][j] = 0;
            }
        }
        tilanne[0][0] = 1;
        tilanne[1][1] = 1;
        alusta.setKaantotilanne(tilanne);
        assertFalse(alusta.getNakyma().tarkistaPari());
        assertEquals(alusta.getKaantotilanne()[0][0], 0);
        assertEquals(alusta.getKaantotilanne()[1][1], 0);
        assertTrue(alusta.getKorttienSijainnit()[0][0] != -99);
        assertTrue(alusta.getKorttienSijainnit()[1][1] != -99);
    }
    
    @Test
    public void alussaKuviaOnNakyvillaNolla() {
        Pelialusta alusta = new Pelialusta(3,4);
        ArrayList<PeliKortti> kortit = new ArrayList<>();
        for(int i = 0 ; i < 12 ; i++) {
            kortit.add(new PeliKortti(i));
        }
        alusta.setKortit(kortit);
        assertEquals(alusta.getNakyma().kuviaNakyvilla(),0);
    }
    
    @Test
    public void kuviaOnNakyvillaOikeaMaara() {
        Pelialusta alusta = new Pelialusta(3,4);
        ArrayList<PeliKortti> kortit = new ArrayList<>();
        for(int i = 0 ; i < 12 ; i++) {
            kortit.add(new PeliKortti(i));
        }
        alusta.setKortit(kortit);
        alusta.kaannaKortti(0, 0);
        alusta.kaannaKortti(0, 1);
        alusta.kaannaKortti(2, 3);
        assertEquals(alusta.getNakyma().kuviaNakyvilla(),3);
    }
    
    @Test
    public void kuviaOnNakyvillaOikeaMaaraUseammanKaannonJalkeen() {
        Pelialusta alusta = new Pelialusta(3,4);
        ArrayList<PeliKortti> kortit = new ArrayList<>();
        for(int i = 0 ; i < 12 ; i++) {
            kortit.add(new PeliKortti(i));
        }
        alusta.setKortit(kortit);
        alusta.kaannaKortti(0, 0);
        alusta.kaannaKortti(0, 1);
        alusta.kaannaKortti(0, 0);
        alusta.kaannaKortti(1, 0);
        assertEquals(alusta.getNakyma().kuviaNakyvilla(),2);
    }
    
}
