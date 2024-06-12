package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegalaTest {
	private Partita partita;
	private Comando comandoRegala;
	private class FakePersonaggio extends AbstractPersonaggio{
		public FakePersonaggio(String nome, String presentaz) { super(nome, presentaz); }
		private Attrezzo attrezzo;
		@Override public String agisci(Partita partita) { return "fatto"; }
		@Override public String riceviRegalo(Attrezzo attrezzo, Partita partita) { 
				this.attrezzo = attrezzo;
				return "fatto"; 
			}
		public boolean hasAttrezzo(Attrezzo attrezzo) { return this.attrezzo != null && this.attrezzo.equals(attrezzo); }
	}
	
	@Before
	public void setUp() {
		this.partita = new Partita(Labirinto.newBuilder()
				.addStanzaIniziale("stanza")
				.addPersonaggio(new FakePersonaggio("nome","presentazione"))
				.getLabirinto()
				);
		this.comandoRegala = new ComandoRegala();
		this.comandoRegala.setIO(new IOSimulator());
	}
	
	@Test
	public void eseguiTest_attrezzoInBorsa() {
		this.partita.getGiocatore().getBag().addAttrezzo(new Attrezzo("piuma", 1));
		this.comandoRegala.setParametro("piuma");
		this.comandoRegala.esegui(partita);
		assertTrue(this.partita.getGiocatore().getBag().getContenutoOrdinatoPerPeso().isEmpty());
		assertTrue(((FakePersonaggio)this.partita.getStanzaCorrente().getPersonaggio()).hasAttrezzo(new Attrezzo("piuma", 1)));
	}
	
	@Test
	public void eseguiTest_attrezzoNonInBorsa() {
		this.comandoRegala.setParametro("piuma");
		this.comandoRegala.esegui(partita);
		assertFalse(((FakePersonaggio)this.partita.getStanzaCorrente().getPersonaggio()).hasAttrezzo(new Attrezzo("piuma", 1)));
	}
	
	@Test
	public void eseguiTest_sovrascriviAttrezzoPersonaggio() {
		this.partita.getGiocatore().getBag().addAttrezzo(new Attrezzo("piuma", 1));
		this.comandoRegala.setParametro("piuma");
		this.comandoRegala.esegui(partita);
		this.partita.getGiocatore().getBag().addAttrezzo(new Attrezzo("bastone", 3));
		this.comandoRegala.setParametro("bastone");
		this.comandoRegala.esegui(partita);
		assertFalse(((FakePersonaggio)this.partita.getStanzaCorrente().getPersonaggio()).hasAttrezzo(new Attrezzo("piuma", 1)));
		assertTrue(((FakePersonaggio)this.partita.getStanzaCorrente().getPersonaggio()).hasAttrezzo(new Attrezzo("bastone", 1)));
	}
}
