package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

/**
 * Classe ComandoNonValido - Modella il 
 * comportamento di un comando sconosciuto
 * 
 * @see Comando
 * @see Partita
 * @see IO
 * @version 3.0
 */
public class ComandoNonValido implements Comando{
	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		this.io.mostraMessaggio("Comando Sconosciuto");
	}
	
	@Override
	public void setParametro(String parametro) {}

	@Override
	public String getNome() {
		return "non valido";
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