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
import javax.swing.JComboBox;
import javax.swing.JButton;

public class CreateTournamentPage {
    
    // TODO: onClick methods for all buttons.
    // TODO: Populating both combo boxes at runtime.
    // TODO: Maybe add a better way to get the two date fields?
    
    private JFrame frame;
    private JTextField tournamentNameTextField;
    private JLabel labelHardcodeLocation;
    private ArrayList<String> databaseIDs = new ArrayList<String>();
    
    /**
     * Launch the application.
     */
    public static void launchPage(ArrayList<String> databaseIDs) {
        // Print out current list of database IDs, will be used a lot throughout the program.
        // for (int i = 0; i < databaseIDs.size(); i++) System.out.println("DB id = " + databaseIDs.get(i));
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    
                    CreateTournamentPage createTournamentPage = new CreateTournamentPage(databaseIDs);
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
    private CreateTournamentPage(ArrayList<String> databaseIDs) {
        this.databaseIDs = databaseIDs;
        initialize();
    }
    
    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        
        frame = new JFrame("Create Tournament");
        frame.setBounds(100, 100, 450, 325);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JLabel labelTournamentName = new JLabel("Tournament Name:");
        labelTournamentName.setBounds(12, 12, 141, 15);
        frame.getContentPane().add(labelTournamentName);
        
        tournamentNameTextField = new JTextField();
        tournamentNameTextField.setBounds(169, 10, 255, 19);
        frame.getContentPane().add(tournamentNameTextField);
        tournamentNameTextField.setColumns(10);
        
        JLabel labelRegistrationDeadline = new JLabel("Registration Deadline: ");
        labelRegistrationDeadline.setBounds(12, 42, 174, 15);
        frame.getContentPane().add(labelRegistrationDeadline);
        
        JLabel labelLocation = new JLabel("Location:");
        labelLocation.setBounds(12, 71, 141, 15);
        frame.getContentPane().add(labelLocation);
        
        labelHardcodeLocation = new JLabel();
        labelHardcodeLocation.setText("St. John's");
        labelHardcodeLocation.setBounds(168, 69, 268, 19);
        frame.getContentPane().add(labelHardcodeLocation);
        
        JLabel labelMinTeams = new JLabel("Minimum Teams:");
        labelMinTeams.setBounds(12, 103, 141, 15);
        frame.getContentPane().add(labelMinTeams);
        
        JLabel labelMaxTeams = new JLabel("Maximum Teams:");
        labelMaxTeams.setBounds(12, 134, 141, 15);
        frame.getContentPane().add(labelMaxTeams);
        
        JLabel lblDdmmyy = new JLabel("dd/mm/yy");
        // lblDdmmyy.setBounds(364, 72, 72, 15);
        // frame.getContentPane().add(lblDdmmyy);
        
        JLabel labelDateFormat = new JLabel("dd/mm/yy");
        labelDateFormat.setBounds(364, 41, 60, 15);
        frame.getContentPane().add(labelDateFormat);
        
        JComboBox MinTeam = new JComboBox();
        
        MinTeam.setBounds(169, 99, 72, 20);
        int z = 2;
        int x = 4;
        int c = 8;
        int v = 16;
        int b = 32;
        MinTeam.addItem(z);
        MinTeam.addItem(x);
        MinTeam.addItem(c);
        MinTeam.addItem(v);
        MinTeam.addItem(b);
        frame.getContentPane().add(MinTeam);
        
        JComboBox MaxTeamSize = new JComboBox();
        MaxTeamSize.addItem(z);
        MaxTeamSize.addItem(x);
        MaxTeamSize.addItem(c);
        MaxTeamSize.addItem(v);
        MaxTeamSize.addItem(b);
        MaxTeamSize.setBounds(169, 131, 72, 20);
        frame.getContentPane().add(MaxTeamSize);
        
        JComboBox MaxPlayerSize = new JComboBox();
        for (int i = 6; i <= 99; i++)
            MaxPlayerSize.addItem(i);
        MaxPlayerSize.setBounds(169, 168, 72, 20);
        frame.getContentPane().add(MaxPlayerSize);
        
        JComboBox dd = new JComboBox();
        dd.setBounds(169, 40, 60, 20);
        for (int i = 0; i <= 31; i++)
            dd.addItem(i);
        frame.getContentPane().add(dd);
        
        JComboBox mm = new JComboBox();
        mm.setBounds(228, 40, 60, 20);
        for (int i = 0; i <= 12; i++)
            mm.addItem(i);
        frame.getContentPane().add(mm);
        
        JComboBox yy = new JComboBox();
        for (int i = 2016; i <= 2256; i++)
            yy.addItem(i);
        yy.setBounds(285, 40, 78, 20);
        frame.getContentPane().add(yy);
        
        
        
        JButton buttonSubmitTournament = new JButton("Submit Tournament");
        buttonSubmitTournament.addMouseListener(new MouseAdapter() {
            @Override
            // TL = Tournament_Listing.txt and GBI = Game_Basic_info
            public void mouseClicked(MouseEvent arg0) {
                // TL0 & GBI 0: Randomized Tournament Name.
                String randomText = UUID.randomUUID().toString();
                String tournamentNameID = "Game_BasicInfo_" + randomText;
                databaseIDs.add(tournamentNameID);
                
                // TL1 & GBI1: The Name of the Tournament
                String tournamentNameContent = tournamentNameTextField.getText();
                
                // TL2 & GB2: The name of the organizer.
                String organizerName = databaseIDs.get(0);
                System.out.println("Organizer Name" + organizerName);
                
                // GBI3: Min number teams
                int minTeamsContent = (int) MinTeam.getSelectedItem();
                
                // TL3: number of teams assigned.
                int teamsAssigned = 0;
                
                // TL4 & GBI4. Max number of teams accepted.
                int maxTeamsContent = (int) MaxTeamSize.getSelectedItem();
                
                // GBI5: Minimum number of players
                String minPlayersContent = "6";
                
                // GBI6: Maximum number of players
                int maxPlayersContent = (int) MaxPlayerSize.getSelectedItem();
                
                // GBI7: The format
                String format = " ";
                
                // TL5 & GBI8. The deadline of tournament
                int day = (int) dd.getSelectedItem();
                int month = (int) mm.getSelectedItem();
                int year = (int) yy.getSelectedItem();
                String registrationDeadlineContent = day + "-" + month + "-" + year ;
                
                // Open up tournament listing and pre
                TextDatabaseTools tournamentListing = new TextDatabaseTools("Tournament_Listing");
                tournamentListing.addRow(tournamentNameID+"/"+tournamentNameContent+"/"+organizerName+"/"+teamsAssigned+"/"+maxTeamsContent
                                         +"/"+registrationDeadlineContent);
                
                // Create a new text file that is the name of the ID and add a row of information.
                TextDatabaseTools game_basic_info = new TextDatabaseTools(tournamentNameID);
                game_basic_info.addRow(tournamentNameID+"/"+tournamentNameContent+"/"+organizerName+"/"+minTeamsContent+"/"+maxTeamsContent
                                       +"/"+minPlayersContent+"/"+maxPlayersContent+"/"+format+"/"+registrationDeadlineContent);
                
                // Create a blank text file with list of teams.
                String teamListID = "Game_BasicInfo_TeamList" + randomText;
                System.out.println("TeamListID is: " + teamListID);
                TextDatabaseTools teamListIDCreate = new TextDatabaseTools(teamListID);
                databaseIDs.add(teamListID);
                databaseIDs.add(randomText);
                
                String[] columns = {"Team", "Coach"}; 
                String[][] data = { };
                PreTournamentStartPage.launchPage(tournamentNameContent, registrationDeadlineContent, data, columns, databaseIDs);
                frame.setVisible(false);
            }
        });
        buttonSubmitTournament.setBounds(117, 212, 206, 25);
        frame.getContentPane().add(buttonSubmitTournament);
        
        JLabel lblMaxPlayerSize = new JLabel("Max Player Size: ");
        
        lblMaxPlayerSize.setBounds(12, 171, 93, 14);
        frame.getContentPane().add(lblMaxPlayerSize);
        
    }
}