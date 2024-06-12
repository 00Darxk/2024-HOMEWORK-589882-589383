package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {
	private Stanza bloccata;
	private Stanza vuota;
	private Stanza bloccataDirezioniOccupate;
	private Attrezzo attrezzo;
	
	@Before
	public void setUp() {
		this.bloccata = new StanzaBloccata("bloccata", Direzione.NORD, "attrezzo");
		this.bloccata.impostaStanzaAdiacente(Direzione.NORD, vuota);
		this.attrezzo = new Attrezzo("attrezzo", 2);
		this.bloccataDirezioniOccupate = new StanzaBloccata("bloccata", Direzione.SUD, "attrezzo");
	}
	
	@Test
	public void getStanzaAdiacenteTest_senzaAttrezzoNecessario() {
		assertEquals(this.bloccata,this.bloccata.getStanzaAdiacente(Direzione.NORD));
	}
	
	@Test
	public void getStanzaAdiacenteTest_conAttrezzoNecessario() {
		this.bloccata.addAttrezzo(this.attrezzo);
		assertEquals(this.vuota,this.bloccata.getStanzaAdiacente(Direzione.NORD));
	}
	
	@Test
	public void getStanzaAdiacenteTest_conAttrezzoDiverso() {
		this.bloccata.addAttrezzo(new Attrezzo("diverso", 3));
		assertEquals(this.bloccata,this.bloccata.getStanzaAdiacente(Direzione.NORD));
	}
	
	@Test
	public void getStanzaAdiacenteTest_conRimozioneAttrezzoNecessario() {
		this.bloccata.addAttrezzo(this.attrezzo);
		assertEquals(this.vuota,this.bloccata.getStanzaAdiacente(Direzione.NORD));
		this.bloccata.removeAttrezzo(this.attrezzo);
		assertEquals(this.bloccata,this.bloccata.getStanzaAdiacente(Direzione.NORD));
	}
	
	@Test
	public void getStanzaAdiacenteTest_direzioniOccupate() {
		int i=0;
		for(Direzione dir : Direzione.values())
			this.bloccataDirezioniOccupate.impostaStanzaAdiacente(dir, new Stanza("Stanza"+(i++)));
		assertFalse(this.bloccataDirezioniOccupate.hasAttrezzo("attrezzo"));
		assertTrue(this.bloccataDirezioniOccupate.getDirezioni().containsAll(Arrays.asList(Direzione.values())));		
	}
}