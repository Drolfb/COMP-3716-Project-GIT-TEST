package main.gui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
 
public class TournamentStartPage
{
	
	ArrayList<String> databaseIDs;
	String[][] bracket;
	String textFile;
	int round;
	JFrame frame;
	
	public static void launchPage(String[][] bracket, String textFile, int round, ArrayList<String> databaseIDs)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TournamentStartPage(bracket, textFile, round, databaseIDs);
            }
        });
    } 
	
    private TournamentStartPage(String[][] bracket, String textFile, int round, ArrayList<String> databaseIDs)
    {
    	// Intialize values.
    	this.databaseIDs = databaseIDs;
    	this.bracket = bracket;
    	this.textFile = textFile;
    	this.round = round;
    	
    	frame = new JFrame("Stuff");
    	
        // Headers for the table. Hardcoded in.
        String[] columns = new String[] {
            "Team One", "Team Two", "Winner"
        };
        
        
        /**
	        TextDatabaseTools teamListTextFile = new TextDatabaseTools(textFile);
	        
	        ArrayList<String> team1Column = teamListTextFile.grabColumnDataAsArrayList(0);
	        ArrayList<String> team2Column = teamListTextFile.grabColumnDataAsArrayList(1);
	        ArrayList<String> winnerColumn = teamListTextFile.grabColumnDataAsArrayList(2);
	        
	        String[][] bracket = new String[team1Column.size()+1][3];
	        
	        for (int i = 0; i < team1Column.size(); i++) {
	        	bracket[i][0] = team1Column.get(i);
	        	bracket[i][1] = team2Column.get(i);
	        	if (winnerColumn.get(i) == null) continue;
	        	bracket[i][2] = winnerColumn.get(i);
	        }
        */
        
        
        //create table with data
        System.out.println("textFile = " + textFile);
        TextDatabaseTools bracketStuff = new TextDatabaseTools(textFile);
        
        ArrayList<String> team1Column = bracketStuff.grabColumnDataAsArrayList(0);
        ArrayList<String> team2Column = bracketStuff.grabColumnDataAsArrayList(1);
        ArrayList<String> winnerColumn = bracketStuff.grabColumnDataAsArrayList(2);
        String[][] updateBracket = new String[winnerColumn.size()+1][3];
        
        System.out.println("Winner Column Size is: " + winnerColumn.size());
        
        System.out.println("team1Column.size() = " + team1Column.size());
        for (int i = 0; i < team1Column.size(); i++) {
        	updateBracket[i][0] = team1Column.get(i);
			updateBracket[i][1] = team2Column.get(i);
			updateBracket[i][2] = winnerColumn.get(i);
		}
        System.out.println(winnerColumn);
        
        
       
        
        DefaultTableModel model = new DefaultTableModel(updateBracket, columns);
        JTable table = new JTable(model);
         
        //add the table to the frame
        frame.getContentPane().add(new JScrollPane(table));
        
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        panel.setLayout(new BorderLayout(0, 0));
        
        JButton buttonNextRound = new JButton("Next Round");
        
        int setVisible = 1;
        buttonNextRound.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		if (winnerColumn.size() == 1 && ( winnerColumn.get(0) != " ") ) {
        			
        			TextDatabaseTools winningBracket = new TextDatabaseTools(databaseIDs.get(databaseIDs.size()-1));
        			ArrayList<String> winnerColumn = winningBracket.grabColumnDataAsArrayList(2);
        			
        			
        			
                	WinnerPage.launchPage(winnerColumn.get(0), databaseIDs);
                	frame.setVisible(false);
                } 
        		else {
        		
	        		int nextRound = round + 1;
	        		
	        		System.out.println("IN NEXT ROUND");
	        		
	        		DefaultTableModel oldTable = (DefaultTableModel) table.getModel();
	        		int rowCount = oldTable.getRowCount();
	        		oldTable.setRowCount(0);
	        		
	        		TextDatabaseTools currentBracket = new TextDatabaseTools(databaseIDs.get(databaseIDs.size()-1));
	        		String newBracketName = "Game_Round" + nextRound + "-" + databaseIDs.get(3);
	        		databaseIDs.add(newBracketName);
	        		TextDatabaseTools newBracketTextFile = new TextDatabaseTools(newBracketName);
	        		
	        		ArrayList<String> winnerColumn = currentBracket.grabColumnDataAsArrayList(2);
	        		
	        		String[][] newBracket = new String[winnerColumn.size()+1][3];
	        		
	        		for (int i = 0; i < winnerColumn.size(); i++) {
	        			newBracket[i][2] = " ";
	        		}
	        		
	        		
	        		int bracketCounter = 0; // Used to access elements in the array
			        for (int i = 0; i < winnerColumn.size(); i++) {
			        	if (bracketCounter >= winnerColumn.size()) break;
			        	newBracket[i][0] = winnerColumn.get(bracketCounter);
						newBracket[i][1] = winnerColumn.get(++bracketCounter);
						bracketCounter++;
						
						bracket[i][2] = " ";
					}
					
			        
			        int counter = 0;
			        for (int i = 0; i < winnerColumn.size(); i++) {
			        	if (counter >= winnerColumn.size()) break;
			        	
			        	Object[] tempArray = new Object[3];
			        	tempArray[0] = winnerColumn.get(counter);
			        	tempArray[1] = winnerColumn.get(++counter);
			        	tempArray[2] = "    ";
			        	counter++;
			        	
			        	oldTable.insertRow(i, tempArray);
			        }
			        
			        currentBracket.updateTextFileUsingArray(newBracket);
			        newBracketTextFile.updateTextFileUsingArray(newBracket);
			        
			        currentBracket.printContents();
			        
        		}
        	}
        });
        panel.add(buttonNextRound, BorderLayout.EAST);
        
        JButton buttonManageScores = new JButton("Manage Scores");
        buttonManageScores.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		
        		int selectedRow = table.getSelectedRow();
				
				System.out.println("SelectedRow = " + selectedRow);
				
				
				
				
				if (selectedRow != -1) {
					String team1Name = (String) table.getValueAt(selectedRow, 0);
					String team2Name = (String) table.getValueAt(selectedRow, 1);

					DetermineWinnerPage.launchPage(team1Name, team2Name, bracket, round, databaseIDs);
					frame.setVisible(false);
					
				}
				else {
					System.out.println("There's no teams there silly!");
				}
        	}
        });
        panel.add(buttonManageScores, BorderLayout.WEST);
        
        JLabel labelTournamentStart = new JLabel("Tournament Start");
        labelTournamentStart.setHorizontalAlignment(SwingConstants.CENTER);
        frame.getContentPane().add(labelTournamentStart, BorderLayout.NORTH);
         
        frame.setTitle("Tournament Start: " + round);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        frame.pack();
        frame.setVisible(true);
    }  
} 