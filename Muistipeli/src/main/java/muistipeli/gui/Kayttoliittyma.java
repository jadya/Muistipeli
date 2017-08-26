package muistipeli.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import muistipeli.domain.Pelaaja;
import muistipeli.domain.Tekoaly;
import muistipeli.logiikka.Peli;

public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private ArrayList<Korttipaikka> korttipaikat;
    private Peli peli;
    private Pelinakyma pelinakyma;
    private VuoronNayttaja vuoronNayttaja;
    private NakymanVaihtaja nakymanVaihtaja;
    private boolean tekoalyllaVuoroKesken;
    private TekoalynVuoro tekoalynVuoro;

    public Kayttoliittyma() {
        this.korttipaikat = new ArrayList<>();
        this.peli = null;
        this.pelinakyma = null;
        this.vuoronNayttaja = null;
        this.nakymanVaihtaja = new NakymanVaihtaja(this);
        this.tekoalyllaVuoroKesken = false;
        this.tekoalynVuoro = null;
    }

    @Override
    public void run() {
        frame = new JFrame("Muistipeli");
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        rakennaValikko(frame.getContentPane());
    }

    public void rakennaPelinakyma(int leveys, int korkeus, Container c) {
        this.tekoalyllaVuoroKesken = false;
        for (int i = 0; i < c.getComponentCount(); i++) {
            c.remove(i);
        }
        this.vuoronNayttaja = new VuoronNayttaja(this.peli);
        c.add(this.vuoronNayttaja, BorderLayout.NORTH);
        c.add(new Kaynnistys(this, "Lopeta peli"), BorderLayout.SOUTH);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(korkeus, leveys));
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                Korttipaikka korttipaikka = new Korttipaikka(this, this.peli.getPelialusta().getKortti(j, i), this.peli.getPelialusta());
                korttipaikat.add(korttipaikka);
                panel.add(korttipaikka);
            }
        }
        c.add(panel, BorderLayout.CENTER);
    }

    public void rakennaValikko(Container c) {
        for (int i = 0; i < c.getComponentCount(); i++) {
            c.remove(i);
        }
        this.pelinakyma = new Pelinakyma();
        c.add(new JLabel("Muistipeli"), BorderLayout.NORTH);
        c.add(new Kaynnistys(this, "Aloita peli"), BorderLayout.SOUTH);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1));
        panel.add(new JLabel("Valitse pelialustan korkeus:"));
        panel.add(luoValintapaneeli("korkeus", 2, 10));
        panel.add(new JLabel("Valitse pelialustan leveys:"));
        panel.add(luoValintapaneeli("leveys", 2, 10));
        panel.add(new JLabel("Valitse pelaajien maara:"));
        panel.add(luoValintapaneeli("pelaajien maara", 2, 10));
        panel.add(new JLabel("Tekoalyjen maara pelaajista:"));
        panel.add(luoValintapaneeli("tekoalyjen maara", 0, 1));
        c.add(panel, BorderLayout.CENTER);
    }

    public JPanel luoValintapaneeli(String valinnanKohde, int alku, int loppu) {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(1, loppu - alku + 1));
        for (int i = alku; i <= loppu; i++) {
            p.add(new ValintaPainike(this, i, valinnanKohde));
        }
        return p;
    }

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

    public void rakennaPistenakyma(Container c) {
        for (int i = 0; i < c.getComponentCount(); i++) {
            c.remove(i);
        }
        c.add(new JLabel("Muistipeli"), BorderLayout.NORTH);
        c.add(new Kaynnistys(this, "Uusi peli"), BorderLayout.SOUTH);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3 + this.peli.getPelaajat().size() + this.peli.johdossa().size(), 1));
        panel.add(new JLabel("Peli ohi"));
        if (this.peli.johdossa().size() > 1) {
            panel.add(new JLabel("Voittajat: "));
            for (int i = 0; i < peli.johdossa().size(); i++) {
                panel.add(new JLabel(peli.johdossa().get(i).toString()));
            }
        } else if (this.peli.johdossa().size() == 1) {
            panel.add(new JLabel("Voittaja: "));
            panel.add(new JLabel(peli.johdossa().get(0).toString()));
        }
        panel.add(new JLabel("Tulokset:"));
        this.peli.getPelaajat().stream().forEach((p) -> {
            panel.add(new JLabel(p.toString()));
        });
        c.add(panel, BorderLayout.CENTER);
    }

    public void uusiKierros() {
        this.nakymanVaihtaja.valmisteleKierros();
    }

    public void poistaYlimaaraisetKorttipaikat() {
        this.korttipaikat.stream().filter((k) -> (this.peli.getPelialusta().getPoistetutKortit().contains(k.getKortti()))).forEach((k) -> {
            k.setTyhja();
        });
    }

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

    public JFrame getFrame() {
        return this.frame;
    }

    public VuoronNayttaja getVuoronNayttaja() {
        return this.vuoronNayttaja;
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
}
