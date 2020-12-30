package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.LinkedList;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;
import view.TopPanel;

public class DealButtonPressed implements ActionListener {
	private TopPanel topPanel;
	private GameEngine gameEngine;
	private MainFrame mainFrame;
	
	public DealButtonPressed(TopPanel topPanel, GameEngine engine, MainFrame mainFrame) {
		this.topPanel = topPanel;
		this.gameEngine = engine;
		this.mainFrame = mainFrame;
	}
		
	public void actionPerformed(ActionEvent e) {
		// Checking if the player can be dealt.
		if (topPanel.getCanDeal() == true) {
			String selectedPlayer = topPanel.getSelectedPlayer();
			// Making sure the the selected player is not house since house cannot deal using deal button.
			if (selectedPlayer != "House") {
				// Getting all the players.
				Collection<Player> players = gameEngine.getAllPlayers();
				for (Player player : players) {
					/*
					 * Checking that the player is the player that we want and 
					 * that the player has not been dealt and
					 * that the players bet is not 0.
					 */
					if (player.getPlayerName() == selectedPlayer && !topPanel.getDeltPlayers().contains(player) && player.getBet() != 0) {
						// Setting canDeal so that no one else can deal at the same time.
						topPanel.setCanDeal(false);
						// Allowing the addCard method to be called.
						mainFrame.getCardPanel().setShowCards(true);
						mainFrame.getTopPanel().setStatusLabel("Dealing player...");
						// Since the player will have been dealt we add then to the list of dealt players.
						topPanel.getDeltPlayers().add(player);
						// disabling buttons that need to be disabled.
						mainFrame.getTopPanel().getBetButton().setEnabled(false);
						mainFrame.getTopPanel().getDealButton().setEnabled(false);
						mainFrame.getTopPanel().getResetBetButton().setEnabled(false);
						new Thread() {
							public void run() {
								gameEngine.dealPlayer(player, 100);	
							}
						}.start();
					}
				}
			}
		}
	}
}
