package it.uniroma3.diadia.ambienti;


/** Classe StanzaBuia - Modella una stanza 
 * 	buia del gioco di ruolo. Richiede un 
 * 	attrezzo specifico per ottenere la descrizione
 * 	completa della stanza. 
 * 
 */
public class StanzaBuia extends Stanza{
	private String nomeAttrezzoNecessario;
	
	public StanzaBuia(String nome, String nomeAttrezzo) {
		super(nome);
		this.nomeAttrezzoNecessario = nomeAttrezzo;
	}
	
	@Override
	public String getDescrizione() {
		if(!super.hasAttrezzo(nomeAttrezzoNecessario))
			return "qui c'è buio pesto...";
		return super.getDescrizione();
	}
}
