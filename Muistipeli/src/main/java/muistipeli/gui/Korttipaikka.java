
package muistipeli.gui;

import javax.swing.JButton;
import muistipeli.domain.PeliKortti;

public class Korttipaikka extends JButton {
    
    private PeliKortti kortti;
    
    public Korttipaikka(PeliKortti pelikortti) {
        this.kortti = pelikortti;
    }
    
    public PeliKortti getKortti() {
        return this.kortti;
    }
    
    public void setKortti(PeliKortti pelikortti) {
        this.kortti = pelikortti;
    }
    
}
