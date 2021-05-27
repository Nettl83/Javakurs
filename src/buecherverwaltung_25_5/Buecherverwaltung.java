package buecherverwaltung_25_5;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Buecherverwaltung {

	private Scanner scanner = new Scanner(System.in);
	
	private String FILE = "data.ser";
	
	//Set von Büchern:
	private Set<Buch> buecher;
	
	public void start() {

		if(!readFromFile()) {
			System.out.println("Altdaten Buch konnten nicht gelesen werden.");
		}
		
		app: while(true) {
			System.out.print("Eingabe: ");
			
			switch(scanner.nextLine().trim().toLowerCase()) {
				
				// neues Buch anlegen:
				case "n": 
					System.out.println("Neu");
					System.out.print("Neues Buch: ");
					// Set, dass alle neuen Bücher anhand Methode leseAus() aufnimmt:
					buecher.add(new Buch(leseAus()));
					System.out.println(buecher);	// Testausgabe				
					break;
				
				// vorhandene Bücher anzeigen:
				case "l": 
					System.out.println("Liste:");
					if(buecher.isEmpty()) {
				
						System.out.println("Keine Bücher vorhanden.");
					}
					else {
						buecher.forEach(t -> System.out.println(t));
					}
					break;
					
				// Buch suchen:
				case "s":
				//TODO Methode zum Auslesen einzelner Scanner-Eingaben, Attribut für Attribut, in sep. Methode								
				
				// Abbruch der Eingaben/des Programms:
				case "q": 
					break app;	
				
				// Standardausgabe:
				default:
					System.out.println("Falsche Eingabe.");
					
			}
		}
		
		if(!saveToFile()) {
			System.out.println("Daten konnten nicht gespeichert werden.");
		}
		System.out.println("Programmende");
	}
	
	
	//Methode Daten einlesen anhand Scanner-Eingaben:
	private String[] leseAus() {
		// Array erzeugen, dass die Eingabetexte pro Objektattribut enthält:
		String[] eingabetext = {"Bitte geben Sie Titel des Buches ein: ", 
								"Bitte geben Sie die ISBN ein: ",
								"Bitte den Autor eingeben: ",
								"Bitte den verlag angeben: ",
								"Bitte das Erscheinungsjahr angeben: ",
								"Seitenanzahl angeben: ",
								"Beschreibung angeben: "};
						
		//TODO ENUM anlegen oder Labels
		
		// Array erzeugen, dass die eingegebenen Werte für das Buch entgegennimmt:
		String[] buchdatensatz = new String[eingabetext.length];
		
		// Schleife zum Durchlaufen und Erzeugen der Eingaben:
		//TODO: Prüfung auf leere Eingabe und falsche Eingabe
		for (int i = 0; i < eingabetext.length; i++)
			{
			System.out.println(eingabetext[i]);
			String eingabewert = scanner.nextLine();
			//TODO Prüfen der Eingaben
			//System.out.println(eingabetext[i] + ": " + eingabewert);
			buchdatensatz[i] = eingabewert;
			}
		return buchdatensatz;	// Rückgabewert an den Konstruktor "public Buch(String[] leseAus) {..." in Buch.java
		}

	
	//TODO Methode "Suche" im Datensatz
	// Suche:
	//default void forEach(Consumer<? super T> action)
		
	private boolean saveToFile() {
		
		// Daten aus Set<Buch> in Datei speichern:
		// Snippet
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE));
			out.writeObject(buecher);
			out.close();
			return true;
		}
		catch (IOException e) {
			//..
			return false;
		}
	}
	
	// Daten aus Datei lesen:
	private boolean readFromFile() {
		
		// Liest Objekt aus Dateien
		// Snippet
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE));
			buecher = (Set<Buch>) in.readObject();
			in.close();
			return true;
		}
		catch (IOException e) {
			//..
		} 
		catch (ClassNotFoundException e) {
			//..
		}
		buecher = new HashSet<>();
		return false;
	}
}
