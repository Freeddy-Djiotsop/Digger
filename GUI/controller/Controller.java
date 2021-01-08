package digger.gui.controller;

import javafx.application.Application;
import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import digger.gui.base.*;


public class Controller extends Application {
	
	static Stage window;
	static Main_menu m_m_scene;
	static New_game_menu n_g_scene;
	static Find_game_menu f_g_scene;
	
	public Controller() throws Exception{
		m_m_scene = new Main_menu();
		n_g_scene = new New_game_menu();
		f_g_scene = new Find_game_menu();
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		window = primaryStage;
		window.setScene(m_m_scene.getScene());
		window.setTitle("Digger");
		window.getIcons().add(new Image(getClass().getResourceAsStream("/digger/gui/extend/digger2.jpg")));
		window.show();
	}
	
	public static void set_scene_new_game_menu() {
        window.setScene(n_g_scene.getScene());
        window.show();
    }
	
	public static void set_scene_main_menu() {
        window.setScene(m_m_scene.getScene());
        window.show();
    }
	
	public static void set_scene_find_game_menu() {
        window.setScene(f_g_scene.getScene());
        window.show();
    }

}
