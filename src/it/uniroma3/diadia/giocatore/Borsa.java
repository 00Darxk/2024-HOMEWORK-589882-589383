package it.uniroma3.diadia.giocatore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.XMLFormatter;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatorePeso;
import it.uniroma3.diadia.attrezzi.ComparatoreNome;

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
		this.attrezzi = new LinkedList<Attrezzo>(); // speriamo bastino...
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
		Attrezzo a = null;
		for (Attrezzo i : this.attrezzi )
			if (i.getNome().equals(nomeAttrezzo))
				a = i;
		return a;
	}
	
	public int getPeso() {
		int peso = 0;
		for (Attrezzo i : this.attrezzi )
			peso += i.getPeso();

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
	
	





/* 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * • Aggiungere alla classe Borsa dei metodi di
interrogazione del suo contenuto:
– 

– 

– Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso()
restituisce una mappa che associa un intero (rappresentante un
peso) con l’insieme (comunque non vuoto) degli attrezzi di tale
peso: tutti gli attrezzi dell'insieme che figura come valore hanno
lo stesso peso pari all'intero che figura come chiave
• Utilizzare questi metodi per migliorare la stampa del
contenuto della Borsa (ad es. aggiungere e/o
modificare un comando guarda per la stampa del suo
contenuto>>)  */

	/**restituisce la lista degli attrezzi nella borsa ordinati per peso e
	 * quindi, a parità di peso, per nome
	 * 
	 * @return
	 */
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		final List<Attrezzo> ordinata = new ArrayList<>(this.attrezzi);
		final ComparatorePerPeso cmp = new ComparatorePerPeso();
		Collections.sort(ordinata,cmp);
		
		return ordinata;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		final SortedSet<Attrezzo> ordinata = new TreeSet<>(this.attrezzi);
		
		return ordinata;
	}
	


}

