package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {
	Borsa vuota;
	Borsa piena;
	Attrezzo leggero;
	Attrezzo pesante;
	
	@Before
	public void setUp() {
		this.vuota = new Borsa();
		this.leggero = new Attrezzo("leggero", 1);
		this.pesante= new Attrezzo("pesante", 20);
		this.piena = new Borsa();
		for(int i=0; i<10; i++)
			this.piena.addAttrezzo(this.leggero);
	}
	
	
	@Test
	public void testAddAttrezzo_borsaVuota_attrezzoNull() {
		assertFalse(this.vuota.addAttrezzo(null));
	}
	@Test
	public void testAddAttrezzo_borsaVuota_attrezzoLeggero() {
		assertTrue(this.vuota.addAttrezzo(this.leggero));
	}
	@Test
	public void testAddAttrezzo_borsaVuota_attrezzoPesante() {
		assertFalse(this.vuota.addAttrezzo(this.pesante));
	}
	@Test
	public void testAddAttrezzo_borsaPienaInNumero() {
		assertFalse(this.piena.addAttrezzo(this.leggero));
	}
	@Test
	public void testAddAttrezzo_borsaPienaInPeso() {
		Attrezzo ingombrante = new Attrezzo("ingombrante", 10);
		this.vuota.addAttrezzo(ingombrante);
		assertFalse(this.vuota.addAttrezzo(ingombrante));
	}
	
	
	@Test
	public void testRemoveAttrezzo_nomeNull() {
		assertNull(this.piena.removeAttrezzo(null));
	}
	@Test
	public void testRemoveAttrezzo_attrezzoNonPresente() {
		assertNull(this.vuota.removeAttrezzo("inesistente"));
	}
	@Test
	public void testRemoveAttrezzo_attrezzoPresente() {
		this.vuota.addAttrezzo(this.leggero);
		assertEquals(this.leggero,this.vuota.removeAttrezzo("leggero"));
	}
}
