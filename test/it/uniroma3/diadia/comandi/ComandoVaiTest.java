package it.uniroma3.diadia.comandi;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;

public class ComandoVaiTest {
	private Partita partita;
	private Comando comando;

	@Before
	public void setUp() {
		this.partita = new Partita();
		this.comando = new ComandoVai();
	}

	@Test
	public void eseguiTest_parametroNullo() {
		comando.setParametro(null);
		//this.comando.esegui(this.partita);
	}
	
}
