/*  Assignment Bonus: Master Mind
 *  Create game, Master Mind, with Java
 *  Section: 16185
 *  Name1: Doyoung Kim
 *  UTEID1: dk24338
 *  Name2: Hyo-Jung Kim
 *  UTEID2: hk6336
 */
package assignmentBonus;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MasterMind extends Applet implements MouseListener, MouseMotionListener{
	private Color burntOrange = new Color (0.9F, 0.33F, 0.0F);
	private final Color BROWN = new Color(165, 42, 42);
	
	private boolean victory, gameover;
	private int x, y;
	private int mouseX, mouseY;
	private boolean leftClick;
	private int guessNumber, pegNumber, numTry;
	private Rectangle board;
	private ColorPalette cp;
	private MMButton checkButton, pegButton, guessButton, resetButton, helpButton;
	private ArrayList<BoardRow> rows;
	private Color selectedColor;
	private ArrayList<FeedBack> feed_Back;
	private Generator answer;
	private AudioClip soundFile1;
	private AudioClip soundFile2;
	
	private boolean audio_Done1 = false;
	private boolean audio_Done2 = false;
	public void init(){
		
		soundFile1 = getAudioClip(getCodeBase(),"sound/fanfare.wav");
		soundFile2 = getAudioClip(getDocumentBase(),"sound/fail2.wav"); 
		guessNumber = 6;
		pegNumber = 4;
		mouseX = 0;
		mouseY = 0;
		leftClick = false;
		addMouseListener(this);
		addMouseMotionListener(this);
		
		ResetBoard();
	}
	
	private void ResetBoard(){
		victory = false;
		gameover = false;
		numTry = 1;
		x = 36 + (6 - pegNumber)*18;
		board = new Rectangle(x, 36, 36*pegNumber, 36 + 36*guessNumber);
		y = guessNumber * 36 + 36;
		checkButton = new MMButton(x + board.width, y, 36, 36, "GO");
		pegButton = new MMButton(0, 0, 68, 36, pegNumber + " Pegs");
		guessButton = new MMButton(68, 0, 80, 36, guessNumber + " Tries");
		resetButton = new MMButton(148, 0, 70, 36, "Restart");
		helpButton = new MMButton(228, 0, 66, 36, "HELP!");
		rows = new ArrayList<BoardRow>();
		rows.add(new BoardRow(x, y, pegNumber));
		cp = new ColorPalette(36, y + 36);
		selectedColor = cp.GetColor(0);
		feed_Back = new ArrayList<FeedBack>();
		answer = new Generator (x,36,pegNumber);
		audio_Done1 = false;
		audio_Done2 = false;
	}
	
	public void mouseEntered( MouseEvent e ) { }
	public void mouseExited( MouseEvent e ) { }
	public void mousePressed( MouseEvent e ) {
		leftClick = true;
		repaint();
	}
	public void mouseReleased( MouseEvent e ) {
		leftClick = false;
		repaint();
	}
	public void mouseClicked( MouseEvent e ) {
		// called after a press and release of a mouse button with no motion in between
	    // (If the user presses, drags, and then releases, there will be no click event generated.)
		
		//check if the mouse click was made inside pegs and change the color, if it was
		rows.get(rows.size() - 1).Click(mouseX, mouseY, selectedColor);
		Color tempColor = cp.Click(mouseX, mouseY);
		if(tempColor != Color.BLACK){
			selectedColor = tempColor;
		}
		//if click was made inside checkRect and if guess is complete, meaning every color was changed
		//at least once, check the guess with answer and proceed accordingly
		if(checkButton.Contains(mouseX, mouseY)){// && rows.get(rows.size() - 1).IsComplete()){
			
			if (rows.get(rows.size() - 1).IsComplete()){
				//get the feedback from checking
				feed_Back.add(new FeedBack(x-36 ,y, pegNumber, answer.get_answer(), rows.get(rows.size()-1).GetGuess()));
				//if guess was correct, player wins
				if(feed_Back.get(feed_Back.size() - 1).Chk_all()){
					checkButton.SetXY(-100, -100);
					answer.Done();
					victory = true;
				}
				//else try again until player wins or runs out of tries
				else{
					if(numTry < guessNumber){
						numTry++;
						y -= 36;
						rows.add(new BoardRow(x, y, pegNumber));
						checkButton.SetXY(x + board.width, y);
					}
					else{
						checkButton.SetXY(-100, -100);
						answer.Done();
						gameover = true;
					}
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "INVALID INPUT: Fill all the Blank Spots!");
			}
		}
		else if(guessButton.Contains(mouseX, mouseY)){
			boolean correctFormat = false;
			while(!correctFormat){
				try{
					JFrame frame = new JFrame("Number of Guesses");
					int g = Integer.parseInt(JOptionPane.showInputDialog(frame , "How many guesses do you want? (6~12)"));
					if(g >= 6 && g <= 12){
						if(guessNumber != g){
							guessNumber = g;
							ResetBoard();
						}
						correctFormat = true;
					}
					else{
				        JOptionPane.showMessageDialog(null, "Please choose 6~12");
					}
				}
				catch(NumberFormatException ex){
			        JOptionPane.showMessageDialog(null, "Not a Number!");
				}
			}
		}
		else if(pegButton.Contains(mouseX, mouseY)){
			boolean correctFormat = false;
			while(!correctFormat){
				try{
					JFrame frame = new JFrame("Number of Pegs");
					int p = Integer.parseInt(JOptionPane.showInputDialog(frame , "How many pegs do you want? (4~6)", pegNumber));
					if(p >= 4 && p <= 6){
						if(pegNumber != p){
							pegNumber = p;
							ResetBoard();
						}
						correctFormat = true;
					}
					else{
				        JOptionPane.showMessageDialog(null, "Please choose 4~6");
					}
				}
				catch(NumberFormatException ex){
			        JOptionPane.showMessageDialog(null, "Not a Number!");
				}
			}
		}
		else if(resetButton.Contains(mouseX, mouseY)){
			int reply = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);
	        if (reply == JOptionPane.YES_OPTION) {
	        	ResetBoard();
	        }
		}
		else if(helpButton.Contains(mouseX, mouseY)){
			JOptionPane.showMessageDialog(null, "Welcome to The DoyoungKim && HyoJungKim's MasterMind\n"
															+ "The computer will think of a secret code. \n"
															+ "The code consists of 4 to 6 colored pegs. \n"
															+ "The pegs may be one of six colors: Red, Orange, Yellow, Green, Blue, Purple.\n"
															+ "A color may appear more than once in the code.\n"
															+ "You try to guess what colored pegs are in the code and what order they are in.\n"
															+ "After you make a valid guess the result (feedback) will be displayed. \n"
															+ "The result consists of a black peg for each peg you have guessed exactly correct (color and position) in your guess.  \n"
															+ "For each peg in the guess that is the correct color, but is out of position, you get a white peg.\n"
															+ "For each peg, which is fully incorrect, you get no feedback.\n\n" +
															" Now Assuming you know the Basic Rules about how to play the Game,\n" + 
															" Let me explain, how to SETUP the game mode.\n" + 
															" 1. On the Top left, there is a \"Pegs\" button deciding how many pegs you can play with.\n"
															+ "	       -       you can choose 4, 5, or 6.\n" + 
															" 2. \"Tries\" button choose how many guesses you can try\n"
															+ "	       -       You can choose number from 6 to 12.\n" + 
															" 3. \"Restart\" button reset the game, as you know the meaning \"reset.\"\n" + 
															" Hope you enjoy our game.\n");
		}
		repaint();
	}
	
	// called during motion when no buttons are down
	public void mouseMoved(MouseEvent e){
		mouseX = e.getX();
		mouseY = e.getY();
		checkButton.SetMouse(mouseX, mouseY);
		guessButton.SetMouse(mouseX, mouseY);
		pegButton.SetMouse(mouseX, mouseY);
		resetButton.SetMouse(mouseX, mouseY);
		helpButton.SetMouse(mouseX, mouseY);
		repaint();
		e.consume();
	}
	// called during motion with buttons down
	public void mouseDragged(MouseEvent e){
	}
	
	//for double buffer
	public void update(Graphics g){
		//double buffer used to avoid flickering
    	Graphics offgc;
        Image offscreen = null;
        Dimension d = this.getSize();

        // create the offscreen buffer and associated Graphics
        offscreen = createImage(d.width, d.height);
        offgc = offscreen.getGraphics();
        // clear the exposed area
        offgc.setColor(getBackground());
        offgc.fillRect(0, 0, d.width, d.height);
        offgc.setColor(getForeground());
        // do normal redraw
        paint(offgc);
        // transfer offscreen to window
        g.drawImage(offscreen, 0, 0, this);
	}
	public void paint(Graphics g) {
		//max size Width 288, Height 540 (289, 541 as parameter)
		//up to 6pegs, up to 12 guesses
		//extra space on top for options
		//extra space on bottom for selecting a color
		//most top row is always reserved for the answer(generated pattern)
		//maybe make each rows into a separate class
    	Graphics2D g2 = (Graphics2D)g;
    	g2.setColor(BROWN);
    	g2.fill(board);
    	g2.setColor(Color.BLACK);
    	g2.draw(board);
    	g.setFont(new Font("Arial", Font.BOLD, 23));
    	g.drawString("By Doyoung", 77, 140);
    	g.drawString("& Hyo-Jung", 77, 180);
		for(int i = 0; i < rows.size(); i++){
			rows.get(i).Draw(g);
		}
		//feedback
		answer.Draw(g2);
		for(int i = 0 ; i < feed_Back.size() ; i ++){
			feed_Back.get(i).Draw(g2);
		}
		//draw Buttons
		checkButton.Draw(g, leftClick);
		guessButton.Draw(g, leftClick);
		pegButton.Draw(g, leftClick);
		resetButton.Draw(g, leftClick);
		helpButton.Draw(g, leftClick);
		//color palette
    	cp.Draw(g);
    	g.setFont(new Font("Arial", Font.BOLD, 45));
    	g.setColor(Color.BLACK);
	    //g.drawString("(" + mouseX + "," + mouseY + ")", 0, 10);
	    if(victory){
	   	 g.drawString("YOU WON!", 35, 100);
	    	
	    	if(!audio_Done1) {
	    		soundFile1.play();
	    		audio_Done1 = true;
	    	}
	    }
	    else if(gameover){
	    	g.drawString("GAME OVER!", 5, 100);
	    	if(!audio_Done2) {
	    		soundFile2.play();
	    		audio_Done2 = true;
	    	}
	    	
	    }
	}
}
