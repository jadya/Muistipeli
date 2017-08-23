
package muistipeli.gui;

import muistipeli.domain.Tekoaly;

public class TekoalynVuoro {
    
    private final Tekoaly tekoaly;
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
        while(this.kayttoliittyma.getPeli().getPelialusta().getNakyma().kuviaNakyvilla() < 2) {
            kohta = this.tekoaly.teeSiirto();
            while(this.kayttoliittyma.getPeli().getPelialusta().getKaantotilanne()[kohta[0]][kohta[1]] != 0) {
                kohta = this.tekoaly.teeSiirto();
            }
            kaannaKortti(kohta[0], kohta[1]);
            kayttoliittyma.setTekoalyllaVuoroKesken(true);
        }
        kayttoliittyma.setTekoalyllaVuoroKesken(false);
        //paataVuoro();
    }
    
    public void kaannaKortti(int x, int y) {
        this.kayttoliittyma.getKorttipaikka(x, y).doClick();
    }
    
    public void paataVuoro() {
        //if(!this.kayttoliittyma.getKorttipaikat().isEmpty()) {
            //this.kayttoliittyma.getKorttipaikat().get(0).doClick();
        //}
        this.kayttoliittyma.getKorttipaikka(0, 0).doClick();
    }
    
    public Tekoaly getTekoaly() {
        return this.tekoaly;
    }
    
}
