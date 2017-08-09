
package muistipeli.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PelaajaTest {
    
    @Test
    public void konstruktoriToimii() {
        Pelaaja pelaaja = new Pelaaja("pelaaja1",1);
        assertEquals(pelaaja.getId(),1);
        assertEquals(pelaaja.getNimimerkki(),"pelaaja1");
        assertEquals(pelaaja.getPisteet(),0);
    }
    
    @Test
    public void nimimerkinJaIdnAsetuksetToimii() {
        Pelaaja pelaaja = new Pelaaja("pelaaja1",1);
        pelaaja.setId(2);
        pelaaja.setNimimerkki("p");
        assertEquals(pelaaja.getId(),2);
        assertEquals(pelaaja.getNimimerkki(),"p");
        assertEquals(pelaaja.getPisteet(),0);
    }
    
    @Test
    public void yhdenPisteenLisaysOnnistuu() {
        Pelaaja pelaaja = new Pelaaja("pelaaja1",1);
        pelaaja.lisaaPiste();
        assertEquals(pelaaja.getPisteet(),1);
    }
    
    @Test
    public void monenPisteenLisaysOnnistuu() {
        Pelaaja pelaaja = new Pelaaja("pelaaja1",1);
        for(int i = 0 ; i < 100 ; i++) {
            pelaaja.lisaaPiste();
        }
        assertEquals(pelaaja.getPisteet(),100);
    }
    
    @Test
    public void pisteidenMuuttaminenToimii() {
        Pelaaja pelaaja = new Pelaaja("pelaaja1",1);
        pelaaja.setPisteet(20);
        pelaaja.lisaaPiste();
        assertEquals(pelaaja.getPisteet(),21);
    }
    
    @Test
    public void pisteidenMuuttaminenToimiiPisteenLisayksenJalkeen() {
        Pelaaja pelaaja = new Pelaaja("pelaaja1",1);
        pelaaja.lisaaPiste();
        pelaaja.setPisteet(20);
        assertEquals(pelaaja.getPisteet(),20);
    }
}
