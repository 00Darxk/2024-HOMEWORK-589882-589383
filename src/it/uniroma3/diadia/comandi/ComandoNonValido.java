package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando{
	public void esegui(Partita partita) {
		System.out.println("Comando Sconosciuto");
	}
	
	public void setParametro(String parametro) {
		
	}
	
}
