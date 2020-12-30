package view;

import java.util.Collection;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback {
	
	private MainFrame mainFrame;
	private boolean notDelt = true;
	
	public GameEngineCallbackGUI(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (notDelt == false) {
					mainFrame.getCardPanel().removeCards();
				}
				// Generates the card directories.
				String cardDirectory = String.format("cards/%s_%s.PNG", card.getSuit(), card.getValue());
				// Adds cards to a players hand.
				mainFrame.getTopPanel().addCardToHand(player.getPlayerName(), cardDirectory);
				// Actually adds the card visually to the card panel.
				mainFrame.getCardPanel().addCard(cardDirectory);
				// Now that the player had been dealt we can set this to true.
				notDelt = true;
			}
		});
	}

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// Does the same as nextCard except for bustCard.
				String cardDirectory = String.format("cards/%s_%s.PNG", card.getSuit(), card.getValue());
				mainFrame.getTopPanel().addCardToHand(player.getPlayerName(), cardDirectory);
				mainFrame.getCardPanel().addCard(cardDirectory);
				// notDelt is reset for the next player.
				notDelt = false;
			}
		});
	}

	@Override
	public void result(Player player, int result, GameEngine engine) {
		mainFrame.getTopPanel().setStatusLabel("Player was delt.");
			// Displays what the result is in the table.
			mainFrame.getTable().setResultValue(String.format("%d", result));
			// Makes it so that addCards (Which adds new cards) can now be used.
			mainFrame.getTopPanel().setCanDeal(true);
			// If the number of players that have been dealt is the same as the number of players in the table.
			if (mainFrame.getTable().getDefaultTable().getRowCount() == mainFrame.getTopPanel().getDeltPlayers().size()) {
				JOptionPane.showMessageDialog(null, "The House will now be delt.", "ATTENTION", JOptionPane.INFORMATION_MESSAGE);
				mainFrame.getTopPanel().setStatusLabel("Dealing house...");
				mainFrame.getTopPanel().getPlayerSelection().setSelectedItem("House");
				new Thread() {
					public void run() {
						engine.dealHouse(100);
					}
				}.start();
			}
	}
	// The following methods for House are near identical to what is done for the players above.
	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) {
		mainFrame.getCardPanel().setShowCards(true);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (notDelt == false) {
					mainFrame.getCardPanel().removeCards();
				}
				String cardDirectory = String.format("cards/%s_%s.PNG", card.getSuit(), card.getValue());
				mainFrame.getTopPanel().addCardToHand("House", cardDirectory);
				mainFrame.getCardPanel().addCard(cardDirectory);
				notDelt = true;
			}
		});
	}

	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				String cardDirectory = String.format("cards/%s_%s.PNG", card.getSuit(), card.getValue());
				mainFrame.getTopPanel().addCardToHand("House", cardDirectory);
				mainFrame.getCardPanel().addCard(cardDirectory);
				notDelt = false;
			}
		});
	}

	@Override
	public void houseResult(int result, GameEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mainFrame.getTopPanel().setStatusLabel("House was delt.");
				String winLoss = "";
				// Loop through all of the players.
				Collection<Player> players = engine.getAllPlayers();
				for (Player p : players) {
					engine.applyWinLoss(p, result);
					// Display the players updated points in the table.
					mainFrame.getTable().setPoints(p.getPlayerName(), String.format("%d", p.getPoints()));
					// Update the WIN/LOSS state of the player.
					if (result > p.getResult()) {
						mainFrame.getTable().setWinLoss(p.getPlayerName(), "LOSE");
					} else if (result < p.getResult()) {
						mainFrame.getTable().setWinLoss(p.getPlayerName(), "WIN");
					} else {
						mainFrame.getTable().setWinLoss(p.getPlayerName(), "DRAW");
					}
					// Remove the player if they run out of points.
					if (p.getPoints() <= 0) {
						mainFrame.getTable().removePlayer(p);
						JOptionPane.showMessageDialog(null, String.format("Player %s was removed because they ran out of points.", p.getPlayerName()), "WARNING", JOptionPane.INFORMATION_MESSAGE);
					}
					// Reset the player bets.
					engine.placeBet(p, 0);
					mainFrame.getTable().resetBetValue(p.getPlayerName());
					// Reset the deltPlayers for the next round.
					mainFrame.getTopPanel().getDeltPlayers().clear();
				}
			}
		});

	}
}
