package Other;

import java.awt.EventQueue;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

import mine.GameBoard;

public class main {
	static File output = new File("weights.txt");

	public static void main(String args[]) {
//		Player allPlayers = new Player();
//		allPlayers.move();
		GameBoard board = new GameBoard();
		board.frame.setVisible(true);
//		EventQueue.invokeLater(new Runnable(){
//			public void run(){
//				try{
//					GameBoard board = new GameBoard();
//					board.frame.setVisible(true);
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//			}
//		});
//		Random rand = new Random();
//		Scanner out;
//		if (!output.exists()) {
//			// board.ranBefore = false;
//		}
//		FileOut weight = new FileOut("weightsOLD.txt");
//		FileOut boards = new FileOut("boards.txt");
//		// FileOut error = new FileOut("error.txt");
//		board b = new board();
//		NeuralNet nN = new NeuralNet(b);
//		// boolean win = false;
//		if (!output.exists()) {
//			nN.ranBefore = false;
//		}
//		boolean run = true;
//		for (int m = 0; m < 7; m++) {
//
//			for (int i = 0; i < 100000; i++) {
//				b.add("X", rand.nextInt(4), rand.nextInt(12));
//				run = false;
//				nN.error.writerln("----------------------------------");
//				for (int j = 0; j < 24; j++) {
//					if (run == true) {
//						if (nN.StartTD("X")) {
//							break;
//						}
//					} else {
//						run = true;
//					}
//					if (nN.StartTD("O")) {
//						break;
//					}
//				}
//
//				for (int n = 0; n < b.Board.length; n++) {
//					for (int j = 0; j < b.Board[0].length; j++) {
//						boards.writer(b.Board[n][j].getPiece());
//
//					}
//					boards.writerln("");
//				}
//				boards.writerln("");
//				b.reset();
//			}
//			System.out.println(m );
//			weight.writerln("-----------------------------------------------------------");
//			for (int i = 0; i < nN.inWeight.length; i++) {
//				for (int j = 0; j < nN.inWeight[0].length; j++) {
//					weight.writer(nN.inWeight[i][j]);
//
//				}
//			}
//			for (int i = 0; i < nN.hWeight.length; i++) {
//				weight.writer(nN.hWeight[i]);
//			}
//			weight.writerln("-----------------------------------------------------------");
//		}
	}
}
