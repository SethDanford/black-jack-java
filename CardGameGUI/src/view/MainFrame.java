package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import model.interfaces.GameEngine;

public class MainFrame extends JFrame {
	private TopPanel topPanel;
	private Table table;
	private CardPanel cardPanel;
	private JMenuBar menu;
	// Get the dimensions of your computer screen.
	private final static Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	// Decide the frames height and width.
	final static int FRAMEHEIGHT = (int) screen.getHeight() / 2;
	final static int FRAMEWHIDTH = (int) screen.getWidth() / 2;
	
	public MainFrame(GameEngine engine) {
		
		setMinimumSize(new Dimension(FRAMEWHIDTH, FRAMEHEIGHT));
		setBounds((int) (FRAMEWHIDTH - screen.getWidth() / 4), (int) (FRAMEHEIGHT - screen.getHeight() / 4), FRAMEWHIDTH, FRAMEHEIGHT);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		// For the file menu.
		PullDownMenu menu = new PullDownMenu();
		setJMenuBar(menu);
		
		// Holds the toolBar, statusBar and all its methods.
		topPanel = new TopPanel(engine, this);
		getContentPane().add(topPanel, BorderLayout.NORTH);
		
		// Holds the table and all its methods.
		table = new Table(this, engine);
		getContentPane().add(table, BorderLayout.CENTER);
		
		// Does all of the card displaying.
		cardPanel = new CardPanel(this.getWidth(), this.getHeight()*2/3);
		this.displayCardPanel(FRAMEHEIGHT);
		
		// Since the house is initially selected we disable the appropriate buttons.
		setButtonsToFalse();
	}
	public TopPanel getTopPanel() {
		return topPanel;
	}
	public Table getTable() {
		return table;
	}
	public CardPanel getCardPanel() {
		return cardPanel;
	}
	public int getFrameHeight() {
		return FRAMEHEIGHT;
	}
	// responsible for setting up the CardPanel.
	public void displayCardPanel(int frameHeight) {
		getContentPane().add(cardPanel, BorderLayout.SOUTH);
		setBounds(100, 100, cardPanel.getCardWidth()*6, cardPanel.getCardHeight() + 240);
	}
	// Use case stated above on line 47.
	public void setButtonsToFalse() {
		topPanel.getBetButton().setEnabled(false);
		topPanel.getDealButton().setEnabled(false);
		topPanel.getResetBetButton().setEnabled(false);
		topPanel.getRemovePlayerBtn().setEnabled(false);
	}
}
