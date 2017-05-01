package board2;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class NeuralNet {
	private board b;
	private int amountTurns = 48;
	private node[][] Board;
	private int hiddenA = 80;
	private int inputA = 100;
	public double[][] inWeight = new double[hiddenA][inputA];
	private double[][] activationPrev = new double[hiddenA][inputA];
	private ArrayList<double[][]> activation = new ArrayList<double[][]>();
	public double[] hWeight = new double[hiddenA];
	private double[] hiddenLayerTotal = new double[hiddenA];
	private double[] hiddenActivationPrev = new double[hiddenA];
	private ArrayList<double[]> hiddenActivation = new ArrayList<double[]>();
	private double learningRate = .1;
	public boolean ranBefore = true;
	private Scanner in;
	private double v = 0;
	FileOut error = new FileOut("error.txt");
	public NeuralNet(board in_b)
	{
		Board = in_b.Board;
		this.b = in_b;
		// Bring in or create the weight for the neural network
		Random rand = new Random();
		boolean done = true;
		File output = new File("weights.txt");
		if (!output.exists()) {
			ranBefore = false;
		}
		if (ranBefore == true) {
			System.out.println("here");
			try {
				in = new Scanner(output);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("fail");
			}
			if (in.hasNextDouble()) {
				for (int i = 0; i < hiddenA; i++) {
					for (int j = 0; j < inputA; j++) {
						// System.out.println(i+" "+j);
						inWeight[i][j] = in.nextDouble();
					}
				}
				for (int i = 0; i < hiddenA; i++) {
					hWeight[i] = in.nextDouble();
				}
			}
		} else {
			System.out.println("here2");
			for (int i = 0; i < hiddenA; i++) {
				for (int j = 0; j < inputA; j++) {
					done = false;
					while (!done) {
						int temp = rand.nextInt(7) - 3;
						if (temp != 0) {
							inWeight[i][j] = temp;
							done = true;
						}
					}

				}
			}
			for (int i = 0; i < hiddenA; i++) {
				done = false;
				while (!done) {
					int temp = rand.nextInt(7) - 3;
					if (temp != 0) {
						hWeight[i] = rand.nextInt(7) - 3;
						done = true;
					}
				}

			}
		}
	}
	/*
	 * TD Neural Network
	 */
	private double TDOUT(int x, int y, String type) {
		activation.add(new double[hiddenA][inputA]);
		hiddenActivation.add(new double[hiddenA]);
		int max = hiddenActivation.size();
		// vPrevious = v;
		String otherType;
		if (type.equals("X")) {
			otherType = "O";
		} else {
			otherType = "X";
		}

		double amount = 0;
		for (int i = 0; i < hiddenA; i++) {
			amount = 0;
			for (int j = 0; j < inputA; j += 2) {
				double temp = amount;
				if (j < 96) {
					if ((Board[(j / 2) % 4][(j / 2) / 12].getPiece().equals(
							type) || ((j / 2) % 4 == x && (j / 2) / 12 == y))) {
						amount += inWeight[i][j];
						amount += inWeight[i][j + 1] * 0;
					} else if (Board[(j / 2) % 4][(j / 2) / 12].getPiece()
							.equals(otherType)) {
						amount += inWeight[i][j] * 0;
						amount += inWeight[i][j + 1] * -1;
					}
				} else if (j == 96) {
					if (type.equals("X")) {
						amount += inWeight[i][j] * (amountTurns / 2);
						amount += inWeight[i][j + 1] * ((amountTurns - 1) / 2);
					} else {
						amount += inWeight[i][j + 1] * (amountTurns / 2);
						amount += inWeight[i][j] * ((amountTurns) / 2);
					}
				} else if (j == 98) {
					if (type.equals("X")) {
						amount += inWeight[i][j];
					} else {
						amount += inWeight[i][j + 1];
					}
				}

				activation.get(activation.size() - 1)[i][j] = amount - temp;
			}
			hiddenLayerTotal[i] = activationFunction(amount);
		}
		amount = 0;
		for (int i = 0; i < hiddenA; i++) {
			double temp = hiddenLayerTotal[i] * hWeight[i];
			hiddenActivation.get(hiddenActivation.size() - 1)[i] = temp;
			amount += temp;
		}
		v = activationFunction(amount);
		return v;
	}

	private double TDNN(String type) {
		activation.add(new double[hiddenA][inputA]);
		hiddenActivation.add(new double[hiddenA]);
		String otherType;
		if (type.equals("X")) {
			otherType = "O";
		} else {
			otherType = "X";
		}
		double amount = 0;
		for (int i = 0; i < hiddenA; i++) {
			amount = 0;
			for (int j = 0; j < inputA; j += 2) {
				double temp = amount;
				if (j < 96) {
					if ((Board[(j / 2) % 4][(j / 2) / 12].getPiece()
							.equals(type))) {
						amount += inWeight[i][j];
						amount += inWeight[i][j + 1] * 0;
					} else if (Board[(j / 2) % 4][(j / 2) / 12].getPiece()
							.equals(otherType)) {
						amount += inWeight[i][j] * 0;
						amount += inWeight[i][j + 1] * -1;
					}
				} else if (j == 96) {
					if (type.equals("X")) {
						amount += inWeight[i][j] * (amountTurns / 2);
						amount += inWeight[i][j + 1] * ((amountTurns - 1) / 2);
					} else {
						amount += inWeight[i][j + 1] * (amountTurns / 2);
						amount += inWeight[i][j] * ((amountTurns) / 2);
					}
				} else if (j == 98) {
					if (type.equals("X")) {
						amount += inWeight[i][j];
					} else {
						amount += inWeight[i][j + 1];
					}
				}
				activation.get(activation.size() - 1)[i][j] = amount - temp;
			}
			hiddenLayerTotal[i] = activationFunction(amount);
		}
		amount = 0;
		for (int i = 0; i < hiddenA; i++) {
			double temp = hiddenLayerTotal[i] * hWeight[i];
			hiddenActivation.get(hiddenActivation.size() - 1)[i] = temp;
			amount += temp;
		}
		v = activationFunction(amount);
		return v;
	}

	private double activationFunction(double in_total) {
		double temp = 0;
		temp = Math.pow(Math.E, -in_total);
		temp++;
		temp = 1 / temp;
		return temp;

	}

	public boolean StartTD(String type) {
		double scoreBefore = TDNN(type);
		double[] score = new double[b.getOptionMoveFull().size()];
		// System.out.println(optionMoveFull.size());
		score[0] = -10000;
		int max = 0;

		for (int i = 0; i < b.getOptionMoveFull().size(); i++) {
			double temp = TDOUT(b.getOptionMoveFull().get(i).getRing(), b.getOptionMoveFull()
					.get(i).getLine(), type);
			if (score[max] < temp) {
				max = i;
			}
			score[i] = temp;
		}
		// for(int i = 0; i<optionMoveFull.size();i++)
		// {
		// System.out.println(score[i]);
		// }
		node temp = b.getOptionMoveFull().get(max);
		temp.setPiece(type);
		b.add(type, b.getOptionMoveFull().get(max).getRing(), b.getOptionMoveFull().get(max)
				.getLine(),true);

		double scoreAfter = TDNN(type);
		error.writer(scoreAfter - scoreBefore);
		b.getOptionMoveFull().get(max).setPiece(type);

		//optionMoveFull.remove(max);
		amountTurns--;
		if (hiddenActivation == null) {
			hiddenActivationPrev = hiddenActivation.get(max);
			activationPrev = activation.get(max);
		} else {
			System.out.println(temp.getPiece());
			if (b.checkWin(temp)) {
				error.writerln("");
				BackProp(1, 0);
				hiddenActivation.clear();
				activation.clear();
				return true;
			} else {
				if(scoreAfter-scoreBefore != 0){
				BackProp(scoreAfter, scoreBefore);
				}
				hiddenActivationPrev = hiddenActivation.get(max);
				activationPrev = activation.get(max);
				return false;
			}
		}
		return false;
	}

	private void BackProp(double output, double scoreBefore) {
		double[] activationResp = new double[hiddenA];
		double error = output - scoreBefore;
		for (int i = 0; i < hiddenA; i++) {
			hWeight[i] = hWeight[i]
					- (learningRate * error * output * hiddenActivationPrev[i]);
		}
		for (int i = 0; i < hiddenA; i++) {
			activationResp[i] = hiddenActivationPrev[i] * error;
		}
		for (int i = 0; i < hiddenA; i++) {
			for (int j = 0; j < inputA; j++) {
				inWeight[i][j] = inWeight[i][j]
						- (learningRate * error * activationResp[i] * inWeight[i][j]);
			}
		}
		// double E = .5*Math.pow(error, 2);

	}

}
