
package muistipeli.domain;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PelialustaTest {
    
    public PelialustaTest() {
    }
    
    @Test
    public void lisaaKorttiTesti1() {
        Pelialusta alusta = new Pelialusta(4,4);
        alusta.lisaaKortti(new PeliKortti(1));
        assertEquals(alusta.getKortit().size(), 1);
    }
    
    @Test
    public void lisaaKorttiTesti2() {
        Pelialusta alusta = new Pelialusta(2,4);
        alusta.lisaaKortti(new PeliKortti(1));
        assertEquals(alusta.getKortit().size(), 1);
    }
    
    @Test
    public void lisaaKorttiTesti3() {
        Pelialusta alusta = new Pelialusta(4,4);
        for(int i = 0 ; i < 6 ; i++) {
            alusta.lisaaKortti(new PeliKortti(i));
        }
        assertEquals(alusta.getKortit().size(), 6);
    }
    
    @Test
    public void arvoKohtaTesti1() {
        Pelialusta alusta = new Pelialusta(4,4);
        for(int i = 0 ; i < 100 ; i++) {
            int[] kohta = alusta.arvoKohta();
            assertEquals(kohta[0] > 3,false);
        }
    }
    
    @Test
    public void arvoKohtaTesti2() {
        Pelialusta alusta = new Pelialusta(4,4);
        for(int i = 0 ; i < 100 ; i++) {
            int[] kohta = alusta.arvoKohta();
            assertEquals(kohta[1] > 3,false);
        }
    }
    
    @Test
    public void arvoKohtaTesti3() {
        Pelialusta alusta = new Pelialusta(4,4);
        for(int j = 0 ; j < 100 ; j++) {
            int[] kohta = alusta.arvoKohta();
            assertEquals(kohta[0] < 0,false);
        }
    }
    
    @Test
    public void arvoKohtaTesti4() {
        Pelialusta alusta = new Pelialusta(4,4);
        for(int j = 0 ; j < 100 ; j++) {
            int[] kohta = alusta.arvoKohta();
            assertEquals(kohta[1] < 0,false);
        }
    }
    
    @Test
    public void arvoKohtaTesti5() {
        Pelialusta alusta = new Pelialusta(2,4);
        for(int j = 0 ; j < 100 ; j++) {
            int[] kohta = alusta.arvoKohta();
            assertEquals(kohta[1] < 0,false);
            assertEquals(kohta[0] < 0, false);
            assertEquals(kohta[0] < 2, true);
            assertEquals(kohta[1] < 4, true);
        }
    }
    
    @Test
    public void taynnaTesti1() {
        Pelialusta alusta = new Pelialusta(4,4);
        assertEquals(alusta.taynna(),false);
    }
    
    @Test
    public void taynnaTesti2() {
        Pelialusta alusta = new Pelialusta(4,4);
        PeliKortti kortti = new PeliKortti(1);
        ArrayList<PeliKortti> kortit = new ArrayList<>();
        kortit.add(kortti);
        alusta.setKortit(kortit);
        assertEquals(alusta.taynna(),false);
    }
    
    @Test
    public void taynnaTesti3() {
        Pelialusta alusta = new Pelialusta(4,3);
        ArrayList<PeliKortti> kortit = new ArrayList<>();
        for(int i = 0 ; i < 19 ; i++) {
            kortit.add(new PeliKortti(i));
        }
        alusta.setKortit(kortit);
        assertEquals(alusta.taynna(),false);
    }
    
    @Test
    public void taynnaTesti4() {
        Pelialusta alusta = new Pelialusta(4,4);
        for(int i = 0 ; i < 16 ; i++) {
            alusta.lisaaKortti(new PeliKortti(i));
        }
        assertEquals(alusta.taynna(),true);
    }
    
    @Test
    public void tyhjaTesti1() {
        Pelialusta alusta = new Pelialusta(4,4);
        assertEquals(alusta.tyhja(),true);
    }
    
    @Test
    public void tyhjaTesti2() {
        Pelialusta alusta = new Pelialusta(4,4);
        PeliKortti kortti = new PeliKortti(1);
        ArrayList<PeliKortti> kortit = new ArrayList<>();
        kortit.add(kortti);
        alusta.setKortit(kortit);
        assertEquals(alusta.tyhja(),false);
    }
    
    @Test
    public void tyhjaTesti3() {
        Pelialusta alusta = new Pelialusta(4,4);
        PeliKortti kortti = new PeliKortti(1);
        PeliKortti kortti2 = new PeliKortti(2);
        PeliKortti kortti3 = new PeliKortti(3);
        ArrayList<PeliKortti> kortit = new ArrayList<>();
        kortit.add(kortti);
        kortit.add(kortti2);
        kortit.add(kortti3);
        alusta.setKortit(kortit);
        assertEquals(alusta.tyhja(),false);
    }
    
    @Test
    public void tyhjaTesti4() {
        Pelialusta alusta = new Pelialusta(4,4);
        PeliKortti kortti = new PeliKortti(1);
        alusta.lisaaKortti(kortti);
        assertEquals(alusta.tyhja(),false);
    }
    
    @Test
    public void paikkojaJaljellaTesti1() {
        Pelialusta alusta = new Pelialusta(4,4);
        assertEquals(alusta.paikkojaJaljella(),16);
    }
    
    @Test
    public void paikkojaJaljellaTesti2() {
        Pelialusta alusta = new Pelialusta(4,4);
        PeliKortti kortti = new PeliKortti(1);
        ArrayList<PeliKortti> kortit = new ArrayList<>();
        kortit.add(kortti);
        alusta.setKortit(kortit);
        assertEquals(alusta.paikkojaJaljella(),15);
    }
    
    @Test
    public void paikkojaJaljellaTesti3() {
        Pelialusta alusta = new Pelialusta(4,3);
        ArrayList<PeliKortti> kortit = new ArrayList<>();
        for(int i = 0 ; i < 12 ; i++) {
            kortit.add(new PeliKortti(i));
            alusta.setKortit(kortit);
        }
        assertEquals(alusta.paikkojaJaljella(),0);
    }
    
    @Test
    public void paikkojaJaljellaTesti4() {
        Pelialusta alusta = new Pelialusta(8,3);
        ArrayList<PeliKortti> kortit = new ArrayList<>();
        for(int i = 0 ; i < 24 ; i++) {
            kortit.add(new PeliKortti(i));
            alusta.setKortit(kortit);
        }
        assertEquals(alusta.paikkojaJaljella(),0);
    }
}
