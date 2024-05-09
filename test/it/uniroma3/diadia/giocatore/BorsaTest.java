package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {
	Borsa vuota;
	Borsa piena;
	Borsa pesata;
	Attrezzo leggero;
	Attrezzo pesante;
	Attrezzo piombo;
	Attrezzo ps;
	Attrezzo piuma;
	Attrezzo libro;
	
	@Before
	public void setUp() {
		this.vuota = new Borsa();
		this.leggero = new Attrezzo("leggero", 1);
		this.pesante= new Attrezzo("pesante", 20);
		this.pesata = new Borsa(30);
		

		this.piena = new Borsa();
		for(int i=0; i<10; i++)
			this.piena.addAttrezzo(this.leggero);
		
		this.piombo= new Attrezzo("piombo", 10);
		this.ps= new Attrezzo("ps", 5);
		this.piuma= new Attrezzo("piuma", 1);
		this.libro= new Attrezzo("libro", 5);
	
		this.pesata.addAttrezzo(piombo);
		this.pesata.addAttrezzo(ps);
		this.pesata.addAttrezzo(piuma);
		this.pesata.addAttrezzo(libro);
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
	
	@Test 
	public void testContenutoOrdinatoPerPeso() {
		List<Attrezzo> vera = this.pesata.getContenutoOrdinatoPerPeso();
		Iterator<Attrezzo> i = vera.iterator();
		assertEquals(piuma,i.next());
		assertEquals(libro,i.next());
		assertEquals(ps,i.next());
		assertEquals(piombo,i.next());
		
		
		
	}
}
