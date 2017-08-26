package muistipeli;

import javax.swing.SwingUtilities;
import muistipeli.gui.Kayttoliittyma;

public class Main {
    
    /**
     * Metodi käynnistää käyttöliittymän.
     * @param args args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Kayttoliittyma());
    }

}
