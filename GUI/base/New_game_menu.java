package digger.gui.base;

import java.io.IOException;
import java.util.ArrayList;

import digger.gui.controller.Controller;
import digger.gui.extend.BackgroundPane;
import digger.gui.extend.Image_button;
import javafx.scene.Scene;

public class New_game_menu {
	private Scene scene;
	private BackgroundPane pane;
	private ArrayList<Image_button> buttons;
	
	public New_game_menu() throws IOException{
		pane = new BackgroundPane(("/digger/gui/extend/New_gameBG.jpg"));
		try {
			new_button();
		}catch (IOException ex2) {
			ex2.printStackTrace();
		}
		scene = new Scene(pane);
		pane.getChildren().addAll(buttons);}
		
		private void new_button() throws IOException{
			buttons = new ArrayList<Image_button>();
			
			/**
	         * button[0] : one player option
	         * button[1] : two player option
	         * button[2] : back to main menu
	         */
			buttons.add(0, new Image_button(("/digger/gui/extend/One_player.jpg"),
					("/digger/gui/extend/One_player1.jpg")));
			buttons.get(0).setTranslateX(185);
			buttons.get(0).setTranslateY(160);
			buttons.add(1, new Image_button(("/digger/gui/extend/Two_player.jpg"),
					("/digger/gui/extend/Two_player1.jpg")));
			buttons.get(1).setTranslateX(185);
			buttons.get(1).setTranslateY(250);
			buttons.add(2, new Image_button(("/digger/gui/extend/Back.jpg"),
					("/digger/gui/extend/Back1.jpg")));
			buttons.get(2).setTranslateX(260);
			buttons.get(2).setTranslateY(350);
			buttons.get(2).setOnAction(e -> Controller.set_scene_main_menu());
		}
		
		public Scene getScene() {
			return scene;
		}

		public void setScene(Scene scene) {
			this.scene = scene;
		}

}
	
