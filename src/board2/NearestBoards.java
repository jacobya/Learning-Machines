package board2;

public class NearestBoards {
	private String board;
	private int distance;
	boolean x = false;
	boolean o = false;
	public NearestBoards(String board, int distance){
		this.board = board;
		this.distance = distance;
		char result = board.charAt(board.length()-1);
		if(result == 'p')
			x = true;
		else if(result == 's')
			o = true;
	}
	
	public int getDistance(){
		return distance;
	}
	
	public String getBoard(){
		return board;
	}
	
	public void setDistance(int temp){
		distance = temp;
	}
	
	public void setBoard(String temp){
		board = temp;
	}
	
	public boolean getWin(String player){
		if(player.equals('X')){
			return x;
		}
		else{
			return o;
		}
	}
}
