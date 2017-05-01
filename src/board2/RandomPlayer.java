package board2;

import java.util.ArrayList;
import java.util.Random;

public class RandomPlayer {
	private String player;
	ArrayList<node> move_list;
	
	public RandomPlayer(){
		
	}
	
	public node move(String p, board current){
		player = p;
		move_list = current.getOptionMove();
		if(move_list.size() == 0)				
			move_list = current.getOptionMoveFull();
		int ranNum = randInt(0, move_list.size());
		node move = move_list.get(ranNum);
		return move;
	}
	
	public int randInt(int min, int max){
		Random rand = new Random();
		int ranNum = rand.nextInt((max-min));
		return ranNum;
	}
	
}
