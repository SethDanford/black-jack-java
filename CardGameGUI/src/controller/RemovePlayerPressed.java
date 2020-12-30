package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.TopPanel;
import view.MainFrame;

public class RemovePlayerPressed implements ActionListener {
	private GameEngine gameEngine;
	private TopPanel toolBar;
	private MainFrame mainFrame;
	
	public RemovePlayerPressed(TopPanel toolBar, GameEngine engine, MainFrame mainFrame) {
		this.gameEngine = engine;
		this.toolBar = toolBar;
		this.mainFrame = mainFrame;
	}
		
	public void actionPerformed(ActionEvent e) {
		String selectedPlayer = toolBar.getSelectedPlayer();
		// Make sure house isn't selected.
		if (selectedPlayer != "House") {
			/**
			 * Get all of the players, find the player we want to remove and remove them.
			 */
			Collection<Player> players = gameEngine.getAllPlayers();
			for (Player player : players) {
				if (player.getPlayerName() == selectedPlayer) {
					gameEngine.removePlayer(player);
					mainFrame.getTable().removePlayer(player);
					toolBar.removePlayer(selectedPlayer);
					mainFrame.getCardPanel().removeCards();
					mainFrame.getTopPanel().setStatusLabel(String.format("Player %s was removed.", selectedPlayer));
				}
			}
		}
	}
}

// TODO: ROMOVE PLAYER FROM MULTIPLE PLACES