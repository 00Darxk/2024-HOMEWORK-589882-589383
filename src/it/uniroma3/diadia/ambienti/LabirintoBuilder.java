package it.uniroma3.diadia.ambienti;
import java.util.Map;
import java.util.HashMap;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * LabirintoBuilder - Classe che permette di realizzare 
 * labirinti utilizzando la tecnica del method chaining
 * 
 * @see Labirinto
 * @see Stanza
 * @see Attrezzo
 * @version 3.0
 */

public class LabirintoBuilder extends Labirinto {
	Map<String, Stanza> stanze;
	String nomeStanzaCorrente;
	
	public LabirintoBuilder() {
		super();
		super.setIngresso(null);
		super.setUscita(null);
		this.stanze = new HashMap<>();
		this.nomeStanzaCorrente = null;
	}
	
	/**
	 * Crea una stanza, e la imposta come stanza iniziale
	 * 
	 * @param nomeStanza
	 * @return riferimento a sé stesso
	 */
	public LabirintoBuilder addStanzaIniziale(String nomeStanza) {
		Stanza stanzaIniziale = new Stanza(nomeStanza);
		super.setIngresso(stanzaIniziale);
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
		super.setIngresso(this.stanze.get(nomeStanza));
		return this;
	}
	
	/**
	 * Crea una stanza, e la imposta come stanza vincente
	 * 
	 * @param nomeStanza
	 * @return riferimento a sé stesso
	 */
	public LabirintoBuilder addStanzaVincente(String nomeStanza) {
		Stanza stanzaVincente = new Stanza(nomeStanza);
		super.setUscita(stanzaVincente);
		this.stanze.put(nomeStanza,stanzaVincente);
		this.nomeStanzaCorrente = nomeStanza;
		return this;
	}
	
	public LabirintoBuilder setStanzaVincente(String nomeStanza) {
		super.setUscita(this.stanze.get(nomeStanza));
		return this;
	}

	/**
	 * Imposta una stanza come stanza vincente
	 * 
	 * @param nomeStanza
	 * @return riferimento a sé stesso
	 */
	public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso) {
		if(stanze.isEmpty() || nomeStanzaCorrente == null) 
			return this;
		stanze.get(nomeStanzaCorrente).addAttrezzo(new Attrezzo(nomeAttrezzo, peso));
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
		if(stanze.isEmpty() || stanze.get(nomeStanza) == null || stanze.get(nomeStanzaAdiacente) == null)
			return this;
		stanze.get(nomeStanza).impostaStanzaAdiacente(direzione, stanze.get(nomeStanzaAdiacente));
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
		stanze.put(nomeStanza, new Stanza(nomeStanza));
		this.nomeStanzaCorrente = nomeStanza;
		return this;
	}
	
	/**
	 * Crea una stanza magica
	 * 
	 * @param nomeStanza
	 * @return riferimento a sé stesso
	 */
	public LabirintoBuilder addStanzaMagica(String nomeStanza) {
		stanze.put(nomeStanza, new StanzaMagica(nomeStanza));
		this.nomeStanzaCorrente = nomeStanza;
		return this;
	}
	
	/**
	 * Crea una stanza magica, di soglia magica specificata
	 * 
	 * @param nomeStanza
	 * @return riferimento a sé stesso
	 */
	public LabirintoBuilder addStanzaMagica(String nomeStanza, int sogliaMagica) {
		stanze.put(nomeStanza, new StanzaMagica(nomeStanza, sogliaMagica));
		this.nomeStanzaCorrente = nomeStanza;
		return this;
	}
	
	/**
	 * Crea una stanza bloccata
	 * 
	 * @param nomeStanza
	 * @param direzioneBloccata
	 * @param nomeAttrezzo
	 * @return riferimento a sé stesso
	 */
	public LabirintoBuilder addStanzaBloccata(String nomeStanza, String direzioneBloccata, String nomeAttrezzo) {
		stanze.put(nomeStanza, new StanzaBloccata(nomeStanza, direzioneBloccata, nomeAttrezzo));
		this.nomeStanzaCorrente = nomeStanza;
		return this;
	}
	
	/**
	 * Crea una stanza buia
	 * 
	 * @param nomeStanza
	 * @param nomeAttrezzo
	 * @return riferimento a sé stesso
	 */
	public LabirintoBuilder addStanzaBuia(String nomeStanza, String nomeAttrezzo) {
		stanze.put(nomeStanza, new StanzaBuia(nomeStanza, nomeAttrezzo));
		this.nomeStanzaCorrente = nomeStanza;
		return this;
	}
	
	public LabirintoBuilder getLabirinto() {
		return this;
	}
	
	/** 
	 * Restituisce la mappa delle stanze
	 * 
	 * @return mappa nome2stanza
	 */
	public Map<String,Stanza> getListaStanze(){
		return stanze;
	}
}