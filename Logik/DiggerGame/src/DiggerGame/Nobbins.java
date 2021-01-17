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
public class Nobbins extends Monster {
    
    
    


    
    public void draw(GraphicsContext gc , int x , int y){
        gc.setFill(Color.RED);
        
        gc.fillRect(x , y, 20, 20);
    }
    
  
    
     public void move(int[][]screenData){
    for(int i = 0;i<5;i++){     
    
    if(monsterX[i]<600 &&monsterY[i]<600&& monsterY[i]>0 && monsterX[i]>0){
    
    if(screenData [(monsterY[i]/20)][(monsterX[i]/20) - 1 ] == 2 ){
    
    monsterX[i] -=20;
    
    System.out.print(screenData [monsterY[i]/20][monsterX[i]/20]);
    System.out.println();
    
   
    }
    if(screenData [(monsterY[i]/20) + 1][(monsterX[i]/20)] == 4 ){
    monsterY[i] +=20;
    
    System.out.print(screenData [monsterY[i]/20][monsterX[i]/20]);
    System.out.println();
    
    }
    if(screenData [(monsterY[i]/20) - 1][(monsterX[i]/20)] == 8 ){
    monsterY[i] -=20;
    
    System.out.print(screenData [monsterY[i]/20][monsterX[i]/20]);
    System.out.println();
    
    }
    if(screenData [(monsterY[i]/20)][(monsterX[i]/20) + 1] == 6 ){
    monsterX[i] +=20;
    
    System.out.print(screenData [monsterY[i]/20][monsterX[i]/20]);
    System.out.println();
    
    
    }
    
    }
    
    }
    }
    
}
