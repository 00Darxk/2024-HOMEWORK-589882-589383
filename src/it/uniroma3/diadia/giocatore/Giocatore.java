package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Proprietà;

/**
 * Classe Giocatore - Il giocatore del gioco di ruolo. 
 * Ha la responsabilità di gestire i CFU, condizione di 
 * vittoria, e di memorizzare gli attrezzi in un oggetto di 
 * tipo Borsa.
 * 
 * @see Borsa
 * @see Proprietà
 * @version 4.0
 */
public class Giocatore {
		static final private int CFU_INIZIALI = Proprietà.getCFUIniziali();
	
		private int CFU;
		private Borsa bag;
		
		public Giocatore() {
			this(CFU_INIZIALI, Proprietà.getPesoMaxBorsa());
		}

		public Giocatore(int CFU, int maxPeso) {
			this.bag = new Borsa(maxPeso);
			this.CFU = CFU;
		} 
		
		public int getCFU() {
			return this.CFU;
		}
		public void setCFU(int CFU) {
			this.CFU = CFU;
		}
		public Borsa getBag() {
			return this.bag;
		}
		public void setBag(Borsa bag) {
			this.bag = bag;
		}
		
}
