package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {

	@Test
	public void testAddAttrezzo_vuotanull() {
		Borsa vuota = new Borsa();
		assertFalse(vuota.addAttrezzo(null));
		
	}
	
	@Test
	public void testAddAttrezzo_vuota() {
		Borsa vuota = new Borsa();
		Attrezzo tool= new Attrezzo("tool", 1);
		assertTrue(vuota.addAttrezzo(tool));
		
	}
	@Test
	public void testAddAttrezzo_vuotapesante() {
		Borsa vuota = new Borsa();
		Attrezzo tool= new Attrezzo("tool", 20);
		assertFalse(vuota.addAttrezzo(tool));
		
	}
	
	@Test
	public void testAddAttrezzo_piena() {
		Borsa piena = new Borsa();
		Attrezzo tool= new Attrezzo("tool", 0);
		for(int i=0; i<10; i++)
			piena.addAttrezzo(tool);
		assertFalse(piena.addAttrezzo(tool));
		
	}
	
	
	@Test
	public void testRemoveAttrezzo_vuota() {
		Borsa vuota= new Borsa();
		assertNull(vuota.removeAttrezzo("inesistente"));
	}
	
	@Test
	public void testRemoveAttrezzo_1() {
		Borsa vuota= new Borsa();
		Attrezzo tool= new Attrezzo("tool", 3);
		vuota.addAttrezzo(tool);
		assertEquals(tool,vuota.removeAttrezzo("tool"));
	}
	
	@Test
	public void testRemoveAttrezzo_sbagliato() {
		Borsa vuota= new Borsa();
		Attrezzo tool=new Attrezzo("tool", 3);
		assertNull(vuota.removeAttrezzo("tool"));
	}
	
	
	
	

}
