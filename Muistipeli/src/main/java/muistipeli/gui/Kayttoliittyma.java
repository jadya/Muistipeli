package muistipeli.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import muistipeli.domain.Pelaaja;
import muistipeli.logiikka.Peli;

public class Kayttoliittyma implements Runnable{

    private JFrame frame;
    private ArrayList<Korttipaikka> korttipaikat;
    private Peli peli;
    private Pelinakyma pelinakyma;

    public Kayttoliittyma(Peli peli) {
        this.korttipaikat = new ArrayList<>();
        this.peli = peli;
        this.pelinakyma = null;
        
    }
    
    public Kayttoliittyma() {
        this.korttipaikat = new ArrayList<>();
        this.peli = null;
        this.pelinakyma = null;
    }
    
    @Override
    public void run() {
        frame = new JFrame("Muistipeli");
        frame.setSize(700,700);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        frame.setVisible(true);
        rakennaValikko(frame.getContentPane());
        
    }
    
    public void rakennaPelinakyma(int leveys, int korkeus, Container c) {
        for(int i = 0 ; i< c.getComponentCount();i++) {
            c.remove(i);
        }
        c.add(new JLabel("Muistipeli"), BorderLayout.NORTH);
        c.add(new Kaynnistys(this,"Lopeta peli"), BorderLayout.SOUTH);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(leveys, korkeus));
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                Korttipaikka korttipaikka = new Korttipaikka(this,this.peli.getPelialusta().getKortti(j, i), this.peli.getPelialusta());
                korttipaikat.add(korttipaikka);
                panel.add(korttipaikka);
            }
        }
        c.add(panel, BorderLayout.CENTER);
    }
    
    public void rakennaValikko(Container c) {
        for(int i = 0 ; i< c.getComponentCount();i++) {
            c.remove(i);
        }
        this.pelinakyma = new Pelinakyma();
        c.add(new JLabel("Muistipeli"), BorderLayout.NORTH);
        c.add(new Kaynnistys(this,"Aloita peli"), BorderLayout.SOUTH);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));
        
        JLabel korkeudenValinta = new JLabel("Valitse pelialustan korkeus:");
        panel.add(korkeudenValinta);
        
        JPanel korkeus = new JPanel();
        korkeus.setLayout(new GridLayout(1, 9));
        for(int i = 2; i<=10 ; i++) {
            korkeus.add(new ValintaPainike(this,i,"korkeus"));
        }
        panel.add(korkeus);
        
        JLabel leveydenValinta = new JLabel("Valitse pelialustan leveys:");
        panel.add(leveydenValinta);
        
        JPanel leveys = new JPanel();
        leveys.setLayout(new GridLayout(1, 9));
        for(int i = 2; i<=10 ; i++) {
            leveys.add(new ValintaPainike(this,i,"leveys"));
        }
        panel.add(leveys);
        
        JLabel pelaajaMaaranValinta = new JLabel("Valitse pelaajien maara:");
        panel.add(pelaajaMaaranValinta);
        
        JPanel pelaajat = new JPanel();
        pelaajat.setLayout(new GridLayout(1, 9));
        for(int i = 2; i<=10 ; i++) {
            pelaajat.add(new ValintaPainike(this,i,"pelaajien maara"));
        }
        panel.add(pelaajat);
        
        c.add(panel, BorderLayout.CENTER);
    }
    
    public void siirryPelinakymaan() {
        this.valmistelePeli();
        this.peli.aloitaPeli();
        
        Container c = frame.getContentPane();
        for(int i = 0 ; i< c.getComponentCount();i++) {
            c.remove(i);
        }
        
        this.rakennaPelinakyma(this.getNakyma().getLeveys(),this.getNakyma().getKorkeus(),frame.getContentPane());
        SwingUtilities.updateComponentTreeUI(frame);
    }
    
    public void valmistelePeli() {
        this.setPeli(new Peli(this.pelinakyma.getLeveys(),this.pelinakyma.getKorkeus()));
        for(int i = 1 ; i<=this.pelinakyma.getPelaajienMaara() ; i++) {
            peli.lisaaPelaaja(new Pelaaja("pelaaja"+i, i));
        }
    }
    
    public void rakennaPistenakyma(Container c) {
        for(int i = 0 ; i< c.getComponentCount();i++) {
            c.remove(i);
        }
        c.add(new JLabel("Muistipeli"), BorderLayout.NORTH);
        c.add(new Kaynnistys(this,"Uusi peli"), BorderLayout.SOUTH);
        JPanel panel = new JPanel();
        int rivit = 2 + this.peli.getPelaajat().size();
        panel.setLayout(new GridLayout(rivit, 1));
        panel.add(new JLabel("Peli ohi"));
        panel.add(new JLabel("Tulokset:"));
        for(Pelaaja p : this.peli.getPelaajat()) {
            panel.add(new JLabel(p.toString()));
        }
        c.add(panel, BorderLayout.CENTER);
    }
    
    public void siirryPistenakymaan() {
        Container c = frame.getContentPane();
        for(int i = 0 ; i< c.getComponentCount();i++) {
            c.remove(i);
        }
        
        this.rakennaPistenakyma(frame.getContentPane());
        SwingUtilities.updateComponentTreeUI(frame);
    }
    
    public void siirryValikkoon() {
        
        Container c = frame.getContentPane();
        for(int i = 0 ; i< c.getComponentCount();i++) {
            c.remove(i);
        }
        
        this.rakennaValikko(frame.getContentPane());
        SwingUtilities.updateComponentTreeUI(frame);
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
        return this.pelinakyma;
    }
    
    public Korttipaikka getKorttipaikka(int x, int y) {
        for(Korttipaikka kp : this.korttipaikat) {
            if(kp.getX() == x && kp.getY() == y) {
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

}
