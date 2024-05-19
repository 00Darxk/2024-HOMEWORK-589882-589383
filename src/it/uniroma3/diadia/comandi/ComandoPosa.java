package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;



/**
 * Classe ComandoPosa - Sposta un attrezzo dalla borsa 
 * del giocatore alla stanza corrente
 * 
 * @see Stanza
 * @see Attrezzo
 * @see Comando
 * @see Partita
 * @see IO
 * @version 3.0
 */
public class ComandoPosa implements Comando {
	private IO io;
	private String nomeAttrezzo;
	
	@Override
	public void esegui(Partita partita) {
		if(nomeAttrezzo == null || !(partita.getGiocatore().getBag().hasAttrezzo(nomeAttrezzo)))
			this.io.mostraMessaggio("Attrezzo inesistente");
		else if(partita.getStanzaCorrente().addAttrezzo(partita.getGiocatore().getBag().getAttrezzo(nomeAttrezzo)))
			partita.getGiocatore().getBag().removeAttrezzo(nomeAttrezzo);
		else
			this.io.mostraMessaggio("Non è stato possibile posare l'attrezzo specificato.");

		this.io.mostraMessaggio(partita.getGiocatore().getBag().toString());
		this.io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo=parametro;
	}

	@Override
	public String getNome() {
		return "posa";
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

	public void setIO(IO io) {
		this.io = io;
	}
	
	public IO getIO() {
		return this.io;
	}
}