package digger.gui.base;

import java.io.IOException;
import java.util.ArrayList;



import digger.gui.controller.Controller;
import digger.gui.extend.BackgroundPane;
import digger.gui.extend.Image_button;
//import digger.gui.extend.Image_label;
import javafx.scene.Scene;

public class Find_game_menu {
	private Scene scene;
	private BackgroundPane pane;
	//private Image_label waiting_for_a_match ;
	private ArrayList<Image_button> buttons;
	
	public Find_game_menu() throws IOException{
		pane = new BackgroundPane(("/digger/gui/extend/Find_gameBG.jpg"));
		try {
			new_button();
		}catch (IOException ex2) {
			ex2.printStackTrace();
		}
		scene = new Scene(pane);
		pane.getChildren().addAll(buttons);
		/*pane.getChildren().addAll(waiting_for_a_match);
		new_label();
		new_button();
		*/
	}
		
		private void new_button() throws IOException{
			buttons = new ArrayList<Image_button>();
			
	        //button[0] : searching for a game
			//button[1] : back to main menu
			buttons.add(0, new Image_button(("/digger/gui/extend/Waiting_for_a_match.jpg"),
					("/digger/gui/extend/Waiting_for_a_match.jpg")));
			buttons.get(0).setTranslateX(90);
			buttons.get(0).setTranslateY(200);
			buttons.add(1, new Image_button(("/digger/gui/extend/Back.jpg"),
					("/digger/gui/extend/Back1.jpg")));
			buttons.get(1).setTranslateX(240);
			buttons.get(1).setTranslateY(350);
			//button 1 functionality
			buttons.get(1).setOnAction(e -> Controller.set_scene_main_menu());
		}
		
		/*private void new_label() throws IOException {
			waiting_for_a_match = new Image_label("/digger/gui/extend/Waiting_for_a_match.jpg");
			waiting_for_a_match.setTranslateX(90);
			waiting_for_a_match.setTranslateY(200);
		}
		*/
		
		
		
		public Scene getScene() {
			return scene;
		}

		public void setScene(Scene scene) {
			this.scene = scene;
		}

}
