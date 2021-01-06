package mama;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Client2Player extends JPanel implements KeyListener
{

	private static final long serialVersionUID = 1L;
	
	private JFrame frame;
	private JLabel label;
	
	private Socket server;
	private ReadFromServer rfs;
	private WriteToServer wts;
	
	private Ellipse2D me;
	private int meX, meY;
	
	private Ellipse2D myFriend;
	private int myX, myY;
	
	private int ID;
	
	
	public Client2Player() 
	{
		
		addKeyListener(this);//Important
		setFocusable(true);// to move with key
		setFocusTraversalKeysEnabled(false);
		
		ID = 0;
		connectToServer();
		
		meX = 100;
		meY = 200;
		myX = 200;
		myY = 200;
		frame = new JFrame();
		label = new JLabel();
		
		if(ID==1) frame.setTitle("Player 1");
		else if(ID==2) frame.setTitle("Player 2");
		else 
		{
			if(ID==0) JOptionPane.showMessageDialog(null, "Launch Server first", "Error Message", JOptionPane.ERROR_MESSAGE);
			else JOptionPane.showMessageDialog(null, "ERROR\nID="+ID, "Error Message", JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}
		
		this.setBackground(Color.BLACK);
		label.setPreferredSize(new Dimension(100,300));
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.add(label, BorderLayout.EAST);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setSize(new Dimension(500,300));
		frame.setResizable(false);
		
		rfs.waiting();
	}
	
	
	
	private void connectToServer()
	{
		try
		{
			server = new Socket("localhost", 1235);/*connect to server*/
			
			/*Preparing communication with Server*/
			DataInputStream fromServer = new DataInputStream(server.getInputStream());
			DataOutputStream toServer = new DataOutputStream(server.getOutputStream());
			
			ID = fromServer.read();//Recieve ID
			if(ID==1) JOptionPane.showMessageDialog(null, "Waiting for Friend to connect...", "Start Message", JOptionPane.INFORMATION_MESSAGE);
			
			rfs = new ReadFromServer(fromServer);
			wts = new WriteToServer(toServer);
			
			//rfs.waiting();//Everything begin here
		} 
		catch (UnknownHostException e) { JOptionPane.showMessageDialog(null, "Unknow host", "Error Message", JOptionPane.ERROR_MESSAGE);}
		catch (IOException e) { JOptionPane.showMessageDialog(null, "Connection failled", "Error Message", JOptionPane.ERROR_MESSAGE);}
	}
	
	
	
	private class ReadFromServer implements Runnable
	{
		private DataInputStream din;
		
		public ReadFromServer(DataInputStream d) {	din = d;	}

		@Override public void run()
		{
			try 
			{
				while(true)
				{
					meX = din.readInt();
					meY = din.readInt();
					
					//myX = din.readInt();
					//myY = din.readInt();
					
					repaint();
				}
			}
			catch (IOException e) { JOptionPane.showMessageDialog(null, "couldn't read", "Error Message", JOptionPane.ERROR_MESSAGE);}
			
			//JOptionPane.showMessageDialog(null, "run de read", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
		
		public void waiting()
		{
			try {
				String s = din.readUTF();
				JOptionPane.showMessageDialog(null, s, "Start Message", JOptionPane.INFORMATION_MESSAGE);
				
				//To communicate
				Thread readThread = new Thread(rfs);
				Thread writeThread = new Thread(wts);
				writeThread.start();
				readThread.start();
				
			} 
			catch (IOException e) { JOptionPane.showMessageDialog(null, "couldn't be ready", "Error Message", JOptionPane.ERROR_MESSAGE);}
		}
	}
	
	private class WriteToServer implements Runnable
	{
		private DataOutputStream dout;
		
		public WriteToServer(DataOutputStream d) {	dout = d;	}
		
		@Override public void run()
		{
			while(true)
			{
				try
				{
					dout.writeInt(meX);
					dout.writeInt(meY);
					dout.flush();
					
					try {//On juste que ca ne soit pas trop trop rapide 
						Thread.sleep(25);
					} catch (InterruptedException e) {}
				} 
				catch (IOException e) { JOptionPane.showMessageDialog(null, "couldn't write", "Error Message", JOptionPane.ERROR_MESSAGE);}
				//JOptionPane.showMessageDialog(null, "run de write", "Error Message", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		
	}
	
	
	
	
	/*we paint the Graphics*/
	public void paintComponent(Graphics g)//Will be automatic called
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		if(ID==1)
		{
			me = new Ellipse2D.Double(meX,meY,50,50);
			myFriend = new Ellipse2D.Double(myX, myY, 50, 50);
			
			g2.setPaint(Color.BLUE);
			g2.fill(me);
			g2.setPaint(Color.RED);
			g2.fill(myFriend);
		}
		else
		{
			myFriend = new Ellipse2D.Double(meX,meY,50,50);
			me = new Ellipse2D.Double(myX, myY, 50, 50);
			
			g2.setPaint(Color.BLUE);
			g2.fill(me);
			g2.setPaint(Color.RED);
			g2.fill(myFriend);;
		}
	}

	//We move the graphics
	@Override public void keyTyped(KeyEvent e) { }

	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		switch(ID)
		{
		case 1:
			switch(key)
			{
			case KeyEvent.VK_UP:
				if(meY-5<0)//Block the going over
					meY += 5;
				if(meY>=0 || meY<=this.getHeight()-50)//Moving up
					meY -= 5;
				break;
				
			case KeyEvent.VK_DOWN:
				if(meY+5>this.getHeight()-50)//help to not exceed the window
					meY -= 5;
				if(meY>=0 || meY<=this.getHeight()-50)
					meY += 5;
				break;
				
			case KeyEvent.VK_LEFT:
				if(meX-5<0)
					meX += 5;
				if(meX>=0 || meX<=this.getWidth()-50)
					meX -= 5;
				break;
				
			case KeyEvent.VK_RIGHT:
				if(meX+5>this.getWidth()-50)
					meX -= 5;
				if(meX>=0 || meX<=this.getWidth()-50)
					meX += 5;
				break;
			}
			label.setText("1");
			break;
		case 2:
			switch(key)
			{
			case KeyEvent.VK_UP:
				if(myY-5<0)//Block the going over
					myY += 5;
				if(myY>=0 || myY<=this.getHeight()-50)//Moving up
					myY -= 5;
				break;
				
			case KeyEvent.VK_DOWN:
				if(myY+5>this.getHeight()-50)//help to not exceed the window
					myY -= 5;
				if(myY>=0 || myY<=this.getHeight()-50)
					myY += 5;
				break;
				
			case KeyEvent.VK_LEFT:
				if(myX-5<0)
					myX += 5;
				if(myX>=0 || myX<=this.getWidth()-50)
					myX -= 5;
				break;
				
			case KeyEvent.VK_RIGHT:
				if(myX+5>this.getWidth()-50)
					myX -= 5;
				if(myX>=0 || myX<=this.getWidth()-50)
					myX += 5;
				break;
			}
			label.setText("2");
			break;
		}
	}

	@Override public void keyReleased(KeyEvent e) { }

	public static void main(String[] args) 
	{
		new Client2Player();
		
	}

}
