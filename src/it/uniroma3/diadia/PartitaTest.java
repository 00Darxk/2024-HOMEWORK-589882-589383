package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Test;

public class PartitaTest {

	

	@Test
	public void testVinta_vinta() {
		Partita game = new Partita();
		game.setStanzaCorrente(game.getLab().getUscita());
		assertTrue(game.vinta());
	}
	
	@Test
	public void testVinta_nonvinta() {
		Partita game = new Partita();
		assertFalse(game.vinta());
	}
	
	
	@Test
	public void testIsFinita_vinta() {
		Partita game = new Partita();
		game.getGiocatore().setCFU(0);
		game.setStanzaCorrente(game.getLab().getUscita());
		assertTrue(game.isFinita());
	}
	@Test
	public void testIsFinita_vinta_cfu() {
		Partita game = new Partita();
		game.getGiocatore().setCFU(0);
		assertTrue(game.isFinita());
	}
	@Test
	public void testIsFinita_persa() {
		Partita game = new Partita();
		assertFalse(game.isFinita());
	}
}
