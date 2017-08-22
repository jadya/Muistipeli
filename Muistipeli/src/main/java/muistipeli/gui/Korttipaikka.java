package muistipeli.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import muistipeli.domain.PeliKortti;
import muistipeli.domain.Pelialusta;
import muistipeli.domain.Tekoaly;

public class Korttipaikka extends JButton implements ActionListener {

    private PeliKortti kortti;
    private int x;
    private int y;
    private final Pelialusta alusta;
    private ArrayList<ImageIcon> kuvat;
    private final ImageIcon kuva;
    private final ImageIcon selka;
    private final ImageIcon tyhja;
    private boolean onTyhja;
    private final Kayttoliittyma kayttoliittyma;

    public Korttipaikka(Kayttoliittyma kl, PeliKortti pelikortti, Pelialusta pelialusta) {
        this.kortti = pelikortti;
        this.x = pelikortti.getX();
        this.y = pelikortti.getY();
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
        if (this.alusta.getKaantotilanne()[x][y] == 0 && this.alusta.getNakyma().kuviaNakyvilla() < 2) {
            this.alusta.kaannaKortti(x, y);
            this.setIcon(kuva);
        } else if (this.alusta.getNakyma().kuviaNakyvilla() == 2) {
            kayttoliittyma.getPeli().kierros();
            kayttoliittyma.poistaYlimaaraisetKorttipaikat();
            kayttoliittyma.getVuoronNayttaja().paivitaVuoro();
            kayttoliittyma.uusiKierros();
            if(kayttoliittyma.getPeli().getVuoro().getPelaaja().onTekoaly()) {
                TekoalynVuoro vuoro = new TekoalynVuoro(kayttoliittyma, (Tekoaly) kayttoliittyma.getPeli().getVuoro().getPelaaja());
                vuoro.pelaa();
            }
        }
    }

    public void setSelka() {
        this.setIcon(selka);
    }

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

}
