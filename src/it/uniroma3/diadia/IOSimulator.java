package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe IOSImulator - Permette di simulare l'interazione con 
 * l'utente iniettando istruzioni precedentemente salvate al 
 * posto di leggere e scrivere a schermo
 * 
 * @see IO
 * @version 3.0
 */
public class IOSimulator implements IO{
	List<String> comandiDaEseguire;
	List<String> stampeEseguite;
	
	public IOSimulator() {
		this.comandiDaEseguire = new ArrayList<>();
		this.stampeEseguite = new ArrayList<>();
	}
	
	/**
	 * Permette di inserire un comando in coda alla lista dei comandi, 
	 * restituisce il riferimento a sé stesso per concatenere le aggiunte
	 * 
	 * @param cmd, istruzione da iniettare
	 * @return riferimento a sé stesso
	 */
	public IOSimulator aggiungiComandoDaEseguire(String cmd) {
		this.comandiDaEseguire.add(cmd);
		return this;
	}
	
	/**
	 * Restituisce la lista delle stampe che il codice 
	 * avrebbe effettuato
	 * 
	 * @return lista 
	 */
	public List<String> getStampeEseguite(){
		return this.stampeEseguite;
	}
	
	@Override
	public void mostraMessaggio(String messaggio) {
		this.stampeEseguite.add(messaggio);
	}

	@Override
	public String leggiRiga() {
		return this.comandiDaEseguire.remove(0);
	}
}
