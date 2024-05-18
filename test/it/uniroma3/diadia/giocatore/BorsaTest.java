package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {
	private Borsa borsaVuota;
	private Borsa borsaPiena;
	private Borsa borsaPesante;
	private Attrezzo piuma;
	private Attrezzo piombo;
	private Attrezzo libro;
	private Attrezzo piumaDorata;
	
	@Before
	public void setUp() {
		this.borsaVuota = new Borsa();
		this.borsaPiena = new Borsa();
		this.borsaPesante = new Borsa(31);

		this.piuma = new Attrezzo("piuma", 1);
		this.piombo = new Attrezzo("piombo", 20);
		this.libro = new Attrezzo("libro", 5);
		this.piumaDorata = new Attrezzo("piuma",5);
	
		for(int i=0; i<10; i++)
			this.borsaPiena.addAttrezzo(this.piuma);
		
		this.borsaPesante.addAttrezzo(piombo);
		this.borsaPesante.addAttrezzo(piuma);
		this.borsaPesante.addAttrezzo(libro);
		this.borsaPesante.addAttrezzo(piumaDorata);
	}
	
	
	
	@Test
	public void testAddAttrezzo_borsaVuota_attrezzoNull() {
		assertFalse(this.borsaVuota.addAttrezzo(null));
	}
	@Test
	public void testAddAttrezzo_borsaVuota_attrezzoLeggero() {
		assertTrue(this.borsaVuota.addAttrezzo(this.piuma));
	}
	@Test
	public void testAddAttrezzo_borsaVuota_attrezzoPesante() {
		assertFalse(this.borsaVuota.addAttrezzo(this.piombo));
	}
	@Test
	public void testAddAttrezzo_borsaPienaInNumero() {
		assertFalse(this.borsaPiena.addAttrezzo(this.piuma));
	}
	@Test
	public void testAddAttrezzo_borsaPienaInPeso() {
		this.borsaVuota.addAttrezzo(new Attrezzo("ingombrante", 10));
		assertFalse(this.borsaVuota.addAttrezzo(piuma));
	}
	
	
	@Test
	public void testRemoveAttrezzo_nomeNull() {
		assertNull(this.borsaPiena.removeAttrezzo(null));
	}
	@Test
	public void testRemoveAttrezzo_attrezzoNonPresente() {
		assertNull(this.borsaVuota.removeAttrezzo("inesistente"));
	}
	@Test
	public void testRemoveAttrezzo_attrezzoPresente() {
		this.borsaVuota.addAttrezzo(this.libro);
		assertEquals(this.libro,this.borsaVuota.removeAttrezzo("libro"));
	}
	@Test
	public void testRemoveAttrezzo_attrezziOmonimi() {
		this.borsaVuota.addAttrezzo(this.piuma);
		this.borsaVuota.addAttrezzo(this.piumaDorata);
		assertEquals(this.piuma,this.borsaVuota.removeAttrezzo("piuma"));
		assertEquals(this.piumaDorata,this.borsaVuota.removeAttrezzo("piuma"));
	}
	
	
	@Test
	public void testContenutoOrdinato_BorsaVuota() {
		assertEquals(0,this.borsaVuota.getContenutoOrdinatoPerNome().size());
		assertEquals(0,this.borsaVuota.getContenutoOrdinatoPerPeso().size());
	}
	@Test 
	public void testContenutoOrdinatoPerPeso() {
		List<Attrezzo> attrezziOrdinatiPerPeso = this.borsaPesante.getContenutoOrdinatoPerPeso();
		assertEquals(Arrays.asList(piuma, libro, piumaDorata, piombo), attrezziOrdinatiPerPeso);
	}
	@Test 
	public void testContenutoOrdinatoPerNome() {
		List<Attrezzo> attrezziOrdinatiPerNome = new ArrayList<>(this.borsaPesante.getContenutoOrdinatoPerNome());
		assertEquals(Arrays.asList(libro, piombo, piuma, piumaDorata), attrezziOrdinatiPerNome);
	}
	
	@Test
	public void testContenutoRaggruppatoPerPeso_borsaVuota() {
		assertEquals(0,this.borsaVuota.getContenutoRaggruppatoPerPeso().size());
	}
	@Test
	public void testContenutoRaggruppatoPerPeso_borsaAttrezziUguali() {
		this.borsaVuota.addAttrezzo(this.libro);
		this.borsaVuota.addAttrezzo(this.libro);
		assertEquals(1,this.borsaVuota.getContenutoRaggruppatoPerPeso().get(this.libro.getPeso()).size());
	}
	public void testContenutoRaggruppatoPerPeso_borsaAttrezziDiversi() {
		this.borsaVuota.addAttrezzo(this.libro);
		this.borsaVuota.addAttrezzo(this.piumaDorata);
		Set<Attrezzo> attrezzi = new HashSet<>();
		Collections.addAll(attrezzi, this.libro, this.piumaDorata);
		assertEquals(attrezzi,this.borsaVuota.getContenutoRaggruppatoPerPeso().get(this.libro.getPeso()).size());
	}
}
