package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Controller.DottoreController;


@SuppressWarnings("serial")
public class Login extends JFrame {

    // Componenti dell'interfaccia
	private DottoreController controller;
	
	private JButton loginButton = new JButton("ACCESSO");
    private JButton registrazioneButton = new JButton("REGISTRAZIONE");
    private JLabel emailLabel = new JLabel("E-MAIL");
    private JLabel passwordLabel = new JLabel("PASSWORD");
    private JTextField emailField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JCheckBox showPasswordCheckbox = new JCheckBox("MOSTRA LA PASSWORD");
    private JLabel loginFormLabel = new JLabel("BENVENUTO");
    
    public JRadioButton ortopedicoRadio = new JRadioButton("ORTOPEDICO");
    public JRadioButton oculistaRadio = new JRadioButton("OCULISTA");
    public JRadioButton dentistaRadio = new JRadioButton("DENTISTA");

    
    private JTextField idSpecializzazioneField = new JTextField(); // Campo ID specializzazione
    private JLabel IdSpecializzazioneField = new JLabel("ID");

    private ButtonGroup radioGroup = new ButtonGroup();
    private JPanel panel = new JPanel(null);

    public Login() {
        // Impostazioni base della finestra
        this.setTitle("ACCESSO");
        this.setSize(1000, 1000);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.add(panel);

        // Layout e posizionamento componenti
        layoutComponents();

        // Imposta visibilità iniziale
        idSpecializzazioneField.setVisible(false);
        IdSpecializzazioneField.setVisible(false);
        
        addRadioButton();
        openreg();
        togglePasswordVisibility();
        handleLogin();
       
    }

    private void layoutComponents() {
        // Configurazione layout e componenti
        panel.setBounds(0, 0, 1000, 1000);
        panel.setBackground(Color.decode("#89CFF0"));

        loginFormLabel.setBounds(350, 200, 600, 100);
        loginFormLabel.setFont(new Font("ALGERIAN", Font.BOLD, 50));

        emailLabel.setBounds(180, 450, 120, 35);
        emailLabel.setFont(new Font("BODONI MT", Font.BOLD, 30));
        emailField.setBounds(300, 450, 400, 35);

        passwordLabel.setBounds(135, 500, 180, 35);
        passwordLabel.setFont(new Font("BODONI MT", Font.BOLD, 30));
        getPasswordField().setBounds(300, 500, 400, 35);
        
        getShowPasswordCheckbox().setBounds(750, 500, 170, 20);
        getShowPasswordCheckbox().setBackground(Color.decode("#89CFF0"));
        
        IdSpecializzazioneField.setBounds(210, 580, 120, 35);
        IdSpecializzazioneField.setFont(new Font("BODONI MT", Font.BOLD, 30));
        idSpecializzazioneField.setBounds(300, 580, 400, 35);

        loginButton.setBounds(550, 650, 150, 25);
        loginButton.setBackground(Color.LIGHT_GRAY);
        loginButton.setFont(new Font("Congenial black", Font.BOLD, 20));
        
        registrazioneButton.setBounds(300, 650, 200, 25);
        registrazioneButton.setBackground(Color.LIGHT_GRAY);
        registrazioneButton.setFont(new Font("Congenial black", Font.BOLD, 20));

        ortopedicoRadio.setBounds(200, 550, 200, 25);
        ortopedicoRadio.setBackground(Color.decode("#89CFF0"));
        oculistaRadio.setBounds(500, 550, 200, 25);
        oculistaRadio.setBackground(Color.decode("#89CFF0"));
        dentistaRadio.setBounds(750, 550, 200, 25);
        dentistaRadio.setBackground(Color.decode("#89CFF0"));

        panel.add(loginFormLabel);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(getPasswordField());
        panel.add(getShowPasswordCheckbox());
        panel.add(loginButton);
        panel.add(registrazioneButton);
        panel.add(ortopedicoRadio);
        panel.add(oculistaRadio);
        panel.add(dentistaRadio);
        panel.add(idSpecializzazioneField);
        panel.add(IdSpecializzazioneField);

        radioGroup.add(ortopedicoRadio);
        radioGroup.add(oculistaRadio);
        radioGroup.add(dentistaRadio);
        
        
        

    }
    
    
	public void handleLogin() {
		this.setLoginListener( e -> {
			String email = this.getEmail();
			String password = this.getPassword();
			String specializzazione = this.getSpecialization();
			String idSpecializzazione = this.getSpecializationId();
			
			if (email.isEmpty() || password.isEmpty() || specializzazione.isEmpty() || idSpecializzazione.isEmpty()) {
				this.showMessage("Compila tutti i campi!");
                return;
            }
			
			 boolean loginRiuscito = controller.converificred(email, password, specializzazione, idSpecializzazione);

	            if (loginRiuscito) {
	                String nomeDottore = controller.congetnomedott(email);
	                this.showMessage("Login effettuato con successo, Benvenuto " + nomeDottore);
	                controller.conHomepage(idSpecializzazione, nomeDottore);
	                this.dispose();
	            } else {
	                this.showMessage("Credenziali non valide");
	            }
		});
		
	}
	

	
	 
		public void mostraLogin() {
			
			passwordLabel.setVisible(true);
	    }
		
		
		
		public void togglePasswordVisibility() {
			showPasswordCheckbox.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if (showPasswordCheckbox.isSelected()) {
			            passwordField.setEchoChar((char) 0);
			        } else {
			            passwordField.setEchoChar('*');
			        }
					return;
				}
			});
			
		}

	
	
	
	public void serHomepage(String Tipo_utente, String nomeDottore) {
		controller.conHomepage(Tipo_utente, nomeDottore);;
	}
	
	
	private void openreg() {
			registrazioneButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					new Registrazione().setVisible(true);
				}
			});
			
	
	}
        
	
     	
    
    private void addRadioButton() {
        ActionListener radioButtonListener = e -> {
            idSpecializzazioneField.setVisible(true);
            IdSpecializzazioneField.setVisible(true);
        };

        ortopedicoRadio.addActionListener(radioButtonListener);
        oculistaRadio.addActionListener(radioButtonListener);
        dentistaRadio.addActionListener(radioButtonListener);
    }
    
    // Getter per i dati inseriti dall'utente
    public String getEmail() {
        return emailField.getText();
    }

    public String getPassword() {
        return new String(getPasswordField().getPassword()).trim();
    }

    public String getSpecialization() {
        if (ortopedicoRadio.isSelected()) return "ORTOPEDICO";
        if (oculistaRadio.isSelected()) return "OCULISTA";
        if (dentistaRadio.isSelected()) return "DENTISTA";
        return "";
    }

    public String getSpecializationId() {
        return idSpecializzazioneField.getText().trim();
    }
    public void setSpecializationIdVisibility(boolean visible) {
        idSpecializzazioneField.setVisible(visible);
        IdSpecializzazioneField.setVisible(visible);
    }


    // Metodi per assegnare listener dal controller
    public void setLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public void setShowPasswordListener(ActionListener listener) {
        getShowPasswordCheckbox().addActionListener(listener);
        togglePasswordVisibility();
    }

    public void setRegistrationListener(ActionListener listener) {
        registrazioneButton.addActionListener(listener);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

	public JCheckBox getShowPasswordCheckbox() {
		return showPasswordCheckbox;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}
	
	public static void main(String[] args) {
        new Login().setVisible(true);
    }

	
}
