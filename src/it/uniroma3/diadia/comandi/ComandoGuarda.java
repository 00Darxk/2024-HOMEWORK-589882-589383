package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

/**
 * Classe ComandoGuarda - Fornisce informazioni sulla 
 * partita e sulla stanza corrente
 * 
 * @see Comando
 * @see Partita
 * @see IO
 * @version 3.0
 */
public class ComandoGuarda implements Comando {
	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		this.io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		this.io.mostraMessaggio("Hai " + partita.getGiocatore().getCFU() + " CFU rimanenti\n");
	}

	@Override
	public void setParametro(String parametro) {}

	@Override
	public String getNome() {
		return "guarda";
	}

	@Override
	public String getParametro() {
		return null;
	}

	@Override
	public void setIO(IO io) {
		this.io = io;
	}
	
	public IO getIO() {
		return this.io;
	}
}