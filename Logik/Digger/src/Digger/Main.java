/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Digger;


import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author ghait
 */
public class Main extends Application {

    
    public static void main(String[] args) {
        launch(args);
        
    }
 
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Drawing Operations Test");
        map m = new map();
        m.root.getChildren().add(m.canvas);
        primaryStage.setScene(m.s);
        primaryStage.show();
        m.root.setFocusTraversable(true);
        primaryStage.setResizable(false);
        
         
        
        
    }


   
}
