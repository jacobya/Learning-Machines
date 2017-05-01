package Other;

import java.util.Random;

public class Player {
	private String startType = "X";
	public int Xplayer;
	public boolean pruning = false;
	public int depth = 2;
	public int Oplayer;
	public node player;
	public void move()
	{
		board b = new board();
		NeuralNet nN = new NeuralNet(b);
		MiniMax mM = new MiniMax(b);
		//Knn kNN = new Knn();
		boolean done = false;
		int choice = Xplayer;
		String type = startType;
		while(!done)
		{
			switch(choice){
				case 1: 
					done = nN.StartTD(type);
					break;
				case 2:
					//node temp=kNN.
					//done = b.checkWin(temp);
					break;
				case 3:
					node temp = mM.MiniMaxStart(type, pruning, depth);
					b.add(temp.getPiece(), temp.getRing(), temp.getLine());
					done = b.checkWin(temp);
					break;
				case 4:
					Random rand = new Random();
					int i = rand.nextInt(b.getOptionMoveFull().size());
					b.add(b.getOptionMoveFull().get(i).getPiece(), b.getOptionMoveFull().get(i).getRing(), b.getOptionMoveFull().get(i).getLine());
					done = b.checkWin(b.getOptionMoveFull().get(i));
					break;
				default:
					b.add(player.getPiece(), player.getRing(), player.getLine());
					done = b.checkWin(player);
					break;
			}
			if (type.equals("X")) {
				type = "O";
				choice = Oplayer;
			} else {
				type = "X";
				choice = Xplayer;
			}

				
					
		}
	}
	
}
