
package muistipeli.gui;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Kayttoliittyma implements Runnable {
    
    private JFrame frame;
    private ArrayList<Korttipaikka> korttipaikat = new ArrayList<>();
    
    @Override
    public void run() {
        this.frame = new JFrame("Muistipeli");
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
    }
    
}
