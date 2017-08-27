package muistipeli.gui;

import muistipeli.domain.Tekoaly;

/**
 * Luokka tarjoaa käyttöliittymän tekoälyn vuoroon liittyviä metodeita.
 */
public class TekoalynVuoro {

    private Tekoaly tekoaly;
    private final Kayttoliittyma kayttoliittyma;
    
    /**
     * Konstruktori TekoalynVuorolle.
     * @param kayttoliittyma käyttöliittymä, johon TekoalynVuoro liittyy
     * @param tekoaly tekoaly, jonka vuoron TekoalynVuoro esittää 
     * käyttöliittymässä
     */
    public TekoalynVuoro(Kayttoliittyma kayttoliittyma, Tekoaly tekoaly) {
        this.kayttoliittyma = kayttoliittyma;
        this.tekoaly = tekoaly;
    }
    
    /**
     * Metodi tekoälyn pelivuoron esittämistä varten.
     */
    public void pelaa() {
        etsiKorttipari();
    }
    
    /**
     * Metodi tekoälyn korttiparin etsimisen käyttöliittymässä esittämistä
     * varten.
     */
    public void etsiKorttipari() {
        int[] kohta;
        if (this.kayttoliittyma.getPeli().getPelialusta().getNakyma().kuviaNakyvilla() < 2) {
            kohta = this.tekoaly.teeSiirto();
            while (this.kayttoliittyma.getPeli().getPelialusta().getKaantotilanne()[kohta[0]][kohta[1]] != 0) {
                kohta = this.tekoaly.teeSiirto();
            }
            kaannaKortti(kohta[0], kohta[1]);
            kayttoliittyma.setTekoalyllaVuoroKesken(true);
        } else {
            kayttoliittyma.setTekoalyllaVuoroKesken(false);
        }
    }
    
    /**
     * Metodi kääntää kortin käyttöliittymässä klikkaamalla.
     * @param x käännettävän kortin x-koordinaatti
     * @param y käännettävän kortin y-koordinaatti
     */
    public void kaannaKortti(int x, int y) {
        this.kayttoliittyma.getKorttipaikka(x, y).doClick();
    }

    public Tekoaly getTekoaly() {
        return this.tekoaly;
    }

    public void setTekoaly(Tekoaly t) {
        this.tekoaly = t;
    }

}
