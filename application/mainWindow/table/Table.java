package application.mainWindow.table;

import java.util.ArrayList;

import application.products.Category;
import application.products.Products;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Table {

	/*
	 * https://docs.oracle.com/javafx/2/ui_controls/table-view.htm
	 */

	public ObservableList<Products> products;
	
	public void createTable(TableView<Products> table) {
		
		TableColumn<Products, Integer> id = new TableColumn<>("ID");
		id.setPrefWidth(25);
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		id.setSortable(false);

		TableColumn<Products, String> name = new TableColumn<>("Name");
		name.setPrefWidth(150);
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		name.setSortable(false);

		TableColumn<Products, Double> menge = new TableColumn<>("Menge");
		menge.setPrefWidth(50);
		menge.setCellValueFactory(new PropertyValueFactory<>("menge"));
		menge.setSortable(false);
		
		TableColumn<Products, Double> preis = new TableColumn<>("Preis");
		preis.setPrefWidth(50);
		preis.setCellValueFactory(new PropertyValueFactory<>("preis"));
		preis.setSortable(false);

		TableColumn<Products, Category> kat = new TableColumn<>("Kategorie");
		kat.setPrefWidth(100);
		kat.setCellValueFactory(new PropertyValueFactory<>("kategorie"));
		kat.setSortable(false);
		
		table.setItems(produkte());
		
		table.getColumns().add(id);
		table.getColumns().add(name);
		table.getColumns().add(menge);
		table.getColumns().add(preis);
		table.getColumns().add(kat);
	}
	


	
	public ArrayList<Products> speicher;
	
	public void listeZwischenspeichern(ObservableList<Products> products) {
		
		speicher = new ArrayList<>();
		
		for(int i = 0; i < products.size(); i++) {
			
			speicher.add(products.get(i));
		}
	}
	
	
	
	
	private ObservableList<Products> produkte(){
				
		products = FXCollections.observableArrayList();
		
		products.add(new Products(1, "RTX 3090", 0, 3300, Category.Grafikkarte));
		products.add(new Products(2, "GTX 1080", 622, 302, Category.Grafikkarte));
		products.add(new Products(3, "Lenovo Legion 16 Pro", 14, 1400, Category.Notebook));
		products.add(new Products(4, "Ryzen 5800H", 67, 459, Category.Prozessor));
		products.add(new Products(5, "Iphone 10", 2021, 799, Category.Smartphone));	
		products.add(new Products(6, "Nokia X20", 21, 299, Category.Smartphone));	
		products.add(new Products(7, "sony ericsson xperia arc s", 102, 479, Category.Smartphone));	
		products.add(new Products(8, "Intel Atom z8350", 3913, 49, Category.Prozessor));
		
		return products;		
	}
}