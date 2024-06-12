package it.uniroma3.diadia;
import java.io.IOException;
import java.util.Scanner;

import it.uniroma3.diadia.ambienti.FormatoFileNonValidoException;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 * @see IOConsole
 * @see Partita
 * @see Labirinto
 * @see Comando
 * @see Proprietà
 * @see FabbricaDiComandiRiflessiva
 * @version 4.0
 */

public class DiaDia {
	static final private String MESSAGGIO_BENVENUTO = Proprietà.getMessaggioBenvenuto();
	static final private String MESSAGGIO_VITTORIA = Proprietà.getMessaggioVittoria();
	static final private String MESSAGGIO_SCONFITTA = Proprietà.getMessaggioSconfitta();

	
	private IO io;
	private Partita partita;


	/**
	 * @param io
	 * @throws FormatoFileNonValidoException
	 * @throws IOException 
	 */
	public DiaDia(IO io) throws FormatoFileNonValidoException, IOException {
		this(new Partita("LabirintoDefault.txt"), io);
	}
	
	public DiaDia(Partita partita, IO io) {
		this.partita = partita;
		this.io = io;
	}

	public void gioca() {
		String istruzione; 

		this.io.mostraMessaggio(MESSAGGIO_BENVENUTO);
			
		do		
			istruzione = this.io.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   
	
	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiRiflessiva();
		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.setIO(this.io);
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.vinta())
			this.io.mostraMessaggio(MESSAGGIO_VITTORIA);
		if (!this.partita.giocatoreIsVivo())
			this.io.mostraMessaggio(MESSAGGIO_SCONFITTA);

		return this.partita.isFinita();
	}

	public static void main(String[] argc) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(System.in);
			IO io = new IOConsole(scanner);
			DiaDia gioco = new DiaDia(io);

			gioco.gioca();
		} catch (Exception e) { e.printStackTrace();
		} finally { scanner.close(); }
	}
}