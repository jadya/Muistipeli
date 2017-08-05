
package muistipeli.logiikka;

import muistipeli.domain.Pelaaja;
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
    }
    
}
