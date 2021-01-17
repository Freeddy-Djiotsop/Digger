/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DiggerGame;

import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Aldemashki
 */
public class Score {
    
    public int score ;

    public void drawScore(GraphicsContext gc)
    {
   
    gc.strokeText("Score : " +score, 2, 10);
    
   }
    
}


