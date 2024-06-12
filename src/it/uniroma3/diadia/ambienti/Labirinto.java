package it.uniroma3.diadia.ambienti;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

/**
 * Classe Labirinto - Il labirinto è il lugo fisico dove è
 * ambientato il gioco di ruolo. Crea le stanze del labirinto, 
 * ogni labirinto ha una stanza iniziale (entrata) ed una 
 * stanza finale (uscita).
 * 
 * @see Stanza
 * @see Attrezzo
 * @see LabirintoBuilder
 * @see CaricatoreLabirintor
 * @version 4.0
 */
public class Labirinto {
	private Stanza ingresso;
	private Stanza uscita;
	
	static private Map<String, Stanza> nome2stanza;

	private Labirinto() {
		this.ingresso = null;
		this.uscita = null;
		Labirinto.nome2stanza = null;
	}

	private void setListaStanze(Map<String, Stanza> nome2stanza) {
		Labirinto.nome2stanza = nome2stanza;
	}
	
	public Map<String, Stanza> getListaStanze(){
		return Labirinto.nome2stanza;
	}
	
    public Stanza getStanzaIniziale() {
		return this.ingresso;
	}

	public Stanza getStanzaVincente() {
		return this.uscita;
	}
	
	public void setIngresso(Stanza ingresso) {
		this.ingresso = ingresso;
	}
	
	public void setUscita(Stanza uscita) {
		this.uscita = uscita;
	}
		
	public static LabirintoBuilder newBuilder(){
		return new LabirintoBuilder();
	}
	
	
	public static LabirintoBuilder newBuilder(String nomeFile) throws IOException, FormatoFileNonValidoException{
		CaricatoreLabirinto caricatoreLab = new CaricatoreLabirinto(nomeFile);
		caricatoreLab.carica();
		return caricatoreLab.getLabirintoBuilder();
	}
	
	/**
	 * LabirintoBuilder - Classe che permette di realizzare 
	 * labirinti utilizzando la tecnica del method chaining
	 * 
	 * @see Labirinto
	 * @see Stanza
	 * @see Attrezzo
	 * @see Cane
	 * @see Mago
	 * @see Strega
	 * @version 4.0
	 */
	public static class LabirintoBuilder {
		private String nomeStanzaCorrente;
		private Labirinto labirinto;
		private Map<String, Stanza> stanze;
		
		private LabirintoBuilder() {
			this.labirinto = new Labirinto();
			this.stanze = new HashMap<>();
			this.nomeStanzaCorrente = null;
		}
		
		/**
		 * Crea una stanza, se non esiste già, e la imposta come stanza iniziale
		 * 
		 * @param nomeStanza
		 * @return riferimento a sé stesso
		 */
		public LabirintoBuilder addStanzaIniziale(String nomeStanza) {
			if(!this.stanze.isEmpty() && this.stanze.containsKey(nomeStanza))
				this.setStanzaIniziale(nomeStanza);
			Stanza stanzaIniziale = new Stanza(nomeStanza);
			this.labirinto.setIngresso(stanzaIniziale);
			this.stanze.put(nomeStanza, stanzaIniziale);
			this.nomeStanzaCorrente = nomeStanza;
			return this;
		}
		
		/**
		 * Imposta una stanza come stanza iniziale
		 * 
		 * @param nomeStanza
		 * @return riferimento a sé stesso
		 */
		public LabirintoBuilder setStanzaIniziale(String nomeStanza) {
			this.labirinto.setIngresso(this.stanze.get(nomeStanza));
			return this;
		}
		
		/**
		 * Crea una stanza, se non esiste già, e la imposta come stanza vincente
		 * 
		 * @param nomeStanza
		 * @return riferimento a sé stesso
		 */
		public LabirintoBuilder addStanzaVincente(String nomeStanza) {
			if(!this.stanze.isEmpty() && this.stanze.containsKey(nomeStanza))
				this.setStanzaVincente(nomeStanza);
			Stanza stanzaVincente = new Stanza(nomeStanza);
			this.labirinto
			.setUscita(stanzaVincente);
			this.stanze.put(nomeStanza,stanzaVincente);
			this.nomeStanzaCorrente = nomeStanza;
			return this;
		}
		
		public LabirintoBuilder setStanzaVincente(String nomeStanza) {
			this.labirinto.setUscita(this.stanze.get(nomeStanza));
			return this;
		}

		/**
		 * Aggiunge un attrezzo all'ultima stanza aggiunta
		 * 
		 * @param nome
		 * @param peso 
		 * @return riferimento a sé stesso
		 */
		public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso) {
			if(this.stanze.isEmpty() || nomeStanzaCorrente == null) 
				return this;
			this.stanze.get(nomeStanzaCorrente).addAttrezzo(new Attrezzo(nomeAttrezzo, peso));
			return this;
		}
		
		/**
		 * Aggiunge un attrezzo nella stanza specificata
		 * 
		 * @param nome della stanza
		 * @param nome
		 * @param peso
		 * @return riferimento a sé stesso
		 */
		public LabirintoBuilder addAttrezzo(String nomeStanza, String nomeAttrezzo, int peso) {
			if(this.stanze.isEmpty() || !this.stanze.containsKey(nomeStanza))
				return this;
			this.stanze.get(nomeStanza).addAttrezzo(new Attrezzo(nomeAttrezzo, peso));
			return this;
		}
		
		/**
		 * Aggiunge un attrezzo all'ultima stanza aggiunta
		 * 
		 * @param riferimento all'attrezzo attrezzo
		 * @return riferimento a sé stesso
		 */
		public LabirintoBuilder addAttrezzo(Attrezzo attrezzo) {
			if(this.stanze.isEmpty() || nomeStanzaCorrente == null) 
				return this;
			this.stanze.get(nomeStanzaCorrente).addAttrezzo(attrezzo);
			return this;
		}
		
		/**
		 * Aggiunge un attrezzo ad una stanza specificata
		 * 
		 * @param nome della stanza e riferimento all'attrezzo attrezzo
		 * @return riferimento a sé stesso
		 */
		public LabirintoBuilder addAttrezzo(String nomeStanza, Attrezzo attrezzo) {
			if(this.stanze.isEmpty() || !this.stanze.containsKey(nomeStanza)) 
				return this;
			this.stanze.get(nomeStanza).addAttrezzo(attrezzo);
			return this;
		}
		
		
		/**
		 * Imposta la seconda stanza come adiacente alla prima, nella direzione specificata. 
		 * Entrambe le stanze devono esistere affinché venga effettuate l'operazione. 
		 * 
		 * @param nomeStanza
		 * @param nomeStanzaAdiacente
		 * @param direzione
		 * @return riferimento a sé stesso
		 */
		public LabirintoBuilder addAdiacenza(String nomeStanza, String nomeStanzaAdiacente, String direzione) {
			if(this.stanze.isEmpty() || !this.stanze.containsKey(nomeStanza) || !this.stanze.containsKey(nomeStanzaAdiacente))
				return this;
			this.stanze.get(nomeStanza).impostaStanzaAdiacente(Direzione.valueOf(direzione.toUpperCase()), this.stanze.get(nomeStanzaAdiacente));
			this.nomeStanzaCorrente = nomeStanza;
			return this;
		}
		
		/**
		 * Crea una stanza
		 * 
		 * @param nomeStanza
		 * @return riferimento a sé stesso
		 */
		public LabirintoBuilder addStanza(String nomeStanza) {
			this.stanze.put(nomeStanza, new Stanza(nomeStanza));
			this.nomeStanzaCorrente = nomeStanza;
			return this;
		}
		
		/**
		 * Imposta una stanza già esistente come magica, altrimenti ne crea un'altra
		 * 
		 * @param nomeStanza
		 * @return riferimento a sé stesso
		 */
		public LabirintoBuilder addStanzaMagica(String nomeStanza) {
			if(this.stanze.isEmpty() || !this.stanze.containsKey(nomeStanza))
				this.stanze.put(nomeStanza, new StanzaMagica(nomeStanza));
			else 
				this.stanze.put(nomeStanza, sovrascriviStanza(new StanzaMagica(nomeStanza), this.stanze.get(nomeStanza)));
			this.nomeStanzaCorrente = nomeStanza;
			return this;
		}
		
		/**
		 * Imposta una stanza già esistente come magica di soglia magica specicata, altrimenti ne crea un'altra
		 * 
		 * @param nomeStanza
		 * @return riferimento a sé stesso
		 */
		public LabirintoBuilder addStanzaMagica(String nomeStanza, int sogliaMagica) {
			if(this.stanze.isEmpty() || !this.stanze.containsKey(nomeStanza))
				this.stanze.put(nomeStanza, new StanzaMagica(nomeStanza, sogliaMagica));
			else 
				this.stanze.put(nomeStanza, sovrascriviStanza(new StanzaMagica(nomeStanza, sogliaMagica), this.stanze.get(nomeStanza)));
			this.nomeStanzaCorrente = nomeStanza;
			return this;
		}
		
		/**
		 * Imposta una stanza già esistente come bloccata, altrimenti ne crea un'altra
		 * 
		 * @param nomeStanza
		 * @param direzioneBloccata
		 * @param nomeAttrezzo
		 * @return riferimento a sé stesso
		 */
		public LabirintoBuilder addStanzaBloccata(String nomeStanza, String direzioneBloccata, String nomeAttrezzo) {
			if(this.stanze.isEmpty() || !this.stanze.containsKey(nomeStanza))
				this.stanze.put(nomeStanza, new StanzaBloccata(nomeStanza, Direzione.valueOf(direzioneBloccata.toUpperCase()), nomeAttrezzo));
			else 
				this.stanze.put(nomeStanza, sovrascriviStanza(new StanzaBloccata(nomeStanza, Direzione.valueOf(direzioneBloccata.toUpperCase()), nomeAttrezzo), this.stanze.get(nomeStanza)));
			this.nomeStanzaCorrente = nomeStanza;
			return this;
		}
		
		/**
		 * Imposta una stanza già esistente come buia, altrimenti ne crea un'altra
		 * 
		 * @param nomeStanza
		 * @param nomeAttrezzo
		 * @return riferimento a sé stesso
		 */
		public LabirintoBuilder addStanzaBuia(String nomeStanza, String nomeAttrezzo) {
			if(this.stanze.isEmpty() || !this.stanze.containsKey(nomeStanza))
				this.stanze.put(nomeStanza, new StanzaBuia(nomeStanza, nomeAttrezzo));
			else 
				this.stanze.put(nomeStanza, sovrascriviStanza(new StanzaBuia(nomeStanza, nomeAttrezzo), this.stanze.get(nomeStanza)));
			this.nomeStanzaCorrente = nomeStanza;
			return this;
		}
		
		/**
		 * Aggiunge una Strega all'ultima stanza aggiunta
		 * 
		 * @param nome della Strega
		 * @param presentazione
		 * @return riferimento a sé stesso
		 */
		public LabirintoBuilder addStrega(String nome, String presentazione) {
			if(this.stanze.isEmpty() || this.nomeStanzaCorrente == null) 
				return this;
			this.stanze.get(nomeStanzaCorrente).setPersonaggio(new Strega(nome, presentazione));
			return this;
		}
		
		/**
		 * Aggiunge una Strega nella stanza specificata
		 * 
		 * @param nome della stanza
		 * @param nome della Strega
		 * @param presentazione
		 * @return riferimento a sé stesso
		 */
		public LabirintoBuilder addStrega(String nomeStanza, String nome, String presentazione) {
			if(this.stanze.isEmpty() || !this.stanze.containsKey(nomeStanza)) 
				return this;
			this.stanze.get(nomeStanza).setPersonaggio(new Strega(nome, presentazione));
			return this;
		}
		
		/**
		 * Aggiunge un Mago all'ultima stanza aggiunta
		 * 
		 * @param nome del Mago
		 * @param presentazione
		 * @return riferimento a sé stesso
		 */
		public LabirintoBuilder addMago(String nome, String presentazione) {
			if(this.stanze.isEmpty() || this.nomeStanzaCorrente == null) 
				return this;
			this.stanze.get(nomeStanzaCorrente).setPersonaggio(new Mago(nome, presentazione));
			return this;
		}
		
		/**
		 * Aggiunge un Mago nella stanza specificata
		 * 
		 * @param nome della stanza
		 * @param nome del Mago
		 * @param presentazione
		 * @return riferimento a sé stesso
		 */
		public LabirintoBuilder addMago(String nomeStanza, String nome, String presentazione) {
			if(this.stanze.isEmpty() || !this.stanze.containsKey(nomeStanza)) 
				return this;
			this.stanze.get(nomeStanza).setPersonaggio(new Mago(nome, presentazione));
			return this;
		}
		
		/**
		 * Aggiunge un Cane all'ultima stanza aggiunta
		 * 
		 * @param nome del Cane
		 * @param presentazione
		 * @param nome del cibo preferito
		 * @param riferimento all'attrezzo in bocca
		 * @return riferimento a sé stesso
		 */
		public LabirintoBuilder addCane(String nome, String presentazione, String ciboPreferito, Attrezzo attrezzoInBocca) {
			if(this.stanze.isEmpty() || this.nomeStanzaCorrente == null) 
				return this;
			this.stanze.get(nomeStanzaCorrente).setPersonaggio(new Cane(nome, presentazione, ciboPreferito, attrezzoInBocca));
			return this;
		}
		
		/**
		 * Aggiunge un Cane nella stanza specificata
		 * 
		 * @param nome della stanza
		 * @param nome del Cane
		 * @param presentazione
		 * @param nome del cibo preferito
		 * @param riferimento all'attrezzo in bocca
		 * @return riferimento a sé stesso
		 */
		public LabirintoBuilder addCane(String nomeStanza, String nome, String presentazione, String ciboPreferito, Attrezzo attrezzoInBocca) {
			if(this.stanze.isEmpty() || !this.stanze.containsKey(nomeStanza)) 
				return this;
			this.stanze.get(nomeStanza).setPersonaggio(new Cane(nome, presentazione, ciboPreferito, attrezzoInBocca));
			return this;
		}
		
		/**
		 * Aggiunge un Cane all'ultima stanza aggiunta
		 * 
		 * @param nome del Cane
		 * @param presentazione
		 * @param nome del cibo preferito
		 * @param nome e peso dell'attrezzo in bocca
		 * @return riferimento a sé stesso
		 */
		public LabirintoBuilder addCane(String nome, String presentazione, String ciboPreferito, String nomeAttrezzoInBocca, int pesoAttrezzoInBocca) {
			if(this.stanze.isEmpty() || this.nomeStanzaCorrente == null) 
				return this;
			this.stanze.get(nomeStanzaCorrente).setPersonaggio(new Cane(nome, presentazione, ciboPreferito, new Attrezzo(nomeAttrezzoInBocca, pesoAttrezzoInBocca)));
			return this;
		}
		
		/**
		 * Aggiunge un Cane nella stanza specificata
		 * 
		 * @param nome dela stanza
		 * @param nome del Cane
		 * @param presentazione
		 * @param nome del cibo preferito
		 * @param nome e peso dell'attrezzo in bocca
		 * @return riferimento a sé stesso
		 */
		public LabirintoBuilder addCane(String nomeStanza, String nome, String presentazione, String ciboPreferito, String nomeAttrezzoInBocca, int pesoAttrezzoInBocca) {
			if(this.stanze.isEmpty() || !this.stanze.containsKey(nomeStanza)) 
				return this;
			this.stanze.get(nomeStanza).setPersonaggio(new Cane(nome, presentazione, ciboPreferito, new Attrezzo(nomeAttrezzoInBocca, pesoAttrezzoInBocca)));
			return this;
		}
		
		/**
		 * Aggiuge un personaggio all'ultima stanza aggiunta
		 * 
		 * @param riferimento all'oggetto personaggio
		 * @return riferimento a sé stesso
		 */
		public LabirintoBuilder addPersonaggio(AbstractPersonaggio personaggio) {
			if(this.stanze.isEmpty() || this.nomeStanzaCorrente == null) 
				return this;
			this.stanze.get(nomeStanzaCorrente).setPersonaggio(personaggio);
			return this;
		}
		
		/**
		 * Aggiuge un personaggio nella stanza specificata
		 * 
		 * @param nome della stanza
		 * @param riferimento all'oggetto personaggio
		 * @return riferimento a sé stesso
		 */
		public LabirintoBuilder addPersonaggio(String nomeStanza, AbstractPersonaggio personaggio) {
			if(this.stanze.isEmpty() || !this.stanze.containsKey(nomeStanza)) 
				return this;
			this.stanze.get(nomeStanza).setPersonaggio(personaggio);
			return this;
		}
		
		public Labirinto getLabirinto() {
			this.labirinto.setListaStanze(stanze);
			return this.labirinto;
		}
		
		/**
		 * Copia gli attrezzi, personaggi e adiacenze da una vecchia stanza ad una nuova stanza
		 * 
		 * @param nuova
		 * @param vecchia
		 * @return riferimento alla nuova stanza
		 */
		private Stanza sovrascriviStanza(Stanza nuova, Stanza vecchia) {
			nuova.addAttrezzi(vecchia.getAttrezzi());
			nuova.setPersonaggio(vecchia.getPersonaggio());
			for(Direzione dir : vecchia.getDirezioni())
				nuova.impostaStanzaAdiacente(dir, vecchia.getStanzaAdiacente(dir));
			return nuova;
		}

		/** 
		 * Restituisce la mappa delle stanze
		 * 
		 * @return mappa nome2stanza
		 */
		public Map<String,Stanza> getListaStanze(){
			return this.stanze;
		}
	}
}

