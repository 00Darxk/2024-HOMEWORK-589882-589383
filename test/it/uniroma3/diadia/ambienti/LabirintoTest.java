package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LabirintoTest {
	Labirinto labirinto;
	
	@Before
	public void setUp() {
		this.labirinto = new Labirinto();		
	}

	@Test
	public void testCreaStanze_uscita() {
		assertEquals(this.labirinto.getStanzaIniziale().getStanzaAdiacente("nord"),this.labirinto.getStanzaIniziale().getStanzaAdiacente("sud").getStanzaAdiacente("est").getStanzaAdiacente("est").getStanzaAdiacente("est").getStanzaAdiacente("nord"));
	}
	@Test
	public void testCreaStanze_ingresso() {
		assertEquals(this.labirinto.getStanzaIniziale(), this.labirinto.getStanzaVincente().getStanzaAdiacente("sud"));
	}
	@Test
	public void testCreaStanze() {
		assertEquals(this.labirinto.getStanzaIniziale().getStanzaAdiacente("est").getStanzaAdiacente("est"), this.labirinto.getStanzaIniziale().getStanzaAdiacente("ovest"));
	}

}
