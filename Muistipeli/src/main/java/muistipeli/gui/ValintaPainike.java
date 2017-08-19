package muistipeli.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class ValintaPainike extends JButton implements ActionListener {

    private int numero;
    private String kohde;
    private Kayttoliittyma kayttoliittyma;

    public ValintaPainike(Kayttoliittyma kayttoliittyma, int numero, String kohde) {
        this.numero = numero;
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
    }

}
