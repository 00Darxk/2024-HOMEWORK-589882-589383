package it.uniroma3.diadia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.giocatore.Giocatore;

public class TestDiAccettazione {
	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	static final private String MESSAGGIO_VITTORIA = "Hai vinto!";
	static final private String MESSAGGIO_SCONFITTA = "Hai esaurito i CFU...";
	static final private String MESSAGGIO_FINE = "\nGrazie di aver giocato!";

	private DiaDia diadia;
	private IOSimulator io;


	@Before
	public void setUp() {
		this.io = new IOSimulator();
	}
	
	@Test
	public void diadiaTest_comandoFine() {
		this.diadia = new DiaDia (
				new Partita(
						new LabirintoBuilder()
						.addStanzaIniziale("aula 1")
						.getLabirinto()), 
				this.io.aggiungiComandoDaEseguire("fine"));
		this.diadia.gioca();
		assertEquals(MESSAGGIO_BENVENUTO, this.io.getStampeEseguite().get(0));
		assertEquals(MESSAGGIO_FINE, this.io.getStampeEseguite().get(this.io.getStampeEseguite().size()-1));
	}

	@Test
	public void diadiaTest_comandoVai() {
		this.diadia = new DiaDia (
				new Partita(
						new LabirintoBuilder()
						.addStanzaIniziale("aula 1")
						.getLabirinto()), 
				this.io.aggiungiComandoDaEseguire("vai nord")
				.aggiungiComandoDaEseguire("fine"));
		this.diadia.gioca();
		assertTrue(this.io.getStampeEseguite().contains("Direzione inesistente"));
	}
	
	@Test
	public void diadiaTest_comandoGuarda() {
		this.diadia = new DiaDia(
				new Partita(
						new LabirintoBuilder()
						.addStanzaIniziale("aula 1")
						.addAttrezzo("piuma", 1)
						.getLabirinto()),
				this.io.aggiungiComandoDaEseguire("guarda")
				.aggiungiComandoDaEseguire("fine"));
		this.diadia.gioca();
		assertTrue(this.io.getStampeEseguite().contains("Hai 20 CFU rimanenti\n"));
	}
	
	@Test 
	public void diadiaTest_comandoPrendi() {
		this.diadia = new DiaDia(
				new Partita(
						new LabirintoBuilder()
						.addStanzaIniziale("aula 1")
						.addAttrezzo("piuma", 1)
						.getLabirinto()),
				this.io.aggiungiComandoDaEseguire("prendi piuma")
				.aggiungiComandoDaEseguire("fine"));
		this.diadia.gioca();
		assertTrue(this.io.getStampeEseguite().contains("Contenuto borsa: [piuma (1kg)]"));
	}
	
	@Test 
	public void diadiaTest_comandoPosa() {
		this.diadia = new DiaDia(
				new Partita(
						new LabirintoBuilder()
						.addStanzaIniziale("aula 1")
						.addAttrezzo("piuma", 1)
						.getLabirinto()),
				this.io.aggiungiComandoDaEseguire("prendi piuma")
				.aggiungiComandoDaEseguire("posa piuma")
				.aggiungiComandoDaEseguire("fine"));
		this.diadia.gioca();
		assertTrue(this.io.getStampeEseguite().indexOf("Contenuto borsa: [piuma (1kg)]") < this.io.getStampeEseguite().indexOf("Borsa vuota"));
	}

	@Test
	public void diadiaTest_vintaInStanzaVincente() {
		this.diadia = new DiaDia(
				new Partita(
						new LabirintoBuilder()
						.addStanzaIniziale("aula 1")
						.setStanzaVincente("aula 1")
						.getLabirinto()), 
				this.io.aggiungiComandoDaEseguire("comando inutile"));
		this.diadia.gioca();
		assertTrue(this.io.getStampeEseguite().contains(MESSAGGIO_VITTORIA));
	}
	


	@Test
	public void diadiaTest_vintaDopoSpostamento() {
		this.diadia = new DiaDia(
				new Partita(
						new LabirintoBuilder()
						.addStanzaIniziale("aula 1")
						.addStanzaVincente("aula 2")
						.addAdiacenza("aula 1", "aula 2", "nord")
						.getLabirinto()), 
				this.io.aggiungiComandoDaEseguire("vai nord")
				.aggiungiComandoDaEseguire("fine"));
		this.diadia.gioca();
		assertTrue(this.io.getStampeEseguite().contains(MESSAGGIO_VITTORIA));
	}

	@Test 
	public void diadiaTest_sconfittaEsaurimentoCFU() {
		this.diadia = new DiaDia(
				new Partita(
						new LabirintoBuilder()
						.addStanzaIniziale("aula 1")
						.addStanza("aula 2")
						.addAdiacenza("aula 1", "aula 2", "nord")
						.getLabirinto(),
						new Giocatore(1 ,10)), 
				this.io.aggiungiComandoDaEseguire("vai nord")
				.aggiungiComandoDaEseguire("fine"));
		this.diadia.gioca();
		assertTrue(this.io.getStampeEseguite().contains(MESSAGGIO_SCONFITTA));
	}
	
	
	@Test
	public void diadiaTest_vintaDopoSbloccaStanzaBloccata() {
		this.diadia = new DiaDia(
				new Partita(
						new LabirintoBuilder()
						.addStanzaIniziale("aula 1")
						.addAttrezzo("chiave", 1)
						.addStanzaBloccata("aula 2", "nord", "chiave")
						.addStanzaVincente("aula 3")
						.addAdiacenza("aula 1", "aula 2", "nord")
						.addAdiacenza("aula 2", "aula 3", "nord")
						.getLabirinto()), 
				this.io.aggiungiComandoDaEseguire("prendi chiave")
				.aggiungiComandoDaEseguire("vai nord")
				.aggiungiComandoDaEseguire("posa chiave")
				.aggiungiComandoDaEseguire("vai nord")
				.aggiungiComandoDaEseguire("fine"));
		this.diadia.gioca();
		assertTrue(this.io.getStampeEseguite().contains(MESSAGGIO_VITTORIA));
	}
	
	@Test
	public void diadiaTest_vintaDopoSbloccaStanzaBloccataConChiaveInvertita() {
		this.diadia = new DiaDia(
				new Partita(
						new LabirintoBuilder()
						.addStanzaMagica("aula 1", 1)
						.setStanzaIniziale("aula 1")
						.addStanzaBloccata("aula 2", "nord", "evaihc")
						.addAttrezzo("chiave", 1)
						.addStanzaVincente("aula 3")
						.addAdiacenza("aula 1", "aula 2", "nord")
						.addAdiacenza("aula 2", "aula 3", "nord")
						.addAdiacenza("aula 2", "aula 1", "sud")
						.getLabirinto()), 
				this.io.aggiungiComandoDaEseguire("vai nord")
				.aggiungiComandoDaEseguire("prendi chiave")
				.aggiungiComandoDaEseguire("vai sud")
				.aggiungiComandoDaEseguire("posa chiave")
				.aggiungiComandoDaEseguire("prendi chiave")
				.aggiungiComandoDaEseguire("posa chiave")
				.aggiungiComandoDaEseguire("prendi evaihc")
				.aggiungiComandoDaEseguire("vai nord")
				.aggiungiComandoDaEseguire("posa evaihc")
				.aggiungiComandoDaEseguire("vai nord")
				.aggiungiComandoDaEseguire("fine"));
		this.diadia.gioca();
		assertTrue(this.io.getStampeEseguite().contains(MESSAGGIO_VITTORIA));
	}
	
	
}
