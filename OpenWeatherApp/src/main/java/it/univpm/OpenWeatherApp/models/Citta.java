package it.univpm.OpenWeatherApp.models;


import java.util.Vector;

/** Classe che descrive le proprietà della città e le previsioni meteo raccolte.
 * 
 *  @author Francesca Colacrai
 *  @author Djouaka Kelefack Lionel
 */
public class Citta  {
	
	/** Nome della città */
	private String nomecitta;
	
	/** Paese a cui appartiene la città */
	private String paesecitta;
	
	/** Identificativo della città ottenuto dalle API di OpenWeather */
	private long idcitta;

	/** Vettore che contiene i dati meteo della città */
	private Vector<Meteo> raccoltaDatiMeteo;
	
	public Citta() {
		this.nomecitta = null;
		this.paesecitta = null;
		this.idcitta = 0;
	}
	
	/** Costruttore della classe
	 * @param nome Nome della città
	 */
	public Citta(String nome) {
		nomecitta = nome;
	}
	
	/** Costruttore della classe
	 * @param id Identificativo della città
	 */
	public Citta(long id) {
		idcitta = id;
	}
	
	/**
	 * Costruttore della classe che inizializza tutti gli attributi
	 *
	 * @param nome  Nome della città
	 * @param paese Paese di appartenenza della città
	 * @param id    Identificativo della città
	 */
	public Citta(String nome, String paese, long id) {
		{
			nomecitta = nome;
			paesecitta = paese;
			idcitta = id;
			this.raccoltaDatiMeteo = new Vector<Meteo>();
		}
	}

	/** Metodo getter
	 * @return Nome della città 
	 */
	public String getNome() {
		return nomecitta;
	}
	
	/**Metodo setter
	 * @param nomecitta
	 */
	public void setNome(String nomecitta) {
		this.nomecitta = nomecitta;
	}
	
	/** Metodo getter
	 * @return Paese a cui appartiene la città 
	 */
	public String getPaese() {
		return paesecitta;
	}
	
	/**Metodo setter
	 * @param paesecitta
	 */
	public void setPaese(String paesecitta) {
		this.paesecitta = paesecitta;
	}
	
	/** Metodo getter
	 * @return Id della città 
	 */
	public long getId() {
		return idcitta;
	}
	
	/**Metodo setter
	 * @param idcitta
	 */
	public void setId(long idcitta) {
		this.idcitta = idcitta;
	}
	
	/** Metodo getter
	 * @return Vettore dei dati della città 
	 */
	public Vector<Meteo> getRaccoltaDatiMeteo() {
		return raccoltaDatiMeteo;
	}
	public void setRaccoltaDatiMeteo(Vector<Meteo> raccoltaDatiMeteo) {
		this.raccoltaDatiMeteo = raccoltaDatiMeteo;
	}
	
	/** Metodo che converte i dati delle previsioni fatte in stringa
	 * @return Stringa contenente le previsioni fatte
	 */
	public String showVector(){
		String risultato="";
		for(int i=0; i<raccoltaDatiMeteo.size(); i++) {
			risultato += raccoltaDatiMeteo.get(i).toString();
		}
		return risultato;	
	}
	
	/** Override del metodo toString() 
	 *  @return Lista completa di tutti i dati della città
	 */
	@Override
	public String toString() {
		System.out.println("I dati relativi alla città cercata sono: \n");
		String risultati = "Nome= " + nomecitta + "\npaese= " + paesecitta + 
				"\nID= " + idcitta + "\nDati meteo raccolti:\n " +
				showVector() + ".";
		return risultati;
	}
}
