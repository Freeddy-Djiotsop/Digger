package digger.gui.controller;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import digger.gui.base.*;
import digger.logic.Map;
/**
 * @author Afraa Habbab
 *
 */

public class Controller extends Application {
	
	static Stage window;
	static Main_menu m_m_scene;
	static New_game_menu n_g_scene;
	static Find_game_menu f_g_scene;
	static Map map_1;
	
	public Controller() throws Exception{
		m_m_scene = new Main_menu();
		n_g_scene = new New_game_menu();
		f_g_scene = new Find_game_menu();
		map_1 = new Map();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		window = primaryStage;
		window.setWidth(793);
		window.setHeight(638);
		window.setScene(m_m_scene.getScene());
		window.setTitle("Digger");
		window.getIcons().add(new Image(getClass().getResourceAsStream("/digger/gui/extend/diggerIcon.jpg")));
		window.show();
        map_1.getRoot().getChildren().add(map_1.canvas);
        map_1.getRoot().setFocusTraversable(true);
        
        
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
	
	public static void set_scene_map_scene() {
		window.setScene(map_1.getScene());
		window.show();
	}

}
