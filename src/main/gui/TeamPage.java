package main.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class TeamPage {

	private JFrame frame;
	private JTable table;
	ArrayList<String> databaseIDs;
	String teamName; 
	String coachName; 
	String tournamentName;
	String deadline;
	String[][] data;
	String[] columns;

	/**
	 * Launch the application.
	 */
	public static void launchPage(ArrayList<String> databaseIDs, String teamName, String coachName, String tournamentName,
			String deadline, String[][] data, String[] columns) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeamPage window = new TeamPage(databaseIDs, teamName, coachName, tournamentName, deadline, data, columns);
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
	private TeamPage(ArrayList<String> databaseIDs, String teamName, String coachName, String tournamentName,
			String deadline, String[][] data, String[] columns) {
		this.databaseIDs = databaseIDs;
		this.teamName = teamName;
		this.coachName = coachName;
		
		this.tournamentName = tournamentName; 
		this.deadline = deadline; 
		this.data = data; 
		this.columns = columns;
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		String[] columnNames = new String[] {"Player Name", "Skill Rating"};
		
		String teamListTextFileName = databaseIDs.get(2);
		TextDatabaseTools teamListTextFile = new TextDatabaseTools(teamListTextFileName);
		
		ArrayList<String> teamListTextFileArray = teamListTextFile.grabRowDataAsArrayList(0);
		
		// Create a textfile for each team or grab a new depending if the coach has already looked at the team.
		System.out.println("teamListTextFileArray.get(0) = " + teamListTextFileArray.get(0));
		System.out.println(databaseIDs);
		System.out.println("databaseIDs.get(3) = " + databaseIDs.get(3));
		String teamTextFileName = "Game_BasicInfo_Team" + teamListTextFileArray.get(0) + databaseIDs.get(3);
		TextDatabaseTools teamTextFile = new TextDatabaseTools(teamTextFileName);
		databaseIDs.add(teamTextFileName);
		
		String[][] teamList = teamTextFile.returnTextFileAsArray();
		ArrayList<String> playerList = teamTextFile.grabColumnDataAsArrayList(0);
		ArrayList<String> skillList = teamTextFile.grabColumnDataAsArrayList(1);
		

		System.out.println("Stuff  " + playerList.size() + "   " + skillList.size());
		
		
		String[][] combinedArrayList;
		if (playerList.size() == 0) {
			combinedArrayList = new String[0][0];
		}
		else if (playerList.size() == 1) {
			combinedArrayList = new String[1][2];
			combinedArrayList[0][0] = playerList.get(0);
			combinedArrayList[0][1] = skillList.get(0);
		}
		else {
			combinedArrayList = new String[playerList.size()][skillList.size()];
			for (int i = 0; i < combinedArrayList.length; i++) {
				combinedArrayList[i][0] = playerList.get(i);
				combinedArrayList[i][1] = skillList.get(i);
			}
		}
		
		int teamSkill = 0;
		int temp;
		for (int i = 0; i < skillList.size(); i++){
			temp = Integer.parseInt(skillList.get(i));
			teamSkill = teamSkill + temp;
		}
		if (skillList.size() != 0) {
			teamSkill = ((teamSkill*100/skillList.size()*100)/100);
		}
		else {
			teamSkill = 0;
		}
		
		
		JLabel lblTeamName = new JLabel("Team: " + teamName);
		lblTeamName.setBounds(135, 15, 150, 30);
		frame.getContentPane().add(lblTeamName);
		
		JButton addPlayerButton = new JButton("Add Player");
		addPlayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterPlayerPage.launchPage(databaseIDs, teamName, coachName, tournamentName, deadline, data, columns);
				frame.setVisible(false);
			}
		});
		addPlayerButton.setBounds(75, 395, 100, 25);
		frame.getContentPane().add(addPlayerButton);
		
		JButton returnButton = new JButton("Return");
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PreTournamentStartPage.launchPage(tournamentName, deadline, data, columns, databaseIDs);
				frame.setVisible(false);
			}
		});
		returnButton.setBounds(265, 395, 100, 25);
		frame.getContentPane().add(returnButton);
		
		JLabel lblCoachName = new JLabel("Coach: " + coachName);
		lblCoachName.setBounds(135, 50, 150, 30);
		frame.getContentPane().add(lblCoachName);
		
		table = new JTable(combinedArrayList, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(76, 91, 280, 242);
		frame.getContentPane().add(scrollPane);
		
		JLabel lblTotalTeamRating = new JLabel("Total Team Rating: " + teamSkill);
		lblTotalTeamRating.setBounds(145, 355, 165, 20);
		frame.getContentPane().add(lblTotalTeamRating);
	}
}
