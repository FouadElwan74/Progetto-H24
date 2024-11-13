package servizi;

import java.sql.SQLException;

import DataBase.RicevimentoDAO;
import dominio.Dottore;
import dominio.Visiti;


public class Servizi {
	
	public Dottore dottore;
	public RicevimentoDAO ricDao;
	
	
	public void ricevimento(Visiti visita) throws SQLException {
	
		ricDao.addRicevimento(visita);
		dottore.AddRicevimenti(visita);
	}
	
	
	
}
