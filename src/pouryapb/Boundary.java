package pouryapb;

import java.awt.Color;
import java.awt.Graphics;

public class Boundary {

	public Vector start = new Vector();
	public Vector end = new Vector();
	public boolean exception = false;
	
	public Boundary(float x1, float y1, float x2, float y2, boolean exception) {
		
		start = new Vector(x1, y1);
		end = new Vector(x2, y2);
		this.exception = exception;
	}
	
	public void update(Graphics g, Color c) {
		
		g.setColor(c);
		g.drawLine((int)start.x, (int)start.y, (int)end.x, (int)end.y);
	}
}
