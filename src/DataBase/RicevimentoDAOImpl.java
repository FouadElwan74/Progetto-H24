/*package DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dominio.Ricevimenti;
import dominio.Visiti;

public class RicevimentoDAOImpl implements RicevimentoDAO {
 
	

    // private String tableName;
  
	//@SuppressWarnings("unused")
	private String Tipo_di_utente;

    // Costruttore che accetta il tipo di ricevimento
    public RicevimentoDAOImpl(String Ricevimenti) {
    //  this.Tipo_di_utente = Ricevimenti;
        //this.tableName = "Ricevimenti_" + Tipo_di_utente;
    }

    // Metodo per aggiungere un ricevimento alla tabella dei ricevimenti
    public void addRicevimento(Ricevimenti ricevimento) throws SQLException {
        String sql = "INSERT INTO Ricevimenti (COGNOME, NOME, DATA, ORA, CODICE_FISCALE) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, ricevimento.getCognome());
            pstmt.setString(2, ricevimento.getNome());
            pstmt.setString(3, ricevimento.getData());
            pstmt.setString(4, ricevimento.getOra());
            pstmt.setString(5, ricevimento.getCodiceFiscale());

            pstmt.executeUpdate();
        }
    }

    // Metodo per eliminare un ricevimento dalla tabella dei ricevimenti
    @Override
    public void deleteRicevimento(String codiceFiscale) throws SQLException {
        String sql = "DELETE FROM Reicevimenti WHERE CODICE_FISCALE = ?";
        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, codiceFiscale);
            pstmt.executeUpdate();
        }
    }

    
    @Override
    public List<Ricevimenti> getAllRicevimenti() throws SQLException {
        String sql = "SELECT * FROM Ricevimenti ";
        List<Ricevimenti> ricevimenti = new ArrayList<>();

        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ricevimenti.add(new Ricevimenti(
                        rs.getString("COGNOME"),
                        rs.getString("NOME"),
                        rs.getString("DATA"),
                        rs.getString("ORA"),
                        rs.getString("CODICE_FISCALE")));
            }
        }

        return ricevimenti;
    }

	@Override
	public void addRicevimento(Visiti visita) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	

	

}
*/
package DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import dominio.Ricevimenti;
import dominio.Visiti;


public class RicevimentoDAOImpl implements RicevimentoDAO {

    private String tipoDiUtente;
    //String tipoDiUtente = "ortopedico"; // Ottieni il tipo di utente dal sistema di login
    //RicevimentoDAO ricevimentoDAO = new RicevimentoDAOImpl(tipoDiUtente);

    // Costruttore che accetta il tipo di utente
    public RicevimentoDAOImpl(String tipoDiUtente) {
        this.tipoDiUtente = tipoDiUtente;
    }

    // Metodo per aggiungere un ricevimento, includendo il tipo di utente
    public void addRicevimento(Ricevimenti ricevimento) throws SQLException {
        String sql = "INSERT INTO Ricevimenti (COGNOME, NOME, DATA, ORA, CODICE_FISCALE, TIPO_DI_UTENTE) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, ricevimento.getCognome());
            pstmt.setString(2, ricevimento.getNome());
            pstmt.setString(3, ricevimento.getData());
            pstmt.setString(4, ricevimento.getOra());
            pstmt.setString(5, ricevimento.getCodiceFiscale());
            pstmt.setString(6, this.tipoDiUtente); // Inserisce il tipo di utente nel database

            pstmt.executeUpdate();
        }
    }

    // Metodo per ottenere tutti i ricevimenti per il tipo di utente specificato
    @Override
    public List<Ricevimenti> getAllRicevimenti() throws SQLException {
        String sql ="SELECT * FROM Ricevimenti WHERE TIPO_DI_UTENTE = ?";

        List<Ricevimenti> ricevimenti = new ArrayList<>();

        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, this.tipoDiUtente); // Filtra i dati per tipo di utente
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    ricevimenti.add(new Ricevimenti(
                            rs.getString("COGNOME"),
                            rs.getString("NOME"),
                            rs.getString("DATA"),
                            rs.getString("ORA"),
                            rs.getString("CODICE_FISCALE")));
                }
            }
        }

        return ricevimenti;
    }

    @Override
    public void deleteRicevimento(String codiceFiscale) throws SQLException {
        String sql = "DELETE FROM Ricevimenti WHERE CODICE_FISCALE = ? AND TIPO_UTENTE = ?";
        
        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, codiceFiscale);
            pstmt.setString(2, this.tipoDiUtente); // Filtra il tipo di utente durante l'eliminazione
            pstmt.executeUpdate();
        }
    }

	@Override
	public void addRicevimento(Visiti visita) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	
}
