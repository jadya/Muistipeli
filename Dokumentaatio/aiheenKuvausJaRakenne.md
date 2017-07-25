## Aihemäärittely

**Aihe:** Muistipeli

Toteutetaan muistipeli, jossa pelaajat etsivät korttien joukosta samanlaisista korteista muodostuvia pareja. Pelaajat voivat kääntää vuorotellen jokainen vuorollaan kaksi korttia. Jos käännetyt kortit muodostavat parin, ne kääntänyt pelaaja saa pisteen. Pisteen saanut pelaaja saa pitää vuoron itsellään eli kääntää uudestaan kaksi korttia, kunnes nämä kaksi käännettyä korttia eivät enää muodosta paria. Sen jälkeen vuoro siirtyy järjestyksessä seuraavalle pelaajalle.

Pelin alussa pelialustalla on parillinen määrä kortteja selkäpuoli näkyvillä. Jokaisen kortin kääntöpuolella on kuva, ja samanlaisia kuvia on aina kaksi kappaletta. Pelaajan löytäessä korttiparin kyseiseen pariin kuuluvat kaksi korttia poistuvat pelialustalta. Peli päättyy, kun alustalla ei ole enää yhtään korttia. Pelin voittaa se pelaaja, jolla on pelin päätyttyä eniten pisteitä.

**Käyttäjät:** Pelaaja

**Käyttäjän toiminnot:**
- pelin aloittaminen
	- onnistuu, jos ei ole käynnissä olevaa peliä (aloitettu, muttei vielä 	lopetettu/päättynyt) 	
- kortin kääntäminen
	- onnistuu, jos peli on käynnissä ja vuoro on pelaajalla sekä käännettyjä kortteja 	alustalla yksi tai ei yhtään
- pelin lopettaminen
	- onnistuu, jos peli on käynnissä ja vuoro pelaajalla