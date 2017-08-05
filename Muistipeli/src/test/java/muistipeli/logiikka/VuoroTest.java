
package muistipeli.logiikka;

import muistipeli.domain.Pelaaja;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class VuoroTest {
    
    public VuoroTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void vuoroTesti1() {
        Pelaaja pelaaja = new Pelaaja("pelaaja", 1);
        Vuoro vuoro = new Vuoro(pelaaja);
        assertEquals(vuoro.getPelaaja(), pelaaja);
        assertEquals(vuoro.getNumero(),0);
    }
    
    @Test
    public void vuoroTesti2() {
        Pelaaja pelaaja = new Pelaaja("pelaaja", 1);
        Pelaaja pelaaja2 = new Pelaaja("pelaaja2", 2);
        Vuoro vuoro = new Vuoro(pelaaja);
        vuoro.setNumero(1);
        vuoro.setPelaaja(pelaaja2);
        assertEquals(vuoro.getPelaaja(), pelaaja2);
        assertEquals(vuoro.getNumero(),1);
    }
}
