package Other;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

public class CrossPointLabel extends JLabel{
	
	private static final long serialVersionUID = 1L;
	private boolean marked;
	private Point location;
//	private PolarCoordinate gameCoordinate;
	//private Game game;
	boolean XisHuman;
	boolean OisHuman;

	public CrossPointLabel (Dimension size, double radian, int layer, double slice) {
		marked = false;
		setText("");
		setVerticalAlignment(JLabel.CENTER);
		setHorizontalAlignment(JLabel.CENTER);
		setForeground(Color.RED);
		setBackground(Color.GRAY);
		setFont(new Font("Arial", Font.BOLD, 20));
		setLocation( size, radian, layer, slice);
		setMouseAction();
	}
	
	public CrossPointLabel() {
		marked = false;
		setText("");
		setVerticalAlignment(JLabel.CENTER);
		setHorizontalAlignment(JLabel.CENTER);
		setForeground(Color.RED);
		setBackground(Color.GRAY);
		setFont(new Font("Arial", Font.BOLD, 20));
		//setLocation( size, radian, layer, slice);
		setMouseAction();
	}

	//A dummy label initialized as far as possible from points on the grid
//	public CrossPointLabel (Game g, PolarCoordinate c) {
//		game = g;
//		gameCoordinate = c;
//		setText("");
//		setVerticalAlignment(JLabel.TOP);
//		setHorizontalAlignment(JLabel.CENTER);
//		setForeground(Color.RED);
//		setBackground(Color.LIGHT_GRAY);
//		setFont(new Font("Arial", Font.BOLD, 20));
//		location = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
//		marked = false;
//		setMouseAction();
//		XisHuman = g.getPlayerX().isHuman();
//		OisHuman = g.getPlayerO().isHuman();
//	}
	public void setMouseAction() {
		this.addMouseListener( new MouseAdapter() {
//			
			@Override
			public void mouseClicked(MouseEvent e) {
//				UnTestedCoordinates updateCoords = new UnTestedCoordinates(gameCoordinate.getX(), gameCoordinate.getY());			
//				// check if the current player is human.
//				if((game.getCurrentPlayer()&&XisHuman)) {
//					game.getPlayerX().Update(updateCoords);
//				} else if ((!game.getCurrentPlayer()&&OisHuman)) {
//					game.getPlayerO().Update(updateCoords);
//				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				CrossPointLabel.this.setOpaque(true);
				CrossPointLabel.this.repaint();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				CrossPointLabel.this.setOpaque(false);
				CrossPointLabel.this.repaint();
			}
			
		});
	}
	
	public void setLocation(Dimension windowSize, double radian, int layer, double slice) {
		
		int center_x = (int) Math.ceil(windowSize.width / 2.0); 
		int center_y = (int) Math.ceil(windowSize.height / 2.0);
		int radius = (int) (windowSize.width * slice * layer);

		int px = (int) Math.ceil( center_x + ( radius * Math.cos(radian))); 
		int py = (int) Math.ceil( center_y + ( radius * Math.sin(radian)));
		
		location = new Point(px, py);
		setBounds(location.x-12, location.y-12, 24, 24);
		repaint();
	
	}
	
	public Point getLocation() {
		return location;
	}

	public void setMarked(boolean m) {
		marked = m;
	}
	
	public boolean isMarked() {
		return marked;
	}
	
	public double distance(int x, int y) {
		return location.distance(x,y);
	}
	
}