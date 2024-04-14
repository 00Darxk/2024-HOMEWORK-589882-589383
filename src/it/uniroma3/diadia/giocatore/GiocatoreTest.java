package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Test;

public class GiocatoreTest {

	@Test
	public void testGetCFU() {
		Giocatore player = new Giocatore(2);
		assertEquals(2, player.getCFU());
	}
	
	
	@Test
	public void testSetCFU() {
		Giocatore player = new Giocatore(2);
		player.setCFU(4);
		assertEquals(4, player.getCFU());
	}
	
	@Test
	public void testSetBag() {
		Giocatore player = new Giocatore();
		Borsa bag = new Borsa();
		player.setBag(bag);
		assertEquals(bag, player.getBag());
		
	}
	
	
	
	

}
