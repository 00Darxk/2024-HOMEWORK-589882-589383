package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendiTest {
	private Partita partita;
	private Comando comandoPrendi;
	
	@Before
	public void setUp() {
		this.comandoPrendi = new ComandoPrendi();
		this.comandoPrendi.setIO(new IOSimulator());
		this.comandoPrendi.setParametro("tool");
	}
	
	@Test
	public void eseguiTest_attrezzoInesistente() {
		this.partita = new Partita(Labirinto.newBuilder()
				.addStanzaIniziale("camera")
				.getLabirinto());
		this.comandoPrendi.esegui(this.partita);
		assertEquals("Attrezzo Inesistente", ((IOSimulator)this.comandoPrendi.getIO()).getStampeEseguite().get(0));
	}
	
	@Test
	public void eseguiTest_borsaVuota() {
		this.partita= new Partita(Labirinto.newBuilder()
				.addStanzaIniziale("camera")
				.addAttrezzo("tool", 3)
				.getLabirinto());
		assertFalse(this.partita.getGiocatore().getBag().hasAttrezzo("tool"));
		this.comandoPrendi.esegui(this.partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("tool"));
		assertTrue(this.partita.getGiocatore().getBag().hasAttrezzo("tool"));
	}
	
	@Test
	public void eseguiTest_borsaPiena() {
		this.partita= new Partita(Labirinto.newBuilder()
				.addStanzaIniziale("camera")
				.addAttrezzo("tool", 3)
				.getLabirinto());
		this.partita.getGiocatore().getBag().addAttrezzo(new Attrezzo("piombo", 10));
		this.comandoPrendi.esegui(partita);
		assertFalse(this.partita.getGiocatore().getBag().hasAttrezzo("tool"));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("tool"));
	}
}