package buecherverwaltung_25_5;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Predicate;

public class MAPBuecherverwaltung {
	private Scanner scanner = new Scanner(System.in);
	
	private String FILE = "data2.ser";	// Datei, in der die eingegebenen Daten gespeichert werden
	private Map<String, Buch> buecher = new HashMap<>(); //Map von Büchern: Klasse HashMap implementiert das Interface Map<> auf der Basis einer Hashtabelle.
		
		
	public void start() {

		if(!readFromFile()) {
			System.out.println("Altdaten Buch konnten nicht gelesen werden.");
		}
		
		app: while(true) {
			System.out.print("Eingabe: ");
			
			switch(scanner.nextLine().trim().toLowerCase()) {
				
				// neues Buch anlegen:
				case "n": 
					System.out.println("Neu (Beachte: erst mit Auswahl \"q\" werden die eingegebenen Daten gespeichert.): ");
					System.out.print("Neues Buch: ");
					
					//lokale Variable anlegen, die leseAus() ausführt:
					//dadurch kann die ISBN, was der Schlüssel sein soll, aus dem Array gelesen werden
					//Gleiche Schreibweise "this.leseAus();"
					String[]buchdatensatz = leseAus();
					//Erzeugen neues Objekt und schreiben in MAP
					buecher.put(buchdatensatz[1], new Buch(buchdatensatz));							
					break;
				
				// vorhandene Bücher anzeigen:
				case "l": 
					System.out.println("Liste:");
					if(buecher.isEmpty()) {
				
						System.out.println("Keine Bücher vorhanden.");
					}
					else {
						//TODO For-Schleife für Map:
						for(String key : buecher.keySet()) {
							System.out.println(key + ": " + buecher.get(key));
						}
					}
					break;
					
					
					
				// Buch suchen:
				case "s":
				//TODO Methode zum Auslesen einzelner Scanner-Eingaben, Attribut für Attribut, in sep. Methode								
				
				// Abbruch der Eingaben/des Programms:
				// erst mit Eingabe von "q" werden die DAten auch wirklich in die Datei gespeichert, denn:
				// wird nicht "q" gewählt (sondern Konsoleneingabe abgebrochen oder einfach Programm neu gestartet), wird nicht aus der while gesprungen und der restliche Code
				// siehe "if(!saveToFile()) {.... usw." ausgeführt 
				case "q": 
					break app;	
				// Standardausgabe:
				default:
					System.out.println("Falsche Eingabe.");
					
			}
		}
		
		//Prüfung, ob Daten gespeichert wurden, nachdem die Methode saveToFile ausgeführt wurde:
		if(!saveToFile()) {
			System.out.println("Daten konnten nicht gespeichert werden.");
		}
		// Daten wurden erst jetzt gespeichert!!!
		System.out.println("Daten wurden gespeichert");
		System.out.println("Programmende");
	}
		
		
		//Methode Daten einlesen anhand Scanner-Eingaben:
		private String[] leseAus() {
		
			
			Predicate<String> inputStringisValid = x -> !x.isEmpty();
			// inputNumberValid = numberString -> Integer.valueOf(numberString) > 0;

			 //String test = new String("lkshdlksa");
			 //System.out.print("------------------>n");
			 //System.out.println(inputNumberValid.test(test));

			// Array erzeugen, dass die Eingabetexte pro Objektattribut enthält:
			String[] eingabetext = { "Bitte geben Sie Titel des Buches ein: ", "Bitte geben Sie die ISBN ein: ",
					"Bitte den Autor eingeben: ", "Bitte den verlag angeben: ", "Bitte das Erscheinungsjahr angeben: ",
					"Seitenanzahl angeben: ", "Beschreibung angeben: " };
				

			// TODO ENUM anlegen oder Labels

			// Array erzeugen, dass die eingegebenen Werte für das Buch entgegennimmt:
			String[] buchdatensatz = new String[eingabetext.length];

			// Schleife zum Durchlaufen und Erzeugen der Eingaben:
			exit:for (int i = 0; i < eingabetext.length; i++) {
				while (true) {
					System.out.println(eingabetext[i]);
					String eingabewert = scanner.nextLine();
					if(eingabewert.equalsIgnoreCase("exit")) break exit;
						if (inputStringisValid.test(eingabewert)) {
							buchdatensatz[i] = eingabewert;
							break;
						}
					}

				// TODO Prüfen der Eingaben
				// System.out.println(eingabetext[i] + ": " + eingabewert);
				 // Rückgabewert an den Konstruktor "public Buch(String[] leseAus) {..." in Buch.java
			}
			return buchdatensatz;
			}	
				
		
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
				System.out.println("Daten konnten nicht gespeichert werden.");
				return false;
			}		
		}
		
		// Daten aus Datei lesen:
		private boolean readFromFile() {
			
			// Liest Objekt aus Dateien
			// Snippet
			try {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE));
				buecher = (Map<String, Buch>) in.readObject();
				in.close();
				return true;
			}
			catch (IOException | ClassNotFoundException e) { // Multi-Catch
				System.out.println("Daten konnten nicht gelesen werden.");
			}
			buecher = new HashMap<>();
			return false;
		}
}
