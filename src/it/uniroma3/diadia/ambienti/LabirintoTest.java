package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Test;

public class LabirintoTest {

	@Test
	public void testSetIngresso() {
		Labirinto labirinto = new Labirinto();
		Stanza nuova= new Stanza("nuova");
		labirinto.setIngresso(nuova);
		assertEquals(nuova,labirinto.getIngresso());
	
	}
	

	@Test
	public void testSetUscita() {
		Labirinto labirinto = new Labirinto();
		Stanza nuova= new Stanza("nuova");
		labirinto.setUscita(nuova);
		assertEquals(nuova,labirinto.getUscita());
	
	}
	

	@Test
	public void testCreaStanze() {
		Labirinto labirinto = new Labirinto();
		
		assertEquals(labirinto.getUscita(),labirinto.getIngresso().getStanzaAdiacente("sud").getStanzaAdiacente("est").getStanzaAdiacente("est").getStanzaAdiacente("est").getStanzaAdiacente("nord"));
	
	}
	
	

}
