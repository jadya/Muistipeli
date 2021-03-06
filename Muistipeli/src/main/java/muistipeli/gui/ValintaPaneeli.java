package muistipeli.gui;

import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Luokka tarjoaa käyttöliittymän valintapaneeleihin liittyviä metodeita.
 */
public class ValintaPaneeli extends JPanel {

    private ArrayList<ValintaPainike> valintaPainikkeet;
    private Kayttoliittyma kayttoliittyma;
    private ImageIcon valintaKuva;

    /**
     * Konstruktori valintaPaneelille.
     *
     * @param k käyttöliittymä, johon valintaPaneeli liittyy
     * @param valinnanKohde valintaPaneelin valinnan kohde
     * @param alku pienin kokonaislukuarvo, joka kohteelle voidaan valita
     * @param loppu suurin kokonaislukuarvo, joka kohteelle voidaan valita
     * @throws java.io.IOException keskeytys
     */
    public ValintaPaneeli(Kayttoliittyma k, String valinnanKohde, int alku, int loppu) throws IOException {
        this.valintaPainikkeet = new ArrayList<>();
        this.kayttoliittyma = k;
        this.valintaKuva = new ImageIcon((Image) ImageIO.read(getClass().getClassLoader().getResourceAsStream("kuvat/valinta.png")));
        this.setLayout(new GridLayout(1, loppu - alku + 1));
        for (int i = alku; i <= loppu; i++) {
            ValintaPainike painike = new ValintaPainike(this.kayttoliittyma, this, i, valinnanKohde);
            this.add(painike);
            this.valintaPainikkeet.add(painike);
            if (i == alku) {
                painike.setIcon(valintaKuva);
            }
        }
    }

    /**
     * Metodi valinnan vahvistamiseen. Siirtää valintakuvan valitun arvon
     * kohdalle.
     *
     * @param numero valintapainikkeen numero, johon valintakuva siirretään
     */
    public void vahvistaValinta(int numero) {
        this.valintaPainikkeet.stream().forEach((v) -> {
            if (v.getNumero() == numero) {
                v.setIcon(valintaKuva);
            } else {
                v.setIcon(null);
            }
        });
    }

}
