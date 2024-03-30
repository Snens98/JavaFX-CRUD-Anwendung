module Lagerbestand {
	
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires java.desktop;
	
	opens application.login;
	opens application.mainWindow;
	opens application.register;
	opens application.user;
	opens application.products;
	
	opens application to javafx.graphics, javafx.fxml;
}
