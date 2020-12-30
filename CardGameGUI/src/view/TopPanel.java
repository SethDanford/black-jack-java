package view;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;


import controller.AddPlayerPressed;
import controller.DealButtonPressed;
import controller.PlayerChanged;
import controller.RemovePlayerPressed;
import controller.ResetBetPressed;
import controller.SetBetPressed;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class TopPanel extends JPanel {
	
	private JLabel statusLabel = new JLabel();
	private JToolBar toolBar = new JToolBar();
	private JComboBox playerSelection;
	private JLabel dealStatusLabel;
	private JButton betButton;
	private JButton dealButton;
	private JButton addPlayerBtn;
	private JButton removePlayerBtn;
	private JButton resetBet;
	
	private Boolean canDeal = true;
	
	private LinkedList<String> players = new LinkedList<>();
	private HashMap<String, String> playerCardAssignment = new HashMap<String, String>();
	private LinkedList<Player> deltPlayers = new LinkedList<>();
	
	public TopPanel(GameEngine engine, MainFrame mainFrame) {
		
		this.setLayout(new BorderLayout(0, 0));
		add(toolBar, BorderLayout.NORTH);
		
		statusLabel.setText("Hurry up and do something!!!");
		add(statusLabel, BorderLayout.WEST);
		
		/**
		 *  The following inserts the necessary elements into the toolBar as well as
		 *  Initializes their listeners.
		 */
		resetBet = new JButton("Reset Bet");
		toolBar.add(resetBet);
		resetBet.addActionListener(new ResetBetPressed(this, engine, mainFrame));

		betButton = new JButton("Bet");
		toolBar.add(betButton);
		betButton.addActionListener(new SetBetPressed(this, engine, mainFrame));
		
		dealButton = new JButton("Deal");
		toolBar.add(dealButton);
		dealButton.addActionListener(new DealButtonPressed(this, engine, mainFrame));
		
		playerSelection = new JComboBox();
		toolBar.add(playerSelection);
		playerSelection.addItemListener(new PlayerChanged(mainFrame, engine));
		addPlayer("House");
		
		addPlayerBtn = new JButton("Add Player");
		toolBar.add(addPlayerBtn);
		addPlayerBtn.addActionListener(new AddPlayerPressed(this, mainFrame, engine));
		
		removePlayerBtn = new JButton("Remove Player");
		toolBar.add(removePlayerBtn);
		removePlayerBtn.addActionListener(new RemovePlayerPressed(this, engine, mainFrame));
	}
	// All of the pop-up dialogue boxes for taking player input.
	public String IDInput() {
		return JOptionPane.showInputDialog("Please input a player ID: ");
	}
	public String nameInput() {
		return JOptionPane.showInputDialog("Please input a player name: ");
	}
	public String pointsInput() {
		return JOptionPane.showInputDialog("Please input a number of points: ");
	}
	public String setBet() {
		return JOptionPane.showInputDialog("Please input you bet amount: ");
	}
	
	public String getSelectedPlayer() {
		return (String) playerSelection.getSelectedItem();
	}
	// Adds player to comboBox.
	public void addPlayer(String playerName) {
		players.add(playerName);
		Object[] playerArray = players.toArray();
		playerSelection.setModel(new DefaultComboBoxModel(playerArray));
	}
	// Removes player from comboBox.
	public void removePlayer(String playerName) {
		players.remove(playerName);
		Object[] playerArray = players.toArray();
		playerSelection.setModel(new DefaultComboBoxModel(playerArray));
	}
	// Adds a card to a players hand.
	public void addCardToHand(String player, String card) {
		playerCardAssignment.put(player+card, card);
	}
	// Gets all the cards that belong to a player.
	public String[] getPlayerHand() {
		int i = 0;
		String[] cardArray = new String[6];
		for (Entry<String, String> entry : playerCardAssignment.entrySet()) {
		    String key = entry.getKey();
		    String value = entry.getValue();
		    if (key.contains(this.getSelectedPlayer())) {
		    	cardArray[i] = value;
				i += 1;
		    }
		}	
		return cardArray;
	}
	public boolean getCardNumbers() {
		return playerCardAssignment.isEmpty();
	}
	public JButton getBetButton() {
		return this.betButton;
	}
	public JButton getDealButton() {
		return this.dealButton;
	}
	public JButton getAddPlayerBtn() {
		return this.addPlayerBtn;
	}
	public JButton getRemovePlayerBtn() {
		return this.removePlayerBtn;
	}
	public JButton getResetBetButton() {
		return this.resetBet;
	}
	public LinkedList getDeltPlayers() {
		return deltPlayers;
	}
	public JComboBox getPlayerSelection() {
		return playerSelection;
	}
	public JToolBar getToolBar() {
		return toolBar;
	}
	public void setStatusLabel(String text) {
		statusLabel.setText(text);
	}
	public void setCanDeal(Boolean set) {
		canDeal = set;
	}
	public Boolean getCanDeal() {
		return canDeal;
	}
}












