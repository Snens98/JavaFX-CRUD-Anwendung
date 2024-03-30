package application.mainWindow;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import application.Main;
import application.file.FileManager;
import application.login.LoginController;
import application.products.Category;
import application.products.Products;
import application.register.RegisterController;
import application.sort.SortAndSearch;
import application.user.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class MainWindowController extends SortAndSearch implements Initializable {
	
	private FileManager fileManager = new FileManager();
	private RegisterController register = new RegisterController();

	
	@FXML
	private TableView<Products> table = new TableView<>();

	@FXML
	private TextField id, name, preis, menge, sucheTextfeld, email, altespasswort, neuespasswort;

	@FXML
	private ChoiceBox<Category> cat;

	@FXML
	private Button bearbeiten, benutzerListe, loeschen, hinzufuegen, abmelden, sortName, sortID, sortPreis, sortMenge, sortKatbtn, registrieren, speichern;
	
	@FXML
	private Text benutzer, info, datum, daten, passwortaendern;
	
	@FXML
	private Rectangle benutzerFenster;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		createTable(table);
		UpdateIDNummer();

		cat.setItems(FXCollections.observableArrayList(Category.values()));
		cat.setValue(Category.Notebook);

		ladeTabelle();
		info.setText("");

		zeigeUserData();
		listeZwischenspeichern(products);
		
		deaktivierteRegistrierung();
		deaktivierteBenutzerButton();
		zeigeDatumUhrzeit();
		
		altespasswort.setVisible(false);
		neuespasswort.setVisible(false);
		speichern.setVisible(false);
	}	
	
		
	@FXML
	private void passwortAendern() {
		
		altespasswort.setVisible(true);
		neuespasswort.setVisible(true);
		speichern.setVisible(true);
	}
	
	
	@FXML
	private void passwortSpeichern() {

		if(altespasswort.getText().equals(RegisterController.userList.get(LoginController.userID).getPasswort())){
		
			if(!neuespasswort.getText().isEmpty() && neuespasswort.getText().length() >= 6) {
				
				RegisterController.userList.get(LoginController.userID).setPasswort(neuespasswort.getText());		
				System.out.println(RegisterController.userList.get(LoginController.userID).getPasswort());
					
				register.speicherUser();
				register.ladeUser();
			}
		}
		altespasswort.setVisible(false);
		neuespasswort.setVisible(false);
		speichern.setVisible(false);
	}
	
	
	
	@FXML
	private void SpielerRegistrieren(ActionEvent e) {
				
		if(RegisterController.userList.get(LoginController.userID).isAdmin()) {
		
			Main.oeffneFenster("/application/register/Register.fxml", "Registrieren", "/application/register/register.css");
			Main.schliesseFenster(e);
		}
	}

	
	
	@FXML
	private void buttonAbmelden(ActionEvent e) {

		Main.oeffneFenster("/application/login/Login.fxml", "Login", "/application/login/login.css");
		Main.schliesseFenster(e);
	}

	
	@FXML
	private void buttonBenutzer(ActionEvent e) {

		if (RegisterController.userList.get(LoginController.userID).isAdmin()) {

			Main.oeffneFenster("/application/user/User.fxml", "Benutzer", "/application/user/user.css");
			Main.schliesseFenster(e);
		}
	}
	
	
	@FXML
	private void suchtexfeld() {

		suche(products, sucheTextfeld.getText());
	}
	
	@FXML
	private void sortMenge() {

		sortMenge(products);
	}

	@FXML
	private void sortName() {

		sortName(products);
	}

	@FXML
	private void sortID() {

		sortID(products);
	}

	@FXML
	private void sortPreis() {

		sortPreis(products);
		mergeSort(products);
	}

	
	@FXML
	private void sortKat() {
		
		sortkat(products);
	}
	
	
	private void zeigeDatumUhrzeit() {
		
		SimpleDateFormat datumFormat = new SimpleDateFormat("EEEE, \nd. MMM yyyy");
        Date aktuellesDatum = new Date();
        datum.setText(datumFormat.format(aktuellesDatum));
	}
	
	
	private void deaktivierteRegistrierung() {
		
		if(!RegisterController.userList.get(LoginController.userID).isAdmin()) {
			
			registrieren.setDisable(true);
		}else {
			registrieren.setDisable(false);
		}
	}
	
	
	private void deaktivierteBenutzerButton() {
		
		if(!RegisterController.userList.get(LoginController.userID).isAdmin()) {
			
			benutzerListe.setDisable(true);
		}else {
			benutzerListe.setDisable(false);
		}
	}
	

	private void zeigeUserData() {
		
		User user = RegisterController.userList.get(LoginController.userID);
		String vorname = user.getVorname();
		String nachname = user.getNachname();
		String admin = user.isAdmin() ? "Administrator" : "Mitarbeiter";
		
		benutzer.setText(vorname + "\n" + nachname);	
		
		email.setText("\n\n" + user.getEMail());
		
		daten.setText("\n" + admin + "\n\n" + "Benutzername: \n" + user.getBenutzername());

	}
	
	
	
	
	private void UpdateIDNummer() {

		id.setText((products.size() + 1) + "");
	}
	
	
	
	
	private boolean textfelderSindLeer() {

		return (id.getText().isEmpty() || name.getText().isEmpty() || preis.getText().isEmpty() || menge.getText().isEmpty());
	}

	
	
	
	private Products neuesProdukt() {

		Products produkt = new Products(Integer.parseInt(id.getText()), name.getText(),
										Integer.parseInt(menge.getText()), 
										Double.parseDouble(preis.getText()), cat.getValue());
		return produkt;
	}

	
	
	
	@FXML
	private void produktHinzufuegen() {

		if (!textfelderSindLeer()) {

			if (gueltigeEingabe()) {

				info.setText("");
				products.add(neuesProdukt());
				
				UpdateIDNummer();
				speicherTabelle();
			} else {
				info.setText("Ungültige Eingabe");
			}
		}
	}

	
	
	
	@FXML
	private void produktLoeschen(ActionEvent e) {

		info.setText("");

		Products n = (Products) table.getSelectionModel().getSelectedItem();
		products.remove(n);

		speicherTabelle();
	}

	
	
	
	@FXML
	private void produktBearbeiten() {

		if (table.getSelectionModel().getSelectedItem() != null) {

			Products p = (Products) table.getSelectionModel().getSelectedItem();

			id.setText(p.getId() + "");
			name.setText(p.getName());
			preis.setText(p.getPreis() + "");
			menge.setText(p.getMenge() + "");
			cat.setValue(p.getKategorie());
		}
	}

	
	
	@FXML
	private void produktBearbeitenButton() {

		if (table.getSelectionModel().getSelectedItem() != null) {

			int i = table.getSelectionModel().getSelectedIndex();
			Products p = new Products();

			if (gueltigeEingabe()) {

				info.setText("");

				p.setId(Integer.parseInt(id.getText()));
				p.setName(name.getText());
				p.setMenge(Integer.parseInt(menge.getText()));
				p.setPreis(Double.parseDouble(preis.getText()));
				p.setKategorie(cat.getValue());

				products.set(i, p);

				speicherTabelle();

			} else {

				info.setText("Ungültige Eingabe");
			}
		}
	}
	
	
	
	private boolean gueltigeEingabe() {
		 
		return (id.getText().length() < 8 && menge.getText().length() < 8 && (id.getText().matches("[0-9]*") 
				&& menge.getText().matches("[0-9]*") && (preis.getText().matches("[0-9]*\\.[0-9]*") 
				|| preis.getText().matches("[0-9]*"))));
	}

	
	
	
	private void ladeTabelle() {

		if (fileManager.file("tabledata.txt").isFile() && fileManager.file("tabledata.txt").length() != 0) {

			try {

				FileInputStream fileIn = new FileInputStream(fileManager.file("tabledata.txt"));
				ObjectInputStream objectIn = new ObjectInputStream(fileIn);

				Products produkt;
				products.clear();

				while(true) {
				
					try {
						produkt = (Products) objectIn.readObject();
						products.add(produkt);
						
					} catch (Exception e) {
						break;
					}
				}

				objectIn.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	
	
	private void speicherTabelle() {

		fileManager.erstelleDatei("tabledata.txt");

		try {

			FileOutputStream fileOut = new FileOutputStream(fileManager.file("tabledata.txt"));
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

			for (int i = 0; i < products.size(); i++) {

				objectOut.writeObject(products.get(i));
			}

			objectOut.flush();
			objectOut.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
