package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

public class ComandoVaiTest {
	private Comando comandoVai;
	private Partita partita;
	
	@Before
	public void setUp() {
		this.comandoVai = new ComandoVai();
		this.comandoVai.setIO(new IOSimulator());
		this.comandoVai.setParametro("nord");
	}
	
	@Test
	public void eseguiTest_stanzaInesistente() {
		this.partita = new Partita(new LabirintoBuilder()
				.addStanzaIniziale("camera")
				.getLabirinto());
		this.comandoVai.esegui(this.partita);
		assertEquals("Direzione inesistente", ((IOSimulator)this.comandoVai.getIO()).getStampeEseguite().get(0));
	}
	
	@Test
	public void eseguiTest_stanzaPresente() {
		this.partita = new Partita(new LabirintoBuilder()
				.addStanzaIniziale("camera")
				.addStanzaVincente("salotto")
				.addAdiacenza("camera", "salotto", "nord")
				.getLabirinto());
		assertEquals(this.partita.getLab().getStanzaIniziale(),this.partita.getStanzaCorrente());
		this.comandoVai.esegui(this.partita);
		assertEquals(this.partita.getLab().getStanzaVincente(),this.partita.getStanzaCorrente());
	}
}