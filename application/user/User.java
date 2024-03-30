package application.user;

import java.io.Serializable;

public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String Vorname;
	private String Nachname;
	private String Postleitzahl;
	private String Wohnort;
	private String Straﬂe;
	private String Telefonnummer;
	private String EMail;
	private String Benutzername;
	private String Passwort;
	private boolean Admin;
	
	
	public User() {}
	
	public User(String benutzername, String passwort) {
		
		Benutzername = benutzername;
		Passwort = passwort;
	}

	public User(String vorname, String nachname, String postleitzahl, String wohnort, String straﬂe, String telefonnummer,String eMail, String benutzername, String passwort, boolean admin) {

		Vorname = vorname;
		Nachname = nachname;
		Postleitzahl = postleitzahl;
		Wohnort = wohnort;
		Straﬂe = straﬂe;
		Telefonnummer = telefonnummer;
		EMail = eMail;
		Benutzername = benutzername;
		Passwort = passwort;
		Admin = admin;
	}
	
	public String getVorname() {
		return Vorname;
	}
	public void setVorname(String vorname) {
		Vorname = vorname;
	}
	public String getNachname() {
		return Nachname;
	}
	public void setNachname(String nachname) {
		Nachname = nachname;
	}
	public String getPostleitzahl() {
		return Postleitzahl;
	}
	public void setPostleitzahl(String postleitzahl) {
		Postleitzahl = postleitzahl;
	}
	public String getWohnort() {
		return Wohnort;
	}
	public void setWohnort(String wohnort) {
		Wohnort = wohnort;
	}
	public String getStraﬂe() {
		return Straﬂe;
	}
	public void setStraﬂe(String straﬂe) {
		Straﬂe = straﬂe;
	}
	public String getTelefonnummer() {
		return Telefonnummer;
	}
	public void setTelefonnummer(String telefonnummer) {
		Telefonnummer = telefonnummer;
	}
	public String getEMail() {
		return EMail;
	}
	public void setEMail(String eMail) {
		EMail = eMail;
	}
	public String getBenutzername() {
		return Benutzername;
	}
	public void setBenutzername(String benutzername) {
		Benutzername = benutzername;
	}
	public String getPasswort() {
		return Passwort;
	}
	public void setPasswort(String passwort) {
		Passwort = passwort;
	}
	public boolean isAdmin() {
		return Admin;
	}
	public void setAdmin(boolean admin) {
		Admin = admin;
	}
}
