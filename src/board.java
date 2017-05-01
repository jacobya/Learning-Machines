import java.util.*;


public class board {
	private static node[][] Board  = new node[4][12];
	private static ArrayList<node> optionMove = new ArrayList<node>();
	public board()
	{
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j< 12;j++)
			{
				Board[i][j] = new node(i,j);
			}
		}
		
	}

	public static void add(String peice, int ring, int line)
	{
		if(Board[ring][line].getPiece()=="N")
		{
			Board[ring][line].setPiece(peice);
			ArrayList<node> optionMoveTemp = new ArrayList<node>();
			if(ring + 1 < 4)
			{
				optionMoveTemp.contains(Board[ring+1][(line+1)%12]);
				optionMoveTemp.contains(Board[ring+1][(line)%12]);
				optionMoveTemp.contains(Board[ring+1][(line-1)%12]);
			}
			optionMoveTemp.contains(Board[ring][(line+1)%12]);
			optionMoveTemp.contains(Board[ring][(line-1)%12]);
			if(ring-1>0)
			{
				optionMoveTemp.contains(Board[ring-1][(line+1)%12]);
				optionMoveTemp.contains(Board[ring-1][(line)%12]);
				optionMoveTemp.contains(Board[ring-1][(line-1)%12]);
			}
			for(int i = 0; i<optionMoveTemp.size();i++)
			{
				node temp = optionMoveTemp.get(i);
				if(temp.getPiece() == "N")
				{
					optionMove.add(temp);
				}
			}
		}
	}
	public static void reset()
	{
		for(int i = 0;i<4;i++)
		{
			for(int j = 0; j<12;j++)
			{
				Board[i][j].setPiece("N");
			}
		}
	}
	
}
