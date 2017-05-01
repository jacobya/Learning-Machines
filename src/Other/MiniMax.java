package Other;

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
		root = new blanket(temp, b);

		blanket best = expand(root, type, pruning, depth);
		
		return best.choice.get(0);
	}

	private blanket expand(blanket in, String move, boolean minimax, int depth) {
		int num = 1000000000;
		if (move.equals(root.current.getPiece())) {
			num = -10000000;
		}

		ArrayList<blanket> score = new ArrayList<blanket>();
		int place = -1;
		if (depth == 0) {
			return in;
		} else {
			String otherType;
			if (move.equals("X")) {
				otherType = "O";
			} else {
				otherType = "X";
			}
			ArrayList<node> turns = in.currentBoard.getOptionMoveFull();
			for (int i = 0; i < turns.size(); i++) {
				if (minimax == false) {

					board tempBoard = in.currentBoard;

					turns.get(i).setPiece(move);
					blanket temp = new blanket(turns.get(i), tempBoard);
					// node nextChoice = new node(turns.get(i).getRing(),
					// turns.get(i).getLine());
					temp.choice.addAll(in.choice);
					temp.choice.add(turns.get(i));
					temp.currentBoard.add(move, turns.get(i).getRing(), turns
							.get(i).getLine());
					in.pointers.add(temp);
					if (!in.currentBoard.checkWin(turns.get(i))) {

						score.add(expand(in.pointers.get(i), otherType,
								minimax, depth--));
					} else {
						in.score = in.currentBoard.score(turns.get(i)
								.getPiece());
						return in;
					}
					if (move.equals(root.current.getPiece())) {
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

					turns.get(i).setPiece(move);
					blanket temp = new blanket(turns.get(i), tempBoard);
					// node nextChoice = new node(turns.get(i).getRing(),
					// turns.get(i).getLine());
					temp.score = num;
					temp.choice.addAll(in.choice);
					temp.choice.add(turns.get(i));
					temp.currentBoard.add(move, turns.get(i).getRing(), turns
							.get(i).getLine());
					in.pointers.add(temp);
					if (move.equals(root.current.getPiece())) {
						if (!in.currentBoard.checkWin(turns.get(i))
								&& in.score < find(root, in, 0).score) {

							score.add(expand(in.pointers.get(i), otherType,
									minimax, depth--));
						} else {
							in.score = in.currentBoard.score(turns.get(i)
									.getPiece());
							return in;
						}
						if (move.equals(root.current.getPiece())) {
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
						if (!in.currentBoard.checkWin(turns.get(i))
								&& in.score > find(root, in, 0).score) {

							score.add(expand(in.pointers.get(i), otherType,
									minimax, depth--));
						} else {
							in.score = in.currentBoard.score(turns.get(i)
									.getPiece());
							return in;
						}
						if (move.equals(root.current.getPiece())) {
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
				}
			}
		}

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
}
