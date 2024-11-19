package servizi;

import java.sql.SQLException;

import DataBase.RicevimentoDAO;
import DataBase.UserDAOimp;
import dominio.Dottore;
import dominio.User;
import dominio.Visiti;
import view.HomePageDentista;
import view.HomePageOculista;
import view.HomePageOrtopedico;


public class Servizi {
	
	public Dottore dottore;
	public RicevimentoDAO ricDao;
	public UserDAOimp userdaoimp;
	
	
	public void ricevimento(Visiti visita) throws SQLException {
		ricDao.addRicevimento(visita);
		dottore.AddRicevimenti(visita);
	}
	
	
	
	public boolean serverificaCredenziali(String email, String password, String Tipo_utente, String ID) {
		
		return userdaoimp.verificaCredenziali(email, password, Tipo_utente, ID);
		
		
	}
	
	
	public boolean serRegisUtente(User user) {
		return userdaoimp.registraUtente(user);
	}
	
	public String sergetnomedottore(String nome) {
		
		return userdaoimp.getNomeDottore(nome);
		
	}
	
	public void homePage(String Tipo_utente, String nomeDottore) {
		switch (Tipo_utente) {
		case "ORTOPEDICO":
		 new HomePageDentista(nomeDottore, Tipo_utente).setVisible(true);
		 break;
		case "OCULISTA":
		 new HomePageOculista(nomeDottore, Tipo_utente).setVisible(true);
		 break;
		case "DENTISTA":
		 new HomePageOrtopedico(nomeDottore, Tipo_utente).setVisible(true);
		 break;
		}
		 
	}
}
