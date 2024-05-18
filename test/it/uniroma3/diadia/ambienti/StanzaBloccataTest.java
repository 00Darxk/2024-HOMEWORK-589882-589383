package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {
	private Stanza bloccata;
	private Stanza vuota;
	private Stanza bloccataDirezioniOccupate;
	private Attrezzo attrezzo;
	
	@Before
	public void setUp() {
		this.bloccata = new StanzaBloccata("bloccata", "nord", "attrezzo");
		this.bloccata.impostaStanzaAdiacente("nord", vuota);
		this.attrezzo = new Attrezzo("attrezzo", 2);
		this.bloccataDirezioniOccupate = new StanzaBloccata("bloccata", "inesistente", "attrezzo");
	}
	
	@Test
	public void getStanzaAdiacenteTest_senzaAttrezzoNecessario() {
		assertEquals(this.bloccata,this.bloccata.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void getStanzaAdiacenteTest_conAttrezzoNecessario() {
		this.bloccata.addAttrezzo(this.attrezzo);
		assertEquals(this.vuota,this.bloccata.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void getStanzaAdiacenteTest_conAttrezzoDiverso() {
		this.bloccata.addAttrezzo(new Attrezzo("diverso", 3));
		assertEquals(this.bloccata,this.bloccata.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void getStanzaAdiacenteTest_conRimozioneAttrezzoNecessario() {
		this.bloccata.addAttrezzo(this.attrezzo);
		assertEquals(this.vuota,this.bloccata.getStanzaAdiacente("nord"));
		this.bloccata.removeAttrezzo(this.attrezzo);
		assertEquals(this.bloccata,this.bloccata.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void getStanzaAdiacenteTest_direzioniOccupate() {
		for(int i=0;i<4;i++)
			this.bloccataDirezioniOccupate.impostaStanzaAdiacente("dir"+i, new Stanza("dir"+i));
		assertFalse(this.bloccataDirezioniOccupate.hasAttrezzo("attrezzo"));
		List<String> direzioni = new ArrayList<>();
		direzioni.addAll(this.bloccataDirezioniOccupate.getDirezioni());
		assertEquals(Arrays.asList("dir3", "dir2", "dir1", "dir0"),direzioni);		
	}
}