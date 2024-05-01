package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza{
	private String direzioneBloccata;
	private String nomeAttrezzoNecessario;
	
	public StanzaBloccata(String nome, String direzione, String nomeAttrezzo) {
		super(nome);
		this.direzioneBloccata = direzione;
		this.nomeAttrezzoNecessario = nomeAttrezzo;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if(this.direzioneBloccata != direzione || 
				super.hasAttrezzo(this.nomeAttrezzoNecessario))
			return super.getStanzaAdiacente(direzione);
		return this;
	}
	
	@Override
	public String getDescrizione() {
		return super.toString() + "\nDirezione Bloccata: " + this.direzioneBloccata;
	}
}
