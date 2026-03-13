package main;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Main{

	public static JFrame Borad = new JFrame();
	public JButton Easy = new JButton("Easy");
	public JButton Medium = new JButton("Medium");
	public JButton Hard = new JButton("Hard");
	public JButton Insane = new JButton("Insane");
	public JButton Nevermind = new JButton("Nevermind");

	public static void main(String[] args) {
		//I prefer to do everything in a sprate class.
		//But I create the Main parts of the game here. 
		//The first four buttons are made and the JFrame is created here.
		Setup set = new Setup();
		
		set.setup();
	}

}
