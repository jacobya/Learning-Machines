package board2;


import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math;

public class board {

	public node[][] Board = new node[4][12];

	public int amount = 48;
	public ArrayList<node> optionMove = new ArrayList<node>();
	public ArrayList<node> optionMoveFull = new ArrayList<node>();
	private int[] score = { 0, 1, 10, 100, 1000 };
	public int scoreX = 0;
	public int scoreY = 0;
	/*
	 * each position is either X or O so 48*2 = 96 two for the amount of
	 * positions open one for the amount of moves to make for that player ie 29
	 * spot are open and black turn are 15 and 14 turn for white two for whoes
	 * turn it is
	 */


	public board() {

		// creates the board
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 12; j++) {
				Board[i][j] = new node(i, j);
				getOptionMoveFull().add(new node(i, j));
			}
		}

	}

	/*
	 * input: string piece(x, o), int ring, int line
	 */
	public void add(String piece, int ring, int line, boolean add) {
		if (Board[ring][line].getPiece().equals("N")) {
			if(add){
//				for(int i = 0; i<48; i++)
//				{
//					if(main.board.game_panel.points[(ring*12)+line])
//				}
				
				//main.board.game_panel.points[ring][line].addPiece(piece);
			}
			else if(!add){
				Board[ring][line].setPiece(piece);
				for(int i = 0; i<optionMove.size();i++)
				{
					if(optionMove.get(i)==null||!optionMove.get(i).getPiece().equals("N"))
					{
						optionMove.remove(i);
						break;
					}
				}
				ArrayList<node> optionMoveTemp = new ArrayList<node>();
				if (ring + 1 < 4) {
					optionMoveTemp.add(Board[ring + 1][(line + 1) % 12]);
					optionMoveTemp.add(Board[ring + 1][(line) % 12]);
					// System.out.println(((line - 1) % 12)+"");
					optionMoveTemp.add(Board[ring + 1][((line + 11) % 12)]);
				}
				optionMoveTemp.add(Board[ring][(line + 1) % 12]);
				// System.out.println(line );
				optionMoveTemp.add(Board[ring][(line + 11) % 12]);
				if (ring - 1 > 0) {
					optionMoveTemp.add(Board[ring - 1][(line + 1) % 12]);
					optionMoveTemp.add(Board[ring - 1][(line) % 12]);
					optionMoveTemp.add(Board[ring - 1][(line + 11) % 12]);
				}
				for (int i = 0; i < optionMoveTemp.size(); i++) {
					node temp = optionMoveTemp.get(i);
					if (temp.getPiece() == "N") {
						optionMove.add(temp);
					}
				}
				return;
			}
			Board[ring][line].setPiece(piece);
			for(int i = 0; i<getOptionMoveFull().size();i++)
			{
				if(optionMoveFull.get(i)==null||!optionMoveFull.get(i).getPiece().equals("N"))
				{
					getOptionMoveFull().remove(i);
					break;
				}
			}
			for(int i = 0; i<optionMove.size();i++)
			{
				if(optionMove.get(i)==null||!optionMove.get(i).getPiece().equals("N"))
				{
					optionMove.remove(i);
					break;
				}
			}
			ArrayList<node> optionMoveTemp = new ArrayList<node>();
			if (ring + 1 < 4) {
				optionMoveTemp.add(Board[ring + 1][(line + 1) % 12]);
				optionMoveTemp.add(Board[ring + 1][(line) % 12]);
				// System.out.println(((line - 1) % 12)+"");
				optionMoveTemp.add(Board[ring + 1][((line + 11) % 12)]);
			}
			optionMoveTemp.add(Board[ring][(line + 1) % 12]);
			// System.out.println(line );
			optionMoveTemp.add(Board[ring][(line + 11) % 12]);
			if (ring - 1 > 0) {
				optionMoveTemp.add(Board[ring - 1][(line + 1) % 12]);
				optionMoveTemp.add(Board[ring - 1][(line) % 12]);
				optionMoveTemp.add(Board[ring - 1][(line + 11) % 12]);
			}
			for (int i = 0; i < optionMoveTemp.size(); i++) {
				node temp = optionMoveTemp.get(i);
				if (temp.getPiece() == "N") {
					optionMove.add(temp);
				}
			}
			// int score = score(piece);
			// if (piece.equals("X")) {
			// scoreX = score;
			// } else if (piece.equals("O")) {
			// scoreY = score;
			// }
		}
		else if(!add){
			node temp = new node(ring, line);
			temp.setPiece(piece);
			getOptionMoveFull().add(temp);
			Board[ring][line].setPiece(piece);
		}
	}

	public void reset() {
		System.out.println("here2");
		getOptionMoveFull().clear();
		optionMove.clear();
		amount = 48;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 12; j++) {
				Board[i][j].setPiece("N");
				getOptionMoveFull().add(new node(i, j));
			}
		}
	}

	public int score(String type) {
		int total = 0;
		for (int i = 0; i < optionMove.size(); i++) {
			node temp = optionMove.get(i);
			total += d1(temp.getRing(), temp.getLine(), type);
			total += h(temp.getRing(), temp.getLine(), type);
			total += d2(temp.getRing(), temp.getLine(), type);
			total += v1(temp.getRing(), temp.getLine(), type);
			total += v2(temp.getRing(), temp.getLine(), type);
			total += v3(temp.getRing(), temp.getLine(), type);
			total += v4(temp.getRing(), temp.getLine(), type);
		}

		return total;
	}

	/*
	 * The is all the different combinations of 4 around one node
	 */
	private int d1(int x, int y, String type) {
		String d1 = "C";
		node move = new node(x, y);
		for (int i = 0; i < 4; i++) {
			if (move.ringNum() - i >= 0)
				d1 = Board[move.ringNum() - i][(move.getLine() - i + 12) % 12]
						.getPiece() + d1;
			if (move.getRing() + i < 4){
				//System.out.println(move.ringNum() + i+"  "+move.getLine() + i);
				d1 = d1
						+ Board[move.ringNum() + i][(move.getLine() + i) % 12]
								.getPiece();
			}
		}
		int score = cal(d1, type);
		return score;
	}

	private int d2(int x, int y, String type) {
		node move = new node(x, y);
		String d1 = "C";
		for (int i = 1; i < 4; i++) {
			if (move.ringNum() - i >= 0)
				d1 = Board[move.ringNum() - i][(move.getLine() + i) % 12]
						.getPiece() + d1;
			if (move.ringNum() + i < 4)
				d1 = d1
						+ Board[move.ringNum() + i][(move.getLine() - i + 12) % 12]
								.getPiece();
		}
		int score = cal(d1, type);
		return score;
	}

	private int h(int x, int y, String type) {
		String h = "C" + Board[(x - 1 + 4) % 4][(y) % 12].getPiece()
				+ Board[(x - 2 + 4) % 4][(y) % 12].getPiece()
				+ Board[(x - 3 + 4) % 4][(y) % 12].getPiece();
		int score = cal(h, type);
		return score;
	}

	private int v1(int x, int y, String type) {
		String v1 = "C" + Board[x][(y - 1 + 12) % 12].getPiece()
				+ Board[x][(y - 2 + 12) % 12].getPiece()
				+ Board[x][(y - 3 + 12) % 12].getPiece();
		int score = cal(v1, type);
		return score;
	}

	private int v2(int x, int y, String type) {
		String v2 = "C" + Board[x][(y - 1 + 12) % 12].getPiece()
				+ Board[(x)][(y - 2 + 12) % 12].getPiece()
				+ Board[(x)][(y + 1) % 12].getPiece();
		int score = cal(v2, type);
		return score;
	}

	private int v3(int x, int y, String type) {
		String v3 = "C" + Board[(x) % 4][(y - 1 + 12) % 12].getPiece()
				+ Board[(x) % 4][(y + 2) % 12].getPiece()
				+ Board[(x) % 4][(y + 1) % 12].getPiece();
		int score = cal(v3, type);
		return score;
	}

	private int v4(int x, int y, String type) {
		String v4 = "C" + Board[(x) % 4][(y + 3) % 12].getPiece()
				+ Board[(x) % 4][(y + 2) % 12].getPiece()
				+ Board[(x) % 4][(y + 1) % 12].getPiece();
		int score = cal(v4, type);
		return score;
	}

	/*
	 * This calculates the score for the different types of sets
	 */
	private int cal(String b, String type) {
		String otherType;
		if (type.equals("X")) {
			otherType = "O";
		} else {
			otherType = "X";
		}
		int total = 0;
		int totalOther = 0;
		for (int i = 0; i < 4; i++) {
			if ((b.charAt(i) + "").equals(type) || b.equals("C")) {
				total++;
			}
			if ((b.charAt(i) + "").equals(otherType)) {
				totalOther++;
			}
		}

		return score[total] - score[totalOther];
	}

	/*
	 * After each turn is completed, check to see if win conditions are met
	 * 
	 * @jacoby input node returns boolean win
	 */
	public boolean checkWin(node move) {
		boolean win = false;
		String x = move.getPiece();
		String player = move.getPiece();
		String y = player + player + player + player;

		// check vertical
//		System.out.println("check vert");
		for (int i = 1; i < 4; i++) {
			x = x + Board[(move.ringNum() + i) % 4][move.getLine()].getPiece();
		}
		win = resolve(x, y);
		if (win)
			return win;
		else
			x = move.getPiece();
		// check horizontal
//		System.out.println("check horizontal");
		for (int i = 1; i < 4; i++) {
			x = x + Board[move.ringNum()][(move.getLine() + i) % 12].getPiece();
			System.out.println(x);
		}
		for (int i = 1; i < 4; i++) {
			x = Board[move.ringNum()][(move.getLine() - i + 12) % 12]
					.getPiece() + x;
			System.out.println(x);
		}

		win = resolve(x, y);
		if (win)
			return win;
		else
			x = move.getPiece();

		// check diag
//		System.out.println("check diag");
		for (int i = 1; i < 4; i++) {
			if (move.ringNum() - i >= 0)
				x = Board[move.ringNum() - i][(move.getLine() - i + 12) % 12]
						.getPiece() + x;
			if (move.ringNum() + i < 4)
				x = x
						+ Board[move.ringNum() + i][(move.getLine() + i) % 12]
								.getPiece();
		}
		win = resolve(x, y);
		if (win)
			return win;
		else
			x = move.getPiece();

		// check diag
//		System.out.println("check diag");
		for (int i = 1; i < 4; i++) {
			if (move.ringNum() - i >= 0)
				x = Board[move.ringNum() - i][(move.getLine() + i) % 12]
						.getPiece() + x;
			if (move.ringNum() + i < 4)
				x = x
						+ Board[move.ringNum() + i][(move.getLine() - i + 12) % 12]
								.getPiece();
		}
		win = resolve(x, y);
		if (win)
			return win;

		return win;
	}

	/*
	 * resolve used in winchecker returns false if no suitable substitution is
	 * found
	 * 
	 * @jacoby
	 */
	private boolean resolve(String x, String y) {
		boolean success = false;
		String tempx = x;
		String tempy = y;
		if (x.contains(y))
			success = true;
//		System.out.println(x + "resolves against" + y);
//		for(int i = 0; i < y.length(); i++){
//			if(x.charAt(i) == x.charAt(i)){
//				tempx = x.substring(0, i+1);
//				tempy = y.substring(1, y.length()-1);
//				System.out.println(tempx + " " + tempy);
//			}
//		}
		print();
		System.out.println(x + " resolves against " + y);
		return success;
	}

	public ArrayList<node> getOptionMove() {
		return optionMove;
	}


	public ArrayList<node> getOptionMoveFull() {
		return optionMoveFull;
	}
	public void setBoard(node[][] board) {
		Board = board;
	}

	public void print(){
		for(int i=0; i< 4; i++)
		{
			for(int j=0;j<12;j++)
			{
				System.out.print(Board[i][j].getPiece());
			}
			System.out.println();
		}
		System.out.println();
	}
}
