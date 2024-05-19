package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {
	Stanza vuota;
	Stanza piena;
	Stanza room;
	Attrezzo tool;
	
	@Before
	public void seUp() {
		this.vuota = new Stanza("vuota");
		this.tool = new Attrezzo("tool2", 1);
		this.piena = new Stanza("piena");
		for(int i=0; i<10 ; i++)
			this.piena.addAttrezzo(new Attrezzo("tool"+ i, 1));
		this.room = new Stanza("room");
	}

	
	@Test
	public void testAddAttrezzo_stanzaVuota() {
		assertTrue(this.vuota.addAttrezzo(this.tool));
	}
	@Test
	public void testAddAttrezzo_stanzaPiena() {
		assertFalse(this.piena.addAttrezzo(this.tool));
	}
	@Test
	public void testAddAttrezzo_attrezzoNull() {
		assertFalse(this.vuota.addAttrezzo(null));
	}
	
	

	@Test 
	public void testGetAttrezzo_nomeNull() {
		assertNull(this.vuota.getAttrezzo(null));
	}
	@Test
	public void testGetAttrezzo_attrezzoPresente() {
		assertEquals(this.tool,this.piena.getAttrezzo("tool2"));
	}
	@Test
	public void testGetAttrezzo_attrezzoNonPresente() {		
		assertNull(this.vuota.getAttrezzo("tool"));
	}
	
	
	
	@Test
	public void testRemoveAttrezzo_attrezzoNull() {
		assertFalse(this.vuota.removeAttrezzo(null));
	}	
	@Test
	public void testRemoveAttrezzo_attrezzoPresente() {
		this.vuota.addAttrezzo(this.tool);
		assertTrue(this.vuota.removeAttrezzo(this.tool));
	}
	@Test
	public void testRemoveAttrezzo_attrezzoNonPresente() {
		assertFalse(this.vuota.removeAttrezzo(this.tool));	
	}
	
	
	
	@Test
	public void testGetStanzaAdiacente_nomeNull() {
		assertNull(this.vuota.getStanzaAdiacente(null));
	}
	@Test
	public void testGetStanzaAdiacente_stanzaNonPresente() {
		assertNull(this.vuota.getStanzaAdiacente("inesistente"));
	}
	
	
	@Test
	public void testImpostaStanzaAdiacente() {
		assertNull(this.room.getStanzaAdiacente("Nord"));
		this.room.impostaStanzaAdiacente("Nord", this.piena);
		assertEquals(this.piena, this.room.getStanzaAdiacente("Nord"));
	}
	@Test
	public void testImpostaStanzaAdiacente_sostituisciStanza() {
		this.room.impostaStanzaAdiacente("Nord", this.piena);
		assertEquals(this.piena, this.room.getStanzaAdiacente("Nord"));
		this.room.impostaStanzaAdiacente("Nord", this.vuota);
		assertEquals(this.vuota, this.room.getStanzaAdiacente("Nord"));
	}
	@Test 
	public void testImpostaStanzaAdiacente_direzioniEsaurite(){
		for(int i = 0; i < 4; i++) 
			this.room.impostaStanzaAdiacente("dir"+i, this.piena);
		this.room.impostaStanzaAdiacente("Nord", this.vuota);
		assertFalse(this.room.getDirezioni().contains("Nord"));
	}
}