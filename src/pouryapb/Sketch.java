package pouryapb;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Area;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Sketch extends JFrame {

	private static final long serialVersionUID = -7044496989599732077L;
	private DrawCanvas canvas;
	private transient ArrayList<Boundary> walls = new ArrayList<>();
	private transient Particle particle = new Particle(200, 100, new Vector(0, -1));

	private class DrawCanvas extends JPanel {

		private static final long serialVersionUID = 8193212504627188925L;

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			setBackground(Color.black);

			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			for (Boundary wall : walls) {
				if (!wall.exception)
					wall.update(g, new Color(255, 255, 255, 120));
			}

			particle.update(g);
			Polygon poly = particle.look(walls);

			// draw the light
			g.setColor(new Color(255, 255, 255, 50));
			g.fillPolygon(poly);

			Area shape = new Area(new Rectangle(199, 299, 22, 22));
			Area tempShape1 = new Area(new Rectangle(199, 299, 22, 22));
			Area tempShape2 = new Area(poly);
			tempShape1.subtract(tempShape2);
			shape.subtract(tempShape1);

			g.setColor(Color.red);
			((Graphics2D) g).fill(shape);
		}
	}

	public Sketch() {

		canvas = new DrawCanvas();
		setContentPane(canvas);

		setPreferredSize(new Dimension(800, 600));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setFocusable(true);
		requestFocusInWindow();
		setVisible(true);

		level();

		// red box
		walls.add(new Boundary(200, 300, 220, 300, true));
		walls.add(new Boundary(200, 300, 200, 320, true));
		walls.add(new Boundary(200, 320, 220, 320, true));
		walls.add(new Boundary(220, 300, 220, 320, true));

		canvas.addMouseMotionListener(new MouseMotionListener() {

			public void mouseMoved(MouseEvent arg0) {
				particle.setPos(arg0.getPoint());
				repaint();
			}

			public void mouseDragged(MouseEvent arg0) {
				// do nothing
			}
		});

	}

	private void level() {

		// frame walls
		walls.add(new Boundary(0, 0, 800, 0, true));
		walls.add(new Boundary(0, 0, 0, 600, true));
		walls.add(new Boundary(800, 0, 800, 600, true));
		walls.add(new Boundary(0, 600, 800, 600, true));

		boolean design = false;

		walls.add(new Boundary(50, 50, 50, 550, design));
		walls.add(new Boundary(150, 50, 150, 600, design));
		walls.add(new Boundary(350, 100, 350, 600, design));
		walls.add(new Boundary(450, 50, 450, 500, design));
		walls.add(new Boundary(590, 250, 590, 600, design));
		walls.add(new Boundary(750, 200, 750, 550, design));
		walls.add(new Boundary(250, 170, 250, 200, design));
		walls.add(new Boundary(700, 50, 700, 450, design));

		walls.add(new Boundary(50, 20, 800, 20, design));
		walls.add(new Boundary(500, 500, 590, 500, design));
		walls.add(new Boundary(640, 500, 750, 500, design));
		walls.add(new Boundary(50, 500, 350, 500, design));
		walls.add(new Boundary(200, 150, 350, 150, design));
		walls.add(new Boundary(50, 200, 330, 200, design));
		walls.add(new Boundary(350, 200, 550, 200, design));
		walls.add(new Boundary(150, 100, 300, 100, design));
		walls.add(new Boundary(150, 50, 350, 50, design));
		walls.add(new Boundary(500, 50, 750, 50, design));
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(Sketch::new);
	}
}
