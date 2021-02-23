package digger.gui.controller;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import digger.gui.base.*;
import digger.logic.Map;
import java.io.IOException;
import javafx.scene.canvas.GraphicsContext;

/* @author Afraa Habbab
 *
 */

public class Controller extends Application {
	
	static Stage window;
	static MainMenu mMenuScene;
	static NewGameMenu nGameScene;
	static FindGameMenu fGameScene;
	static Map map1;
	static GameOverScene gOverScene;
	
	public Controller() throws Exception{
		mMenuScene = new MainMenu();
		nGameScene = new NewGameMenu();
		fGameScene = new FindGameMenu();
		map1 = new Map();
		gOverScene = new GameOverScene();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
             
		window = primaryStage;
		window.setWidth(793);
		window.setHeight(638);
		window.setScene(mMenuScene.getScene());
		window.setTitle("Digger");
		window.getIcons().add(new Image(getClass().getResourceAsStream("/digger/gui/extend/diggerIcon.jpg")));
		window.show();
        map1.getRoot().getChildren().add(map1.canvas);
        map1.getRoot().setFocusTraversable(true);
        
	}
		
	

	
public static void setSceneResumeGame(){
  map1.timer.start();  
map1.loadGame();
if(map1.getRoot().getChildren().isEmpty()) {
        	map1.getRoot().getChildren().add(map1.canvas);
        	map1.getRoot().setFocusTraversable(true);
        }
           
window.setScene(map1.getScene());

map1. mediaPlayer.setAutoPlay(true);
window.show();

}
	
	public static void setSceneNewGameMenu() {
        window.setScene(nGameScene.getScene());
        window.show();
    }
	
	public static void setSceneMainMenu() {
        window.setScene(mMenuScene.getScene());
        window.show();
    }
	
	public static void setSceneFindGameMenu() {
        window.setScene(fGameScene.getScene());
        window.show();
    }
	
	public static void setSceneMap() {
		map1.timer.start();
        
        if(map1.getRoot().getChildren().isEmpty()) {
        	map1.getRoot().getChildren().add(map1.canvas);
        	map1.getRoot().setFocusTraversable(true);
        }
                
		window.setScene(map1.getScene());
                map1. mediaPlayer.setAutoPlay(true);
		window.show();
	}

	public static void setSceneGameOver(int [] highScore) throws IOException {
                map1. mediaPlayer.stop();
		map1.timer.stop();
		map1 = null;
		map1 = new Map();
               
                 
		window.setScene(gOverScene.getScene(highScore));
		window.show();
		
	}

}
