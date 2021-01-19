/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DiggerGame;


import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author Aldemashki
 */
public class Nobbins extends Monster {
    public boolean hobbinsOn = false;
    public Random random = new Random();
    public Digger digger = new Digger();
    public int k=1;
    public Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(10), ev -> { 
      
         k++;
       
         }));
   
    
    
    


    
    public void draw(GraphicsContext gc , int x , int y){
        gc.setFill(Color.RED);
        if(k<5)
        gc.fillRect(x , y, 20, 20);
        else{
        gc.fillOval(x, y, 20, 20);
        /*hobbinsOn = true;*/}
    }
    
  
    
     public void move(int[][]screenData){
    timeline2.play();
    for(int i = 0;i<k;i++){
        
    if(monsterX[i]<=600 &&monsterY[i]<=600&& monsterX[i]>=0 && monsterY[i]>=20 && hobbinsOn == false) {
    if((screenData [(monsterY[i]/20)][(monsterX[i]/20) - 1] == 2 
       || screenData [(monsterY[i]/20) - 1][(monsterX[i]/20)] == 2 
       || screenData [(monsterY[i]/20) + 1][(monsterX[i]/20)] == 2) && screenData [(monsterY[i]/20)][(monsterX[i]/20) - 1] != 3){
    monsterX[i] -=20;
    /*    System.out.print(screenData [monsterY[i]/20][monsterX[i]/20]);
    System.out.println();*/
    }
    else if((screenData [(monsterY[i]/20)][(monsterX[i]/20) + 1] == 6 
       || screenData [(monsterY[i]/20) - 1][(monsterX[i]/20)] == 6 
       || screenData [(monsterY[i]/20) + 1][(monsterX[i]/20)] == 6) && screenData [(monsterY[i]/20)][(monsterX[i]/20) + 1] != 3){
    monsterX[i] +=20;
    /*    System.out.print(screenData [monsterY[i]/20][monsterX[i]/20]);
    System.out.println();*/
    }    
    else if((screenData [(monsterY[i]/20)][(monsterX[i]/20) - 1] == 8 
       || screenData [(monsterY[i]/20)][(monsterX[i]/20) + 1] == 8
       || screenData [(monsterY[i]/20) - 1][(monsterX[i]/20)] == 8 )&& screenData [(monsterY[i]/20) - 1][(monsterX[i]/20)] != 3){
    monsterY[i] -=20;
    /*    System.out.print(screenData [monsterY[i]/20 - 1][monsterX[i]/20]);
    System.out.println();*/
    }    
    else if((screenData [(monsterY[i]/20)][(monsterX[i]/20) - 1] == 4 
       || screenData [(monsterY[i]/20)][(monsterX[i]/20) + 1] == 4
       || screenData [(monsterY[i]/20) + 1][(monsterX[i]/20)] == 4)&& screenData [(monsterY[i]/20) + 1][(monsterX[i]/20)] != 3){
    monsterY[i] +=20;
    /*    System.out.print(screenData [monsterY[i]/20][monsterX[i]/20]);
    System.out.println();*/
    }
    else if(screenData [(monsterY[i]/20)][(monsterX[i]/20)] == 4 && screenData [(monsterY[i]/20) + 1][(monsterX[i]/20)] == 2 ){
    monsterY[i] +=20;
    }
    else if(screenData [(monsterY[i]/20)][(monsterX[i]/20)] == 4 && screenData [(monsterY[i]/20) + 1][(monsterX[i]/20)] == 6 ){
    monsterY[i] +=20;
    }
    else if(screenData [(monsterY[i]/20)][(monsterX[i]/20)] == 4 && screenData [(monsterY[i]/20) + 1][(monsterX[i]/20)] == 8 ){
    monsterY[i] +=20;
    }    
    else if(screenData [(monsterY[i]/20)][(monsterX[i]/20)] == 8 && screenData [(monsterY[i]/20) - 1][(monsterX[i]/20)] == 2 ){
    monsterY[i] -=20;
    }
    else if(screenData [(monsterY[i]/20)][(monsterX[i]/20)] == 8 && screenData [(monsterY[i]/20) - 1][(monsterX[i]/20)] == 6 ){
    monsterY[i] -=20;
    }
    else if(screenData [(monsterY[i]/20)][(monsterX[i]/20)] == 8 && screenData [(monsterY[i]/20) - 1][(monsterX[i]/20)] == 4 ){
    monsterY[i] -=20;
    }
    else if(screenData [(monsterY[i]/20)][(monsterX[i]/20)] == 2 && screenData [(monsterY[i]/20)][(monsterX[i]/20) - 1] == 8 ){
    monsterX[i] -=20;
    }
    else if(screenData [(monsterY[i]/20)][(monsterX[i]/20)] == 2 && screenData [(monsterY[i]/20)][(monsterX[i]/20) - 1] == 6 ){
    monsterX[i] -=20;
    }
    else if(screenData [(monsterY[i]/20)][(monsterX[i]/20)] == 2 && screenData [(monsterY[i]/20)][(monsterX[i]/20) - 1] == 4 ){
    monsterX[i] -=20;
    }
    else if(screenData [(monsterY[i]/20)][(monsterX[i]/20)] == 6 && screenData [(monsterY[i]/20)][(monsterX[i]/20) + 1] == 2 ){
    monsterX[i] +=20;
    }
    else if(screenData [(monsterY[i]/20)][(monsterX[i]/20)] == 6 && screenData [(monsterY[i]/20)][(monsterX[i]/20) + 1] == 4 ){
    monsterX[i] +=20;
    }
    else if(screenData [(monsterY[i]/20)][(monsterX[i]/20)] == 6 && screenData [(monsterY[i]/20)][(monsterX[i]/20) + 1] == 8 ){
    monsterX[i] +=20;
    }     
    }
    /*    else if (hobbinsOn == true){
    if(digger.diggerX<monsterX[i]){
    monsterX[i] -=20;
    screenData [(monsterY[i]/20)][(monsterX[i]/20)] = 2;}
    if(digger.diggerX>monsterX[i]){
    monsterX[i] +=20;
    screenData [(monsterY[i]/20)][(monsterX[i]/20)] = 6;}
    if(digger.diggerY<monsterY[i]){
    monsterY[i] -=20;
    screenData [(monsterY[i]/20)][(monsterX[i]/20)] = 8;}
    if(digger.diggerY<monsterY[i]){
    monsterY[i] +=20;
    screenData [(monsterY[i]/20)][(monsterX[i]/20)] = 4;}
    }*/
    }
    }

 
}
