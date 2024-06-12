package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Proprietà;



/**
 * Classe ComandoPosa - Sposta un attrezzo dalla borsa 
 * del giocatore alla stanza corrente
 * 
 * @see Stanza
 * @see Borsa
 * @see Attrezzo
 * @see AbstractComando
 * @see Partita
 * @see IO
 * @see Proprietà
 * @version 4.0
 */
public class ComandoPosa extends AbstractComando {
	final static private String MESSAGGIO_ATTREZZO_INESISTENTE = Proprietà.getMessaggioAttrezzoInesistente();
	final static private String MESSAGGIO_ATTREZZO_NON_POSATO = Proprietà.getMessaggioAttrezzoNonPosato();
	
	public ComandoPosa() {
		super.setNome(this.getClass().getSimpleName());
	}
	
	@Override
	public void esegui(Partita partita) {
		if(super.getParametro() == null || !(partita.getGiocatore().getBag().hasAttrezzo(super.getParametro())))
			super.getIO().mostraMessaggio(MESSAGGIO_ATTREZZO_INESISTENTE);
		else if(partita.getStanzaCorrente().addAttrezzo(partita.getGiocatore().getBag().getAttrezzo(super.getParametro())))
			partita.getGiocatore().getBag().removeAttrezzo(super.getParametro() );
		else
			super.getIO().mostraMessaggio(MESSAGGIO_ATTREZZO_NON_POSATO);

		super.getIO().mostraMessaggio(partita.getGiocatore().getBag().toString());
		super.getIO().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}
}