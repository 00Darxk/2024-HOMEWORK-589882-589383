package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IOSimulator implements IO{
	List<String> comandiDaEseguire;
	List<String> stampeEseguite;
	
	public IOSimulator() {
		this.comandiDaEseguire = new ArrayList<>();
		this.stampeEseguite = new ArrayList<>();
	}
	
	public void aggiungiComandoDaEseguire(String cmd) {
		this.comandiDaEseguire.add(cmd);
	}

	public void aggiungiComandoDaEseguire(String... cmd) {
		Collections.addAll(this.comandiDaEseguire, cmd);
	}
	
	@Override
	public void mostraMessaggio(String messaggio) {
		System.out.println(messaggio);
		stampeEseguite.add(messaggio);
	}

	@Override
	public String leggiRiga() {
		String riga = this.comandiDaEseguire.remove(0);
		return riga;
	}
}
