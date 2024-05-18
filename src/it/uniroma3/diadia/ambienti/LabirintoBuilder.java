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
	
	public LabirintoBuilder addStanzaIniziale(String nomeStanzaIniziale) {
		Stanza stanzaIniziale = new Stanza(nomeStanzaIniziale);
		super.setIngresso(stanzaIniziale);
		this.stanze.put(nomeStanzaIniziale, stanzaIniziale);
		this.nomeStanzaCorrente = nomeStanzaIniziale;
		return this;
	}
	
	public LabirintoBuilder addStanzaVincente(String nomeStanzaVincente) {
		Stanza stanzaVincente = new Stanza(nomeStanzaVincente);
		super.setUscita(stanzaVincente);
		this.stanze.put(nomeStanzaVincente,stanzaVincente);
		this.nomeStanzaCorrente = nomeStanzaVincente;
		return this;
	}
	
	public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso) {
		if(stanze.isEmpty()) return this;
		stanze.get(nomeStanzaCorrente).addAttrezzo(new Attrezzo(nomeAttrezzo, peso));
		return this;
	}
	
	public LabirintoBuilder addAdiacenza(String nomeStanza, String nomeStanzaAdiacente, String direzione) {
		if(stanze.isEmpty() || stanze.get(nomeStanza) == null || stanze.get(nomeStanzaAdiacente) == null)
			return this;
		stanze.get(nomeStanza).impostaStanzaAdiacente(direzione, stanze.get(nomeStanzaAdiacente));
		this.nomeStanzaCorrente = nomeStanza;
		return this;
	}
	
	public LabirintoBuilder addStanza(String nomeStanza) {
		stanze.put(nomeStanza, new Stanza(nomeStanza));
		this.nomeStanzaCorrente = nomeStanza;
		return this;
	}
	
	public LabirintoBuilder addStanzaMagica(String nomeStanza) {
		stanze.put(nomeStanza, new StanzaMagica(nomeStanza));
		this.nomeStanzaCorrente = nomeStanza;
		return this;
	}
	
	public LabirintoBuilder addStanzaMagica(String nomeStanza, int sogliaMagica) {
		stanze.put(nomeStanza, new StanzaMagica(nomeStanza, sogliaMagica));
		this.nomeStanzaCorrente = nomeStanza;
		return this;
	}
	
	public LabirintoBuilder addStanzaBloccata(String nomeStanza, String direzioneBloccata, String nomeAttrezzo) {
		stanze.put(nomeStanza, new StanzaBloccata(nomeStanza, direzioneBloccata, nomeAttrezzo));
		this.nomeStanzaCorrente = nomeStanza;
		return this;
	}
	
	public LabirintoBuilder addStanzaBuia(String nomeStanza, String nomeAttrezzo) {
		stanze.put(nomeStanza, new StanzaBuia(nomeStanza, nomeAttrezzo));
		this.nomeStanzaCorrente = nomeStanza;
		return this;
	}
	
	public LabirintoBuilder getLabirinto() {
		return this;
	}

	public Map<String,Stanza> getListaStanze(){
		return stanze;
	}
}