package dominio;

public class Visiti {

	private String cognome;
    private String nome;
    private String data;
    private String ora;
    private String codiceFiscale;
    private String Tipo;

    public Visiti(String cognome, String nome, String data, String ora, String codiceFiscale, String Tipo) {
        this.cognome = cognome;
        this.nome = nome;
        this.data = data;
        this.ora = ora;
        this.codiceFiscale = codiceFiscale;
        this.Tipo = Tipo;
    }

    public String getCognome() {
        return cognome;
    }

    public String getTipo() {
		return Tipo;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}

	public String getNome() {
        return nome;
    }

    public String getData() {
        return data;
    }

    public String getOra() {
        return ora;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    

}
