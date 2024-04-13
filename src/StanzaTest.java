import static org.junit.Assert.*;

import org.junit.Test;

public class StanzaTest {
	Stanza nonVuota = new Stanza("Non Vuota");
	Attrezzo Bastone = new Attrezzo("Bastone", 2);
	
	
	@Test
	public void testGetAttrezzo_stanzaVuota() {
		assertNull(new Stanza("vuota").getAttrezzo("inesistente"));
	}

	@Test
	public void testGetAttrezzo_stanzaNonVuota_attrezzoPresente() {
		nonVuota.addAttrezzo(Bastone);
		assertEquals(Bastone, nonVuota.getAttrezzo("Bastone"));
	}

	@Test
	public void testGetAttrezzo_stanzaNonVuota_attrezzoNonPresente() {
		assertEquals(null, nonVuota.getAttrezzo("Bastone"));
	}

}
