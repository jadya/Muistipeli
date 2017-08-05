
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
    public void PelialustaTesti1() {
        Pelialusta alusta = new Pelialusta(4,2);
        assertEquals(alusta.getKortit().size(), 0);
        assertEquals(alusta.getLeveys() == 4, true);
        assertEquals(alusta.getKorkeus() == 2, true);
    }
    
    @Test
    public void PelialustaTesti2() {
        Pelialusta alusta = new Pelialusta(4,2);
        alusta.setLeveys(500);
        alusta.setKorkeus(4);
        assertEquals(alusta.getKortit().size(), 0);
        assertEquals(alusta.getLeveys() == 500, true);
        assertEquals(alusta.getKorkeus() == 4, true);
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
    public void lisaaKorttiTesti4() {
        Pelialusta alusta = new Pelialusta(2,4);
        for(int i = 0 ; i < 8 ; i++) {
            alusta.lisaaKortti(new PeliKortti(i));
        }
        assertEquals(alusta.getKortit().size(), 8);
    }
    
    @Test
    public void lisaaKorttiTesti5() {
        Pelialusta alusta = new Pelialusta(2,4);
        for(int i = 0 ; i < 18 ; i++) {
            alusta.lisaaKortti(new PeliKortti(i));
        }
        assertEquals(alusta.getKortit().size(), 8);
    }
    
    @Test
    public void lisaaKorttiTesti6() {
        Pelialusta alusta = new Pelialusta(2,4);
        PeliKortti kortti = null;
        for(int i = 0 ; i < 18 ; i++) {
            PeliKortti kortti2 = new PeliKortti(i);
            alusta.lisaaKortti(kortti2);
            if(i==5) {
                kortti = kortti2;
            }
        }
        assertEquals(alusta.getKortit().contains(kortti), true);
    }
    
    @Test
    public void lisaaKorttiTesti7() {
        Pelialusta alusta = new Pelialusta(2,4);
        PeliKortti kortti = new PeliKortti(1);
        PeliKortti kortti2 = new PeliKortti(2);
        alusta.lisaaKortti(kortti);
        alusta.lisaaKortti(kortti2);
        assertEquals(alusta.getKorttienSijainnit()[kortti.getX()][kortti.getY()] == 1, true);
        assertEquals(alusta.getKorttienSijainnit()[kortti2.getX()][kortti2.getY()] == 2, true);
        assertEquals(alusta.getKaantotilanne()[kortti.getX()][kortti.getY()] == 0, true);
        assertEquals(alusta.getKaantotilanne()[kortti2.getX()][kortti2.getY()] == 0, true);
    }
    
    @Test
    public void poistaKorttiTesti1() {
        Pelialusta alusta = new Pelialusta(4,4);
        PeliKortti kortti = new PeliKortti(1);
        alusta.lisaaKortti(kortti);
        alusta.poistaKortti(kortti);
        assertEquals(alusta.getKortit().size(), 0);
    }
    
    @Test
    public void poistaKorttiTesti2() {
        Pelialusta alusta = new Pelialusta(2,4);
        PeliKortti kortti3 = null;
        for(int i = 0 ; i < 8 ; i++) {
            if(i==3) {
                kortti3 = new PeliKortti(i);
                alusta.lisaaKortti(kortti3);
            } else {
                PeliKortti kortti = new PeliKortti(i);
                alusta.lisaaKortti(kortti);
            }
        }
        
        alusta.poistaKortti(kortti3);
        assertEquals(alusta.getKortit().size(), 7);
        assertEquals(kortti3 == null, false);
    }
    
    @Test
    public void poistaKorttiTesti3() {
        Pelialusta alusta = new Pelialusta(4,4);
        PeliKortti kortti = new PeliKortti(1);
        alusta.lisaaKortti(kortti);
        PeliKortti kortti2 = new PeliKortti(1);
        alusta.lisaaKortti(kortti2);
        alusta.poistaKortti(kortti);
        assertEquals(alusta.getKortit().size(), 1);
    }
    
    @Test
    public void poistaKorttiTesti4() {
        Pelialusta alusta = new Pelialusta(4,4);
        PeliKortti kortti7 = null;
        for(int i = 0 ; i < 16 ; i++) {
            PeliKortti kortti = new PeliKortti(i);
            alusta.lisaaKortti(kortti);
            if(i==7){
                kortti7 = kortti;
            }
        }
        alusta.poistaKortti(kortti7);
        assertEquals(alusta.getKortit().size(), 15);
    }
    
    @Test
    public void poistaKorttiTesti5() {
        Pelialusta alusta = new Pelialusta(2,4);
        PeliKortti kortti = new PeliKortti(1);
        alusta.lisaaKortti(kortti);
        alusta.poistaKortti(kortti);
        assertEquals(alusta.getKorttienSijainnit()[kortti.getX()][kortti.getY()] == -99, true);
        assertEquals(alusta.getKaantotilanne()[kortti.getX()][kortti.getY()] == -99, true);
    }
    
    @Test
    public void kaannaKorttiTesti1() {
        Pelialusta alusta = new Pelialusta(2,4);
        PeliKortti kortti = new PeliKortti(1);
        alusta.lisaaKortti(kortti);
        alusta.kaannaKortti(kortti.getX(), kortti.getY());
        assertEquals(alusta.getKaantotilanne()[kortti.getX()][kortti.getY()] == 1, true);
    }
    
    @Test
    public void kaannaKorttiTesti2() {
        Pelialusta alusta = new Pelialusta(2,4);
        PeliKortti kortti = new PeliKortti(1);
        alusta.lisaaKortti(kortti);
        alusta.kaannaKortti(kortti.getX(), kortti.getY());
        alusta.kaannaKortti(kortti.getX(), kortti.getY());
        assertEquals(alusta.getKaantotilanne()[kortti.getX()][kortti.getY()] == 0, true);
    }
    
    @Test
    public void kaannaKorttiTesti3() {
        Pelialusta alusta = new Pelialusta(2,4);
        PeliKortti kortti = new PeliKortti(4);
        alusta.lisaaKortti(kortti);
        alusta.kaannaKortti(kortti.getX(), kortti.getY());
        assertEquals(alusta.getKorttienSijainnit()[kortti.getX()][kortti.getY()] == 4, true);
        assertEquals(alusta.getKaantotilanne()[kortti.getX()][kortti.getY()] == 1, true);
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
    public void arvoKohtaTesti6() {
        Pelialusta alusta = new Pelialusta(4,2);
        for(int j = 0 ; j < 100 ; j++) {
            int[] kohta = alusta.arvoKohta();
            assertEquals(kohta[0] < 0,false);
            assertEquals(kohta[1] < 0, false);
            assertEquals(kohta[1] < 2, true);
            assertEquals(kohta[0] < 4, true);
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
