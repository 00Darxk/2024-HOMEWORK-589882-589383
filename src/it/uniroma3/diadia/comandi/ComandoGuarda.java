package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

/**
 * Classe ComandoGuarda - Fornisce informazioni sulla 
 * partita e sulla stanza corrente
 * 
 * @see AbstractComando
 * @see Partita
 * @see IO
 * @version 4.0
 */
public class ComandoGuarda extends AbstractComando{
	public ComandoGuarda() {
		super.setNome(this.getClass().getSimpleName());
	}
	
	@Override
	public void esegui(Partita partita) {
		super.getIO().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		super.getIO().mostraMessaggio("Hai " + partita.getGiocatore().getCFU() + " CFU rimanenti\n");
	}
}