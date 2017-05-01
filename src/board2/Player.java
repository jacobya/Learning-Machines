package board2;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import board2.RandomPlay;

public class Player implements ActionListener  {
	//private String startType = "X";
	public int Xplayer = 1;
	public boolean pruning = false;
	public int depth = 2;
	public int Oplayer = 1;
	public node player;
	public board b = new board();
	String type;
	boolean done = false;
	public boolean clicked = false;
	
	public Player(int x, int o){
		Xplayer = x;
		Oplayer= o;
		//b = new board();
	}
	public void move() 
	{

		NeuralNet nN = new NeuralNet(b);
		MiniMax mM = new MiniMax(b);
		Knn kNN = new Knn();
		Human hUM = new Human();
		RandomPlayer rAN = new RandomPlayer();
		
		int choice;
		type = main.startType;
		if (type.equals("X")) {
			//main.startType = "O";
			choice = main.choiceO;
		} else {
			//main.startType = "X";
			choice = main.choiceX;
		}
//		while(!done){
//			done=full();
//			node temp = mM.MiniMaxStart(type, pruning, depth);
//			temp.setPiece(type);
//			//print();
//			b.add(temp.getPiece(), temp.getRing(), temp.getLine(),true);
//			//print();
//			done = b.checkWin(temp);
//			//System.out.println(done);
//			if (type.equals("X")) {
//				type = "O";
//			//	choice = Oplayer;
//			} else {
//				type = "X";
//				//choice = Xplayer;
//			}
//		}
		if(done){
			winTest(type);
			return;
		}
		if(full())
		{
			return;
		}
			switch(choice){
				case 1:
					//pause();
					//run();
					//QCoreApplication.processEvents();
					//main.wait2();
				//	b.add(player.getPiece(), player.getRing(), player.getLine(),true);
					done = b.checkWin(player);
					break;
				case 2: 
					done = nN.StartTD(type);
					print();
					break;
				case 3:
					node t = kNN.move(type, b);
					b.add(type, t.getRing(), t.getLine(), true);
					t.setPiece(type);
					done = b.checkWin(t);
					break;
				case 4:
					node temp = mM.MiniMaxStart(type, pruning, depth);
					temp.setPiece(type);
					b.add(temp.getPiece(), temp.getRing(), temp.getLine(),true);
					done = b.checkWin(temp);
					break;
				case 5:
					node temp2 = mM.MiniMaxStart(type, true, depth);
					temp2.setPiece(type);
					b.add(temp2.getPiece(), temp2.getRing(), temp2.getLine(),true);
					done = b.checkWin(temp2);
					break;

				default:
					node rand = rAN.move(type, b);
					b.add(type, rand.getRing(), rand.getLine(), true);
					rand.setPiece(type);
					done = b.checkWin(rand);
					//Random rand = new Random();
//					int i = rand.nextInt(b.getOptionMoveFull().size());
//					b.add(b.getOptionMoveFull().get(i).getPiece(), b.getOptionMoveFull().get(i).getRing(), b.getOptionMoveFull().get(i).getLine(),true);
//					done = b.checkWin(b.getOptionMoveFull().get(i));
					break;
					

					
			}
			if(done){
				winTest(type);
				return;
			}
			if (type.equals("X")) {
				main.startType = "O";
			//	choice = Oplayer;
			} else {
				main.startType = "X";
				//choice = Xplayer;
			}

				
					
		
	}
	public void print(){
		for(int i=0; i< 4; i++)
		{
			for(int j=0;j<12;j++)
			{
				System.out.print(b.Board[i][j].getPiece());
			}
			System.out.println();
		}
		System.out.println();
	}
	public boolean full()
	{
		//System.out.println(b.optionMoveFull.size());
		if(b.optionMoveFull.size() == 0)
		{
			CustomDialog dia= new CustomDialog(main.board.frame,"Full",false);
			System.out.println("hello");
			return true;

		}
		return false;
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void winTest(String type)
	{
		CustomDialog dia= new CustomDialog(main.board.frame,"Win "+type,false);
	}

}
