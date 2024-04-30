package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;


/**
 * Classe ComandoFine - Termina la partita. 
 */
public class ComandoFine implements Comando {

	@Override
	public void esegui(Partita partita) {
		System.out.println("\nGrazie di aver giocato!");  // si desidera smettere
	}

	@Override
	public void setParametro(String parametro) {

	}

}
