package digger.gui.base;

import java.io.IOException;
import java.util.ArrayList;

import digger.gui.controller.Controller;
import digger.gui.extend.BackgroundPane;
import digger.gui.extend.ImageButton;
import javafx.application.Platform;
import javafx.scene.Scene;

/**
 * @see Controller, Map
 * @author Afraa Habbab
 *
 */
public class GameOverScene {
	private Scene scene;
	private BackgroundPane pane;
	private ArrayList<ImageButton> buttons;
	
	public GameOverScene() throws IOException {
		pane = new BackgroundPane("/digger/gui/extend/GameOver.jpg");
		try {
			newButton();
		}catch (IOException ex2) {
			ex2.printStackTrace();
		}
		
		scene = new Scene(pane);
		pane.getChildren().addAll(buttons);
	}
	
	private void newButton() throws IOException{
		buttons = new ArrayList<ImageButton>();
		
		/**
         * button[0] : new game option
         * button[1] : go back to main menu
         * button[2] : Exit the game
         */
		buttons.add(0, new ImageButton(("/digger/gui/extend/NewGame.jpg"),
				("/digger/gui/extend/NewGame1.jpg")));
		buttons.get(0).setTranslateX(450);
		buttons.get(0).setTranslateY(160);
		buttons.add(1, new ImageButton(("/digger/gui/extend/ReturnToMainMenu.jpg"),
				("/digger/gui/extend/ReturnToMainMenu1.jpg")));
		buttons.get(1).setTranslateX(330);
		buttons.get(1).setTranslateY(315);
		buttons.add(2, new ImageButton(("/digger/gui/extend/Exit.jpg"),
				("/digger/gui/extend/Exit1.jpg")));
		buttons.get(2).setTranslateX(500);
		buttons.get(2).setTranslateY(470);
		
		/*
		 * buttons functionality
		 */
		buttons.get(0).setOnAction(e -> Controller.setSceneNewGameMenu());
		buttons.get(1).setOnAction(e -> Controller.setSceneMainMenu());
		buttons.get(2).setOnAction(e -> Platform.exit());
		
	}
	
	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

}
