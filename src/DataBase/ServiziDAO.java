package DataBase;

import java.sql.SQLException;
import java.util.List;

import dominio.Servizi;

public interface ServiziDAO {
	void addServizi(Servizi servizi) throws SQLException;
    void deleteServizi(String codiceFiscale) throws SQLException;
    List<Servizi> searchByCodiceFiscale(String codiceFiscale) throws SQLException;
    List<Servizi> getAllServices() throws SQLException;

	
	}
    
