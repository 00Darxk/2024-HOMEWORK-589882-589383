package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {
	private Stanza buia;
	private Attrezzo attrezzo;
	
	@Before
	public void setUp() {
		this.buia = new StanzaBuia("buia", "attrezzo");
		this.attrezzo = new Attrezzo("attrezzo", 2);
	}
	
	
	@Test
	public void getDescrizioneTest_senzaAttrezzoNecessario() {
		assertEquals("qui c'è buio pesto...",this.buia.getDescrizione());
	}

	@Test
	public void getDescrizioneTest_conAttrezzoNecessario() {
		this.buia.addAttrezzo(this.attrezzo);
		assertNotEquals("qui c'è buio pesto...",this.buia.getDescrizione());
	}
	
	@Test
	public void getDescrizioneTest_RimozioneAttrezzoNecessario() {
		this.buia.addAttrezzo(this.attrezzo);
		assertNotEquals("qui c'è buio pesto...",this.buia.getDescrizione());
		this.buia.removeAttrezzo(this.attrezzo);
		assertEquals("qui c'è buio pesto...",this.buia.getDescrizione());
	}
	
	
}
