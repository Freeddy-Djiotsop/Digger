/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DiggerGame;
import javafx.application.Application;
import javafx.stage.Stage;

/**
/**
 *
 * @author Aldemashki
 */
public class Player extends Application {
    
    
    public static void main(String[] args) {
        launch(args);
        
    }
 
    @Override
    @SuppressWarnings("empty-statement")
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Digger");
        Map map = new Map();
        map.root.getChildren().add(map.canvas);
        primaryStage.setScene(map.s);
        primaryStage.show();
        map.root.setFocusTraversable(true);
        primaryStage.setResizable(false);
       
        
        
         
        
        
    }


   
}

