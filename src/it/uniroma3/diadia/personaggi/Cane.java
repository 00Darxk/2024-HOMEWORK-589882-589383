
package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Proprietà;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe Cane - Modella un cane 
 * nel gioco di ruolo. 
 * 
 * @see AbstractPersonaggio
 * @see Proprietà
 * @see Attrezzo
 * @see Stanza
 * @see Borsa
 * @see Partita
 * @versione 4.0
 */
public class Cane extends AbstractPersonaggio{
	private static final String MESSAGGIO_MORSO = Proprietà.getMessaggioMorso();
	private static final String MESSAGGIO_CIBO = Proprietà.getMessaggioCibo();
	
	private String ciboPreferito;
	private Attrezzo attrezzoInBocca;
	private boolean haMangiato;
	
	public Cane(String nome, String presentaz, String ciboPreferito, Attrezzo attrezzo) {
		super(nome, presentaz);
		this.ciboPreferito = ciboPreferito;
		this.attrezzoInBocca = attrezzo;
		this.haMangiato = false;
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		if (this.haMangiato) {
			if(this.attrezzoInBocca != null && partita.getStanzaCorrente().addAttrezzo(this.attrezzoInBocca))
				this.attrezzoInBocca = null;
			msg = MESSAGGIO_CIBO;
			this.haMangiato = false;
		}
		else {
			partita.getGiocatore().setCFU(partita.getGiocatore().getCFU()-1);
			msg = MESSAGGIO_MORSO;
		}
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(attrezzo.getNome().equals(this.ciboPreferito))
			this.haMangiato = true;
		else if(this.attrezzoInBocca == null)
			this.attrezzoInBocca = attrezzo;
		else if(!partita.getStanzaCorrente().addAttrezzo(attrezzo)) 
			partita.getGiocatore().getBag().addAttrezzo(attrezzo);

		return agisci(partita);
	}
}
