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
        Peli peli = new Peli(4, 4);
        Tekoaly t = new Tekoaly("t", 1, peli);
        peli.lisaaPelaaja(t);
        peli.aloitaPeli();
        assertTrue(t.getOnTekoaly());
        assertEquals(t.getPeli(), peli);
        assertEquals(t.getPisteet(), 0);
        assertEquals(t.getId(), 1);
        assertEquals(t.getNimimerkki(), "t");
    }

    @Test
    public void kohdanArvontaToimii() {
        Peli peli = new Peli(20, 20);
        Tekoaly t = new Tekoaly("t", 1, peli);
        peli.lisaaPelaaja(t);
        peli.aloitaPeli();
        for (int i = 0; i < 1000; i++) {
            assertTrue(t.satunnainenKohta()[0] < 20);
            assertTrue(t.satunnainenKohta()[1] < 20);
            assertTrue(t.satunnainenKohta()[0] >= 0);
            assertTrue(t.satunnainenKohta()[1] >= 0);
        }
    }
    
    @Test
    public void siirtoPysyyAlustalla() {
        Peli peli = new Peli(20, 20);
        Tekoaly t = new Tekoaly("t", 1, peli);
        peli.lisaaPelaaja(t);
        peli.aloitaPeli();
        for (int i = 0; i < 1000; i++) {
            assertTrue(t.teeSiirto()[0] < 20);
            assertTrue(t.teeSiirto()[1] < 20);
            assertTrue(t.teeSiirto()[0] >= 0);
            assertTrue(t.teeSiirto()[1] >= 0);
        }
    }
    
    @Test
    public void siirollaOnKaksiKoordinaattia() {
        Peli peli = new Peli(20, 20);
        Tekoaly t = new Tekoaly("t", 1, peli);
        peli.lisaaPelaaja(t);
        peli.aloitaPeli();
        for (int i = 0; i < 1000; i++) {
            assertTrue(t.teeSiirto().length == 2);
        }
    }

    @Test
    public void muistiinVoiLisata() {
        Peli peli = new Peli(4, 6);
        Tekoaly t = new Tekoaly("t", 1, peli);
        peli.lisaaPelaaja(t);
        peli.aloitaPeli();
        PeliKortti p = peli.getPelialusta().getKortti(0, 3);
        t.lisaaMuistiin(p);
        assertEquals(t.getMuisti().size(), 1);
        assertTrue(t.getMuisti().contains(p));
    }
    
    @Test
    public void muistiinVoiLisataMontaKorttia() {
        Peli peli = new Peli(4, 6);
        Tekoaly t = new Tekoaly("t", 1, peli);
        peli.lisaaPelaaja(t);
        peli.aloitaPeli();
        PeliKortti p = peli.getPelialusta().getKortti(0, 3);
        PeliKortti pk = peli.getPelialusta().getKortti(1, 3);
        PeliKortti pe = peli.getPelialusta().getKortti(0, 0);
        t.lisaaMuistiin(p);
        t.lisaaMuistiin(pk);
        t.lisaaMuistiin(pe);
        assertEquals(t.getMuisti().size(), 3);
        assertTrue(t.getMuisti().contains(pe));
        assertTrue(t.getMuisti().contains(pk));
        assertTrue(t.getMuisti().contains(p));
    }
    
    @Test
    public void muististaVoiPoistaaKortin() {
        Peli peli = new Peli(4, 6);
        Tekoaly t = new Tekoaly("t", 1, peli);
        peli.lisaaPelaaja(t);
        peli.aloitaPeli();
        PeliKortti p = peli.getPelialusta().getKortti(0, 3);
        t.lisaaMuistiin(p);
        t.poistaMuistista(p);
        assertFalse(t.getMuisti().contains(p));
        assertEquals(t.getMuisti().size(), 0);
    }
    
    @Test
    public void muististaVoiPoistaaMontaKorttia() {
        Peli peli = new Peli(4, 6);
        Tekoaly t = new Tekoaly("t", 1, peli);
        peli.lisaaPelaaja(t);
        peli.aloitaPeli();
        PeliKortti p = peli.getPelialusta().getKortti(0, 3);
        PeliKortti pk = peli.getPelialusta().getKortti(1, 3);
        PeliKortti pe = peli.getPelialusta().getKortti(0, 0);
        PeliKortti pelikortti = peli.getPelialusta().getKortti(0, 1);
        PeliKortti kortti = peli.getPelialusta().getKortti(1, 2);
        PeliKortti pelikortti2 = peli.getPelialusta().getKortti(0, 2);
        t.lisaaMuistiin(p);
        t.lisaaMuistiin(pk);
        t.lisaaMuistiin(pe);
        t.lisaaMuistiin(pelikortti);
        t.lisaaMuistiin(kortti);
        t.lisaaMuistiin(pelikortti2);
        t.poistaMuistista(p);
        t.poistaMuistista(pe);
        t.poistaMuistista(kortti);
        assertEquals(t.getMuisti().size(), 3);
        assertTrue(t.getMuisti().contains(pelikortti));
        assertTrue(t.getMuisti().contains(pk));
        assertTrue(t.getMuisti().contains(pelikortti2));
        assertFalse(t.getMuisti().contains(p));
    }

}
