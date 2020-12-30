package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseProgram implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// Closes the whole cardGameGUI.
		System.exit(0);
		
	}

}
