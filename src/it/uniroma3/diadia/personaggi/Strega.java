package it.uniroma3.diadia.personaggi;

import java.util.HashSet;
import java.util.Set;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Proprietà;
import it.uniroma3.diadia.attrezzi.Attrezzo;


/**
 * Classe Strega - Modella una strega 
 * nel gioco di ruolo. 
 * 
 * @see Partita
 * @see Attrezzo
 * @versione 4.0
 */
public class Strega extends AbstractPersonaggio{
	private static final String MESSAGGIO_RISATA = Proprietà.getMessaggioRisata();
	private static final String MESSAGGIO_INGANNO = Proprietà.getMessaggioInganno();

	private Set<Attrezzo> attrezzi;
	private boolean rubato;

	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
		this.attrezzi = new HashSet<>();
		this.rubato = false;
	}
	
	public Set<Attrezzo> getAttrezziRubati(){
		return this.attrezzi;
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		if (this.rubato == true) {
			this.rubato = false;
			msg = MESSAGGIO_RISATA;
		}
		else 
			msg = MESSAGGIO_INGANNO;
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(attrezzo != null) {
			this.attrezzi.add(attrezzo);
			this.rubato = true;
		} else 
			this.rubato = false;
		return agisci(partita);
	}
}

