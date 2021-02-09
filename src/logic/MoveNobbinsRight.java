/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digger.logic;

/**
 *
 * @author ghait
 */
public class MoveNobbinsRight implements Runnable {

  public int counter;
    MoveNobbinsRight(int c){
    counter=c;
    
    }
    public int getCounter (){
    
    return counter;
    }
    @Override
    public void run() {
      System.out.println("hiiiii");
      counter++;  
	  
	  
    }
    
}
