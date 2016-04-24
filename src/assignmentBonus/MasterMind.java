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
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MasterMind extends Applet implements MouseListener, MouseMotionListener{

	private final Color BROWN = new Color(165, 42, 42);
	
	private boolean victory, gameover;
	private int x, y;
	private int mouseX, mouseY;
	private boolean leftClick;
	private int guessNumber, pegNumber, numTry;
	private Rectangle board;
	private ColorPalette cp;
	private MMButton checkButton;
	private ArrayList<BoardRow> rows;
	private Color selectedColor;
	private ArrayList<FeedBack> feed_Back;
	private Generator answer;
	
	public void init(){
		guessNumber = 8;
		pegNumber = 6;
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
		rows = new ArrayList<BoardRow>();
		rows.add(new BoardRow(x, y, pegNumber));
		cp = new ColorPalette(36, y + 36);
		selectedColor = cp.GetColor(0);
		feed_Back = new ArrayList<FeedBack>();
		answer = new Generator (x,36,pegNumber);  // location has to be changed
		
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
		if(checkButton.Contains(mouseX, mouseY) && rows.get(rows.size() - 1).IsComplete()){
			//get the feedback from checking
			//if guess was correct, player wins
			feed_Back.add(new FeedBack(x-36 ,y, pegNumber, answer.get_answer(), rows.get(rows.size()-1).GetGuess()));
			
			//else try again until player wins or runs out of tries
			if(numTry < guessNumber){
				numTry++;
				y -= 36;
				rows.add(new BoardRow(x, y, pegNumber));
				checkButton.SetXY(x + board.width, y);
			}
			else{
				checkButton.SetXY(-100, -100);
				gameover = true;
			}
		}
		repaint();
	}
	
	// called during motion when no buttons are down
	public void mouseMoved(MouseEvent e){
		mouseX = e.getX();
		mouseY = e.getY();
		checkButton.SetMouse(mouseX, mouseY);
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
		for(int i = 0; i < rows.size(); i++){
			rows.get(i).Draw(g);
		}
		//feedback
		answer.Draw(g2);
		for(int i = 0 ; i < feed_Back.size() ; i ++){
			feed_Back.get(i).Draw(g2);
		}
		//checkButton
		checkButton.Draw(g, leftClick);
		//color palette
    	cp.Draw(g);
	    g.drawString("(" + mouseX + "," + mouseY + ")", 0, 10);
	    if(victory){
	    	
	    }
	    else if(gameover){
	    	g.drawString("GAME OVER", 80, 20);
	    }
	}
}
