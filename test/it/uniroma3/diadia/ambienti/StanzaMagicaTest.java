package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaTest {
	Stanza subitoMagica;
	Stanza quasiMagica;
	Attrezzo attrezzo;
	
	@Before
	public void setUp() {
		this.subitoMagica = new StanzaMagica("subitoMagica", 0);
		this.quasiMagica = new StanzaMagica("magica", 1);
		this.attrezzo = new Attrezzo("attrezzo", 2);
	}
	
	
	@Test
	public void addAttrezzoTest_stanzaSubitoMagica() {
		this.subitoMagica.addAttrezzo(this.attrezzo);
		assertFalse(this.subitoMagica.hasAttrezzo("attrezzo"));
		assertTrue(this.subitoMagica.hasAttrezzo("ozzertta"));
		assertEquals(4, this.subitoMagica.getAttrezzo("ozzertta").getPeso());
	}	
	

	@Test
	public void addAttrezzoTest_stanzaMagica_comportamentoNonMagico() {
		this.quasiMagica.addAttrezzo(this.attrezzo);
		assertTrue(quasiMagica.hasAttrezzo("attrezzo"));
	}
	
	@Test
	public void addAttrezzoTest_stanzaMagica_comportamentoMagico() {
		this.quasiMagica.addAttrezzo(this.attrezzo);
		assertTrue(quasiMagica.hasAttrezzo("attrezzo"));
		this.quasiMagica.removeAttrezzo(this.attrezzo);
		this.quasiMagica.addAttrezzo(this.attrezzo);
		assertFalse(this.subitoMagica.hasAttrezzo("attrezzo"));
	}	
}
