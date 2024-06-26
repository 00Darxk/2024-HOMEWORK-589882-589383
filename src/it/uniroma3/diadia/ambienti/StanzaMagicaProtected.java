package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.Proprietà;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/** Classe StanzaMagicaProtected - Modella il comportamento 
 * 	di stanze magiche nel gioco di ruolo. 
 * 
 * 	@see StanzaProtected
 *	@see Proprietà
 *  @version 4.0
 */
public class StanzaMagicaProtected extends StanzaProtected{
	final static private int SOGLIA_MAGICA_DEFAULT = Proprietà.getSogliaMagica();
	
	private int contatoreAttrezziPosati;
	private int sogliaMagica;
	
	public StanzaMagicaProtected(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}
	public StanzaMagicaProtected(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia;
	}

	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.contatoreAttrezziPosati++;
		if (this.contatoreAttrezziPosati > this.sogliaMagica)
			attrezzo = this.modificaAttrezzo(attrezzo);		
		if (this.nome2attrezzo.size() < Proprietà.getNumeroMassimoAttrezzi()) {
			this.nome2attrezzo.put(attrezzo.getNome(), attrezzo);
			return true;
		}
		return false;	
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
}