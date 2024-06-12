package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.Proprietà;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/** Classe StanzaMagica - Modella il comportamento 
 * 	di stanze magiche nel gioco di ruolo. Dopo aver superato
 *  una soglia magica, gli attrezzi vengono modificati
 * 
 * 	@see Stanza
 * 	@see Proprietà
 *  @version 4.0
 */
public class StanzaMagica extends Stanza{
	final static private int SOGLIA_MAGICA_DEFAULT = Proprietà.getSogliaMagica();
	
	private int contatoreAttrezziPosati;
	private int sogliaMagica;
	
	public StanzaMagica(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}
	public StanzaMagica(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia;
	}

	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.contatoreAttrezziPosati++;
		if(this.contatoreAttrezziPosati>this.sogliaMagica)
			attrezzo=this.modificaAttrezzo(attrezzo);
		return super.addAttrezzo(attrezzo);	
	}
	
	/**
	 * Modifica un attrezzo invertendo il nome, e raddoppiandone il peso
	 * 
	 * @param attrezzo
	 * @return attrezzo modificato
	 */
	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito;
		int pesoX2 = attrezzo.getPeso() * 2;
		nomeInvertito = new StringBuilder(attrezzo.getNome());
		nomeInvertito = nomeInvertito.reverse();
		attrezzo = new Attrezzo(nomeInvertito.toString(),pesoX2);
		return attrezzo;
	}
	public boolean isMagica() {
		return true;
	}
}