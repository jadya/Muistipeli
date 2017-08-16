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

public class Kayttoliittyma implements Runnable{

    private JFrame frame;
    private ArrayList<Korttipaikka> korttipaikat;
    private Peli peli;
    private Pelinakyma nakyma;

    public Kayttoliittyma(Peli peli) {
        this.korttipaikat = new ArrayList<>();
        this.peli = peli;
        this.nakyma = null;
        
    }
    
    @Override
    public void run() {
        frame = new JFrame("Muistipeli");
        frame.setSize(700,700);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        frame.setVisible(true);
        rakennaPelinakyma(4,4);
    }

    public void rakennaPelinakyma(int leveys, int korkeus) {
        this.frame.add(new JLabel("Muistipeli"), BorderLayout.NORTH);
        this.frame.add(new JButton("Lopeta peli"), BorderLayout.EAST);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(leveys, korkeus));
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                Korttipaikka korttipaikka = new Korttipaikka(this,this.peli.getPelialusta().getKortti(j, i), this.peli.getPelialusta());
                korttipaikat.add(korttipaikka);
                panel.add(korttipaikka);
            }
        }
        this.frame.add(panel, BorderLayout.CENTER);
        this.nakyma = new Pelinakyma();
    }
    
    public void kaynnistaPeli() {
        while(peli.kaynnissa()) {
            if(peli.getPelialusta().kuviaNakyvilla() == 2) {
                uusiKierros();
            }
        }
    }
    
    public void uusiKierros() {
        for(Korttipaikka k : this.korttipaikat) {
            if(!k.getTyhja()) {
                k.setSelka();
            } 
        }
        System.out.println(this.peli.tilanne()); //GUI
    }
    
    public void poistaYlimaaraisetKorttipaikat() {
        for(Korttipaikka k : this.korttipaikat) {
            if(this.peli.getPelialusta().getPoistetutKortit().contains(k.getKortti())) {
                k.setTyhja();
            }
        }
    }

    public void setPeli(Peli peli) {
        this.peli = peli;
    }

    public Peli getPeli() {
        return this.peli;
    }
    
    public Pelinakyma getNakyma() {
        return this.nakyma;
    }
    
    public Korttipaikka getKorttipaikka(int x, int y) {
        for(Korttipaikka kp : this.korttipaikat) {
            if(kp.getX() == x && kp.getY() == y) {
                return kp;
            }
        }
        return null;
    }

}
