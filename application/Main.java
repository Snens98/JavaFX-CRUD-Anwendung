package application;
	
import java.io.IOException;

import application.register.RegisterController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	
	private RegisterController register = new RegisterController();
		
	public static void oeffneFenster(String fxmlDatei, String name, String stylesheet) {

		try {
			FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlDatei));
			
			Parent root = (Parent) fxmlLoader.load();
			root.getStylesheets().add(Main.class.getResource(stylesheet).toExternalForm());
		
			Stage stage = new Stage();
			stage.setTitle(name);
			stage.setScene(new Scene(root));
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static void schliesseFenster(ActionEvent event) {

		Stage stage1 = (Stage) ((Button) event.getSource()).getScene().getWindow();
		stage1.close();
	}
	
	
	
	@Override
	public void start(Stage primaryStage) {
		try {		
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/application/login/Login.fxml"));
			Scene scene = new Scene(root,250,400);
			scene.getStylesheets().add(Main.class.getResource("/application/login/login.css").toExternalForm());
			
			primaryStage.setTitle("Login");

			primaryStage.setScene(scene);
			primaryStage.show();
						
		} catch(Exception e) {;
			e.printStackTrace();
		}
		register.ladeUser();
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
