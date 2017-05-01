package mine;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

public class Knn {
	//track 3 nearest boards
	private NearestBoards[] nb = new NearestBoards[3];
	private String[] testData = new String[200];
	private board move_board = new board();
	private String player;
	private InputStream is;
	private BufferedReader br;
	private String line;
	/*
	 * constructor
	 * Builds the test set from test data
	 * 
	 */
	public Knn(){
		try {
			is = new FileInputStream("knntraining.old.txt");
			br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			int i = 0;
			while((line = br.readLine()) != null && i < 200){
				testData[i] = line;
				i++;
			}
			br.close();
			br = null;
			is = null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0;i < 3; i++)
			nb[i] = new NearestBoards("p", 0);
	}

	/*
	 * check if the move made has a positive return
	 * 
	 */
	private boolean getPos(){
		int p = 0;
		boolean pos = false;
		for(int i = 0; i < 3; i++){
			if(nb[i].getWin(player))
				p++;
		}
		if(p>=2)
			pos = true;
		return pos;
	}

	/*
	 * in: current board state
	 * out: board state after move is made
	 */
	public node move(String p, board current){
		player = p;
		board old = current;
		node toReturn = null;
		String currStr = convertStr(current);
		ArrayList<node> move_list = current.getOptionMove(); 
		if(move_list.isEmpty()){
			ArrayList<node> move_list2 = current.getOptionMoveFull();
			Random rand = new Random();
			int ranNum = rand.nextInt(move_list2.size());
			toReturn = move_list2.get(ranNum);
			toReturn.setPiece(p);
			//System.out.println(toReturn.getPiece());
		}else{
			for(int k = 0; k < move_list.size(); k++){
				currStr = add(currStr, move_list.get(k));
				checkDistance(currStr);
				if(getPos())
					toReturn = getNode(currStr, old);
				else{
					move_list = current.getOptionMove();
					Random rand = new Random();
					int ranNum = rand.nextInt(move_list.size());
					toReturn = move_list.get(ranNum);
				//	System.out.println(toReturn.getPiece());

				}
				currStr = remove(currStr, move_list.get(k));
			}
			//System.out.println(toReturn.getPiece());
		}
		toReturn.setPiece(p);
		return toReturn;
	}
	/*
	 * Find the distance between the current board and another
	 * sample board
	 */
	private int calcDistance(String current_game, String train_game){
		int distance = 0;
		for(int i = 0; i < current_game.length(); i++){
			if(current_game.charAt(i) != train_game.charAt(i))
				distance++;
		}
		distance += (train_game.length() - current_game.length());
		return distance;
	}
	
	/*
	 * Adds legal move to board
	 * in: String current, node addition
	 * Out: String move
	 */
	public String add(String current, node addition){
		StringBuilder withAdd = new StringBuilder(current);
		withAdd.setCharAt((addition.getLine()+addition.getRing()), player.charAt(0));
		return withAdd.toString();
	}
	
	/*
	 * remove move from board
	 * in: String current, node addition
	 * Out: String move
	 */
	public String remove(String current, node addition){
		StringBuilder withAdd = new StringBuilder(current);
		withAdd.setCharAt((addition.getLine()+addition.getRing()), 'N');
		return withAdd.toString();
	}
	
	
	/*
	 * Each possible move
	 * in: game board String with move
	 * out: fills the 3 nearest neighbor array :nb
	 */
	private void checkDistance(String test){
		int distance;
		for(int i = 0; i < 200; i++){
			distance = calcDistance(test, testData[i]);
			for(int j = 0; j < 3; j++){
				if(nb[j].getDistance() < distance){
					nb[j].setDistance(distance);
					nb[j].setBoard(testData[i]);
				}
			}
		}
	}
	/*
	 * convert current board to string for easy comparison
	 * in: board current
	 * out: String currentTS
	 */
	private String convertStr(board current){
		String currentTS = "";
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 12; j++){
				currentTS = currentTS + current.Board[i][j].getPiece();
			}
		}
		return currentTS;
	}
	
	/*
	 * convert the String representing the board state
	 * with the optimal move to a board object
	 */
	private board convertBoard(String moved){
		board converted = null;
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 12; j++){
				converted.Board[i][j].setPiece(moved.charAt(i+j));
				converted.Board[i][j].setLine(i);
				converted.Board[i][j].setRing(j);
			}
		}
		return converted;
	}
	
	/*
	 * get node from String value
	 */
	private node getNode(String currBoard, board old){
		board temp = convertBoard(currBoard);
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 12; j++){
				if(temp.Board[i][j].getPiece() != old.Board[i][j].getPiece())
					return temp.Board[i][j];
			}
		}
		return null;
	}
}
