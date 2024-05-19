package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosaTest {
	private Partita partita;
	private Comando comandoPosa;
	
	@Before
	public void setUp() {
		this.comandoPosa = new ComandoPosa();
		this.comandoPosa.setIO(new IOSimulator());
		this.comandoPosa.setParametro("tool");
	}
	
	@Test
	public void eseguiTest_attrezzoInesistente() {
		this.partita = new Partita(new LabirintoBuilder()
				.addStanzaIniziale("camera")
				.getLabirinto());
		assertFalse(partita.getGiocatore().getBag().hasAttrezzo("tool"));
		this.comandoPosa.esegui(this.partita);
		assertEquals("Attrezzo inesistente", ((IOSimulator)this.comandoPosa.getIO()).getStampeEseguite().get(0));
	}
	
	@Test
	public void eseguiTest_stanzaVuota() {
		this.partita = new Partita(new LabirintoBuilder()
				.addStanzaIniziale("camera")
				.getLabirinto());
		this.partita.getGiocatore().getBag().addAttrezzo(new Attrezzo("tool",3));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("tool"));
		this.comandoPosa.esegui(this.partita);
		assertFalse(this.partita.getGiocatore().getBag().hasAttrezzo("tool"));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("tool"));
	}
	
	@Test
	public void eseguiTest_stanzaPiena() {
		this.partita = new Partita(new LabirintoBuilder()
				.addStanzaIniziale("camera")
				.addAttrezzo("piuma1", 1)
				.addAttrezzo("piuma2", 2)
				.addAttrezzo("piuma3", 3)
				.addAttrezzo("piuma4", 4)
				.addAttrezzo("piuma5", 5)
				.addAttrezzo("piuma6", 6)
				.addAttrezzo("piuma7", 7)
				.addAttrezzo("piuma8", 8)
				.addAttrezzo("piuma9", 9)
				.addAttrezzo("piuma10", 10)
				.getLabirinto());
		this.partita.getGiocatore().getBag().addAttrezzo(new Attrezzo("tool",3));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("tool"));
		this.comandoPosa.esegui(this.partita);
		assertTrue(this.partita.getGiocatore().getBag().hasAttrezzo("tool"));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("tool"));
	}
}
