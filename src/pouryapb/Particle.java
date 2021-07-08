package pouryapb;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

public class Particle {

	public Vector pos;
	public Vector dir;
	public ArrayList<Ray> rays = new ArrayList<Ray>();
	
	public Particle(float x, float y, Vector dir) {
		pos = new Vector(x, y);
		this.dir = dir;
		
		for (float i = 0; i <= 360; i += 0.1f) {
			rays.add(new Ray(pos, i));
		}
		
//		double theta = Math.toDegrees(Math.atan2(dir.y, dir.x));
//		
//		for (float i = (float) (theta - 30); i < (float) (theta + 30); i += 0.1f) {
//			rays.add(new Ray(pos, i));
//		}
	}
	
	public void setPos(Point p) {
		pos.x = p.x;
		pos.y = p.y;
	}
	
	public void updateDir(Vector dir) {
		this.dir = dir;
		
		double theta = Math.toDegrees(Math.atan2(dir.y, dir.x));
		
		rays = new ArrayList<Ray>();
		
		for (float i = (float) (theta - 30); i < (float) (theta + 30); i += 0.1) {
			rays.add(new Ray(pos, i));
		}
	}
	
	public void update(Graphics g) {
		
		g.setColor(new Color(255, 255, 255, 120));
		g.fillOval((int)pos.x - 4, (int)pos.y - 4, 8, 8);
	}
	
	public Polygon look(ArrayList<Boundary> walls) {
		
		ArrayList<Vector> points = new ArrayList<Vector>();
		
		for (Ray ray : rays) {
			Vector closest = null;
			float record = Float.MAX_VALUE;
			for (Boundary wall : walls) {
				Vector pt = ray.cast(wall);
				if (pt != null) {
					float d = (float) Math.sqrt(Math.pow(pos.x - pt.x, 2) + Math.pow(pos.y - pt.y, 2));
					if (d < record) {
						record = d;
						closest = pt;
					}
				}
			}
			if (closest != null)
				points.add(closest);
		}
		
		Polygon poly = new Polygon();
		
		for (Vector point : points) {
			poly.addPoint((int)point.x, (int)point.y);
		}
		
		return poly;
	}
}
