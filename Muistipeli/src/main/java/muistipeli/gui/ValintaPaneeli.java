
package muistipeli.gui;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ValintaPaneeli extends JPanel {
    
    private ArrayList<ValintaPainike> valintaPainikkeet;
    private Kayttoliittyma kayttoliittyma;
    private ImageIcon valintaKuva;
    
    public ValintaPaneeli(Kayttoliittyma k,String valinnanKohde, int alku, int loppu) {
        this.valintaPainikkeet = new ArrayList<>();
        this.kayttoliittyma = k;
        this.valintaKuva = new ImageIcon("kuvat/valinta.png");
        this.setLayout(new GridLayout(1, loppu - alku + 1));
        for (int i = alku; i <= loppu; i++) {
            ValintaPainike painike = new ValintaPainike(this.kayttoliittyma, this, i, valinnanKohde);
            this.add(painike);
            this.valintaPainikkeet.add(painike);
            if(i ==alku) {
                painike.setIcon(valintaKuva);
            }
        }
    }
    
    public void vahvistaValinta(int numero) {
        this.valintaPainikkeet.stream().forEach((v) -> {
            if(v.getNumero()==numero) {
                v.setIcon(valintaKuva);
            } else {
                v.setIcon(null);
            }
        });
        
    }
    
}
