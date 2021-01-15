/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Digger;



import java.awt.Dimension;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.*;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.util.Duration;
import javafx.scene.text.Text;


public class map implements EventHandler<KeyEvent> {        //EventHandler<KeyEvent> wie KeyListenr in swing für Tastatur Eingabe
    
    private Dimension d;                                   // Für die Background color in unserem Fall orange gehe zur  Zeile 200 (d.height, d.width )                            
    
    private boolean inGame = false;
    private final int BlOCK_SIZE = 20;                   // wir haben die App. Fenster in BLocken geteilt hier entspricht das die Länge der Block in x,y Achse 
    private final int N_BLOCK = 30;                      // Anzahl die Blocken
    private final int SCREEN_SIZE = N_BLOCK * BlOCK_SIZE;// Fenster Größe entspricht Block_size mal Die Anzahl der Blocken
    private int  score;                  
  
    private int Hobbinsxx[]={500,500,500};               // Die Hobbins Position auf x Achse und hier als Array,da wir haben mehrer Hobbins
    private int Hobbinsyy[] ={10,10,10};                 // Die Hobbins Position auf y Achse und hier als Array,da wir haben mehrer Hobbins
    private int DiggerX = 100, DiggerY = 100;            // Digger positon auf x und y Achse Am Anfang
    private int currentSpeed = 4;                        // Geschwindigkeit der Hobbins
private final int levelData[][]= {
    {3,3,3,3,8,8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,2,2,2,2,2,2},
    {3,3,3,3,8,8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,2,2,2,2,2,2},
    {3,3,3,3,8,8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,4,3,3,3,3,3},
    {3,3,3,3,8,8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,4,3,3,3,3,3},
    {3,3,3,3,8,8,3,3,3,3,3,3,3,3,3,5,5,3,3,3,3,3,3,4,4,3,3,3,3,3},
    {3,3,3,3,8,8,3,3,5,3,3,3,3,3,3,5,5,3,3,3,3,3,3,4,4,3,3,3,3,3},
    {3,3,3,3,8,8,3,3,5,5,3,3,3,3,3,5,5,3,3,3,3,3,3,4,4,3,5,5,3,3},
    {3,3,3,3,8,8,3,3,5,5,3,3,3,3,3,5,5,3,3,3,3,3,3,4,4,3,5,5,3,3},
    {3,3,3,3,8,8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,4,3,5,5,3,3},
    {3,3,3,3,8,8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,4,3,5,5,3,3},
    {3,3,3,3,8,8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,4,3,3,3,3,3},
    {3,3,3,3,8,8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,4,3,3,3,3,3},
    {3,3,3,3,8,8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,4,3,3,3,3,3},
    {3,3,3,3,8,8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,4,3,3,3,3,3},
    {3,3,3,3,8,8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,4,3,3,3,3,3},
    {3,3,3,3,8,8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,4,3,3,3,3,3},
    {3,3,3,3,8,8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,4,3,3,3,3,3},
    {3,3,3,3,8,8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,4,3,3,3,3,3},
    {3,3,3,3,8,8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,4,3,3,3,3,3},
    {3,3,3,3,8,8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,4,3,3,3,3,3},
    {3,3,3,3,8,2,2,2,2,2,2,2,8,3,3,3,3,3,3,3,3,3,3,4,4,3,3,3,3,3},
    {3,3,3,3,2,2,2,2,2,2,2,8,8,3,3,3,3,3,3,3,3,3,3,4,4,3,3,3,3,3},
    {3,3,3,3,3,3,3,3,3,3,3,8,8,3,3,3,3,3,3,3,3,3,3,4,4,3,3,3,3,3},
    {3,3,3,3,3,3,3,3,3,3,3,8,8,3,3,3,3,3,3,3,3,3,3,4,4,3,3,3,3,3},
    {3,3,3,3,3,3,5,5,3,3,3,8,8,3,3,3,3,3,3,3,3,3,3,4,4,3,3,3,3,3},
    {3,3,3,3,3,3,5,5,3,3,3,8,8,3,3,3,3,3,3,3,3,3,3,4,4,3,3,3,3,3},
    {3,3,3,3,3,3,3,3,3,3,3,8,8,2,2,2,2,2,2,2,2,2,2,4,4,3,3,3,3,3},
    {3,3,3,3,3,3,3,3,3,3,3,8,2,2,2,2,2,2,2,2,2,2,2,2,4,3,3,3,3,3},
    {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
    {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
    };
    private int[] [] screendata ;
    // wenn wir mehrere Karten bzw. Level erstellen möchten , dann brauchen wir diese Matrix  aber zurzeit ist screendata gleich LevelData
    
    public Canvas canvas = new Canvas(600, 600); 
    public GraphicsContext gc = canvas.getGraphicsContext2D();
    Group root = new Group();
    public Scene s= new Scene(root);
    private int dyingnr=0; // versuch Zählen
    
    
   
    
    AnimationTimer timer = new AnimationTimer() {  // Timer
        @Override
        public void handle(long now) {
       
          
           /* if(Hobbinsx>DiggerX)
            Hobbinsx -= 20;
            else if(Hobbinsx<DiggerX)
            Hobbinsx += 20;
            else if(Hobbinsy>DiggerY)
            Hobbinsy -= 20;
            else if(Hobbinsy<DiggerY)
            Hobbinsy += 20;
            drawMap(gc);*/
            moveHobbins(gc);
           
            
                   }
    };
    
    private int x=0;  //Zähler, wird diese In timeline genutzt also im Respawn()
    private Text text= new Text();
    private Timeline timeline;
   
    
    
    public map(){   // konstruktur
    
        initVariables();  // intialsieren von Vari.
        initLevel();      // intialisieren von Level , diese ist wichtig wenn wir mehrere Karten erstellen wollen
        drawMap(gc);      // wie paint in javaSwing , um die karte vorzustellen 
//        respawn();        // zeit verschiebung  zwischen Hobbins respawn 
         
        //s steht für Scene und diese Funktion ist für Tastatur eingabe zuständig
        s.setOnKeyPressed((KeyEvent event) -> {
            
            switch (event.getCode()) {
                case UP:{ 
                            if(DiggerY <= 0){
                            DiggerY = 0;
                            
                            break;
                            }
                            else{
                            DiggerY -=10;
                            screendata[DiggerY/20][DiggerX/20]=8; // Digger lässt in seiner Stelle Schwarze Weg
                            break;
                            }
                }
                case DOWN:{
                            if(DiggerY >= 580){
                            DiggerY = 580;
                            break;
                            }
                            else{
                            DiggerY +=10;
                            if(screendata[DiggerY/20][DiggerX/20] ==5){ // hier bedeutet wenn Digger Emeraldsstelle eintrifft,dann erhöhe die Score
            
                                             score +=50;
                                   }
                            screendata[DiggerY/20][DiggerX/20]=4; // Digger lässt in seiner Stelle Schwarze Weg
                            break;
                            }
                }
                case LEFT:{
                            if(DiggerX <= 0){
                            DiggerX = 0;
                            break;
                            }
                            else{
                            DiggerX -=10;
                            if(screendata[DiggerY/20][DiggerX/20] ==5){ // hier bedeutet wenn Digger Emeraldsstelle eintrifft,dann erhöhe die Score
            
                                             score +=50;
                                   }
                            screendata[DiggerY/20][DiggerX/20]=2; // Digger lässt in seiner Stelle Schwarze Weg
                            break;
                            }
                }
                case RIGHT:{ 
                            if(DiggerX >= 580){
                            DiggerX = 580;
                            break;
                            }
                            else{
                            DiggerX +=10;
                             if(screendata[DiggerY/20][DiggerX/20] ==5){ // hier bedeutet wenn Digger Emeraldsstelle eintrifft,dann erhöhe die Score
            
                                             score +=50;
                                   }
                            screendata[DiggerY/20][DiggerX/20]=6; // Digger lässt in seiner Stelle Schwarze Weg
                            break;
                            }
                }
            }
           
            
            
            
            drawMap(gc); // wird nochmal die karte ausdruckt nach jede Bewegung von Digger, ist untershiedlich von Swing. in swing steht die Methode repaint()
        });
      
       
        timer.start();
       
       
    }
    
    
    
    private void initVariables(){
        screendata= new int[30][30]; //wir haben 30 zeile und 30 spalte in der Karte d.h. , dass jede zeile hat 30 block,der Länge 20 ist und auch bei Spalten
        d = new Dimension (600 , 600); // Größe der fenster 
         
         
        
         
    }
    
    private void initLevel(){ //hier level in Screnndata inti.
        int i,j;
        for(i=0; i<30; i++){
        for(j=0; j<30;j++)
        {
            screendata[i][j] = levelData[i][j];
        }
        }
    }
   
    private void drawMap(GraphicsContext gc){
        if(dyingnr < 3) { //  die Versuche, wenn der spieler 3 Versuche verloren hat , dann gehe zu else in zeile 245 und druck schwarze Fenster(Gameover)
          gc.setFill(Color.ORANGE);
        gc.fillRect(0, 0, d.height, d.width);
        int i , j;
        int x=0, y=0 ;
        for(i =0; i<30;i++ ){
            for(j=0; j<30;j++){
                if(screendata[i][j] % 2 == 0){
                    gc.setFill(Color.BLACK);
                    gc.fillRect(x,y,BlOCK_SIZE,BlOCK_SIZE);
                }
                if(screendata[i][j]==5){
                    gc.setFill(Color.GREEN);
                    gc.fillOval(x+11, y+11, 10, 10);
                }
                x +=20;
            }
            x=0;
            y +=20;
        }
        
        
        drawDigger(gc); 
  
    
        drawScore(gc);
//        respawn();
        for(int k =0; k<3;k++)
        {
        
           drawHobbins(gc,Hobbinsxx[k],Hobbinsyy[k]);

        

        
        
   }
        }else // wenn der Spieler 3 versuche verloren hat.
        {
             gc.setFill(Color.BLACK);
             gc.fillRect(0, 0, d.height, d.width);
                     }
    }
    
    
   private static void delay(int i){  // um delay (Verzögerung) zu erstellen
       
        try {
            Thread.sleep(i);
        } catch (InterruptedException ex) {
            Logger.getLogger(map.class.getName()).log(Level.SEVERE, null, ex);
        }
      
   }
    private void drawDigger(GraphicsContext gc) { //digger ausdrucken
       
        gc.setFill(Color.BLUE);
        
        gc.fillRect(DiggerX, DiggerY, 20, 20);
    }
   
    private void drawHobbins(GraphicsContext gc,int x , int y) { // Hobbins ausdrucken
        
        gc.setFill(Color.RED);
        
        gc.fillRect(x, y, 20, 20);
    }
    
    
    
   /* Random rand = new Random();
    private int xrand= rand.nextInt(4);
    */
    
    
      /*private void moveHobbins(GraphicsContext gc) {
      /*
      xrand=rand.nextInt(4);
      
      switch (xrand){
      case 0:
      { //go down
      while(screendata[(Hobbinsyy[0]/20)+1][Hobbinsxx[0]/20]== 1){
      {
      
      
      Hobbinsyy[0] += 20;
      drawHobbins(gc,Hobbinsxx[0],Hobbinsyy[0]);
      drawMap(gc);
      
      
      
      
      }
      
      }
      break;
      }
      case 1:
      { //gotop
      while(screendata[(Hobbinsyy[0]/20)-1][Hobbinsxx[0]/20]== 1){
      
      
      Hobbinsyy[0] -= 20;
      drawHobbins(gc,Hobbinsxx[0],Hobbinsyy[0]);
      drawMap(gc);
      
      
      }
      break;
      }
      case 2:
      {  //goright
      while(screendata[(Hobbinsyy[0]/20)][(Hobbinsxx[0]/20)+1]== 1){
      Hobbinsxx[0] += 20;
      drawHobbins(gc,Hobbinsxx[0],Hobbinsyy[0]);
      drawMap(gc);
      
      }
      break;
      }
      case 3:
      { //goleft
      while(screendata[(Hobbinsyy[0]/20)][(Hobbinsxx[0]/20)-1]== 1){
      Hobbinsxx[0] -= 20;
      drawHobbins(gc,Hobbinsxx[0],Hobbinsyy[0]);
      drawMap(gc);
      }
      break;
      }
      
      }
      
  }*/
    private boolean inverse = false;
    
  private void moveHobbins(GraphicsContext gc) {
      
     int i =0;
      do{
      
      dying(); // Überprüfen ob das Digger Tot ist
      
      for(i=0; i < 1; i++){
            if(Hobbinsxx[i]<600 &&Hobbinsyy[i]<600&& Hobbinsyy[i]>0 && Hobbinsxx[i]>0&& inverse==false){
                if(screendata [(Hobbinsyy[i]/20)][(Hobbinsxx[i]/20)] % 2 == 0){
            if(screendata [Hobbinsyy[i]/20][Hobbinsxx[i]/20] == 2){
                Hobbinsxx[i] -=5;
                System.out.println("Hallo I am  left"  );
                drawMap(gc);
           }
             if(screendata [Hobbinsyy[i]/20][Hobbinsxx[i]/20] == 4 ){
                Hobbinsyy[i] +=5;
                System.out.println("Hallo I am right"  );
                drawMap(gc);
            }
              if(screendata [Hobbinsyy[i]/20][Hobbinsxx[i]/20] == 8 ){
                Hobbinsyy[i] -=5;
                 System.out.println("Hallo I am top"  );
                drawMap(gc);
            } 
              if(screendata [Hobbinsyy[i]/20][Hobbinsxx[i]/20] == 6 ){
                Hobbinsxx[i] +=5;
                 System.out.println("Hallo I am down"  );
                drawMap(gc);
            }
             
                    
                }   
            }else{
                inverse=true;
                moveHobbinsInvers(0);
            }
        }
      
      } while(i<x); // x für anzahl der Hobbins 
      
  }
 private void moveHobbinsInvers(int i){
     
 
      do{
     
      dying(); // Überprüfen ob das Digger Tot ist
      System.out.print(Hobbinsxx[0]  );
      System.out.println(Hobbinsyy[0]);
       if(Hobbinsxx[0]<580 &&Hobbinsyy[0]<600&& Hobbinsyy[i]>=0 && Hobbinsxx[0]>0&& inverse==true){
           
                if(screendata [(Hobbinsyy[i]/20)][(Hobbinsxx[i]/20)] % 2 == 0){
            if(screendata [Hobbinsyy[i]/20][Hobbinsxx[i]/20] == 2 && screendata [(Hobbinsyy[i]/20)][(Hobbinsxx[i]/20)+1] % 2 == 0 ){
                Hobbinsxx[i] +=5;
                System.out.print("Hallo I am inverse right"  );
                drawMap(gc);
           }
             if(screendata [Hobbinsyy[i]/20][Hobbinsxx[i]/20] == 4 && screendata [(Hobbinsyy[i]/20)-1][(Hobbinsxx[i]/20)] % 2 == 0){
                Hobbinsyy[i] -=5;
                System.out.print("Hallo I am inverse left"  );
                drawMap(gc);
            }
              if(screendata [(Hobbinsyy[i]/20)][Hobbinsxx[i]/20] == 8 && screendata [(Hobbinsyy[i]/20)+1][(Hobbinsxx[i]/20)] % 2 == 0){
                Hobbinsyy[i] +=5;
                 System.out.print("Hallo I am inverse down"  );
                drawMap(gc);
            } 
               if(screendata [(Hobbinsyy[i]/20)][Hobbinsxx[i]/20] == 8 && screendata [(Hobbinsyy[i]/20)+1][(Hobbinsxx[i]/20) ] % 2 != 0){
                Hobbinsxx[i] +=5;
                System.out.print("Hallo I am inverse down2"  );
                 
                drawMap(gc);
            } 
              
              if(screendata [Hobbinsyy[i]/20][Hobbinsxx[i]/20] == 6){
                Hobbinsxx[i] -=5;
                System.out.print("Hallo I am inverse top"  );
                drawMap(gc);
            }
            }
            
       }
       else
           inverse =false;
      
      } while(i<x); // x für anzahl der Hobbins 
      
  }
      
private void dying(){
    
    for(int i =0; i<Hobbinsxx.length;i++){
if(Math.abs(Hobbinsxx[i]-DiggerX)<20 && Math.abs(Hobbinsyy[i]-DiggerY)<20)
{ dyingnr++;
   
     for(int j =0; j<Hobbinsxx.length;j++)
     {
         
         Hobbinsxx[j]=500;
         Hobbinsyy[j]=10;
//         respawn();
     }
     DiggerX=100;
     DiggerY=100;
     break;
}
    

}
}

/*private void respawn(){
 //respawn every 3 sec.
 x=0;
        
         timeline = new Timeline(new KeyFrame(Duration.seconds(2), ev -> {
        drawHobbins(gc,Hobbinsxx[x],Hobbinsyy[x]);
        x++;
        
         }));
        timeline.setCycleCount(3);//do it x times
        timeline.setCycleCount(Animation.INDEFINITE);//or indefinitely

        //play:
        timeline.play();
        
///////////////////////////////////////////

}
*/
public void drawScore(GraphicsContext gc)
    {
   
    gc.strokeText("Score : " +score, 2, 10);
    
   }
  
    

    @Override
    public void handle(KeyEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }
