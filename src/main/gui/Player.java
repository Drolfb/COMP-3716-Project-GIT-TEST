package main.gui;

public class Player {

	int skill;
	String name; 
	
	String fileName = "Game_123";
	
	public Player(int skill, String name) {
		if (skill > 5) {
			skill = 5;
		} 
		else if (skill < 0) {
			skill = 0;
		}
		this.skill = skill;
		this.name = name; 
		
    	TextDatabaseTools tournamentDatabase = new TextDatabaseTools(fileName);
    	tournamentDatabase.addRow(name + "/" + skill);

		
	}
}
