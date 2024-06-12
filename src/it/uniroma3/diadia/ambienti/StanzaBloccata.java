package it.uniroma3.diadia.ambienti;

/**
 * Classe Stanza Bloccata - Modella il comportamento di una 
 * stanza bloccata. Presenta una direzione non accessibile, se non è 
 * presente uno specifico attrezzo nella stanza. 
 * 
 * @see Stanza
 * @version 4.0
 */
public class StanzaBloccata extends Stanza{
	private Direzione direzioneBloccata;
	private String nomeAttrezzoNecessario;
	
	public StanzaBloccata(String nome, Direzione direzione, String nomeAttrezzo) {
		super(nome);
		this.direzioneBloccata = direzione;
		this.nomeAttrezzoNecessario = nomeAttrezzo;
	}
	
	@Override
	public Stanza getStanzaAdiacente(Direzione direzione) {
		if(!this.direzioneBloccata.equals(direzione) || super.hasAttrezzo(this.nomeAttrezzoNecessario))
			return super.getStanzaAdiacente(direzione);
		return this;
	}
	
	@Override
	public String getDescrizione() {
		return super.toString() + "\nDirezione Bloccata: " + this.direzioneBloccata;
	}
}
