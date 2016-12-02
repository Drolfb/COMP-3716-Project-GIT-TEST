package main.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterPlayerPage {

	private JFrame frame;
	private JTextField textField_1;
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
					RegisterPlayerPage window = new RegisterPlayerPage(databaseIDs, teamName, coachName, tournamentName, deadline, data, columns);
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
	private RegisterPlayerPage(ArrayList<String> databaseIDs, String teamName, String coachName, String tournamentName,
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
		System.out.println(databaseIDs);
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblRegisterPlayer = new JLabel("Register Player");
		lblRegisterPlayer.setFont(new Font("Dialog", Font.BOLD, 12));
		lblRegisterPlayer.setBounds(167, 17, 110, 14);
		frame.getContentPane().add(lblRegisterPlayer);
		
		JLabel lblPlayerName = new JLabel("Player Name:");
		lblPlayerName.setFont(new Font("Dialog", Font.BOLD, 12));
		lblPlayerName.setBounds(47, 62, 102, 14);
		frame.getContentPane().add(lblPlayerName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(167, 60, 167, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblStarRating = new JLabel("Star Rating:");
		lblStarRating.setFont(new Font("Dialog", Font.BOLD, 12));
		lblStarRating.setBounds(47, 112, 102, 14);
		frame.getContentPane().add(lblStarRating);
		
		JComboBox comboBoxStarRating = new JComboBox();
		comboBoxStarRating.setBounds(167, 110, 60, 20);
		comboBoxStarRating.addItem("0");
		comboBoxStarRating.addItem("1");
		comboBoxStarRating.addItem("2");
		comboBoxStarRating.addItem("3");
		comboBoxStarRating.addItem("4");
		comboBoxStarRating.addItem("5");
		frame.getContentPane().add(comboBoxStarRating);
		
		JButton btnRegisterPlayer = new JButton("Register Player");
		btnRegisterPlayer.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				//Output the values entered
				
				
				TextDatabaseTools teamTextFile = new TextDatabaseTools(databaseIDs.get(4));
				
				teamTextFile.addRow(textField_1.getText()+"/"+comboBoxStarRating.getSelectedItem());
				
				System.out.println(textField_1.getText());
				System.out.println(comboBoxStarRating.getSelectedItem() + " Star Rating!");
				System.out.println("ANOTHER LINE!?!?!?!");
				System.out.println("databaseIDs(4) = " + databaseIDs.get(4));
				
				JOptionPane.showMessageDialog(frame,
					    "Player Added!.");
			}
		});
		btnRegisterPlayer.setFont(new Font("Dialog", Font.BOLD, 12));
		btnRegisterPlayer.setBounds(134, 175, 153, 23);
		frame.getContentPane().add(btnRegisterPlayer);
		
		JButton buttonReturn = new JButton("Return");
		buttonReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TeamPage.launchPage(databaseIDs, teamName, coachName, tournamentName, deadline, data, columns);
				frame.setVisible(false);
			}
		});
		buttonReturn.setBounds(150, 210, 117, 29);
		frame.getContentPane().add(buttonReturn);
		
		

	}
}