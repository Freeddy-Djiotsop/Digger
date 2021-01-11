/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Digger;
import java.awt.Dimension;
import java.awt.Font;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;


public class map implements EventHandler<KeyEvent> {
    
    private Dimension d;
    private final Font smallFont = new Font("Helvetica",Font.BOLD,14);
    private boolean inGame = false;
    private final int BlOCK_SIZE = 20;
    private final int N_BLOCK = 30;
    private final int SCREEN_SIZE = N_BLOCK * BlOCK_SIZE;
    private int Diggerleft , score;
    private int Hobbinsx =500 , Hobbinsy=10;
    private int DiggerX = 480, DiggerY = 520;
    private int speed = 6;
    private final int levelData[][]= {
    {0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1},
    {0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1},
    {0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0},
    {0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0},
    {0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,1,1,0,0,0,0,0},
    {0,0,0,0,1,1,0,0,2,0,0,0,0,0,0,2,2,0,0,0,0,0,0,1,1,0,0,0,0,0},
    {0,0,0,0,1,1,0,0,2,2,0,0,0,0,0,2,2,0,0,0,0,0,0,1,1,0,2,2,0,0},
    {0,0,0,0,1,1,0,0,2,2,0,0,0,0,0,2,2,0,0,0,0,0,0,1,1,0,2,2,0,0},
    {0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,2,2,0,0},
    {0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,2,2,0,0},
    {0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0},
    {0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0},
    {0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0},
    {0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0},
    {0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0},
    {0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0},
    {0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0},
    {0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0},
    {0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0},
    {0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0},
    {0,0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0},
    {0,0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0},
    {0,0,0,0,0,0,2,2,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0},
    {0,0,0,0,0,0,2,2,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    };
    private int[] [] screendata ;
    public Canvas canvas = new Canvas(600, 600);
    public GraphicsContext gc = canvas.getGraphicsContext2D();
    Group root = new Group();
    public Scene s= new Scene(root);
    boolean  goNorth, goSouth, goEast, goWest;
    
    public map(){
        s.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case UP:{ 
                            if(DiggerY <= 0){
                            DiggerY = 0;
                            break;
                            }
                            else{
                            DiggerY -=20;
                            break;
                            }
                }
                case DOWN:{
                            if(DiggerY >= 580){
                            DiggerY = 580;
                            break;
                            }
                            else{
                            DiggerY +=20;
                            break;
                            }
                }
                case LEFT:{
                            if(DiggerX <= 0){
                            DiggerX = 0;
                            break;
                            }
                            else{
                            DiggerX -=20;
                            break;
                            }
                }
                case RIGHT:{ 
                            if(DiggerX >= 580){
                            DiggerX = 580;
                            break;
                            }
                            else{
                            DiggerX +=20;
                            break;
                            }
                }
            }
            screendata[DiggerY/20][DiggerX/20]=1;
            drawMap(gc);
        });
        initVariables();
        initLevel();
        drawMap(gc);
        timer.start();
    }
    
    private void initVariables(){
        screendata= new int[30][30];
        d = new Dimension (600 , 600);
    }
    
    private void initLevel(){
        int i,j;
        for(i=0; i<30; i++){
        for(j=0; j<30;j++)
        {
            screendata[i][j] = levelData[i][j];
        }
        }
    }
   
    private void drawMap(GraphicsContext gc){
        gc.setFill(Color.ORANGE);
        gc.fillRect(0, 0, d.height, d.width);
        int i , j;
        int x=0, y=0 ;
        for(i =0; i<30;i++ ){
            for(j=0; j<30;j++){
                if(screendata[i][j] == 1){
                    gc.setFill(Color.BLACK);
                    gc.fillRect(x,y,BlOCK_SIZE,BlOCK_SIZE);
                }
                if(screendata[i][j]==2){
                    gc.setFill(Color.GREEN);
                    gc.fillOval(x+11, y+11, 10, 10);
                }
                x +=20;
            }
            x=0;
            y +=20;
        }
        drawDigger(gc);
        drawHobbins(gc);
   }
   
    private void drawDigger(GraphicsContext gc) {
        gc.setFill(Color.BLUE);
        gc.fillRect(DiggerX, DiggerY, 20, 20);
    }
   
    private void drawHobbins(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillOval(Hobbinsx, Hobbinsy, 10, 10);
    }
    
    Timeline timeline = new Timeline(
    );
    
    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            if (goNorth) DiggerY -= 20;
            if (goSouth) DiggerY += 20;
            if (goEast)  DiggerX += 20;
            if (goWest)  DiggerX -= 20;
        }
    };

    @Override
    public void handle(KeyEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }
