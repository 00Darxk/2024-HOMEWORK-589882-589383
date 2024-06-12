package it.uniroma3.diadia.giocatore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.Proprietà;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe Borsa - Ha la responsabilità di memorizzare 
 * gli oggetti del giocatore. 
 * 
 * @see Attrezzo
 * @see Proprietà
 * @version 4.0
 */
public class Borsa {
	private final static int DEFAULT_PESO_MAX_BORSA = Proprietà.getPesoMaxBorsa();
	
	
	private List<Attrezzo> attrezzi;
	private int pesoMax;
	private int pesoCorrente;
	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.pesoCorrente = 0;
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
		else
			this.pesoCorrente += attrezzo.getPeso();
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
		if(nomeAttrezzo == null || attrezzi.isEmpty() || attrezzi.indexOf(new Attrezzo(nomeAttrezzo, 0)) == -1)
			return null;
		return attrezzi.get(attrezzi.indexOf(new Attrezzo(nomeAttrezzo, 0)));
	}
	
	/**
	 * Restituisce il peso complessivo della borsa
	 * 
	 * @return il peso della borsa
	 */
	public int getPeso() {
		return this.pesoCorrente;
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
		if(nomeAttrezzo == null || attrezzi.isEmpty() || attrezzi.indexOf(new Attrezzo(nomeAttrezzo, 0)) == -1)
			return null;
		else
			this.pesoCorrente -= attrezzi.get(attrezzi.indexOf(new Attrezzo(nomeAttrezzo ,0))).getPeso();
		return this.attrezzi.remove(attrezzi.indexOf(new Attrezzo(nomeAttrezzo ,0)));
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
			s.append("Contenuto borsa: ");
			s.append(attrezzi.toString());
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
		Collections.sort(ordinata, new Comparator<Attrezzo>() {
			@Override
			public int compare(Attrezzo a1, Attrezzo a2) {
				if(a1 == null) return -1;
				else if(a2 == null) return 1;
				int cmp = a1.getPeso() - a2.getPeso();
				if(cmp==0) {
					cmp= a1.getNome().compareTo(a2.getNome());
				}
				return cmp;
			}
		});
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
	 * @return mappa di tutti gli insiemi di attrezzi peso2attrezzi
	 */
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
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