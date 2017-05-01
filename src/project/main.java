package project;
import java.io.*;
import java.util.Scanner;

public class main {
	static File output = new File("weights.txt");

	public static void main(String args[]) {
		Scanner out;
		if (!output.exists()) {
			// board.ranBefore = false;
		}
		FileOut weight = new FileOut("weightsOLD.txt");
		FileOut boards = new FileOut("boards.txt");
		// FileOut error = new FileOut("error.txt");
		board b = new board();
		// boolean win = false;
		if (!output.exists()) {
			b.ranBefore = false;
		}
		for (int m = 0; m < 3; m++) {
			for (int i = 0; i < 100000; i++) {
				for (int j = 0; j < 24; j++) {
					if (b.StartTD("X")) {
						break;
					}
					if (b.StartTD("O")) {
						break;
					}
				}
				System.out.println(i);
				for (int n = 0; n < b.Board.length; n++) {
					for (int j = 0; j < b.Board[0].length; j++) {
						boards.writer(b.Board[n][j].getPiece());

					}
					boards.writerln("");
				}
				boards.writerln("");
				b.reset();
			}
			for (int i = 0; i < b.inWeight.length; i++) {
				for (int j = 0; j < b.inWeight[0].length; j++) {
					weight.writer(b.inWeight[i][j]);

				}
			}
			for (int i = 0; i < b.hWeight.length; i++) {
				weight.writer(b.hWeight[i]);
			}
		}
	}
}
