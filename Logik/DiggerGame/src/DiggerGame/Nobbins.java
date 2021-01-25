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
   
   
    public int k=1;
    public Random random= new Random ();
    public int rand =6;
    
    public Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(10), ev -> { 
      if(k<5)
         k++;
       
         }));
     public boolean hobbinsOn = false;
    public Timeline timeline3 = new Timeline(new KeyFrame(Duration.seconds(4), ev -> { 
      hobbinsOn=true;
      rand=random.nextInt(k);
       
         }));
    
     public Timeline timeline4 = new Timeline(new KeyFrame(Duration.seconds(5), ev -> { 
         timeline3.stop();
         hobbinsOn=false;
         rand=6;
       
         }));
    
    
   
    
    
    


    
    public void draw(GraphicsContext gc , int x , int y){
       
        gc.setFill(Color.RED);
        gc.fillRect(x , y, 20, 20);
    }
        public void draw2(GraphicsContext gc , int x , int y){
       
        gc.setFill(Color.BROWN);
        gc.fillOval(x , y, 20, 20);
    }
      
   
        
      
    
    public boolean chase = false , matrix= true;
    public int z=0;
    
  
    
     public void move (int [][]screenData , Digger digger){
    timeline2.play();
    
    timeline3.play();
    
    
    timeline4.play();
    
    for(int i=0;i<k;i++)
    {
    if(i!=rand  )
    
    {
    
    if(screenData[monsterY[i]/20][monsterX[i]/20] % 2 == 0 &&   matrix==false && chase == true){
        /*  System.out.println("chase");*/
    chase= false;
    if(digger.diggerX<monsterX[i] && screenData[monsterY[i]/20][Math.abs((monsterX[i]/20)-1)] % 2 == 0){
        /*System.out.println("chase1");*/
    monsterX[i] -=20;
    chase = true;
    }
    if(digger.diggerX>monsterX[i] && screenData[monsterY[i]/20][Math.abs((monsterX[i]/20)+1)] % 2 == 0){
        /*System.out.println("chase2");*/
    monsterX[i] +=20;
    chase =true;
    }
    if(digger.diggerY>monsterY[i] && screenData[(monsterY[i]/20)+1][Math.abs((monsterX[i]/20))] % 2 == 0){
        /* System.out.println("chase3");*/
    monsterY[i] +=20;
    chase =true;
    }
    if(digger.diggerY<monsterY[i] && screenData[Math.abs((monsterY[i]/20)-1)][Math.abs((monsterX[i]/20))] % 2 == 0){
        /*System.out.println("chase4");*/
    monsterY[i] -=20;
    chase = true;
    }
    if(chase == false && matrix == false){
    matrix = true;
    }
    }
    
    
    else{
    
    
    if(matrix==true && chase == false )
    {/* System.out.println("matrix");*/
    matrix = false;
    if(screenData[monsterY[i]/20][(Math.abs((monsterX[i]/20)-1))] == 2){
        /*System.out.println("matrix1");*/
    monsterX[i] -=20;
    matrix = true;
    }
    if(screenData[monsterY[i]/20][(Math.abs((monsterX[i]/20)+1))] == 6){
        /* System.out.println("matrix2");*/
    monsterX[i] +=20;
    matrix = true;
    }
    if(screenData[Math.abs((monsterY[i]/20)-1)][(monsterX[i]/20)] == 8){
        /*System.out.println("matrix3");*/
    monsterY[i] -=20;
    matrix = true;
    }
    if(screenData[(monsterY[i]/20)+1][(monsterX[i]/20)] == 4){
        /*  System.out.println("matrix4");*/
    monsterY[i] +=20;
    matrix = true;
    }
    if(screenData [(monsterY[i]/20)][(monsterX[i]/20)] == 4 && screenData [(monsterY[i]/20) + 1][(monsterX[i]/20)] == 2 ){
    monsterY[i] +=20;
    }
    if(screenData [(monsterY[i]/20)][(monsterX[i]/20)] == 4 && screenData [(monsterY[i]/20) + 1][(monsterX[i]/20)] == 6 ){
    monsterY[i] +=20;
    }
    if(screenData [(monsterY[i]/20)][(monsterX[i]/20)] == 4 && screenData [(monsterY[i]/20) + 1][(monsterX[i]/20)] == 8 ){
    monsterY[i] +=20;
    }
    if(screenData [(monsterY[i]/20)][(monsterX[i]/20)] == 8 && screenData [(monsterY[i]/20) - 1][(monsterX[i]/20)] == 2 ){
    monsterY[i] -=20;
    }
    if(screenData [(monsterY[i]/20)][(monsterX[i]/20)] == 8 && screenData [(monsterY[i]/20) - 1][(monsterX[i]/20)] == 6 ){
    monsterY[i] -=20;
    }
    if(screenData [(monsterY[i]/20)][(monsterX[i]/20)] == 8 && screenData [(monsterY[i]/20) - 1][(monsterX[i]/20)] == 4 ){
    monsterY[i] -=20;
    }
    if(screenData [(monsterY[i]/20)][(monsterX[i]/20)] == 2 && screenData [(monsterY[i]/20)][(monsterX[i]/20) - 1] == 8 ){
    monsterX[i] -=20;
    }
    if(screenData [(monsterY[i]/20)][(monsterX[i]/20)] == 2 && screenData [(monsterY[i]/20)][(monsterX[i]/20) - 1] == 6 ){
    monsterX[i] -=20;
    }
    if(screenData [(monsterY[i]/20)][(monsterX[i]/20)] == 2 && screenData [(monsterY[i]/20)][(monsterX[i]/20) - 1] == 4 ){
    monsterX[i] -=20;
    }
    if(screenData [(monsterY[i]/20)][(monsterX[i]/20)] == 6 && screenData [(monsterY[i]/20)][(monsterX[i]/20) + 1] == 2 ){
    monsterX[i] +=20;
    }
    if(screenData [(monsterY[i]/20)][(monsterX[i]/20)] == 6 && screenData [(monsterY[i]/20)][(monsterX[i]/20) + 1] == 4 ){
    monsterX[i] +=20;
    }
    if(screenData [(monsterY[i]/20)][(monsterX[i]/20)] == 6 && screenData [(monsterY[i]/20)][(monsterX[i]/20) + 1] == 8 ){
    monsterX[i] +=20;
    }
    
    
    }else{
    chase = true;
    
    
    }
    
    }
    
    
    
    }else
    {
    
    if(digger.diggerX<monsterX[rand] ){
    screenData[monsterY[rand]/20][(monsterX[rand]/20)]  = 2;
    monsterX[rand] -=20;
    
    }
    else if(digger.diggerX>monsterX[rand] ){
    screenData[monsterY[rand]/20][(monsterX[rand]/20)]  = 6;
    monsterX[rand] +=20;
    
    }
    else if(digger.diggerY>monsterY[rand] ){
    screenData[monsterY[rand]/20][(monsterX[rand]/20)]  = 8;
    monsterY[rand] +=20;
    
    }
    else if(digger.diggerY<monsterY[rand] ){
    screenData[monsterY[rand]/20][(monsterX[rand]/20)]  = 4;
    monsterY[rand] -=20;
    
    }
    
    
    }
    
    }
    
    }
  
  
  
  }

 

