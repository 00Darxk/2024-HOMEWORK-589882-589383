package it.uniroma3.diadia.ambienti;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.Proprietà;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @see Proprietà
 * @see AbstractPersonaggio
 * @version 4.0
 */

public class StanzaProtected {
	static final private int NUMERO_MASSIMO_ATTREZZI = Proprietà.getNumeroMassimoAttrezzi();

	private String nome;
	protected Map<String, Attrezzo> nome2attrezzo;
	private Map<Direzione, Stanza> direzione2stanza;
	private AbstractPersonaggio personaggio;
	
	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */
	public StanzaProtected(String nome) {
		this.nome = nome;
		this.direzione2stanza = new HashMap<>(Direzione.values().length);
		this.nome2attrezzo = new HashMap<>(NUMERO_MASSIMO_ATTREZZI);
	}
	
	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione la direzione in cui impostare la stanza
	 * @param riferimento alla stanza adiacente
	 */
	public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {
		this.direzione2stanza.put(direzione, stanza);
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(Direzione direzione) {
		return this.direzione2stanza.get(direzione);
	}
	
	/**
	 * Restituisce il nome della stanza.
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Restitruisce il personaggio presente nella stanza
	 * @return riferimento al personaggio
	 */
	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}
	
	/**
	 * Imposta il personaggio presente nella stanza
	 * @param personaggio il riferimento al personaggio
	 */
	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return la collezione di attrezzi nella stanza.
	 */
	public Collection<Attrezzo> getAttrezzi() {
		return this.nome2attrezzo.values();
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false altrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo != null) {
			if (this.nome2attrezzo.size() < NUMERO_MASSIMO_ATTREZZI) {
				this.nome2attrezzo.put(attrezzo.getNome(), attrezzo);
				return true;
			}
		}
		return false;
	}

	/**
	 * Mette una collezione di attrezzi nella stanza. 
	 * @param attrezzi
	 * @return true se è stata effettuata almento un'aggiunta, false altrimenti
	 */
	public boolean addAttrezzi(Collection<Attrezzo> attrezzi) {
		if(attrezzi == null || attrezzi.isEmpty())
			return false;
		for(Attrezzo attrezzo : attrezzi)
			this.addAttrezzo(attrezzo);
		return true;
	}
	
	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampandone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		risultato.append(this.getDirezioni().toString());
		risultato.append("\nAttrezzi nella stanza: ");
		risultato.append(this.getAttrezzi().toString());
		risultato.append("\nPersonaggio nella stanza: ");
		if(this.personaggio != null)
			risultato.append(this.personaggio.toString());
		return risultato.toString();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.nome2attrezzo.containsKey(nomeAttrezzo);
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.nome2attrezzo.get(nomeAttrezzo);	
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		if(attrezzo != null)
			return this.nome2attrezzo.remove(attrezzo.getNome()) != null;
		return false;
	}

	/**
	 * Restituisce l'insieme delle direzioni adiacenti
	 * @return insieme contenente tutte le direzioni
	 */
	public Set<Direzione> getDirezioni() { 
		return this.direzione2stanza.keySet(); 
	}

	/**
	 * Restituise la mappa delle stanze adiacenti
	 * @return mappa direzione2stanza
	 */
	public Map<Direzione, Stanza> getMapStanzeAdiacenti() {
		return this.direzione2stanza;
	}
	
	@Override
	public boolean equals(Object o) {
		Stanza that = (Stanza) o;
		return this.getNome().equals(that.getNome());
	}
	
	@Override
	public int hashCode() {
		return this.getNome().hashCode();
	}
}