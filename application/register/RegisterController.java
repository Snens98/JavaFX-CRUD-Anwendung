package application.register;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import application.Main;
import application.file.FileManager;
import application.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class RegisterController implements Initializable{

	public static ArrayList<User> userList = new ArrayList<User>();
	
	private FileManager fileManager = new FileManager();
	
	
	@FXML
	private Button zurueck, registrieren;
	
	@FXML
	private TextField vorname, nachname, plz, wohnort, strasse, telefon, email, benutzername, passwort, passwortbes;
	
	@FXML
	private CheckBox admin;
	
	@FXML
	private Text info;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		admin.setSelected(false);
	}
	
	
	@FXML
	private void zurueckButton(ActionEvent e) {
		
		Main.oeffneFenster("/application/mainWindow/MainWindow.fxml", "Lagerbestand", "/application/mainWindow/mainWindow.css");
		Main.schliesseFenster(e);
	}
	
	
	@FXML
	private void benutzerAnlegen() {
		
		if (!textfelderBeschrieben()) {
			
			info.setText("Nicht alle Felder ausgefüllt!");
		}else{
			
			if(!gueltigeEingabe()) {
				
				info.setText("Ungültige Eingabe");
				
			}else if (benutzernameVergeben()) {

				info.setText("Der Benutzername ist vergeben!");
				
			}else if(userBereitsRegistriert()) {
				
				info.setText("Benutzer ist schon registriert!");
				
			}else if(!passwortHatMinZeichen()) {
				
				info.setText("Das Passwort muss mindestens 6 Zeichen haben.");
				
			}else if(!benutzernameHatMinZeichen()) {
				
				info.setText("Der Benutzername muss mindestens 4 Zeichen haben.");
				
			}else if(!passwortUebereinstimmung()) {
				
				info.setText("Das Passwort stimmt nicht überein.");

			} else {

				User user = new User();

				user.setVorname(vorname.getText());
				user.setNachname(nachname.getText());
				user.setPostleitzahl(plz.getText());
				user.setWohnort(wohnort.getText());
				user.setStraße(strasse.getText());
				user.setTelefonnummer(telefon.getText());
				user.setEMail(email.getText());
				user.setBenutzername(benutzername.getText());
				user.setPasswort(passwort.getText());
				user.setAdmin(admin.isSelected());

				userList.add(user);
				speicherUser();
				leereTextfelder();
				
				info.setText("Benutzer registriert!");
			}
		}		
	}

	
	
	private boolean gueltigeEingabe() {
		 
		return telefon.getText().matches("[0-9]*") && plz.getText().matches("[0-9]*");		
	}
	
	
	private void leereTextfelder() {
		
		vorname.setText("");
		nachname.setText("");
		plz.setText("");
		wohnort.setText("");
		strasse.setText("");
		telefon.setText("");
		email.setText("");
		benutzername.setText("");
		passwort.setText("");
		passwortbes.setText("");
	}
	
	
	
	private boolean textfelderBeschrieben() {
		
		return !(vorname.getText().isEmpty() || nachname.getText().isEmpty() || plz.getText().isEmpty() 
				|| wohnort.getText().isEmpty() || strasse.getText().isEmpty() || telefon.getText().isEmpty() 
				|| email.getText().isEmpty() || benutzername.getText().isEmpty() || passwort.getText().isEmpty() 
				|| passwortbes.getText().isEmpty());
	}
	
	
	
	private boolean benutzernameHatMinZeichen() {
		
		return (benutzername.getText().length() >= 4);
	}
	
	
	
	private boolean passwortHatMinZeichen() {
		
		return (passwort.getText().length() >= 6); 
	}
	
	
	
	private boolean passwortUebereinstimmung() {
		
		return passwort.getText().equals(passwortbes.getText());
	}
	
	
	
	private boolean benutzernameVergeben() {
		
		for(int i = 0; i < userList.size(); i++) {
		
			if(benutzername.getText().equals(userList.get(i).getBenutzername())) {
				
				return true;
			}
		}
		return false;
	}
	
	
	
	
	private boolean userBereitsRegistriert() {
		
		for(int i = 0; i < userList.size(); i++) {
		
			if(vorname.getText().equals(userList.get(i).getVorname()) && nachname.getText().equals(userList.get(i).getNachname())) {
				
				return true;
			}
		}
		return false;
	}
	
	
	
	public void speicherUser() {
	
		fileManager.erstelleDatei("userdata.txt");

		try {
			
			FileOutputStream fileOut = new FileOutputStream(fileManager.file("userdata.txt"));
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			
			objectOut.writeObject(userList);
			objectOut.flush();
			objectOut.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public void ladeUser() {

		userList.clear();

		if (fileManager.file("userdata.txt").isFile() && fileManager.file("userdata.txt").length() != 0) {

			try {
				
				FileInputStream fileIn = new FileInputStream(fileManager.file("userdata.txt"));
				ObjectInputStream objectIn = new ObjectInputStream(fileIn);

				userList = (ArrayList<User>) objectIn.readObject();
				objectIn.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(RegisterController.userList.isEmpty()){
			
			User admin = new User("Willibert", "Einhorn", "39287", "Elend", "Prügelweg 2", "012836239", "Einhorn.horn@Mail.de", "admin", "nimda", true);
			userList.add(admin);
		}
	}
}
