package view;

import javax.swing.JFrame;

import model.interfaces.GameEngine;
import view.interfaces.GameEngineCallback;

public class App {
	MainFrame frame;

	public App(GameEngine engine) {
		initialize(engine);
	}
	// Creates the gameEngineCallback's needed for running the program
	private void initialize(GameEngine engine) {
		frame = new MainFrame(engine);
		GameEngineCallback callBackGUI = new GameEngineCallbackGUI(frame);
		GameEngineCallback callBack = new GameEngineCallbackImpl();
		engine.addGameEngineCallback(callBackGUI);
		engine.addGameEngineCallback(callBack);
		
	}

	public JFrame getFrame() {
		return frame;
	}
}







