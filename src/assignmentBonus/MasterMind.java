/*  Assignment Bonus: Master Mind
 *  Create game, Master Mind, with Java
 *  Section: 16185
 *  Name1: Doyoung Kim
 *  UTEID1: dk24338
 *  Name2:
 *  UTEID2:
 */
package assignmentBonus;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class MasterMind extends Applet implements MouseListener, MouseMotionListener{
	
	private int mouseX, mouseY;
	public void init(){
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
	
	public void paint(Graphics g) {
	    g.drawString("(" + mouseX + "," + mouseY + ")", 0, 10);
	}
}
