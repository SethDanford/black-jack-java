package simpleTestClientGUI;

import java.awt.EventQueue;
import javax.swing.JFrame;

import model.GameEngineImpl;
import model.interfaces.GameEngine;
import view.App;

/**
 * Responsible for starting the application.
 * @author eclipsespecial
 *
 */
public class A2Tester {
	final static GameEngine gameEngine = new GameEngineImpl();
	static App framer = new App(gameEngine);
	private JFrame fraim = framer.getFrame();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					A2Tester window = new A2Tester();
					window.fraim.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
