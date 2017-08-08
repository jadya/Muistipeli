
package muistipeli.gui;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Kayttoliittyma implements Runnable {
    
    private JFrame frame;
    
    public Kayttoliittyma() {
        
    }

    @Override
    public void run() {
        this.frame = new JFrame("Muistipeli");
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
    }
    
}
