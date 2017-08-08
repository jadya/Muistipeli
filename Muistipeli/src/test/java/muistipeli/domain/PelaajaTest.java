
package muistipeli.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PelaajaTest {
    
    public PelaajaTest() {
        
    }
    
    @Test
    public void pelaajaTesti1() {
        Pelaaja pelaaja = new Pelaaja("pelaaja1",1);
        assertEquals(pelaaja.getId(),1);
        assertEquals(pelaaja.getNimimerkki(),"pelaaja1");
        assertEquals(pelaaja.getPisteet(),0);
    }
    
    @Test
    public void pelaajaTesti2() {
        Pelaaja pelaaja = new Pelaaja("pelaaja1",1);
        pelaaja.setId(2);
        pelaaja.setNimimerkki("p");
        assertEquals(pelaaja.getId(),2);
        assertEquals(pelaaja.getNimimerkki(),"p");
        assertEquals(pelaaja.getPisteet(),0);
    }
    
    @Test
    public void lisaaPisteTesti1() {
        Pelaaja pelaaja = new Pelaaja("pelaaja1",1);
        pelaaja.lisaaPiste();
        assertEquals(pelaaja.getPisteet(),1);
    }
    
    @Test
    public void lisaaPisteTesti2() {
        Pelaaja pelaaja = new Pelaaja("pelaaja1",1);
        for(int i = 0 ; i < 100 ; i++) {
            pelaaja.lisaaPiste();
        }
        assertEquals(pelaaja.getPisteet(),100);
    }
    
    @Test
    public void lisaaPisteTesti3() {
        Pelaaja pelaaja = new Pelaaja("pelaaja1",1);
        pelaaja.setPisteet(20);
        pelaaja.lisaaPiste();
        assertEquals(pelaaja.getPisteet(),21);
    }
}
