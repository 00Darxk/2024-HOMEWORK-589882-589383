package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSalutaTest {
	final static private String NOME = "Personaggio";
	final static private String PRESENTAZIONE = "Salve, Sono un personaggio di prova!";
	private static final String MESSAGGIO_CON_CHI ="Con chi dovrei interagire?...";
	
	private Comando comandoSaluta;
	private Partita partita;
	
	@Before
	public void setUp() {
		this.comandoSaluta = new ComandoSaluta();
		this.comandoSaluta.setIO(new IOSimulator());
	}
	
	@Test
	public void eseguiTest_personaggioPresente() {
		this.partita = new Partita(Labirinto.newBuilder()
				.addStanzaIniziale("stanza")
				.addPersonaggio(new AbstractPersonaggio(NOME, PRESENTAZIONE){
					@Override
					public String agisci(Partita partita) { return "fatto"; }
					@Override
					public String riceviRegalo(Attrezzo attrezzo, Partita partita) { return "fatto"; }
				})
				.getLabirinto()
				);
		this.comandoSaluta.esegui(this.partita);
		assertEquals("Ciao, io sono "+NOME+"."+PRESENTAZIONE,((IOSimulator)this.comandoSaluta.getIO()).getStampeEseguite().get(0));
	}
	
	@Test
	public void eseguiTest_personaggioNonPresente() {
		this.partita = new Partita(Labirinto.newBuilder()
				.addStanzaIniziale("stanza")
				.getLabirinto()
				);
		this.comandoSaluta.esegui(this.partita);
		assertEquals(MESSAGGIO_CON_CHI,((IOSimulator)this.comandoSaluta.getIO()).getStampeEseguite().get(0));
	}
}
