package it.uniroma3.diadia.personaggi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Proprietà;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class MagoTest {
	private static final String MESSAGGIO_DONO = Proprietà.getMessaggioDono();
	private static final String MESSAGGIO_SCUSE = Proprietà.getMessaggioScuse();
	
	private AbstractPersonaggio mago;
	private Attrezzo bastone;
	private Partita partita;
	
	@Before
	public void setUp() {
		this.bastone = new Attrezzo("bastone", 2);
		this.mago = new Mago("Merlino", "Buonsalve");
		this.partita = new Partita(Labirinto.newBuilder()
				.addStanzaIniziale("stanza")
				.addPersonaggio(this.mago)
				.getLabirinto());
	}
	
	@Test
	public void agisciRegaloTest_magoSenzaAttrezzo() {
		assertEquals(MESSAGGIO_SCUSE, this.mago.agisci(this.partita));
	}
	
	@Test
	public void riceviRegaloTest_attrezzoNull_magoSenzaAttrezzo() {
		assertEquals(MESSAGGIO_SCUSE, this.mago.riceviRegalo(null, this.partita));
	}
	
	@Test
	public void riceviRegaloTest_regalaAttrezzo_magoSenzaAttrezzo() {
		assertEquals(MESSAGGIO_DONO, this.mago.riceviRegalo(this.bastone, this.partita));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("bastone"));
		assertEquals(this.bastone.getPeso() / 2, this.partita.getStanzaCorrente().getAttrezzo("bastone").getPeso());
	}
	
	@Test
	public void riceviRegaloTest_regalaAttrezzo_magoSenzaAttrezzo_stanzaPiena() {
		this.riempiStanza(this.partita.getStanzaCorrente());
		assertEquals(MESSAGGIO_SCUSE, this.mago.riceviRegalo(this.bastone, this.partita));
	}
	
	@Test
	public void riceviRegaloTest_regalaAttrezzo_magoConAttrezzo_stanzaVuota() {
		assertTrue(this.partita.getGiocatore().getBag().getContenutoOrdinatoPerPeso().isEmpty());
		this.riempiStanza(this.partita.getStanzaCorrente());
		this.mago.riceviRegalo(this.bastone, this.partita);
		this.svuotaStanza(this.partita.getStanzaCorrente());
		this.mago.riceviRegalo(new Attrezzo("osso", 3), this.partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("bastone"));
		assertTrue(this.partita.getGiocatore().getBag().hasAttrezzo("osso"));
		
	}
	
	private void riempiStanza(Stanza stanza) {
		for(int i = 0; i<Proprietà.getNumeroMassimoAttrezzi(); i++)
			stanza.addAttrezzo(new Attrezzo("temp"+i,i));
	}
	
	private void svuotaStanza(Stanza stanza) {
		for(int i = 0; i<Proprietà.getNumeroMassimoAttrezzi(); i++)
			stanza.removeAttrezzo(new Attrezzo("temp"+i,i));
	}
}
