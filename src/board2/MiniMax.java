package board2;


import java.util.ArrayList;

public class MiniMax {
	private board b;
	// private ArrayList<blanket> tree = new ArrayList<blanket>();
	// private ArrayList<Integer> lowestLevel= new ArrayList<Integer>();

	private blanket root;

	public MiniMax(board in) {
		b = in;
	}

	public node MiniMaxStart(String type, Boolean pruning, int depth) {

		String otherType;
		if (type.equals("X")) {
			otherType = "O";
		} else {
			otherType = "X";
		}

		node temp = new node(700, 700);
		temp.setPiece(type);
		board tempBoard = new board();
		tempBoard.amount = b.amount;
		tempBoard.Board = b.Board.clone();
		tempBoard.optionMoveFull = (ArrayList<node>) b.optionMoveFull.clone();
		tempBoard.optionMove = (ArrayList<node>) b.optionMove.clone();	
		root = new blanket(temp, tempBoard);
		root.depth = 2;
		root.turns=(ArrayList<node>) b.getOptionMoveFull().clone();
		blanket best = expand(root, type, pruning, depth);
		//System.out.println(root.pointers.get(root.place).getRing()+" "+best.choice.get(0).getLine());
		return best.current;
	}

	private blanket expand(blanket in, String move, boolean minimax, int depthTotal) {
		int num = 1000000000;
		if (move.equals(root.current.getPiece())) {
			num = -10000000;
		}

		ArrayList<blanket> score = new ArrayList<blanket>();
		int place = -1;
		if (in.depth == 0) {
			in.score = in.currentBoard.score(move);
			//print();
		//	System.out.println(in.score);
			return in;
		} else {
			String otherType;
			if (move.equals("X")) {
				otherType = "O";
			} else {
				otherType = "X";
			}
			//ArrayList<node> turns = in.currentBoard.getOptionMoveFull();
			for (int i = 0; i < in.turns.size(); i++) {
				if( i==root.turns.size()&&move.equals(root.current.getPiece() ))
				{
			in.score = in.currentBoard.score(in.turns.get(i)
					.getPiece());
			//place = 0;
			return in;
				}
				if (minimax == false) {

					board tempBoard = new board();
					tempBoard.amount = in.currentBoard.amount;
					tempBoard.Board = in.currentBoard.Board.clone();
					tempBoard.optionMoveFull = (ArrayList<node>) in.currentBoard.optionMoveFull.clone();
					tempBoard.optionMove = (ArrayList<node>) in.currentBoard.optionMove.clone();		
							//in.currentBoard;

					in.turns.get(i).setPiece(move);
					blanket temp = new blanket(in.turns.get(i), tempBoard);
					temp.depth=in.depth-1;
					temp.turns = (ArrayList<node>) in.turns.clone();
					node thing = temp.turns.remove(i);
					// node nextChoice = new node(turns.get(i).getRing(),
					// turns.get(i).getLine());
					temp.choice.addAll(in.choice);
					//print();
					temp.choice.add(in.turns.get(i));
					temp.currentBoard.add(move, thing.getRing(), thing.getLine(), false);
				//	temp.score =temp.currentBoard.score(move);
					//print();
					in.pointers.add(temp);
					if (in.turns.get(i).getPiece().equals("N")||!in.currentBoard.checkWin(in.turns.get(i))) {
						blanket theThing = expand(in.pointers.get(i), otherType, minimax, depthTotal);
						//theThing.score = theThing.currentBoard.score(move);
						//System.out.println(theThing.score);
						//temp.score= theThing.score;
						score.add(theThing);
						
						//print();
						//place = 0;
					} else if(in.turns.get(i).getPiece().equals("N")|| i==root.turns.size()&&move.equals(root.current.getPiece())){
						in.score = in.currentBoard.score(in.turns.get(i)
								.getPiece());
						//place = 0;
						return in;
					}
				//	place =0;
					temp.currentBoard.add("N", temp.current.getRing(), temp.current.getLine(), false);

					//in.turns.add(i, thing);
				//	print();
					if (in.depth%2==0) {
						for (int j = 0; j < score.size(); j++) {
							if (num < score.get(j).score) {
								num = score.get(j).score;
								place = j;
							}
						}
					} else {
						for (int j = 0; j < score.size(); j++) {
							if (num > score.get(j).score) {
								num = score.get(j).score;
								place = j;
							}
						}
					}
				} else if (minimax == true) {
					board tempBoard = in.currentBoard;

					//board tempBoard = in.currentBoard;

					in.turns.get(i).setPiece(move);
					blanket temp = new blanket(in.turns.get(i), tempBoard);
					temp.depth=in.depth-1;
					temp.turns = (ArrayList<node>) in.turns.clone();
					node thing = temp.turns.remove(i);
					// node nextChoice = new node(turns.get(i).getRing(),
					// turns.get(i).getLine());
					temp.choice.addAll(in.choice);
					//print();
					temp.choice.add(in.turns.get(i));
					temp.currentBoard.add(move, thing.getRing(), thing.getLine(), false);
					//print();
					in.pointers.add(temp);
					if (in.depth%2==0) {
						if (in.turns.get(i).getPiece().equals("N")||!in.currentBoard.checkWin(in.turns.get(i))
								&& num < find(root, in, 0).score) {

							score.add(expand(in.pointers.get(i), otherType,
									minimax, depthTotal));
						} else if(in.turns.get(i).getPiece().equals("N")|| i==root.turns.size()&&move.equals(root.current.getPiece())){
							in.score = in.currentBoard.score(in.turns.get(i)
									.getPiece());
							return in;
						}
						if (in.depth%2==0) {
							for (int j = 0; j < score.size(); j++) {
								if (num < score.get(j).score) {
									num = score.get(j).score;
									place = j;
								}
							}
						} else {
							for (int j = 0; j < score.size(); j++) {
								if (num > score.get(j).score) {
									num = score.get(j).score;
									place = j;
								}
							}
						}
					} else {
						if (in.turns.get(i).getPiece().equals("N")||!in.currentBoard.checkWin(in.turns.get(i))
								&& num >= find(root, in, 0).score) {

							score.add(expand(in.pointers.get(i), otherType,
									minimax, depthTotal));
						} else if(in.turns.get(i).getPiece().equals("N")|| i==root.turns.size()&&move.equals(root.current.getPiece())){
							in.score = in.currentBoard.score(in.turns.get(i)
									.getPiece());
							return in;
						}
						if (in.depth%2==0) {
							for (int j = 0; j < score.size(); j++) {
								if (num < score.get(j).score) {
									num = score.get(j).score;
									place = j;
								}
							}
						} else {
							for (int j = 0; j < score.size(); j++) {
								if (num > score.get(j).score) {
									num = score.get(j).score;
									place = j;
								}
							}
						}
					}
					temp.currentBoard.add("N", temp.current.getRing(), temp.current.getLine(), false);
					if( i==main.allPlayers.b.amount&&move.equals(root.current.getPiece() ))
							{
						in.score = in.currentBoard.score(in.turns.get(i)
								.getPiece());
						//place = 0;
						return in;
							}
				}
			}
//			if (!in.current.getPiece().equals(root.current.getPiece())) {
//				for (int j = 0; j < score.size(); j++) {
//					if (num < score.get(j).score) {
//						num = score.get(j).score;
//						place = j;
//					}
//				}
//			} else {
//				for (int j = 0; j < score.size(); j++) {
//					if (num > score.get(j).score) {
//						num = score.get(j).score;
//						place = j;
//					}
//				}
//			}
		}
		if(place==-1)
		{
			return in;
		}
		//print();
		in.place = place;
		return score.get(place);
	}

	private blanket find(blanket parent, blanket find, int depth) {
		if (depth < find.pointers.size()) {
			for (int i = 0; i < parent.choice.size(); i++) {
				if (parent.pointers.get(i).current.getLine() == find.choice
						.get(depth).getLine()
						&& parent.pointers.get(i).current.getRing() == find.choice
								.get(depth).getRing()) {
					find(parent.pointers.get(i), find, depth++);
				}
			}
		} else {
			return parent;
		}
		return parent;
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
}
