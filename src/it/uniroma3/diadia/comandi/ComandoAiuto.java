package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;


/**
 * Classe ComandoAiuto - Stampa informazioni di aiuto.
 * 
 * @see Comando
 * @see Partita
 * @see IO
 * @version 3.0
 */
public class ComandoAiuto implements Comando {
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "guarda"};
	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		StringBuilder elenco = new StringBuilder();
		for(int i=0; i< elencoComandi.length; i++) 
			elenco.append(elencoComandi[i]+" ");
		io.mostraMessaggio(elenco + "\n");
	}

	@Override
	public void setIO(IO io) {
		this.io = io;
	}
	
	@Override
	public void setParametro(String parametro) {}

	@Override
	public String getNome() {
		return "aiuto";
	}

	@Override
	public String getParametro() {
		return null;
	}
	
	public IO getIO() {
		return this.io;
	}
}