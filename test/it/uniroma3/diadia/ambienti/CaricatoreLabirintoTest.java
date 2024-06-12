package it.uniroma3.diadia.ambienti;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;

public class CaricatoreLabirintoTest {
	private CaricatoreLabirinto caricatoreLabirinto;
	private Labirinto labirinto;
			
	
	@Test
	public void caricaTest_monolocale() throws FormatoFileNonValidoException {
		this.caricatoreLabirinto = new CaricatoreLabirinto(new StringReader(
				"Stanze: Atrio\n"
				+ "Stanze Buie:\n"
				+ "Stanze Magiche:\n"
				+ "Stanze Bloccate:\n"
				+ "Inizio: Atrio\n"
				+ "Vincente: Atrio\n"
				+ "Attrezzi:\n"
				+ "Personaggi:\n"
				+ "Uscite:"));
		this.caricatoreLabirinto.carica();
		this.labirinto =  this.caricatoreLabirinto.getLabirinto();
		assertTrue(this.labirinto.getListaStanze().containsKey("Atrio"));
		assertEquals("Atrio", this.labirinto.getStanzaIniziale().getNome());
		assertEquals("Atrio", this.labirinto.getStanzaVincente().getNome());
	}	
	
	@Test
	public void caricaTest_bilocale() throws FormatoFileNonValidoException {
		this.caricatoreLabirinto = new CaricatoreLabirinto(new StringReader(
				"Stanze: Atrio, Biblioteca\n"
				+ "Stanze Buie:\n"
				+ "Stanze Magiche:\n"
				+ "Stanze Bloccate:\n"
				+ "Inizio: Atrio\n"
				+ "Vincente: Biblioteca\n"
				+ "Attrezzi:\n"
				+ "Personaggi:\n"
				+ "Uscite: Atrio nord Biblioteca"));
		this.caricatoreLabirinto.carica();
		this.labirinto =  this.caricatoreLabirinto.getLabirinto();
		assertTrue(this.labirinto.getListaStanze().containsKey("Atrio"));
		assertTrue(this.labirinto.getListaStanze().containsKey("Biblioteca"));
		assertEquals("Biblioteca",this.labirinto.getListaStanze().get("Atrio").getStanzaAdiacente(Direzione.NORD).getNome());
	}
	
	@Test
	public void caricaTest_monolocaleAggiuntaAttrezzo() throws FormatoFileNonValidoException{
		this.caricatoreLabirinto = new CaricatoreLabirinto(new StringReader(
				"Stanze: Atrio\n"
				+ "Stanze Buie:\n"
				+ "Stanze Magiche:\n"
				+ "Stanze Bloccate:\n"
				+ "Inizio: Atrio\n"
				+ "Vincente: Atrio\n"
				+ "Attrezzi: osso 3 Atrio\n"
				+ "Personaggi:\n"
				+ "Uscite:"));
		this.caricatoreLabirinto.carica();
		this.labirinto =  this.caricatoreLabirinto.getLabirinto();
		assertTrue(this.labirinto.getStanzaIniziale().hasAttrezzo("osso"));
		assertTrue(this.labirinto.getStanzaVincente().hasAttrezzo("osso"));
	}
	
	@Test
	public void caricaTest_monolocaleBuio() throws FormatoFileNonValidoException {
		this.caricatoreLabirinto = new CaricatoreLabirinto(new StringReader(
				"Stanze: Atrio\n"
				+ "Stanze Buie:Atrio lanterna\n"
				+ "Stanze Magiche:\n"
				+ "Stanze Bloccate:\n"
				+ "Inizio: Atrio\n"
				+ "Vincente: Atrio\n"
				+ "Attrezzi:\n"
				+ "Personaggi:\n"
				+ "Uscite:"));
		this.caricatoreLabirinto.carica();
		this.labirinto =  this.caricatoreLabirinto.getLabirinto();
		assertEquals("StanzaBuia",this.labirinto.getStanzaIniziale().getClass().getSimpleName());
	}
	
	@Test
	public void caricaTest_monolocaleMagico() throws FormatoFileNonValidoException {
		this.caricatoreLabirinto = new CaricatoreLabirinto(new StringReader(
				"Stanze: Atrio\n"
				+ "Stanze Buie:\n"
				+ "Stanze Magiche:Atrio\n"
				+ "Stanze Bloccate:\n"
				+ "Inizio: Atrio\n"
				+ "Vincente: Atrio\n"
				+ "Attrezzi:\n"
				+ "Personaggi:\n"
				+ "Uscite:"));
		this.caricatoreLabirinto.carica();
		this.labirinto =  this.caricatoreLabirinto.getLabirinto();
		assertEquals("StanzaMagica",this.labirinto.getStanzaIniziale().getClass().getSimpleName());
	}
	
	@Test
	public void caricaTest_bilocaleBloccato() throws FormatoFileNonValidoException {
		this.caricatoreLabirinto = new CaricatoreLabirinto(new StringReader(
				"Stanze: Atrio, Biblioteca\n"
				+ "Stanze Buie:\n"
				+ "Stanze Magiche:\n"
				+ "Stanze Bloccate:Atrio nord osso\n"
				+ "Inizio: Atrio\n"
				+ "Vincente: Biblioteca\n"
				+ "Attrezzi:\n"
				+ "Personaggi:\n"
				+ "Uscite:Atrio nord Biblioteca"));
		this.caricatoreLabirinto.carica();
		this.labirinto =  this.caricatoreLabirinto.getLabirinto();
		assertEquals("StanzaBloccata",this.labirinto.getStanzaIniziale().getClass().getSimpleName());
		assertEquals("Atrio", this.labirinto.getStanzaIniziale().getStanzaAdiacente(Direzione.NORD).getNome());
	}

	@Test
	public void caricaTest_monolocaleMago() throws FormatoFileNonValidoException {
		this.caricatoreLabirinto = new CaricatoreLabirinto(new StringReader(
				"Stanze: Atrio\n"
				+ "Stanze Buie:\n"
				+ "Stanze Magiche:\n"
				+ "Stanze Bloccate:\n"
				+ "Inizio: Atrio\n"
				+ "Vincente: Atrio\n"
				+ "Attrezzi:\n"
				+ "Personaggi: Atrio Mago Merlino buonsalve\n"
				+ "Uscite:"));
		this.caricatoreLabirinto.carica();
		this.labirinto =  this.caricatoreLabirinto.getLabirinto();
		assertEquals("Merlino",this.labirinto.getStanzaIniziale().getPersonaggio().getNome());
		assertEquals("Mago",this.labirinto.getStanzaIniziale().getPersonaggio().getClass().getSimpleName());
		assertEquals("Ciao, io sono Merlino.buonsalve",this.labirinto.getStanzaIniziale().getPersonaggio().saluta());
	}
	
	@Test
	public void caricaTest_monolocaleStrega() throws FormatoFileNonValidoException {
		this.caricatoreLabirinto = new CaricatoreLabirinto(new StringReader(
				"Stanze: Atrio\n"
				+ "Stanze Buie:\n"
				+ "Stanze Magiche:\n"
				+ "Stanze Bloccate:\n"
				+ "Inizio: Atrio\n"
				+ "Vincente: Atrio\n"
				+ "Attrezzi:\n"
				+ "Personaggi: Atrio Strega Medusa ihihi\n"
				+ "Uscite:"));
		this.caricatoreLabirinto.carica();
		this.labirinto =  this.caricatoreLabirinto.getLabirinto();
		assertEquals("Medusa",this.labirinto.getStanzaIniziale().getPersonaggio().getNome());
		assertEquals("Strega",this.labirinto.getStanzaIniziale().getPersonaggio().getClass().getSimpleName());
		assertEquals("Ciao, io sono Medusa.ihihi",this.labirinto.getStanzaIniziale().getPersonaggio().saluta());
	}
	
	@Test
	public void caricaTest_monolocaleCane() throws FormatoFileNonValidoException {
		this.caricatoreLabirinto = new CaricatoreLabirinto(new StringReader(
				"Stanze: Atrio\n"
				+ "Stanze Buie:\n"
				+ "Stanze Magiche:\n"
				+ "Stanze Bloccate:\n"
				+ "Inizio: Atrio\n"
				+ "Vincente: Atrio\n"
				+ "Attrezzi:\n"
				+ "Personaggi: Atrio Cane Cerbero WOOOF_WOOOF! osso bastone 2\n"
				+ "Uscite:"));
		this.caricatoreLabirinto.carica();
		this.labirinto =  this.caricatoreLabirinto.getLabirinto();
		assertEquals("Cerbero",this.labirinto.getStanzaIniziale().getPersonaggio().getNome());
		assertEquals("Cane",this.labirinto.getStanzaIniziale().getPersonaggio().getClass().getSimpleName());
		assertEquals("Ciao, io sono Cerbero.WOOOF WOOOF!",this.labirinto.getStanzaIniziale().getPersonaggio().saluta());
	}
	
	@Test
	public void caricaTest_labirintoCompleto() throws FormatoFileNonValidoException {
		this.caricatoreLabirinto = new CaricatoreLabirinto(new StringReader(
				"Stanze: Atrio, Sala, Corridoio, Camera, Cucina, Laboratorio, Giardino\n"
				+ "Stanze Buie: Camera Torcia\n"
				+ "Stanze Magiche:\n"
				+ "Stanze Bloccate: Laboratorio est Chiave, Sala est Chiave\n"
				+ "Inizio: Atrio\n"
				+ "Vincente: Giardino\n"
				+ "Attrezzi: Torcia 3 Sala, Osso 2 Atrio, Chiave 1 Corridoio\n"
				+ "Personaggi: Cucina Strega Medusa SSHsHsh, Corridoio Cane Cerbero woof Osso bastone 2, Laboratorio Mago Merlino_il_Vecchio ciao\n"
				+ "Uscite: Atrio nord Sala, Sala ovest Corridoio, Sala est Giardino, Corridoio nord Camera, Corridoio ovest Cucina, Cucina nord Laboratorio, Laboratorio est Giardino, Camera ovest Laboratorio\n"
				));
		/**
		 * il labirinto utilizzato è contenuto nel file "Labirinto1.txt"
		 */
		this.caricatoreLabirinto.carica();
		this.labirinto =  this.caricatoreLabirinto.getLabirinto();
		
		// test personaggi
		assertEquals("Cerbero", this.labirinto.getListaStanze().get("Corridoio").getPersonaggio().getNome());
		assertEquals("Merlino il Vecchio", this.labirinto.getListaStanze().get("Laboratorio").getPersonaggio().getNome());
		assertEquals("Medusa", this.labirinto.getListaStanze().get("Cucina").getPersonaggio().getNome());
		
		// test attrezzi
		assertTrue(this.labirinto.getListaStanze().get("Sala").hasAttrezzo("Torcia"));
		assertTrue(this.labirinto.getListaStanze().get("Atrio").hasAttrezzo("Osso"));
		assertTrue(this.labirinto.getListaStanze().get("Corridoio").hasAttrezzo("Chiave"));
		
		// test adiacenze
		assertEquals("Sala",this.labirinto.getListaStanze().get("Atrio").getStanzaAdiacente(Direzione.NORD).getNome());
		assertEquals("Corridoio",this.labirinto.getListaStanze().get("Sala").getStanzaAdiacente(Direzione.OVEST).getNome());
		assertEquals("Sala",this.labirinto.getListaStanze().get("Sala").getStanzaAdiacente(Direzione.EST).getNome()); // stanza bloccata
		assertEquals("Camera",this.labirinto.getListaStanze().get("Corridoio").getStanzaAdiacente(Direzione.NORD).getNome());
		assertEquals("Cucina",this.labirinto.getListaStanze().get("Corridoio").getStanzaAdiacente(Direzione.OVEST).getNome());
		assertEquals("Laboratorio",this.labirinto.getListaStanze().get("Cucina").getStanzaAdiacente(Direzione.NORD).getNome());
		assertEquals("Laboratorio",this.labirinto.getListaStanze().get("Laboratorio").getStanzaAdiacente(Direzione.EST).getNome()); // stanza bloccata
		assertEquals("Laboratorio",this.labirinto.getListaStanze().get("Camera").getStanzaAdiacente(Direzione.OVEST).getNome());
		
		// test stanze speciali
		assertEquals("StanzaBloccata", this.labirinto.getListaStanze().get("Laboratorio").getClass().getSimpleName());
		assertEquals("StanzaBloccata", this.labirinto.getListaStanze().get("Sala").getClass().getSimpleName());
		assertEquals("StanzaBuia", this.labirinto.getListaStanze().get("Camera").getClass().getSimpleName());
	}
}
