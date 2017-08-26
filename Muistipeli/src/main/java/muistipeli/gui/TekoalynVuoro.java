package muistipeli.gui;

import muistipeli.domain.Tekoaly;

public class TekoalynVuoro {

    private Tekoaly tekoaly;
    private final Kayttoliittyma kayttoliittyma;

    public TekoalynVuoro(Kayttoliittyma kayttoliittyma, Tekoaly tekoaly) {
        this.kayttoliittyma = kayttoliittyma;
        this.tekoaly = tekoaly;
    }

    public void pelaa() {
        etsiKorttipari();
    }

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
