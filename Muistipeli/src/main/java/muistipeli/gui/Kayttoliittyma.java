package muistipeli.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import muistipeli.logiikka.Peli;

public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private ArrayList<Korttipaikka> korttipaikat;
    private Peli peli;

    public Kayttoliittyma() {
        this.korttipaikat = new ArrayList<>();

    }

    @Override
    public void run() {
        this.frame = new JFrame("Muistipeli");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public void rakennaPelinakyma(int leveys, int korkeus) {
        this.frame.add(new JLabel("Muistipeli"), BorderLayout.NORTH);
        this.frame.add(new JButton("Lopeta peli"), BorderLayout.EAST);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(leveys, korkeus));
        for (int i = 0; i < leveys * korkeus; i++) {
            Korttipaikka korttipaikka = new Korttipaikka(null, this.peli.getPelialusta());
            korttipaikat.add(korttipaikka);
            panel.add(korttipaikka);
        }
        this.frame.add(panel, BorderLayout.CENTER);
    }

    public void setPeli(Peli peli) {
        this.peli = peli;
    }

    public Peli getPeli() {
        return this.peli;
    }

}
