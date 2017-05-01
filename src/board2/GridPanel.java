package board2;


import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.*;

import javax.swing.JLayeredPane;

//import mine.PointLabel;

public class GridPanel extends JLayeredPane implements ComponentListener{
	
	private int center_x, center_y, slice, width, height, radius;

	// X values for line segments
	private int r1x, r2x, r3x, r4x;

	// Y values for line segments
	private int r1y, r2y, r3y, r4y;

	private Dimension size;
	public CrossPointLabel[][] points = new CrossPointLabel[4][12];
	
	//Radian values for diagonals
	private final double RADIAN1 = Math.PI / 6.0;
	private final double RADIAN1_X = Math.cos(RADIAN1);
	private final double RADIAN1_Y = Math.sin(RADIAN1);
	private final double RADIAN2 = Math.PI / 3.0;
	private final double RADIAN2_X = Math.cos(RADIAN2);
	private final double RADIAN2_Y = Math.sin(RADIAN2);
	private final double RADIAN3 = 2.0 * Math.PI / 3.0;
	private final double RADIAN3_X = Math.cos(RADIAN3);
	private final double RADIAN3_Y = Math.sin(RADIAN3);
	private final double RADIAN4 = 5.0 * Math.PI / 6.0;
	private final double RADIAN4_X = Math.cos(RADIAN4);
	private final double RADIAN4_Y = Math.sin(RADIAN4);
	private final Point2D.Double[] RADIANS = {
			new Point2D.Double(RADIAN1_X, RADIAN1_Y),
			new Point2D.Double(RADIAN2_X, RADIAN2_Y),
			new Point2D.Double(RADIAN3_X, RADIAN3_Y),
			new Point2D.Double(RADIAN4_X, RADIAN4_Y)
	};
	
	public GridPanel() {
		for (int line = 0; line < 4; line ++) {
			for(int vert = 0; vert < 12; vert++){
				//System.out.println(line + "," + vert);
				points[line][vert] = new CrossPointLabel(line, vert);
				add(points[line][vert]);
			}
		}
		this.addComponentListener(this);
	}
	
	public void componentResized(ComponentEvent e) { 
		double radian = Math.PI / 6.0;
		double current_radian = 0;
		
		// The cross-points need to be repositioned whenever the panel changes size
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 12; j++) {
				points[i][j].setLocation(getSize(), current_radian, i+1, 0.1);
				//System.out.println((i-1)+"  " +(j-1));
				//System.out.println(points[i][j].location.getX()+" "+points[i][j].location.getY());
				current_radian += radian;
			}
			current_radian = 0;
		}
	}
	
	public void componentMoved(ComponentEvent e) { }

	public void componentShown(ComponentEvent e) { }

	public void componentHidden(ComponentEvent e) { }

	public CrossPointLabel nearestPoint(int x, int y) {
		CrossPointLabel nearest = new CrossPointLabel(30000000, 30000000);
		double min_distance = Double.MAX_VALUE;
		double current_distance;
		for (CrossPointLabel[] p : points) {
			for(CrossPointLabel q : p){
			current_distance = q.distance(x,y);
				if ( current_distance < min_distance) {
					min_distance = current_distance;
					nearest = q;
				}
			}
		}
		return nearest;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2));
		g2.setColor(Color.BLACK);
		
		radius = 0;
		size = this.getSize();
		width = size.width;
		height = size.height;
		slice = (int) Math.ceil(width/10);
		center_x = (int) Math.ceil(width / 2.0);
		center_y = (int) Math.ceil(height / 2.0);
		
		//Draw layered circles
		for (int i = 1; i <= 4; i++) {
			radius += slice;
			g2.draw(new Ellipse2D.Double( center_x-radius, center_y-radius, radius*2, radius*2));
		}

		// Draw vertical and horizontal lines
		g2.draw(new Line2D.Double(center_x-radius-20, center_y, center_x+radius+20, center_y));
		g2.draw(new Line2D.Double(center_x, center_y-radius-20, center_x, center_y+radius+20));
		
		//Coordinates for intersecting diagonals
		r1x = (int) Math.ceil( center_x + ( radius * RADIAN1_X) );
		r1y = (int) Math.ceil( center_y + ( radius * RADIAN1_Y) );
		r2x = (int) Math.ceil( center_x + ( radius * RADIAN2_X) );
		r2y = (int) Math.ceil( center_y + ( radius * RADIAN2_Y) );
		r3x = (int) Math.ceil( center_x + ( radius * RADIAN3_X) );
		r3y = (int) Math.ceil( center_y + ( radius * RADIAN3_Y) );
		r4x = (int) Math.ceil( center_x + ( radius * RADIAN4_X) );
		r4y = (int) Math.ceil( center_y + ( radius * RADIAN4_Y) );
		
		//Draw intersecting diagonals
		g2.draw(new Line2D.Double(r1x,r1y,(center_x-(r1x-center_x)),(center_y-(r1y-center_y))));
		g2.draw(new Line2D.Double(r2x,r2y,(center_x-(r2x-center_x)),(center_y-(r2y-center_y))));
		g2.draw(new Line2D.Double(r3x,r3y,(center_x-(r3x-center_x)),(center_y-(r3y-center_y))));
		g2.draw(new Line2D.Double(r4x,r4y,(center_x-(r4x-center_x)),(center_y-(r4y-center_y))));
		
	}


}