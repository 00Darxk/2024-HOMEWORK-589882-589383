package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @see Labirinto
 * @see Giocatore
 * @version base
 */

public class Partita {

	private Stanza stanzaCorrente;
	private boolean finita;
	private Labirinto lab;
	private Giocatore player;
	
	public Partita(Labirinto labirinto) {
		this.finita = false;
		this.lab = labirinto;
		this.player = new Giocatore();
		this.stanzaCorrente = this.lab.getStanzaIniziale();	
	}
	
	public Partita() {
		this(new Labirinto());
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente() == this.lab.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
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

	public boolean giocatoreIsVivo() {
		return this.player.getCFU()>0;
	}

	public void setLabirinto(Labirinto labirinto) {
		this.lab = labirinto;
	}
}