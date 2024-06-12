package it.uniroma3.diadia.personaggi;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class AbstractPersonaggioTest {
	final static private String NOME = "Personaggio";
	final static private String PRESENTAZIONE = "Salve, Sono un personaggio di prova!";
	private AbstractPersonaggio personaggio;
	
	@Before
	public void setUp() {
		this.personaggio = new AbstractPersonaggio(NOME, PRESENTAZIONE) {
			@Override
			public String agisci(Partita partita) { return "fatto"; }
			@Override
			public String riceviRegalo(Attrezzo attrezzo, Partita partita) { return "fatto"; }
		};
	}
	
	@Test
	public void salutaTest() {
		assertEquals("Ciao, io sono "+NOME+"."+PRESENTAZIONE,this.personaggio.saluta());
	}
	
	@Test
	public void salutaTest_ripetitivo() {
		this.personaggio.saluta();
		assertEquals("Ciao, io sono "+NOME+"."+"Ci siamo gia' presentati!", this.personaggio.saluta());
	}
}
