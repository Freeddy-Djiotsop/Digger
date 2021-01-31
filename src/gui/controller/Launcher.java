package digger.gui.controller;

import digger.logic.Map;
import java.awt.EventQueue;
import javafx.application.Application;

public class Launcher {

    public static void main(String[] args) {
        Application.launch(Controller.class, args);
        EventQueue.invokeLater(() -> {
			
			
			
			
			String filepath = "pacmanMusic.wav";
			Map musicObject = new Map();
			musicObject.playMusic(filepath);
		

	});
    }

}