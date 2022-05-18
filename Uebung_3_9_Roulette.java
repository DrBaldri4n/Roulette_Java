import java.util.Scanner;
import java.lang.Math; //jeder Import mit Java davor ist erlaubt?

public class Uebung_3_9_Roulette {

	public static void main(String[] args) {
		//Variablen benennung
		int[] red = {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};
		int[] black = {2,4,6,8,10,11,13,15,17,20,22,24,26,28,29,31,33,35}; //nur zur �bersicht wird nicht genutzt
		int green = 0;
		
		int input;
		int preselect;
		int number;
		int capital = 1000;
		
		boolean result = false;
		String color = "";
		
		Scanner sc = new Scanner(System.in);
		while (capital > 0) {
			//Zuf�llige Zahl wird generiert
			int random = (int)(Math.random()*(36)); //Wann ich die Random Zahl generiere ist egal?
			
			//User Input
			System.out.printf("Ihr aktuelles Kapital betr�gt %d EUR%n%n", capital);
			System.out.println("Wie viel Geld wollen Sie setzten?");
			input = sc.nextInt();
			sc.nextLine();
			
			if (input > capital) {
				System.out.printf("Sie besitzen nur %d EUR%n%n", capital);
			} else { 
				System.out.printf("Wollen Sie auf eine Zahl Setzten = 1%nWollen Sie auf eine Farbe setzten = 2%n");
				preselect = sc.nextInt();
				sc.nextLine();
					
				//erste Vorauswahl und verteilung an die richtigen Funktionen
				if (preselect == 1) {
					System.out.println("Waehlen Sie eine Zahl zwischen 0 und 36");
					number = sc.nextInt();
					sc.nextLine();
					result = numberFunc(number, random);
					
				} else {
					System.out.println("Waehlen Sie eine Farbe: Schwarz, Rot oder Gruen");
					color = sc.nextLine();
					sc.nextLine();
					color = color.toLowerCase();
					
					result = colorFunc(color, random, red, green);
				}
					
				//Gewinn bzw. Verlust wird berechnet und zur�ckgegeben
				capital = calculatorFunc(result, capital, input, preselect);
			}
		}
		sc.close();
		System.out.println("Sie haben das Spiel verloren");
	}
	
	private static boolean numberFunc(int number, int random) {
		System.out.printf("Die Kugel ist auf Feld %d gefallen%n", random);
		if (number == random) {
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean colorFunc(String color, int random, int[] red, int green) {
		//zuordnung Farbe der Benutzereingabe
		int choice = 0;
		if (color.equals("rot")) {
			choice = 1;
		}
		if (color.equals("gruen")) {
			choice = 2;
		}
		
		//zuordnung Farbe der Random Zahl
		int choiceRandom = 0;
		String choiceRandomStr = "Schwarz";
		
		for (int counter = 0; counter < red.length; counter++) {
			if (random == red[counter]) {
				choiceRandom = 1;
				choiceRandomStr = "Rot";
				break;
			}
		}if (random == green) {
			choiceRandom = 2;
			choiceRandomStr = "Gruen";
		}
		
		System.out.printf("Die Kugel ist auf die Farbe %s gefallen%nFarbe benutzer = %s%n", choiceRandomStr, color);
		
		//Kontrolle ob Benutzereingabe und Randomzahl in der selben Farbe sind
		if (choice == choiceRandom) {
			return true;
		} else {
			return false;
		}
	}
	
	private static int calculatorFunc(boolean result, int capital, int input, int preselect) {
		if (result == false) {
			capital -= input;
		} else {
			capital -= input;
			if (preselect == 1) {		 //1 = auf Zahl gesetzt, 2 = auf Farbe gesetzt
				capital += input * 35;
			} else {
				capital += input * 2;
			}
		}
		return capital;
	}
}