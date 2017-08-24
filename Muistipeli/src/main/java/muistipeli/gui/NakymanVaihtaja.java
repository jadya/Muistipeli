
package muistipeli.gui;

import java.awt.Container;
import java.awt.event.WindowEvent;
import javax.swing.SwingUtilities;

public class NakymanVaihtaja {
    
    private Kayttoliittyma kayttoliittyma;
    private Kayttoliittyma kayttoliittyma2;
    
    public NakymanVaihtaja(Kayttoliittyma kayttoliittyma) {
        this.kayttoliittyma = kayttoliittyma;
        this.kayttoliittyma2 = null;
    }
    
    public void siirryPelinakymaan() {
        kayttoliittyma.valmistelePeli();
        kayttoliittyma.getPeli().aloitaPeli();
        kayttoliittyma.getPelinakyma().setKorkeus(kayttoliittyma.getPeli().getPelialusta().getKorkeus());
        kayttoliittyma.getPelinakyma().setLeveys(kayttoliittyma.getPeli().getPelialusta().getLeveys());

        Container c = kayttoliittyma.getFrame().getContentPane();
        for (int i = 0; i < c.getComponentCount(); i++) {
            c.remove(i);
        }

        kayttoliittyma.rakennaPelinakyma(kayttoliittyma.getPeli().getPelialusta().getLeveys(), kayttoliittyma.getPeli().getPelialusta().getKorkeus(), kayttoliittyma.getFrame().getContentPane());
        SwingUtilities.updateComponentTreeUI(kayttoliittyma.getFrame());
    }
    
    public void siirryPistenakymaan() {
        Container c = kayttoliittyma.getFrame().getContentPane();
        for (int i = 0; i < c.getComponentCount(); i++) {
            c.remove(i);
        }

        kayttoliittyma.rakennaPistenakyma(kayttoliittyma.getFrame().getContentPane());
        SwingUtilities.updateComponentTreeUI(kayttoliittyma.getFrame());
    }
    
    public void siirryValikkoon() {

        Container c = kayttoliittyma.getFrame().getContentPane();
        for (int i = 0; i < c.getComponentCount(); i++) {
            c.remove(i);
        }
        
//        this.kayttoliittyma.getFrame().setVisible(false);
//        this.kayttoliittyma = new Kayttoliittyma();
//        SwingUtilities.invokeLater(kayttoliittyma);
        this.kayttoliittyma.nollaa();
        kayttoliittyma.rakennaValikko(kayttoliittyma.getFrame().getContentPane());
        SwingUtilities.updateComponentTreeUI(kayttoliittyma.getFrame());
    }
    
}
