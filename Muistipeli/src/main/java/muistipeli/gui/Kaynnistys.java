
package muistipeli.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Kaynnistys extends JButton implements ActionListener {
    
    private String toiminto;
    private final Kayttoliittyma kayttoliittyma;
    
    public Kaynnistys(Kayttoliittyma kayttoliittyma, String toiminto) {
        this.toiminto = toiminto;
        this.setText(toiminto);
        this.kayttoliittyma = kayttoliittyma;
        this.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(this.toiminto.equals("Lopeta peli")) {
            this.kayttoliittyma.getPeli().lopeta();
            this.kayttoliittyma.siirryPistenakymaan();
            this.setToiminto("Uusi peli");
        }
        if(this.toiminto.equals("Aloita peli")) {
            this.kayttoliittyma.siirryPelinakymaan();
            this.setToiminto("Lopeta peli");
        }
        if(this.toiminto.equals("Uusi peli")) {
            this.kayttoliittyma.siirryValikkoon();
            this.setToiminto("Aloita peli");
        }
    }
    
    public String getToiminto() {
        return this.toiminto;
    }
    
    public void setToiminto(String toiminto) {
        this.toiminto = toiminto;
        this.setText(toiminto);
    }
    
}
