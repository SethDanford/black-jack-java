package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.util.EventObject;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.interfaces.GameEngine;
import model.interfaces.Player;

public class Table extends JPanel {
	private Player addingPlayer;
	private JTable table;
	private DefaultTableModel defaultTable;
	private String[] columnNames = {"ID", "NAME", "POINTS", "BET", "RESULT", "WIN/LOSS"};
	private LinkedList<Player> playerList = new LinkedList<>();
	private MainFrame mainFrame;
	private GameEngine gameEngine;
	
	public Table(MainFrame mainFrame, GameEngine gameEngine) {
		
		this.mainFrame = mainFrame;
		this.gameEngine = gameEngine;
		// What the table will look like.
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(mainFrame.FRAMEWHIDTH, mainFrame.getFrameHeight()/3));
		setBackground(Color.blue);
		
		defaultTable = new DefaultTableModel(columnNames, 0);
		
		table = new JTable(defaultTable) {
			public boolean editCellAt(int row, int column, EventObject e) {
				return false;
			}
		};
		// So that the table is scrollable.
		JScrollPane scrollingPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		table.setEnabled(false);
		add(scrollingPane, BorderLayout.CENTER);
	}
	// Updating the bet value in the table.
	public void setBetValue(String bet) {
		for (int i = 0; i <= defaultTable.getRowCount()-1; i++) {
			if (mainFrame.getTopPanel().getSelectedPlayer() == defaultTable.getValueAt(i, 1)) {
				defaultTable.setValueAt(bet, i, 3);
			}
		}
	}
	// Updating the bet value to 0.
	public void resetBetValue(String name) {
		for (int i = 0; i <= defaultTable.getRowCount()-1; i++) {
			if (defaultTable.getValueAt(i, 1) == name) {
				defaultTable.setValueAt("0", i, 3);
			}
		}
	}
	// Updating the result value in the table.
	public void setResultValue(String result) {
		for (int i = 0; i <= defaultTable.getRowCount()-1; i++) {
			if (mainFrame.getTopPanel().getSelectedPlayer() == defaultTable.getValueAt(i, 1)) {
				defaultTable.setValueAt(result, i, 4);
			}
		}
	}
	// Updating the points value in the table.
	public void setPoints(String playerName, String points) {
		for (int i = 0; i <= defaultTable.getRowCount()-1; i++) {
			if (playerName == defaultTable.getValueAt(i, 1)) {
				defaultTable.setValueAt(points, i, 2);
			}
		}
	}
	// Updating the WIN/LOSS value in the table.
	public void setWinLoss(String playerName, String winLoss) {
		
		for (int i = 0; i <= defaultTable.getRowCount()-1; i++) {
			if (playerName == defaultTable.getValueAt(i, 1)) {
				defaultTable.setValueAt(winLoss, i, 5);
			}
		}
	}
	
	public void storePlayer(Player player) {
		addingPlayer = player;
		playerList.add(player);
	}
	// Adds a player to the table.
	public void addPlayer(Player player) {
		final String DEFAULTWINLOSS = "NONE";
		Object[] row = {
				player.getPlayerId(),
				player.getPlayerName(),
				player.getPoints(),
				player.getBet(),
				player.getResult(),
				DEFAULTWINLOSS
		};
		// Makes sure that there are no players in the table who already have the same player ID.
		for (Player player1 : playerList) {
			if (Integer.parseInt(player1.getPlayerId()) == Integer.parseInt(player.getPlayerId())) {
				removePlayer(player1);
			}
		}
		defaultTable.addRow(row);
	}
	// Totally and completely removes a player from everywhere necessary.
	public void removePlayer(Player player) {
		for (int i = 0; i < defaultTable.getRowCount(); i++) {
			if (player.getPlayerId().equals(defaultTable.getValueAt(i, 0))) {
				defaultTable.removeRow(i);
				mainFrame.getTopPanel().removePlayer(player.getPlayerName());
				playerList.remove(player);
				gameEngine.removePlayer(player);
			}
		}
	}
	
	public Player getAddingPlayer() {
		return addingPlayer;
	}
	public DefaultTableModel getDefaultTable() {
		return defaultTable;
	}
}
