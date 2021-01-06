/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diggerLayout2;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/*import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;*/


import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Aldemashki
 */
public class Map extends JPanel implements KeyListener , ActionListener {
    
    private Dimension d;
    
    
    
    
    private final Font smallFont = new Font("Helvetica",Font.BOLD,14);
    
    private Image ii;
    
    private final Color emeColor = new Color (192,192,0);
    
    private Color mapColor;
    
    private boolean inGame = false;
    
    private boolean dying = false;
    
    private final int BlOCK_SIZE = 43;
    
    private final int N_BLOCK = 15;
    
    private final int SCREEN_SIZE = N_BLOCK * BlOCK_SIZE;
    
    private final int DiggerAnimDelay = 2;
    
    private final int DiggerAnimCount = 4;
    
    private final int MaxHobbins = 12;
    
    private final int DiggerSpeed = 6;
    
    private int Digger_Anim_Count = DiggerAnimDelay;
    
    private int DiggerAnimDir = 1;
    
    private int DiggerAnimPos = 0;
    
    private int N_Hobbins = 6;
    
    private int Diggerleft , score;
    
    private int [] dx , dy;
    private int Hobbinsx =600 , Hobbinsy=10;
    private int [] HobbinsX , HobbinsY , HobbinsDX , HobbinsDY , HobbinsSpeed;
    
    private int DiggerX = 480, DiggerY = 530, DiggerDX , DiggerDY;
    
    private int reqDX , reqDY , viewDX ,viewDY;
    
    private final int validSpeed[] = {1,2,3,4,6,8};
    
    private final int maxSpeed = 6;
    
    private int currentSpeed = 4;
    

    
    private final int levelData[][]= {
    
    
    {0,1,0,0,0,0,0,0,0,0,0,1,1,1,1},
    {0,1,0,0,0,0,0,0,0,0,0,1,0,0,0},
    {0,1,0,0,0,0,0,2,2,0,0,1,0,0,0},
    {0,1,0,0,0,0,0,2,2,0,0,1,0,0,0},
    {0,1,0,0,0,0,0,2,2,0,0,1,2,2,0},
    {0,1,0,0,0,0,0,2,2,0,0,1,2,2,0},
    {0,1,0,0,0,0,0,0,0,0,0,1,2,2,0},
    {0,1,0,0,0,0,0,0,0,0,0,1,2,2,0},
    {0,1,0,0,0,0,0,0,0,0,0,1,0,0,0},
    {0,1,0,0,0,0,0,0,0,0,0,1,0,0,0},
    {0,1,1,1,1,1,1,0,0,0,0,1,0,0,0},
    {0,0,2,2,0,0,1,0,0,0,0,1,0,0,0},
    {0,0,2,2,0,0,1,0,0,0,0,1,0,0,0},
    {0,0,0,0,0,0,1,1,1,1,1,1,0,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    };
    
    private int[] [] screendata ;
    
    private Timer timer;
    
   

    
    public Map(){
        initVariables();
        initBoard();
    }
    
    public void playMusic(String musicLocation) {
		
		try {
			
			File musicPath = new File(musicLocation);
			if(musicPath.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			} else {
				System.out.println("Cannot find the Audio File");
			}
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
    
    private void initBoard(){
        addKeyListener(this);
        setFocusTraversalKeysEnabled(false);
        //addKeyListener(new TAdapter());/
        setFocusable(true);
        setBackground(Color.orange);

    }
    
    private void initVariables(){

       screendata= new int[15][15];
        mapColor = new Color (0,0,0);
        d = new Dimension (645 , 645);
        HobbinsX = new int [MaxHobbins];
        HobbinsY = new int [MaxHobbins];
        HobbinsDX = new int [MaxHobbins];
        HobbinsDY = new int [MaxHobbins];
        HobbinsSpeed = new int [MaxHobbins];
        dx = new int[4];
        dy = new int [4];
        timer = new Timer(40,this);
        timer.start();
        

    }
    
    @Override 
    public void addNotify(){
        super.addNotify();
        initGame();
    }
    
    private void playgame(Graphics2D g2d){
    //moveDigger();//
    drawDigger(g2d);
    drawHobbins(g2d);
    moveHobbins(g2d);
    
    
    }
    
    private void initGame(){
        Diggerleft = 3;
        score = 0;
        initLevel();
        N_Hobbins = 6;
        currentSpeed = 3;
    }
    
    private void initLevel(){
        int i,j;
        for(i=0; i<15; i++){
         for(j=0; j<15;j++)
         {
         screendata[i][j] = levelData[i][j];
         }
        }
            
            
    }
    
    private void drawMap(Graphics2D g2d){
        
    drawHobbins(g2d);
    int i = 0, j=0;
    int x=0, y=0 ;
   for(i =0; i<15;i++ ){
   for(j=0; j<15;j++){
   
   
   
    
    if(screendata[i][j] == 1){
    g2d.setColor(Color.BLACK);
    g2d.fillRect(x,y,BlOCK_SIZE,BlOCK_SIZE);
    }
    if(screendata[i][j]==2){
        g2d.setColor(emeColor);
        g2d.fillOval(x+11, y+11, 20, 20);
    }
  
    x +=43;
    }
    x=0;
    y +=43;
    }
    
    }
    
    public void drawscore(Graphics g)
    {

    
    
    g.setColor(Color.white);
    g.setFont(new Font("areal", Font.PLAIN,20));
    g.drawString("Score :"+ score, 10, 15);
    
    
    }
    
    
     @Override
     public void paintComponent(Graphics g){
     super.paintComponents(g);
     doDrawing(g);
     }
     
     private void doDrawing(Graphics g){
     Graphics2D g2d = (Graphics2D) g;
     g2d.setColor(Color.orange);
     g2d.fillRect(0,0,d.width,d.height);
     drawMap(g2d);
     playgame(g2d);
     drawscore(g);
             
          
     
     g2d.drawImage(ii,5,5,this);
     Toolkit.getDefaultToolkit().sync();
     g2d.dispose();
     }

     	private void drawDigger(Graphics2D g2d) {
		
            g2d.setColor(Color.BLUE);
            g2d.fillOval(DiggerX , DiggerY , 20, 20);
		}
        
        private void drawHobbins(Graphics2D g2d) {
            
		g2d.setColor(Color.RED);
		g2d.fillOval(Hobbinsx, Hobbinsy, 20, 20);
	}
        
      private void moveHobbins(Graphics2D g2d) {
        if(screendata[Hobbinsy/43][(Hobbinsx)/43] == 1){
               
                   int random;
                   random = (int)(Math.random()*(currentSpeed + 1));
                   if(random>currentSpeed){
                       random = currentSpeed;
                       
                   }
                   
                   if(Hobbinsx>DiggerX)
                   {Hobbinsx -= random;
                   }
                   else if(Hobbinsx<DiggerX )
                   Hobbinsx += random;
                   else if(Hobbinsy>DiggerY)
                   Hobbinsy -= random;
                   else if(Hobbinsy<DiggerY)
                  {Hobbinsy += random;
                   }
                   

        

      }
      }
		
	
        
       
        
        /*       private void moveDigger() {
        
        int pos;
        int ch;
        
        if(reqDX == -DiggerDX && reqDY == DiggerDY) {
        DiggerDX = reqDX;
        DiggerDY = reqDY;
        viewDX = DiggerDX;
        viewDY = DiggerDY;
        
        }
        
        if(DiggerX % BlOCK_SIZE == 0 && DiggerY % BlOCK_SIZE == 0) {
        pos = DiggerX/BlOCK_SIZE + N_BLOCK*(int)(DiggerY/BlOCK_SIZE);
        ch = screendata[pos];
        
        if (ch == 3) {
        screendata[pos] = 0;
        score++;
        }
        if(reqDX != 0 || reqDY != 0) {
        if(!((reqDX == -1 && reqDY == 0 && ch == 0)
        ||(reqDX == 1 && reqDY == 0 && ch == 0)
        ||(reqDX == 0 && reqDY ==-1 && ch == 0)
        ||(reqDX == 0 && reqDY == 1 && ch == 0 ))) {
        DiggerX = reqDX;
        DiggerY = reqDY;
        viewDX = DiggerX;
        viewDY = DiggerY;
        }
        
        
        }
        DiggerX = DiggerX + DiggerSpeed*DiggerDX;
        DiggerY = DiggerY + DiggerSpeed*DiggerDY;
        
        }
        }
        
        */
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        timer.start();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        
        
        
             
   if(screendata[DiggerY/43][DiggerX/43]==2){
       score +=5;
    screendata[DiggerY/43][DiggerX/43]=1;
    }else
       screendata[DiggerY/43][DiggerX/43]=1;
       
        
        
    
         
        
    if (ke.getKeyCode() == KeyEvent.VK_RIGHT){
    if(DiggerX >= 629){
    DiggerX = 629;
    }
    else {
    moveRight();
    }
    }
    if (ke.getKeyCode() == KeyEvent.VK_LEFT){
    if(DiggerX <= 0){
    DiggerX = 0;
    }
    else {
    moveLeft();
    }
    }
    if (ke.getKeyCode() == KeyEvent.VK_UP){
    if(DiggerY <= 0){
    DiggerY = 0;
    }
    else {
    moveUp();
    }
    }
    if (ke.getKeyCode() == KeyEvent.VK_DOWN){
    if(DiggerY >= 600){
    DiggerY = 600;
    }
    else {
    moveDown();
    }
    }
     
    }
    public void moveRight(){
   
    inGame = true;
    DiggerX += 5;
    
    }
    public void moveLeft(){
 ;
    inGame = true;
    DiggerX -= 5;
    }
    public void moveUp(){
 
    inGame = true;
    DiggerY -= 5;
  
    }
    public void moveDown(){

    inGame = true;
    DiggerY += 5;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyReleased(KeyEvent ke) {
       
    }
        
}