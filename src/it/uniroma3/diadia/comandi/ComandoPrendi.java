package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;


/**
 * Classe ComandoPrendi - Sposta un attrezzo dalla stanza corrente 
 * del giocatore alla borsa
 * 
 * @see Stanza
 * @see Attrezzo
 * @see Comando
 * @see Partita
 * @see IO
 * @version 3.0
 */
public class ComandoPrendi implements Comando {
	private IO io;
	private String nomeAttrezzo;
	
	@Override
	public void esegui(Partita partita) {
		if(nomeAttrezzo == null || !(partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo)))
			this.io.mostraMessaggio("Attrezzo inesistente");
		else if(partita.getGiocatore().getBag().addAttrezzo(partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo))) 
			partita.getStanzaCorrente().removeAttrezzo(partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo));
		else
			this.io.mostraMessaggio("Non è stato possibile prendere l'attrezzo specificato.");

		this.io.mostraMessaggio(partita.getGiocatore().getBag().toString());
		this.io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo=parametro;
	}

	@Override
	public String getNome() {
		return "prendi";
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

	@Override
	public void setIO(IO io) {
		this.io = io;
	}
	
	@Override
	public IO getIO() {
		return this.io;
	}
}