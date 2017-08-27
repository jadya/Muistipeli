package muistipeli.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class ValintaPainike extends JButton implements ActionListener {

    private int numero;
    private String kohde;
    private Kayttoliittyma kayttoliittyma;
    private ValintaPaneeli valintaPaneeli;

    public ValintaPainike(Kayttoliittyma kayttoliittyma, ValintaPaneeli vp, int numero, String kohde) {
        this.numero = numero;
        this.valintaPaneeli = vp;
        this.setText("" + numero);
        this.kohde = kohde;
        this.kayttoliittyma = kayttoliittyma;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (this.kohde.equals("korkeus")) {
            this.kayttoliittyma.getPelinakyma().setKorkeus(numero);
        }
        if (this.kohde.equals("leveys")) {
            this.kayttoliittyma.getPelinakyma().setLeveys(numero);
        }
        if (this.kohde.equals("pelaajien maara")) {
            this.kayttoliittyma.getPelinakyma().setPelaajienMaara(numero);
        }
        if (this.kohde.equals("tekoalyjen maara")) {
            this.kayttoliittyma.getPelinakyma().setTekoalyjenMaara(numero);
        }
        this.valintaPaneeli.vahvistaValinta(this.numero);
    }
    
    public int getNumero() {
        return this.numero;
    }

}
