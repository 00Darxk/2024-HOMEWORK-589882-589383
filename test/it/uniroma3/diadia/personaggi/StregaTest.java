package it.uniroma3.diadia.personaggi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Proprietà;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StregaTest {
	private static final String MESSAGGIO_RISATA = Proprietà.getMessaggioRisata();
	private static final String MESSAGGIO_INGANNO = Proprietà.getMessaggioInganno();

	private AbstractPersonaggio strega;
	private Attrezzo bastone;
	private Partita partita;

	@Before
	public void setUp() {
		this.bastone = new Attrezzo("bastone", 2);
		this.strega = new Strega("Medusa", "Buonsalve");
		this.partita = new Partita(Labirinto.newBuilder()
				.addStanzaIniziale("stanza")
				.addPersonaggio(this.strega)
				.getLabirinto());
	}
	
	@Test
	public void riceviRegaloTest_attrezzoNull() {
		assertEquals(MESSAGGIO_INGANNO,this.strega.riceviRegalo(null, this.partita));
	}
	
	@Test
	public void riceviRegaloTest_attrezzoNotNull() {
		assertEquals(MESSAGGIO_RISATA,this.strega.riceviRegalo(this.bastone, this.partita));
	}
	
	@Test
	public void getAttrezziRubatiTest() {
		this.strega.riceviRegalo(this.bastone, this.partita);
		assertTrue(((Strega)this.strega).getAttrezziRubati().contains(this.bastone));
	}
	
}
