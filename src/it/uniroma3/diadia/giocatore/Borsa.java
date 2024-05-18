package it.uniroma3.diadia.giocatore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe Borsa - Ha la responsabilità di memorizzare 
 * gli oggetti del giocatore. 
 * 
 * @see Attrezzo
 */
public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	
	
	private List<Attrezzo> attrezzi;
	private int pesoMax;
	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new LinkedList<Attrezzo>();
	}
	
	/**
	 * Aggiunge un attrezzo alla borsa. 
	 * 
	 * @param attrezzo
	 * @return true se aggiunto, false altrimenti
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo == null || (this.getPeso() + attrezzo.getPeso() > this.getPesoMax()))
			return false;
		return this.attrezzi.add(attrezzo);
	}
	
	public int getPesoMax() {
		return this.pesoMax;
	}
	
	/**
	 * Restituisce un riferimento ad un attrezzo della borsa. 
	 * 
	 * @param nomeAttrezzo
	 * @return riferimento ad Attrezzo, null altrimenti
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo cercato = null;
		for (Attrezzo attrezzo : this.attrezzi )
			if (attrezzo.getNome().equals(nomeAttrezzo))
				cercato = attrezzo;
		return cercato;
	}
	
	/**
	 * Restituisce il peso complessivo della borsa
	 * 
	 * @return il peso della borsa
	 */
	public int getPeso() {
		int peso = 0;
		for (Attrezzo attrezzo : this.attrezzi )
			peso += attrezzo.getPeso();
		return peso;
	}
	
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}
	
	/**
	 * Verifica se un attrezzo è presente in borsa. 
	 * 
	 * @param nomeAttrezzo
	 * @return true è presente, false altrimenti
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	/**
	 * Rimuove un attrezzo dalla borsa. 
	 * 
	 * @param nomeAttrezzo
	 * @return riferimento ad Attrezzo, null altrimenti
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		//verifico che ci sia
		for(Attrezzo i : this.attrezzi) {
			if(i.getNome().equals(nomeAttrezzo)) {
				a=i;
				this.attrezzi.remove(a);
			}
		}
		return a;
	}
	
	/**
	 * Restituisce una rappresentazione stringa di questa borsa, 
	 * stampandone il peso e gli eventuali attrezzi contenuti, specificando il loro pero. 
	 * 
	 * @return la rappresentazione stringa 
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (Attrezzo i : this.attrezzi)
				s.append(i.toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}

	/**
	 * Restituisce la lista degli attrezzi nella borsa ordinati per peso e
	 * quindi, a parità di peso, per nome
	 * 
	 * @return lista ordinata per peso degli attrezzi
	 */
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		final List<Attrezzo> ordinata = new ArrayList<>(this.attrezzi);
		final ComparatorePerPeso cmp = new ComparatorePerPeso();
		Collections.sort(ordinata, cmp);
		return ordinata;
	}
	
	
	/**
	 * Restituisce l'insieme degli attrezzi nella borsa ordinati per nome
	 * 
	 * @return l'insieme degli attrezzi ordinati
	 */
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		final SortedSet<Attrezzo> ordinata = new TreeSet<>(this.attrezzi);
		return ordinata;
	}
	
	/**
	 * Restituisce una mappa che associa il peso con tutti gli atterzzi di quel peso
	 * 
	 * @return mappa di tutti gli insiemi di attrezzi
	 */
	Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer, Set<Attrezzo>> mappa = new HashMap<>();
		for(Attrezzo attrezzo : this.attrezzi) {
			Set<Attrezzo> pesoCorrente = mappa.get(attrezzo.getPeso());
			if(pesoCorrente == null)
				pesoCorrente = new HashSet<Attrezzo>();
			pesoCorrente.add(attrezzo);
			mappa.put(attrezzo.getPeso(), pesoCorrente);
		}
		return mappa;
	}
}