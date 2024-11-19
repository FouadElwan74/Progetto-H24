 package dominio;

import DataBase.RegistrazioneDAO;
import DataBase.ServiziDAO;

public class Servizi {

	    private String prezzo;
	    private String descrizioneMalattia;
	    private String trattamento;
	    private String codiceFiscale;
	    
	    
		private RegistrazioneDAO registrazioneDAO; // Per la registrazione degli utenti
	  
		private ServiziDAO serviziDAO; // DAO per i servizi

	    public Servizi( String prezzo, String descrizioneMalattia, String trattamento, String codiceFiscale) {
	      
	        this.prezzo = prezzo;
	        this.descrizioneMalattia = descrizioneMalattia;
	        this.trattamento = trattamento;
	        this.codiceFiscale = codiceFiscale;
	        
	        this.registrazioneDAO = registrazioneDAO;
	        this.serviziDAO = serviziDAO;
	    }



		public String getPrezzo() {
			return prezzo;
		}

		public void setPrezzo(String prezzo) {
			this.prezzo = prezzo;
		}

		public String getDescrizioneMalattia() {
			return descrizioneMalattia;
		}

		public void setDescrizioneMalattia(String descrizioneMalattia) {
			this.descrizioneMalattia = descrizioneMalattia;
		}

		public String getTrattamento() {
			return trattamento;
		}

		public void setTrattamento(String trattamento) {
			this.trattamento = trattamento;
		}

		public String getCodiceFiscale() {
			return codiceFiscale;
		}

		public void setCodiceFiscale(String codiceFiscale) {
			this.codiceFiscale = codiceFiscale;
		}
	

}
