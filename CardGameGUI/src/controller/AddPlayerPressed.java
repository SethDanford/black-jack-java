package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JOptionPane;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;
import view.TopPanel;

public class AddPlayerPressed implements ActionListener {
	private MainFrame mainFrame;
	private TopPanel topPanel;
	private GameEngine gameEngine;
	
	public AddPlayerPressed(TopPanel topPanel, MainFrame mainFrame, GameEngine engine) {
		this.mainFrame = mainFrame;
		this.topPanel = topPanel;
		this.gameEngine = engine;
	}
		
	public void actionPerformed(ActionEvent e) {
		/*
		 * A try-catch is put over the whole block of code in order to catch any invalid inputs from the user.
		 */
		try {
			/*
			 * Getting player inputs as follows.
			 */
			String ID = topPanel.IDInput();
			String name = topPanel.nameInput();
			String points = topPanel.pointsInput();
			/*
			 * Making sure that all inputs are not empty.
			 */
			if (!(ID.equals("") || name.equals("") || points.equals(""))) {
					/*
					 * Turning points into an integer.
					 */
					int intPoints = Integer.parseInt(points);
					// addPlayer(name) will add a player to the comboBox.
					topPanel.addPlayer(name);
					Player newPlayer = new SimplePlayer(ID, name, intPoints);
					gameEngine.addPlayer(newPlayer);
					
					// The following will add a player to the table and update the statusBar.
					mainFrame.getTable().addPlayer(newPlayer);
					mainFrame.getTable().storePlayer(newPlayer);
					mainFrame.getTopPanel().setStatusLabel(String.format("Player %s was added.", name));
			}
			else {
				JOptionPane.showMessageDialog(null, "INVALID INPUT!", "WARNING", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		catch(Exception InvalidInputException) {
			JOptionPane.showMessageDialog(null, "INVALID INPUT!", "WARNING", JOptionPane.INFORMATION_MESSAGE);
		}
}
}
