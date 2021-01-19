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
    public int fireBallX=diggerX , fireBallY=diggerY ;
   

    
    
 
    public void draw(GraphicsContext gc){
        gc.setFill(Color.BLUE);
        gc.fillRect(diggerX, diggerY, 20, 20);
        
        
    }
    
    public void drawFireBall(GraphicsContext gc )
    {
     
        gc.setFill(Color.BLUE);
        gc.fillOval(fireBallX,fireBallY , 15, 15);
    
    
    }
    /*public boolean fireBallMove(GraphicsContext gc , String dir, int [][] screenData){
     
    switch(dir){
    
        case "up":
        { if(fireBallY>20 &&screenData[(fireBallY/20)-1][fireBallX/20] %2==0){
        fireBallY -=20;
        
        }
        else{
           fireBallY=diggerY;
           fireBallX=diggerX;
            active=false;
            dir="l";
        }
        break;
        }
         case "down":
        {if(fireBallY<600 &&screenData[(fireBallY/20)+1][fireBallX/20] %2==0){
        fireBallY +=20;
        }
        else {
         fireBallY=diggerY;
         fireBallX=diggerX;
         active=false;
            dir="l";
        }
        break;
        }
         case "right":
        {
          if(fireBallX<600  && screenData[(fireBallY/20)][(fireBallX/20)+1] %2==0){
        fireBallX +=20;
          }
          else{
          fireBallY=diggerY;
          fireBallX=diggerX;
          active=false;
          dir="l";
          }
        break;
        }
         case "left":
        {
            if(fireBallX>20 && screenData[(fireBallY/20)][(fireBallX/20)-1] %2==0){
        fireBallX -=20;
            }
            else {
            fireBallY=diggerY;
            fireBallX=diggerX;
            active=false;
            dir="l";
            
            }
        break;
        }
    
    }
    
     return active;
     }*/

    
 
 
 


    
}
