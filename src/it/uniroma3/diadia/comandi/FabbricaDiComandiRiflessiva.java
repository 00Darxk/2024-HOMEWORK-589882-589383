package it.uniroma3.diadia.comandi;

import java.util.Scanner;

/**
 * Classe FabbricaDiComandiRiflessiva - Modella la 
 * creazione di comandi. 
 * 
 * @see AbstractComando	
 * @versione 4.0
 */
public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi{

	@Override
	public Comando costruisciComando(String istruzione){
		if(istruzione == null) return new ComandoNonValido();
		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		Comando comando = null;
		

		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next();//prima parola: nome del comando
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next();//seconda parola: eventuale parametro
		
		try {
			StringBuilder nomeClasse = new StringBuilder("it.uniroma3.diadia.comandi.Comando");
			nomeClasse.append( Character.toUpperCase(nomeComando.charAt(0)) );
			nomeClasse.append( nomeComando.substring(1) ) ;
			comando = (Comando)Class.forName(nomeClasse.toString()).newInstance();
			comando.setParametro(parametro);
			
		}catch(Exception e){
			comando = new ComandoNonValido();
		} finally {
			scannerDiParole.close();
		}
		
		return comando;
	}
	
}
