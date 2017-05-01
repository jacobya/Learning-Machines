package mine;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class RandomRun {

	public static void main(String[] args) throws IOException {
		String turn = "X";
		board b = new board();
		RandomPlayer c = new RandomPlayer();
		boolean win = false;
		node n = null;
		Knn d = new Knn();
		//GameBoard e = new GameBoard();
		//e.frame.setVisible(true);
		int ring=0;
		int line=0;
			while(!win){
				if(turn.equalsIgnoreCase("X")){
					n = c.move(turn, b);
					ring = n.getRing();
					line = n.getLine();
					b.add(turn, ring, line, true);
					n.setPiece(turn);
				}else{
					n = d.move(turn, b);
					b.add(turn, n.getRing(), n.getLine(), true);
					n.setPiece(turn);
				}
				for(int i = 0; i < 4; i++){
					System.out.println();
					for(int j = 0; j < 12; j++){
						System.out.print(b.Board[i][j].getPiece());
					}
				}
				System.out.println();
				win = b.checkWin(n);
				if(turn.equals("X"))
					turn = "O";
				else if(turn.equals("O"))
					turn = "X";
		System.out.println(turn);
		}
	}
}
	



