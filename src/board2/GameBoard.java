package board2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class GameBoard implements ActionListener {

	public JFrame frame;
	GridPanel game_panel;
	JButton b;
	JButton b1;
	public GameBoard() {
		init();
	}

	private void init() {
		frame = new JFrame("Polar TTT");
		frame.setResizable(true);
		frame.setMinimumSize(new Dimension(600, 600));
		frame.setBounds(100, 100, 1400, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.5, 1.0, 0.5 };
		gridBagLayout.rowWeights = new double[] { 0.95, 0.05 };
		frame.getContentPane().setLayout(gridBagLayout);

		JPanel player_one_panel = new JPanel();
		player_one_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null,
				null, null, null));
		GridBagConstraints gbc_player_one_panel = new GridBagConstraints();
		gbc_player_one_panel.insets = new Insets(0, 0, 5, 0);
		gbc_player_one_panel.fill = GridBagConstraints.BOTH;
		gbc_player_one_panel.gridx = 0;
		gbc_player_one_panel.gridy = 0;
		frame.getContentPane().add(player_one_panel, gbc_player_one_panel);

		game_panel = new GridPanel();
		game_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		GridBagConstraints gbc_game_panel = new GridBagConstraints();
		gbc_game_panel.insets = new Insets(0, 0, 5, 0);
		gbc_game_panel.fill = GridBagConstraints.BOTH;
		gbc_game_panel.gridx = 1;
		gbc_game_panel.gridy = 0;
		frame.getContentPane().add(game_panel, gbc_game_panel);

		JPanel player_two_panel = new JPanel();
		player_two_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null,
				null, null, null));
		GridBagConstraints gbc_player_two_panel = new GridBagConstraints();
		gbc_player_two_panel.insets = new Insets(0, 0, 5, 0);
		gbc_player_two_panel.fill = GridBagConstraints.BOTH;
		gbc_player_two_panel.gridx = 2;
		gbc_player_two_panel.gridy = 0;
		frame.getContentPane().add(player_two_panel, gbc_player_two_panel);

		JPanel options_panel = new JPanel();
		options_panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null,
				null, null));
		options_panel.setLayout(new GridBagLayout());

		String[] options1 = { "Player1", "Neural Net1", "Knn Classifier1",
				"Minimax1", "Minimax AB1", "Random1" };

		JComboBox<String> option_b1 = new JComboBox<String>(options1);
		GridBagConstraints gbc_options_panel = new GridBagConstraints();
		gbc_options_panel.gridx = 0;
		gbc_options_panel.weightx = .5;
		gbc_options_panel.gridy = 1;
		option_b1.setSelectedIndex(4);
		option_b1.addActionListener(this);
		options_panel.add(option_b1, gbc_options_panel);
		frame.getContentPane().add(options_panel, gbc_options_panel);

		JPanel options_panel2 = new JPanel();
		options_panel2.setBorder(new BevelBorder(BevelBorder.RAISED, null,
				null, null, null));
		options_panel2.setLayout(new GridBagLayout());
		String[] options2 = { "Player2", "Neural Net2", "Knn Classifier2",
				"Minimax2", "Minimax AB2", "Random2" };
		JComboBox<String> option_b2 = new JComboBox<String>(options2);
		GridBagConstraints gbc_options_panel2 = new GridBagConstraints();
		gbc_options_panel2.gridx = 2;
		gbc_options_panel2.weightx = .5;
		gbc_options_panel2.gridy = 1;
		option_b2.setSelectedIndex(4);
		option_b2.addActionListener(this);
		options_panel2.add(option_b2, gbc_options_panel2);
		frame.getContentPane().add(options_panel2, gbc_options_panel2);

		JPanel play_panel = new JPanel();
		b = new JButton("Play");
		b.setVerticalTextPosition(AbstractButton.CENTER);
		b.setMnemonic(KeyEvent.VK_P);
		b.addActionListener(this);
		play_panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null,
				null, null));
		play_panel.setLayout(new GridBagLayout());
		// JComboBox<String> play_panel = new JComboBox<String>(options2);
		GridBagConstraints gbc_play_panel = new GridBagConstraints();
		gbc_play_panel.gridx = 1;
		gbc_play_panel.weightx = .5;
		gbc_play_panel.gridy = 0;
		play_panel.add(b, gbc_play_panel);
		frame.getContentPane().add(play_panel, gbc_play_panel);

		b1 = new JButton("Reset");
		b1.setVerticalTextPosition(AbstractButton.CENTER);
		b1.setMnemonic(KeyEvent.VK_P);
		b1.addActionListener(this);
		//play_panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null,
			//	null, null));
	//	play_panel.setLayout(new GridBagLayout());
		// JComboBox<String> play_panel = new JComboBox<String>(options2);
		//GridBagConstraints gbc_play_panel = new GridBagConstraints();
		gbc_play_panel.gridx = 1;
		gbc_play_panel.weightx = .5;
		gbc_play_panel.gridy = -1;
		play_panel.add(b1, gbc_play_panel);
		frame.getContentPane().add(play_panel, gbc_play_panel);
	}

	public void actionPerformed(ActionEvent e) {
		if (!e.getSource().equals(b)&&!e.getSource().equals(b1)) {
			JComboBox cb = (JComboBox) e.getSource();
			String gameType = (String) cb.getSelectedItem();
			if (gameType.equals("Player1")) {
				System.out.println("here2");
				main.choiceX = 1;
				main.playing = true;
				main.allPlayers = new Player(main.choiceX,main.choiceO);
			}

			if (gameType.equals("Neural Net1")) {
				main.choiceX = 2;
			}
			if (gameType.equals("Knn Classifier1")) {
				main.choiceX = 3;
			}
			if (gameType.equals("Minimax1")) {
				
				main.choiceX = 4;
			}
			if (gameType.equals("Minimax AB1")) {
				main.choiceX = 5;
			}
			if (gameType.equals("Random1")) {
				main.choiceX = 6;
			}

			if (gameType.equals("Player2")) {
				System.out.println("here2");
				main.choiceO = 1;
				main.playing = true;
				main.allPlayers = new Player(main.choiceX,main.choiceO);
			}

			if (gameType.equals("Neural Net2")) {
				main.choiceO = 2;
			}
			if (gameType.equals("Knn Classifier2")) {
				main.choiceO = 3;
			}
			if (gameType.equals("Minimax2")) {
				main.choiceO = 4;
			}
			if (gameType.equals("Minimax AB2")) {
				main.choiceO = 5;
			}
			if (gameType.equals("Random2")) {
				main.choiceO = 6;
			}
		} else if(e.getSource().equals(b)){
			main.play();
		}else{
			main.allPlayers.b.reset();
			game_panel.setVisible(false);
			game_panel = new GridPanel();
			game_panel.setVisible(true);
			game_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
					null, null));
			GridBagConstraints gbc_game_panel = new GridBagConstraints();
			gbc_game_panel.insets = new Insets(0, 0, 5, 0);
			gbc_game_panel.fill = GridBagConstraints.BOTH;
			gbc_game_panel.gridx = 1;
			gbc_game_panel.gridy = 0;
			frame.getContentPane().add(game_panel, gbc_game_panel);
		//	game_panel.repaint();
		}
	}
	

}
