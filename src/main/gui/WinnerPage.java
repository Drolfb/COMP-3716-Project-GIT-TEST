package main.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.JLabel;
import javax.swing.JTextField;

import main.gui.MainMenu.USER;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WinnerPage {
	
	// TODO: onClick methods for all buttons.
	// TODO: Populating both combo boxes at runtime.
	// TODO: Maybe add a better way to get the two date fields?

	private JFrame frame;
	private JTextField tournamentNameTextField;
	private JLabel labelHardcodeLocation;
	private ArrayList<String> databaseIDs = new ArrayList<String>();
	
	String winner;

	/**
	 * Launch the application.
	 */
	public static void launchPage(String winner, ArrayList<String> databaseIDs) {
		// Print out current list of database IDs, will be used a lot throughout the program.
		// for (int i = 0; i < databaseIDs.size(); i++) System.out.println("DB id = " + databaseIDs.get(i));
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					WinnerPage createTournamentPage = new WinnerPage(winner, databaseIDs);
					createTournamentPage.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	private WinnerPage(String winner, ArrayList<String> databaseIDs) {
		this.databaseIDs = databaseIDs;
		this.winner = winner;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame("Winner!");
		frame.setBounds(100, 100, 450, 325);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCongratulationsToThe = new JLabel("Congratulations to the Winners\r\n");
		lblCongratulationsToThe.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblCongratulationsToThe.setBounds(64, 29, 297, 22);
		frame.getContentPane().add(lblCongratulationsToThe);
		
		JLabel lblTeamHarambe = new JLabel(winner);
		lblTeamHarambe.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTeamHarambe.setBounds(113, 107, 235, 22);
		frame.getContentPane().add(lblTeamHarambe);
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 1; i < databaseIDs.size(); i++) {
					databaseIDs.remove(i);
				}
				MainMenu.launchMainMenu(USER.MANAGER, frame, databaseIDs);
				frame.setVisible(false);
			}
		});
		btnMainMenu.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnMainMenu.setBounds(121, 177, 136, 23);
		frame.getContentPane().add(btnMainMenu);
		
		JLabel lblWinnersOf = new JLabel("of the Tournament!");
		lblWinnersOf.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblWinnersOf.setBounds(120, 48, 185, 21);
		frame.getContentPane().add(lblWinnersOf);
		

	}
}