package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PartitaTest {
	Partita game;
	
	@Before
	public void setUp() {
		this.game = new Partita();
	}
	

	@Test
	public void testVinta_stanzaVincente() {
		this.game.setStanzaCorrente(this.game.getLab().getUscita());
		assertTrue(this.game.vinta());
	}
	@Test
	public void testVinta_stanzaNonVincente() {
		this.game.setStanzaCorrente(this.game.getLab().getIngresso().getStanzaAdiacente("est"));
		assertFalse(this.game.vinta());
	}
	@Test
	public void testVinta_nonVinta() {
		assertFalse(this.game.vinta());
	}
	
	
	@Test
	public void testIsFinita_vinta() {
		this.game.setStanzaCorrente(this.game.getLab().getUscita());
		assertTrue(this.game.isFinita());
	}
	@Test
	public void testIsFinita_CFUFiniti() {
		this.game.getGiocatore().setCFU(0);
		assertTrue(this.game.isFinita());
	}
	@Test
	public void testIsFinita_persa() {
		assertFalse(this.game.isFinita());
	}
}
