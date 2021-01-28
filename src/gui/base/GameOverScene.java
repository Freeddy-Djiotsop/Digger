package digger.gui.base;

import java.io.IOException;
import java.util.ArrayList;

import digger.gui.controller.Controller;
import digger.gui.extend.BackgroundPane;
import digger.gui.extend.Image_button;
import javafx.application.Platform;
import javafx.scene.Scene;

public class GameOverScene {
	private Scene scene;
	private BackgroundPane pane;
	private ArrayList<Image_button> buttons;
	
	public GameOverScene() throws IOException {
		pane = new BackgroundPane("/digger/gui/extend/GameOver.jpg");
		try {
			new_button();
		}catch (IOException ex2) {
			ex2.printStackTrace();
		}
		
		scene = new Scene(pane);
		pane.getChildren().addAll(buttons);
	}
	
	private void new_button() throws IOException{
		buttons = new ArrayList<Image_button>();
		
		/**
         * button[0] : new game option
         * button[1] : creating a new game option
         * button[2] : searching for a game option
         * button[3] : game manual(original digger instructions)
         * button[4] : exit the game
         */
		buttons.add(0, new Image_button(("/digger/gui/extend/New_game.jpg"),
				("/digger/gui/extend/New_game1.jpg")));
		buttons.get(0).setTranslateX(250);
		buttons.get(0).setTranslateY(160);
		buttons.add(1, new Image_button(("/digger/gui/extend/ReturnToMainMenu.jpg"),
				("/digger/gui/extend/ReturnToMainMenu1.jpg")));
		buttons.get(1).setTranslateX(260);
		buttons.get(1).setTranslateY(215);
		buttons.add(2, new Image_button(("/digger/gui/extend/Exit.jpg"),
				("/digger/gui/extend/Exit1.jpg")));
		buttons.get(2).setTranslateX(255);
		buttons.get(2).setTranslateY(270);
		
		
		buttons.get(0).setOnAction(e -> Controller.set_scene_new_game_menu());
		buttons.get(1).setOnAction(e -> Controller.set_scene_main_menu());
		buttons.get(2).setOnAction(e -> Platform.exit());
		
	}
	
	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

}
