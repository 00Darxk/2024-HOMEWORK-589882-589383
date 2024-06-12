package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Proprietà;


/**
 * Classe ComandoPrendi - Sposta un attrezzo dalla stanza corrente 
 * del giocatore alla borsa
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
public class ComandoPrendi extends AbstractComando {
	final static private String MESSAGGIO_ATTREZZO_INESISTENTE = Proprietà.getMessaggioAttrezzoInesistente();
	final static private String MESSAGGIO_ATTREZZO_NON_PRESO = Proprietà.getMessaggioAttrezzoNonPreso();
	
	public ComandoPrendi() {
		super.setNome(this.getClass().getSimpleName());
	}
	
	@Override
	public void esegui(Partita partita) {
		if(super.getParametro()  == null || !(partita.getStanzaCorrente().hasAttrezzo(super.getParametro() )))
			super.getIO().mostraMessaggio(MESSAGGIO_ATTREZZO_INESISTENTE);
		else if(partita.getGiocatore().getBag().addAttrezzo(partita.getStanzaCorrente().getAttrezzo(super.getParametro() ))) 
			partita.getStanzaCorrente().removeAttrezzo(partita.getStanzaCorrente().getAttrezzo(super.getParametro() ));
		else
			super.getIO().mostraMessaggio(MESSAGGIO_ATTREZZO_NON_PRESO);

		super.getIO().mostraMessaggio(partita.getGiocatore().getBag().toString());
		super.getIO().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}
}