package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Proprietà;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe Mago - Modella un mago nel 
 * gioco di ruolo. 
 * 
 * @see AbstractPersonaggio
 * @see Partita
 * @see Attrezzo
 * @see Proprietà
 * @versione 4.0
 */
public class Mago extends AbstractPersonaggio{
	private static final String MESSAGGIO_DONO = Proprietà.getMessaggioDono();
	private static final String MESSAGGIO_SCUSE = Proprietà.getMessaggioScuse();
	
	private Attrezzo attrezzo;
	
	public Mago(String nome, String presentazione) {
		super(nome, presentazione);
		attrezzo = null;
	}
	
	@Override
	public String agisci(Partita partita) {
		String msg;
		if (this.attrezzo!=null && partita.getStanzaCorrente().addAttrezzo(new Attrezzo(this.attrezzo.getNome(), this.attrezzo.getPeso()/2))) {
			this.attrezzo = null;
			msg = MESSAGGIO_DONO;
		}
		else {
			msg = MESSAGGIO_SCUSE;
		}
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(this.attrezzo == null)
			this.attrezzo = attrezzo;
		else 
			partita.getGiocatore().getBag().addAttrezzo(attrezzo);
		return agisci(partita);
	}
}
