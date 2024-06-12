package it.uniroma3.diadia;
import java.util.Scanner;

/**
 * Classe IOConsole - Gestisce la stampa e la lettura a schermo. 
 * 
 * @see IO
 * @version 4.0
 */
public class IOConsole implements IO{
	private Scanner scannerDiLinee;
	
	public IOConsole(Scanner scanner) {
		this.scannerDiLinee = scanner;
	}
	
	@Override
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	
	@Override
	public String leggiRiga() {
		String riga = this.scannerDiLinee.nextLine();
		return riga;
	}
}