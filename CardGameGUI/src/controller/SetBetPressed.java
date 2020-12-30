package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;
import view.TopPanel;

public class SetBetPressed implements ActionListener {
	private TopPanel toolBar;
	private GameEngine gameEngine;
	private MainFrame mainFrame;
	
	public SetBetPressed(TopPanel toolBar, GameEngine engine, MainFrame mainFrame) {
		this.toolBar = toolBar;
		this.gameEngine = engine;
		this.mainFrame = mainFrame;
	}
		
	public void actionPerformed(ActionEvent e) {
		String selectedPlayer = toolBar.getSelectedPlayer();
		if (selectedPlayer != "House") {
			// Find the player that we want to set the bet of.
			Collection<Player> players = gameEngine.getAllPlayers();
			for (Player player1 : players) {
				if (player1.getPlayerName() == selectedPlayer && !toolBar.getDeltPlayers().contains(player1)) {
					// Take their bet input.
					String bet = toolBar.setBet();
					// Set the bet to 0 if they don't type anything in order to avoid an error.
					if (bet.equals("")) {
						bet = "0";
					}
					int intBet = Integer.parseInt(bet);
					// Make sure the bet isn't below 0 or greater than the amount of points the player has.
					if (intBet <= player1.getPoints() && intBet > 0) {
						gameEngine.placeBet(player1, intBet);
						mainFrame.getTable().setBetValue(bet);
						mainFrame.getTopPanel().setStatusLabel(String.format("Player %s bet %s points.", mainFrame.getTopPanel().getSelectedPlayer(), bet));
					} else {
						JOptionPane.showMessageDialog(null, "You bet is invalid!", "WARNING", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}
	}
}
