package main.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu extends JPanel {

	/**
	 * Launch the application.
	 */
	public static void launchMainMenu(USER currentUser, JFrame prevFrame, ArrayList<String> databaseIDs) {
        MainMenu panel = new MainMenu(currentUser, prevFrame, databaseIDs);
        panel.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.frame.setSize(300, 275);
        panel.frame.setResizable(false); // let's not allow this
        panel.frame.setLocationRelativeTo(null); // center the window
        panel.setBackground(Color.LIGHT_GRAY);
        panel.frame.getContentPane().add(panel);
        panel.frame.setVisible(true);
	}

    private JButton myTournamentButton;
    private JButton createTournamentButton;
    private JLabel Header;
    private JButton viewTournamentsButton;
    private JButton logoutButton;
    JFrame frame = new JFrame("Main Menu");
    public String userName;
    public JFrame prevFrame;
    public USER currentUser;
    private ArrayList<String> databaseIDs;
    
	public enum USER {
		MANAGER, COACH, REFEREE, PLAYER
	}


    private MainMenu(USER currentUser, JFrame prevFrame, ArrayList<String> databaseIDs)
    {
    	this.prevFrame = prevFrame;
    	this.currentUser = currentUser;
    	this.databaseIDs = databaseIDs;
    	
    	System.out.println(databaseIDs);
        setBackground(Color.LIGHT_GRAY);
        Header = new JLabel("Volleyball Tournament v0.1");
        Header.setFont(new Font("Georgia", Font.BOLD, 16));
        createTournamentButton = new JButton("Create Tournament");
        createTournamentButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		CreateTournamentPage.launchPage(databaseIDs);
        		frame.setVisible(false);
        	}
        });
        myTournamentButton = new JButton("My Tournament");
        myTournamentButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		// dropDownMenu(dummyOptions);
        	}
        });
        viewTournamentsButton = new JButton("View Tournaments");
        viewTournamentsButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		// TournamentListing.tournamentListing(data); // REPLACE DATA WITH WHAT TO PUT IN TABLE
        	}
        });
        logoutButton = new JButton("Log-out");
        logoutButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		prevFrame.setVisible(true);
        		frame.dispose();
        	}
        });
        JPanel createTournamentPanel = new JPanel();
        JPanel myTournamentPanel = new JPanel();
        JPanel viewTournamentsPanel = new JPanel();
        JPanel logoutPanel = new JPanel();
        createTournamentPanel.setPreferredSize(new Dimension(200,35));
        myTournamentPanel.setPreferredSize(new Dimension(200,35));
        viewTournamentsPanel.setPreferredSize(new Dimension(200,35));
        logoutPanel.setPreferredSize(new Dimension(200,35));
        myTournamentPanel.add(myTournamentButton);
        
        createTournamentPanel.add(createTournamentButton);
        viewTournamentsPanel.add(viewTournamentsButton);
        logoutPanel.add(logoutButton);
        add(Header);
        add(myTournamentPanel);
        if (currentUser == USER.MANAGER) {add(createTournamentPanel);} // only add a "create tournament" menu for organizers
        add(viewTournamentsPanel);
        add(logoutPanel);
    }

}