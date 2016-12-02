package main.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DetermineWinnerPage {

	private JFrame frame;
	private JTextField textFieldTeam1Score;
	private JTextField textFieldTeam2Score;
	
	ArrayList<String> databaseIDs;
	String teamName1;
	String teamName2;
	String[][] bracket;
	int round; 

	/**
	 * Launch the application.
	 */
	public static void launchPage(String teamName1, String teamName2, String[][] bracket, int round, ArrayList<String> databaseIDs) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetermineWinnerPage window = new DetermineWinnerPage(teamName1, teamName2, bracket, round, databaseIDs);
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
	private DetermineWinnerPage(String teamName1, String teamName2, String[][] bracket, int round, ArrayList<String> databaseIDs) {
		this.teamName1 = teamName1;
		this.teamName2 = teamName2; 
		this.bracket = bracket; 
		this.databaseIDs = databaseIDs;
		this.round = round;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/*JLabel labelGameID = new JLabel("Game ID: " + databaseIDs.get(1));
		labelGameID.setFont(new Font("Dialog", Font.BOLD, 12));
		labelGameID.setBounds(85, 18, 253, 14);
		frame.getContentPane().add(labelGameID);*/
		
		JLabel labelTeam1Score = new JLabel(teamName1 + " Score: ");
		labelTeam1Score.setFont(new Font("Dialog", Font.BOLD, 12));
		labelTeam1Score.setBounds(75, 36, 120, 14);
		frame.getContentPane().add(labelTeam1Score);
		
		textFieldTeam1Score = new JTextField();
		textFieldTeam1Score.setText("");
		textFieldTeam1Score.setBounds(200, 34, 70, 20);
		frame.getContentPane().add(textFieldTeam1Score);
		textFieldTeam1Score.setColumns(10);
		
		JLabel labelTeam2Score = new JLabel(teamName2 + " Score: ");
		labelTeam2Score.setFont(new Font("Dialog", Font.BOLD, 12));
		labelTeam2Score.setBounds(75, 83, 120, 14);
		frame.getContentPane().add(labelTeam2Score);
		
		textFieldTeam2Score = new JTextField();
		textFieldTeam2Score.setText("");
		textFieldTeam2Score.setBounds(200, 81, 70, 20);
		frame.getContentPane().add(textFieldTeam2Score);
		textFieldTeam2Score.setColumns(10);
		
		JLabel labelWinner = new JLabel("THE WINNER IS: ");
		labelWinner.setFont(new Font("Times New Roman", Font.BOLD, 16));
		labelWinner.setBounds(90, 180, 250, 14);
		frame.getContentPane().add(labelWinner);
		
		JButton buttonDetermineWinner = new JButton("Determine Winner");
		buttonDetermineWinner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int teamName1Score = Integer.parseInt(textFieldTeam1Score.getText());
				int teamName2Score = Integer.parseInt(textFieldTeam2Score.getText());
				String winner = "";
				
				if (teamName1Score > teamName2Score) {
					winner = teamName1; 
				} 
				else if (teamName1Score < teamName2Score) {
					winner = teamName2;
				} else {
					winner = "DRAW"; 
				}
				
				TextDatabaseTools teamListTextFile = new TextDatabaseTools(databaseIDs.get(databaseIDs.size()-1));
				teamListTextFile.printContents();
				
				ArrayList<String> teamOneColumn = teamListTextFile.grabColumnDataAsArrayList(0);
				ArrayList<String> teamTwoColumn = teamListTextFile.grabColumnDataAsArrayList(1);
				ArrayList<String> winnerColumn = teamListTextFile.grabColumnDataAsArrayList(2);
				
				System.out.println("winnerColumn size = " + winnerColumn.size());
				
				for (int i = 0; i < teamOneColumn.size(); i++) {
					if ( (teamOneColumn.get(i).equals(teamName1)) || (teamTwoColumn.get(i).equals(teamName2)) ) {
						winnerColumn.set(i, winner);
						bracket[i][2] = winner;
						break;
					}
				}
				
				System.out.println(winnerColumn);
				
				labelWinner.setText("THE WINNER IS " + winner + "!");
				
				teamListTextFile.updateColumnUsingArrayList(winnerColumn, 2);
				
				teamListTextFile.printContents();
				
				JOptionPane.showMessageDialog(frame,
					    winner + " wins the game!");
				
				
				// TournamentStartPage.launchPage(bracket, databaseIDs);

			}
		});
		buttonDetermineWinner.setFont(new Font("Dialog", Font.BOLD, 12));
		buttonDetermineWinner.setBounds(120, 130, 157, 23);
		frame.getContentPane().add(buttonDetermineWinner);
		
		JButton btnNextGame = new JButton("Return");
		btnNextGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textFile = databaseIDs.get(databaseIDs.size()-1);
				
				TextDatabaseTools teamListTextFile = new TextDatabaseTools(textFile);
				teamListTextFile.printContents();
				
				
				
				TournamentStartPage.launchPage(bracket, textFile, round, databaseIDs);
				frame.setVisible(false);
			}
		});
		btnNextGame.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnNextGame.setBounds(142, 211, 108, 23);
		frame.getContentPane().add(btnNextGame);

	}
}