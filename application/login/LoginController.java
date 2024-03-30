package application.login;

import application.Main;
import application.register.RegisterController;
import application.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginController {
	
	public static int userID = -1;
	
	
	@FXML
	private TextField textBenutername, textPasswort;

	@FXML
	private Button loginButton, schliessenButton;
	
	@FXML
	private Text info, nichtRegistriert;
	

	@FXML
	private void buttonLogin(ActionEvent e) {

		if(!textfelderBeschrieben()) {
			
			info.setText("Nicht alle Textfelder ausgefüllt");
			
		}else if(benutzerIstRegistriert()) {
		
			info.setText("Login...");

			Main.oeffneFenster("/application/mainWindow/MainWindow.fxml",
					"Lagerbestand", "/application/mainWindow/mainWindow.css");
			Main.schliesseFenster(e);
			
		} else {
			
			info.setText("Benutzer ist nicht registriert");
		}
	}
	

	@FXML
	private void nichtRegistriertBtn() {
		
		nichtRegistriert.setText("Kontaktieren Sie einen Administrator");
	}
	
	
	
	@FXML
	private void nichtRegistriertBtnExit() {
		
		nichtRegistriert.setText("Nicht registriert?");
	}

	
	
	@FXML
	private void buttonSchliessen(ActionEvent e) {

		Main.schliesseFenster(e);
	}

	
	
	private boolean textfelderBeschrieben() {
		
		return !(textBenutername.getText().isEmpty() || textPasswort.getText().isEmpty());
	}
	
	
	
	private boolean benutzerIstRegistriert() {

		for (int i = 0; i < RegisterController.userList.size(); i++) {

			if ((textBenutername.getText().equals(RegisterController.userList.get(i).getBenutzername()))
					&& (textPasswort.getText().equals(RegisterController.userList.get(i).getPasswort()))) {
				
				User aktuellerUser = RegisterController.userList.get(i);
				userID = RegisterController.userList.indexOf(aktuellerUser);
				return true;
			}
		}
		return false;
	}
}
