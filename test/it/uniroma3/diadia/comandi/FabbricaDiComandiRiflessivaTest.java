package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Test;

public class FabbricaDiComandiRiflessivaTest {
	final private FabbricaDiComandi factory = new FabbricaDiComandiRiflessiva();
	final private String parametro = "parametro";
	
	private String istruzione;
	
	
	@Test
	public void costruisciComandoTest_istruzioneNull() {
		this.istruzione = null;
		assertEquals("nonvalido", this.factory.costruisciComando(this.istruzione).getNome());
	}
	
	@Test
	public void costruisciComandoTest_nomeIstruzioneNonNull() {
		this.istruzione = "nonvalido";
		assertEquals(this.istruzione, this.factory.costruisciComando(null).getNome());
		this.istruzione = "vai";
		assertEquals(this.istruzione, this.factory.costruisciComando(this.istruzione).getNome());
		this.istruzione = "prendi";
		assertEquals(this.istruzione, this.factory.costruisciComando(this.istruzione).getNome());
		this.istruzione = "posa";
		assertEquals(this.istruzione, this.factory.costruisciComando(this.istruzione).getNome());
		this.istruzione = "guarda";
		assertEquals(this.istruzione, this.factory.costruisciComando(this.istruzione).getNome());
		this.istruzione = "fine";
		assertEquals(this.istruzione, this.factory.costruisciComando(this.istruzione).getNome());
		this.istruzione = "aiuto";
		assertEquals(this.istruzione, this.factory.costruisciComando(this.istruzione).getNome());
	}
	
	@Test
	public void costruisciComandoTest_parametroIstruzioneNonNull() {
		this.istruzione = "vai "+this.parametro;
		assertEquals(this.parametro, this.factory.costruisciComando(this.istruzione).getParametro());
		this.istruzione = "prendi "+this.parametro;
		assertEquals(this.parametro, this.factory.costruisciComando(this.istruzione).getParametro());
		this.istruzione = "posa "+this.parametro;
		assertEquals(this.parametro, this.factory.costruisciComando(this.istruzione).getParametro());
	}
}
