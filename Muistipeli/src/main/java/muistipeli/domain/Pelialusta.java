package muistipeli.domain;

import java.util.ArrayList;
import java.util.Random;

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

    public Pelialusta(int leveys, int korkeus) {
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
    }

    /**
     * Metodi lisää annetun kortin pelialustalle, mikäli alusta ei ole täysi, ja
     * palauttaa kortin asettumista pelialustalle kuvaavan totuusarvon.
     *
     * @param kortti Pelialustalle lisättävä pelikortti
     *
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
     * Metodi poistaa annetun kortin pelialustalta, mikäli kyseinen kortti on
     * alustalla.
     *
     * @param kortti Poistettava kortti
     */
    public void poistaKortti(PeliKortti kortti) {
        if (this.kortit.contains(kortti)) {
            this.korttienSijainnit[kortti.getX()][kortti.getY()] = -99;
            this.kaantotilanne[kortti.getX()][kortti.getY()] = -99;
            this.poistetutKortit.add(kortti);
            this.kortit.remove(kortti);
        }
    }

    /**
     * Metodi antaa kysytyissä koordinaateissa pelialustalla sijaitsevan kortin.
     *
     * @param x Halutun kortin x-koordinaatti pelialustalla
     * @param y Halutun kortin y-koordinaatti pelialustalla
     *
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
     * kortin, jos kyseisissä koordinaateissa sijaitsee kortti.
     *
     * @param x Käännettävän kortin x-koordinaatti pelialustalla
     * @param y Käännettävän kortin y-koordinaatti pelialustalla
     *
     * @return totuusarvo kortin löytymiselle annetuista koordinaateista
     */
    public boolean kaannaKortti(int x, int y) {
        if (this.kaantotilanne[x][y] != -99) {
            this.kaantotilanne[x][y] = Math.abs(this.kaantotilanne[x][y] - 1);
            return true;
        }
        return false;
    }

    /**
     * Metodi kertoo, onko pelialustalla käännettynä kaksi samanlaisella kuvalla
     * varustettua korttia.
     *
     * @return totuusarvo väitteelle näkyvillä on kaksi samaa kuvaa
     */
    public boolean tarkistaPari() {
        int a = -99;
        int ai = -99;
        int aj = -99;
        int b = -99;
        int bi = -99;
        int bj = -99;
        for (int i = 0; i < this.korkeus; i++) {
            for (int j = 0; j < this.leveys; j++) {
                if (this.kaantotilanne[j][i] == 1) {
                    if (a == -99) {
                        a = this.korttienSijainnit[j][i];
                        ai = i;
                        aj = j;
                    } else {
                        b = this.korttienSijainnit[j][i];
                        bi = i;
                        bj = j;
                    }
                }
            }
        }
        if (a == b) {
            this.poistaKortti(getKortti(aj, ai));
            this.poistaKortti(getKortti(bj, bi));
            return true;
        } else {
            this.kaannaKortti(aj, ai);
            this.kaannaKortti(bj, bi);
            return false;
        }
    }

    /**
     * Metodi arpoo satunnaisen kohdan pelialustalta.
     *
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
     *
     * @return totuusarvo väitteelle pelialusta on täynnä
     */
    public boolean taynna() {
        return this.kortit.size() == this.korkeus * this.leveys;
    }

    /**
     * Metodi kertoo, onko pelialusta tyhjä eli ovatko pelialustan kaikki kohdat
     * vapaana.
     *
     * @return totuusarvo väitteelle pelialusta on tyhjä
     */
    public boolean tyhja() {
        return this.kortit.isEmpty();
    }

    /**
     * Metodi kertoo kuinka monta paikkaa pelialustalla on vapaana.
     *
     * @return vapaiden paikkojen määrä
     */
    public int paikkojaJaljella() {
        return this.leveys * this.korkeus - this.kortit.size();
    }

    /**
     * Metodi kertoo kuinka monta pelialustalla olevista korteista on
     * käännetttnä niin, että niiden kuvat ovat näkyvillä.
     *
     * @return näkyvillä olevien kuvien määrä
     */
    public int kuviaNakyvilla() {
        int maara = 0;
        for (int i = 0; i < this.korkeus; i++) {
            for (int j = 0; j < this.leveys; j++) {
                if (this.kaantotilanne[j][i] == 1) {
                    maara++;
                }
            }
        }
        return maara;
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

    public void setKorkeus(int korkeus) {
        this.korkeus = korkeus;
    }

    public void setLeveys(int leveys) {
        this.leveys = leveys;
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
     *
     * @param korttiLista lista pelialustalle lisättävistä korteista
     */
    public void setKortit(ArrayList<PeliKortti> korttiLista) {
        if (korttiLista.size() <= this.korkeus * this.leveys) {
            for (PeliKortti kortti : korttiLista) {
                this.lisaaKortti(kortti);
            }
        }
    }

}
