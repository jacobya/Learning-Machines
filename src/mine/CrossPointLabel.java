package mine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

public class CrossPointLabel extends JLabel {

	// private static final long serialVersionUID = 1L;
	private boolean marked = false;
	Point location;
	// private PolarCoordinate gameCoordinate;
	// private Game game;
	boolean XisHuman;
	boolean OisHuman;
	public int line;
	public int vert;

	public CrossPointLabel(Dimension size, double radian, int layer,
			double slice) {
		marked = false;
		setText("");
		setVerticalAlignment(JLabel.CENTER);
		setHorizontalAlignment(JLabel.CENTER);
		setForeground(Color.RED);
		setBackground(Color.GRAY);
		setFont(new Font("Arial", Font.BOLD, 20));
		setLocation(size, radian, layer, slice);
		setMouseAction();
	}

	public CrossPointLabel(int in_line, int in_vert) {
		line = in_line;
		vert = in_vert;
		marked = false;
		setText(Integer.toString(line)+','+Integer.toString(vert));
		setVerticalAlignment(JLabel.CENTER);
		setHorizontalAlignment(JLabel.CENTER);
		setForeground(Color.RED);
		setBackground(Color.GRAY);
		setFont(new Font("Cursor", Font.BOLD, 22));
		//setLocation( getSize(), radian, layer, slice);
		setMouseAction();
	}

//	 A dummy label initialized as far as possible from points on the grid
//	 public CrossPointLabel (Game g, PolarCoordinate c) {
//	 game = g;
//	 gameCoordinate = c;
//	 setText("");
//	 setVerticalAlignment(JLabel.TOP);
//	 setHorizontalAlignment(JLabel.CENTER);
//	 setForeground(Color.RED);
//	 setBackground(Color.LIGHT_GRAY);
//	 setFont(new Font("Arial", Font.BOLD, 20));
//	 location = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
//	 marked = false;
//	 setMouseAction();
//	 XisHuman = g.getPlayerX().isHuman();
//	 OisHuman = g.getPlayerO().isHuman();
//	 }
	public void setMouseAction() {
		this.addMouseListener( new MouseAdapter() {
//			
			@Override
			public void mouseClicked(MouseEvent e) {
				//System.out.print("here");
				//System.out.println(location.getX()+" "+location.getY());
				//CrossPointLabel.this.setText("X");
				int l = line;
				int v = vert;
				
				
				if(main.playing){
					if(main.allPlayers.b.Board[l][v].getPiece().equals("N"))
					{
						node temp = new node(l,v);
						temp.setPiece(main.allPlayers.type);
						main.allPlayers.player = temp;
						main.allPlayers.clicked = true;
						if(main.choiceO!= 1 && temp.getPiece().equals("O"))
						{
							CrossPointLabel.this.setText("O");
						}
						else if(main.choiceX!= 1 &&temp.getPiece().equals("X"))
						{
							CrossPointLabel.this.setText("X");
						}

						else if(main.startType.equals("O"))
						{
//							main.allPlayers.b.Board[l][v].setPiece("O");
							main.allPlayers.b.add("O", l , v,true);
						    CrossPointLabel.this.setText("O");
						    if(main.allPlayers.b.checkWin(main.allPlayers.b.Board[l][v]))
						    	System.out.println("game win O");
							main.startType = "X";
						}
						else if(main.startType.equals("X"))
						{
							
							main.allPlayers.b.add("X", l,v,true);
							CrossPointLabel.this.setText("X");
							if(main.allPlayers.b.checkWin(main.allPlayers.b.Board[l][v]))
								System.out.println("game win X");
							main.startType = "O";
						}
						marked = true;
						//main.allPlayers.continue2();
					}
					
				}
//				if()
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

	public void setLocation(Dimension windowSize, double radian, int layer,
			double slice) {

		int center_x = (int) Math.ceil(windowSize.width / 2.0);
		int center_y = (int) Math.ceil(windowSize.height / 2.0);
		int radius = (int) (windowSize.width * slice * layer);

		int px = (int) Math.ceil(center_x + (radius * Math.cos(radian)));
		int py = (int) Math.ceil(center_y + (radius * Math.sin(radian)));

		location = new Point(px, py);
		setBounds(location.x - 12, location.y - 12, 24, 24);
		repaint();

	}

	public Point getLocation() {
		return location;
	}

	public void addPiece(String in) {
		CrossPointLabel.this.setText(in);
		marked = true;
	}

	public void setMarked(boolean m) {
		marked = m;
	}

	public boolean isMarked() {
		return marked;
	}

	public double distance(int x, int y) {
		return location.distance(x, y);
	}

}