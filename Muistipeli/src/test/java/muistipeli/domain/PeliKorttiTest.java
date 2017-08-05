
package muistipeli.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PeliKorttiTest {
    
    public PeliKorttiTest() {
    }
    
    @Test
    public void korttiTesti1() {
        PeliKortti kortti = new PeliKortti(123);
        kortti.setX(5);
        kortti.setY(2);
        assertEquals(kortti.getKuvanNumero(),123);
        assertEquals(kortti.getX(),5);
        assertEquals(kortti.getY(),2);
    }
    
    @Test
    public void korttiTesti2() {
        PeliKortti kortti = new PeliKortti(123);
        kortti.setKuva(12);
        assertEquals(kortti.getKuvanNumero(),12);
    }
}
