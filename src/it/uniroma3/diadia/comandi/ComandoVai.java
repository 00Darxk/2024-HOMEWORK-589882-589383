package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;


/**
 * Classe ComandoVai - Cerca di andare in una direzione. Se c'e' una stanza ci entra 
 * e ne stampa il nome, altrimenti stampa un messaggio di errore
 * 
 * @see Stanza
 * @see Comando
 * @see Partita
 * @see IO
 * @version 3.0
 */

public class ComandoVai implements Comando{
	private IO io;
	private String direzione;

	@Override
	public void esegui(Partita partita) {
		if(direzione == null)
			this.io.mostraMessaggio("Dove vuoi andare ?");

		Stanza prossimaStanza = null;
		prossimaStanza = partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			this.io.mostraMessaggio("Direzione inesistente");
		else {
			partita.setStanzaCorrente(prossimaStanza);
			partita.getGiocatore().setCFU(partita.getGiocatore().getCFU()-1);
		}
		this.io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}
	
	@Override
	public void setParametro(String parametro) {
		this.direzione=parametro;
	}

	@Override
	public String getNome() {
		return "vai";
	}

	@Override
	public String getParametro() {
		return this.direzione;
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