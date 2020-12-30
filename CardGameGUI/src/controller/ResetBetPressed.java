package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;
import view.TopPanel;

public class ResetBetPressed implements ActionListener {
	private TopPanel toolBar;
	private MainFrame mainFrame;
	private GameEngine gameEngine;
		
	public ResetBetPressed(TopPanel toolBar, GameEngine engine, MainFrame mainFrame) {
		this.toolBar = toolBar;
		this.gameEngine = engine;
		this.mainFrame = mainFrame;
	}
	public void actionPerformed(ActionEvent e) {
		String selectedPlayer = toolBar.getSelectedPlayer();
		/**
		 * Make sure house isn't selected, find the player we want and set their bet to 0.
		 */
		if (selectedPlayer != "House") {
			Collection<Player> players = gameEngine.getAllPlayers();
			for (Player player1 : players) {
				if (player1.getPlayerName() == selectedPlayer && !toolBar.getDeltPlayers().contains(player1)) {
					mainFrame.getTopPanel().setStatusLabel(String.format("Player %s reset their bet.", player1.getPlayerName()));
					gameEngine.placeBet(player1, 0);
					mainFrame.getTable().setBetValue("0");
				}
			}
		}
	}
}
