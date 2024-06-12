package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Proprietà;

/**
 * Classe ComandoNonValido - Modella il 
 * comportamento di un comando sconosciuto
 * 
 * @see AbstractComando
 * @see Partita
 * @see IO
 * @see Proprietà
 * @version 4.0
 */
public class ComandoNonValido extends AbstractComando{
	final static private String MESSAGGIO_COMANDO_SCONOSCIUTO = Proprietà.getMessaggioComandoSconosciuto();
	
	public ComandoNonValido() {
		super.setNome(this.getClass().getSimpleName());
	}
	
	@Override
	public void esegui(Partita partita) {
		super.getIO().mostraMessaggio(MESSAGGIO_COMANDO_SCONOSCIUTO);
	}
}