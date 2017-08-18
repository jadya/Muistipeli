
package muistipeli.gui;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import muistipeli.logiikka.Peli;

public class VuoronNayttaja extends JPanel {
    
    private final Peli peli;
    private JLabel vuoro;
    
    public VuoronNayttaja(Peli peli) {
        this.peli = peli;
        this.setLayout(new GridLayout(1,2));
        this.add(new JLabel("Muistipeli"));
        this.vuoro = new JLabel("Vuoro: " + this.peli.getVuoro().getPelaaja().getNimimerkki() + " (" + this.peli.getVuoro().getPelaaja().getId() + ")");
        this.add(vuoro);
    }
    
    public void paivitaVuoro() {
        this.remove(vuoro);
        this.vuoro = new JLabel("Vuoro: " + peli.getVuoro().getPelaaja().getNimimerkki() + " (" + peli.getVuoro().getPelaaja().getId() + ")");
        this.add(vuoro);
    }
    
}
