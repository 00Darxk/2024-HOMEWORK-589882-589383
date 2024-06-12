package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Proprietà;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;


/**
 * Classe ComandoVai - Cerca di andare in una direzione. Se c'e' una stanza ci entra 
 * e ne stampa il nome, altrimenti stampa un messaggio di errore
 * 
 * @see Stanza
 * @see AbstractComando
 * @see Partita
 * @see IO
 * @see Proprietà
 * @see Direzione
 * @version 4.0
 */

public class ComandoVai extends AbstractComando{
	final static private String MESSAGGIO_QUALE_DIREZIONE = Proprietà.getMessaggioQualeDirezione();
	
	public ComandoVai() {
		super.setNome(this.getClass().getSimpleName());
	}
	
	@Override
	public void esegui(Partita partita) {
		if(super.getParametro()  == null) {
			super.getIO().mostraMessaggio(MESSAGGIO_QUALE_DIREZIONE);
			return ;
		}
		Stanza prossimaStanza = null;
		prossimaStanza = partita.getStanzaCorrente().getStanzaAdiacente(Direzione.valueOf(super.getParametro().toUpperCase()));
		if (prossimaStanza == null)
			super.getIO().mostraMessaggio("Direzione inesistente");
		else {
			partita.setStanzaCorrente(prossimaStanza);
			partita.getGiocatore().setCFU(partita.getGiocatore().getCFU()-1);
		}
		super.getIO().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}
}