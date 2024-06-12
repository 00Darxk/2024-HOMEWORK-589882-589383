package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Proprietà;


/**
 * Classe ComandoFine - Termina la partita. 
 * 
 * @see AbstractComando
 * @see Partita
 * @see IO
 * @see Proprietà
 * @version 4.0
 */
public class ComandoFine extends AbstractComando {
	final static private String MESSAGGIO_FINE = Proprietà.getMessaggioFine();
	
	public ComandoFine() {
		super.setNome(this.getClass().getSimpleName());
	}
	
	@Override
	public void esegui(Partita partita) {
		super.getIO().mostraMessaggio(MESSAGGIO_FINE);  
		partita.setFinita();
	}
}