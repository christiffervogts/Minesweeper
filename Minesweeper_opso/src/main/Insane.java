package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Insane implements ActionListener, MouseListener {

	
	//Variable descriptions:
	//number of tiles in the x
	//number of tiles in the y
	//Tile is the tiles that you click, easy game mode has a numb_of_tile_x x numb_of_tiles_y grid
	//bomb is the grid of possible places that a bomb could go
	//clicked trackes each tile and sees if it has been clicked yet
	//x_bomb is the x value for the bomb
	//y_bomb is the y value for the bomb
	//flag int is the number of flags you ger
	//first move is used to see if it is your first move	
	
	int numb_of_tiles_x = 30;
	int numb_of_tiles_y = 21;
	public JButton[][] tile = new JButton[numb_of_tiles_x][numb_of_tiles_y];
	public boolean[][] bomb = new boolean[numb_of_tiles_x][numb_of_tiles_y];
	public boolean[][] clicked = new boolean[numb_of_tiles_x][numb_of_tiles_y];
	int flag_int = 150;
	int[] x_bomb = new int[flag_int];
	int[] y_bomb = new int[flag_int];
	boolean first_move = true;

	public void hard() {
		        		
		int x = 40;
		int y = 10;
		
		//creates the random x and y values for the bomb
		for(int i = 0; i < x_bomb.length; i++) {
			x_bomb[i] = (int)(Math.random()*tile.length);
			y_bomb[i] = (int)(Math.random()*tile.length);
		}
		
		//creates the borad, and sets the settings for the borad
		for (int i = 0; i < tile.length; i++) {
			for (int j = 0; j < tile[i].length; j++) {
				
				tile[i][j] = new JButton();
				tile[i][j].setSize(40, 40);
				tile[i][j].setLocation(x, y);
				tile[i][j].addActionListener(this);
			    tile[i][j].setBackground(new Color(102, 102, 102));

				Main.Borad.add(tile[i][j]);
				
				tile[i][j].addMouseListener(this);
				bomb[i][j] = true;
				clicked[i][j] = false;

				y += 40;
				Main.Borad.repaint();
				Main.Borad.revalidate();
			
			}
			x = x + 40; 
			y = 10;
		}
		Main.Borad.setSize(1300, 1300);
		Main.Borad.setLocationRelativeTo(null);
		Main.Borad.repaint();
		Main.Borad.revalidate();
		Main.Borad.addMouseListener(this);
		int idx;

		//Check if any bomb is a duplicate; if it is then re genrate
		//I used a while loop so it can't keep choosing the same square

		
		while ((idx = findDuplicateIndex(x_bomb, y_bomb)) != -1) {

		    x_bomb[idx] = (int)(Math.random()*tile.length);
		    y_bomb[idx] = (int)(Math.random()*tile.length);
		}
		
	}
	
	public static int findDuplicateIndex(int[] x, int[] y) {
		
		//This stores the bomb array x and bomb array y then throws them into
		//a Hashmap and turns them into a string then checks the string if it matches any other string
		
	    HashMap<String, Integer> seen = new HashMap<>();

	    for (int i = 0; i < x.length; i++) {
	        String pair = x[i] + "," + y[i];

	        if (seen.containsKey(pair)) {
	            return i; 
	        }

	        seen.put(pair, i);
	    }
	    return -1; 
	}
	
	public int bomb_check(int i, int j) {
		
		//This uses an x and y value called i and j
		//i and j are the x and yh of the button just clicked
		//if the button just clicked is equal to the a bomb's x an y coordinate 
		//than the bomb_numb is returned to a value 9;
		//if it is not the same then the nine serounding tiles are checked
		//each time there is a bomb in the serounding tiles then we increase the bomb numbe by one
		//then return the bomb_numb
		
    	clicked[i][j] = true;

    	int bomb_numb = 0;

        for (int b = 0; b < x_bomb.length; b++) {
            if (x_bomb[b] == i && y_bomb[b] == j) {
                if(first_move && i != 0 && j != 0) {
                	x_bomb[b] = 0; y_bomb[b] = 0;
                	return 0;
                }
                else if(first_move &&i == 0 && j !=0) {
                	x_bomb[b] = 1; y_bomb[b] = 0;
                	return 0;

                }
                else if(first_move &&i != 0 && j ==0) {
                	x_bomb[b] = 0; y_bomb[b] = 1;
                	return 0;

                }
                else if(first_move &&i == 0 && j ==0) {
                	x_bomb[b] = 1; y_bomb[b] = 1;
                	return 0;

                }

                return 9;
            }
            else if(((x_bomb[b] == i+1 || x_bomb[b] == i-1)&& y_bomb[b] == j) 
            		||(((y_bomb[b] == j+1 || y_bomb[b] == j-1)&& x_bomb[b] == i))
            ||(((y_bomb[b] == j+1 || y_bomb[b] == j-1)&&(x_bomb[b] == i+1 || x_bomb[b] == i-1)))) {
            	 bomb_numb++;
            }
        }
        return bomb_numb;
	}
	public void reveal(int i, int j) {
		
		//when a button is clicked the reveil method is called and given it's x and y coordinates called i andj
		//we first check if the tile is in the borad, else we quit, then we check if the tile has already been clicked
		//then run bomb_numb with the bomb_check method
		//then change the background of the tile to be darker to know what has already been clicked
		//then put the number on the tile and color the text to be the right color
		//if bomb_numb is 9 run lose
		//if bomb_numb is 0 see how meny tiles are left, if it is the same number as number of bombs then you win
		//then check neighboring tiles;
		//then disable the button so it cannot be clicked again

		
	    if (i < 0 || j < 0 || i >= tile.length || j >= tile[i].length)
	        return;

	    if (!tile[i][j].isEnabled() && tile[i][j] != null ) {
	        return;
	    }
	    int bomb_numb = bomb_check(i, j);

	    tile[i][j].setBackground(new Color(255, 255, 255));
	    first_move = false;
	    switch(bomb_numb) {
	    case 1: tile[i][j].setText(String.valueOf(bomb_numb));	tile[i][j].setForeground(new Color(26, 28, 243  ));	return;
	    case 2: tile[i][j].setText(String.valueOf(bomb_numb));	tile[i][j].setForeground(new Color(12, 118, 2   ));	return;
	    case 3: tile[i][j].setText(String.valueOf(bomb_numb));	tile[i][j].setForeground(new Color(242, 2, 0    ));	return;
	    case 4: tile[i][j].setText(String.valueOf(bomb_numb));	tile[i][j].setForeground(new Color(8, 8, 124    ));	return;
	    case 5: tile[i][j].setText(String.valueOf(bomb_numb));	tile[i][j].setForeground(new Color(117, 0, 0	));	return;
	    case 6: tile[i][j].setText(String.valueOf(bomb_numb));	tile[i][j].setForeground(new Color(17, 124, 123 ));	return;
	    case 7: tile[i][j].setText(String.valueOf(bomb_numb));	tile[i][j].setForeground(new Color(0, 0, 0      ));	return;
	    case 8: tile[i][j].setText(String.valueOf(bomb_numb));	tile[i][j].setForeground(new Color(123, 123, 123));	return;
	    case 9:	tile[i][j].setBackground(Color.red);	lose();		 return;
	    case 0:	tile[i][j].setEnabled(false); tile[i][j].setBackground(Color.gray);	tiles_left();	revealNeighbors(i, j);	return;
	    }
	    tile[i][j].setEnabled(false);
	    

	}
	public void revealNeighbors(int i, int j) {
		
		//take the x and y called i and j
		//then just reviel if they are not a bomb
		
	    for (int dx = -1; dx <= 1; dx++) {
	        for (int dy = -1; dy <= 1; dy++) {

	            if (dx == 0 && dy == 0)
	                continue;

	            reveal(i + dx, j + dy);

	        }
	    }
	}
	public void tiles_left() {
		
		//start by seeing how meny tiles there are, when a button is clicked then the total is subtracted
		//when the number of tiles equals the number of bombs you win 
		
		int tiles_left = tile.length*tile[1].length;
		for(int i = 0; i < tile.length; i++) {
			for(int j = 0; j < tile[i].length; j++) {				
				if(clicked[i][j]) {
					tiles_left--;
					System.out.println(tiles_left);
				}
				if(tiles_left == 10) {
					win();
				}
			}

		}
		
	}
	public void lose() {
		//lose option menu

		Object[] options = {"Play Again?", "Quit"};
		
		int choice = JOptionPane.showOptionDialog(
			    Main.Borad,
			    "You blew up.",
			    "sorry.",
			    JOptionPane.DEFAULT_OPTION,
			    JOptionPane.DEFAULT_OPTION,
			    null,
			    options,
			    options[0]
			);
		
		
		if(choice == 0) {
			for(int i = 0; i < tile.length; i++) {
				for(int j = 0; j < tile[i].length; j++) {
					Main.Borad.remove(tile[i][j]);
				}
			}
			Setup setup = new Setup();
			
		}
		if(choice == 1) {
			System.exit(0);
		}

	}
	public void win() {
		//win option menu

		Object[] options = {"Play Again?", "Quit"};
		
		int choice = JOptionPane.showOptionDialog(
			    Main.Borad,
			    "You cleared the borad.",
			    "Congradulations.",
			    JOptionPane.DEFAULT_OPTION,
			    JOptionPane.DEFAULT_OPTION,
			    null,
			    options,
			    options[0]
			);
		
		
		if(choice == 0) {
			for(int i = 0; i < tile.length; i++) {
				for(int j = 0; j < tile[i].length; j++) {
					Main.Borad.remove(tile[i][j]);
				}
			}
			Setup setup = new Setup();
			
		}
		if(choice == 1) {
			System.exit(0);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//checks for when an action is preformed

		 for (int i = 0; i < tile.length; i++) {
		        for (int j = 0; j < tile[i].length; j++) {
		            if (e.getSource() == tile[i][j]) {
		            	reveal(i, j);
		                return;
		            }
		        }
		    }
		 first_move = false;
		}

	@Override
	public void mouseClicked(MouseEvent e) {
		//check for left mouse click
		for (int i = 0; i < tile.length; i++) {
			for(int j = 0; j < tile[i].length; j++) {
				if (e.getButton() == MouseEvent.BUTTON3 && e.getSource() == tile[i][j] && !clicked[i][j] && flag_int != 0) {
		            System.out.println(("Right click detected: " + i +", "+j));
		            
		            if(bomb[i][j]) {
		            	bomb[i][j] = false;
			            tile[i][j].removeActionListener(this);
			            tile[i][j].setBackground(Color.BLACK);
			            flag_int--;
			            return;
		            }
		            if(!bomb[i][j]) {
		            	bomb[i][j] = true;
			            tile[i][j].addActionListener(this);
			            tile[i][j].setBackground(null);
			            flag_int++;
			            return;
		            }

		        } 
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}


}
