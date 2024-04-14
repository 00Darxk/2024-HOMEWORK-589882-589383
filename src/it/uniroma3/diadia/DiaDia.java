package it.uniroma3.diadia;


import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	private Partita partita;

	private IOConsole IO;

	public DiaDia() {
		this.partita = new Partita();
		this.IO = new IOConsole();
	}

	public void gioca() {
		String istruzione; 

		this.IO.mostraMessaggio(MESSAGGIO_BENVENUTO);
		
			
		do		
			istruzione = this.IO.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		if(istruzione.isEmpty()) return false;
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if (comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else
			this.IO.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			this.IO.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			this.IO.mostraMessaggio(elencoComandi[i]+" ");
		this.IO.mostraMessaggio("\n");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			this.IO.mostraMessaggio("Dove vuoi andare ?");

		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			this.IO.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCFU();
			this.partita.getGiocatore().setCFU(cfu--);
		}
		this.IO.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	private void posa(String nomeAttrezzo) {
		//	if(nomeAttrezzo==null)
		//System.out.println("Cosa vuoi posare?");
		if(nomeAttrezzo!=null)
			if(this.partita.getGiocatore().getBag().hasAttrezzo(nomeAttrezzo))
				this.partita.getStanzaCorrente().addAttrezzo(this.partita.getGiocatore().getBag().removeAttrezzo(nomeAttrezzo));
		this.IO.mostraMessaggio(this.partita.getGiocatore().getBag().toString());
		this.IO.mostraMessaggio(this.partita.getStanzaCorrente().getDescrizione());
	}

	private void prendi(String nomeAttrezzo) {
		if(nomeAttrezzo!=null) {
			if(this.partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) {

				this.partita.getGiocatore().getBag().addAttrezzo(this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo));
				this.partita.getStanzaCorrente().removeAttrezzo(this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo));


				this.IO.mostraMessaggio(this.partita.getGiocatore().getBag().toString());
				this.IO.mostraMessaggio(this.partita.getStanzaCorrente().getDescrizione());
			}
			else
				this.IO.mostraMessaggio("non ci sta");
		}
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		this.IO.mostraMessaggio("\nGrazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {

		DiaDia gioco = new DiaDia();

		gioco.gioca();
	}
}