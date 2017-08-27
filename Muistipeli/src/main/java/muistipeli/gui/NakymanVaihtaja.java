
package muistipeli.gui;

import java.awt.Container;
import javax.swing.SwingUtilities;

/**
 * Luokka tarjoaa käyttöliittymän näkymän vaihtamiseen liittyviä metodeita.
 */
public class NakymanVaihtaja {
    
    private final Kayttoliittyma kayttoliittyma;
    
    /**
     * Konstruktori nakymanVaihtajalle.
     * @param kayttoliittyma käyttöliittymä, johon nakymanVaihtaja liittyy
     */
    public NakymanVaihtaja(Kayttoliittyma kayttoliittyma) {
        this.kayttoliittyma = kayttoliittyma;
    }
    
    /**
     * Metodi pelinäkymään siirtymiseen. Päivittää käyttöliittymän näyttämään 
     * pelinäkymän.
     */
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
    
    /**
     * Metodi pistenäkymään siirtymiseen. Päivittää käyttöliittymän näyttämään 
     * pistenäkymän.
     */
    public void siirryPistenakymaan() {
        Container c = kayttoliittyma.getFrame().getContentPane();
        for (int i = 0; i < c.getComponentCount(); i++) {
            c.remove(i);
        }
        kayttoliittyma.rakennaPistenakyma(kayttoliittyma.getFrame().getContentPane());
        SwingUtilities.updateComponentTreeUI(kayttoliittyma.getFrame());
    }
    
    /**
     * Metodi valikkoon siirtymiseen. Päivittää käyttöliittymän näyttämään 
     * valikon.
     */
    public void siirryValikkoon() {
        Container c = kayttoliittyma.getFrame().getContentPane();
        for (int i = 0; i < c.getComponentCount(); i++) {
            c.remove(i);
        }
        this.kayttoliittyma.nollaa();
        kayttoliittyma.rakennaValikko(kayttoliittyma.getFrame().getContentPane());
        SwingUtilities.updateComponentTreeUI(kayttoliittyma.getFrame());
    }
    
    /**
     * Metodi tekee uuden kierroksen alkuun tarvittavat käyttöliittymän 
     * päivitystoimenpiteet eli asettaa korttien selkäpuolet näkyville.
     */
    public void valmisteleKierros() {
        this.kayttoliittyma.getKorttipaikat().stream().filter((k) -> (!k.getTyhja())).forEach((k) -> {
            k.setSelka();
        });
        this.kayttoliittyma.getFrame().invalidate();
        this.kayttoliittyma.getFrame().validate();
        this.kayttoliittyma.getFrame().repaint();
    }
    
}
