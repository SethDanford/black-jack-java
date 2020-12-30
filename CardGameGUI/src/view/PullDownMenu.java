package view;

import java.awt.Font;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import controller.CloseProgram;
import controller.ClosePullDownMenu;
/**
 * Sets up the File menu.
 * @author eclipsespecial
 *
 */
public class PullDownMenu extends JMenuBar {
	
	private JTextArea textArea;
	
	private JMenu pullDownMenu;
	public PullDownMenu() {
		pullDownMenu = new JMenu("File");
		// Creating the buttons.
		JMenuItem quit = new JMenuItem("QUIT", KeyEvent.VK_C);
		JMenuItem closeMenu = new JMenuItem("CLOSE", KeyEvent.VK_X);
		
		// Control q for quit shortcut.
		quit.setAccelerator(KeyStroke.getKeyStroke('q', InputEvent.CTRL_DOWN_MASK));
		textArea = new JTextArea(10, 30);
		textArea.setFont(new Font("Calibre", Font.PLAIN, 13));
		// What the buttons will do.
		quit.addActionListener(new CloseProgram());
		closeMenu.addActionListener(new ClosePullDownMenu(this));
		// Adding the buttons the file menu.
		pullDownMenu.add(closeMenu);
		pullDownMenu.addSeparator();
		pullDownMenu.add(quit);
		// adds FILE to the menu bar.
		add(pullDownMenu);
	}
	public JTextArea getTextArea() {
		return textArea;
	}
}
