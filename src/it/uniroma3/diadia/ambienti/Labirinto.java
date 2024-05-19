package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe Labirinto - Il labirinto è il lugo fisico dove è
 * ambientato il gioco di ruolo. Crea le stanze del labirinto, 
 * ogni labirinto ha una stanza iniziale (entrata) ed una 
 * stanza finale (uscita).
 * 
 * @see Stanza
 * @see Attrezzo
 * @version 3.0
 */
public class Labirinto {
	private Stanza ingresso;
	private Stanza uscita;

	public Labirinto() {
		creaStanze();
	}

	/**
     * Crea tutte le stanze e le porte di collegamento
     */
    private void creaStanze() {

		/* crea gli attrezzi */
		Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);

		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");
		
		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);

        /* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);

		// il gioco comincia nell'atrio
        this.ingresso = atrio;  
		this.uscita = biblioteca;
    }
    
    
    public Stanza getStanzaIniziale() {
		return this.ingresso;
	}

	public Stanza getStanzaVincente() {
		return this.uscita;
	}
	
	public void setIngresso(Stanza ingresso) {
		this.ingresso = ingresso;
	}
	
	public void setUscita(Stanza uscita) {
		this.uscita = uscita;
	}
}

