package main.gui;

import javax.swing.JFrame;
import java.awt.Color;

public class LoginPageRunner
{
    /**
     Creates the main frame of the program.
     */
    public static void main(String[] args)
    {
    	
        LoginPagePanel panel = new LoginPagePanel();
        panel.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.frame.setSize(300, 275);
        panel.frame.setResizable(false); // let's not allow this shitshow
        panel.frame.setLocationRelativeTo(null); // center the window
        panel.setBackground(Color.LIGHT_GRAY);
        panel.frame.getContentPane().add(panel);
        panel.frame.setVisible(true);
    }
}
