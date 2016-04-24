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

public class MasterMind extends Applet implements MouseListener, MouseMotionListener{
	
	private int mouseX, mouseY;
	private int guessNumber, pegNumber;
	public void init(){
		guessNumber = 12;
		pegNumber = 4;
		mouseX = 0;
		mouseY = 0;
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void mouseEntered( MouseEvent e ) { }
	public void mouseExited( MouseEvent e ) { }
	public void mousePressed( MouseEvent e ) { }
	public void mouseReleased( MouseEvent e ) { }
	public void mouseClicked( MouseEvent e ) {
		// called after a press and release of a mouse button with no motion in between
	    // (If the user presses, drags, and then releases, there will be no click event generated.)
	}
	
	// called during motion when no buttons are down
	public void mouseMoved(MouseEvent e){
		mouseX = e.getX();
		mouseY = e.getY();
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
		for(int i = 1; i < 14; i++){
		    g.drawRect(36, 36*i, 36*6, 36);
		    g.drawOval(39, 3 + 36*i, 30, 30);
		    g.drawOval(39 + 36, 3 + 36*i, 30, 30);
		    g.drawOval(39 + 72, 3 + 36*i, 30, 30);
		    g.drawOval(39 + 108, 3 + 36*i, 30, 30);
		    g.drawOval(39 + 144, 3 + 36*i, 30, 30);
		    g.drawOval(39 + 180, 3 + 36*i, 30, 30);
	    }
		g.drawRect(0, 468, 36, 36);//feedback
		g.drawRect(252, 468, 36, 36);//confirmButton
	    g.drawString("(" + mouseX + "," + mouseY + ")", 0, 10);
	}
}
