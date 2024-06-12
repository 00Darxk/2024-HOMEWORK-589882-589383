package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoInteragisciTest {
	final static private String INTERAZIONE = "interazione eseguita";
	private static final String MESSAGGIO_CON_CHI =
			 "Con chi dovrei interagire?...";
	
	private Partita partita;
	private Comando comandoInteragisci;
	
	@Before
	public void setUp() {
		this.comandoInteragisci = new ComandoInteragisci();
		this.comandoInteragisci.setIO(new IOSimulator());
	}
	
	@Test
	public void eseguiTest_personaggioPresente() {
		this.partita = new Partita(Labirinto.newBuilder()
				.addStanzaIniziale("stanza")
				.addPersonaggio(new AbstractPersonaggio("nome", "presentazione") {
					@Override
					public String agisci(Partita partita) { return INTERAZIONE; }
					@Override
					public String riceviRegalo(Attrezzo attrezzo, Partita partita) { return "fatto"; }
				})
				.getLabirinto()
				);
		this.comandoInteragisci.esegui(this.partita);
		assertEquals(INTERAZIONE, ((IOSimulator)this.comandoInteragisci.getIO()).getStampeEseguite().get(0));
	}
	
	@Test
	public void eseguiTest_personaggioNonPresente() {
		this.partita = new Partita(Labirinto.newBuilder()
				.addStanzaIniziale("stanza1")
				.getLabirinto()
				);
		this.comandoInteragisci.esegui(this.partita);
		assertEquals(MESSAGGIO_CON_CHI, ((IOSimulator)this.comandoInteragisci.getIO()).getStampeEseguite().get(0));
	
	}
}
