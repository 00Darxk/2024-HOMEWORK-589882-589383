package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;


/**
 * Classe ComandoVai - Cerca di andare in una direzione. Se c'e' una stanza ci entra 
 * e ne stampa il nome, altrimenti stampa un messaggio di errore
 * 
 * @see Partita
 */

public class ComandoVai implements Comando{
	
	private String direzione;


	@Override
	public void esegui(Partita partita) {
		if(direzione == null)
			System.out.println("Dove vuoi andare ?");

		Stanza prossimaStanza = null;
		prossimaStanza = partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			System.out.println("Direzione inesistente");
		else {
			partita.setStanzaCorrente(prossimaStanza);
			partita.getGiocatore().setCFU(partita.getGiocatore().getCFU()-1);
		}
		System.out.println(partita.getStanzaCorrente().getDescrizione());
	}
	
	@Override
	public void setParametro(String parametro) {
		this.direzione=parametro;
	}
	
}
