package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

/**
 * Classe AbstractComando - Modella un 
 * generico comando nel gioco di ruolo
 * 
 * @see Comando
 * @versione 4.0
 */
public abstract class AbstractComando implements Comando{

	private String nome;
	private String parametro;
	private IO io;
	
	abstract public void esegui(Partita partita);
	
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = new String(new StringBuilder(nome.replaceAll("Comando", ""))).toLowerCase();
	}
	
	public String getParametro() {
		return this.parametro;
	}
	
	public void setIO(IO io) {
		this.io = io;
	}
	
	public IO getIO() {
		return this.io;
	}
}
