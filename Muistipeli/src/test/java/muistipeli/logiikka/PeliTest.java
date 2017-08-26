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

    @Test
    public void konstruktoriToimiiSopivillaArvoilla() {
        Peli peli = new Peli(2, 6);
        assertEquals(peli.getPelialusta().getLeveys(), 2);
        assertEquals(peli.getPelialusta().getKorkeus(), 6);
        assertEquals(peli.getPelaajat().isEmpty(), true);
        assertEquals(peli.getVuoro().getNumero(), 0);
    }

    @Test
    public void konstruktoriToimiiKunSyotettyLeveysLiianPieni() {
        Peli peli = new Peli(1, 6);
        assertEquals(peli.getPelialusta().getLeveys(), 2);
        assertEquals(peli.getPelialusta().getKorkeus(), 6);
    }

    @Test
    public void konstruktoriToimiiKunSyotetyillaArvoillaEiTuleParillistaMaaraaPaikkoja() {
        Peli peli = new Peli(3, 51);
        assertEquals(peli.getPelialusta().getLeveys(), 4);
        assertEquals(peli.getPelialusta().getKorkeus(), 51);
    }

    @Test
    public void konstruktoriToimiiKunSyotetytArvotLiianPienet() {
        Peli peli = new Peli(1, 0);
        assertEquals(peli.getPelialusta().getLeveys(), 2);
        assertEquals(peli.getPelialusta().getKorkeus(), 2);
    }

    @Test
    public void konstruktoriToimiiKunSyotettyKorkeusLiianPieni() {
        Peli peli = new Peli(3, 1);
        assertEquals(peli.getPelialusta().getLeveys(), 4);
        assertEquals(peli.getPelialusta().getKorkeus(), 2);
    }

    @Test
    public void yhdenPelaajanLisaaminenOnnistuu() {
        Peli peli = new Peli(4, 4);
        peli.lisaaPelaaja(new Pelaaja("p", 1));
        assertEquals(peli.getPelaajat().size(), 1);
    }

    @Test
    public void useammanPelaajanLisaaminenOnnistuu() {
        Peli peli = new Peli(4, 4);
        peli.lisaaPelaaja(new Pelaaja("p", 1));
        peli.lisaaPelaaja(new Pelaaja("p2", 2));
        assertEquals(peli.getPelaajat().size(), 2);
    }

    @Test
    public void pelinAlkaessaVuoroOnOikein() {
        Peli peli = new Peli(4, 4);
        Pelaaja pelaaja = new Pelaaja("p", 1);
        peli.lisaaPelaaja(pelaaja);
        peli.lisaaPelaaja(new Pelaaja("p2", 2));
        peli.aloitaPeli();
        assertEquals(peli.getVuoro().getPelaaja(), pelaaja);
        assertEquals(peli.getVuoro().getNumero(), 0);
    }

    @Test
    public void pelinAlkaessaKortitOvatAlustallaJaAlustaTaysi() {
        Peli peli = new Peli(4, 4);
        peli.lisaaPelaaja(new Pelaaja("p", 1));
        peli.aloitaPeli();
        assertEquals(peli.getPelialusta().getKortit().size(), 16);
    }

    @Test
    public void pelinAlkaessaJokaiselleKortilleOnPariEikaYlimaaraisiaKorttejaOle() {
        Peli peli = new Peli(4, 4);
        peli.lisaaPelaaja(new Pelaaja("p", 1));
        peli.aloitaPeli();
        int summa = 0;
        int a1 = 0;
        int a2 = 0;
        int a3 = 0;
        int a4 = 0;
        int a5 = 0;
        int a6 = 0;
        int a7 = 0;
        int a8 = 0;
        for (PeliKortti kortti : peli.getPelialusta().getKortit()) {
            int k = kortti.getKuvanNumero();
            summa += k;
            if (k == 1) {
                a1++;
            }
            if (k == 2) {
                a2++;
            }
            if (k == 3) {
                a3++;
            }
            if (k == 4) {
                a4++;
            }
            if (k == 5) {
                a5++;
            }
            if (k == 6) {
                a6++;
            }
            if (k == 7) {
                a7++;
            }
            if (k == 8) {
                a8++;
            }
        }
        assertEquals(summa, 72);
        assertEquals(a1, 2);
        assertEquals(a2, 2);
        assertEquals(a3, 2);
        assertEquals(a4, 2);
        assertEquals(a5, 2);
        assertEquals(a6, 2);
        assertEquals(a7, 2);
        assertEquals(a8, 2);
    }

    @Test
    public void vuoroVaihtuuEnsimmaiseltaPelaajaltaSeuraavalle() {
        Peli peli = new Peli(4, 4);
        peli.lisaaPelaaja(new Pelaaja("p", 1));
        Pelaaja p2 = new Pelaaja("p2", 2);
        peli.lisaaPelaaja(p2);
        peli.aloitaPeli();
        peli.vaihdaVuoro();
        assertEquals(peli.getVuoro().getNumero(), 1);
        assertEquals(peli.getVuoro().getPelaaja(), p2);
    }

    @Test
    public void vuoroVaihtuuViimeiseltaPelaajaltaEnsimmaiselle() {
        Peli peli = new Peli(4, 4);
        Pelaaja p1 = new Pelaaja("p1", 1);
        peli.lisaaPelaaja(p1);
        Pelaaja p2 = new Pelaaja("p2", 2);
        peli.lisaaPelaaja(p2);
        peli.aloitaPeli();
        peli.vaihdaVuoro();
        peli.vaihdaVuoro();
        assertEquals(peli.getVuoro().getNumero(), 0);
        assertEquals(peli.getVuoro().getPelaaja(), p1);
    }

    @Test
    public void vuoronVaihtoOnnistuuPelaajalistanMentyaYmpari() {
        Peli peli = new Peli(4, 4);
        Pelaaja p1 = new Pelaaja("p1", 1);
        peli.lisaaPelaaja(p1);
        Pelaaja p2 = new Pelaaja("p2", 2);
        peli.lisaaPelaaja(p2);
        peli.aloitaPeli();
        peli.vaihdaVuoro();
        peli.vaihdaVuoro();
        peli.vaihdaVuoro();
        assertEquals(peli.getVuoro().getNumero(), 1);
        assertEquals(peli.getVuoro().getPelaaja(), p2);
    }

    @Test
    public void uudessaNakymassaEiOleKortteja() {
        Peli peli = new Peli(4, 4);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(peli.nakyma()[i][j], -99);
            }
        }
    }

    @Test
    public void kaannetytJaKaantamattomatKortitOvatOikeinNakymassa() {
        Peli peli = new Peli(4, 4);
        for (int i = 0; i < 16; i++) {
            int luku = Math.abs(16 - 2 * i);
            if (luku == 0) {
                luku += 16;
            }
            peli.getPelialusta().lisaaKortti(new PeliKortti(luku));
        }
        peli.getPelialusta().kaannaKortti(3, 3);
        peli.getPelialusta().kaannaKortti(2, 2);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if ((i == 3 && j == 3) || (i == 2 && j == 2)) {
                    assertTrue(peli.nakyma()[j][i] != -99);
                    assertTrue(peli.nakyma()[j][i] != 0);
                } else {
                    assertEquals(peli.nakyma()[j][i], 0);
                }
            }
        }
    }

    @Test
    public void tilanneTulostuuOikein() {
        Peli peli = new Peli(4, 4);
        Pelaaja p1 = new Pelaaja("p1", 1);
        peli.lisaaPelaaja(p1);
        Pelaaja p2 = new Pelaaja("p2", 2);
        peli.lisaaPelaaja(p2);
        peli.aloitaPeli();
        assertEquals(peli.tilanne(), "Pisteet \n" + "p1 : 0\n" + "p2 : 0\n" + "Vuoro: p1");
    }

    @Test
    public void peliKaynnistyyAloitettaessa() {
        Peli peli = new Peli(4, 4);
        Pelaaja p1 = new Pelaaja("p1", 1);
        peli.lisaaPelaaja(p1);
        peli.aloitaPeli();
        assertTrue(peli.getKaynnissa());
    }

    @Test
    public void peliEiOleKaynnissaJosSitaEiOleAloitettu() {
        Peli peli = new Peli(4, 4);
        Pelaaja p1 = new Pelaaja("p1", 1);
        peli.lisaaPelaaja(p1);
        assertFalse(peli.getKaynnissa());
    }

    @Test
    public void pelinLopettaminenOnnistuu() {
        Peli peli = new Peli(4, 4);
        Pelaaja p1 = new Pelaaja("p1", 1);
        peli.lisaaPelaaja(p1);
        peli.aloitaPeli();
        peli.lopeta();
        assertFalse(peli.getKaynnissa());
    }

    @Test
    public void oikeaPelaajaJohdossa() {
        Peli peli = new Peli(4, 4);
        Pelaaja p1 = new Pelaaja("p1", 1);
        Pelaaja p2 = new Pelaaja("p2", 2);
        Pelaaja p3 = new Pelaaja("p3", 3);
        Pelaaja p4 = new Pelaaja("p4", 4);
        peli.lisaaPelaaja(p1);
        peli.lisaaPelaaja(p2);
        peli.lisaaPelaaja(p3);
        peli.lisaaPelaaja(p4);
        peli.aloitaPeli();
        p1.lisaaPiste();
        p1.lisaaPiste();
        p2.lisaaPiste();
        p3.lisaaPiste();
        p3.lisaaPiste();
        p3.lisaaPiste();
        p4.lisaaPiste();
        p2.lisaaPiste();
        peli.lopeta();
        assertEquals(peli.johdossa().get(0), p3);
        assertEquals(peli.johdossa().size(), 1);
    }

    @Test
    public void oikeatPelaajatJohdossa() {
        Peli peli = new Peli(4, 4);
        Pelaaja p1 = new Pelaaja("p1", 1);
        Pelaaja p2 = new Pelaaja("p2", 2);
        Pelaaja p3 = new Pelaaja("p3", 3);
        Pelaaja p4 = new Pelaaja("p4", 4);
        peli.lisaaPelaaja(p1);
        peli.lisaaPelaaja(p2);
        peli.lisaaPelaaja(p3);
        peli.lisaaPelaaja(p4);
        peli.aloitaPeli();
        p1.lisaaPiste();
        p1.lisaaPiste();
        p1.lisaaPiste();
        p3.lisaaPiste();
        p3.lisaaPiste();
        p3.lisaaPiste();
        p4.lisaaPiste();
        p2.lisaaPiste();
        peli.lopeta();
        assertTrue(peli.johdossa().contains(p1));
        assertTrue(peli.johdossa().contains(p3));
        assertEquals(peli.johdossa().size(), 2);
    }

    @Test
    public void oikeatPelaajatJohdossaKeskenPelin() {
        Peli peli = new Peli(4, 4);
        Pelaaja p1 = new Pelaaja("p1", 1);
        Pelaaja p2 = new Pelaaja("p2", 2);
        Pelaaja p3 = new Pelaaja("p3", 3);
        Pelaaja p4 = new Pelaaja("p4", 4);
        peli.lisaaPelaaja(p1);
        peli.lisaaPelaaja(p2);
        peli.lisaaPelaaja(p3);
        peli.lisaaPelaaja(p4);
        peli.aloitaPeli();
        p1.lisaaPiste();
        p1.lisaaPiste();
        p3.lisaaPiste();
        p3.lisaaPiste();
        p4.lisaaPiste();
        p2.lisaaPiste();
        assertTrue(peli.johdossa().contains(p1));
        assertTrue(peli.johdossa().contains(p3));
        assertEquals(peli.johdossa().size(), 2);
    }

    @Test
    public void vuoroEiVaihduKierroksellaJollaPariLoytyy() {
        Peli p = new Peli(2, 2);
        Pelaaja eka = new Pelaaja("eka", 1);
        Pelaaja toka = new Pelaaja("toka", 2);
        p.lisaaPelaaja(eka);
        p.lisaaPelaaja(toka);
        p.aloitaPeli();
        p.getPelialusta().kaannaKortti(0, 0);
        if (p.getPelialusta().getKortti(1, 1).getKuvanNumero() == p.getPelialusta().getKortti(0, 0).getKuvanNumero()) {
            p.getPelialusta().kaannaKortti(1, 1);
        } else if (p.getPelialusta().getKortti(0, 1).getKuvanNumero() == p.getPelialusta().getKortti(0, 0).getKuvanNumero()) {
            p.getPelialusta().kaannaKortti(0, 1);
        } else {
            p.getPelialusta().kaannaKortti(1, 0);
        }
        p.kierros();
        assertEquals(p.getVuoro().getPelaaja(), eka);
        assertEquals(eka.getPisteet(), 1);
        assertEquals(toka.getPisteet(), 0);
    }

    @Test
    public void kierrosVaihtaaVuoronJosPariaEiLoydy() {
        Peli p = new Peli(2, 2);
        Pelaaja eka = new Pelaaja("eka", 1);
        Pelaaja toka = new Pelaaja("toka", 2);
        p.lisaaPelaaja(eka);
        p.lisaaPelaaja(toka);
        p.aloitaPeli();
        p.getPelialusta().kaannaKortti(0, 0);
        if (p.getPelialusta().getKortti(1, 1).getKuvanNumero() != p.getPelialusta().getKortti(0, 0).getKuvanNumero()) {
            p.getPelialusta().kaannaKortti(1, 1);
        } else if (p.getPelialusta().getKortti(0, 1).getKuvanNumero() != p.getPelialusta().getKortti(0, 0).getKuvanNumero()) {
            p.getPelialusta().kaannaKortti(0, 1);
        } else {
            p.getPelialusta().kaannaKortti(1, 0);
        }
        p.kierros();
        assertEquals(p.getVuoro().getPelaaja(), toka);
        assertEquals(eka.getPisteet(), 0);
        assertEquals(toka.getPisteet(), 0);
    }
}
