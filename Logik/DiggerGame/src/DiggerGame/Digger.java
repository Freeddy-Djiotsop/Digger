/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DiggerGame;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Aldemashki
 */
public class Digger {
    public int diggerX = 80, diggerY = 80;

    
    
 
    public void draw(GraphicsContext gc){
        gc.setFill(Color.BLUE);
        gc.fillRect(diggerX, diggerY, 20, 20);
    }
    
      

 


    
}
