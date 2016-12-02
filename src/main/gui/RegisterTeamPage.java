package main.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class RegisterTeamPage {

	private JFrame frame;
	private JTextField textFieldTeamName;
	private JTextField textFieldCoachName;
	
	ArrayList<String> databaseIDs;
	String[][] data;
	
	// PreTournamentStartPage
	String tournamentName;
	String deadline;

	/**
	 * Launch the application.
	 */
	public static void launchPage(String[][] data, ArrayList<String> databaseIDs, String tournamentName, String deadline) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterTeamPage window = new RegisterTeamPage(data, databaseIDs, tournamentName, deadline);
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
	private RegisterTeamPage(String[][] data, ArrayList<String> databaseIDs, String tournamentName, String deadline) {
		this.data = data;
		this.databaseIDs = databaseIDs;
		
		this.tournamentName = tournamentName; 
		this.deadline = deadline; 
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Register Team");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel RegisterTeamHeaderLabel = new JLabel("Register Team ");
		RegisterTeamHeaderLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		RegisterTeamHeaderLabel.setBounds(164, 11, 126, 14);
		frame.getContentPane().add(RegisterTeamHeaderLabel);
		
		JLabel TeamNameLabel = new JLabel("Team Name:");
		TeamNameLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		TeamNameLabel.setBounds(46, 46, 86, 14);
		frame.getContentPane().add(TeamNameLabel);
		
		textFieldTeamName = new JTextField();
		textFieldTeamName.setBounds(164, 44, 86, 20);
		frame.getContentPane().add(textFieldTeamName);
		textFieldTeamName.setColumns(10);
		
		JLabel labelCoachName = new JLabel("Coach Name:");
		labelCoachName.setBounds(46, 87, 96, 16);
		frame.getContentPane().add(labelCoachName);
		
		textFieldCoachName = new JTextField();
		textFieldCoachName.setBounds(164, 82, 86, 26);
		frame.getContentPane().add(textFieldCoachName);
		textFieldCoachName.setColumns(10);
		
		JButton buttonSubmit = new JButton("Register");
		buttonSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] columns = {"Team", "Coach"}; 
				
				
				for (int i = 0; i < databaseIDs.size(); i++) {
					System.out.println("databaseIDs = " + databaseIDs.get(i));
				}
				
				
				String[][] updateData;
				if (data.length == 0) {
					updateData = new String[data.length+1][2];
					updateData[0][0] = textFieldTeamName.getText();
					updateData[0][1] = textFieldCoachName.getText();
				}
				else {
					System.out.println("data.length " + data.length);
					updateData = new String[data.length+1][2];
					for (int i = 0; i < data.length; i++) {
						updateData[i][0] = data[i][0];
						updateData[i][1] = data[i][1];
					}
					updateData[data.length][0] = textFieldTeamName.getText();
					updateData[data.length][1] = textFieldCoachName.getText();
				}
				
				// Add Row.
				String teamListTextFileName = databaseIDs.get(2);
				TextDatabaseTools teamListTextFile = new TextDatabaseTools(teamListTextFileName);
				teamListTextFile.addRow(textFieldTeamName.getText()+"/"+textFieldCoachName.getText()+"/"+"0"+"0");
				
				String textFieldTeamNameContent = textFieldTeamName.getText();
				
        		PreTournamentStartPage.launchPage(tournamentName, deadline, updateData, columns, databaseIDs);
        		frame.setVisible(false);
			}
		});
		buttonSubmit.setFont(new Font("Dialog", Font.BOLD, 12));
		buttonSubmit.setBounds(158, 213, 113, 23);
		frame.getContentPane().add(buttonSubmit);
	}
}