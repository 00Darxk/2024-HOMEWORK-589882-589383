package it.uniroma3.diadia;
import java.io.IOException;

import it.uniroma3.diadia.ambienti.FormatoFileNonValidoException;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author docente di POO
 * @see Stanza
 * @see Labirinto
 * @see Giocatore
 * @version 4.0
 */

public class Partita {

	private Stanza stanzaCorrente;
	private boolean finita;
	private Labirinto lab;
	private Giocatore player;
	
	public Partita(Labirinto labirinto, Giocatore giocatore) {
		this.finita = false;
		this.lab = labirinto;
		this.player = giocatore;
		this.stanzaCorrente = this.lab.getStanzaIniziale();
	}
	
	public Partita(Labirinto labirinto) {
		this(labirinto, new Giocatore());
	}

	public Partita(String nomeFile) throws FormatoFileNonValidoException, IOException {
		this(Labirinto.newBuilder(nomeFile).getLabirinto(), new Giocatore());
	}
	
	public Partita() throws FormatoFileNonValidoException, IOException {
		this("LabirintoDefault");
	}

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * 
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente() == this.lab.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * 
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return this.finita || this.vinta() || (this.player.getCFU() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

	public Giocatore getGiocatore() {
		return this.player;
	}
	
	public Labirinto getLab() {
		return this.lab;
	}
	
	/**
	 * Controlla se il giocatore ha abbastanza CFU rimanenti
	 * 
	 * @return true se CFU > 0, false altrimenti
	 */
	public boolean giocatoreIsVivo() {
		return this.player.getCFU()>0;
	}

	public void setLabirinto(Labirinto labirinto) {
		this.lab = labirinto;
	}
	
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
}