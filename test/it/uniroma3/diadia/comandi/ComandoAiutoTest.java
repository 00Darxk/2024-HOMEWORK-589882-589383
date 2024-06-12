package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;

public class ComandoAiutoTest {
	private Partita partita;
	private Comando comandoAiuto;
	
	@Before
	public void setUp() {
		this.partita = new Partita(Labirinto.newBuilder().addStanza("N1")
				.getLabirinto());
		this.comandoAiuto = new ComandoAiuto();
		this.comandoAiuto.setIO(new IOSimulator());
	}
	
	@Test
	public void eseguiTest() {
		this.comandoAiuto.esegui(this.partita);
		assertTrue(((IOSimulator)this.comandoAiuto.getIO()).getStampeEseguite().get(0).contains("fine"));
		assertTrue(((IOSimulator)this.comandoAiuto.getIO()).getStampeEseguite().get(0).contains("prendi"));
		assertTrue(((IOSimulator)this.comandoAiuto.getIO()).getStampeEseguite().get(0).contains("posa"));
		assertTrue(((IOSimulator)this.comandoAiuto.getIO()).getStampeEseguite().get(0).contains("vai"));
		assertTrue(((IOSimulator)this.comandoAiuto.getIO()).getStampeEseguite().get(0).contains("guarda"));
		assertTrue(((IOSimulator)this.comandoAiuto.getIO()).getStampeEseguite().get(0).contains("interagisci"));
		assertTrue(((IOSimulator)this.comandoAiuto.getIO()).getStampeEseguite().get(0).contains("saluta"));
		assertTrue(((IOSimulator)this.comandoAiuto.getIO()).getStampeEseguite().get(0).contains("regala"));
		assertTrue(((IOSimulator)this.comandoAiuto.getIO()).getStampeEseguite().get(0).contains("aiuto"));
	}
}
