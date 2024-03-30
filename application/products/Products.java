package application.products;

import java.io.Serializable;

public class Products implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int menge;
	private Category kategorie;
	private double preis;	
	
	public Products() {
		
	}
	
	public Products(int id, String name, int menge, double preis, Category cat) {
		
		this.id = id;
		this.name = name;
		this.menge = menge;
		this.preis = preis;
		this.kategorie = cat;
	}
	
	public double getPreis() {
		return preis;
	}
	public void setPreis(double preis) {
		this.preis = preis;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMenge() {
		return menge;
	}
	public void setMenge(int menge) {
		this.menge = menge;
	}
	public Category getKategorie() {
		return kategorie;
	}
	public void setKategorie(Category kategorie) {
		this.kategorie = kategorie;
	}
}
