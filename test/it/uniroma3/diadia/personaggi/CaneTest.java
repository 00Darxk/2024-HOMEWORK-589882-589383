package it.uniroma3.diadia.personaggi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Proprietà;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class CaneTest {
	private static final String MESSAGGIO_MORSO = Proprietà.getMessaggioMorso();
	private static final String MESSAGGIO_CIBO = Proprietà.getMessaggioCibo();
	
	private AbstractPersonaggio cane;
	private Attrezzo bastone;
	private Partita partita;
	private int CFUIniziali;
	
	@Before
	public void setUp() {
		this.bastone = new Attrezzo("bastone", 2);
		this.cane = new Cane("cane","baubau", "osso", bastone);
		this.partita = new Partita(Labirinto.newBuilder()
				.addStanzaIniziale("stanza")
				.addPersonaggio(this.cane)
				.getLabirinto());
		this.CFUIniziali = this.partita.getGiocatore().getCFU();
	}
	
	@Test
	public void agisciTest_senzaDareCibo() {
		assertEquals(MESSAGGIO_MORSO, this.cane.agisci(this.partita));
		assertEquals(this.CFUIniziali-1, this.partita.getGiocatore().getCFU());
	}
	
	@Test
	public void riceviRegaloTest_conCiboGiusto() {
		assertEquals(MESSAGGIO_CIBO, this.cane.riceviRegalo(new Attrezzo("osso", 2), this.partita));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("bastone"));
	}
	
	@Test
	public void riceviRegaloTest_conCiboSbagliato() {
		assertEquals(MESSAGGIO_MORSO, this.cane.riceviRegalo(new Attrezzo("piuma", 1), this.partita));
		assertEquals(this.CFUIniziali-1, this.partita.getGiocatore().getCFU());
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("bastone"));
	}
	
	@Test
	public void riceviRegaloTest_nessunAttrezzoInBocca() {
		this.cane = new Cane("cane","baubau", "osso", null);
		this.cane.riceviRegalo(new Attrezzo("osso", 2), this.partita);
		assertTrue(this.partita.getStanzaCorrente().getAttrezzi().isEmpty());
		this.cane.riceviRegalo(bastone, this.partita);
		this.cane.riceviRegalo(new Attrezzo("osso", 2), this.partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("bastone"));
	}
	
	@Test
	public void riceviRegaloTest_stanzaPienaCiboMangiato() {
		this.riempiStanza(this.partita.getStanzaCorrente());
		this.cane.riceviRegalo(new Attrezzo("osso", 2), this.partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("bastone"));
	}
	
	@Test
	public void riceviRegaloTest_stanzaPienaCiboSbgliato() {
		this.riempiStanza(this.partita.getStanzaCorrente());
		this.cane.riceviRegalo(new Attrezzo("piuma", 2), this.partita);
		assertTrue(this.partita.getGiocatore().getBag().hasAttrezzo("piuma"));
	}
	
	private void riempiStanza(Stanza stanza) {
		for(int i = 0; i<Proprietà.getNumeroMassimoAttrezzi(); i++)
			stanza.addAttrezzo(new Attrezzo("temp"+i,i));
	}
}
