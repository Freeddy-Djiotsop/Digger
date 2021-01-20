package digger.gui.base;

import javafx.application.Platform;
import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.layout.Pane;

import java.awt.Desktop;
//import java.awt.Graphics;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import digger.gui.controller.Controller;
import digger.gui.extend.*;

/**
 * 
 * @see Controller
 * @author Afraa Habbab
 *
 */
public class Main_menu {
	
	private Scene scene;
	private BackgroundPane pane;
	private ArrayList<Image_button> buttons;
	//Graphics g;
	public Main_menu() throws IOException{
		pane = new BackgroundPane("/digger/gui/extend/BackgroundMainMenu.jpg");
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
         * button[0] : resume game option
         * button[1] : creating a new game option
         * button[2] : searching for a game option
         * button[3] : game manual(original digger instructions)
         * button[4] : exit the game
         */
		buttons.add(0, new Image_button(("/digger/gui/extend/Resume_game.jpg"),
				("/digger/gui/extend/Resume_game1.jpg")));
		buttons.get(0).setTranslateX(250);
		buttons.get(0).setTranslateY(160);
		buttons.add(1, new Image_button(("/digger/gui/extend/New_game.jpg"),
				("/digger/gui/extend/New_game1.jpg")));
		buttons.get(1).setTranslateX(260);
		buttons.get(1).setTranslateY(215);
		buttons.add(2, new Image_button(("/digger/gui/extend/Find_game.jpg"),
				("/digger/gui/extend/Find_game1.jpg")));
		buttons.get(2).setTranslateX(255);
		buttons.get(2).setTranslateY(270);
		buttons.add(3, new Image_button(("/digger/gui/extend/How_to_play.jpg"),
				("/digger/gui/extend/How_to_play1.jpg")));
		buttons.get(3).setTranslateX(255);
		buttons.get(3).setTranslateY(320);
		buttons.add(4, new Image_button(("/digger/gui/extend/Exit.jpg"),
				("/digger/gui/extend/Exit1.jpg")));
		buttons.get(4).setTranslateX(300);
		buttons.get(4).setTranslateY(390);
		/**
		 * buttons functionality
		 */
		buttons.get(1).setOnAction(e -> Controller.set_scene_new_game_menu());
		buttons.get(2).setOnAction(e -> Controller.set_scene_find_game_menu());
		buttons.get(3).setOnAction(e -> {
            try {
                Desktop.getDesktop()
                        .browse(new URI("http://digger.org/instruct.html"));
            } catch (IOException | URISyntaxException e1) {
                e1.printStackTrace();
            }
        });
		buttons.get(4).setOnAction(e -> Platform.exit());
		


		
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}
}

