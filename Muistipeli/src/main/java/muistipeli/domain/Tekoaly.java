package muistipeli.domain;

import java.util.ArrayList;
import java.util.Random;
import muistipeli.logiikka.Peli;

public class Tekoaly extends Pelaaja {

    private ArrayList<PeliKortti> muisti;
    private Peli peli;

    /**
     * Konstruktori uuden Tekoaly-olion luomiseen.
     *
     * @param nimimerkki luotavan olion nimimerkki
     * @param id luotavan tekoalyn id-numero
     * @param peli Peli, johon luotava tekoaly liittyy
     */
    public Tekoaly(String nimimerkki, int id, Peli peli) {
        super(nimimerkki, id);
        this.muisti = new ArrayList<>();
        this.peli = peli;
        super.setOnTekoaly(true);
    }

    /**
     * Metodi palauttaa kortin, jonka tekoaly päättää kääntää.
     *
     * @return kortin koordinaatit pelialustalla [x,y]
     */
    public int[] teeSiirto() {
        int[] kaannettavanKortinKohta;
        PeliKortti kaannettyKortti = this.etsiAlustaltaKaannettyKortti();
        if (!this.muisti.isEmpty() && kaannettyKortti != null) {
            for (PeliKortti kortti : this.muisti) {
                if (kortti.getKuvanNumero() == kaannettyKortti.getKuvanNumero() && (kortti.getX() != kaannettyKortti.getX() || kortti.getY() != kaannettyKortti.getY())) {
                    kaannettavanKortinKohta = new int[2];
                    kaannettavanKortinKohta[0] = kortti.getX();
                    kaannettavanKortinKohta[1] = kortti.getY();
                    return kaannettavanKortinKohta;
                }
            }
        }
        kaannettavanKortinKohta = satunnainenKohta();
        return kaannettavanKortinKohta;
    }

    /**
     * Metodi arpoo pelialustalta satunnaisen kohdan, jossa on kortti.
     *
     * @return arvotun kohdan koordinaatit ilmoitettuna kaksipaikkaisessa
     * taulukossa
     */
    public int[] satunnainenKohta() {
        Random random = new Random();
        int[] kohta = new int[]{random.nextInt(this.peli.getPelialusta().getLeveys()), random.nextInt(this.peli.getPelialusta().getKorkeus())};
        while (this.peli.getPelialusta().getKaantotilanne()[kohta[0]][kohta[1]] == -99) {
            kohta = new int[]{random.nextInt(this.peli.getPelialusta().getLeveys()), random.nextInt(this.peli.getPelialusta().getKorkeus())};
        }
        return kohta;
    }

    /**
     * Metodi etsii pelialustalta kohdan, jossa on kortti käännettynä kuvapuoli
     * näkyvillä.
     *
     * @return pelialustalta löytynyt käännetty kortti tai null, jos korttia ei
     * löydy
     */
    public PeliKortti etsiAlustaltaKaannettyKortti() {
        for (int i = 0; i < this.peli.getPelialusta().getKorkeus(); i++) {
            for (int j = 0; j < this.peli.getPelialusta().getLeveys(); j++) {
                if (this.peli.getPelialusta().getKaantotilanne()[j][i] == 1) {
                    return this.peli.getPelialusta().getKortti(j, i);
                }
            }
        }
        return null;
    }

    /**
     * Metodi lisää kortin tekoälyn muistiin.
     *
     * @param kortti muistiin lisättävä kortti
     */
    public void lisaaMuistiin(PeliKortti kortti) {
        this.muisti.add(kortti);
    }

    /**
     * Metodi poistaa kortin tekoälyn muistista.
     *
     * @param kortti muistista poistettava kortti
     */
    public void poistaMuistista(PeliKortti kortti) {
        this.muisti.remove(kortti);
    }

    public Peli getPeli() {
        return this.peli;
    }

    public ArrayList<PeliKortti> getMuisti() {
        return this.muisti;
    }

}
