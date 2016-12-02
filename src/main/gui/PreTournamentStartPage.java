package main.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PreTournamentStartPage {

	private JFrame frame;
	private JTable table;
	ArrayList<String> databaseIDs;
	String[][] data;
	String[] columns;
	String deadline;

	/**
	 * Launch the application.
	 */
	public static void launchPage(String tournamentName, String deadline, String[][] data, String[] columns, ArrayList<String> databaseIDs) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PreTournamentStartPage window = new PreTournamentStartPage(tournamentName, deadline, data, columns, databaseIDs);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	private PreTournamentStartPage(String tournamentName, String deadline, String[][] data, String[] columns, ArrayList<String> databaseIDs) {
		this.databaseIDs = databaseIDs;
		this.data = data;
		this.columns = columns;
		this.deadline = deadline;
		initialize(tournamentName, deadline, data, columns);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String tournamentName, String deadline, String[][] data, String[] columns) {
		frame = new JFrame("Pre Tournament Start");
		frame.setBounds(100, 100, 450, 450);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		//headers for the table

		JLabel lblTournamentName = new JLabel("Tournament Name: " + tournamentName);
		lblTournamentName.setBounds(139, 0, 200, 50);
		frame.getContentPane().add(lblTournamentName);
		
		JLabel lblDeadlineTitle = new JLabel("Deadline:");
		lblDeadlineTitle.setBounds(12, 56, 81, 50);
		frame.getContentPane().add(lblDeadlineTitle);
		
		JLabel lblDeadLine = new JLabel(deadline);
		lblDeadLine.setBounds(99, 56, 337, 50);
		frame.getContentPane().add(lblDeadLine);
		
		table = new JTable(data, columns);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 102, 424, 257);
		frame.getContentPane().add(scrollPane);
		
		JButton btnAddTeam = new JButton("Add Team");
		btnAddTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Pre Tournament Start Page " + databaseIDs.get(0));
				RegisterTeamPage.launchPage(data, databaseIDs, tournamentName, deadline);
				frame.setVisible(false);
			}
		});
		btnAddTeam.setBounds(12, 371, 206, 25);
		frame.getContentPane().add(btnAddTeam);
		
		JButton btnGenMatchups = new JButton("Generate Matchups");
		btnGenMatchups.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button Pressed!!!!!!!!!!!!");
				
				TextDatabaseTools teamListTextFile = new TextDatabaseTools(databaseIDs.get(2));
				teamListTextFile.printContents();
				
				String textFile = "Game_" + databaseIDs.get(3);
				databaseIDs.add(textFile);
				System.out.println("end of list = " + databaseIDs.get(databaseIDs.size()-1));
				
				TextDatabaseTools gameTextFile = new TextDatabaseTools(textFile); 
				ArrayList<String> teamData = teamListTextFile.grabColumnDataAsArrayList(0);

		        String[][] bracket = new String[teamData.size()+1][3];
		        
		        for (int i = 0; i < teamData.size(); i++) {
		        	bracket[i][2] = " ";
		        }
		        
		        int counter = 0; // Used to access elements in the array
		        for (int i = 0; i < teamData.size(); i++) {
		        	if (counter >= teamData.size()) break;
					bracket[i][0] = teamData.get(counter);
					bracket[i][1] = teamData.get(++counter);
					counter++;
					
					bracket[i][2] = " ";
				}
		        
		        gameTextFile.updateTextFileUsingArray(bracket);
		        gameTextFile.printContents();
		        
				
				TournamentStartPage.launchPage(bracket, textFile, 1, databaseIDs);
				frame.setVisible(false);
			}
		});
		btnGenMatchups.setBounds(230, 371, 206, 25);
		frame.getContentPane().add(btnGenMatchups);
		
		JButton buttonViewTeam = new JButton("View Team");
		buttonViewTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				
				System.out.println("SelectedRow = " + selectedRow);
				
				if (selectedRow != -1) {
					String teamName = (String) table.getValueAt(selectedRow, 0);
					String coachName = (String) table.getValueAt(selectedRow, 1);

					TeamPage.launchPage(databaseIDs, teamName, coachName, tournamentName, deadline, data, columns);
					frame.setVisible(false);
				}
				else {
					System.out.println("There's no teams there silly!");
				}
				
			}
		});
		buttonViewTeam.setBounds(12, 399, 206, 29);
		frame.getContentPane().add(buttonViewTeam);
	}
}
