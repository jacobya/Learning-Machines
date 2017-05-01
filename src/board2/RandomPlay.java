package board2;

import java.util.ArrayList;
import java.util.Random;

public class RandomPlay {
	private String player;
	ArrayList<node> move_list;
	
	public RandomPlay(){
		
	}
	
	public node move(String p, board b){
		player = p;
//		move_list = b.getOptionMove();
//		if(move_list.size() == 0)				
			move_list = b.getOptionMoveFull();
		int ranNum = randInt(0, move_list.size());
		node move = move_list.get(ranNum);
		return move;
	}
	
	public int randInt(int min, int max){
		Random rand = new Random();
		int ranNum = rand.nextInt((max-min)+1)+min;
		return ranNum;
	}
	
}
