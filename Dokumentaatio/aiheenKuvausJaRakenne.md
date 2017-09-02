## Aihemäärittely

**Aihe:** Muistipeli

Toteutetaan muistipeli, jossa pelaajat etsivät korttien joukosta samanlaisista korteista muodostuvia pareja. Pelaajat voivat kääntää vuorotellen jokainen vuorollaan kaksi korttia. Jos käännetyt kortit muodostavat parin, ne kääntänyt pelaaja saa pisteen. Pisteen saanut pelaaja saa pitää vuoron itsellään eli kääntää uudestaan kaksi korttia, kunnes nämä kaksi käännettyä korttia eivät enää muodosta paria. Sen jälkeen vuoro siirtyy järjestyksessä seuraavalle pelaajalle.

Pelin alussa pelialustalla on parillinen määrä kortteja selkäpuoli näkyvillä. Jokaisen kortin kääntöpuolella on kuva, ja samanlaisia kuvia on aina kaksi kappaletta. Pelaajan löytäessä korttiparin kyseiseen pariin kuuluvat kaksi korttia poistuvat pelialustalta. Peli päättyy, kun alustalla ei ole enää yhtään korttia. Pelin voittaa se pelaaja, jolla on pelin päätyttyä eniten pisteitä. Pelaaja voi myös lopettaa kesken pelin, jolloin peli päättyy.

**Käyttäjät:** Pelaaja

**Käyttäjän toiminnot:**
- pelin aloittaminen
	- onnistuu, jos ei ole käynnissä olevaa peliä (aloitettu, muttei vielä 	lopetettu/päättynyt) 	
- kortin kääntäminen
	- onnistuu, jos peli on käynnissä ja vuoro on pelaajalla sekä käännettyjä kortteja 	alustalla yksi tai ei yhtään
- pelin lopettaminen
	- onnistuu, jos peli on käynnissä ja vuoro pelaajalla
	
## Rakennekuvaus

Pelissä on seitsemän luokkaa ja käyttöliittymäluokat. Graafinen käyttöliittymä muodostuu käyttöliittymäluokista. Itse peliin liittyvät luokat Peli, Pelaaja, Pelialusta, Vuoro, PeliKortti, Nakyma ja Tekoaly.

Keskeinen luokka on Peli, johon kytkeytyvät luokat Pelialusta, Pelaaja, Vuoro ja Tekoaly. Luokat Nakyma ja PeliKortti kytkeytyvät Pelialustaan. Vuoro kytkeytyy myös luokkaan Pelaaja ja Tekoaly on Pelaajan aliluokka. Peliin liittyy yksi pelikohtainen Pelialusta ja Pelialustaan yksi vain siihen liittyvä Nakyma. Pelialustaan liittyy useita PeliKortteja ja jokainen PeliKortti liittyy ainoastaan kyseiseen Pelialustaan. Peliin liittyy yksi pelikohtainen Vuoro, johon liittyy useita Pelaajia, jotka kaikki liittyvät yhteen Vuoroon. Tekoalyt ovat lisäksi suoraan yhteydessä peliin.

## Luokkakaavio

![Luokkakaavio](/Dokumentaatio/luokkakaavio.PNG "Luokkakaavio")

## Sekvenssikaavioita


Pelin aloittaminen

![Sekvenssikaavio](/Dokumentaatio/sekvenssikaavio_aloitapeli.PNG "Sekvenssikaavio")

Kortin kääntäminen

![Sekvenssikaavio](/Dokumentaatio/sekvenssikaavio_kaannakortti.PNG "Sekvenssikaavio")

Vuoron vaihto

![Sekvenssikaavio](/Dokumentaatio/sekvenssikaavio_vaihdavuoro.PNG "Sekvenssikaavio")
