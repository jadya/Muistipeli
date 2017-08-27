package muistipeli.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class PeliKorttiTest {

    @Test
    public void konstruktoriJaXnJaYnAsetuksetToimii() {
        PeliKortti kortti = new PeliKortti(123);
        kortti.setX(5);
        kortti.setY(2);
        assertEquals(kortti.getKuvanNumero(), 123);
        assertEquals(kortti.getX(), 5);
        assertEquals(kortti.getY(), 2);
    }

    @Test
    public void kuvanAsetusToimii() {
        PeliKortti kortti = new PeliKortti(123);
        kortti.setKuva(12);
        assertEquals(kortti.getKuvanNumero(), 12);
    }
}
