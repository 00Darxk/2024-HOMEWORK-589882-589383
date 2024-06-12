package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Proprietà;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Classe ComandoInteragisci - Interagisce con il 
 * personaggio nella stanza corrente. 
 * 
 * @see AbstractComando
 * @see Partita
 * @see IO
 * @see Proprietà
 * @version 4.0
 */
public class ComandoInteragisci extends AbstractComando{
	private static final String MESSAGGIO_CON_CHI = Proprietà.getMessaggioConChi();
	private String messaggio;


	
	public ComandoInteragisci(){
		super.setNome(this.getClass().getSimpleName());
	}
	
	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio;
		personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio!=null) {
			this.messaggio = personaggio.agisci(partita);
			super.getIO().mostraMessaggio(this.messaggio);
		} else super.getIO().mostraMessaggio(MESSAGGIO_CON_CHI);
	}
	
	public String getMessaggio() {
		return this.messaggio;
	}
}
