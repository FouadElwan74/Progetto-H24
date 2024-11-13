package Controller;

import view.Registrazione;
import DataBase.UserDAO;
import view.Login;
import view.HomePageDentista;
import view.HomePageOculista;
import view.HomePageOrtopedico;
import dominio.User;
import dominio.Visiti;
import servizi.Servizi;

import java.sql.SQLException;

import javax.swing.JFrame;


public class DottoreController {
	
	public Servizi servizio;
	@SuppressWarnings("rawtypes")
	public UserDAO userDAO;
	public Login loginView;
	
	//@SuppressWarnings("unused")
	//private REGDAOimp registrazioneDAO;
	
	@SuppressWarnings("rawtypes")
	public DottoreController(UserDAO userDAO) {
		this.userDAO = userDAO;
		this.loginView = new Login();
		
		//this.registrazioneDAO = new REGDAOimp();
		setupListeners();
	}
	
	private void setupListeners() {
        // Listener per il bottone di login
        loginView.setLoginListener(e -> handleLogin());
 
        // Listener per il checkbox "Mostra Password"
        loginView.setShowPasswordListener(e -> togglePasswordVisibility());

        // Listener per il bottone di registrazione
        loginView.setRegistrationListener(e -> openRegistration());
        
    }
	
	public void handleLogin() {
		loginView.setLoginListener( e -> {
			String email = loginView.getEmail();
			String password = loginView.getPassword();
			String specializzazione = loginView.getSpecialization();
			String idSpecializzazione = loginView.getSpecializationId();
			
			if (email.isEmpty() || password.isEmpty() || specializzazione.isEmpty() || idSpecializzazione.isEmpty()) {
                loginView.showMessage("Compila tutti i campi!");
                return;
            }
			
			 boolean loginRiuscito = userDAO.verificaCredenziali(email, password, specializzazione, idSpecializzazione);

	            if (loginRiuscito) {
	                String nomeDottore = userDAO.getNomeDottore(email);
	                loginView.showMessage("Login effettuato con successo, Benvenuto " + nomeDottore);
	                homePage(specializzazione, nomeDottore);
	                loginView.dispose();
	            } else {
	                loginView.showMessage("Credenziali non valide");
	            }
		});
		
	}
	
	private void openRegistration() {
        // Implementazione apertura finestra di registrazione
        new Registrazione().setVisible(true);
    }
	
	private void togglePasswordVisibility() {
		loginView.togglePasswordVisibility();
    }
	
	private void homePage(String Tipo_utente, String nomeDottore) {
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
	
	
	public boolean registraDottore(String email, String password, String nome, String cognome, String codiceFiscale, String dataNascita, String indirizzio, String Tipo_utente, String ID) throws SQLException {
	    if (email.isEmpty() || password.isEmpty() || nome.isEmpty() || cognome.isEmpty()) {
	        return false; // Non registrare se i campi obbligatori sono vuoti
	    }
	    
	    // Logica per la registrazione nel database
		User user = new User(email, password, nome, cognome, codiceFiscale, dataNascita, indirizzio, Tipo_utente, ID);
		System.out.println("Registrazione utente completata con successo");
		userDAO.registraUtente(user); // Chiama il DAO per inserire l'utente nel database
		return true; // Registrazione riuscita
	}

	
	
	
	 public void chiudiRegistrazione(JFrame frame) {
	        frame.dispose();  // Chiude la finestra corrente
	        System.out.println("Finestra chiusa.");
	    }
	
	public void mostraLogin() {
		
        loginView.setVisible(true);
    }
	
	
	public void addRicevimento(Visiti visita) throws SQLException{
		
		servizio.ricevimento(visita);
	
	
	}

	
	

}
