package it.univpm.OpenWeatherApp.stats_and_filters;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import it.univpm.OpenWeatherApp.exceptions.StringaErrataException;

/**
 * Classe che contiene i metodi per effettuare il filtraggio sulla pressione 
 * 
 * @author Francesca Colacrai
 * @author Djouaka Kelefack Lionel
 *
 */
public class FiltraPressione {

	Statistiche stats = new Statistiche();
	/**
	 * Questo metodo calcola media, varianza, presisone massima e minima di un giorno delle città passate 
	 * in ingresso e filtra rispetto al valore. Restituisce un JSONArray con i JSONObject 
	 * che rappresentano le città e le relative statistiche sulla pressione. 
	 * A seconda di "valore" c'è un JSONObject che rappresenta la città con il valore max/min di pressione
	 * @param citta rappresenta le città con cui si vuole fare la statistica e il filtraggio
	 * @param valore rappresenta il valore con cui si vuole fare il filtraggio.
	 * @param flag Parametro per scegliere la statistica giornaliera(true) o su 5 giorni (false)
	 * @return JSONArray come anticipato
	 * @throws StringaErrataException se viene inserita una stringa errata.
	 * 
	 */
	
	public JSONArray filtraPressione(ArrayList<String> citta, String valore, String flag) throws StringaErrataException {
		
		JSONArray array = new JSONArray();
		
		ArrayList<JSONObject> media = new ArrayList<JSONObject>();
		ArrayList<JSONObject> pressioneMedia = new ArrayList<JSONObject>();
		ArrayList<JSONObject> oggetti = new ArrayList<JSONObject>();
		
		Iterator<String> iter = citta.iterator();
		
		int i = 0;
		
		double r1max = 0;
		double r1min = 1000;
		double r2max = 0;
		double r2min = 1000;
		double r3max = 0;
		double r3min = 1000;
		double r4max = 0;
		double r4min = 1000;
		
		while(iter.hasNext()) {
			JSONObject object = new JSONObject();
			object = stats.statistichePressione(iter.next(), flag);
			media.add(object);
			JSONObject datiPressione = object.getJSONObject("Dati sulla pressione");
			pressioneMedia.add(datiPressione);
			
			double pressioneMax = datiPressione.getDouble("Pressione massima");
			double pressioneMin = datiPressione.getDouble("Pressione minima");
			double pressione_Media = datiPressione.getDouble("Pressione media");
			double varianzaPressione = datiPressione.getDouble("Varianza della pressione");
			
			JSONObject obj = new JSONObject();
			obj.put("Nome città", citta.get(i));
			obj.put("Dati sulla pressione",datiPressione);
			oggetti.add(obj);
			array.put(obj);
			
			if(valore.equals("max")) {
				
					if(pressioneMax > r1max) 
						r1max = pressioneMax;
					
					if(pressioneMin > r2max) 
						r2max = pressioneMin;
					
					if(pressione_Media > r3max) 
						r3max = pressione_Media;
					
					if(varianzaPressione >= r4max) 
						r4max = varianzaPressione;
					
					i++;
			}
			else if(valore.equals("min")) {
				
				if(pressioneMax < r1max) {
					r1max = pressioneMax;
				}
				if(pressioneMin < r2max) {
					r2max = pressioneMin;
				}
				if(pressione_Media < r3max) {
					r3max = pressione_Media;
				}
				if(varianzaPressione < r4max) {
					r4max = varianzaPressione;
				}
				
				i++;
			}
			else throw new StringaErrataException (valore + " non è una stringa valida! Scegliere tra min o max");
		}
		
		JSONObject object = new JSONObject();
		
		if(valore.equals("max")) {
			object.put("Pressione_max massima", r1max);
			object.put("Presione_min massima", r2max);
			object.put("Pressione media massima", r3max);
			object.put("Varianza massima", r4max);
		}
		else { 
			object.put("Pressione_max minima", r1min);
			object.put("Presione_min minima", r2min);
			object.put("Pressione media minima",r3min);
			object.put("Varianza minima", r4min);
		}
		
		array.put(object);
		
		return array;
	}
}

