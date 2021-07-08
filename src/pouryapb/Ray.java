package pouryapb;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Ray {

	public Vector pos, dir;
	
	public Ray(float x, float y) {
		pos = new Vector(x, y);
		dir = new Vector(1, 0);
	}
	
	public Ray(Vector pos, float angle) {
		this.pos = pos;
		dir = Vector.VectorFromAngle((float) Math.toRadians(angle));
	}
	
	public void setDir(Point p) {
		dir = new Vector(p.x - pos.x, p.y - pos.y);
		dir.normalize();
	}
	
	public void update(Graphics g) {
		
		g.setColor(Color.white);
		g.drawLine((int)pos.x, (int)pos.y, (int)(pos.x + dir.x * 10), (int)(pos.y + dir.y * 10));
	}
	
	public Vector cast(Boundary wall) {
		
		float x1 = wall.start.x;
		float y1 = wall.start.y;
		float x2 = wall.end.x;
		float y2 = wall.end.y;
		
		float x3 = pos.x;
		float y3 = pos.y;
		float x4 = pos.x + dir.x;
		float y4 = pos.y + dir.y;
		
		float den = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
		
		if (den == 0)
			return null;
		
		float t = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / den;
		float u = -((x1 - x2) * (y1 - y3) - (y1 - y2) * (x1 - x3)) / den;
		
		if (t > 0 && t < 1 && u > 0) {
			Vector pt = new Vector();
			
			pt.x = x1 + t * (x2 - x1);
			pt.y = y1 + t * (y2 - y1);
			
			return pt;
		}
		else
			return null;
	}
}
