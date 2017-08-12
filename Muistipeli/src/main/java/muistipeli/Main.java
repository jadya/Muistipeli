package muistipeli;

import java.util.Scanner;
import muistipeli.domain.Pelaaja;
import muistipeli.logiikka.Peli;

public class Main {

    public static void main(String[] args) {
        System.out.println("Muistipeli");
        Peli peli = new Peli(6, 4);
        peli.lisaaPelaaja(new Pelaaja("a", 1));
        peli.lisaaPelaaja(new Pelaaja("b", 2));
        peli.aloitaPeli();
        System.out.println(peli.tilanne());
        for (int i = 0; i < peli.getPelialusta().getKorkeus(); i++) {
            for (int j = 0; j < peli.getPelialusta().getLeveys(); j++) {
                System.out.print(peli.nakyma()[j][i] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
//        for(int i = 0 ; i < peli.getPelialusta().getKorkeus() ; i++) {
//            for(int j = 0 ; j < peli.getPelialusta().getLeveys() ; j++) {
//                System.out.print(peli.getPelialusta().getKorttienSijainnit()[j][i] + " ");
//            }
//            System.out.println("");
//        }

        while (peli.kaynnissa() && !peli.getPelialusta().tyhja()) {
            for (int f = 0; f < peli.getPelialusta().getKorkeus(); f++) {
                for (int j = 0; j < peli.getPelialusta().getLeveys(); j++) {
                    System.out.print(peli.nakyma()[j][f] + " ");
                }
                System.out.println("");
            }
            System.out.println("Vuoro: " + peli.getVuoro().getPelaaja().getNimimerkki());
            int i = 0;
            while (i < 2 && peli.kaynnissa()) {
                int u = 0;
                while (u == 0) {
                    try {
                        siirto(peli);
                        u++;
                    } catch (Exception e) {
                        System.out.println("Siirto ei ole mahdollinen. Yritä uudestaan.");
                    }
                }
                i++;
                for (int f = 0; f < peli.getPelialusta().getKorkeus(); f++) {
                    for (int j = 0; j < peli.getPelialusta().getLeveys(); j++) {
                        System.out.print(peli.nakyma()[j][f] + " ");
                    }
                    System.out.println("");
                }
            }

            if (peli.kaynnissa()) {
                boolean vuoroSailyy = peli.getPelialusta().tarkistaPari();
                if (!vuoroSailyy) {
                    peli.vaihdaVuoro();
                } else {
                    peli.getVuoro().getPelaaja().lisaaPiste();
                }
            }
            System.out.println(peli.tilanne());
        }
        System.out.println("Voittaja:");
        for (Pelaaja p : peli.johdossa()) {
            System.out.println(p.getNimimerkki() + " (" + p.getId() + ") : " + p.getPisteet() + " pistettä");
        }

    }

    public static void siirto(Peli peli) throws Exception {
        Scanner lukija = new Scanner(System.in);
        int x;
        int y;
        System.out.println("Anna käännettävän kortin koordinaatit tai lopeta antamalla negatiivinen luku:");
        System.out.print("x: ");
        String syote = lukija.nextLine();
        x = Integer.parseInt(syote);
        if (x < 0) {
            peli.lopeta();
            return;
        }
        System.out.print("y: ");
        y = Integer.parseInt(lukija.nextLine());
        if (y < 0) {
            peli.lopeta();
            return;
        }

        if (peli.getPelialusta().getKaantotilanne()[x][y] == 1) {
            throw new Exception();
        }
        peli.getPelialusta().kaannaKortti(x, y);
    }

}
