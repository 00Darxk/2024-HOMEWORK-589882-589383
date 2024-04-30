package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;


/**
 * Cerca di spostare un attrezzo dalla borsa del giocatore alla stanza corrente. Se non è presente, o non riesce a completare l'operazione mostra un 
 * messaggio di errore. 
 * @param nomeAttrezzo
 */
public class ComandoPosa implements Comando {
	
	private String nomeAttrezzo = null;
	
	@Override
	public void esegui(Partita partita) {
		if(nomeAttrezzo == null || !(partita.getGiocatore().getBag().hasAttrezzo(nomeAttrezzo)))
			System.out.println("Attrezzo inesistente");
		else if(partita.getStanzaCorrente().addAttrezzo(partita.getGiocatore().getBag().getAttrezzo(nomeAttrezzo)))
			partita.getGiocatore().getBag().removeAttrezzo(nomeAttrezzo);
		else
			System.out.println("Non è stato possibile posare l'attrezzo specificato.");

		System.out.println(partita.getGiocatore().getBag().toString());
		System.out.println(partita.getStanzaCorrente().getDescrizione());
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo=parametro;
	}

}
