package mama;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Server2Player 
{

	private final int maxClient = 2;
	private int currentClient = 0;/*aktuelle Anzahl an Client*/
	
	private ServerSocket serverSocket;	
	
	@SuppressWarnings("unused")
	private Socket me;
	private ReadFromClient meRfc;
	private WriteToClient meWtc;
	
	@SuppressWarnings("unused")
	private Socket myfriend;
	private ReadFromClient myRfc;
	private WriteToClient myWtc;
	
	private int meX, meY, myX, myY;//for moving each ball
	
	//private String meMsg, myMsg;

	public Server2Player()
	{
		//meMsg = "1: inti";
		//myMsg = "2: init";
		meX = 100;
		meY = 200;
		myX = 200;
		myY = 200;
		
		try
		{
			serverSocket = new ServerSocket(1235);
		}
		catch (IOException e) { System.out.println("Server couldn't launch");}
		
		acceptConnexion();
	}
	
	private void acceptConnexion()
	{
		
		try
		{
			while(currentClient < maxClient )
			{
				System.out.println("Waiting...");
				Socket client = serverSocket.accept();
				
				DataInputStream fromClient = new DataInputStream(client.getInputStream());
				DataOutputStream toClient = new DataOutputStream(client.getOutputStream());
				
				currentClient++;
				
				toClient.write(currentClient);//We send the ID
				
				//On initialise le din et le dout pour l'actuel socket.
				ReadFromClient rfc = new ReadFromClient(currentClient, fromClient);
				WriteToClient wtc = new WriteToClient(currentClient, toClient);
				
				//On se rassure de le sauvegarder exactement ou on le veut
				if(currentClient==1)
				{
					me = client;
					meRfc = rfc;
					meWtc = wtc;
					
					//La suite concernant ce socket sera fait qnd le 2eme socket sera initialisé, ainsi on demarre tt qnd tout est fait
				}
				else if(currentClient==2)
				{
					myfriend = client;
					myRfc = rfc;
					myWtc = wtc;

					
					//We inform the client, that everything is ready to start
					meWtc.ready();
					myWtc.ready();
					
					
					//La suite et la fin pour le socket 1 et ainsi que pour celui de 2
					//Les Thread nous permet d'échanger les informations rentre les Clients
					Thread rThread1 = new Thread(meRfc);
					Thread wThread1 = new Thread(meWtc);
					
					Thread rThread2 = new Thread(myRfc);
					Thread wThread2 = new Thread(myWtc);
					
					//On lance donc chaque Thread en appelant run
					rThread1.start();
					rThread2.start();
					wThread1.start();
					wThread2.start();
				}
				else
				{
					System.exit(-1);
				}
			}
		}
		catch (IOException e) { System.out.println("Client "+currentClient + "couldn't be connected");}
	}
	
	private class ReadFromClient implements Runnable
	{
		private int ID;
		private DataInputStream din;
		public ReadFromClient(int i, DataInputStream j)
		{
			ID = i;
			din = j;
		}
		
		@Override public void run() 
		{
			//Server knows where Data are coming from
				try 
				{
					
					while(true)//So server can receive data at any time 
					{
						if(ID==1)
						{
							meX = din.readInt();
							meY = din.readInt();
							
						}
						else
						{
							myX = din.readInt();
							myY = din.readInt(); 
						}
						
					}
				} 
				catch (IOException e) { JOptionPane.showMessageDialog(null, "Server couldn't read", "Error Message", JOptionPane.ERROR_MESSAGE);}
		}
	}
	
	private class WriteToClient implements Runnable
	{
		private int ID;
		private DataOutputStream dout;
		
		public WriteToClient(int i, DataOutputStream j)
		{
			ID = i;
			dout = j;
		}
		
		@Override public void run() 
		{
			//Server knows who is actually sending data
			try
			{
				while(true)//So server can send data at any time
				{
					if(ID==1)
					{//All what you want to send to ID=1
						dout.writeInt(myX);
						dout.writeInt(myY);
						dout.flush();
					}
					else
					{
						dout.writeInt(meX);
						dout.writeInt(meY);
						dout.flush();
					}
					 
					try {//On juste que ca ne soit pas trop trop rapide 
						Thread.sleep(25);
					} catch (InterruptedException e) {}
				}
				
			} 
			catch (IOException e) { JOptionPane.showMessageDialog(null, "Server couldn't write", "Error Message", JOptionPane.ERROR_MESSAGE);}
		}
		
		public void ready()
		{
			try {
				dout.writeUTF("Game can start!");
			} 
			catch (IOException e) { JOptionPane.showMessageDialog(null, "couldn't be ready", "Error Message", JOptionPane.ERROR_MESSAGE);}
		}
	}

	
	
	
	public static void main(String[] args)
	{
		new Server2Player();
	}

}
