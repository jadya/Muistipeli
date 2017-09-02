package muistipeli.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * Luokka tarjoaa käyttöliittymän valintapainikkeisiin liittyviä metodeita.
 */
public class ValintaPainike extends JButton implements ActionListener {

    private int numero;
    private String kohde;
    private Kayttoliittyma kayttoliittyma;
    private ValintaPaneeli valintaPaneeli;

    /**
     * Konstruktori valintaPainikkeelle.
     *
     * @param kayttoliittyma käyttöliittymä, johon valintaPainike liittyy
     * @param vp valintapaneeli, johon valintapainike lisätään
     * @param numero valintapainikkeen numero
     * @param kohde kohde, jonka arvon valitsemiseen valintapainiketta käytetään
     */
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
        boolean ok = true;
        if (this.kohde.equals("korkeus")) {
            if((this.kayttoliittyma.getPelinakyma().getLeveys() * numero) % 2 == 0) {
                this.kayttoliittyma.getPelinakyma().setKorkeus(numero);
            } else {
                this.kayttoliittyma.getPelinakyma().setKorkeus(numero + 1);
                this.valintaPaneeli.vahvistaValinta(numero + 1);
                ok = false;
            }
        }
        if (this.kohde.equals("leveys")) {
            if ((this.kayttoliittyma.getPelinakyma().getKorkeus() * numero) % 2 == 0) {
                this.kayttoliittyma.getPelinakyma().setLeveys(numero);
            } else {
                this.kayttoliittyma.getPelinakyma().setLeveys(numero + 1);
                this.valintaPaneeli.vahvistaValinta(numero + 1);
                ok = false;
            }
        }
        if (this.kohde.equals("pelaajien maara")) {
            this.kayttoliittyma.getPelinakyma().setPelaajienMaara(numero);
            if (this.kayttoliittyma.getPelinakyma().getTekoalyjenMaara() > numero) {
                this.kayttoliittyma.getPelinakyma().setTekoalyjenMaara(numero);
                this.kayttoliittyma.getTekoalyvalinta().vahvistaValinta(numero);
            }
        }
        if (this.kohde.equals("tekoalyjen maara")) {
            if (this.kayttoliittyma.getPelinakyma().getPelaajienMaara() >= numero) {
                this.kayttoliittyma.getPelinakyma().setTekoalyjenMaara(numero);
            } else {
                ok = false;
            }
        }
        if (ok) {
            this.valintaPaneeli.vahvistaValinta(this.numero);
        }
    }

    public int getNumero() {
        return this.numero;
    }

}
