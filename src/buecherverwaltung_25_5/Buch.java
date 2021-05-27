package buecherverwaltung_25_5;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

// Konstruktor von Buch-Objekt:
// Serializable: erlaubt, dass dieses Objekt serialisiert wird
// sonst entsteht eine NotSerializableException
public class Buch implements Serializable {
	private String erscheinungsjahr;
	private String titel;
	private String isbn;
	private String autor;
	private String verlag;
	private String beschreibung;
	private String seitenanzahl;
	
	//Konstruktor, der die Daten aus der Methode leseAus() entgegennimmt
	// und an die Attribute des eigentlichen Konstruktor des Objektes übergibt:
	public Buch(String[] leseAus) {
		this.titel = leseAus[0];
		this.isbn = leseAus[1];
		this.autor = leseAus[2];
		this.verlag = leseAus[3];
		this.erscheinungsjahr = leseAus[4];
		this.seitenanzahl = leseAus[5];
		this.beschreibung = leseAus[6];
	}
	
	//Konstruktor, der nur die ISBN aus der Methode leseAus() entgegennimmt
	// und an das Attribut des eigentlichen Konstruktor des Objektes übergibt:
	//public Buch(String[] leseAus) {
	//	this.isbn = leseAus[1];
	//}



//toString()-Methode überschreiben, wegen der späteren Ausgabe:
	@Override
	public String toString() {
		return "Buecher [erscheinungsjahr=" + erscheinungsjahr + ", titel=" + titel + ", isbn=" + isbn + ", autor="
				+ autor + ", verlag=" + verlag + ", beschreibung=" + beschreibung + ", seitenanzahl=" + seitenanzahl
				+ "]";
	}
}
