package controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collection;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;
import view.TopPanel;

public class PlayerChanged implements ItemListener {
	
	private GameEngine gameEngine;
	private MainFrame mainFrame;
	
	public PlayerChanged(MainFrame mainFrame, GameEngine engine) {
		this.gameEngine = engine;
		this.mainFrame = mainFrame;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		/**
		 * We set set the setShowCards to false so that if other cards are
		 * currently being drawn they stop being displayed in the card panel.
		 */
		mainFrame.getCardPanel().setShowCards(false);
		// Updating the status
		mainFrame.getTopPanel().setStatusLabel(String.format("Selected player is %s.", mainFrame.getTopPanel().getSelectedPlayer()));
		// getting all of the card directories that belong to the player.
		String[] playersHand = mainFrame.getTopPanel().getPlayerHand();
		// Clearing the cards from the current card panel.
		mainFrame.getCardPanel().removeCards();
		// Adding the cards to the card panel.
		for (String card : playersHand) {
			mainFrame.getCardPanel().updateCardPanel(card);
		}
		// Buttons available to the house.
		if (mainFrame.getTopPanel().getSelectedPlayer() == "House") {
			mainFrame.getTopPanel().getBetButton().setEnabled(false);
			mainFrame.getTopPanel().getDealButton().setEnabled(false);
			mainFrame.getTopPanel().getResetBetButton().setEnabled(false);
			mainFrame.getTopPanel().getAddPlayerBtn().setEnabled(true);
			mainFrame.getTopPanel().getRemovePlayerBtn().setEnabled(false);
		} else {
			Collection<Player> players = gameEngine.getAllPlayers();
			for (Player player1 : players) {
				// Deactivate these buttons if the player has already been dealt.
				if (player1.getPlayerName() == mainFrame.getTopPanel().getSelectedPlayer() && mainFrame.getTopPanel().getDeltPlayers().contains(player1)) {
					mainFrame.getTopPanel().getBetButton().setEnabled(false);
					mainFrame.getTopPanel().getDealButton().setEnabled(false);
					mainFrame.getTopPanel().getResetBetButton().setEnabled(false);
				}
				// Otherwise the buttons are all available.
				else if (player1.getPlayerName() == mainFrame.getTopPanel().getSelectedPlayer()) {
					mainFrame.getTopPanel().getBetButton().setEnabled(true);
					mainFrame.getTopPanel().getDealButton().setEnabled(true);
					mainFrame.getTopPanel().getResetBetButton().setEnabled(true);
				}
			}
			mainFrame.getTopPanel().getAddPlayerBtn().setEnabled(true);
			mainFrame.getTopPanel().getRemovePlayerBtn().setEnabled(true);
		}
	}

}
