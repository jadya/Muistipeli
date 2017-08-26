package muistipeli.domain;

import java.util.ArrayList;
import java.util.Random;
import muistipeli.logiikka.Nakyma;
import muistipeli.logiikka.Peli;

/**
 * Luokka tarjoaa muistipelin pelialustan käsittelyyn tarvittavia metodeita.
 */
public class Pelialusta {

    private int leveys;
    private int korkeus;
    private int[][] korttienSijainnit;
    private int[][] kaantotilanne;
    private ArrayList<PeliKortti> kortit;
    private ArrayList<PeliKortti> poistetutKortit;
    private Nakyma nakyma;
    private Peli peli;
    
    /**
     * Konstruktori alustaa Pelialustan.
     * @param peli Peli, johon alusta liittyy
     * @param leveys luotavan alustan leveys
     * @param korkeus luotavan alustan korkeus
     */
    public Pelialusta(Peli peli, int leveys, int korkeus) {
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.korttienSijainnit = new int[leveys][korkeus];
        this.kaantotilanne = new int[leveys][korkeus];
        for (int i = 0; i < this.korkeus; i++) {
            for (int j = 0; j < this.leveys; j++) {
                this.korttienSijainnit[j][i] = -99;
                this.kaantotilanne[j][i] = -99;
            }
        }
        this.kortit = new ArrayList<>();
        this.poistetutKortit = new ArrayList<>();
        this.nakyma = new Nakyma(this);
        this.peli = peli;
    }

    /**
     * Metodi lisää annetun kortin pelialustalle, mikäli alusta ei ole täysi, ja
     * palauttaa kortin asettumista pelialustalle kuvaavan totuusarvon.
     * @param kortti Pelialustalle lisättävä pelikortti
     * @return totuusarvo kortin lisäämisen onnistumiselle
     */
    public boolean lisaaKortti(PeliKortti kortti) {
        if (!this.taynna()) {
            int[] kohta = this.arvoKohta();
            while (this.korttienSijainnit[kohta[0]][kohta[1]] != -99) {
                kohta = this.arvoKohta();
            }
            kortti.setX(kohta[0]);
            kortti.setY(kohta[1]);
            this.kortit.add(kortti);
            this.korttienSijainnit[kohta[0]][kohta[1]] = kortti.getKuvanNumero();
            this.kaantotilanne[kohta[0]][kohta[1]] = 0;
            return true;
        }
        return false;
    }

    /**
     * Metodi poistaa annetun kortin pelialustalta ja pelin tekoalyjen 
     * muisteista, mikäli kyseinen kortti onalustalla.
     * @param kortti Poistettava kortti
     */
    public void poistaKortti(PeliKortti kortti) {
        if (this.kortit.contains(kortti)) {
            this.korttienSijainnit[kortti.getX()][kortti.getY()] = -99;
            this.kaantotilanne[kortti.getX()][kortti.getY()] = -99;
            this.poistetutKortit.add(kortti);
            this.kortit.remove(kortti);
            this.peli.getTekoalyt().stream().forEach((t) -> {
                t.poistaMuistista(kortti);
            });
        }
    }

    /**
     * Metodi antaa kysytyissä koordinaateissa pelialustalla sijaitsevan kortin.
     * @param x Halutun kortin x-koordinaatti pelialustalla
     * @param y Halutun kortin y-koordinaatti pelialustalla
     * @return annetussa paikassa sijaitseva kortti
     */
    public PeliKortti getKortti(int x, int y) {
        for (PeliKortti kortti : this.kortit) {
            if (kortti.getX() == x && kortti.getY() == y) {
                return kortti;
            }
        }
        return null;
    }

    /**
     * Metodi kääntää kysytyissä koordinaateissa pelialustalla sijaitsevan
     * kortin, jos kyseisissä koordinaateissa sijaitsee kortti, sekä lisää 
     * kyseisen kortin pelin tekoalyjen muisteihin, jos se ei jo ole niissä.
     * @param x Käännettävän kortin x-koordinaatti pelialustalla
     * @param y Käännettävän kortin y-koordinaatti pelialustalla
     * @return totuusarvo kortin löytymiselle annetuista koordinaateista
     */
    public boolean kaannaKortti(int x, int y) {
        if (this.kaantotilanne[x][y] != -99) {
            this.kaantotilanne[x][y] = Math.abs(this.kaantotilanne[x][y] - 1);
            this.peli.getTekoalyt().stream().filter((t) -> (!t.getMuisti().contains(this.getKortti(x, y)))).forEach((t) -> {
                t.lisaaMuistiin(this.getKortti(x, y));
            });
            return true;
        }
        return false;
    }

    /**
     * Metodi arpoo satunnaisen kohdan pelialustalta.
     * @return arvotun kohdan x- ja y-koordinaatti taulukossa
     */
    public int[] arvoKohta() {
        int[] kohta = new int[2];
        Random random = new Random();
        kohta[0] = random.nextInt(leveys);
        kohta[1] = random.nextInt(korkeus);
        return kohta;
    }

    /**
     * Metodi kertoo, onko pelialusta täynnä eli onko pelialustan kaikissa
     * kohdissa kortti.
     * @return totuusarvo väitteelle pelialusta on täynnä
     */
    public boolean taynna() {
        return this.kortit.size() == this.korkeus * this.leveys;
    }

    /**
     * Metodi kertoo, onko pelialusta tyhjä eli ovatko pelialustan kaikki kohdat
     * vapaana.
     * @return totuusarvo väitteelle pelialusta on tyhjä
     */
    public boolean tyhja() {
        return this.kortit.isEmpty();
    }

    public int getKorkeus() {
        return this.korkeus;
    }

    public int getLeveys() {
        return this.leveys;
    }

    public int[][] getKorttienSijainnit() {
        return this.korttienSijainnit;
    }

    public int[][] getKaantotilanne() {
        return this.kaantotilanne;
    }

    public ArrayList<PeliKortti> getKortit() {
        return this.kortit;
    }

    public ArrayList<PeliKortti> getPoistetutKortit() {
        return this.poistetutKortit;
    }
    
    public void setKorttienSijainnit(int[][] sijainnit) {
        this.korttienSijainnit = sijainnit;
    }

    public void setKaantotilanne(int[][] tilanne) {
        this.kaantotilanne = tilanne;
    }

    /**
     * Metodi asettaa annetun listan kortit pelialustalle listan pituuden
     * ollessa korkeintaan pelialustan suuruinen.
     * @param korttiLista lista pelialustalle lisättävistä korteista
     */
    public void setKortit(ArrayList<PeliKortti> korttiLista) {
        if (korttiLista.size() <= this.korkeus * this.leveys) {
            korttiLista.stream().forEach((kortti) -> {
                this.lisaaKortti(kortti);
            });
        }
    }

    public Nakyma getNakyma() {
        return this.nakyma;
    }

    public Peli getPeli() {
        return this.peli;
    }
}