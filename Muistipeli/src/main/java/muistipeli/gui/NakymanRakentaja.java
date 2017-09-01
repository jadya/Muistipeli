package muistipeli.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Luokka tarjoaa käyttöliittymän näkymien rakentamiseen liittyviä metodeita.
 */
public class NakymanRakentaja {

    private final Kayttoliittyma kayttoliittyma;

    /**
     * Konstruktori nakymanRakentajalle.
     *
     * @param kl käyttöliittymä, johon näkymän rakentaja liittyy
     */
    public NakymanRakentaja(Kayttoliittyma kl) {
        this.kayttoliittyma = kl;
    }

    /**
     * Metodi pelinäkymän rakentamiseen. Lisää pelinäkymän komponentit annettuun
     * säiliöön.
     *
     * @param leveys pelinäkymän pelin pelialustan leveys
     * @param korkeus pelinäkymän pelin pelialustan korkeus
     * @param c säiliö, johon luodut komponentit lisätään
     * @throws java.io.IOException keskeytys
     */
    public void rakennaPelinakyma(int leveys, int korkeus, Container c) throws IOException {
        this.kayttoliittyma.setTekoalyllaVuoroKesken(false);
        for (int i = 0; i < c.getComponentCount(); i++) {
            c.remove(i);
        }
        this.kayttoliittyma.setVuoronNayttaja(new VuoronNayttaja(this.kayttoliittyma.getPeli()));
        c.add(this.kayttoliittyma.getVuoronNayttaja(), BorderLayout.NORTH);
        c.add(new Kaynnistys(this.kayttoliittyma, "Lopeta peli"), BorderLayout.SOUTH);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(korkeus, leveys));
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                Korttipaikka korttipaikka = new Korttipaikka(this.kayttoliittyma, this.kayttoliittyma.getPeli().getPelialusta().getKortti(j, i), this.kayttoliittyma.getPeli().getPelialusta());
                this.kayttoliittyma.getKorttipaikat().add(korttipaikka);
                panel.add(korttipaikka);
            }
        }
        c.add(panel, BorderLayout.CENTER);
    }

    /**
     * Metodi valikon rakentamiseen. Lisää valikon komponentit annettuun
     * säiliöön.
     *
     * @param c säiliö, johon luodut komponentit lisätään
     * @throws java.io.IOException keskeytys
     */
    public void rakennaValikko(Container c) throws IOException {
        for (int i = 0; i < c.getComponentCount(); i++) {
            c.remove(i);
        }
        this.kayttoliittyma.setPelinakyma(new Pelinakyma());
        c.add(new JLabel("Muistipeli"), BorderLayout.NORTH);
        c.add(new Kaynnistys(this.kayttoliittyma, "Aloita peli"), BorderLayout.SOUTH);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1));
        panel.add(new JLabel("Valitse pelialustan korkeus:"));
        panel.add(new ValintaPaneeli(this.kayttoliittyma, "korkeus", 2, 10));
        panel.add(new JLabel("Valitse pelialustan leveys:"));
        panel.add(new ValintaPaneeli(this.kayttoliittyma, "leveys", 2, 10));
        panel.add(new JLabel("Valitse pelaajien maara:"));
        panel.add(new ValintaPaneeli(this.kayttoliittyma, "pelaajien maara", 2, 10));
        panel.add(new JLabel("Tekoalyjen maara pelaajista:"));
        ValintaPaneeli aly = new ValintaPaneeli(this.kayttoliittyma, "tekoalyjen maara", 0, 10);
        this.kayttoliittyma.setTekoalyvalinta(aly);
        panel.add(aly);
        c.add(panel, BorderLayout.CENTER);
    }

    /**
     * Metodi pistenäkymän rakentamiseen. Lisää pistenäkymän komponentit
     * annettuun säiliöön.
     *
     * @param c säiliö, johon luodut komponentit lisätään
     */
    public void rakennaPistenakyma(Container c) {
        for (int i = 0; i < c.getComponentCount(); i++) {
            c.remove(i);
        }
        c.add(new JLabel("Muistipeli"), BorderLayout.NORTH);
        c.add(new Kaynnistys(this.kayttoliittyma, "Uusi peli"), BorderLayout.SOUTH);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3 + this.kayttoliittyma.getPeli().getPelaajat().size() + this.kayttoliittyma.getPeli().johdossa().size(), 1));
        panel.add(new JLabel("Peli ohi"));
        if (this.kayttoliittyma.getPeli().johdossa().size() > 1) {
            panel.add(new JLabel("Voittajat: "));
            for (int i = 0; i < kayttoliittyma.getPeli().johdossa().size(); i++) {
                panel.add(new JLabel(kayttoliittyma.getPeli().johdossa().get(i).toString()));
            }
        } else if (this.kayttoliittyma.getPeli().johdossa().size() == 1) {
            panel.add(new JLabel("Voittaja: "));
            panel.add(new JLabel(kayttoliittyma.getPeli().johdossa().get(0).toString()));
        }
        panel.add(new JLabel("Tulokset:"));
        this.kayttoliittyma.getPeli().getPelaajat().stream().forEach((p) -> {
            panel.add(new JLabel(p.toString()));
        });
        c.add(panel, BorderLayout.CENTER);
    }

}
