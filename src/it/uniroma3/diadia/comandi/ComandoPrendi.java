package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;


/**
 * Cerca di spostare un attrezzo dalla stanza corrente alla borsa del giocatore. Se non è presente, o non riesce a completare l'operazione mostra un 
 * messaggio di errore. 
 * @param nomeAttrezzo
 */
public class ComandoPrendi implements Comando {
	private String nomeAttrezzo = null;
	
	@Override
	public void esegui(Partita partita) {
		if(nomeAttrezzo == null || !(partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo)))
			System.out.println("Attrezzo inesistente");
		else if(partita.getGiocatore().getBag().addAttrezzo(partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo))) 
			partita.getStanzaCorrente().removeAttrezzo(partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo));
		else
			System.out.println("Non è stato possibile prendere l'attrezzo specificato.");

		System.out.println(partita.getGiocatore().getBag().toString());
		System.out.println(partita.getStanzaCorrente().getDescrizione());
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo=parametro;
	}

}
