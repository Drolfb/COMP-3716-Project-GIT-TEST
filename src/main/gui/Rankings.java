package main.gui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
 
public class Rankings extends JFrame
{
    public Rankings()
    {
        //headers for the table
        String[] columns = new String[] {
            "Team Name", "Wins", "Loses", "Skill Rating"
        };
         
        //actual data for the table in a 2d array
        Object[][] data = new Object[][] {
            {"BobCats", 1, 5, 4 },
            {"Team 2", 3, 2, 5 },
            {"Zorro", 4, 4, 1 },
            {"Crunk", 10, 2, 3 },
        };
 
        //create table with data
        JTable table = new JTable(data, columns);
         
        //add the table to the frame
        this.add(new JScrollPane(table));
         
        this.setTitle("Rankings");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        this.pack();
        this.setVisible(true);
    }
     
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Rankings();
            }
        });
    }   
} 