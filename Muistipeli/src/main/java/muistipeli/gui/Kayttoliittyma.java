package muistipeli.gui;

import java.awt.Component;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import muistipeli.domain.Pelaaja;
import muistipeli.domain.Tekoaly;
import muistipeli.logiikka.Peli;

/**
 * Käyttöliittymä pelille.
 */
public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private ArrayList<Korttipaikka> korttipaikat;
    private Peli peli;
    private Pelinakyma pelinakyma;
    private VuoronNayttaja vuoronNayttaja;
    private NakymanVaihtaja nakymanVaihtaja;
    private NakymanRakentaja nakymanRakentaja;
    private boolean tekoalyllaVuoroKesken;
    private TekoalynVuoro tekoalynVuoro;

    /**
     * Konstruktori käyttöliittymälle.
     */
    public Kayttoliittyma() {
        this.korttipaikat = new ArrayList<>();
        this.peli = null;
        this.pelinakyma = null;
        this.vuoronNayttaja = null;
        this.nakymanVaihtaja = new NakymanVaihtaja(this);
        this.nakymanRakentaja = new NakymanRakentaja(this);
        this.tekoalyllaVuoroKesken = false;
        this.tekoalynVuoro = null;
    }

    @Override
    public void run() {
        frame = new JFrame("Muistipeli");
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        try {
            this.nakymanRakentaja.rakennaValikko(frame.getContentPane());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog((Component) frame, "Jotain meni nyt pahasti pieleen.");
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
    }

    /**
     * Metodi valmistelee pelin luomalla sen ja lisäämällä pelaajat ja
     * mahdollisen tekoälyn vuoron.
     */
    public void valmistelePeli() {
        this.peli = new Peli(this.pelinakyma.getLeveys(), this.pelinakyma.getKorkeus());
        for (int i = 1; i <= this.pelinakyma.getPelaajienMaara() - this.pelinakyma.getTekoalyjenMaara(); i++) {
            peli.lisaaPelaaja(new Pelaaja("pelaaja" + i, i));
        }
        for (int i = 1; i <= this.pelinakyma.getTekoalyjenMaara(); i++) {
            Tekoaly t = new Tekoaly("cpu" + i, this.pelinakyma.getPelaajienMaara() - this.pelinakyma.getTekoalyjenMaara() + i, this.peli);
            peli.lisaaPelaaja(t);
        }
        if (this.pelinakyma.getTekoalyjenMaara() > 0) {
            this.tekoalynVuoro = new TekoalynVuoro(this, null);
        }
    }

    /**
     * Metodi käyttöliittymän pelin kierroksen vaihtamiseen.
     */
    public void uusiKierros() {
        this.nakymanVaihtaja.valmisteleKierros();
    }

    /**
     * Metodi asettaa poistettuja kortteja vastaavat korttipaikat tyhjiksi
     * korttipaikkoiksi.
     */
    public void poistaYlimaaraisetKorttipaikat() {
        this.korttipaikat.stream().filter((k) -> (this.peli.getPelialusta().getPoistetutKortit().contains(k.getKortti()))).forEach((k) -> {
            k.setTyhja();
        });
    }
    
    /**
     * Metodi asettaa osan käyttöliittymän attribuuteista takaisin
     * alkuarvoihinsa.
     */
    public void nollaa() {
        this.korttipaikat = new ArrayList<>();
        this.peli = null;
        this.pelinakyma = null;
        this.vuoronNayttaja = null;
        this.nakymanVaihtaja = new NakymanVaihtaja(this);
        this.tekoalyllaVuoroKesken = false;
        this.tekoalynVuoro = null;
    }

    public Peli getPeli() {
        return this.peli;
    }

    /**
     * Metodi palauttaa kysyttyä pelialustan kohtaa vastaavassa kohdassa
     * sijaitsevan korttipaikan, tai null, jos kyseisessä kohdassa ei ole
     * korttipaikkaa.
     *
     * @param x x-koordinaatti pelialustalla
     * @param y y-koordinaatti pelialustalla
     * @return kysytyssä kohdassa sijaitseva korttipaikka
     */
    public Korttipaikka getKorttipaikka(int x, int y) {
        for (Korttipaikka kp : this.korttipaikat) {
            if (kp.getXkoord() == x && kp.getYkoord() == y) {
                return kp;
            }
        }
        return null;
    }

    public Pelinakyma getPelinakyma() {
        return this.pelinakyma;
    }

    public void setPelinakyma(Pelinakyma pelinakyma) {
        this.pelinakyma = pelinakyma;
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public VuoronNayttaja getVuoronNayttaja() {
        return this.vuoronNayttaja;
    }

    public void setVuoronNayttaja(VuoronNayttaja vuoronNayttaja) {
        this.vuoronNayttaja = vuoronNayttaja;
    }

    public NakymanVaihtaja getNakymanVaihtaja() {
        return this.nakymanVaihtaja;
    }

    public void setTekoalyllaVuoroKesken(boolean kesken) {
        this.tekoalyllaVuoroKesken = kesken;
    }

    public boolean getTekoalyllaVuoroKesken() {
        return this.tekoalyllaVuoroKesken;
    }

    public ArrayList<Korttipaikka> getKorttipaikat() {
        return this.korttipaikat;
    }

    public TekoalynVuoro getTekoalynVuoro() {
        return this.tekoalynVuoro;
    }

    public NakymanRakentaja getNakymanRakentaja() {
        return this.nakymanRakentaja;
    }
}
