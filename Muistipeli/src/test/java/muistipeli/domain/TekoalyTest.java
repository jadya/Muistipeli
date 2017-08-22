
package muistipeli.domain;

import muistipeli.logiikka.Peli;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TekoalyTest {
    
    public TekoalyTest() {
    }

    @Test
    public void konstruktoriToimii() {
        Peli peli = new Peli(4,4);
        Tekoaly t = new Tekoaly("t", 1, peli);
        peli.lisaaPelaaja(t);
        peli.aloitaPeli();
        for (int i = 0; i < peli.getPelialusta().getKorkeus(); i++) {
            for (int j = 0; j < peli.getPelialusta().getLeveys(); j++) {
                assertEquals(t.getMuisti()[j][i], 0);
            }
        }
        assertTrue(t.onTekoaly());
        assertEquals(t.getPeli(), peli);
        assertEquals(t.getPisteet(), 0);
        assertEquals(t.getId(), 1);
        assertEquals(t.getNimimerkki(), "t");
    }
    
    @Test
    public void kohdanArvontaToimii() {
        Peli peli = new Peli(20,20);
        Tekoaly t = new Tekoaly("t", 1, peli);
        peli.lisaaPelaaja(t);
        peli.aloitaPeli();
        for (int i = 0; i < 100; i++) {
            assertTrue(t.satunnainenKohta()[0]<20);
            assertTrue(t.satunnainenKohta()[1]<20);
            assertTrue(t.satunnainenKohta()[0]>=0);
            assertTrue(t.satunnainenKohta()[1]>=0);
        }
    }
    
    @Test
    public void muistiinVoiLisata() {
        Peli peli = new Peli(4,6);
        Tekoaly t = new Tekoaly("t", 1, peli);
        peli.lisaaPelaaja(t);
        peli.aloitaPeli();
        PeliKortti p = peli.getPelialusta().getKortti(0, 3);
        t.lisaaMuistiin(p);
        assertEquals(t.getTiedostetutKuvat().size(), 1);
        assertTrue(t.getTiedostetutKuvat().contains(p.getKuvanNumero()));
        assertEquals(t.getSeuraavatSiirrot().size(), 0);
    }
    
    @Test
    public void muistiinVoiLisataMonta() {
        Peli peli = new Peli(4,6);
        Tekoaly t = new Tekoaly("t", 1, peli);
        peli.lisaaPelaaja(t);
        peli.aloitaPeli();
        PeliKortti p1 = peli.getPelialusta().getKortti(0, 3);
        PeliKortti p2 = peli.getPelialusta().getKortti(0, 4);
        PeliKortti p3 = peli.getPelialusta().getKortti(1, 3);
        PeliKortti p4 = peli.getPelialusta().getKortti(1, 4);
        t.lisaaMuistiin(p1);
        t.lisaaMuistiin(p2);
        t.lisaaMuistiin(p3);
        t.lisaaMuistiin(p4);
        assertEquals(t.getTiedostetutKuvat().size()+t.getSeuraavatSiirrot().size(), 4);
    }
}
