package muistipeli.domain;

import java.util.ArrayList;
import muistipeli.logiikka.Peli;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PelialustaTest {

    @Test
    public void konstruktoriToimii() {
        Pelialusta alusta = new Pelialusta(null, 4, 2);
        assertEquals(alusta.getKortit().size(), 0);
        assertTrue(alusta.getLeveys() == 4);
        assertTrue(alusta.getKorkeus() == 2);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(alusta.getKaantotilanne()[i][j], -99);
                assertEquals(alusta.getKorttienSijainnit()[i][j], -99);
            }
        }
    }

    @Test
    public void kortinLisaaminenOnnistuuNelionMuotoiselleAlustalle() {
        Pelialusta alusta = new Pelialusta(null, 4, 4);
        alusta.lisaaKortti(new PeliKortti(1));
        assertEquals(alusta.getKortit().size(), 1);
    }

    @Test
    public void kortinLisaaminenOnnistuu() {
        Pelialusta alusta = new Pelialusta(null, 2, 4);
        alusta.lisaaKortti(new PeliKortti(1));
        assertEquals(alusta.getKortit().size(), 1);
    }

    @Test
    public void monenKortinLisaaminenOnnistuu() {
        Pelialusta alusta = new Pelialusta(null, 4, 4);
        for (int i = 0; i < 6; i++) {
            alusta.lisaaKortti(new PeliKortti(i));
        }
        assertEquals(alusta.getKortit().size(), 6);
    }

    @Test
    public void alustalleVoiLisataAlustanKoonVerranKortteja() {
        Pelialusta alusta = new Pelialusta(null, 2, 4);
        int ylimenevat = 0;
        for (int i = 0; i < 8; i++) {
            if (!alusta.lisaaKortti(new PeliKortti(i))) {
                ylimenevat++;
            }
        }
        assertEquals(alusta.getKortit().size(), 8);
        assertEquals(ylimenevat, 0);
    }

    @Test
    public void alustalleEiVoiLaittaaEnempaaKorttejaKuinAlustallaOnPaikkoja() {
        Pelialusta alusta = new Pelialusta(null, 2, 4);
        int ylimenevat = 0;
        for (int i = 0; i < 18; i++) {
            if (!alusta.lisaaKortti(new PeliKortti(i))) {
                ylimenevat++;
            }
        }
        assertEquals(alusta.getKortit().size(), 8);
        assertEquals(ylimenevat, 10);
    }

    @Test
    public void kunKorttejaYritetaanLisataLiikaaEnsimmaisetKortitLisataan() {
        Pelialusta alusta = new Pelialusta(null, 2, 4);
        PeliKortti kortti = null;
        for (int i = 0; i < 18; i++) {
            PeliKortti kortti2 = new PeliKortti(i);
            alusta.lisaaKortti(kortti2);
            if (i == 5) {
                kortti = kortti2;
            }
        }
        assertTrue(alusta.getKortit().contains(kortti));
    }

    @Test
    public void korttienLisaaminenNakyyTaulukoissa() {
        Pelialusta alusta = new Pelialusta(null, 2, 4);
        PeliKortti kortti = new PeliKortti(1);
        PeliKortti kortti2 = new PeliKortti(2);
        alusta.lisaaKortti(kortti);
        alusta.lisaaKortti(kortti2);
        assertTrue(alusta.getKorttienSijainnit()[kortti.getX()][kortti.getY()] == 1);
        assertTrue(alusta.getKorttienSijainnit()[kortti2.getX()][kortti2.getY()] == 2);
        assertTrue(alusta.getKaantotilanne()[kortti.getX()][kortti.getY()] == 0);
        assertTrue(alusta.getKaantotilanne()[kortti2.getX()][kortti2.getY()] == 0);
    }

    @Test
    public void taulukoissaEiNayYlimaaraisiaKortteja() {
        Pelialusta alusta = new Pelialusta(null, 4, 2);
        PeliKortti kortti = new PeliKortti(1);
        PeliKortti kortti2 = new PeliKortti(2);
        alusta.lisaaKortti(kortti);
        alusta.lisaaKortti(kortti2);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                if (i == kortti.getX() && j == kortti.getY()) {
                    assertEquals(alusta.getKaantotilanne()[i][j], 0);
                    assertEquals(alusta.getKorttienSijainnit()[i][j], 1);
                } else if (i == kortti2.getX() && j == kortti2.getY()) {
                    assertEquals(alusta.getKaantotilanne()[i][j], 0);
                    assertEquals(alusta.getKorttienSijainnit()[i][j], 2);
                } else {
                    assertEquals(alusta.getKaantotilanne()[i][j], -99);
                    assertEquals(alusta.getKorttienSijainnit()[i][j], -99);
                }
            }
        }
    }

    @Test
    public void yhdenLisatynKortinPoistoOnnistuu() {
        Pelialusta alusta = new Pelialusta(new Peli(4,4), 4, 4);
        PeliKortti kortti = new PeliKortti(1);
        alusta.lisaaKortti(kortti);
        alusta.poistaKortti(kortti);
        assertEquals(alusta.getKortit().size(), 0);
    }

    @Test
    public void tietynKortinPoistoAlustaltaOnnistuuKunLisattyMontaKorttia() {
        Pelialusta alusta = new Pelialusta(new Peli(2,4), 2, 4);
        PeliKortti kortti3 = null;
        for (int i = 0; i < 8; i++) {
            if (i == 3) {
                kortti3 = new PeliKortti(i);
                alusta.lisaaKortti(kortti3);
            } else {
                PeliKortti kortti = new PeliKortti(i);
                alusta.lisaaKortti(kortti);
            }
        }

        alusta.poistaKortti(kortti3);
        assertEquals(alusta.getKortit().size(), 7);
        assertTrue(kortti3 != null);
    }

    @Test
    public void yhdenKortinPoistoOnnistuuKunKorttejaLisattyAlustalleKaksi() {
        Pelialusta alusta = new Pelialusta(new Peli(4,4), 4, 4);
        PeliKortti kortti = new PeliKortti(1);
        alusta.lisaaKortti(kortti);
        PeliKortti kortti2 = new PeliKortti(1);
        alusta.lisaaKortti(kortti2);
        alusta.poistaKortti(kortti);
        assertEquals(alusta.getKortit().size(), 1);
    }

    @Test
    public void kortinPoistoTaydeltaAlustaltaOnnistuu() {
        Pelialusta alusta = new Pelialusta(new Peli(4,4), 4, 4);
        PeliKortti kortti7 = null;
        for (int i = 0; i < 16; i++) {
            PeliKortti kortti = new PeliKortti(i);
            alusta.lisaaKortti(kortti);
            if (i == 7) {
                kortti7 = kortti;
            }
        }
        alusta.poistaKortti(kortti7);
        assertEquals(alusta.getKortit().size(), 15);
        assertEquals(alusta.getPoistetutKortit().size(), 1);
        assertTrue(alusta.getPoistetutKortit().contains(kortti7));
    }

    @Test
    public void kortiPoistoNakyyTaulukoissa() {
        Pelialusta alusta = new Pelialusta(new Peli(2,4), 2, 4);
        PeliKortti kortti = new PeliKortti(1);
        alusta.lisaaKortti(kortti);
        alusta.poistaKortti(kortti);
        assertTrue(alusta.getKorttienSijainnit()[kortti.getX()][kortti.getY()] == -99);
        assertTrue(alusta.getKaantotilanne()[kortti.getX()][kortti.getY()] == -99);
    }

    @Test
    public void korttiLoytyyAlustaltaTaulukonAlusta() {
        Pelialusta alusta = new Pelialusta(null, 4, 4);
        for (int i = 0; i < 16; i++) {
            PeliKortti kortti = new PeliKortti(i);
            alusta.lisaaKortti(kortti);
        }
        assertTrue(alusta.getKortti(0, 0) != null);
    }

    @Test
    public void korttiLoytyyAlustaltaTaulukonLopusta() {
        Pelialusta alusta = new Pelialusta(null, 4, 4);
        for (int i = 0; i < 16; i++) {
            PeliKortti kortti = new PeliKortti(i);
            alusta.lisaaKortti(kortti);
        }
        assertTrue(alusta.getKortti(3, 3) != null);
    }

    @Test
    public void poistettuKorttiEiLoydyAlustalta() {
        Pelialusta alusta = new Pelialusta(new Peli(4,4), 4, 4);
        for (int i = 0; i < 16; i++) {
            PeliKortti kortti = new PeliKortti(i);
            alusta.lisaaKortti(kortti);
        }
        alusta.poistaKortti(alusta.getKortti(0, 0));
        assertTrue(alusta.getKortti(0, 0) == null);
    }

    @Test
    public void kortinKaantaminenOnnistuuKunKorttiOnAlustalla() {
        Pelialusta alusta = new Pelialusta(new Peli(2,4), 2, 4);
        PeliKortti kortti = new PeliKortti(1);
        alusta.lisaaKortti(kortti);
        assertTrue(alusta.kaannaKortti(kortti.getX(), kortti.getY()));
    }

    @Test
    public void KorttiaJotaEiOleEiVoiKaantaa() {
        Pelialusta alusta = new Pelialusta(new Peli(2,4), 2, 4);
        PeliKortti kortti = new PeliKortti(1);
        alusta.lisaaKortti(kortti);
        alusta.poistaKortti(kortti);
        assertFalse(alusta.kaannaKortti(kortti.getX(), kortti.getY()));
    }

    @Test
    public void kortinKaantaminenOnnistuu() {
        Pelialusta alusta = new Pelialusta(new Peli(2,4), 2, 4);
        PeliKortti kortti = new PeliKortti(1);
        alusta.lisaaKortti(kortti);
        alusta.kaannaKortti(kortti.getX(), kortti.getY());
        assertTrue(alusta.getKaantotilanne()[kortti.getX()][kortti.getY()] == 1);
    }

    @Test
    public void kortinSelkaOnNakyvillaKahdenKaannonJalkeen() {
        Pelialusta alusta = new Pelialusta(new Peli(2,4), 2, 4);
        PeliKortti kortti = new PeliKortti(1);
        alusta.lisaaKortti(kortti);
        alusta.kaannaKortti(kortti.getX(), kortti.getY());
        alusta.kaannaKortti(kortti.getX(), kortti.getY());
        assertTrue(alusta.getKaantotilanne()[kortti.getX()][kortti.getY()] == 0);
    }

    @Test
    public void korttiPysyyKaannettaessaPaikoillaan() {
        Pelialusta alusta = new Pelialusta(new Peli(2,4), 2, 4);
        PeliKortti kortti = new PeliKortti(4);
        alusta.lisaaKortti(kortti);
        alusta.kaannaKortti(kortti.getX(), kortti.getY());
        assertTrue(alusta.getKorttienSijainnit()[kortti.getX()][kortti.getY()] == 4);
        assertTrue(alusta.getKaantotilanne()[kortti.getX()][kortti.getY()] == 1);
    }

    @Test
    public void arvotunKohdanXKoordinaattiOnTarpeeksiPieni() {
        Pelialusta alusta = new Pelialusta(null, 4, 4);
        for (int i = 0; i < 100; i++) {
            int[] kohta = alusta.arvoKohta();
            assertEquals(kohta[0] > 3, false);
        }
    }

    @Test
    public void arvotunKohdanYKoordinaattiOnTarpeeksiPieni() {
        Pelialusta alusta = new Pelialusta(null, 4, 4);
        for (int i = 0; i < 100; i++) {
            int[] kohta = alusta.arvoKohta();
            assertEquals(kohta[1] > 3, false);
        }
    }

    @Test
    public void arvotunKohdanXKoordinaattiOnTarpeeksiSuuri() {
        Pelialusta alusta = new Pelialusta(null, 4, 4);
        for (int j = 0; j < 100; j++) {
            int[] kohta = alusta.arvoKohta();
            assertEquals(kohta[0] < 0, false);
        }
    }

    @Test
    public void arvotunKohdanYKoordinaattiOnTarpeeksiSuuri() {
        Pelialusta alusta = new Pelialusta(null, 4, 4);
        for (int j = 0; j < 100; j++) {
            int[] kohta = alusta.arvoKohta();
            assertEquals(kohta[1] < 0, false);
        }
    }

    @Test
    public void kohdanArvontaToimiiKunAlustanKorkeusOnSuurempiKuinLeveys() {
        Pelialusta alusta = new Pelialusta(null, 2, 4);
        for (int j = 0; j < 100; j++) {
            int[] kohta = alusta.arvoKohta();
            assertFalse(kohta[1] < 0);
            assertFalse(kohta[0] < 0);
            assertTrue(kohta[0] < 2);
            assertTrue(kohta[1] < 4);
        }
    }

    @Test
    public void kohdanArvontaToimiiKunAlustanLeveysOnSuurempiKuinKorkeus() {
        Pelialusta alusta = new Pelialusta(null, 4, 2);
        for (int j = 0; j < 100; j++) {
            int[] kohta = alusta.arvoKohta();
            assertFalse(kohta[0] < 0);
            assertFalse(kohta[1] < 0);
            assertTrue(kohta[1] < 2);
            assertTrue(kohta[0] < 4);
        }
    }

    @Test
    public void tyhjaAlustaEiOleTaysi() {
        Pelialusta alusta = new Pelialusta(null, 4, 4);
        assertFalse(alusta.taynna());
    }

    @Test
    public void alustaEiOleTaysiKunSillaOnYksiKortti() {
        Pelialusta alusta = new Pelialusta(null, 4, 4);
        PeliKortti kortti = new PeliKortti(1);
        ArrayList<PeliKortti> kortit = new ArrayList<>();
        kortit.add(kortti);
        alusta.setKortit(kortit);
        assertEquals(alusta.taynna(), false);
    }

    @Test
    public void alustaEiOleTaynnaJosSilleOnYritettyLisataLiikaaKortteja() {
        Pelialusta alusta = new Pelialusta(null, 4, 3);
        ArrayList<PeliKortti> kortit = new ArrayList<>();
        for (int i = 0; i < 19; i++) {
            kortit.add(new PeliKortti(i));
        }
        alusta.setKortit(kortit);
        assertEquals(alusta.taynna(), false);
    }

    @Test
    public void alustaOnTaynnaKunSenKaikillaPaikoillaOnKortti() {
        Pelialusta alusta = new Pelialusta(null, 4, 4);
        for (int i = 0; i < 16; i++) {
            alusta.lisaaKortti(new PeliKortti(i));
        }
        assertTrue(alusta.taynna());
    }

    @Test
    public void uusiAlustaOnTyhja() {
        Pelialusta alusta = new Pelialusta(null, 4, 4);
        assertTrue(alusta.tyhja());
    }

    @Test
    public void alustaJollaOnKorttiEiOleTyhja() {
        Pelialusta alusta = new Pelialusta(null, 4, 4);
        PeliKortti kortti = new PeliKortti(1);
        ArrayList<PeliKortti> kortit = new ArrayList<>();
        kortit.add(kortti);
        alusta.setKortit(kortit);
        assertEquals(alusta.tyhja(), false);
    }

    @Test
    public void alustaJollaOnMontaKorttiaEiOleTyhja() {
        Pelialusta alusta = new Pelialusta(null, 4, 4);
        PeliKortti kortti = new PeliKortti(1);
        PeliKortti kortti2 = new PeliKortti(2);
        PeliKortti kortti3 = new PeliKortti(3);
        ArrayList<PeliKortti> kortit = new ArrayList<>();
        kortit.add(kortti);
        kortit.add(kortti2);
        kortit.add(kortti3);
        alusta.setKortit(kortit);
        assertFalse(alusta.tyhja());
    }

    @Test
    public void alustaJolleOnLisattyKorttiEiOleTyhja() {
        Pelialusta alusta = new Pelialusta(null, 4, 4);
        PeliKortti kortti = new PeliKortti(1);
        alusta.lisaaKortti(kortti);
        assertFalse(alusta.tyhja());
    }

    @Test
    public void korttienAsetusToimii() {
        Pelialusta alusta = new Pelialusta(null, 3, 4);
        ArrayList<PeliKortti> kortit = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            kortit.add(new PeliKortti(i));
        }
        alusta.setKortit(kortit);
        assertEquals(alusta.getKortit().size(), 12);
    }

    @Test
    public void korttienSijaintienAsetusToimii() {
        Pelialusta alusta = new Pelialusta(null, 2, 2);
        int[][] sijainnit = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                sijainnit[j][i] = j + i;
            }
        }
        alusta.setKorttienSijainnit(sijainnit);
        assertEquals(alusta.getKorttienSijainnit()[0][0], 0);
        assertEquals(alusta.getKorttienSijainnit()[0][1], 1);
        assertEquals(alusta.getKorttienSijainnit()[1][0], 1);
        assertEquals(alusta.getKorttienSijainnit()[1][1], 2);
    }

    @Test
    public void oikeaPeliPalautuuPyydettaessa() {
        Peli p = new Peli(4, 5);
        Pelialusta a = new Pelialusta(p, 4, 5);
        assertEquals(a.getPeli(), p);
    }

}
