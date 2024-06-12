package it.uniroma3.diadia;

import java.io.IOException;
import java.util.Properties;

/**
 * Classe Proprietà - Gestisce le 
 * costanti del gioco di ruolo
 * 
 * @version 4.0
 */
public class Proprietà {
	final static private String CFU_INIZIALI = "cfu_iniziali";
	final static private String PESO_MAX_BORSA = "peso_max_borsa";
	final static private String SOGLIA_MAGICA = "soglia_magica";
	final static private String NUMERO_MASSIMO_ATTREZZI = "numero_massimo_attrezzi";
	final static private String MESSAGGIO_FINE = "messaggio_fine";
	final static private String MESSAGGIO_VITTORIA= "messaggio_vittoria";
	final static private String MESSAGGIO_SCONFITTA = "messaggio_sconfitta";
	final static private String MESSAGGIO_BENVENUTO = "messaggio_benvenuto";
	final static private String MESSAGGIO_CON_CHI = "messaggio_con_chi";
	final static private String MESSAGGIO_COMANDO_SCONOSCIUTO = "messaggio_comando_sconosciuto";
	final static private String MESSAGGIO_ATTREZZO_INESISTENTE = "messaggio_attrezzo_inesistente";
	final static private String MESSAGGIO_ATTREZZO_NON_POSATO = "messaggio_attrezzo_non_posato";
	final static private String MESSAGGIO_ATTREZZO_NON_PRESO = "messaggio_attrezzo_non_preso";
	final static private String MESSAGGIO_QUALE_DIREZIONE = "messaggio_quale_direzione";
	final static private String MESSAGGIO_MORSO = "messaggio_morso";
	final static private String MESSAGGIO_CIBO = "messaggio_cibo";
	final static private String MESSAGGIO_DONO = "messaggio_dono";
	final static private String MESSAGGIO_SCUSE = "messaggio_scuse";
	final static private String MESSAGGIO_RISATA = "messaggio_risata";
	final static private String MESSAGGIO_INGANNO = "messaggio_inganno";
	
	private static Properties properties = null;
	
	
	public static int getCFUIniziali() {
		if(properties == null) carica();
		return Integer.parseInt(properties.getProperty(CFU_INIZIALI));
	}
	
	public static int getPesoMaxBorsa() {
		if(properties == null) carica();
		return Integer.parseInt(properties.getProperty(PESO_MAX_BORSA));
	}
	
	public static int getSogliaMagica() {
		if(properties == null) carica();
		return Integer.parseInt(properties.getProperty(SOGLIA_MAGICA));
	}

	public static String getMessaggioBenvenuto() {
		if(properties == null) carica();
		return properties.getProperty(MESSAGGIO_BENVENUTO);
	}
	
	public static int getNumeroMassimoAttrezzi() {
		if(properties == null) carica();
		return Integer.parseInt(properties.getProperty(NUMERO_MASSIMO_ATTREZZI));
	}
	
	public static String getMessaggioConChi() {
		if(properties == null) carica();
		return properties.getProperty(MESSAGGIO_CON_CHI);
	}
	
	public static String getMessaggioFine() {
		if(properties == null) carica();
		return properties.getProperty(MESSAGGIO_FINE);
	}
	
	public static String getMessaggioVittoria() {
		if(properties == null) carica();
		return properties.getProperty(MESSAGGIO_VITTORIA);
	}
	
	public static String getMessaggioSconfitta() {
		if(properties == null) carica();
		return properties.getProperty(MESSAGGIO_SCONFITTA);
	}
	
	public static String getMessaggioComandoSconosciuto() {
		if(properties == null) carica();
		return properties.getProperty(MESSAGGIO_COMANDO_SCONOSCIUTO);
	}
	
	public static String getMessaggioAttrezzoInesistente() {
		if(properties == null) carica();
		return properties.getProperty(MESSAGGIO_ATTREZZO_INESISTENTE);
	}

	public static String getMessaggioAttrezzoNonPosato() {
		if(properties == null) carica();
		return properties.getProperty(MESSAGGIO_ATTREZZO_NON_POSATO);
	}
	
	public static String getMessaggioAttrezzoNonPreso() {
		if(properties == null) carica();
		return properties.getProperty(MESSAGGIO_ATTREZZO_NON_PRESO);
	}

	public static String getMessaggioQualeDirezione() {
		if(properties == null) carica();
		return properties.getProperty(MESSAGGIO_QUALE_DIREZIONE);
	}
	
	public static String getMessaggioMorso() {
		if(properties == null) carica();
		return properties.getProperty(MESSAGGIO_MORSO);
	}
	
	public static String getMessaggioCibo() {
		if(properties == null) carica();
		return properties.getProperty(MESSAGGIO_CIBO);
	}
	
	public static String getMessaggioDono() {
		if(properties == null) carica();
		return properties.getProperty(MESSAGGIO_DONO);
	}
	
	public static String getMessaggioScuse() {
		if(properties == null) carica();
		return properties.getProperty(MESSAGGIO_SCUSE);
	}

	public static String getMessaggioRisata() {
		if(properties == null) carica();
		return properties.getProperty(MESSAGGIO_RISATA);
	}
	
	public static String getMessaggioInganno() {
		if(properties == null) carica();
		return properties.getProperty(MESSAGGIO_INGANNO);
	}
	
	private static void carica() {
		try {
			properties = new Properties();
			Proprietà.class.getClassLoader();
			properties.load(Proprietà.class.getClassLoader().getResourceAsStream("diadia.properties"));
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
}
