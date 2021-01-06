package netzteil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class bouge extends JPanel implements KeyListener
{

	private int x1, x2, y1, y2, id, move=10;
	JFrame f;
	JMenuBar mbar;
	JMenu menu;
	JMenuItem mItem1, mItem2;
	JButton btn;
	JButton chat;
	LeftPanel right;
	private boolean closeRight = false;
	
	public bouge() 
	{
		
		addKeyListener(this);
		setFocusable(true);
		f = new JFrame("Bouge");
		right = new LeftPanel();
		mbar = new JMenuBar();
		menu = new JMenu("Menu");
		mItem1 = new JMenuItem("chat");
		
		mItem1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!closeRight)
				{
					f.add(right, BorderLayout.EAST);
					f.setSize(800,550);
					closeRight=true;
				}
				else
				{
					f.remove(right);
					closeRight = false;
					f.pack();
				}
			}
		});
		mItem2 = new JMenuItem("add");	
		menu.add(mItem1);
		menu.add(mItem2);
		mbar.add(menu);
		f.setJMenuBar(mbar);
		
		
		
		id = 2;
		x1 = 100;
		y1 = 200;
		x2 = 200;
		y2 = 200;
		
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(500,550));
		
		//setLayout(new BorderLayout());
		

		f.setLayout(new BorderLayout());
		f.add(mbar, BorderLayout.NORTH);
		f.add(this, BorderLayout.CENTER);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setPreferredSize(new Dimension(500,550));
		f.pack();
	}
	
	@Override public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		
		if(id==1)
		{
			g2.setColor(Color.YELLOW);
			g2.fill(new Ellipse2D.Double(x1, y1, 40, 40));
			
			g2.setColor(Color.CYAN);
			g2.fill(new Ellipse2D.Double(x2, y2, 40, 40));
		}
		else
		{
			g2.setColor(Color.YELLOW);
			g2.fill(new Ellipse2D.Double(x2, y2, 40, 40));
			
			g2.setColor(Color.CYAN);
			g2.fill(new Ellipse2D.Double(x1, y1, 40, 40));
		}
		
	}
	
	@Override public void keyPressed(KeyEvent e) 
	{
		int key = e.getKeyCode();
		
		if(id==1)
		{
			switch(key)
			{
			case KeyEvent.VK_UP:
				if(y1-move>=0)
					y1-=move;
				break;
				
			case KeyEvent.VK_DOWN:
				if(y1+move<=getHeight()-40)
					y1 += move;
				break;
			case KeyEvent.VK_LEFT:
				if(x1-move>=0)
					x1 -=move;
				break;
			case KeyEvent.VK_RIGHT:
				if(x1+move<=getWidth()-40)
					x1 +=move;
				break;
			}
		}
		
		else
		{
			switch(key)
			{
			case KeyEvent.VK_UP:
				if(y2-move>=0)
					y2-=move;
				break;
				
			case KeyEvent.VK_DOWN:
				if(y2+move<=getHeight()-40)
					y2 +=move;
				break;
			case KeyEvent.VK_LEFT:
				if(x2-move>=0)
					x2 -=move;
				break;
			case KeyEvent.VK_RIGHT:
				if(x2+move<=getWidth()-40)
					x2+=move;
				break;
			}
		}
		
		System.out.println("OUI");
		repaint();
	}
	
	@Override public void keyTyped(KeyEvent e) {}
	@Override public void keyReleased(KeyEvent e) {}

	public static void main(String[] args)
	{
		
		new bouge();
	}

}
