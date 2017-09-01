package muistipeli.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import muistipeli.domain.PeliKortti;
import muistipeli.domain.Pelialusta;
import muistipeli.domain.Tekoaly;

/**
 * Luokka tarjoaa pelin käyttöliittymän korttipaikkoihin liittyviä metodeita.
 */
public class Korttipaikka extends JButton implements ActionListener {

    private PeliKortti kortti;
    private int xKoord;
    private int yKoord;
    private final Pelialusta alusta;
    private ArrayList<ImageIcon> kuvat;
    private final ImageIcon kuva;
    private final ImageIcon selka;
    private final ImageIcon tyhja;
    private boolean onTyhja;
    private final Kayttoliittyma kayttoliittyma;
    
    /**
     * Konstruktori korttipaikalle.
     * @param kl käyttöliittymä, johon korttipaikka liittyy
     * @param pelikortti korttipaikkaan asetettava pelikortti
     * @param pelialusta pelialusta, jonka kohtaa korttipaikka esittää 
     * käyttöliittymässä
     */
    public Korttipaikka(Kayttoliittyma kl, PeliKortti pelikortti, Pelialusta pelialusta) {
        this.kortti = pelikortti;
        this.xKoord = pelikortti.getX();
        this.yKoord = pelikortti.getY();
        this.alusta = pelialusta;
        String sijainti = "kuvat/kuva" + pelikortti.getKuvanNumero() + ".png";
        this.kuva = new ImageIcon(sijainti);
        this.selka = new ImageIcon("kuvat/selka.png");
        this.tyhja = new ImageIcon("kuvat/tyhja.png");
        this.setIcon(selka);
        this.onTyhja = false;
        this.addActionListener(this);
        this.kayttoliittyma = kl;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (this.alusta.getKaantotilanne()[xKoord][yKoord] == 0 && this.alusta.getNakyma().kuviaNakyvilla() < 2) {
            this.alusta.kaannaKortti(xKoord, yKoord);
            this.setIcon(kuva);
        } else if (this.alusta.getNakyma().kuviaNakyvilla() == 2) {
            kayttoliittyma.getPeli().kierros();
            kayttoliittyma.poistaYlimaaraisetKorttipaikat();
            kayttoliittyma.getVuoronNayttaja().paivitaVuoro();
            if (!this.kayttoliittyma.getPeli().getPelialusta().tyhja()) {
                kayttoliittyma.uusiKierros();
            }
            SwingUtilities.updateComponentTreeUI(kayttoliittyma.getFrame());
        }
        if (!this.kayttoliittyma.getPeli().getPelialusta().tyhja() && kayttoliittyma.getPeli().getVuoro().getPelaaja().getOnTekoaly()) {
            this.kayttoliittyma.getTekoalynVuoro().setTekoaly((Tekoaly) kayttoliittyma.getPeli().getVuoro().getPelaaja());
            this.kayttoliittyma.getTekoalynVuoro().pelaa();
        }
        SwingUtilities.updateComponentTreeUI(kayttoliittyma.getFrame());
    }
    
    /**
     * Metodi asettaa kortin selkää vastaavan kuvan näkyville käyttöliittymässä.
     */
    public void setSelka() {
        this.setIcon(selka);
    }
    
    /**
     * Metodi asettaa tyhjää kohtaa vastaavan kuvan näkyville käyttöliittymässä.
     */
    public void setTyhja() {
        this.onTyhja = true;
        this.setIcon(tyhja);
    }

    public boolean getTyhja() {
        return this.onTyhja;
    }

    public PeliKortti getKortti() {
        return this.kortti;
    }

    public void setKortti(PeliKortti pelikortti) {
        this.kortti = pelikortti;
    }

    public int getXkoord() {
        return this.xKoord;
    }

    public int getYkoord() {
        return this.yKoord;
    }

}
