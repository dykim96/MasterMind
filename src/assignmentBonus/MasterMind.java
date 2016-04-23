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

public class MasterMind extends Applet implements KeyListener{

	String s = "";
	public void init(){
		addKeyListener(this);
	}
	public void keyPressed( KeyEvent e ) { }
	public void keyReleased( KeyEvent e ) {
		char c = e.getKeyChar();
		if ( c != KeyEvent.CHAR_UNDEFINED ) {
			s = s + c;
			System.out.println(s);
			repaint();
			e.consume();
		}
	}
	public void keyTyped( KeyEvent e ) {}
	
	public void paint(Graphics g) {
	    g.drawString(s, 0, 10);
	}
}
