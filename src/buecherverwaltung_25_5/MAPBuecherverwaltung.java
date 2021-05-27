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

public class MAPBuecherverwaltung {
	private Scanner scanner = new Scanner(System.in);
	
	private String FILE = "data2.ser";
	
	//Map von Büchern:
	// Die Klasse HashMap implementiert das Interface Map<> auf der Basis einer Hashtabelle.
		private Map<String, Buch> buecher = new HashMap<>();
		
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
				buecher = (Map<String, Buch>) in.readObject();
				in.close();
				return true;
			}
			catch (IOException e) {
				//..
			} 
			catch (ClassNotFoundException e) {
				//..
			}
			buecher = new HashMap<>();
			return false;
		}
}
