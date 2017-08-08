
package muistipeli.logiikka;

import muistipeli.domain.Pelaaja;
import muistipeli.domain.PeliKortti;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PeliTest {
    
    public PeliTest() {
    }
    
    @Test
    public void peliTesti1() {
        Peli peli = new Peli(2,6);
        assertEquals(peli.getPelialusta().getLeveys(), 2);
        assertEquals(peli.getPelialusta().getKorkeus(), 6);
        assertEquals(peli.getPelaajat().isEmpty(), true);
        assertEquals(peli.getVuoro().getNumero(), 0);
    }
    
    @Test
    public void peliTesti2() {
        Peli peli = new Peli(1,6);
        assertEquals(peli.getPelialusta().getLeveys(), 2);
        assertEquals(peli.getPelialusta().getKorkeus(), 6);
    }
    
    @Test
    public void peliTesti3() {
        Peli peli = new Peli(3,51);
        assertEquals(peli.getPelialusta().getLeveys(), 4);
        assertEquals(peli.getPelialusta().getKorkeus(), 51);
    }
    
    @Test
    public void peliTesti4() {
        Peli peli = new Peli(1,0);
        assertEquals(peli.getPelialusta().getLeveys(), 2);
        assertEquals(peli.getPelialusta().getKorkeus(), 2);
    }
    
    @Test
    public void peliTesti5() {
        Peli peli = new Peli(3,1);
        assertEquals(peli.getPelialusta().getLeveys(), 4);
        assertEquals(peli.getPelialusta().getKorkeus(), 2);
    }
    
    @Test
    public void lisaaPelaajaTesti1() {
        Peli peli = new Peli(4,4);
        peli.lisaaPelaaja(new Pelaaja("p",1));
        assertEquals(peli.getPelaajat().size(), 1);
    }
    
    @Test
    public void lisaaPelaajaTesti2() {
        Peli peli = new Peli(4,4);
        peli.lisaaPelaaja(new Pelaaja("p",1));
        peli.lisaaPelaaja(new Pelaaja("p2",2));
        assertEquals(peli.getPelaajat().size(), 2);
    }
    
    @Test
    public void aloitaPeliTesti1() {
        Peli peli = new Peli(4,4);
        Pelaaja pelaaja = new Pelaaja("p",1);
        peli.lisaaPelaaja(pelaaja);
        peli.lisaaPelaaja(new Pelaaja("p2",2));
        peli.aloitaPeli();
        assertEquals(peli.getVuoro().getPelaaja(), pelaaja);
        assertEquals(peli.getVuoro().getNumero(),0);
    }
    
    @Test
    public void aloitaPeliTesti2() {
        Peli peli = new Peli(4,4);
        peli.lisaaPelaaja(new Pelaaja("p",1));
        peli.aloitaPeli();
        assertEquals(peli.getPelialusta().getKortit().size(), 16);
    }
    
    @Test
    public void aloitaPeliTesti3() {
        Peli peli = new Peli(4,4);
        peli.lisaaPelaaja(new Pelaaja("p",1));
        peli.aloitaPeli();
        int summa = 0;
        for(PeliKortti kortti : peli.getPelialusta().getKortit()) {
            summa += kortti.getKuvanNumero();
        }
        assertEquals(summa, 72);
    }
    
    @Test
    public void vaihdaVuoroTesti1() {
        Peli peli = new Peli(4,4);
        peli.lisaaPelaaja(new Pelaaja("p",1));
        Pelaaja p2 = new Pelaaja("p2",2);
        peli.lisaaPelaaja(p2);
        peli.aloitaPeli();
        peli.vaihdaVuoro();
        assertEquals(peli.getVuoro().getNumero(), 1);
        assertEquals(peli.getVuoro().getPelaaja(),p2);
    }
    
    @Test
    public void vaihdaVuoroTesti2() {
        Peli peli = new Peli(4,4);
        Pelaaja p1 = new Pelaaja("p1",1);
        peli.lisaaPelaaja(p1);
        Pelaaja p2 = new Pelaaja("p2",2);
        peli.lisaaPelaaja(p2);
        peli.aloitaPeli();
        peli.vaihdaVuoro();
        peli.vaihdaVuoro();
        assertEquals(peli.getVuoro().getNumero(), 0);
        assertEquals(peli.getVuoro().getPelaaja(),p1);
    }
    
    @Test
    public void vaihdaVuoroTesti3() {
        Peli peli = new Peli(4,4);
        Pelaaja p1 = new Pelaaja("p1",1);
        peli.lisaaPelaaja(p1);
        Pelaaja p2 = new Pelaaja("p2",2);
        peli.lisaaPelaaja(p2);
        peli.aloitaPeli();
        peli.vaihdaVuoro();
        peli.vaihdaVuoro();
        peli.vaihdaVuoro();
        assertEquals(peli.getVuoro().getNumero(), 1);
        assertEquals(peli.getVuoro().getPelaaja(),p2);
    }
    
    @Test
    public void nakymaTesti1() {
        Peli peli = new Peli(4,4);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(peli.nakyma()[i][j], -99);
            }
        }
    }
    
    @Test
    public void nakymaTesti2() {
        Peli peli = new Peli(4,4);
        for(int i = 0 ; i < 16 ; i++) {
            int luku = Math.abs(16 -2*i);
            if(luku == 0) {
                luku += 16;
            }
            peli.getPelialusta().lisaaKortti(new PeliKortti(luku));
        }
        peli.getPelialusta().kaannaKortti(3, 3);
        peli.getPelialusta().kaannaKortti(2, 2);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if((i==3 && j==3) || (i==2 && j==2)) {
                    assertEquals(peli.nakyma()[j][i] == -99, false);
                    assertEquals(peli.nakyma()[j][i] == 0, false);
                } else {
                    assertEquals(peli.nakyma()[j][i], 0);
                }
            }
        }
    }
    
    @Test
    public void tilanneTesti1() {
        Peli peli = new Peli(4,4);
        Pelaaja p1 = new Pelaaja("p1",1);
        peli.lisaaPelaaja(p1);
        Pelaaja p2 = new Pelaaja("p2",2);
        peli.lisaaPelaaja(p2);
        peli.aloitaPeli();
        assertEquals(peli.tilanne(), "Pisteet \n" + "p1 : 0\n" + "p2 : 0\n" + "Vuoro: p1");
    }
    
    @Test
    public void kaynnissaTesti1() {
        Peli peli = new Peli(4,4);
        Pelaaja p1 = new Pelaaja("p1",1);
        peli.lisaaPelaaja(p1);
        peli.aloitaPeli();
        assertTrue(peli.kaynnissa());
    }
    
    @Test
    public void kaynnissaTesti2() {
        Peli peli = new Peli(4,4);
        Pelaaja p1 = new Pelaaja("p1",1);
        peli.lisaaPelaaja(p1);
        assertFalse(peli.kaynnissa());
    }
    
    @Test
    public void lopetaTesti1() {
        Peli peli = new Peli(4,4);
        Pelaaja p1 = new Pelaaja("p1",1);
        peli.lisaaPelaaja(p1);
        peli.aloitaPeli();
        peli.lopeta();
        assertFalse(peli.kaynnissa());
    }
    
}
