package main.gui;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;


/**
 * A class that tests out the maintaining system information part of the architecture. It adds a row to a textfile 
 * database and the prints out the contents of the database.
 * @author Freddie Pike
 */


public class DatabaseTester {
    public static void main(String [] args) {
    	Scanner in = new Scanner(System.in);
    	// System.out.println("Do you want to create a tournament: Enter Y or N");
    	ArrayList<String> tempArray = new ArrayList<String>();
    	String team1[][] = new String[50][50];
    	
    	TextDatabaseTools teamDB = new TextDatabaseTools("Pitbulls");
    
    	tempArray = teamDB.grabColumnDataAsArrayList(1);
    	
    	int tempSkill = 0;
    	int totalskill = 0;
    	for (int i = 0; i < tempArray.size(); i++) {
    		tempSkill = Integer.parseInt(tempArray.get(i));
    		System.out.print(tempArray.get(i) + " ");
    		totalskill += tempSkill;
    	}
    	System.out.println("\n" + totalskill);
    	
    	TextDatabaseTools game1 = new TextDatabaseTools("Game_123");
    	ArrayList<String> gameColumn1 = new ArrayList<String>();
    	
    	gameColumn1 = game1.grabColumnDataAsArrayList(1);
    	
    	gameColumn1.set(0, "The");
    	gameColumn1.set(1, "Quick");
    	gameColumn1.set(2, "Brown");
    	gameColumn1.set(3, "Test");
    	
    	game1.updateColumnUsingArrayList(gameColumn1, 1);
    
    	ArrayList<String> gameRow1 = new ArrayList<String>();
    	
    	for (int i = 0; i < gameRow1.size(); i++) {
    		System.out.print(gameRow1.get(i) + " ");
    	}
    	
    	
    	
    	
    	
    	/**
    	String uuid = UUID.randomUUID().toString();
    	System.out.println(uuid);
    	TextDatabaseTools tournamentDatabase = new TextDatabaseTools("Game_123");
    	
    	String testArray[][] = new String[10][10];
    	testArray = tournamentDatabase.returnTextFileAsArray();
    	
    	testArray[1][0] = "Pitbulls";
    	
    	
    	
    	tournamentDatabase.updateTextFileUsingArray(testArray);
    	
    	tournamentDatabase.printContentsByColumn(1);
    	*/
    	
    	
            

        // String gameName = "Game_BasicInfo_" + uuid;
    	// TextDatabaseTools tournamentDatabase = new TextDatabaseTools(gameName); 
        // tournamentDatabase.addRow(gameName + "/Fred/2/8/6/10/Single Elimination/2016 11 20/Madison Square Garden/2016-11-28");
        // tournamentDatabase.addRow(gameName + "/Steve/2/10/6/12/Single Elimination-2016 8 7/MileOne/2016-10-2");
        // tournamentDatabase.printContents();
    	
    }
}