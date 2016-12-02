package main.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TournamentListing {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void tournamentListing(String[][] data) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TournamentListing window = new TournamentListing(data);
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
	private TournamentListing(String[][] data) {
		initialize(data);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String[][] data) {
		
		String[] columns = new String[] {"Tournament Name", "Organizer Name", "Min. Teams" ,"Max. Teams", "Deadline"};
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		table = new JTable(data, columns);
		table.setDefaultEditor(Object.class, null);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 50, 435, 325);
		frame.getContentPane().add(scrollPane);
		
		JLabel lblTournamentListing = new JLabel("Tournament Listing");
		lblTournamentListing.setBounds(145, 12, 138, 15);
		frame.getContentPane().add(lblTournamentListing);
		
		JButton btnJoinTournament = new JButton("Join Tournament");
		btnJoinTournament.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// JOIN TOURNAMENT CLICKED!
				
			}
		});
		btnJoinTournament.setBounds(12, 388, 194, 25);
		frame.getContentPane().add(btnJoinTournament);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				// RETURN CLICKED!
			
			}
		});
		btnReturn.setBounds(242, 388, 194, 25);
		frame.getContentPane().add(btnReturn);
	}
}
