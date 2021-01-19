/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DiggerGame;

import java.awt.Dimension;


import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.*;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.util.Duration;
;

/**
 *
 * @author Aldemashki
 */
public class Map {
    public Canvas canvas = new Canvas(600, 600); 
    public GraphicsContext gc = canvas.getGraphicsContext2D();
    Group root = new Group();
    Scene s = new Scene(root);
    private Dimension d; 
    public Digger digger = new Digger();
    public Nobbins nobbins = new Nobbins();
    public Score score = new Score();
    private final int blockSize = 20;             
    private final int nBlock = 30;                    
    private final int screenSize = nBlock * blockSize;
    private final int levelData[][]= {
    {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
    {3,3,3,3,8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,2,2,2,2,2,2},
    {3,3,3,3,8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,3,3,3,3,3},
    {3,3,3,3,8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,3,3,3,3,3},
    {3,3,3,3,8,3,3,3,3,3,3,3,3,3,3,5,5,3,3,3,3,3,3,3,4,3,3,3,3,3},
    {3,3,3,3,8,3,3,3,5,3,3,3,3,3,3,5,5,3,3,3,3,3,3,3,4,3,3,3,3,3},
    {3,3,3,3,8,3,3,3,5,5,3,3,3,3,3,5,5,3,3,3,3,3,3,3,4,3,5,5,3,3},
    {3,3,3,3,8,3,3,3,5,5,3,3,3,3,3,5,5,3,3,3,3,3,3,3,4,3,5,5,3,3},
    {3,3,3,3,8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,3,5,5,3,3},
    {3,3,3,3,8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,3,5,5,3,3},
    {3,3,3,3,8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,3,3,3,3,3},
    {3,3,3,3,8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,3,3,3,3,3},
    {3,3,3,3,8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,3,3,3,3,3},
    {3,3,3,3,8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,3,3,3,3,3},
    {3,3,3,3,8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,3,3,3,3,3},
    {3,3,3,3,8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,3,3,3,3,3},
    {3,3,3,3,8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,3,3,3,3,3},
    {3,3,3,3,8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,3,3,3,3,3},
    {3,3,3,3,8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,3,3,3,3,3},
    {3,3,3,3,8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,3,3,3,3,3},
    {3,3,3,3,8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,3,3,3,3,3},
    {3,3,3,3,2,2,2,2,2,2,2,8,3,3,3,3,3,3,3,3,3,3,3,3,4,3,3,3,3,3},
    {3,3,3,3,3,3,3,3,3,3,3,8,3,3,3,3,3,3,3,3,3,3,3,3,4,3,3,3,3,3},
    {3,3,3,3,3,3,3,3,3,3,3,8,3,3,3,3,3,3,3,3,3,3,3,3,4,3,3,3,3,3},
    {3,3,3,3,3,3,5,5,3,3,3,8,3,3,3,3,3,3,3,3,3,3,3,3,4,3,3,3,3,3},
    {3,3,3,3,3,3,5,5,3,3,3,8,3,3,3,3,3,3,3,3,3,3,3,3,4,3,3,3,3,3},
    {3,3,3,3,3,3,3,3,3,3,3,8,3,3,3,3,3,3,3,3,3,3,3,3,4,3,3,3,3,3},
    {3,3,3,3,3,3,3,3,3,3,3,2,2,2,2,2,2,2,2,2,2,2,2,2,4,3,3,3,3,3},
    {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
    {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
    };
     public int[][] screenData ;
     public int dyingNr = 0;
     public boolean up=false,down=false,right=false,left=false ,active =false, killed=false ,go=true,move=false;
     public String dir ="l";
     public Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(20), ev -> { // für fireball
      
         go=true;
       
         }));
     
     public Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(10), ev -> { 
      
       
       
         }));
     
        

        
       
  
   
    
    public Map(){
        
        initVariables(); 
        initLevel();    
        drawMap(gc);
        diggerMove();
        timer.start();
        
    
        
       
}
    
    private void initVariables(){
        screenData= new int[30][30]; //wir haben 30 zeile und 30 spalte in der Karte d.h. , dass jede zeile hat 30 block,der Länge 20 ist und auch bei Spalten
        d = new Dimension (600 , 600); // Größe der fenster 
         
         
        
         
    }
    
    private void initLevel(){ //hier level in Screnndata inti.
        int i,j;
        for(i=0; i<30; i++){
        for(j=0; j<30;j++)
        {
            screenData[i][j] = levelData[i][j];
        }
        }
    }
   
    public void drawMap(GraphicsContext gc){
        if(dyingNr < 3){
            
        gc.setFill(Color.ORANGE);
        gc.fillRect(0, 0, d.height, d.width);
        int i , j;
        int x=0, y=0 ;
        for(i =0; i<30;i++ ){
            for(j=0; j<30;j++){
                if(screenData[i][j] % 2 == 0){
                    gc.setFill(Color.BLACK);
                    gc.fillRect(x,y,blockSize,blockSize);
                }
                if(screenData[i][j]==5){
                    gc.setFill(Color.GREEN);
                    gc.fillOval(x+11, y+11, 10, 10);
                }
                x +=20;
            }
            x=0;
            y +=20;
        }
        
      
        digger.draw(gc);
        digger.drawFireBall(gc);
        
        
        for(int k = 0; k < 5; k++){
            
        nobbins.draw(gc , nobbins.monsterX[k] , nobbins.monsterY[k]);
            }
            
        
        score.drawScore(gc);
        }
        else
        {
       gc.setFill(Color.BLACK);
       gc.fillRect(0, 0, d.height, d.width);
                     }
    }
   
    public void diggerMove(){
        
           s.setOnKeyPressed((KeyEvent event) -> {
            
            switch (event.getCode()) {
                case UP:{   up=true;
                            down=false;
                            right=false;
                            left=false;
                            if(digger.diggerY <= 20){
                            digger.diggerY =20;
                            if(active==false)
                            digger.fireBallY=digger.diggerY;
                            
                            break;
                            }
                            else{
                            digger.diggerY -=20;
                            if(active == false)
                            digger.fireBallY=digger.diggerY;
                            if(screenData[digger.diggerY/20][digger.diggerX/20] ==5){ // hier bedeutet wenn Digger Emeraldsstelle eintrifft,dann erhöhe die Score
            
                                 score.score +=50;
                                   }                            
                            screenData[digger.diggerY/20][digger.diggerX/20]=8; // Digger lässt in seiner Stelle Schwarze Weg
                            for(int i = 0;i<30;i++){
                            for(int j = 0;j<30;j++)
                            System.out.print(screenData[i][j]);
                            System.out.println();}                            
                            break;
                            }
                }
                case DOWN:{ up=false;
                            down=true;
                            right=false;
                            left=false;
                            if(digger.diggerY >= 580){
                            digger.diggerY = 580;
                            if(active==false)
                            digger.fireBallY=digger.diggerY;
                            break;
                            }
                            else{
                            digger.diggerY +=20;
                            if(active==false)
                            digger.fireBallY=digger.diggerY;
                            if(screenData[digger.diggerY/20][digger.diggerX/20] ==5){ // hier bedeutet wenn Digger Emeraldsstelle eintrifft,dann erhöhe die Score
            
                                 score.score +=50;
                                   }
                            screenData[digger.diggerY/20][digger.diggerX/20]=4; // Digger lässt in seiner Stelle Schwarze Weg
                            for(int i = 0;i<30;i++){
                            for(int j = 0;j<30;j++)
                            System.out.print(screenData[i][j]);
                            System.out.println();}                            
                            break;
                            }
                }
                case LEFT:{ up=false;
                            down=false;
                            right=false;
                            left=true;
                            if(digger.diggerX <= 0){
                            digger.diggerX = 0;
                            if(active==false)
                            digger.fireBallX=digger.diggerX;
                            break;
                            }
                            else{
                            digger.diggerX -=20;
                            if(active==false)
                            digger.fireBallX=digger.diggerX;
                            if(screenData[digger.diggerY/20][digger.diggerX/20] ==5){ // hier bedeutet wenn Digger Emeraldsstelle eintrifft,dann erhöhe die Score
            
                                score.score +=50;
                                   }
                            screenData[digger.diggerY/20][digger.diggerX/20]=2; // Digger lässt in seiner Stelle Schwarze Weg
                            for(int i = 0;i<30;i++){
                            for(int j = 0;j<30;j++)
                            System.out.print(screenData[i][j]);
                            System.out.println();}                            
                            break;
                            }
                }
                case RIGHT:{ up=false;
                            down=false;
                            right=true;
                            left=false;
                            if(digger.diggerX >= 580){
                            digger.diggerX = 580;
                            if(active==false)
                            digger.fireBallX=digger.diggerX;
                            break;
                            }
                            else{
                            digger.diggerX +=20;
                            if(active==false)
                            digger.fireBallX=digger.diggerX;
                             if(screenData[digger.diggerY/20][digger.diggerX/20] ==5){ // hier bedeutet wenn Digger Emeraldsstelle eintrifft,dann erhöhe die Score
            
                                 score.score +=50;
                                   }
                            screenData[digger.diggerY/20][digger.diggerX/20]=6; // Digger lässt in seiner Stelle Schwarze Weg
                            for(int i = 0;i<30;i++){
                            for(int j = 0;j<30;j++)
                            System.out.print(screenData[i][j]);
                            System.out.println();}
                            break;
                            }
                }
               
            }
           
           
            
           
            drawMap(gc); // wird nochmal die karte ausdruckt nach jede Bewegung von Digger, ist untershiedlich von Swing. in swing steht die Methode repaint()
              });
             s.setOnKeyReleased((KeyEvent event) -> {
                 
           timeline.play();
           if(go==true){
       
        
            switch(event.getCode()) {
            
                case SPACE:{
                 go = false;
                active=true;
                            
                    
                    if(up==true){
                       dir="up";
                       
                      
                    }
                    if(right==true){
                    dir= "right";
                    }
                   if(down==true){
                    dir ="down";
                    }
                 if(left==true){
                   dir="left";
                    }
                
                break;
                }
            
            }
            
            
      }
           
            
            
            });
    }
     
  
    public void fireBallMove(GraphicsContext gc){
     
    switch(dir){
    
        case "up":
        { if(digger.fireBallY>20 &&screenData[(digger.fireBallY/20)][digger.fireBallX/20] %2==0 && killMonster()==false){
        digger.fireBallY -=20;
        
        }
        else{
            digger.fireBallY=digger.diggerY;
            digger.fireBallX=digger.diggerX;
            active=false;
            dir="l";
        }
        break;
        }
         case "down":
        {
           
        if(digger.fireBallY<600 &&screenData[(digger.fireBallY/20)][digger.fireBallX/20] %2==0&& killMonster()==false ){
        digger.fireBallY +=20;
        }
        else {
         digger.fireBallY=digger.diggerY;
         digger.fireBallX=digger.diggerX;
         active=false;
            dir="l";
        }
        break;
        }
         case "right":
        {
          if(digger.fireBallX<600  && screenData[(digger.fireBallY/20)][(digger.fireBallX/20)] %2==0 && killMonster()==false ){
        digger.fireBallX +=20;
          }
          else{
          digger.fireBallY=digger.diggerY;
          digger.fireBallX=digger.diggerX;
          active=false;
          dir="l";
          }
        break;
        }
         case "left":
        {
            if(digger.fireBallX>20 && screenData[(digger.fireBallY/20)][(digger.fireBallX/20)] %2==0 && killMonster()==false){
        digger.fireBallX -=20;
            }
            else {
            digger.fireBallY=digger.diggerY;
            digger.fireBallX=digger.diggerX;
            active=false;
            dir="l";
            
            }
        break;
        }
    
    }
  
  
 
        
     
     }
     
     
    AnimationTimer timer = new AnimationTimer() {  // Timer
        @Override
        public void handle(long now) {
           
            fireBallMove(gc);
            nobbins.move(screenData);
            dying();
            delay(100);
            drawMap(gc);
          
          
   
        }
    };
         
    private static void delay(int i){  // um delay (Verzögerung) zu erstellen
       
        try {
            Thread.sleep(i);
        } catch (InterruptedException ex) {
            Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
        }
      
   }
        
    private void dying(){
    
    for(int i =0; i<5;i++){
    if(Math.abs(nobbins.monsterX[i]-digger.diggerX)<20 && Math.abs(nobbins.monsterY[i]-digger.diggerY)<20)
    { dyingNr++;
   
     for(int j =0; j<5;j++)
     {
         
         nobbins.monsterX[j]=580;
         nobbins.monsterY[j]=20;

     }
     digger.diggerX=80;
     digger.fireBallX=80;
     digger.diggerY=80;
     digger.fireBallY=80;
     nobbins.k=1;
     
}
    
    

}
}
    
 private boolean killMonster(){
    
     for(int i=0 ; i<5;i++){
        
 if(Math.abs(digger.fireBallX - nobbins.monsterX[i])<20 && Math.abs(digger.fireBallY - nobbins.monsterY[i])<20)
 {
 score.score +=50;

 nobbins.monsterX[i]= 580;
 nobbins.monsterY[i] =20;
nobbins.k=1;
 
 return true;

 

 
 
 
 
 }
 
     
     }
     
 return false;
 
 }
 

 
   

}
    

