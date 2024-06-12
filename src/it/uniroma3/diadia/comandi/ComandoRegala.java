package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Proprietà;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Classe ComandoRegala - Regala un attrezzo 
 * al personaggio nella stanza corrente
 * 
 * @see AbstractComando
 * @see Proprietà
 * @see IO
 * @see Attrezzo
 * @see AbstractPersonaggio
 * @see Borsa
 * @versione 4.0
 */
public class ComandoRegala extends AbstractComando{
	private static final String MESSAGGIO_CON_CHI = Proprietà.getMessaggioConChi();
	final static private String MESSAGGIO_ATTREZZO_INESISTENTE = Proprietà.getMessaggioAttrezzoInesistente();

	public ComandoRegala() {
		super.setNome(this.getClass().getSimpleName());
	}

	
	@Override
	public void esegui(Partita partita) {
		String nomeAttrezzo = super.getParametro();
		if(super.getParametro() == null || !(partita.getGiocatore().getBag().hasAttrezzo(nomeAttrezzo))) {
			super.getIO().mostraMessaggio(MESSAGGIO_ATTREZZO_INESISTENTE);
			return ;
		}
		AbstractPersonaggio personaggio;
		personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio!=null) {
			super.getIO().mostraMessaggio(personaggio.riceviRegalo(partita.getGiocatore().getBag().getAttrezzo(nomeAttrezzo), partita));
			partita.getGiocatore().getBag().removeAttrezzo(nomeAttrezzo);
			
		} else super.getIO().mostraMessaggio(MESSAGGIO_CON_CHI);

		super.getIO().mostraMessaggio(partita.getGiocatore().getBag().toString());
	}
}
