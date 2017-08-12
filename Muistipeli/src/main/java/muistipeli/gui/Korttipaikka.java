package muistipeli.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import muistipeli.domain.PeliKortti;
import muistipeli.domain.Pelialusta;

public class Korttipaikka extends JButton implements ActionListener {

    private PeliKortti kortti;
    private int x;
    private int y;
    private final Pelialusta alusta;

    public Korttipaikka(PeliKortti pelikortti, Pelialusta pelialusta) {
        this.kortti = pelikortti;
        this.x = pelikortti.getX();
        this.y = pelikortti.getY();
        this.alusta = pelialusta;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (this.alusta.getKaantotilanne()[x][y] == 0 && this.alusta.kuviaNakyvilla() < 2) {
            this.alusta.kaannaKortti(x, y);
        }

    }

    public PeliKortti getKortti() {
        return this.kortti;
    }

    public void setKortti(PeliKortti pelikortti) {
        this.kortti = pelikortti;
    }

}
