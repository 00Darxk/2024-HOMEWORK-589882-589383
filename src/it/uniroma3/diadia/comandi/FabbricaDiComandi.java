package it.uniroma3.diadia.comandi;

/** 
 * Interfaccia FabbriaDiComandi - Modella la creazione dei comandi
 * 
 * @see Comando
 * @version 3.0
 */
public interface FabbricaDiComandi{
	public Comando costruisciComando(String istruzione);
}