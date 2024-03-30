package application.user;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import application.login.LoginController;
import application.register.RegisterController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class UserController extends RegisterController implements Initializable {

	@FXML
	private AnchorPane pane;
	
	@FXML
	private Text info;

	@FXML
	private Button bestaetigen, zurueck, hinzufuegen;

	private ArrayList<User> user = new ArrayList<>();

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		user.addAll(RegisterController.userList);
		user();
	}
	
	
	@FXML
	private void buttonZurueck(ActionEvent e) {

		Main.oeffneFenster("/application/mainWindow/MainWindow.fxml", "Lagerbestand", "/application/mainWindow/mainWindow.css");
		Main.schliesseFenster(e);
	}
	
	
	@FXML
	private void buttonHinzufuegen(ActionEvent e) {
		
		Main.oeffneFenster("/application/register/Register.fxml", "Registrieren", "/application/register/register.css");
		Main.schliesseFenster(e);
	}

	
	
	@FXML
	private void bestaetige(ActionEvent e) {
		
		RegisterController.userList.clear();

		for (int i = 0; i < user.size(); i++) {

			if (user.get(i) != null) {

				RegisterController.userList.add(user.get(i));
			}
		}
		
		speicherUser();
		
		Main.oeffneFenster("/application/user/User.fxml", "Benutzer", "/application/user/user.css");
		Main.schliesseFenster(e);
	}

	
	
	
	private void user() {

		TextField b[] = new TextField[RegisterController.userList.size()];
		Button btn[] = new Button[RegisterController.userList.size()];
		Button btn2[] = new Button[RegisterController.userList.size()];

		for (int i = 0; i < RegisterController.userList.size(); i++) {

			pane.getChildren().remove(b[i]);
			pane.getChildren().remove(btn[i]);

			pane.setMinSize(280	, 85 + (40 * (i)));
			
			b[i] = new TextField(RegisterController.userList.get(i).getVorname() + 
					" " + RegisterController.userList.get(i).getNachname());
			b[i].setLayoutX(10);
			b[i].setLayoutY(30 + (40 * (i)));

			btn[i] = new Button("-");
			btn[i].setMinSize(20, 10);
			btn[i].setLayoutX(215);
			btn[i].setLayoutY(30 + (40 * (i)));

			btn2[i] = new Button("+");
			btn2[i].setMinSize(20, 10);
			btn2[i].setLayoutX(265);
			btn2[i].setLayoutY(30 + (40 * (i)));

			pane.getChildren().add(b[i]);
			pane.getChildren().add(btn[i]);
			pane.getChildren().add(btn2[i]);

			int o = i;

			// -
			btn[i].setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					
					if (LoginController.userID == o) {

					} else {

						if (!b[o].isDisable()) {

							b[o].setDisable(true);
							user.set(o, null);
						}
					}
				}
			});

			// +
			btn2[i].setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {

					if (b[o].isDisable()) {

						b[o].setDisable(false);
						user.set(o, RegisterController.userList.get(o));
					}
				}
			});
			
			b[i].setEditable(false);
		}
	}
}
