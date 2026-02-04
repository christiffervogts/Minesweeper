package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class Setup extends Main implements ActionListener{
	
	// These are the four game modes, no game mode has a constructor because I call them so early.
	
	Easy easy_mode = new Easy();
	Medium medium_mode = new Medium();
	Hard hard_mode = new Hard();
	Insane insane_mode = new Insane();
	
	boolean selected = false;
	public Setup() {
		setup();
	}
	
	public void setup() {
		
		//Borad Settings and Button setings
				
		Borad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Borad.setVisible(true);
		Borad.setResizable(false);
		Borad.setSize(500, 500);
		Borad.setLocationRelativeTo(null);
		Borad.setLayout(null);
		
		Easy.setLocation(30, 10);
		Easy.setSize(200, 100);
		Easy.setBackground(Color.gray);
		Easy.setForeground(new Color(26, 28, 243));
		
		Medium.setLocation(260, 10);
		Medium.setSize(200, 100);
		Medium.setBackground(Color.gray);
		Medium.setForeground(new Color(12, 118, 2));

		Hard.setLocation(30, 120);
		Hard.setSize(200, 100);
		Hard.setBackground(Color.gray);
		Hard.setForeground(new Color(242, 2, 0));

		Insane.setLocation(260, 120);
		Insane.setSize(200, 100);
		Insane.setBackground(Color.gray);
		Insane.setForeground(new Color(8, 8, 124));

		Nevermind.setLocation(150, 230);
		Nevermind.setSize(200, 100);
		Nevermind.setBackground(Color.gray);
		Nevermind.setForeground(new Color(0, 0, 0));

		Easy.addActionListener(this);
		Medium.addActionListener(this);
		Hard.addActionListener(this);
		Insane.addActionListener(this);
		Nevermind.addActionListener(this);

		Borad.getContentPane().setBackground(new Color(150, 150, 150));
		Borad.add(Easy);
		Borad.add(Medium);
		Borad.add(Hard);
		Borad.add(Insane);
		Borad.add(Nevermind);
		
		Borad.repaint();
		Borad.revalidate();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//desition tree for button clicking
		//selected is needed because if it is not here then the button will be counted as pressed sevral times.
		//each button does basically the same thing just to a diffrent game mode, except nevermind, that closes the game 
		if(e.getSource() == Easy && !selected) {	
			selected = true;
			Borad.remove(Easy);
			Borad.remove(Medium);
			Borad.remove(Hard);
			Borad.remove(Insane);
			Borad.remove(Nevermind);
			easy_mode.easy();
		}
		if(e.getSource() == Medium && !selected) {			
			selected = true;
			Borad.remove(Easy);
			Borad.remove(Medium);
			Borad.remove(Hard);
			Borad.remove(Insane);
			Borad.remove(Nevermind);
			medium_mode.medium();
		}
		if(e.getSource() == Hard && !selected) {			
			selected = true;
			Borad.remove(Easy);
			Borad.remove(Medium);
			Borad.remove(Hard);
			Borad.remove(Insane);
			Borad.remove(Nevermind);
			hard_mode.hard();
		}
		if(e.getSource() == Insane && !selected) {			
			selected = true;
			Borad.remove(Easy);
			Borad.remove(Medium);
			Borad.remove(Hard);
			Borad.remove(Insane);
			Borad.remove(Nevermind);
			insane_mode.hard();
		}
		if(e.getSource() == Nevermind) {			
			System.exit(0);
		}

	}
}
