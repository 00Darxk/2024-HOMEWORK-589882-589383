package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {



	
	@Test
	public void testAddAttrezzo_vuoto() {
		Stanza vuota = new Stanza("vuota");
		Attrezzo tool= new Attrezzo("tool", 0);
		assertTrue(vuota.addAttrezzo(tool) );
	}

	@Test
	public void testAddAttrezzo_piena() {
		Stanza piena = new Stanza("piena");
		Attrezzo tool= new Attrezzo("tool", 1);
		for(int i=0; i<10 ; i++)
			piena.addAttrezzo(tool);
		assertFalse(piena.addAttrezzo(tool));
	}
	
	@Test
	public void testAddAttrezzo_nullo() {
		Stanza room = new Stanza("room");
		assertFalse(room.addAttrezzo(null));
	}
	
	
	@Test
	public void testGetAttrezzo() {
		Stanza vuota = new Stanza("vuota");
		assertNull(vuota.getAttrezzo("inesistente"));
	}
	
	@Test
	public void testGetAttrezzocista() {
		Stanza con = new Stanza("con");
		Attrezzo tool= new Attrezzo("tool", 0);
		con.addAttrezzo(tool);
		assertEquals(tool,con.getAttrezzo("tool"));
	}
	
	@Test
	public void testGetAttrezzononcista() {
		Stanza con = new Stanza("con");
		
		assertNull(con.getAttrezzo("tool"));
	}
	
	
	@Test
	public void testRemoveAttrezzo() {
		Stanza vuota = new Stanza("vuota");
		assertFalse(vuota.removeAttrezzo(null));
		
	}
	
	@Test
	public void testRemoveAttrezzo1() {
		Stanza room = new Stanza("room");
		Attrezzo tool = new Attrezzo("tool", 1);
		room.addAttrezzo(tool);
		assertTrue(room.removeAttrezzo(tool));
		
	}
	
	
	@Test
	public void testRemoveAttrezzo_noncista() {
		Stanza room = new Stanza("room");
		Attrezzo tool = new Attrezzo("tool", 1);
		assertFalse(room.removeAttrezzo(tool));
		
	}

	@Test
	public void testGetStanzaAdiacente_null() {
		Stanza room= new Stanza("room");
		assertNull(room.getStanzaAdiacente("inesistente"));
	}
	
	@Test
	public void testGetStanzaAdiacente_ultima() {
		Stanza room= new Stanza ("room");
		for(int i = 0 ; i<4 ; i++) {
			room.impostaStanzaAdiacente("dir"+i, new Stanza("room"+i));
		}
		assertEquals("room3", room.getStanzaAdiacente("dir3").getNome() );
	}
	
	
	
	

}
