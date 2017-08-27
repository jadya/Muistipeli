package muistipeli.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import javax.swing.JButton;

/**
 * Luokka tarjoaa käyttöliittymän käynnistyspainikkeeseen liittyviä metodeita.
 */
public class Kaynnistys extends JButton implements ActionListener {

    private String toiminto;
    private final Kayttoliittyma kayttoliittyma;
    
    /**
     * Konstruktori käynnistykselle.
     * @param kayttoliittyma käyttöliittymä, johon käynnistys-painike liittyy
     * @param toiminto toiminto, joka toteutetaan käynnistys-painiketta 
     * klikatessa
     */
    public Kaynnistys(Kayttoliittyma kayttoliittyma, String toiminto) {
        this.toiminto = toiminto;
        this.setText(toiminto);
        this.kayttoliittyma = kayttoliittyma;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.toiminto.equals("Lopeta peli")) {
            this.kayttoliittyma.getPeli().lopeta();
            Collections.sort(this.kayttoliittyma.getPeli().getPelaajat());
            this.kayttoliittyma.getNakymanVaihtaja().siirryPistenakymaan();
        }
        if (this.toiminto.equals("Aloita peli")) {
            this.kayttoliittyma.getNakymanVaihtaja().siirryPelinakymaan();
            this.setToiminto("Lopeta peli");
        }
        if (this.toiminto.equals("Uusi peli")) {
            this.kayttoliittyma.getNakymanVaihtaja().siirryValikkoon();
            this.setToiminto("Aloita peli");
        }
    }

    public String getToiminto() {
        return this.toiminto;
    }
    
    /**
     * Metodi asettaa käynnistys-painikkeelle toiminnon ja muuttaa painikkeen 
     * tekstin sitä vastaavaksi.
     * @param toiminto toiminto, joka käynnistys-painikkeeseen asetetaan
     */
    public void setToiminto(String toiminto) {
        this.toiminto = toiminto;
        this.setText(toiminto);
    }

}
