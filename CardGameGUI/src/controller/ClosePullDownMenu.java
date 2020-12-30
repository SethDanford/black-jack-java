package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;

import view.PullDownMenu;

public class ClosePullDownMenu implements ActionListener {
	
	private PullDownMenu menu;
	
	public ClosePullDownMenu(PullDownMenu menu) {
		this.menu = menu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Closes the "File" menu.
		JScrollPane scrollPane = new JScrollPane(menu.getTextArea());
		menu.getTextArea().setText("");
		menu.remove(scrollPane);
		menu.repaint();
	}

}
