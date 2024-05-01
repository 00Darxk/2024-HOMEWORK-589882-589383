package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;


/**
 * Classe ComandoFine - Termina la partita. 
 */
public class ComandoFine implements Comando {
	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("\nGrazie di aver giocato!");  // si desidera smettere
	}

	@Override
	public void setParametro(String parametro) {}

	@Override
	public String getNome() {
		return "fine";
	}

	@Override
	public String getParametro() {
		return null;
	}

	@Override
	public void setIO(IO io) {
		this.io = io;
	}

}
