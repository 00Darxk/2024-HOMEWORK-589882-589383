package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GiocatoreTest {
	Giocatore player;
	
	@Before
	public void setUp() {
		this.player = new Giocatore();
	}
	
	
	@Test
	public void testGetCFU() {
		assertEquals(20, this.player.getCFU());
	}
	
	@Test
	public void testSetCFU() {
		this.player.setCFU(4);
		assertEquals(4, this.player.getCFU());
	}
	
	@Test
	public void testSetBag() {
		Borsa bag = new Borsa();
		this.player.setBag(bag);
		assertEquals(bag, this.player.getBag());	
	}
}