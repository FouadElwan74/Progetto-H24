package dominio;

public class Ricevimenti {
    private String cognome;
    private String nome;
    private String data;
    private String ora;
    private String codiceFiscale;
    
    // Costruttore
    public Ricevimenti(String cognome, String nome, String data, String ora, String codiceFiscale) {
        this.cognome = cognome;
        this.nome = nome;
        this.data = data;
        this.ora = ora;
        this.codiceFiscale = codiceFiscale;
    }

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getOra() {
		return ora;
	}

	public void setOra(String ora) {
		this.ora = ora;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}


}
