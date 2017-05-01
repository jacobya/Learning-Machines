package Other;

import java.util.ArrayList;

public class blanket {
	public node current;
	public ArrayList<blanket> pointers = new ArrayList<blanket>();
	public int score;
	public board currentBoard;
	public ArrayList<node> choice = new ArrayList<node>();
	public blanket(node in_current,board in_currentBoard)
	{
		currentBoard = in_currentBoard;
		current = in_current;
		score = currentBoard.score(current.getPiece());
	}
}
